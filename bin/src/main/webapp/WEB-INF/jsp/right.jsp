<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>主页界面</title>
<%@ include file="common/common_header.jsp"%>
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    </ul>
    </div>
    
    <div class="mainindex">
    
    
    <div class="welinfo">
    <span><img src="${ctx}/skin/default/images/sun.png" alt="天气" /></span>
    <b>Admin早上好，欢迎使用xxxx系统</b>(admin@sdyy.com)
    <a href="#">帐号设置</a>
    </div>
    
    <div class="welinfo">
    <span><img src="${ctx}/skin/default/images/time.png" alt="时间" /></span>
    <i>您上次登录的时间：2013-10-09 15:22</i> （不是您登录的？<a href="#">请点这里</a>）
    </div>
    
    <div class="xline"></div>
    
    <%-- <ul class="iconlist">
    
    <li><img src="${ctx}/skin/default/images/ico01.png" /><p><a href="#">管理设置</a></p></li>
    <li><img src="${ctx}/skin/default/images/ico02.png" /><p><a href="#">发布文章</a></p></li>
    <li><img src="${ctx}/skin/default/images/ico03.png" /><p><a href="#">数据统计</a></p></li>
    <li><img src="${ctx}/skin/default/images/ico04.png" /><p><a href="#">文件上传</a></p></li>
    <li><img src="${ctx}/skin/default/images/ico05.png" /><p><a href="#">目录管理</a></p></li>
    <li><img src="${ctx}/skin/default/images/ico06.png" /><p><a href="#">查询</a></p></li> 
            
    </ul>
    
    <div class="ibox"><a class="ibtn"><img src="${ctx}/skin/default/images/iadd.png" />添加新的快捷功能</a></div>
    
    <div class="xline"></div> --%>
    <div class="box"></div>
    
    
    </div>
</body>
</html>