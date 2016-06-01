<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../../common/common_header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>角色分页列表</title>
<script type="text/javascript">

</script>
<style type="text/css">
	td 
	{ 
	    word-break:break-all; 
	} 
</style>
</head>
<body>
	<div class="location_box"><b>位置：</b><a href="">首页</a>&nbsp;&gt;&nbsp;<a href="">权限管理</a>&nbsp;&gt;&nbsp;<span>角色管理</span></div>
	<div class="right_con" style="height: 100%">
		<div class="form_box">
			<form id="queryForm" action="${ctx}/sysLog/forQueryPage.do" method="post">
				<table class="form_table">
					<tr>
						<th width="10%">操作用户：</th>
						<td width="26%"><input class="forminput" type="text" name="CREATE_BY" value="${record.CREATE_BY }" />&nbsp;</td>
						<th width="10%">操作时间：</th>
						<td width="16%">
						<input class="forminput Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" name="CREATE_TIME_FROM" value="${record.CREATE_TIME_FROM }" />&nbsp;
						</td>
						<td width="16%">
						<input class="forminput Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" name="CREATE_TIME_TO" value="${record.CREATE_TIME_TO }" />&nbsp;
						</td>
						<td>
							<input style="" onclick="$('#queryForm').submit();" id="search" type="button" class="formbtn1" value="查  询" />&nbsp; 
						</td>
					</tr>
				</table>
			</form>
		</div>

		<div class="right_list">
			<table class="tablelist" style="table-layout:fixed;">
				<tr>
					<th width="4%">序号</th>
					<th width="20%" align="center">操作用户</th>
					<th width="20%" align="center">操作时间</th>
					<th width="12%" align="center">操作IP</th>
					<th width="16%" align="center">访问链接</th>
					<th width="20%" align="center">访问参数</th>
				</tr>

				<c:forEach var="r" items="${pageView.records}" varStatus="status">
					<tr>
						<td>${(pageView.pageNow - 1) * pageView.pageSize + status.index + 1}</td>
						<td>${r.CREATE_BY }</td>
						<td>${r.CREATE_TIME }</td>
						<td>${r.REMOTE_ADDR }</td>
						<td>${r.REQUEST_URI }</td>
						<td>${r.PARAMS }</td>

					</tr>
				</c:forEach>
			</table>
			<%@ include file="../../../common/pagination_tail.jsp"%>
		</div>
	</div>
</body>
</html>
