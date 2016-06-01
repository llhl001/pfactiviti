/**
 * Copyright2015-2019 山东亿云信息技术有限公司 All Rights Reserved.
 */
package platform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @ClassName: SendWeixinMsg
 * @Description: TODO
 * @author: liuyx 
 * @date: 2015年10月10日下午2:14:58
 */
public class SendWeixinMsg {
	/**
	 * 获取accesstoken 需要的 corpid
	 */
	private static String ACCESS_TOKEN_CORPID = "wxba1f64b5c4e14174";
	/**
	 * 获取accesstoken 需要的corpsecret
	 */
	private static String ACCESS_TOKEN_CORPSECRET = "MpvBNMTctjVyqZGvCsW9l1EcVMwWSX5nxFU9R0d3BhyPLtSATkkKT3cxnumCXOoM";
	public static void main(String arg[]) {
		try {
			sendMsg("@all","大家好，这是刘宇祥测试消息",null);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void sendMsg(String toUser, String content, HttpServletRequest request) throws IOException, ClientProtocolException {
		String SEND_MESSAGE_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + getAccessTokenFromContext();
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

		Map<String, Object> params = new HashMap<String, Object>();

		HttpPost post = new HttpPost(SEND_MESSAGE_URL);
		params.put("touser", toUser);
		params.put("msgtype", "news");
		params.put("agentid", "0");
		Map<String, Object> news = new HashMap<String, Object>();
		List articles = new ArrayList();
		Map<String, Object> article = new HashMap<String, Object>();
		article.put("title", "我院与菏泽市人民政府科技合作");
		article.put("picurl", "http://www.sdas.org/u/cms/www/201509/181259362a76.jpg");
		article.put("url", "http://www.sdas.org/tpxw/6063.htm");
		articles.add(article);
		article = new HashMap<String, Object>();
		article.put("title", "情报所一项目通过鉴定");
		article.put("url", "http://www.sdas.org/kydt/6069.htm");
		articles.add(article);
		news.put("articles", articles);
		params.put("news", news);
		/* 简单消息
		 * params.put("msgtype", "text");
		params.put("agentid", "0");
		Map<String, Object> t = new HashMap<String, Object>();
		t.put("content", content.toString());
		params.put("text", t);*/

		System.out.println(gson.toJson(params));

		post.setEntity(new StringEntity(gson.toJson(params), "UTF-8"));
		// 设置编码
		HttpResponse re = new DefaultHttpClient().execute(post);
		// 发送Post,并返回一个HttpResponse对象
		if (re.getStatusLine().getStatusCode() == 200) {// 如果状态码为200,就是正常返回
			String result = EntityUtils.toString(re.getEntity());
			// 得到返回的字符串
			System.out.println(result);
			// 打印输出
		}
		post.releaseConnection();
	}
	
	/**
	 * 获取accesstoken
	 * 
	 * @param request
	 *            accessToken一天只能请求2000次每次有效期为7200秒（2小时）因此需要把accesstoken全局的保存起来
	 * @return
	 */
	public static String getAccessTokenFromContext() {
		String accessToken = null;
		//ServletContext context = request.getSession().getServletContext();
		/*Date createTime = null;//(Date) context.getAttribute("accesstoken");
		if (createTime == null) {// 尚未存储accesstoken
			accessToken = getAccessTokenByUrl();
			//setAccessTokenToContext(request, accessToken);

		} else {
			long a = createTime.getTime();
			long b = new Date().getTime();
			int seconds = (int) ((b - a) / 1000);
			if (seconds >= 6500) {// 快过期了，再取一次
				accessToken = getAccessTokenByUrl();
				//setAccessTokenToContext(request, accessToken);
			} else {// 用原来的
				accessToken = (String) context.getAttribute("accesstoken");
			}
			accessToken = getAccessTokenByUrl();
		}*/
		accessToken = getAccessTokenByUrl();
		return accessToken;
	}
	
	public static String getAccessTokenByUrl() {
		String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + ACCESS_TOKEN_CORPID + "&corpsecret="
				+ ACCESS_TOKEN_CORPSECRET;
		int tryCount = 0;
		// 获取token最多试五次
		while (true) {
			tryCount = tryCount + 1;
			if (tryCount >= 5) {
				return null;
			}
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(ACCESS_TOKEN_URL);
			JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
			String result = null;
			try {
				HttpResponse res = client.execute(get);
				// 响应内容
				String responseContent = null;
				HttpEntity entity = res.getEntity();
				responseContent = EntityUtils.toString(entity, "UTF-8");
				JsonObject json = jsonparer.parse(responseContent).getAsJsonObject();
				// 将json字符串转换为json对象
				if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					if (json.get("errcode") != null) {
						// 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}
						System.out.println(responseContent.toString());
					} else {// 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
						result = json.get("access_token").getAsString();
					}
				}
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 关闭连接 ,释放资源
				client.getConnectionManager().shutdown();
				if (result != null) {
					return result;
				}
			}
		}
	}
	
	
	private void setAccessTokenToContext(HttpServletRequest request, String accessToken) {
		if (accessToken == null) {
			System.out.println("服务器端异常，错误代码：ACCESS_TOKEN get error");
			return;
		}

		ServletContext context = request.getSession().getServletContext();
		context.setAttribute("createtime", new Date());
		context.setAttribute("accesstoken", accessToken);
	}
}
