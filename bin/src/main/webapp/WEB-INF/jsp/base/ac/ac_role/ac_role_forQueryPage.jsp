<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../../common/common_header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>角色分页列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=8"/>
<%@ include file="../../../common/common_header.jsp"%>
<script type="text/javascript">
function forUpdate(id){
	layer.open({
		title:'编辑角色',
	    type: 2,
	    area: ['700px', '300px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '${ctx}/acRole/forUpdate.do?id='+id+'&pageNow=${pageView.pageNow}'
	});
}

function forAcFuncCheckTree(roleId,appId){
	layer.open({
		title:'功能分配',
	    type: 2,
	    area: ['300px', '400px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '${ctx}/acFunction/forCheckTree.do?ROLE_ID='+roleId+'&APP_ID='+appId+'&TYPE=AC_ROLE&pageNow=${pageView.pageNow}'
	});
}

function forOmEmployeeCheckTree(roleId){
	layer.open({
		title:'关联人员',
	    type: 2,
	    area: ['300px', '500px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '${ctx}/omEmployee/forCheckTree.do?ROLE_ID='+roleId+'&TYPE=AC_ROLE&pageNow=${pageView.pageNow}'
	});
}

function deleteRole(){
	var checkedList = $("input[name='check']:checked")
	if(checkedList.length==0){
		alert('请选择要删除的记录');
		return;
	}
	
	var ids = [];
	for (var i = 0; i < checkedList.length; i++) {
		ids.push($(checkedList[i]).val());
	}
	
	defaultConfirm("你确定要删除吗？删除后不可恢复！",(function(){
		BaseUtils.showWaitMsg();
		$.ajax({
			type: "post",
			url:'${ctx}/acRole/batchDelete.do',
			data : {
				ids : ids.join(',')
			},
			dataType:"json",
			success : function(data) {
				//var ret = jQuery.parseJSON(data);
				BaseUtils.hideWaitMsg();
				alert(data.msg);
				if (data.flag) {
					$('#queryForm').submit();
					//pageNow('1');
				}
			}
		});
	}));
}

$(function(){
	//全选择 全解除
	$("#checkAll").click(function(){
	    $(":checkbox").prop("checked", this.checked);
	    $(":checkbox").attr("checked", this.checked);
	    //var list = $("input[name='check']:checkbox:checked")
	    /* var list = $("input[name='check']:checked")
	    alert($(list[0]).val()); */
	});
});
</script>
</head>
<body>
	<div class="location_box"><b>位置：</b><a href="">首页</a>&nbsp;&gt;&nbsp;<a href="">权限管理</a>&nbsp;&gt;&nbsp;<span>角色管理</span></div>
	<div class="right_con" style="height: 100%">
		<div class="form_box">
			<form id="queryForm" action="${ctx}/acRole/forQueryPage.do" method="post">
				<table class="form_table">
					<tr>
						<th width="10%">角色名称：</th>
						<td width="26%"><input class="forminput" type="text" name="ROLE_NAME" value="${record.ROLE_NAME }" />&nbsp;</td>
						<th width="10%">角色类型：</th>
						<td width="26%"><select name='ROLE_TYPE' id='ROLE_TYPE' value="${record.ROLE_TYPE }" class="formselect">
								<%-- <c:forEach items='${dictList}' var='dict'>
							<c:if test="${dict.dictcode =='JSLX' || dict.dictcode ==''}">
								<option value='${dict.value}' ${acRole.ROLE_TYPE==dict.value?'selected':''}>${dict.name}</option>
							</c:if>
						</c:forEach> --%>
								<option value='0' >默认类型</option>
						</select></td>
						<td>
							<input style="width: 25%" onclick="$('#queryForm').submit();" id="search" type="button" class="formbtn1" value="查  询" />&nbsp; 
							<input style="width: 25%" onclick="forUpdate('create')" id="add" type="button" class="formbtn1" value="添  加" />&nbsp; 
							<input style="width: 25%" onclick="deleteRole()" id="delete" type="button" class="formbtn1" value="删  除" />
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

				<c:forEach var="r" items="${pageView.records}" varStatus="status">
					<tr>
						<td><input class="formcheckbox" type="checkbox" name="check" value="${r.ROLE_ID}" /></td>
						<td>${(pageView.pageNow - 1) * pageView.pageSize + status.index + 1}</td>
						<td>${r.ROLE_NAME }</td>
						<td>${r.ROLE_TYPE }</td>
						<td>${r.ROLE_DESC }</td>
						<td>${r.CREATE_TIME }</td>
						<td><a href="javascript:forUpdate('${r.ROLE_ID}');">修改</a> 
						<a href="javascript:forAcFuncCheckTree('${r.ROLE_ID}','${r.APP_ID}');">功能分配</a> 
						<a href="javascript:forOmEmployeeCheckTree('${r.ROLE_ID}');">关联人员</a></td>
					</tr>
				</c:forEach>
			</table>
			<%@ include file="../../../common/pagination_tail.jsp"%>
		</div>
	</div>
</body>
</html>
