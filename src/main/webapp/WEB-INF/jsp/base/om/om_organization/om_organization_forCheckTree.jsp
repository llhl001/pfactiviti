<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>function check tree</title>
<%@ include file="../../../common/common_header.jsp"%>
<%@ include file="../../../common/zTree_header.jsp"%>
<script type="text/javascript" src="${ctx}/script/common/commonZTree.js"></script>
<script type="text/javascript">
function filterPer(node) {
	var halfCheck = node.getCheckStatus();
	return node.checked&&!halfCheck.half;
}


var load;
function confirmClick(){
	//等待状态
	load = layer.load(0, 0);
	var checkedNodes = mainTreeObj.getNodesByFilter(filterPer);
	var orgIdsStr = "";
	var orgNamesStr = "";
	for(var i=0;i<checkedNodes.length;i++){
		var orgId = checkedNodes[i].id;
		var orgName = checkedNodes[i].name;
		orgIdsStr+=orgId;
		orgNamesStr+=orgName;
		if(i<checkedNodes.length-1){
			orgIdsStr+=",";
			orgNamesStr+=",";
		}
	}
	
	parent.$('#ORG_ID_LIST_TEXT').val(orgNamesStr);
	parent.$('#ORG_ID_LIST').val(orgIdsStr);
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	parent.layer.close(index);
}

$(function(){
	//序列化查询条件
	var param = $("#paramForm").serialize();
	//初始化选择树
	loadTreeWithParam("${ctx}/omOrganization/queryTreeNodes.do",param,'CHECK');
	//alert(funcZTree.getCheckedNodes());
	$("#confirmButton").click(function(){
		confirmClick();
	});

	mainTreeObj.expandAll(true);
	
});


</script>
</head>
<body style="background-color:#EEEEEE;overflow:hidden;">
<form id="paramForm">
	<input type="hidden" name="pId" id="pId" value="${record.pId}"/>
	<input type="hidden" name="checkedOrgIdList" id="checkedOrgIdList" value="${record.checkedOrgIdList}"/>
	<input type="hidden" name="onlyOrg" id="checkedOrgIdList" value="${record.onlyOrg}"/>
</form>
<table border="0" id="rolefunctreetable" cellpadding="0" cellspacing="0" width="100%" height="100%" style="background-color:#f3f4f5;" align="left">
	<tr>
		<td style="padding:15px 0px 0px 10px;">
			<input id="confirmButton" type="button" value="确定" style="width: 15%;height:60%" class="formbtn1" />
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