/**
 * 
 */
package com.sdyy.common.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author caojian email:caojian@keylab.net time： 2009-7-22上午01:25:55
 *         由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供相对精 确的浮点数运算，包括加减乘除和四舍五入。
 */
public final class Arithmetic {

	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;

	private Arithmetic() {
	}

	/**
	 * 
	 * 提供精确的加法运算。
	 * 
	 * @param v1被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {

		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 
	 * 提供精确的减法运算。
	 * 
	 * @param v1被减数
	 * @param v2减数
	 * @return 两个参数的差
	 * 
	 */
	public static double sub(double v1, double v2) {

		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();

	}

	/**
	 * 
	 * 提供精确的乘法运算。
	 * 
	 * @param v1被乘数
	 * @param v2乘数
	 * @return 两个参数的积
	 * 
	 */
	public static double mul(double v1, double v2) {

		BigDecimal b1 = new BigDecimal(Double.toString(v1));

		BigDecimal b2 = new BigDecimal(Double.toString(v2));

		return b1.multiply(b2).doubleValue();

	}

	/**
	 * 
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 * 
	 */
	public static double div(double v1, double v2) {

		return div(v1, v2, DEF_DIV_SCALE);

	}

	/**
	 * 
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 * 
	 */
	public static double div(double v1, double v2, int scale) {

		if (scale < 0) {

			throw new IllegalArgumentException(

			"The scale must be a positive integer or zero");

		}

		BigDecimal b1 = new BigDecimal(Double.toString(v1));

		BigDecimal b2 = new BigDecimal(Double.toString(v2));

		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

	}

	/**
	 * 
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v需要四舍五入的数字
	 * @param scale小数点后保留几位
	 * @return 四舍五入后的结果
	 * 
	 */
	public static double round(double v, int scale) {

		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * double非科学计数法表示
	 * 
	 * @param var输入double型参数（科学计数法也可以）
	 * @param format等于输出参数的格式如0.00000
	 * @return 输出字符串型的值
	 */
	public static String doubleToStr(double var, String format) {
		DecimalFormat df = (DecimalFormat) NumberFormat.getPercentInstance();
		df.applyPattern(format);
		String temp = df.format(var);
		return temp;
	}

	/**
	 * 将BIgDecimal类型转换为Percent
	 * 
	 * @author caojian 2010-9-23 上午11:20:49
	 * @param decimal
	 * @return
	 */
	public static String bigdecimalToPercent(BigDecimal decimal) {
		double percent = decimal.doubleValue();// 
		// 获取格式化对象
		NumberFormat nt = NumberFormat.getPercentInstance();
		// 设置百分数精确度2即保留两位小数
		nt.setMinimumFractionDigits(2);

		return nt.format(percent);
	}

	/**
	 * 将double类型转化为Percent
	 * 
	 * @param decimal
	 * @return
	 */
	public static String doubleToPercent(double d) {
		// 获取格式化对象
		NumberFormat nt = NumberFormat.getPercentInstance();
		// 设置百分数精确度2即保留两位小数
		nt.setMinimumFractionDigits(2);

		return nt.format(d);
	}

	/**
	 * @param args
	 */
	 
	   public static void main(String[] args) { // TODO Auto-generated method
		  System.out.print(div(11, 2, 2)); 
		   }
	 
	/**
	 * 
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v需要四舍五入的数字
	 * @param scale小数点后保留几位
	 * @return 四舍五入后的结果 tanwn添加BigDecimal四舍五入处理
	 */
	public static BigDecimal round(BigDecimal v, int scale) {

		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal one = new BigDecimal("1");
		return v.divide(one, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 
	 * 提供BigDecimal开平方的方法。
	 * 
	 * @param v需要四舍五入的数字
	 * @param scale小数点后保留几位
	 * @return 四舍五入后的结果 tanwn添加
	 */
	public static BigDecimal sqrt(BigDecimal num) {
		if (num.compareTo(BigDecimal.ZERO) < 0) {
			return BigDecimal.ZERO;
		}
		BigDecimal x = num.divide(new BigDecimal("2"), MathContext.DECIMAL128);
		while (x.subtract(x = sqrtIteration(x, num)).abs().compareTo(
				new BigDecimal("0.0000000000000000000001")) > 0)
			;
		return x;
	}

	private static BigDecimal sqrtIteration(BigDecimal x, BigDecimal n) {
		return x.add(n.divide(x, MathContext.DECIMAL128)).divide(
				new BigDecimal("2"), MathContext.DECIMAL128);
	}

	/**
	 * 起始值与结束值必须大于1
	 * 
	 * @param start
	 * @param end
	 * @return >= start && <=end 的随机数 end需大于start 否则返回0
	 */
	public static int getRandom(int start, int end) {
		if (start > end || start < 0 || end < 0) {
			return 0;
		}
		return (int) (Math.random() * (end - start + 1)) + start;
	}

}
