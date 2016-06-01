<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>机构详情</title>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ include file="../../../common/common_header.jsp"%>
<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function save() {
		var validateRule = [
		        			{id:"ROLE_NAME",message:"角色名称不能为空！"},
		        			{id:"ROLE_TYPE",message:"角色类型不能为空！"},
		        			{id:"APP_ID",message:"应用权限不能为空！"}
		               	];
		BaseUtils.showWaitMsg();
		/* if(!formValidationFun(validateRule)) return; */
		$.ajax({
					url : $("#roleForm").attr("action"),
					type : "post",
					data : $("#roleForm").serialize(),
					dataType : "json",
					success : function(data) {
						//var ret = jQuery.parseJSON(data);
						BaseUtils.hideWaitMsg();
						alert(data.msg);
						if (data.flag) {
							parent.$('#queryForm').submit();
							//parent.pageNow('${pageNow}');
							parent.layer.close(index);
						}
					}
				});
	}
</script>
<style>

</style>
</head>
<body>
<div class="right_con">
	<div class="form-group"> 
	<form id="roleForm" name="roleForm" action="${ctx }/acHomeModule/update.do" method="post">
		<input type="hidden" name="HOME_MODULE_ID" value="${record.HOME_MODULE_ID }" />
		<%-- <input type="hidden" name="MENU_LEVEL" value="${record.MENU_LEVEL }" /> --%>
		
		<!-- <table width="98%" border="0" cellspacing="0" cellpadding="0" style="margin-bottom: 15px;"> -->
		<table class="form_table">
            <tr>
                <th width="15%"><font class="redfont">*</font>&nbsp;模块标题:</th>
                <td width="35%"><input type="text" class="forminput" style="width:89%" id="MODULE_NAME" name="MODULE_NAME" value="${record.MODULE_NAME}"/></td>
                <th width="15%"><font class="redfont">*</font>&nbsp;模块类型:</th>
                <td width="35%">
                	<sys:dictSelect cls="formselect" name="MODULE_TYPE" typeCode="HOME_MODULE_TYPE" defaultValue="${record.MODULE_TYPE }" />
                </td>
            </tr>
            <tr>
                <th><font class="redfont">*</font>&nbsp;编号:</th>
                <td>
                	<input type="text" class="forminput" style="width:89%" id="CODE" name="CODE" value="${record.CODE}"/>
                </td>
                <th><font class="redfont">*</font>&nbsp;是否使用:</th>
                <td><sys:dictSelect cls="formselect" name="IN_USE" typeCode="YN" defaultValue="${record.IN_USE }" /></td>
            </tr>
            <tr>
                <th><font class="redfont">*</font>&nbsp;坐标列:</th>
                <td>
                	<input type="text" class="forminput" style="width:89%" id="DATA_COL" name="DATA_COL" value="${record.DATA_COL}"/>
                </td>
                <th><font class="redfont">*</font>&nbsp;坐标行:</th>
                <td><input type="text" class="forminput" style="width:89%" id="DATA_ROW" name="DATA_ROW" value="${record.DATA_ROW}"/></td>
            </tr>
            <tr>
                <th><font class="redfont">*</font>&nbsp;占用列:</th>
                <td>
                	<input type="text" class="forminput" style="width:89%" id="DATA_SIZEX" name="DATA_SIZEX" value="${record.DATA_SIZEX}"/>
                </td>
                <th><font class="redfont">*</font>&nbsp;占用行:</th>
                <td><input type="text" class="forminput" style="width:89%" id="DATA_SIZEY" name="DATA_SIZEY" value="${record.DATA_SIZEY}"/></td>
            </tr>
            <tr>
				<th width="190">HTML内容:</th>
				<td colspan="3">
					<textarea class="formtextarea" type="text" id="HTML_CONTENT" name="HTML_CONTENT">${record.HTML_CONTENT}</textarea> 
				</td>
			</tr>
			<tr>
				<th width="190">参数（json格式）:</th>
				<td colspan="3">
					<textarea class="formtextarea" type="text" id="PARAM_JSON" name="PARAM_JSON">${record.PARAM_JSON}</textarea> 
				</td>
			</tr>
            
			<tr>
            	<th></th>
                <td colspan="3"><input type="button" onclick="save()" class="btn btn-primary" value="确认保存" /></td>
            </tr>
		</table>
	</form>
	</div>
</div>
</body>
</html>
