<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../common/common_header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>流程定义分页列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=8"/>
<script type="text/javascript">
function start(key){
		BaseUtils.showWaitMsg();
		$.ajax({
			type: "post",
			url:'${ctx}/pi/startProcessInstance.do',
			data : {key : key},
			success : function(data) {
				BaseUtils.hideWaitMsg();
			 	if (data.flag) {
					$('#queryForm').submit();
				} 
			}
		});
}
</script>
</head>
<body>
	<div class="location_box"><b>位置：</b><a href="">首页</a>&nbsp;&gt;&nbsp;<a href="">工作流管理</a>&nbsp;&gt;&nbsp;<span>流程实例管理</span></div>
	<div class="right_con" style="height: 100%">
		<div class="form_box">
			<form id="queryForm" action="${ctx}/pi/query.do" method="post">
				<table class="form_table">
					<tr>
						<th width="10%">定义名称：</th>
						<td width="26%"><input class="forminput" type="text" name="name" value="${record.name }" />&nbsp;</td>
						<th width="10%"></th>
						<td width="26%" align="right">
							<input style="width: 25%" onclick="$('#queryForm').submit();" id="search" type="button" class="btn2" value="查&nbsp; &nbsp; 询" />&nbsp; 
						</td>
					</tr>
				</table>
			</form>
		</div>

		<div class="right_list">
			<table class="tablelist">
				<tr>
					<th width="3%"><input class="formcheckbox" type="checkbox" id="checkAll" /></th>
					<th width="6%" align="center">编号&nbsp;&nbsp;<i class="sort"><img src="${ctx}/skin/default/images/px.gif" /></i></th>
					<th width="15%" align="center">ID</th>
					<th width="5%" align="center">部署ID</th>
					<th width="8%" align="center">KEY</th>
					<th width="8%" align="center">名称</th>
					<th width="12%" align="center">资源名称</th>
					<th width="5%" align="center">版本</th>
					<th width="10%" align="center">备注</th>
					<th width="8%" align="center">操作</th>
				</tr>
				<c:forEach var="r" items="${pageView.records}" varStatus="status">
					<tr>
						<td><input class="formcheckbox" type="checkbox" name="check" value="${r.id}" /></td>
						<td align="center">${(pageView.pageNow - 1) * pageView.pageSize + status.index + 1}</td>
						<td align="center">${r.id }</td>
						<td>${r.id}</td>
						<td>${r.id }</td>
						<td>${r.id }</td>
						<td>${r.id }</td>
						<td align="center">${r.id }</td>
						<td align="center">${r.id }</td>
						<td align="center">
							<a href="javascript:start('${r.id}');">启动实例</a> 
						</td>
					</tr>
				</c:forEach>
			</table>
			<%@ include file="../../common/pagination_tail.jsp"%>
			
		</div>
	</div>
</body>
</html>
