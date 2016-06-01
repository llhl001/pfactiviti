<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dictionary left</title>
<%@ include file="../../../common/common_header.jsp"%>
<%-- <script type="text/javascript" src="${ctx}/script/base/sys/sys_dict/sys_dict_right.js"></script> --%>
<script type="text/javascript">
function forUpdate(dicId){
	var typeId = $("#TYPE_CODE").val();
	layer.open({
		title:'编辑字典项',
	    type: 2,
	    area: ['460px', '300px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '${ctx}/sysDict/rightForUpdate.do?id='+dicId+"&typeId="+typeId+'&pageNow=${pageView.pageNow}'
	});
}
function deleteDetail(){
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
			url:'${ctx}/sysDict/rightBatchDelete.do',
			data : {
				ids : ids.join(',')
			},
			dataType:"json",
			success : function(data) {
				//var ret = jQuery.parseJSON(data);
				BaseUtils.hideWaitMsg();
				alert(data.msg);
				if (data.flag) {
					var typeId = $("#TYPE_CODE").val();
				/* 	alert("typecode="+type); */
					var url = '${ctx}/sysDict/rightQueryPage.do?id='+typeId;
					parent.document.getElementById('dictRightFrame').src=url;
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
</script>
</head>
<body>
	<div class="right_list" style="valign:top;">
		 <input type="hidden" name="TYPE_CODE" id="TYPE_CODE" value="${record.TYPE_ID}" /> 
			<table class="form_table" >
				<tr>
					<td colspan="3" align="right">
							<input style="width: 12%" onclick="forUpdate('')" id="add" type="button" class="formbtn1" value="添  加" />&nbsp; 
							<input style="width: 12%" onclick="deleteDetail()" id="delete" type="button" class="formbtn1" value="删  除" />
					</td>
				</tr>
			</table>
			<table class="tablelist">
				<tr>
					<th width="4%"><input class="formcheckbox" type="checkbox" id="checkAll" /></th>
					<th width="9%" align="center">编号&nbsp;&nbsp;<i class="sort"><img src="${ctx}/skin/default/images/px.gif" /></i></th>
					<th width="12%" align="center">字典项</th>
					<th width="12%" align="center">字典值</th>
					<th width="12%" align="center">是否使用</th>
					<!-- <th width="12%" align="center">父项</th> -->
					<th width="10%" align="center">操作</th>
				</tr>

				<c:forEach var="r" items="${pageView.records}" varStatus="status">
					<tr>
						<td><input class="formcheckbox" type="checkbox" name="check" value="${r.DIC_ID}" /></td>
						<td>${(pageView.pageNow - 1) * pageView.pageSize + status.index + 1}</td>
						<td>${r.TEXT }</td>
						<td>${r.VALUE}</td>
						<td>${r.IS_USE}</td>
						<%-- <td>${r.PID}</td> --%>
						<td><a href="javascript:forUpdate('${r.DIC_ID}');">修改</a> 
							<%-- <a href="javascript:deleteDetail('${r.DIC_ID}');">删除</a> --%> 
						</td>
					</tr>
				</c:forEach>
			</table>
			<%@ include file="../../../common/pagination_tail.jsp"%>
		</div>
</body>
</html>