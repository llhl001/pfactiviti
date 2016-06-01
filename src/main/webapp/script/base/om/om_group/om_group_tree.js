var nodes;//节点;
var omTree;//树对象;
var offsetTree;//下拉树形;
var offzNodes;//下拉节点;
var rMenu;//右键菜单；
var selectId;//当前tree对象选中的id;
var selectNode;//当前tree对象选中的节点;
var params;//需要传递的参数；
var zNodes;//树形节点;
var curDragNodes, autoExpandNode;

var inShowNode;
var setting = {//树形配置;
	async: {
		enable: true,
		url:"queryTreeNodes.do",
		autoParam:["id=pId"],
		dataFilter: null
	},
	view : {
		showTitle : false,
		fontCss : {
			color : "#104E8B"
		}
	},
	data : {
		key : {
			children : "children",
			name : "name",
			title : ""
		},
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "pId",
			rootPId : "root"
		}
	},
	callback : {
		onClick : meunClick,
		onRightClick: OnRightClick//,
	}
};

//单击菜单方法;
function meunClick(event, treeId, treeNode) {
	selectNode = treeNode;
	inShowNode = treeNode;
	var url= "";
	if(treeNode.type=="ROOT"){//根节点；
		return;
	}else if(treeNode.type=="group"){
		url="../omGroup/forUpdate.do";
	}/*else if(treeNode.type=="leafGroup"){
		url="../omGroup/forUpdate.do";
	}*/else{
		url="../omEmployee/forDetail.do";
	}
	if(treeNode.id!="ROOT"){
		url+="?id="+ treeNode.id+"&isParent="+ treeNode.isParent;
	}
	document.getElementById('orgRightFrame').src=url;
}


//初始化左边树形菜单;
function loadTree(url){
	windowresize();
	$.fn.zTree.init($("#omTree"), setting, zNodes);
	$.ajax({
		type: "post",
		global:false,
		url: url,
		dataType:"json",
		success: function(data) {
			zNodes=data;
			windowresize();//加载窗口大小高度;
			$.fn.zTree.init($("#omTree"), setting, zNodes);
			omTree = $.fn.zTree.getZTreeObj("omTree");

		}
	});
}
var inter;
//窗口大小改变监听方法;
window.onresize=function(){
	windowresize();
	inter = setInterval("setSize()","1");
}
//窗口大小改变时执行的方法;
function windowresize(){
	var height = document.documentElement.clientHeight;
}
function setSize(){
	$(window.parent.document.body).find("#orgRightFrame").attr("style",$(window.parent.document.body).find("#treeDiv").attr("style"));
	clearInterval(inter);
}
$(document).ready(function() {
	var scrollHeight = window.screen.height;
	var screenWidth = window.screen.width;
	//$("#ac_menu_guid_jsp1").parents("table:first").css({"margin-top":"-10px","margin-left":"-10px"});
	$("#omTree").css({"height":$(window.parent.rightFrame).innerHeight()-50,"width":210});
	$("#orgRightFrame").css({"height":$(window.parent.rightFrame).innerHeight()-50,"width":window.screen.width-$(window.parent.leftFrame).innerWidth() - 235});
	loadTree("queryTreeNodes.do");//加载树形菜单;
});


//右键单击菜单执行方法;
function OnRightClick(event, treeId, treeNode)
{
	selectId=treeNode.id;
	selectNode=treeNode;
	omTree.selectNode(treeNode);
	showRMenu(treeNode.type, event.clientX, event.clientY);
}


//展现右键菜单方法;
function showRMenu(type, x, y) {
	var contextMenuId = '';
	if(type=='ROOT'){
		contextMenuId = 'root';
	}else if(type=='group'){
		contextMenuId = 'group';
	}else if(type=='LEAF'){
		contextMenuId = 'leaf';
	}else{
		return;
	}
	$('#'+contextMenuId).menu('show', {
		left:x,
		top: y
	});
}

//右键增加应用;
function forCreateMenu(type){
	var pId = omTree.getSelectedNodes()[0].id;
	var level= omTree.getSelectedNodes()[0].extendAttr;
	if(type=='ROOT'){
		level=0;
	}
	level=parseInt(level)+1;
	 var url = "../omGroup/forUpdate.do?pId="+pId+"&level="+level;
	 var height = 500;
	 var width = 800;
	 document.getElementById('orgRightFrame').src=url;
}

