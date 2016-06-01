<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>工作组详情</title>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ include file="../../../common/common_header.jsp"%>
<script type="text/javascript" src="${ctx}/script/common/formValidation.js"></script>
<script type="text/javascript">
function leafInfo(){
	var IS_LEAF=$("#IS_LEAF").val();
	if("1" == IS_LEAF){//1:是        0:否
		/* $(".leafinfo").show(); */
	} else if ("0" == IS_LEAF) {
		/* $(".leafinfo").hide();
		$("#FUNC_ID").val("");
		$("#FUNC_NAME").val(""); */
	}
}
$(function(){
	leafInfo()
	var a=$(window).height();
	//a-=96;(a=a-96)
	$(".center_right").css("height",a);
	})
function save(){
	var validateRule = [
	        			{id:"GROUP_NAME",message:"工作组名称不能为空！"},
	        			{id:"ORG_ID",message:"所属不能为空！"}
	        			/* {id:"menucode",message:"菜单代码不能为空！"},
	        			{id:"displayorder",message:"显示顺序必须为正整数！",contentType:"number",allowNull:"true"},
	        			{id:"isleaf",message:"是否是子菜单不能为空！"} */
	               	];
	BaseUtils.showWaitMsg();
	if(!formValidationFun(validateRule)) return;
	$.ajax({
		url: $("#menuForm").attr("action"),
		type:"post",
		data:  $("#menuForm").serialize(),
		dataType:"json",
		success: function(data) {
			//var ret = jQuery.parseJSON(data);
			BaseUtils.hideWaitMsg();
			var data = data;	
			alert(data.msg);
			if (data.flag) {
				var omtree = parent.window.$.fn.zTree.getZTreeObj("omTree");
            	var pnode=omtree.getNodeByParam("id","${record.PARENT_GROUP_ID}");
            	pnode.isParent = true;
            	omtree.reAsyncChildNodes(pnode, "refresh");
				BaseUtils.showWaitMsg();
				window.location.href="${ctx }/omGroup/forUpdate.do?id="+data.obj;
			}
		}
	});
	/* $.ajax({
		url: "${ctx }/omGroup/query.do",
		type:"post",
		data:  $("#menuForm").serialize(),
		dataType:"json",
		success: function(data) {
			BaseUtils.hideWaitMsg();
			var data = data;
			if (data.flag) {
				alert(data.msg);
				$("#ORG_ID").val("").focus();
				return false;
			}else{
				$.ajax({
					url: $("#menuForm").attr("action"),
					type:"post",
					data:  $("#menuForm").serialize(),
					dataType:"json",
					success: function(data) {
						//var ret = jQuery.parseJSON(data);
						BaseUtils.hideWaitMsg();
						var data = data;	
						alert(data.msg);
						if (data.flag) {
							var omtree = parent.window.$.fn.zTree.getZTreeObj("omTree");
			            	var pnode=omtree.getNodeByParam("id","${record.PARENT_GROUP_ID}");
			            	pnode.isParent = true;
			            	omtree.reAsyncChildNodes(pnode, "refresh");
							BaseUtils.showWaitMsg();
							window.location.href="${ctx }/omGroup/forUpdate.do?id="+data.obj;
						}
					}
				});
			}
		}
	}); */
	
	
}
</script>

