/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package platform;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.alibaba.fastjson.JSON;

import javax.xml.bind.*;

/**
 * @ClassName: WebServiceTest
 * @Description: TODO
 * @author: liuyx 
 * @date: 2015年9月27日下午5:22:15
 */
public class WebServiceTest {
	private static JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
	private static final String testUrl = "http://localhost:8080/platform/webService/TestWebservice?wsdl";
    public static Client client = dcf.createClient("http://172.16.10.86:8080/jeecms/webService/appChannelService?wsdl");
	public static void main(String[] args) throws Exception {
		
        //sayHello 为接口中定义的方法名称   张三为传递的参数   返回一个Object数组
        Object[] objects=client.invoke("getAppContent", 589); 
        //输出调用结果
        System.out.println(JSON.toJSONString(objects));
	}
}
