<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dictionary left</title>
<%@ include file="../../../common/common_header.jsp"%>
<%-- <script type="text/javascript" src="${ctx}/script/base/sys/sys_dict/sys_dict_left.js"></script> --%>
<script type="text/javascript">
function forUpdate(id){
	layer.open({
		title:'编辑字典',
	    type: 2,
	    area: ['700px', '300px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '${ctx}/sysDict/forUpdate.do?id='+id+'&pageNow=${pageView.pageNow}'
	});
}
function deleteDict(){
	var checkedList = $("input[name='check']:checked")
	if(checkedList.length==0){
		alert('请选择要删除的记录');
		return;
	}
	
	var ids = [];
	for (var i = 0; i < checkedList.length; i++) {
		ids.push($(checkedList[i]).val());
	}
	
	defaultConfirm("你确定要删除吗？该字典的子项也一起删除，删除后不可恢复！",(function(){
		BaseUtils.showWaitMsg();
		$.ajax({
			type: "post",
			url:'${ctx}/sysDict/batchDelete.do',
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
	});
});
function forDetail(id){
	var url = '${ctx}/sysDict/rightQueryPage.do?id='+id;
	document.getElementById('dictRightFrame').src=url;
}

</script>
</head>
<body>
<div class="location_box"><b>位置：</b><a href="">首页</a>&nbsp;&gt;&nbsp;<a href="">其它管理</a>&nbsp;&gt;&nbsp;<span>字典</span></div>
<div class="right_con" style="height: 100%；width:100%;boder:1px solid grey;">
	<div class="left_list" style="width:46%;float:left;boder:1px solid grey;">
		<form id="queryForm" action="${ctx}/sysDict/forQueryPage.do" method="post">
			<table class="form_table" >
				<tr>
					<%-- <th width="10%">角色名称：</th>
					<td width="26%"><input class="forminput" type="text" name="ROLE_NAME" value="${record.ROLE_NAME }" />&nbsp;</td>
					<th width="10%">角色类型：</th>
					<td width="26%"><select name='ROLE_TYPE' id='ROLE_TYPE' value="${record.ROLE_TYPE }" class="formselect">
							
						<option value='0' >默认类型</option>
						</select>
					</td> --%>
					<td colspan="3" align="right">
							<!-- <input style="width: 25%" onclick="$('#queryForm').submit();" id="search" type="button" class="formbtn1" value="查  询" />&nbsp; --> 
							<input style="width: 12%" onclick="forUpdate('')" id="add" type="button" class="formbtn1" value="添  加" />&nbsp; 
							<input style="width: 12%" onclick="deleteDict()" id="delete" type="button" class="formbtn1" value="删  除" />
					</td>
				</tr>
			</table>
		</form>
			<table class="tablelist">
				<tr>
					<th width="4%"><input class="formcheckbox" type="checkbox" id="checkAll" /></th>
					<th width="8%" align="center">编号&nbsp;&nbsp;<i class="sort"><img src="${ctx}/skin/default/images/px.gif" /></i></th>
					<th width="12%" align="center">字典名称</th>
					<th width="12%" align="center">字典编码</th>
					<th width="10%" align="center">操作</th>
				</tr>

				<c:forEach var="r" items="${pageView.records}" varStatus="status">
					<tr>
						<td><input class="formcheckbox" type="checkbox" name="check" value="${r.DIC_TYPE_ID}" /></td>
						<td>${(pageView.pageNow - 1) * pageView.pageSize + status.index + 1}</td>
						<td>${r.TYPE_NAME }</td>
						<td>${r.TYPE_CODE }</td>
						<td><a href="javascript:forUpdate('${r.DIC_TYPE_ID}');">修改</a> 
						<a href="javascript:forDetail('${r.DIC_TYPE_ID}');">查看子项</a> 
						</td>
					</tr>
				</c:forEach>
			</table>
			<%@ include file="../../../common/pagination_tail.jsp"%>
		</div>
		<div style="boder:1px solid grey;height:100%;width:49%;float:left;margin-left:8px;">
		<iframe id="dictRightFrame" style="height:500px;width:100%" name="dictRightFrame" src=""   frameborder="0" ></iframe>
		</div>
	</div>
</body>
</html>