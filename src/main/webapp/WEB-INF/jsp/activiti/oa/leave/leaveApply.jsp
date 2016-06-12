
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>机构详情</title>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ include file="../../../common/common_header.jsp"%>
<script type="text/javascript"
	src="${ctx}/script/common/formValidation.js"></script>
<script type="text/javascript">
function open(){
	var url="www.baidu.com";
}
function abc(){
	var url="www.baidu.com";
	alert(url);
	$('#queryForm').submit();
	alert(url);
}
</script>

<style>
</style>
</head>
<body>
<div class="container showgrid">
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success">${message}</div>
		<!-- 自动隐藏提示信息 -->
		<script type="text/javascript">
		setTimeout(function() {
			$('#message').hide('slow');
		}, 5000);
		</script>
	</c:if>
	<c:if test="${not empty error}">
		<div id="error" class="alert alert-error">${error}</div>
		<!-- 自动隐藏提示信息 -->
		<script type="text/javascript">
		setTimeout(function() {
			$('#error').hide('slow');
		}, 5000);
		</script>
	</c:if>
	<%-- <form:form id="queryForm" action="${ctx}/leave/start.do" > --%>
	<form id="queryForm" action="${ctx}/leave/start.do" method="post">
		<fieldset>
			<legend><small>请假申请</small></legend>
			<table border="1">
			<tr>
				<td>请假类型：</td>
				<td>
					<select id="leaveType" name="leaveType">
						<option>公休</option>
						<option>病假</option>
						<option>调休</option>
						<option>事假</option>
						<option>婚假</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>开始时间：</td>
				<td><input type="text" id="startTime" name="startTime" /></td>
			</tr>
			<tr>
				<td>结束时间：</td>
				<td><input type="text" id="endTime" name="endTime" /></td>
			</tr>
			<tr>
				<td>请假原因：</td>
				<td>
					<textarea name="reason"></textarea>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
					<button type="submmit">申请</button>
					<input style="width: 25%" onclick="$('#queryForm').submit();" id="search" type="button" class="btn2" value="查&nbsp; &nbsp; 询" />&nbsp; 
				</td>
			</tr>
		</table>
		</fieldset>
	</form>
	</div>
</body>
</html>
