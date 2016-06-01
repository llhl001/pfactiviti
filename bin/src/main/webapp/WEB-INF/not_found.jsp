<%@ page language="java" import="java.util.*,org.apache.log4j.Logger"
	pageEncoding="utf8"%>
<%-- 
    Document   : error
    Created on : 2015年7月20日15:58:30
    Author     : liuyx
--%>


<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Not Found!</title>
<%@ include file="./jsp/common/common_header.jsp"%>
<script language="javascript">
	$(function(){
    $('.error').css({'position':'absolute','left':($(window).width()-490)/2});
	$(window).resize(function(){  
    $('.error').css({'position':'absolute','left':($(window).width()-490)/2});
    })  
});  
</script>
</head>
<body style="background:#edf6fa;">

	<!-- <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">404错误提示</a></li>
    </ul>
    </div> -->
    
    <div class="error">
    
    <h2>非常遗憾，您访问的页面不存在！</h2>
    <%--=ex.getMessage()--%>
	<div>
		<!-- <a href="javascript:;" onclick="window.history.go(-1)">返回</a> -->
	</div>
    <!-- <div class="reindex"><a href="main.html" target="_parent">返回首页</a></div> -->
    
    </div>


</body>
</html>