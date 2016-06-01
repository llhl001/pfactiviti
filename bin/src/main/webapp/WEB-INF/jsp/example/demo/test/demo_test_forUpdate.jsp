<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增/修改 示例页面</title>
<%@ include file="../../../common/common_header.jsp"%>
</head>
<body>
<form action="${ctx }/demoTest/update.do" method="post">

<input name="TEST_ID" type="text"/>
<input name="VALUE" type="text"/>
<input name="TEXT" type="text"/>
<input type="submit" value="提交"/>
</form>
</body>
</html>