<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>主页界面</title>
<%@ include file="../../common/common_header.jsp"%>
<script type="text/javascript" src="${ctx}/component/pushlet/ajax-pushlet-client.js"></script> 

<script type="text/javascript">
      
  	//PL._init(); 无需初始化 否则chrome下会报错  
	//设置该页面监听的主题
	PL.joinListen("/test/pushMessage", "${loginOperator.OPERATOR_ID}");//监听该主题的事件，如果发生该主题的事件，那么onData()方法会被调用  

	function onData(event) {
		if (event.get("message")) {//消息改变;
			//document.getElementById("testPushlet").innerText = decodeURIComponent(event.get("message"));
			$('#testPushlet').append(decodeURIComponent(event.get("message")+"<br/>"))
		}
		if (event.get("sessionCount")) {//消息改变;
			//document.getElementById("testPushlet").innerText = decodeURIComponent(event.get("message"));
			$('#testPushlet').append("当前共有session数量："+event.get("sessionCount")+"<br/>");
		}
	}

	function sendAMessage(userId, message) {
		message=$('#message').val();
		//encodeURIComponent(message);
		//编码存在问题
		//p_publish('/test/pushMessage','message','heihei','p_to','${loginOperator.OPERATOR_ID}'); 
		$.ajax({
			type : "post",
			async : true,
			global : false,
			url : "${ctx}/pushletDemo/pushMessage.do",
			dataType : "html",
			data : {
				"userId" : userId,
				"message" : message
			},
			success : function(data) {

			}
		});
	}
</script>
</head>
<body>
本页面写法请查看：pushlet_forDemo.jsp
<input type="text" id="message"></input>

<button onclick="sendAMessage('${loginOperator.OPERATOR_ID}','本消息通过服务端发送成功')" value="click">发送消息给当前回话用户</button>
	<!-- 如果做成聊天室需要维护多套业务状态，
	例如是否已读未读，通过这个状态结合缓存来存储离线消息，
	何种情况下更新这些状态，相对来说比较复杂，偏业务，暂时不做
	框架暂时仅提供技术支持
	
	另外：后期可以考虑使用支持websocket协议的开源项目来做消息推送，xmpp较为重量级，且存在数据负载过重的问题。
	 -->
	<div id="testPushlet">
		
	</div> 
 
</body>
</html>