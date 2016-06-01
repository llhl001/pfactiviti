<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>function check tree</title>
<%@ include file="../../../common/common_header.jsp"%>
<%@ include file="../../../common/zTree_header.jsp"%>
<script type="text/javascript" src="${ctx}/script/base/om/om_employee/om_employee_check_tree.js"></script>
<script type="text/javascript">
function acRoleFilterPer(node) {
	return (node.type =="EMP"&&node.checked);
}
//提交保存时不应删除的操作员id
var notInOperatorIds = "";
function acRoleCallBack(data){
	var node;
	for(var i=0;i<data.length;i++){
		node=funcZTree.getNodeByParam("extendAttr",data[i].OPERATOR_ID);
		if(node==null){
			notInOperatorIds = notInOperatorIds+data[i].OPERATOR_ID+",";
			continue;
		}
		funcZTree.checkNode(node,true,true,false);
	}
}
function acOperatorRoleSaveCallBack(data){
	//var ret = jQuery.parseJSON(data);
	//BaseUtils.hideWaitMsg();
	layer.close(load);
	alert(data.msg);
	if (data.flag) {
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		parent.pageNow('${record.pageNow}');
		parent.layer.close(index);
	}
}

var load;
function acOperatorRoleSave(){
	//等待状态
	load =  layer.load(0, 0);
	var checkedFuncNodes = funcZTree.getNodesByFilter(acRoleFilterPer);
	var jsonIdStr = "";
	for(var i=0;i<checkedFuncNodes.length;i++){
		var operatorId = checkedFuncNodes[i].extendAttr;
		//去重复
		if(jsonIdStr.indexOf(operatorId) != -1){
			continue;
		}
		jsonIdStr+="{ROLE_ID:'${record.ROLE_ID}',OPERATOR_ID:'"+checkedFuncNodes[i].extendAttr+"'}";
		if(i<checkedFuncNodes.length-1){
			jsonIdStr+=",";
		}
	}
	var jsonDataPer="["+jsonIdStr+"]";
	var jsonData = {"dataJsonStr":jsonDataPer,'roleId':'${record.ROLE_ID}','notInOperatorIds':notInOperatorIds};
	
	defaultAjax("${ctx}/acOperatorRole/batchInsertByJson.do",jsonData,acOperatorRoleSaveCallBack);
	
	
}

//保存时不可删除的empid
var notInEmpIds = "";
function omGroupCallBack(data){
	var node;
	for(var i=0;i<data.length;i++){
		node=funcZTree.getNodeByParam("id",data[i].EMP_ID);
		if(node==null){
			notInEmpIds = notInOperatorIds+data[i].EMP_ID+",";
			continue;
		}
		funcZTree.checkNode(node,true,true,false);
	}
}

function omEmpGroupSave(){
	//等待状态
	load =  layer.load(0, 0);
	var checkedFuncNodes = funcZTree.getNodesByFilter(acRoleFilterPer);
	var jsonIdStr = "";
	for(var i=0;i<checkedFuncNodes.length;i++){
		var operatorId = checkedFuncNodes[i].extendAttr;
		//去重复
		if(jsonIdStr.indexOf(operatorId) != -1){
			continue;
		}
		jsonIdStr+="{GROUP_ID:'${record.GROUP_ID}',EMP_ID:'"+checkedFuncNodes[i].id+"'}";
		if(i<checkedFuncNodes.length-1){
			jsonIdStr+=",";
		}
	}
	var jsonDataPer="["+jsonIdStr+"]";
	var jsonData = {"dataJsonStr":jsonDataPer,'groupId':'${record.GROUP_ID}','notInEmpIds':notInEmpIds};
	
	defaultAjax("${ctx}/omEmpGroup/batchInsertByJson.do",jsonData,omEmpGroupSaveCallBack);

	
}

function omEmpGroupSaveCallBack(data){
	//var ret = jQuery.parseJSON(data);
	//BaseUtils.hideWaitMsg();
	layer.close(load);
	alert(data.msg);
	if (data.flag) {
		
		var omtree = parent.window.$.fn.zTree.getZTreeObj("omTree");
		var pnode=omtree.getNodeByParam("id","${record.GROUP_ID}");
		pnode.isParent = true;
		omtree.reAsyncChildNodes(pnode, "refresh");
		
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		parent.layer.close(index);
	}
}

$(function(){
	//序列化查询条件
	var param = $("#paramForm").serialize();
	//初始化选择树
	loadOmEmployeeCheckTreeWithParam("${ctx}/omEmployee/queryOrgEmpTreeNodes.do");
	var type="${record.TYPE}";
	//根据TYPE参数来断定是哪个页面调用选择树
	if(type=='AC_ROLE'){
		//加载默认选中
		defaultAjax("${ctx}/acOperatorRole/queryOperatorRole.do",param,acRoleCallBack);
		$("#saveButton").click(function(){
			acOperatorRoleSave();
		});
	}else if(type=='OM_GROUP'){
		//加载默认选中
		defaultAjax("${ctx}/omEmpGroup/queryEmpGroup.do",param,omGroupCallBack);
		$("#saveButton").click(function(){
			omEmpGroupSave();
		});
	}
	funcZTree.expandAll(true);
	//alert(funcZTree.getCheckedNodes());
	
});


</script>
</head>
<body style="background-color:#EEEEEE;overflow:hidden;">
<form id="paramForm">
	<input type="hidden" name="ROLE_ID" id="ROLE_ID" value="${record.ROLE_ID}"/>
	<input type="hidden" name="GROUP_ID" id="GROUP_ID" value="${record.GROUP_ID}"/>
</form>
<table border="0" id="rolefunctreetable" cellpadding="0" cellspacing="0" width="100%" height="100%" style="background-color:#f3f4f5;" align="left">
	<tr>
		<td style="padding:15px 0px 0px 10px;">
			<input id="saveButton" type="button" value="保存" style="width: 15%;height:60%" class="formbtn1" />
		</td>
	</tr>
	<tr>
		<td width="260px" align="left" valign="top">
			<div id="treeDiv"
				style="background-color:#f3f4f5;padding:10px 0px 0px 0px;height:400px;overflow-y:auto">
				<ul id="zTree" class="ztree" style="overflow:auto;">
					<%-- <li>
						<img src="XXXXXX/component/ztree/css/zTreeStyle/img/diy/loading.gif"></img>
						<font size="4">正在加载菜单项，请稍候...</font>
					</li> --%>
				</ul>
			</div>
		</td>
	</tr>
</table>
<script type="text/javascript">
	var scrollHeight = window.screen.height;
	var screenWidth = window.screen.width;
	if (navigator.userAgent.indexOf("MSIE") > 0) {
		if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
			flag = false;
		}
		if (navigator.userAgent.indexOf("MSIE 7.0") > 0) {
			flag = false;
		}
		if (navigator.userAgent.indexOf("MSIE 8.0") > 0) {
			scrollHeight -= 201;
		}
		if (navigator.userAgent.indexOf("MSIE 9.0") > 0) {
			scrollHeight -= 201;
		}
	} else {
		flag = false;
	}
	/* document.getElementById("savediv").style.height = scrollHeight + "px"; */
</script>
</body>
</html>