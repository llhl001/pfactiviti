package com.sdyy.example.demo.jasper.controller;
/**
 * 说明：
 * 本示例只展示了java代码的写法，
 * 用到的jasper文件需要用iReport工具生成
 * 
 * 本示例的jasper文件中使用java的bean为数据源
 * 对于以数据库connection为数据源的方式生成的jasper的使用办法也写在了注释掉的代码中
 * 
 * iReport的工具使用并非一两句能说完，而且控件又多种多样，并非我一个人在短时间内可以做完，希望可以和大家共同研究进步
 */

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sdyy.example.demo.jasper.model.ReportDemo;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

@Controller
@RequestMapping("/reportDemo/")
public class ReportDemoController {
	@RequestMapping("forDemo")
	public void forDemo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("title", "各类奖励统计");
		//String filePath=request.getServletContext().getRealPath("reports")+"/report2.jasper";
		System.out.println("home.jsp");
		String filePath = ReportDemoController.class.getClassLoader().getResource("jasper\\demo.jasper").toString();
		File reportFile = new File("E:/workweb/wtpwebapps/platform/WEB-INF/classes/jasper/demo.jasper");
		JRDataSource dataSource = null;
		
		List<ReportDemo> list = new ArrayList<ReportDemo>();
		ReportDemo rec = new ReportDemo();
		rec.setId("1");
		rec.setName("name1");
		list.add(rec);
		rec = new ReportDemo();
		rec.setId("2");
		rec.setName("名字");
		list.add(rec);
		
		dataSource = new JRBeanCollectionDataSource(list);
		try{
			
			
//			$P{},就是从java程序给它传的参数，放在map里面，用JasperFillManager.fillReport时的第二个参数就是它
//			$V{},就相当于在模板里定义的变量，它可以进行运算，但是不能用java赋值
//
//			$F{},当然就是报表中的数据了，JasperFillManager.fillReport的第三个参数,用JRBeanCollectionDataSource包一下
			JasperReport jasperReport =(JasperReport)JRLoader.loadObject(reportFile);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,dataSource);
			//JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,JDBCUtil.getConnection());
			HtmlExporter htmlExporter = new HtmlExporter();
			htmlExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			htmlExporter.setExporterOutput( new SimpleHtmlExporterOutput(response.getOutputStream(),"UTF-8"));
			//htmlExporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,Boolean.FALSE);
			htmlExporter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("forPrintDemo")
	public void forPrintDemo(HttpServletRequest request,HttpServletResponse response,String printType) throws IOException{
		printType = "excel";
		
		response.setCharacterEncoding("UTF-8");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("title", "各类奖励统计");
		//String filePath=request.getServletContext().getRealPath("reports")+"/report2.jasper";
		System.out.println("home.jsp");
		String filePath = ReportDemoController.class.getClassLoader().getResource("jasper\\demo.jasper").toString();
		File reportFile = new File("E:/workweb/wtpwebapps/platform/WEB-INF/classes/jasper/demo.jasper");
		JRDataSource dataSource = null;
		
		List<ReportDemo> list = new ArrayList<ReportDemo>();
		ReportDemo rec = new ReportDemo();
		rec.setId("1");
		rec.setName("name1");
		list.add(rec);
		rec = new ReportDemo();
		rec.setId("2");
		rec.setName("名字");
		list.add(rec);
		
		dataSource = new JRBeanCollectionDataSource(list);
		try{
			
			
//			$P{},就是从java程序给它传的参数，放在map里面，用JasperFillManager.fillReport时的第二个参数就是它
//			$V{},就相当于在模板里定义的变量，它可以进行运算，但是不能用java赋值
//			$F{},当然就是报表中的数据了，JasperFillManager.fillReport的第三个参数,用JRBeanCollectionDataSource包一下
			JasperReport jasperReport =(JasperReport)JRLoader.loadObject(reportFile);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,dataSource);
			
			OutputStream ouputStream = response.getOutputStream();
			if (printType.equals("excel")) {
				
				String fileName =  java.net.URLEncoder.encode("文件名.xls", "UTF-8");
				response.addHeader("content-disposition", "inline; filename=" + fileName);
			response.setContentType("application/vnd.ms-excel");
			//response.setHeader("Content-Disposition", "attachment;filename=first.xls");
			JRXlsExporter  exporter = new JRXlsExporter();
				exporter.setParameter(JRHtmlExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
				//exporter.setParameter(JRHtmlExporterParameter.OUTPUT_WRITER,response.getWriter());
				exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,Boolean.FALSE);
				exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "utf-8");
				exporter.exportReport();
			}else if (printType.equals("pdf")) {
			response.setContentType("application/pdf");
			//response.setHeader("Content-Disposition", "attachment;filename=first.pdf");
			JRPdfExporter  exporter = new JRPdfExporter();
				exporter.setParameter(JRHtmlExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
				//exporter.setParameter(JRHtmlExporterParameter.OUTPUT_WRITER,response.getWriter());
				exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,Boolean.FALSE);
				exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "utf-8");
				exporter.exportReport();
			}
			ouputStream.close();
			
			
			//JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,JDBCUtil.getConnection());
				/*JRHtmlExporter exporter = new JRHtmlExporter();
				exporter.setParameter(JRHtmlExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRHtmlExporterParameter.OUTPUT_WRITER,response.getWriter());
				exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,Boolean.FALSE);
				exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "utf-8");
				exporter.exportReport();*/
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

}
