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
$(function(){
	var a=$(window).height();
	//a-=96;(a=a-96)
	$(".center_right").css("height",a);
	})
function save(){
	var validateRule = [
	                    {id:"ORG_CODE",message:"机构代码不能为空！"},
	        			{id:"ORG_NAME",message:"机构名称不能为空！"},
	        			{id:"STATUS",message:"机构状态不能为空！"},
	        			{id:"SORT_NO",message:"排列顺序必须为正整数！",contentType:"number",allowNull:"false"},
	        			{id:"EMAIL",message:"电子邮箱格式不正确！",contentType:"email",allowNull:"true"}
	               	];
	BaseUtils.showWaitMsg();
	if(!formValidationFun(validateRule)) return;
	$.ajax({
		url: $("#orgForm").attr("action"),
		type:"post",
		data:  $("#orgForm").serialize(),
		dataType:"json",
		success: function(data) {
			//var ret = jQuery.parseJSON(data);
			BaseUtils.hideWaitMsg();
			var ret = data;
			
			alert(ret.msg);
			if (ret.flag) {
				var omtree = parent.window.$.fn.zTree.getZTreeObj("omTree");
            	var pnode=omtree.getNodeByParam("id","${record.PARENT_ORG_ID }");
            	pnode.isParent = true;
            	omtree.reAsyncChildNodes(pnode, "refresh");
				BaseUtils.showWaitMsg();
				window.location.href="${ctx }/omOrganization/forUpdate.do?id="+data.obj;
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
	<form id="orgForm" name="orgForm" action="${ctx }/omOrganization/update.do" method="post">
		<input type="hidden" name="ORG_ID" value="${record.ORG_ID }" />
		<input type="hidden" name="ORG_LEVEL" value="${record.ORG_LEVEL }" />
		
		<!-- <table width="98%" border="0" cellspacing="0" cellpadding="0" style="margin-bottom: 15px;"> -->
		<table class="form_table">
			<!-- <tr>
				<td colspan="4" align="right">
					<input class="formbtn1" value="保存" type="button" onclick="save()" />
				</td>
			</tr> -->
			<tr>
				<th width="190"><font class="redfont">*</font>&nbsp;机构代码:</th>
				<td>
					<input class="forminput" type="text" style="width: 89%" id="ORG_CODE" name="ORG_CODE" value="${record.ORG_CODE}" />
				</td>
				<th width="190"><font class="redfont">*</font>&nbsp;机构名称:</th>
				<td>
					<input class="forminput" type="text" style="width: 89%" id="ORG_NAME" name="ORG_NAME" value="${record.ORG_NAME}" />
				</td>
			</tr>
			<tr>
				<th width="190">上级机构:</th>
				<td>
					<input class="forminput" type="hidden" name="PARENT_ORG_ID" value="${record.PARENT_ORG_ID }" readonly="readonly" />
					<input class="forminput" type="text" name="PARENT_ORG_NAME" value="${record.PARENT_ORG_NAME }" readonly="readonly" />
				</td>
				<th width="190"><font class="redfont">*</font>&nbsp;机构状态（待做成字典）:</th>
				<td>
					<%-- <input class="forminput" type="text" style="width: 89%" id="STATUS" name="STATUS" value="${record.STATUS}" /> --%>
					<select name="STATUS" id="STATUS" class="formselect">
                		<%-- <c:forEach items='${dictList}' var='dict'>
							<c:if test="${dict.dictcode =='SFWYZCD'}">
								<option value='${dict.value}' ${record.IS_LEAF==dict.value?'selected':''}>${dict.name}</option>
							</c:if>
						</c:forEach> --%>
						<option value='0' ${record.STATUS=='0'?'selected':''}>默认</option>
                	</select>
				</td>
			</tr>
			<tr>
				<th width="190"><font class="redfont">*</font>&nbsp;排列顺序:</th>
				<td>
					<input class="forminput" type="text" style="width: 89%" id="SORT_NO" name="SORT_NO" value="${record.SORT_NO}" />
				</td>
				<%-- <th width="190">所属地域:</th>
				<td>
					<input class="forminput" type="text" style="width: 89%" id="AREA" name="AREA" value="${record.AREA}" />
				</td> --%>
			</tr>
			<tr>
				<th width="190">机构地址:</th>
				<td>
					<input class="forminput" type="text" style="width: 89%" id="ORG_ADDR" name="ORG_ADDR" value="${record.ORG_ADDR}" />
				</td>
				<th width="190">邮编:</th>
				<td>
					<input class="forminput" type="text" style="width: 89%" id="ZIP_CODE" name="ZIP_CODE" value="${record.ZIP_CODE}" />
				</td>
			</tr>
			<%-- <tr>
				<th width="190">机构主管人员:</th>
				<td>
					<select name="manaposition" id="manaposition" class="formselect">
						<option value="">请选择...</option>
						<c:forEach items='${emps}' var='emp'>"
							<c:if test='${record.manaposition==emp.empid}'>
								<option value='${emp.empid}' selected='selected'>${emp.empname}</option>
							</c:if>
							<c:if test='${record.manaposition!=emp.empid}'>
								<option value='${emp.empid }'>${emp.empname}</option>
							</c:if>
						</c:forEach>
					</select>
				</td>
				<th width="190">上级机构主管人员:</th>
				<td>
					<select name="managerid" id="managerid" class="formselect">
						<option value="">请选择...</option>
						<c:forEach items='${pemps}' var='pe'>"
									<c:if test='${record.managerid==pe.empid}'>
								<option value='${pe.empid}' selected='selected'>${pe.empname}</option>
							</c:if>
							<c:if test='${record.managerid!=pe.empid}'>
								<option value='${pe.empid }'>${pe.empname}</option>
							</c:if>
						</c:forEach>
					</select>
				</td>
			</tr> --%>
			<tr>
				<th width="190">联系人:</th>
				<td>
					<input class="forminput" type="text" style="width: 89%" id="LINK_MAN" name="LINK_MAN" value="${record.LINK_MAN}" />
				</td>
				<th width="190">联系电话:</th>
				<td>
					<input class="forminput" type="text" style="width: 89%" id="LINK_TEL" name="LINK_TEL" value="${record.LINK_TEL}" />
				</td>
			</tr>
			<tr>
				<th width="190">电子邮箱:</th>
				<td>
					<input class="forminput" type="text" style="width: 89%" id="EMAIL" name="EMAIL" value="${record.EMAIL}" />
				</td>
				<th width="190">网站地址:</th>
				<td>
					<input class="forminput" type="text" style="width: 89%" id="WEB_URL" name="WEB_URL" value="${record.WEB_URL}" />
				</td>
			</tr>
			<%-- <tr>
				<th width="190">生效日期:</th>
				<td>
					<input class="forminput Wdate" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 89%" id="START_DATE" name="START_DATE" value="${record.START_DATE}" />
				</td>
				<th width="190">失效日期:</th>
				<td>
					<input class="forminput Wdate" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 89%" id="END_DATE" name="END_DATE" value="${record.END_DATE}" />
				</td>
			</tr> --%>
			<tr>
				<th width="190">备注:</th>
				<td colspan="3">
					<textarea class="formtextarea" type="text" id="REMARK" name="REMARK">${record.REMARK}</textarea> 
				</td>
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
