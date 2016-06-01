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
	                    {id:"POSI_CODE",message:"岗位代码不能为空！"},
	        			{id:"POSI_NAME",message:"岗位名称不能为空！"}/* ,
	        			{id:"POSITION_SEQ",message:"岗位序列不能为空！"} */ 
	               	];
	BaseUtils.showWaitMsg();
	if(!formValidationFun(validateRule)) return;
	$.ajax({
		url: $("#posiForm").attr("action"),
		type:"post",
		data:  $("#posiForm").serialize(),
		dataType:"json",
		success: function(data) {
			//var ret = jQuery.parseJSON(data);
			BaseUtils.hideWaitMsg();
			var ret = data;
			
			alert(ret.msg);
			if (ret.flag) {
				var omtree = parent.window.$.fn.zTree.getZTreeObj("omTree");
				var pid ="${record.PARENT_POSI_ID }";
				if(pid==""){
					pid ="${record.ORG_ID }";
				}
            	var pnode=omtree.getNodeByParam("id",pid);
            	pnode.isParent = true;
            	omtree.reAsyncChildNodes(pnode, "refresh");
				BaseUtils.showWaitMsg();
				window.location.href="${ctx }/omPosition/forUpdate.do?id="+data.obj;
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
	<form id="posiForm" name="posiForm" action="${ctx }/omPosition/update.do" method="post">
		<input type="hidden" name="POSITION_ID" value="${record.POSITION_ID }" />
		<input type="hidden" name="POSI_LEVEL" value="${record.POSI_LEVEL }" />
		<input type="hidden" name="PARENT_POSI_ID" value="${record.PARENT_POSI_ID }" />
		<input type="hidden" name="ORG_ID" value="${record.ORG_ID }" />
		
		<!-- <table width="95%" border="0" cellspacing="0" cellpadding="0" style="margin-bottom: 0px;">
			<tr>
				<td align="right">
					<input class="btn" value="保存" type="button" onclick="save()" />
				</td>
			</tr>
		</table> -->
		<table class="form_table">
            <tr>
                <th width="20%"><font class="redfont">*</font>&nbsp;岗位代码:</th>
                <td><input type="text" class="forminput" id="POSI_CODE" name="POSI_CODE" value="${record.POSI_CODE}"/></td>
                <th width="20%"><font class="redfont">*</font>&nbsp;岗位名称:</th>
                <td><input type="text" class="forminput" id="POSI_NAME" name="POSI_NAME" value="${record.POSI_NAME}"/></td>
            </tr>
            <tr>
                <th>隶属机构:</th>
                <td>
                 <input type="hidden" readonly="readonly" class="forminput" id="ORG_ID" name="ORG_ID" value="${record.ORG_ID}"/>
               	 <input type="text" readonly="readonly" class="forminput" id="ORG_NAME" name="ORG_NAME" value="${record.ORG_NAME}"/>
                </td>
                <%-- <th><font class="redfont">*</font>&nbsp;岗位序列:</th>
                <td><input type="text" class="forminput" id="POSITION_SEQ" name="POSITION_SEQ" value="${record.POSITION_SEQ}"/></td> --%>
           
            </tr>
            <tr>
                <th>岗位类别:</th>
                <td>
                	<%-- <input type="text" class="forminput" id="POSI_TYPE" name="POSI_TYPE" value="${record.POSI_TYPE}"/> --%>
                	<select name="POSI_TYPE" id="POSI_TYPE" class="formselect">
                		<%-- <c:forEach items='${dictList}' var='dict'>
							<c:if test="${dict.dictcode =='SFWYZCD'}">
								<option value='${dict.value}' ${record.IS_LEAF==dict.value?'selected':''}>${dict.name}</option>
							</c:if>
						</c:forEach> --%>
						<option value='0' ${record.POSI_TYPE=='0'?'selected':''}>默认</option>
                	</select>
                </td>
                <th>岗位状态:</th>
                <td>
                	<%-- <input type="text" class="forminput" id="STATUS" name="STATUS" value="${record.STATUS}"/> --%>
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
                <th>生效日期:</th>
                <td><input type="text" class="forminput Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="START_DATE" name="START_DATE" value="${record.START_DATE}"/></td>
                <th>失效日期:</th>
                <td>
                	<input type="text" class="forminput Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="END_DATE" name="END_DATE" value="${record.END_DATE}"/>
                </td>
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
