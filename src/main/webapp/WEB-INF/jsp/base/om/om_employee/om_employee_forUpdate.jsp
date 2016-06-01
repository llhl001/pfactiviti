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
	                    {id:"EMP_NAME",message:"人员姓名不能为空！"},
	        			{id:"EMP_CODE",message:"人员代码不能为空！"},
	        			{id:"GENDER",message:"性别不能为空！"},
	        			{id:"USER_ID",message:"登陆用户名不能为空！"},
	        			/* {id:"PASSWORD",message:"登陆密码不能为空！"}, */
	        			{id:"STATUS",message:"操作员状态不能为空！"},
	        			{id:"EMP_STATUS",message:"人员状态不能为空！"},
	        			{id:"CARD_TYPE",message:"证件类型不能为空！"},
	        			{id:"CARD_NO",message:"证件号码不能为空！"},
	        			{id:"P_EMAIL",message:"电子邮箱格式不正确！",contentType:"email",allowNull:"true"}
	               	];
	BaseUtils.showWaitMsg();
	if(!formValidationFun(validateRule)) return;
	$.ajax({
		url: $("#empForm").attr("action"),
		type:"post",
		data:  $("#empForm").serialize(),
		dataType:"json",
		success: function(data) {
			//var ret = jQuery.parseJSON(data);
			BaseUtils.hideWaitMsg();
			var ret = data;
			
			alert(ret.msg);
			if (ret.flag) {
				var omtree = parent.window.$.fn.zTree.getZTreeObj("omTree");
				var pid ="${record.POSITION }";
				if(pid==""){
					pid ="${record.ORG_ID }";
				}
            	var pnode=omtree.getNodeByParam("id",pid);
            	if(pnode!=null){
            		pnode.isParent = true;
                	omtree.reAsyncChildNodes(pnode, "refresh");
            	}
            	BaseUtils.showWaitMsg();
				window.location.href="${ctx }/omEmployee/forUpdate.do?id="+data.obj;
			}
		}
	});
}

