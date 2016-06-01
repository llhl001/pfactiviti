/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package platform;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Test
 * @Description: TODO
 * @author: liuyx 
 * @date: 2015年8月31日下午12:46:08
 */
public class Test implements FatherTest{

	public<T extends Test> void temp1(List<T> tList) {
		
	}
	
	public static void temp2(List<Test> tList) {
		
	}
	
	public static void temp3(Test t) {
		
	}
	
	public static void temp4(List<Object> tList) {
		
	}
	
	public static void main(String[] args) {
		ChildTest c = new ChildTest();
		System.out.println(c instanceof FatherTest);
	}

	/**
	 * @Title: test1
	 * @author：liuyx 
	 * @date：2015年9月5日下午4:19:53
	 * @Description: TODO
	 */
	private static void test1() {
		List<ChildTest> cList = new ArrayList<ChildTest>();
		ChildTest c = new ChildTest();
		cList.add(c);
		new Test().temp1(cList);
		//temp2(cList);
		temp3(c);
	}
}

class ChildTest extends Test{
	
}

interface FatherTest{
	
}
