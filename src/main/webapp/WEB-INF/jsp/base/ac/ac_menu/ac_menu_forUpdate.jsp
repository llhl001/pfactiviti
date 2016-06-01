<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>机构详情</title>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ include file="../../../common/common_header.jsp"%>
<script type="text/javascript">
function forAcFuncCheckTree(menuId,appId){
	layer.open({
		title:'功能分配',
	    type: 2,
	    area: ['300px', '400px'],
	    fix: false, //不固定
	    maxmin: true,
	    content: '${ctx}/acFunction/forCheckTree.do?APP_ID='+appId+'&MENU_ID='+menuId+'&TYPE=AC_MENU&IS_MENU=1'
	});
}
function leafInfo(){
	var IS_LEAF=$("#IS_LEAF").val();
	if("1" == IS_LEAF){//1:是        0:否
		$(".leafinfo").show();
		$('#ICON_SKIN').val('RFicon03');
	} else {
		$('#ICON_SKIN').val('');
		$(".leafinfo").hide();
		$("#FUNC_ID").val("");
		$("#FUNC_NAME").val("");
		/* $("#appid").val(""); */
		$("#MENU_ACTION").val("");
		$("#PARAMETER").val("");
		/* $("#expandpath").val("");
		$("#imagepath").val(""); */
	}
	
	if("${record.MENU_LEVEL=='4'}"=='true'){
		$(".leafinfo").show();
		$('#ICON_SKIN').val('RFicon03');
		$('#IS_LEAF').val('1');
		$('#IS_LEAF').prop('disabled',true);
	}
	if("${record.MENU_LEVEL=='1'}"=='true'){
		$(".leafinfo").hide();
		
		$('#IS_LEAF').val('0');
		$('#IS_LEAF').prop('disabled',true);
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
	                    {id:"MENU_NAME",message:"菜单名称不能为空！"},
	        			{id:"MENU_LABEL",message:"菜单显示名称不能为空！"},
	        			{id:"MENU_CODE",message:"菜单代码不能为空！"},
	        			{id:"DISPLAY_ORDER",message:"显示顺序请填写数字！",contentType:"number",allowNull:"false"},
	        			/* {id:"MENU_CSS",message:"菜单直接样式不能为空！"}, */
	        			{id:"IS_LEAF",message:"是否叶子菜单不能为空！"}
	        			 
	               	];
	BaseUtils.showWaitMsg();
	if(!formValidationFun(validateRule)) return;
	$('#IS_LEAF').prop('disabled',false);
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
			            	var pnode=omtree.getNodeByParam("id","${record.PARENT_ID}");
			            	pnode.isParent = true;
			            	omtree.reAsyncChildNodes(pnode, "refresh");
							BaseUtils.showWaitMsg();
							window.location.href="${ctx }/acMenu/forUpdate.do?id="+data.obj;
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
	<form id="menuForm" name="menuForm" action="${ctx }/acMenu/update.do" method="post">
		<input type="hidden" id="MENU_ID" name="MENU_ID" value="${record.MENU_ID }" />
		<input type="hidden" id="FUNC_ID" name="FUNC_ID" value="${record.FUNC_ID }" />
		<input type="hidden" id="APP_ID" name="APP_ID" value="${record.APP_ID }" />
		<%-- <input type="hidden" name="MENU_LEVEL" value="${record.MENU_LEVEL }" /> --%>
		
		<!-- <table width="98%" border="0" cellspacing="0" cellpadding="0" style="margin-bottom: 15px;"> -->
		<table class="form_table">
            <tr>
                <th width="15%"><font class="redfont">*</font>&nbsp;父菜单:</th>
                <td width="35%">
                	<input type="hidden" class="forminput" readonly="readonly" name="PARENT_ID" value="${record.PARENT_ID }" />
                	<input type="text" class="forminput" readonly="readonly" name="PARENT_NAME" value="${record.PARENT_NAME }" />
                </td>
                <th width="15%"><font class="redfont">*</font>&nbsp;菜单名称:</th>
                <td width="35%"><input type="text" class="forminput" id="MENU_NAME" name="MENU_NAME" value="${record.MENU_NAME}"/></td>
            </tr>
            <tr>
                <th><font class="redfont">*</font>&nbsp;菜单显示名称:</th>
                <td><input type="text" class="forminput" id="MENU_LABEL" name="MENU_LABEL" value="${record.MENU_LABEL}"/></td>
                <th><font class="redfont">*</font>&nbsp;菜单代码:</th>
                <td><input type="text" class="forminput" id="MENU_CODE" name="MENU_CODE" value="${record.MENU_CODE}"/></td>
            </tr>
            <tr>
                <th>显示顺序:</th>
                <td><input type="text" class="forminput" id="DISPLAY_ORDER" name="DISPLAY_ORDER" value="${record.DISPLAY_ORDER}"/></td>
                <th>菜单层次:</th>
                <td><input type="text" class="forminput" id="MENU_LEVEL" name="MENU_LEVEL" readonly value="${record.MENU_LEVEL}"/></td>
            </tr>
            <tr>
            	<th><font class="redfont">*</font>&nbsp;是否叶子菜单:</th>
                <td><input type="hidden" class="forminput" id="ICON_SKIN" name="ICON_SKIN" value="${record.ICON_SKIN}"/>
                	<select name="IS_LEAF" id="IS_LEAF" class="formselect" onchange="leafInfo()">
                		<%-- <c:forEach items='${dictList}' var='dict'>
							<c:if test="${dict.dictcode =='SFWYZCD'}">
								<option value='${dict.value}' ${record.IS_LEAF==dict.value?'selected':''}>${dict.name}</option>
							</c:if>
						</c:forEach> --%>
						<option value="" >请选择...      </option>
						<option value='0' ${record.IS_LEAF=='0'?'selected':''}>否</option>
						<option value='1' ${record.IS_LEAF=='1'?'selected':''}>是</option>
                	</select>
                </td>
            	<th><!-- <font class="redfont">*</font>&nbsp; -->菜单直接样式:</th>
                <td><input type="text" class="forminput" id="MENU_CSS" name="MENU_CSS" value="${record.MENU_CSS}"/></td>
                <!-- <th><font class="redfont">*</font>&nbsp;ZTree皮肤:</th> -->
                
                
            </tr>
            <c:if test="${record.MENU_LEVEL!=1}">
            <tr>
            	
            </tr>
            </c:if>
            <tr class="leafinfo">
                <th>对应功能:</th>
                <td><input type="text" class="forminput" id="FUNC_NAME" name="FUNC_NAME" readonly value="${record.FUNC_NAME}" onclick="forAcFuncCheckTree('${record.MENU_ID }','${record.APP_ID }')"/></td>
                <th>资源url:</th>
                <td>
                	<span id="MENU_URL">${record.MENU_ACTION}${record.PARAMETER}</span>
                	<input type="hidden" class="forminput" id="MENU_ACTION" name="MENU_ACTION" readonly value="${record.MENU_ACTION}" />
                	<input type="hidden" class="forminput" id="PARAMETER" name="PARAMETER" readonly value="${record.PARAMETER}"/>
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
