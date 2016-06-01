/*
 * @(#)StringUtils.java caojian 2012-4-16 下午11:15:51
 * Copyright (C)1997-2012 山东重信通用软件有限责任公司
 * All Rights Reserved.
 */
package com.sdyy.common.utils;
 
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.sdyy.common.spring.ComponentFactory;

/**
 * 
 * @ClassName: StringUtils
 * @Description: TODO
 * @author: liuyx 
 * @date: 2015年9月18日下午2:52:50
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {
	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request){
		String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}
	/**
	 * 缩略字符串（不区分中英文字符）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}
	
	/**
	 * 
	 * @Description: 生成UUID，UUID生成用到当前时间和网卡号，现有技术很难会出现重复
	 * @author liuyx
	 * @date 2015年9月18日14:55:48
	 * @return
	 */
	public static String uniqueKey() {// 32位长
		String key = UUID.randomUUID().toString();
		key = key.replace("-", "");
		return key.toLowerCase();// .toUpperCase();
	}
	public static String uniqueKey36() {// 36位长
		String key = UUID.randomUUID().toString();
//		key = key.replace("-", "");
		return key.toLowerCase();// .toUpperCase();
	}
	/**
	 * 
	 * @Title: isEmpty
	 * @author：liuyx 
	 * @date：2015年9月18日下午2:54:12
	 * @Description: TODO
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		} else {
			return str.trim().length() == 0;
		}
	}
	
	public static boolean isEmpty(Object str) {
		if (str == null) {
			return true;
		} else {
			return str.toString().trim().length() == 0;
		}
	}

	/**
	 * 
	 * @Title: getTrimStr
	 * @author：liuyx 
	 * @date：2015年9月20日下午12:01:57
	 * @Description: 获取trim后的字符串，如果是NULL则返回空字符串
	 * @param obj
	 * @return
	 */
	public static String getTrimStr(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString().trim();
		}
	}
	
	/**
	 * 
	 *@Description: TODO(用一句话描述该文件做什么)
	 *@author 曹建
	 *@date 2012-10-9 下午1:56:29
	 *@param strSource  源字符串
	 *@param strFrom要替换的子串
	 *@param strTo  替换为的字符串
	 *@return  返回字符串
	 */
	public static String replace(String strSource, String strFrom, String strTo) {
		// 如果要替换的子串为空，则直接返回源串
		if (strFrom == null || strFrom.equals(""))
			return strSource;
		String strDest = "";
		// 要替换的子串长度
		int intFromLen = strFrom.length();
		int intPos;
		// 循环替换字符串
		while ((intPos = strSource.indexOf(strFrom)) != -1) {
			// 获取匹配字符串的左边子串
			strDest = strDest + strSource.substring(0, intPos);
			// 加上替换后的子串
			strDest = strDest + strTo;
			// 修改源串为匹配子串后的子串
			strSource = strSource.substring(intPos + intFromLen);
		}
		// 加上没有匹配的子串
		strDest = strDest + strSource;
		// 返回
		return strDest;
	}
	//替换特殊字符
	public static String replaceSpeChar(String replaceText) {
		if(null != replaceText && !"".equals(replaceText)){
			String[] speCharArray = {"<br />" , "&micro;" , "</f14>" , "<f14>" , "&Phi;" , "&nbsp;", "&amp;" , "&times;" , "&mu;" , "&quot;" , "&plusmn;" , "&alpha;" , "&phi;" , "&ldquo;" , "&rdquo;" , "&trade;" , "&reg;" , "&divide;" , "<div>" , "</div>" , "<DIV>" , "</DIV>"};
			String[] parCharArray = {"" , "µ" , "" , "" , "φ" , " " , "&" , "×" , "μ" , "\"" , "±" , "α" , "φ" , "“" , "”" , "™" , "®" , "÷" , "" , "" , "" , ""};
			for (int i = 0; i < speCharArray.length; i++) {
				while (replaceText.indexOf(speCharArray[i]) != -1) {
	    			int j = replaceText.indexOf(speCharArray[i]);
	    			replaceText = replaceText.substring(0, j) + parCharArray[i] 
	    			                 + replaceText.substring(j + speCharArray[i].length());
	    		}
			}
		}
    	return replaceText;
    }
	
	/**
	 * 
	 * @Title: getGlobMessage
	 * @author：liuyx 
	 * @date：2015年9月21日下午3:18:46
	 * @Description: 获取全球化文本内容
	 * @param key
	 * @param args
	 * @param request
	 * @return
	 */
	public static String getGlobMessage(String key,Object[] args,HttpServletRequest request) {
		ApplicationContext applicationContext = ComponentFactory.getCtx();
		return applicationContext.getMessage(key,args, RequestContextUtils.getLocale(request));
	}
	
	public static String getGlobMessage(String key,HttpServletRequest request) {
		ApplicationContext applicationContext =  ComponentFactory.getCtx();
		return applicationContext.getMessage(key,null, RequestContextUtils.getLocale(request));
	}
	
	public static void main(String[] args) {   
		System.out.println(getStrJoinQuotes("djauirwhp,sadjfiowwwww"));
//		String s1 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//		String s2 = "ysG3MHPZdLx5vO1N0TeRtcmljkziK4bECWoIpuJ62AVrBUDnX9SgFYq7wfh8aQ";
//		Map<Character, Character> charMap = new HashMap<Character, Character>(s1.length());   
//		for (int i = 0; i < s1.length(); i++) {       
//			charMap.put(s1.charAt(i), s2.charAt(i));   
//			}         
//		String src = "abcd123 456";   
//		char[] arrChr = new char[src.length()];   
//		for (int i = 0; i < src.length(); i++) { 
//			// decode       
//			Character c = charMap.get(src.charAt(i));        
//			arrChr[i] = (c != null) ? c : ' ';   
//		}   
//		System.out.println(String.valueOf(arrChr));
		
		
/*		System.out.println("36位："+uniqueKey36());
		 String specialStr = "&divide;";
	        String str1 = HtmlUtils.htmlEscape(specialStr); //①转换为HTML转义字符表示
	        System.out.println(str1);
	       
	        String str2 = HtmlUtils.htmlEscapeDecimal(specialStr);// ②转换为数据转义表示
	        System.out.println(str2);
	       
	        String str3 = HtmlUtils.htmlEscapeHex(specialStr); //③转换为十六进制数据转义表示
	        System.out.println(str3);
	        
	       // ④下面对转义后字符串进行反向操作
	        System.out.println(HtmlUtils.htmlUnescape(specialStr));
	        System.out.println(HtmlUtils.htmlUnescape(str2));
	        System.out.println(HtmlUtils.htmlUnescape(str3));*/

	}
	
	public static String getStrJoinQuotes(String str) {
		String[] strs = str.split(",");
		StringBuffer sb = new StringBuffer("");
		for(String s:strs) {
			sb.append(",'"+s+"'");
		}
		return sb.toString().substring(1);
	}

}
