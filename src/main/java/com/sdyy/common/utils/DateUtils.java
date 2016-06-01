package com.sdyy.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static String nowSysTime="";
	public static final String DEFAULT_DATE_TYPE="yyyy-MM-dd HH:mm:ss";

	/**
	 * 
	 * @Title: getCurrentDate
	 * @author：liuyx
	 * @date：2015年12月22日下午4:01:31
	 * @Description: 根据格式获取当前时间
	 * @param str 	时间格式
	 * @return
	 */
	public static String getCurrentDate(String str) {
		SimpleDateFormat df=new SimpleDateFormat(str); 
		Calendar rightNow = Calendar.getInstance();
		return df.format(rightNow.getTime());
	}
	/**
	 * 
	 * @Title: parseDate
	 * @author：liuyx
	 * @date：2015年12月22日下午4:02:12
	 * @Description: 根据格式将字符串转为时间
	 * @param strFormat 日期格式，可以为null
	 * @param dateValue 日期字符串
	 * @return
	 */
	public static Date parseDate(String strFormat, String dateValue) {	
		if (dateValue == null)		
			return null;	
		if (strFormat == null)	
			strFormat = DEFAULT_DATE_TYPE;
			SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);	
		Date newDate = null;	
		try {		
			newDate = dateFormat.parse(dateValue);	
		} catch (ParseException pe) {	
				newDate = null;	
	   }	
		return newDate;
	}
	/**
	 * 
	 *@Description: 获取当前时间 格式为yyyy-MM-dd HH:mm:ss
	 *@return
	 */
	public static String getCurrentDate() {
		SimpleDateFormat df=new SimpleDateFormat(DEFAULT_DATE_TYPE); 
		Calendar rightNow = Calendar.getInstance();
		return df.format(rightNow.getTime());
	}
}