function forAddEmp(){
	var pId = omTree.getSelectedNodes()[0].id;
	defaultDialog('增加人员','../omEmployee/forCheckTree.do?GROUP_ID='+pId+'&TYPE=OM_GROUP','300px','500px');
}

//---------------------------------------------------------分界线---------------------------------------------------------------------


function isParentJudge(treeId, treeNode){
	alert(treeNode);

	return true;
}


//弹出子窗口
function readStatus(url,title,width,height){
	 $.dialog({
	title:title,
	id:'permissio',
	width:width,
	height:height,
	content:'url:'+url
	});
}

//ajax加载之前执行方法;
function zTreeBeforeAsync(treeId, treeNode) {
    return true;
};

//ajax加载失败时执行方法;
function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
    wx_error("系统出现异常:"+XMLHttpRequest);
};
//删除节点方法;
function zTreeOnRemove(event, treeId, treeNode){
	var nodes=treeNode.getParentNode().children;
	if(nodes.length<=0){
		treeNode.getParentNode().iconSkin="icon10";
		omTree.updateNode(treeNode.getParentNode());
	}else{
		omTree.selectNode(treeNode.getParentNode().children[0]);//选中的节点;*/
	}
}
//重新刷新节点；
function reloadNode(){
	omTree.reAsyncChildNodes(selectNode, "refresh");
}
// 去除根节点打开关闭方法;
function dblClickExpand(treeId, treeNode) {
	return treeNode.level > 0;
}

//右键增加节点方法;
function rightAddNode(){
	showIframeData('ShowReport.wx?PAGEID=acmenureport',params);
}

////右键增加岗位方法;
//function addOrgPosition(){
//	var pId=omTree.getSelectedNodes()[0].id;
//	var pname=omTree.getSelectedNodes()[0].name;
//	var level = omTree.getSelectedNodes()[0].level;
//	 var url = "organization/addChildPosition.do?pId="+pId+"&pname="+pname+"&level="+level;
//	 var height = 500;
//	 var width = 800;
//	 document.getElementById('orgRightFrame').src=url;
//}
////右键岗位增加下级岗位方法;
//function addPPosition(){
//	var pId=omTree.getSelectedNodes()[0].id;
//	var pname=omTree.getSelectedNodes()[0].name;
//	var level = omTree.getSelectedNodes()[0].level;
//	 var url = "organization/addPChildPosition.do?pId="+pId+"&pname="+pname+"&level="+level;
//	 var height = 500;
//	 var width = 800;
//	 document.getElementById('orgRightFrame').src=url;
//}
//初始化方法;xml中onload事件;
function pnodonload(){
	omTree=window.parent.omTree;
	var nodes =omTree.getSelectedNodes();
	if (nodes.length>0) {
		var node = nodes[0];
		$("[value_name='parentsid']").attr("value",node.id);
		$("#acmenureporttreenode_guid_report1_parentsname").attr("value",node.name);
	}
}
//右键删除工作组；
function forDelete(type){

	var nodes = omTree.getSelectedNodes();
	var node=nodes[0];
	
	defaultConfirm("确定要删除？",(function(){
		if(type=='LEAF'){
			deleteEmpGroup();
		}else{
			okHandler();
		}
	}));
	
/*	if(confirm("确定要删除？ ")){
		if(type=='LEAF'){
			deleteEmpGroup();
		}else{
			okHandler();
		}
	}*/
				
//	$.dialog.confirm(
//			"您确认要删除选中的组织机构？<br>删除机构: "+node.name+"<br><br><font color='red'>注意：删除组织机构将删除该组织机构下所有子菜单。</font>",
//			function(){
//				alert("confirm");
//			}
//	);
}
function deleteEmpGroup(){
	var nodes = omTree.getSelectedNodes();
	$.ajax({
		type: "post",
		global:false,
		url:"../omEmpGroup/delete.do",
		data:{EMP_ID:nodes[0].id,GROUP_ID:nodes[0].pId},
		dataType:"json",
		success: function(data) {
			alert(data.msg);
			if(data.flag){
				var pnode=omTree.getNodeByParam("id",nodes[0].pId);
				omTree.reAsyncChildNodes(pnode, "refresh");
			}
		}
	});
}

