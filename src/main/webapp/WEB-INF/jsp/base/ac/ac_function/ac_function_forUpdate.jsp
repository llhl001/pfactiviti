<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>岗位详情</title>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ include file="../../../common/common_header.jsp"%>
<script type="text/javascript" src="${ctx}/script/common/formValidation.js"></script>
<script type="text/javascript">
function save(){
	var validateRule = [
	                    {id:"FUNC_NAME",message:"功能名称不能为空！"},
	        			{id:"IS_MENU",message:"是否定义为菜单不能为空！"},
	        			{id:"FUNC_ACTION",message:"功能调用入口不能为空！"} 
	               	];
	BaseUtils.showWaitMsg();
	if(!formValidationFun(validateRule)) return;
	$.ajax({
		url: $("#functionForm").attr("action"),
		type:"post",
		data:  $("#functionForm").serialize(),
		dataType:"json",
		success: function(data) {
			//var ret = jQuery.parseJSON(data);
			BaseUtils.hideWaitMsg();
			var ret = data;
			
			alert(ret.msg);
			if (ret.flag) {
				var omtree = parent.window.$.fn.zTree.getZTreeObj("omTree");
				var pid ="${record.FUNC_GROUP_ID }";
            	var pnode=omtree.getNodeByParam("id",pid);
            	pnode.isParent = true;
            	omtree.reAsyncChildNodes(pnode, "refresh");
            	BaseUtils.showWaitMsg();
				window.location.href="${ctx }/acFunction/forUpdate.do?id="+data.obj;
			}
		}
	});
}
</script>
<style>
/*  input{ height:27px; border:1px solid #ccc; width:70%;  line-height:27px}

table {
  border-spacing: 0;
  border-collapse: collapse;
}
td,
th {
  padding:0 3px;height:36px;
} */
</style>
</head>
<body>
<div class="right_con">
	<div class="form_box">
	<form id="functionForm" name="functionForm" action="${ctx }/acFunction/update.do" method="post">
		<input type="hidden" name="FUNC_ID" value="${record.FUNC_ID }" />
		<input type="hidden" name="FUNC_GROUP_ID" value="${record.FUNC_GROUP_ID }" />
		<table class="form_table">
            <tr>
                <th width="15%">功能名称:</th>
                <td width="35%"><input type="text" class="forminput" id="FUNC_NAME" name="FUNC_NAME" value="${record.FUNC_NAME}"/><span style="color: red">&nbsp;*</span></td>
                <th width="15%">功能类型:</th>
                <td width="35%">
                	<select name="FUNC_TYPE" id="FUNC_TYPE" class="formselect">
                		<%-- <c:forEach items='${dictList}' var='dict'>
							<c:if test="${dict.dictcode =='GNLX' || dict.dictcode ==''}">
								<option value='${dict.value}' ${record.FUNC_TYPE==dict.value?'selected':''}>${dict.name}</option>
							</c:if>
						</c:forEach> --%>
						<option value='0'selected>默认</option>
                	</select>
                </td>
            </tr>
            <tr>
                <th><font class="redfont">*</font>&nbsp;是否定义为菜单:</th>
                <td>
                	<select name="IS_MENU" id="IS_MENU" class="formselect">
                		<%-- <c:forEach items='${dictList}' var='dict'>
							<c:if test="${dict.dictcode =='SFDYWCD'}">
								<option value='${dict.value}' ${record.IS_MENU==dict.value?'selected':''}>${dict.name}</option>
							</c:if>
						</c:forEach> --%>
						<option value='1' selected>是</option>
						<option value='0'>否</option>
                	</select>
                </td>
                <th>功能调用入口:</th>
                <td><input type="text" class="forminput" id="FUNC_ACTION" name="FUNC_ACTION" value="${record.FUNC_ACTION}"/><span style="color: red">&nbsp;*</span></td>
            </tr>
            <tr>
                <th>输入参数:</th>
                <td><input type="text" class="forminput" id="PARA_INFO" name="PARA_INFO" value="${record.PARA_INFO}"/></td>
                <th>功能描述:</th>
                <td><input type="text" class="forminput" id="FUNC_DESC" name="FUNC_DESC" value="${record.FUNC_DESC}"/></td>
            </tr>
            <tr>
            	<th></th>
                <td colspan="5"><input type="button" class="formbtn1" onclick="save()" value="确认保存" /></td>
            </tr>
        </table>
	</form>
	</div>
</div>
</body>
</html>
