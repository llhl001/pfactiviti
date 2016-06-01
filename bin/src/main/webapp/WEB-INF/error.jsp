<%@ page language="java" import="java.util.*,org.apache.log4j.Logger"
	pageEncoding="utf8"%>
<%-- 
    Document   : error
    Created on : 2015年7月20日15:58:43
    Author     : liuyx
--%>


<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exception!</title>
</head>
<body>
	<%
		Exception ex = (Exception) request.getAttribute("exception");
	%>
	<H2>error!</H2>

	<div style="color: red;">
		<%
			if (request.getAttribute("sysMsg") != null) {
		%>
		<%=request.getAttribute("sysMsg")%>
		<%
			}
		%>
	</div>
	<%--=ex.getMessage()--%>
	<div style="margin-top: 10px;">
		<!-- <a href="javascript:;" onclick="window.history.go(-1)">返回</a> -->
	</div>
	<P />
	<%
		Logger log = Logger.getLogger(this.getClass());
		if (ex != null) {
			//log.info(ex.getMessage());
			//ex.printStackTrace();
		}
	%>
</body>
</html>