function forOmOrganCheckTree(pId){
	var checkedOrgIdList = $('#ORG_ID_LIST').val();
	layer.open({
		title:'关联人员',
	    type: 2,
	    area: ['300px', '400px'],
	    fix: false, //不固定
	    maxmin: true,
	    //content: '${ctx}/omOrganization/forCheckTree.do?pId='+pId+'&checkedOrgIdList='+checkedOrgIdList+'&onlyOrg=onlyOrg'
	    content: '${ctx}/omOrganization/forCheckTree.do?pId=${loginEmp.ORG_ID_LIST}&checkedOrgIdList='+checkedOrgIdList+'&onlyOrg=onlyOrg'
	});
}
</script>
<style>
</style>
</head>
<body>
<div class="right_con">
	<div class="form_box">
	<form id="empForm" name="empForm" action="${ctx }/omEmployee/update.do" method="post">
		<input type="hidden" name="EMP_ID" value="${record.EMP_ID }" />
		<input type="hidden" name="OPERATOR_ID" value="${record.OPERATOR_ID }" />
		<input type="hidden" name="ORG_ID" value="${record.ORG_ID }" />
		
		<table class="form_table">
            <tr>
                <th width="15%"><font class="redfont">*</font>&nbsp;人员姓名:</th>
                <td><input type="text" class="forminput" id="EMP_NAME" name="EMP_NAME" value="${record.EMP_NAME}"/></td>
                <th width="15%"><font class="redfont">*</font>&nbsp;人员代码:</th>
                <td><input type="text" class="forminput" id="EMP_CODE" name="EMP_CODE" value="${record.EMP_CODE}"/></td>
				<th width="15%"><font class="redfont">*</font>&nbsp;性别:</th>
                <td>
					<select class="formselect" name="GENDER" id="GENDER">
						<option value="" >请选择...      </option>
						<option value='1' ${record.GENDER==1?'selected':'' }>男</option>
						<option value='2' ${record.GENDER==2?'selected':'' }>女</option>
					</select> 
                </td>
            </tr>
            <tr>
                <th><font class="redfont">*</font>登录用户名:</th>
                <td><input type="text" class="forminput" id="USER_ID" name="USER_ID" value="${oper.USER_ID}"/></td>
                <th>密码(不改留空):</th>
                <td><input type="password" class="forminput" id="PASSWORD" name="PASSWORD"/></td>
                <th><font class="redfont">*</font>操作员状态:</th>
                <td>
                	<select class="formselect" id="STATUS" name="STATUS" >
                		<option value='0'>正常</option>
						<%-- <c:forEach items='${dictList}' var='dict'>
							<c:if test="${dict.dictcode =='CZYZT' || dict.dictcode ==''}">
								<option value='${dict.value}' ${oper.status==dict.value?'selected':''}>${dict.name}</option>
							</c:if>
						</c:forEach> --%>
					</select>
                </td>
            </tr>
            <tr>
                <th><font class="redfont">*</font>人员状态:</th>
                <td>
					<select class="formselect" name="EMP_STATUS" id="EMP_STATUS" >
						<%-- <c:forEach items='${dictList}' var='dict'>
							<c:if test="${dict.dictcode =='PERSONNELSTATUS' || dict.dictcode ==''}">
								<option value='${dict.value}' ${record.EMP_STATUS==dict.value?'selected':''}>${dict.name}</option>
							</c:if>
						</c:forEach> --%>
						<option value='0' selected>在职</option>
					</select>
                </td>
                <th><font class="redfont">*</font>证件类型:</th>
                <td>
                	<sys:dictSelect cls="formselect" name="CARD_TYPE" typeCode="certificate" defaultValue="${record.CARD_TYPE }" />
                </td>
                <th><font class="redfont">*</font>证件号码:</th>
                <td><input type="text" class="forminput" id="CARD_NO" name="CARD_NO" value="${record.CARD_NO}"/></td><!--  -->
            </tr>
            <tr>
            	<th>出生日期:</th>
                <td><input type="text" class="forminput Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="BIRTH_DATE" name="BIRTH_DATE" value="${record.BIRTH_DATE}"/></td>
                <th>入职日期:</th>
                <td><input type="text" class="forminput Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="IN_DATE" name="IN_DATE" value="${record.IN_DATE}"/></td>
                <th>离职日期:</th>
                <td><input type="text" class="forminput Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="OUT_DATE" name="OUT_DATE" value="${record.OUT_DATE}"/></td>
                
            </tr>
            <tr>
                <th>办公电话:</th>
                <td><input type="text" class="forminput" id="O_TEL" name="O_TEL" value="${record.O_TEL}"/></td>
                <th>办公邮编:</th>
                <td><input type="text" class="forminput" id="O_ZIP_CODE" name="O_ZIP_CODE" value="${record.O_ZIP_CODE}"/></td>
                <th>办公邮箱:</th>
                <td><input type="text" class="forminput" id="O_EMAIL" name="O_EMAIL" value="${record.O_EMAIL}"/></td>
            </tr>
            <tr>
                <th>办公地址:</th>
                <td><input type="text" class="forminput" id="O_ADDRESS" name="O_ADDRESS" value="${record.O_ADDRESS}"/></td>
                <th>传真号码:</th>
                <td><input type="text" class="forminput" id="FAX_NO" name="FAX_NO" value="${record.FAX_NO}"/></td>
                <th>手机号码:</th>
                <td><input type="text" class="forminput" id="MOBILE_NO" name="MOBILE_NO" value="${record.MOBILE_NO}"/></td>
            </tr>
            <tr>
				<th>MSN号码:</th>
                <td><input type="text" class="forminput" id="MSN" name="MSN" value="${record.MSN}"/></td>
                <th>家庭电话:</th>
                <td><input type="text" class="forminput" id="H_TEL" name="H_TEL" value="${record.H_TEL}"/></td>
                <th>家庭地址:</th>
                <td><input type="text" class="forminput" id="H_ADDRESS" name="H_ADDRESS" value="${record.H_ADDRESS}"/></td>
            </tr>
            <tr>
                <th>家庭邮编:</th>
                <td><input type="text" class="forminput" id="H_ZIP_CODE" name="H_ZIP_CODE" value="${record.H_ZIP_CODE}"/></td>
                <th>私人邮箱:</th>
                <td><input type="text" class="forminput" id="P_EMAIL" name="P_EMAIL" value="${record.P_EMAIL}"/></td>
                <th>政治面貌:</th>
                <td>
                	<select class="formselect" id="PARTY" name="PARTY" >
						<%-- <c:forEach items='${dictList}' var='dict'>
							<c:if test="${dict.dictcode =='POLIAFFILIATION' || dict.dictcode ==''}">
								<option value='${dict.value}' ${record.party==dict.value?'selected':''}>${dict.name}</option>
							</c:if>
						</c:forEach> --%>
						<option value="" >请选择...      </option>
					</select>
                </td>
            </tr>
            <tr>
                <th>职级:</th>
                <td>
                	<select class="formselect" id="DEGREE" name="DEGREE" >
                		<option value="" >请选择...      </option>
						<%-- <c:forEach items='${dictList}' var='dict'>
							<c:if test="${dict.dictcode =='ZJ' || dict.dictcode ==''}">
								<option value='${dict.value}' ${record.degree==dict.value?'selected':''}>${dict.name}</option>
							</c:if>
						</c:forEach> --%>
					</select>
                </td>
                <th>直接主管:</th>
                <td>
                	<select class="formselect" id="D_SUPERVISOR" name="D_SUPERVISOR" >
					</select>
                </td>
                <th>可管理机构:</th>
                <td>
                	<input type="text" id="ORG_ID_LIST_TEXT" value="${record.ORG_ID_LIST_TEXT}" onclick="forOmOrganCheckTree('${record.ORG_ID}')" readonly="readonly"  class="forminput"/>
                	<input type="hidden" id="ORG_ID_LIST" name="ORG_ID_LIST" value="${record.ORG_ID_LIST}"/>
                	
                </td>
            </tr>
			<tr>
				<%-- <th>主岗位:</th>
				<td>
					<input type="text" class="forminput" readonly="readonly" name="POSITION" value="${record.POSITION }" />
					<select class="input1" id="position" name="position" >
						<c:forEach items='${recordPosiList}' var='t'>
							<option value='${t.position}' ${record.ismain=='1'?'selected':'' }>${t.positionName}</option>
						</c:forEach>
					</select>
				</td>	 --%>
				<th>工作描述:</th>
				<td colspan="5"><input class="forminput" name="WORK_EXP" id ="WORK_EXP" value="${record.WORK_EXP}"/></td>				
			</tr>
			<tr>
				<th>备注:</th>
				<td colspan="5"><input class="forminput" name="REMARK" id ="REMARK" value="${record.REMARK}"/></td>
			</tr>
            <tr>
            	<th></th>
                <td colspan="5"><input type="button" class="formbtn1" onclick="save()" value="确认保存" /></td>
            </tr>
            
            <%-- <tr>
                <th>菜单风格:</th>
                <td>
                	<select class="formselect" id="menutype" name="menutype" >
						<c:forEach items='${dictList}' var='dict2'>
							<c:if test="${dict2.dictcode =='CDFG' || dict2.dictcode ==''}">
								<option value='${dict2.value}' ${oper.menutype==dict2.value?'selected':''}>${dict2.name}</option>
							</c:if>
						</c:forEach>
					</select>
                </td>
                <th>认证模式:</th>
                <td>
                	<select class="formselect" id="authmode" name="authmode" >
						<c:forEach items='${dictList}' var='dict'>
							<c:if test="${dict.dictcode =='RZMS' || dict.dictcode ==''}">
								<option value='${dict.value}' ${oper.authmode==dict.value?'selected':''}>${dict.name}</option>
							</c:if>
						</c:forEach>
					</select>
                </td>
                <th>IP地址:</th>
                <td>
                	<input type="text" class="forminput" id="ipaddress" name="ipaddress" value="${oper.ipaddress}"/>
                </td>
            </tr>
            <tr>
                <th>密码失效时间:</th>
                <td><input type="text" class="forminput Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="invaldate" name="invaldate" value="${oper.invaldate}"/></td>
                <th>有效开始时间:</th>
                <td><input type="text" class="forminput Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="startdate" name="startdate" value="${oper.startdate}"/></td>
                <th>有效结束时间:</th>
                <td><input type="text" class="forminput Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="enddate" name="enddate" value="${oper.enddate}"/></td>
            </tr> --%>
        </table>
	</form>
	</div>
</div>
</body>
</html>
