/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package com.sdyy.base.sys.sys_file.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.sdyy.base.sys.sys_file.service.ISysFileService;
import com.sdyy.common.RetObj;
import com.sdyy.common.service.ConfigService;
import com.sdyy.common.utils.DateUtils;

/**
 * @ClassName: SysFileController
 * @Description: 文件管理：上传、下载、文件表操作
 * @author: liuyx 
 * @date: 2015年10月10日上午8:50:22
 */
@Controller
@RequestMapping("/sysFile")
public class SysFileController {

	@Autowired
	private ISysFileService sysFileService;

	@PostConstruct
	public void init() throws ServletException {
		String tempDir = ConfigService.getConfig(ConfigService.TMP_DIR);
		String uploadDir = ConfigService.getConfig(ConfigService.UPLOAD_DIR);
		if (!new File(tempDir).exists()) {
			new File(tempDir).mkdirs();
		}
		if (!new File(uploadDir).exists()) {
			new File(uploadDir).mkdirs();
		}
	}
	
	@RequestMapping("/forDemo")
	public String forDemo() {
		return "base/sys/sys_file/sys_file_forDemo";
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param chunk分割块数
	 * @param chunks总分割数
	 * @param uuid分割时防止几个人同时上传相同文件名的文件
	 * 
	 * @return
	 */
	@RequestMapping(value = "batchUpload", method = RequestMethod.POST)
	public void batchUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam ArrayList<MultipartFile> multipartFiles,
			Integer chunk, Integer chunks, String uuid) {

		RetObj ret = new RetObj();
		String name = null;// 文件名
		try {
			String fileId = null;
			int size = 0;
			String newFileName = null;
			String fileName = null;
			String refileName="";
			String fileType="";
			String now =DateUtils.getCurrentDate();
			String ymd =DateUtils.getCurrentDate("yyyyMMdd");
			String uploadDir = ConfigService.getConfig(ConfigService.UPLOAD_DIR)+"/" + ymd + "/";
			// 创建文件夹  
			File file = new File(uploadDir);    
			if (!file.exists()) {    
				file.mkdirs();    
			}
			List<Map> fileInfoMaps = new ArrayList<Map>();
			Map fileInfo = new HashMap();
			for (MultipartFile multipartFile : multipartFiles) {
				name = multipartFile.getOriginalFilename();
				fileType = FilenameUtils.getExtension(name);
				fileId = UUID.randomUUID().toString().replace("-", "");
				newFileName = fileId.concat(".").concat(fileType);
				String nFname = newFileName;
				if (chunk != null) {
					nFname = uuid + "_" + chunk + "_" + name;
				}
				File savedFile = new File(uploadDir, nFname);
				multipartFile.transferTo(savedFile);

				if (chunk != null && chunk + 1 == chunks) {
					try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(
							uploadDir, newFileName)));) {
						// 遍历文件合并
						for (int i = 0; i < chunks; i++) {
							File tempFile = new File(uploadDir, uuid + "_" + i + "_" + name);
							byte[] bytes = FileUtils.readFileToByteArray(tempFile);
							outputStream.write(bytes);
							outputStream.flush();
							tempFile.delete();
						}
						outputStream.flush();
					}
				}
				
				
				fileInfo = new HashMap();
				fileInfo.put("FILE_ID", fileId);
				fileInfo.put("MAIN_ID", null);
				fileInfo.put("FILE_NAME", name);
				fileInfo.put("FILE_REAL_NAME", newFileName);
				fileInfo.put("FILE_TYPE", fileType);
				fileInfo.put("FILE_LOCATION", uploadDir);
				fileInfo.put("UPLOAD_TIME",now );
				fileInfo.put("UPLOADER", null);
				fileInfo.put("UPDATE_TIME", null);
				fileInfo.put("UPDATER", null);
				fileInfo.put("INVALID_TIME", null);
				fileInfo.put("INVALIDER", null);
				fileInfo.put("STATUS", "0");
				fileInfo.put("REMARK", null);
				
				size = 0;

				try (FileInputStream inputStream = new FileInputStream(new File(ConfigService.getConfig(ConfigService.UPLOAD_DIR), newFileName));) {
					size = inputStream.available() / 1024;
				} catch (FileNotFoundException ex) {
					// 因文件分片此处可能报nofilefound异常 忽略
					// ex.printStackTrace();
				}
				
				fileInfo.put("SIZE", size);
				
				fileInfoMaps.add(fileInfo);

			}

			sysFileService.batchInsert(fileInfoMaps);
			
			
			
			ret = new RetObj(true, "操作成功",fileInfoMaps);
		} catch (Exception e) {
			e.printStackTrace();
			ret = new RetObj(false, "操作失败");
		}
		
		response.setContentType("application/json; charset=UTF-8");
		try(PrintWriter pw = response.getWriter();) {
			
			pw.write(JSON.toJSONString(ret));
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@RequestMapping(value="/downloadFile")  
	public void downloadFile(HttpServletResponse response,HttpServletRequest request) {
		String fileId=request.getParameter("FILE_ID");
		Map fileMap;
		String fileLocation = "";
		String fileName = "";
		String fileRealName = "";
		try {
			fileMap = sysFileService.get(fileId);
        	if(fileMap != null) {
        		fileLocation = fileMap.get("FILE_LOCATION").toString();
        		fileName = fileMap.get("FILE_NAME").toString();
        		fileRealName = fileMap.get("FILE_REAL_NAME").toString();
        	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
            //创建file对象
            File file=new File(fileLocation+fileRealName);
            //设置response的编码方式
            response.setContentType("application/x-msdownload");
            //写明要下载的文件的大小
            response.setContentLength((int)file.length());
            //解决中文乱码
            response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("gbk"),"iso-8859-1"));       

            //读出文件到i/o流
            FileInputStream fis=new FileInputStream(file);
            BufferedInputStream buff=new BufferedInputStream(fis);

            byte [] b=new byte[1024];//相当于我们的缓存
            long k=0;//该值用于计算当前实际下载了多少字节
            //从response对象中得到输出流,准备下载
            OutputStream myout=response.getOutputStream();
            //开始循环下载
            while(k<file.length()){

                int j=buff.read(b,0,1024);
                k+=j;
                //将b中的数据写到客户端的内存
                myout.write(b,0,j);

            }
            //将写入到客户端的内存的数据,刷新到磁盘
            myout.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