function okHandler(){
	var nodes = omTree.getSelectedNodes();
	$.ajax({
		type: "post",
		global:false,
		url:"../omGroup/delete.do",
		data:{id:nodes[0].id},
		dataType:"json",
		success: function(data) {
			status=data.status;
			if(status!=0){
//				$.dialog({
//					title:'消息',
//					content: '操作成功！',
//					time:1
//				});
				alert(" 操作成功！");
				var pnode=omTree.getNodeByParam("id",nodes[0].pId);
				omTree.reAsyncChildNodes(pnode, "refresh");
			}
		}
	});
}

//跳转树形加载菜单;
	function LoadTreeIframeData(url){
		var Iframe = document.getElementById("treeframe");
		Iframe.src=url;
	}
//树形单击菜单在主iframe中展现方法;
	function showIframeData(url,params){
		if(isJson(params)){
			url=getParamUrl(url,params);
		}
			var Iframe = document.getElementById("rightFrame");
			Iframe.src=url;
		
	}
	//获取参数url
	function getParamUrl(url,params){
		if(url.indexOf("?")>=0){
			if(url.indexOf("?")!=url.length-1){
				if(url.substring(url.indexOf("?")+1).length>0){
					$.each(params,function(index,value){
						url+="&"+index+"="+value;
					});
					return url;
				}
			}else{
					$.each(params,function(index,value){
						if(url.substring(url.indexOf("?")+1).length>0){
							url+="&"+index+"="+value;
						}else{
							url+=index+"="+value;
						}
					});
					return url;
			}
		}else{
			url+="?";
			url=getParamUrl(url,params);
			return url;
		}
	}
	//判断是否为json格式数据；
 isJson = function(obj){
	var isjson = typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;
	return isjson;
}
//点击之前执行的方法;
function beforeClick(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("offsetTree");
	zTree.checkNode(treeNode,treeNode.checked, false, true);
	return false;
}
//选中节点执行的方法;
function onCheck(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("offsetTree"), nodes = zTree.getCheckedNodes(true), v = "";
	$("[oldvalue_name='parentsid']").attr("oldvalue",nodes[0].id);
	$("#acmenureport_guid_report1_parentsname").val(nodes[0].name);
	$("#acmenureport_guid_report1_parentsname").focus();
}
//下拉菜单事件显示菜单；
function showMenu() {
	var nodeID=$("#[oldvalue_name='menuid']").attr("oldvalue");
	initOffsetTree("acmenu/loadOffSetTree.do?nodeId="+nodeID);
	var offsetObj = $("#acmenureport_guid_report1_parentsname");
	var treeOffset = $("#acmenureport_guid_report1_parentsname").offset();
	$("#menuContent").css({left : treeOffset.left + "px",top : treeOffset.top + offsetObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}
//隐藏菜单;
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
//下拉树形菜单隐藏方法;
function onBodyDown(event) {
	if (!(event.target.id == "acmenureport_guid_acmenu_report1_parentsname"
			|| event.target.id == "menuContent" || $(event.target)
			.parents("#menuContent").length > 0)) {
		hideMenu();
	}
}
//获取加载树形菜单路径;
function getAsyncUrl(treeId,treeNode) {
	alert("inGetAsyncUrl");
	return "";
};
//初始化下拉菜单值;
function initOffsetTree(url){
	var offsetsetting = {
	 check : {
		enable : true,
		chkStyle: "radio",
		radioType: "level"
	},
	async : {
		autoParam : ["id"],
		enable : true,
		type : "post",
		otherParam : {
			"name" : "admin",
			"pwd" : "123"
		},
		url : ""
	},
	view : {
		showTitle : true,
		fontCss : {
			color : "#104E8B"
		},
		dblClickExpand : dblClickExpand
	},
	data : {
		key : {
			children : "children",
			name : "name",
			title : ""
		},
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "pId",
			rootPId : ""
		}
	},
	callback : {
		beforeClick:beforeClick,
		onCheck : onCheck
	}
};
	$.ajax({
		type: "post",
		global:false,
		url: url,
		dataType:"json",
		success: function(data) {
			if(data!=null){
				var pId=$("[oldvalue_name='parentsid']").attr("oldvalue");
				if(pId!=0){
					offzNodes=data;
					$.fn.zTree.init($("#offsetTree"), offsetsetting, offzNodes);
					offsetTree= $.fn.zTree.getZTreeObj("offsetTree");
					var node=offsetTree.getNodeByParam("id",pId);
					offsetTree.selectNode(node);
				}
			}
		}
	});
}
