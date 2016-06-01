/**
 * 
 */
package com.sdyy.common.utils;

/**
 * @author caojian email:caojian@keylab.net time： 2009-7-22上午03:23:54 字符的编码转换
 *         字符串与ASCII码之间的转换等。
 */
public final class CharSet {
	/**
	 * 说明: ISO_8559_1字符转换为GBK字符
	 * 
	 * @param 需要转换的字符
	 * @return 转换后的GBK字符
	 */
	public static String IsoToGBK(String strIn) {

		String strOut = null;
		if (strIn == null) {
			return "";
		}
		try {
			byte[] b = strIn.getBytes("ISO_8859_1");
			strOut = new String(b, "GBK");
		} catch (java.io.UnsupportedEncodingException e) {
		}
		return strOut;
	}

	/**
	 * 说明: GBK字符转换为ISO_8559_1字符
	 * 
	 * @param 需要转换的GBK字符
	 * @return 转换后的ISO_8559_1字符
	 */
	public static String GBKToIso(String strIn) {
		byte[] b;
		String strOut = null;
		if (strIn == null)
			return "";
		try {
			b = strIn.getBytes("GBK");
			strOut = new String(b, "ISO_8859_1");
		} catch (java.io.UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return strOut;
	}

	/**
	 * 说明: 处理中文字符的乱码问题
	 * 
	 * @param 需要转换的字符
	 * @return 转换后的ISO_8559_1字符
	 */
	public static String encode(String Str) {
		if (Str == null)
			return ("");
		else {
			try {
				String temp_p = Str;
				byte[] temp_t = temp_p.getBytes("ISO8859_1");
				Str = new String(temp_t);
			} catch (Exception e) {
			}

			return (Str.trim());
		}
	}

	/**
	 * ASCII转换为字符串
	 * 
	 * @param str
	 *            传入ASCII码 格式/32993;/32993;
	 * @return 字符串
	 */
	public static String asciiToStr(String str) {
		String str1 = str.replaceAll("/", ",").replaceAll(";", "");
		String[] s2 = str1.split(",");
		String s1 = "";
		for (int i = 1; i < s2.length; i++) {
			int a = Integer.parseInt(s2[i], 10);
			s1 = s1 + (char) a;
		}
		return s1;
	}

	/**
	 * 字符串转换为ASCII码
	 * 
	 * @param str
	 *            传入任意字符串 如 中华人民共和国
	 * @return 返回ASCII码，格式为/20013;/21326;/20154;/27665;/20849;/21644;/22269;
	 */
	public static String strToAscii(String str) {
		char[] chars = str.toCharArray(); // 把字符中转换为字符数组
		String str1 = "";

		for (int i = 0; i < chars.length; i++) {// 输出结果
			str1 = str1 + "/" + (int) chars[i] + ";";
		}
		return str1;
	}

	/**
	 * 将sybase ISO_1字符集转换成java GBK字符集
	 * 
	 * @author lvchq
	 * @time 2010-10-9 下午11:24:01
	 * @param uniStr
	 * @return
	 */
	public static String toGbk(String uniStr) {
		String gbkStr = "";
		if (uniStr == null) {
			uniStr = "";
		}

		try {
			byte[] tempByte = uniStr.getBytes("ISO8859_1");
			gbkStr = new String(tempByte, "GBK");
		} catch (Exception e) {

		}

		return gbkStr;
//		return uniStr;
	}

	/**
	 * 将java GBK字符集转换为sybase ISO_1字符集
	 * 
	 * @author lvchq
	 * @time 2010-10-9 下午11:27:45
	 * @param gbkStr
	 * @return
	 */
	public static String toUni(String gbkStr) {
		String uniStr = "";
		if (gbkStr == null) {
			gbkStr = "";
		}

		try {
			byte[] tempByte = gbkStr.getBytes("GBK");
			uniStr = new String(tempByte, "ISO8859_1");
		} catch (Exception e) {

		}

		return uniStr;
	}

	/**
	 * 测试
	 * 
	 * @param arg
	 */
	public static void main(String[] arg) {
	}
}