</head>
<body>
<div class="right_con">
	<div class="form_box">
	<form id="menuForm" name="menuForm" action="${ctx }/omGroup/update.do" method="post">
		<%-- <input type="hidden" name="MENU_ID" value="${record.MENU_ID }" />--%>
		<input type="hidden" name="GROUP_ID" value="${record.GROUP_ID }" /> 
		<input type="hidden" name="GROUP_LEVEL" value="${record.GROUP_LEVEL }" />

		<table class="form_table">
            <tr>
                <th width="15%"><font class="redfont">*</font>&nbsp;父工作组:</th>
                <td width="35%"><input type="text" class="forminput" readonly="readonly" name="PARENT_GROUP_ID" value="${record.PARENT_GROUP_ID }" /></td>
                <th width="15%"><font class="redfont">*</font>&nbsp;工作组名称:</th>
                <td width="35%"><input type="text" class="forminput" id="GROUP_NAME" name="GROUP_NAME" value="${record.GROUP_NAME}"/></td>
            </tr>
            <tr>          
                <th width="15%">&nbsp;工作组描述:</th>
                <td width="35%"><input type="text" class="forminput" id="GROUP_DESC" name="GROUP_DESC" value="${record.GROUP_DESC}"/></td>
                <th><font class="redfont">*</font>&nbsp;所属机构:</th>
                <td>
                	<%-- <input type="text" class="forminput" id="ORG_ID" name="ORG_ID" value="${record.ORG_ID}"/> --%>
                	<select name="ORG_ID" id="ORG_ID" class="formselect">
                		<option value="" >请选择...      </option>
        				<c:forEach var="key" items="${orgList}">
							<option value="${key.ORG_ID}" ${record.ORG_ID==key.ORG_ID?'selected':''}>${key.ORG_NAME}</option>
				        </c:forEach>
                	</select>
                </td>
            </tr>
            <%-- <tr>
                <th width="15%"><font class="redfont">*</font>&nbsp;工作组级别:</th>
                <td width="35%"><input type="text" class="forminput" name="GROUP_LEVEL" value="${record.GROUP_LEVEL }" /></td>
                <th width="15%"><font class="redfont">*</font>&nbsp;工作组描述:</th>
                <td width="35%"><input type="text" class="forminput" id="GROUP_DESC" name="GROUP_DESC" value="${record.GROUP_DESC}"/></td>
            </tr> --%>
            <tr>
                <th>工作组类型:</th>
                <td>
                	<%-- <input type="text" class="forminput" id="GROUP_TYPE" name="GROUP_TYPE" value="${record.GROUP_TYPE}"/> --%>
                	<select name="GROUP_TYPE" id="GROUP_TYPE" class="formselect">
                		<%-- <c:forEach items='${dictList}' var='dict'>
							<c:if test="${dict.dictcode =='SFWYZCD'}">
								<option value='${dict.value}' ${record.IS_LEAF==dict.value?'selected':''}>${dict.name}</option>
							</c:if>
						</c:forEach> --%>
						<option value='0' ${record.GROUP_TYPE=='0'?'selected':''}>默认</option>
                	</select>
                </td>
                <%-- <th>工作组序列号:</th>
                <td><input type="text" class="forminput" id="GROUP_SEQ" name="GROUP_SEQ" readonly="readonly" value="${record.GROUP_SEQ}"/></td> --%>
            </tr>
            <%-- <tr>
            	<th>&nbsp;开始日期:</th>
                <td><input type="text" class="forminput" id="START_DATE" name="START_DATE" value="${record.START_DATE}"/></td>
                <th>&nbsp;截止日期:</th>
                <td><input type="text" class="forminput" id="END_DATE" name="END_DATE" value="${record.END_DATE}"/></td>
            </tr>
            <tr>
            	<th>&nbsp;工作组状态:</th>
                <td>
                <input type="text" class="forminput" id="GROUP_STATUS" name="GROUP_STATUS" value="${record.GROUP_STATUS}"/>
                <select name="GROUP_STATUS" id="GROUP_STATUS" class="formselect">
                		<c:forEach items='${dictList}' var='dict'>
							<c:if test="${dict.dictcode =='SFWYZCD'}">
								<option value='${dict.value}' ${record.IS_LEAF==dict.value?'selected':''}>${dict.name}</option>
							</c:if>
						</c:forEach>
						<option value='0' ${record.GROUP_STATUS=='0'?'selected':''}>默认</option>
                	</select>
                </td>
                <th>&nbsp;创建日期:</th>
                <td><input type="text" class="forminput" id="CREATE_TIME" name="CREATE_TIME" value="${record.CREATE_TIME}"/></td>
            </tr> --%>
            <c:if test="${record.GROUP_LEVEL!=1}">
            <tr>
            	<th>&nbsp;上次更新日期:</th>
                <td><input type="text" class="forminput" id="LASTUP_DATE" name="LASTUP_DATE" value="${record.LASTUP_DATE}"/></td>
            	<th>&nbsp;是否叶子工作组:</th>
                <td>
                	<select name="IS_LEAF" id="IS_LEAF" class="formselect" onchange="leafInfo()">               	
						<option value="" >请选择...      </option>
						<option value='0' ${record.IS_LEAF=='0'?'selected':''}>否</option>
						<option value='1' ${record.IS_LEAF=='1'?'selected':''}>是</option>
                	</select>
                </td>
            </tr>
            </c:if>    
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
