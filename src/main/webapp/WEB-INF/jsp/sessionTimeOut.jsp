<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="./common/common_header.jsp"%>
<title>会话超时</title>
<%-- <%@ include file="public/header.jsp"%> --%>
</head>
<body class="bgray">

	<script>
		alert('您的会话已超时，请重新登录！');
		window.top.location = '${ctx }/login.do';
	</script>
</body>
</html>




