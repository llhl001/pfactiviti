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
	        			{id:"APP_NAME",message:"应用名称不能为空！"},
	        			{id:"APP_CODE",message:"应用代码不能为空！"},
	        			{id:"APP_TYPE",message:"应用类别不能为空！"},
	        			{id:"IS_OPEN",message:"是否开通不能为空！"} 
	               	];
	BaseUtils.showWaitMsg();
	if(!formValidationFun(validateRule)) return;

				$.ajax({
					url: $("#appForm").attr("action"),
					type:"post",
					data:  $("#appForm").serialize(),
					dataType:"json",
					success: function(data) {
						BaseUtils.hideWaitMsg();
						var data = data;
						alert(data.msg);
						if (data.flag) {
							var omtree = parent.window.$.fn.zTree.getZTreeObj("omTree");
			            	var pnode=omtree.getNodeByParam("id","root");
			            	pnode.isParent = true;
			            	omtree.reAsyncChildNodes(pnode, "refresh");
							BaseUtils.showWaitMsg();
							window.location.href="${ctx }/acApplication/forUpdate.do?id="+data.obj;
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
	<form id="appForm" name="appForm" action="${ctx }/acApplication/update.do" method="post">
		<input type="hidden" name="APP_ID" value="${record.APP_ID }" />
		
		<!-- <table width="98%" border="0" cellspacing="0" cellpadding="0" style="margin-bottom: 15px;"> -->
		<table class="form_table">
			<!-- <tr>
				<td colspan="4" align="right">
					<input class="formbtn1" value="保存" type="button" onclick="save()" />
				</td>
			</tr> -->
			<tr>
                <th width="15%"><font class="redfont">*</font>&nbsp;应用名称:</th>
                <td width="35%"><input type="text" class="forminput" style="width:89%" id="APP_NAME" name="APP_NAME" value="${record.APP_NAME}"/></td>
                <th width="15%"><font class="redfont">*</font>&nbsp;应用代码:</th>
                <td width="35%"><input type="text" class="forminput" style="width:89%" id="APP_CODE" name="APP_CODE" value="${record.APP_CODE}"/></td>
            </tr>
            <tr>
                <th><font class="redfont">*</font>&nbsp;应用类别:</th>
                <td>
                	<select name="APP_TYPE" id="APP_TYPE" class="formselect">
                		<%-- <c:forEach items='${dictList}' var='dict'>
                			<c:if test="${dict.dictcode =='YYLX' || dict.dictcode ==''}">
								<option value='${dict.value}' ${record.APP_TYPE==dict.value?'selected':'' }>${dict.name}</option>
							</c:if>
						</c:forEach> --%>
						<option value='0' selected >普通</option>
                	</select>
                </td>
                <th><font class="redfont">*</font>&nbsp;是否开通:</th>
                <td>
                	<select name="IS_OPEN" id="IS_OPEN" class="formselect">
                		<%-- <c:forEach items='${dictList}' var='dict'>
                			<c:if test="${dict.dictcode =='SFKT' || dict.dictcode ==''}">
								<option value='${dict.value}' ${record.IS_OPEN==dict.value?'selected':'' }>${dict.name}</option>
							</c:if>
						</c:forEach> --%>
						<option value='1' selected >是</option>
                	</select>
                </td>
            </tr>
            <tr>
                <th>开通日期:</th>
                <td><input type="text" class="forminput Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:89%" id="OPEN_DATE" name="OPEN_DATE" value="${record.OPEN_DATE}"/></td>
                <th>访问地址:</th>
                <td><input type="text" class="forminput" style="width:89%" id="URL" name="URL" value="${record.URL}"/></td>
            </tr>
            <tr>
                <th>IP地址:</th>
                <td><input type="text" class="forminput" style="width:89%" id="IP_ADDR" name="IP_ADDR" value="${record.IP_ADDR}"/></td>
                <th>端口:</th>
                <td><input type="text" class="forminput" style="width:89%" id="IP_PORT" name="IP_PORT" value="${record.IP_PORT}"/></td>
            </tr>
			<tr>
				<th>应用描述:</th>
				<td colspan="3">
					<input type="text" class="forminput" style="width:89%" id="APP_DESC" name="APP_DESC" value="${record.APP_DESC}"/>
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
