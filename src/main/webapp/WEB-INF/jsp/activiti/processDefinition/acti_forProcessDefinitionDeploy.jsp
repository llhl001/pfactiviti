<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>机构详情</title>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ include file="../../common/common_header.jsp"%>
<script type="text/javascript"
	src="${ctx}/script/common/formValidation.js"></script>
<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function save() {
		BaseUtils.showWaitMsg();
		$.ajax({
					url : $("#roleForm").attr("action"),
					//type : "post",
					//data : $("#roleForm").serialize(),
					//dataType : "json",
					success : function(data) {
						BaseUtils.hideWaitMsg();
						alert(data.msg);
					/* 	if (data.flag) {
							parent.$('#queryForm').submit();
							parent.layer.close(index);
						} */
					}
				});
	}
</script>

<style>
</style>
</head>
<body>
	<div class="right_con">
		<div class="form_box">
			<form id="roleForm" name="roleForm" action="${ctx }/processDefinition/processDefinitionDeploy.do"
			enctype="multipart/form-data"
				method="post">
				<table class="form_table">
					<tr>
						<th width="15%"><font class="redfont">*</font>&nbsp;流程定义名称:</th>
						<td width="35%"><input type="text" class="forminput"
							style="width: 89%" id="name" name="name"
							value="" /></td>
					</tr>
					<tr>
						<th width="15%"><font class="redfont">*</font>&nbsp;流程定义:</th>
						<td width="35%"><input type="file" name="file"
							id="uploadFile" /></td>
					</tr>
					<tr>
						<th></th>
						<td colspan="3">
								<input type="submit" 							class="formbtn1" value="确认部署" />
							</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
