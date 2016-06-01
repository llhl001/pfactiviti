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
	        			{id:"FUNC_GROUP_NAME",message:"工作组名称不能为空！"}
	               	];
	BaseUtils.showWaitMsg();
	if(!formValidationFun(validateRule)) return;
	$.ajax({
		url: $("#funcGroupForm").attr("action"),
		type:"post",
		data:  $("#funcGroupForm").serialize(),
		dataType:"json",
		success: function(data) {
			//var data = jQuery.parseJSON(data);
			BaseUtils.hideWaitMsg();
			alert(data.msg);
			if (data.flag) {
				var omtree = parent.window.$.fn.zTree.getZTreeObj("omTree");
				var pid ="${record.PARENT_GROUP }";
				if(pid==""){
					pid ="${record.APP_ID }";
				}
            	var pnode=omtree.getNodeByParam("id",pid);
            	pnode.isParent = true;
            	omtree.reAsyncChildNodes(pnode, "refresh");
				BaseUtils.showWaitMsg();
				window.location.href="${ctx }/acFuncGroup/forUpdate.do?id="+data.obj;
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
	<form id="funcGroupForm" name="funcGroupForm" action="${ctx }/acFuncGroup/update.do" method="post">
		<input type="hidden" name="APP_ID" id="APP_ID" value="${record.APP_ID}"/>
        <input type="hidden" name="FUNC_GROUP_ID" id="FUNC_GROUP_ID" value="${record.FUNC_GROUP_ID}"/>
        <input type="hidden" name="PARENT_GROUP" id="PARENT_GROUP" value="${record.PARENT_GROUP}"/>
		
		<!-- <table width="95%" border="0" cellspacing="0" cellpadding="0" style="margin-bottom: 0px;">
			<tr>
				<td align="right">
					<input class="btn" value="保存" type="button" onclick="save()" />
				</td>
			</tr>
		</table> -->
		<table class="form_table">
            <tr>
                <td width="20%"><font class="redfont">*</font>&nbsp;功能组名称:</td>
                <td width="55%"><input type="text" class="forminput" id="FUNC_GROUP_NAME" name="FUNC_GROUP_NAME" value="${record.FUNC_GROUP_NAME}"/><span style="color: red">&nbsp;*</span></td>
            </tr>
            <tr>
            	<th></th>
                <td colspan="3"><input type="button" class="formbtn1" value="确认保存" onclick="save()" /></td>
            </tr>
        </table>
	</form>
	</div>
</div>
</body>
</html>
