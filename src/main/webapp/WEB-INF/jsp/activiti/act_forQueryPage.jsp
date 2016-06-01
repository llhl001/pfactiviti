<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/common_header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>工作流分页列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=8"/>
<script type="text/javascript">

function deploy(){

	//BaseUtils.showWaitMsg();

alert("发布流程");
			/* 	$.ajax({
					url: $("#appForm").attr("action"),
					type:"post",
					data:  $("#appForm").serialize(),
					dataType:"json",
					success: function(data) {
						BaseUtils.hideWaitMsg();
						var data = data;
						alert(data.msg);
						if (data.flag) {
							var omtree = parent.window.$.fn.zTree.getZTreeObj("omTree");
			            	var pnode=omtree.getNodeByParam("id","root");
			            	pnode.isParent = true;
			            	omtree.reAsyncChildNodes(pnode, "refresh");
							BaseUtils.showWaitMsg();
							window.location.href="${ctx }/acApplication/forUpdate.do?id="+data.obj;
						}
					}
				}); */
}
</script>
</head>
<body>
	<div class="location_box"><b>位置：</b><a href="">首页</a>&nbsp;&gt;&nbsp;<a href="">工作流管理</a>&nbsp;&gt;&nbsp;<span>流程管理</span></div>
	<div class="right_con" style="height: 100%">
		<div class="form_box">
			<form id="queryForm" action="${ctx}/acRole/forQueryPage.do" method="post">
				<table class="form_table">
					<tr>
						<th width="10%">角色名称：</th>
						<td width="26%"><input class="forminput" type="text" name="ROLE_NAME" value="${record.ROLE_NAME }" />&nbsp;</td>
						<td>
							<input style="width: 25%" onclick="$('#queryForm').submit();" id="search" type="button" class="formbtn1" value="查  询" />&nbsp; 
							<input style="width: 25%" onclick="forUpdate('create')" id="add" type="button" class="formbtn1" value="添  加" />&nbsp; 
							<input style="width: 25%" onclick="deleteRole()" id="delete" type="button" class="formbtn1" value="删  除" />
							<input style="width: 25%" onclick="deploy()" id="add" type="button" class="formbtn1" value="发布流程" />&nbsp; 
						</td>
					</tr>
				</table>
			</form>
		</div>

		<div class="right_list">
			<table class="tablelist">
				<tr>
					<th width="4%"><input class="formcheckbox" type="checkbox" id="checkAll" /></th>
					<th width="6%" align="center">编号&nbsp;&nbsp;<i class="sort"><img src="${ctx}/skin/default/images/px.gif" /></i></th>
					<th width="15%" align="center">角色名称</th>
					<th width="12%" align="center">角色类型</th>
					<th width="29%" align="center">角色描述</th>
					<th width="16%" align="center">创建时间</th>
					<th width="20%" align="center">操作</th>
				</tr>

				<%-- <c:forEach var="r" items="${pageView.records}" varStatus="status">
					<tr>
						<td><input class="formcheckbox" type="checkbox" name="check" value="${r.ROLE_ID}" /></td>
						<td>${(pageView.pageNow - 1) * pageView.pageSize + status.index + 1}</td>
						<td>${r.ROLE_NAME }</td>
						<td>${r.ROLE_TYPE }</td>
						<td>${r.ROLE_DESC }</td>
						<td>${r.CREATE_TIME }</td>
						<td><a href="javascript:forUpdate('${r.ROLE_ID}');">修改</a> 
						<a href="javascript:forAcFuncCheckTree('${r.ROLE_ID}','${r.APP_ID}');">功能分配</a> 
						<a href="javascript:forOmEmployeeCheckTree('${r.ROLE_ID}');">关联人员</a>
						<a href="javascript:forAcHomeModuleCheck('${r.ROLE_ID}','${r.APP_ID}');">首页模块</a></td>
					</tr>
				</c:forEach> --%>
			</table>
			<%@ include file="../common/pagination_tail.jsp"%>
		</div>
	</div>
</body>
</html>
