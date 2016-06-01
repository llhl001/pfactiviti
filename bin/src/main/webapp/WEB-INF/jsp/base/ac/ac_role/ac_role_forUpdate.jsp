<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>机构详情</title>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ include file="../../../common/common_header.jsp"%>
<script type="text/javascript" src="${ctx}/script/common/formValidation.js"></script>
<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function save() {
		var validateRule = [
		        			{id:"ROLE_NAME",message:"角色名称不能为空！"},
		        			{id:"ROLE_TYPE",message:"角色类型不能为空！"},
		        			{id:"APP_ID",message:"应用权限不能为空！"}
		               	];
		BaseUtils.showWaitMsg();
		if(!formValidationFun(validateRule)) return;
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
	<div class="form_box">
	<form id="roleForm" name="roleForm" action="${ctx }/acRole/update.do" method="post">
		<input type="hidden" name="ROLE_ID" value="${record.ROLE_ID }" />
		<%-- <input type="hidden" name="MENU_LEVEL" value="${record.MENU_LEVEL }" /> --%>
		
		<!-- <table width="98%" border="0" cellspacing="0" cellpadding="0" style="margin-bottom: 15px;"> -->
		<table class="form_table">
            <tr>
                <th width="15%"><font class="redfont">*</font>&nbsp;角色名称:</th>
                <td width="35%"><input type="text" class="forminput" style="width:89%" id="ROLE_NAME" name="ROLE_NAME" value="${record.ROLE_NAME}"/></td>
                <th width="15%"><font class="redfont">*</font>&nbsp;角色类型:</th>
                <td width="35%">
                	<select name="ROLE_TYPE" id="ROLE_TYPE" class="formselect">
		 				<%-- <c:forEach items='${dictList}' var='dict'>
							<c:if test="${dict.dictcode =='JSLX' || dict.dictcode ==''}">
								<option value='${dict.value}' ${record.ROLE_TYPE==dict.value?'selected':''}>${dict.name}</option>
							</c:if>
						</c:forEach> --%>
						<option value='0' >默认类型</option>
                	</select>
                </td>
            </tr>
            <tr>
                <th><font class="redfont">*</font>&nbsp;应用权限:</th>
                <td>
                	<select name="APP_ID" id="APP_ID" class="formselect">
                		<option value="" >请选择...      </option>
        				<c:forEach var="key" items="${appList}">
							<option value="${key.APP_ID}" ${record.APP_ID==key.APP_ID?'selected':''}>${key.APP_NAME}</option>
				        </c:forEach>
                	</select>
                </td>
                <th>角色描述:</th>
                <td><input type="text" class="forminput" style="width:89%" id="ROLE_DESC" name="ROLE_DESC" value="${record.ROLE_DESC}"/></td>
            </tr>
			<tr>
            	<th></th>
                <td colspan="3"><input type="button" onclick="save()" class="formbtn1" value="确认保存" /></td>
            </tr>
		</table>
	</form>
	</div>
</div>
</body>
</html>
