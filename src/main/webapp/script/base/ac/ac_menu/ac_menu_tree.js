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
		//url:getAsyncUrl,
		url:"queryTreeNodes.do",
		autoParam:["id=pId"],
		dataFilter: null
	},
	/*async : {
		autoParam : ["id"],
		enable : true,
		type : "post",
		otherParam : {
			"name" : "name",
			"pwd" : ""
		},
		url :getAsyncUrl
	},*/
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
//		beforeAsync:zTreeBeforeAsync,
//		onAsyncError: zTreeOnAsyncError,
//		//onAsyncSuccess:zTreeOnAsyncSuccess,
//		onRemove:zTreeOnRemove,
	}
};

//单击菜单方法;
function meunClick(event, treeId, treeNode) {
	/*var nodes = omTree.getSelectedNodes();*/
	selectNode = treeNode;
	inShowNode = treeNode;
	var url= "";
	if(treeNode.type=="ROOT"){//根节点；
		return;
	}else if(treeNode.type=="APP"){
		return;
	}else if(treeNode.type=="PARENT_MENU"){
		url="../acMenu/forUpdate.do";
	}else{
		url="../acMenu/forUpdate.do";
	}
	
	if(treeNode.id!="ROOT"){
		url+="?id="+ treeNode.id+"&isParent="+ treeNode.isParent;
	}
//	window.frames.rightFrame.location.href = url;
	document.getElementById('orgRightFrame').src=url;
}


//初始化左边树形菜单;
function loadTree(url){
	windowresize();
	/*zNodes = [
	        { "id":"1", pId:0, name:"父1", open:true,iconSkin:""},
			{ id:11, pId:1, name:"父11", iconSkin:"OpIcon02"},
			{ id:111, pId:11, name:"叶111", iconSkin:"OpIcon02"},
			{ id:112, pId:11, name:"叶112", iconSkin:"OpIcon02"},
			{ id:113, pId:11, name:"叶113", iconSkin:"OpIcon02"},
			{ id:114, pId:11, name:"叶114", type:"parent", iconSkin:"OpIcon02"},
			{ id:12, pId:1, name:"叶12"}
	  	];*/
	/*zNodes = [{"id":"root","pId":"-1","name":"机构人员树","type":"ORG","checked":false,"iconSkin":"OpIcon02","attributes":null,"parent":true,open:true},
	          {"id": "000B9F7C-0000-0000-0000-00004D545C34","pId": "root","name": "山东省科学院","type": "ORG","checked": false,"iconSkin": "OpIcon02","attributes": null,"parent": true},
	          {"id": "51ab80a0-96c8-4b8d-af19-184a08a55f59","pId": "root","name": "测试1","type": "ORG","checked": false,"iconSkin": "OpIcon02","attributes": null,"parent": true}];
	*/
	//$.fn.zTree.init($("#omTree"), setting, zNodes);
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
//	omTree = $.fn.zTree.getZTreeObj("omTree");
//	furl="organization/showOrgEmp.do";//右侧加载页面
//	document.getElementById('orgRightFrame').src=furl;
//	//window.frames.rightFrame.location.href = furl;
//	rMenu = $("#rMenu");
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
	/*if(navigator.userAgent.indexOf("MSIE")>0)
	{ 
	    if(navigator.userAgent.indexOf("MSIE 6.0")>0)
	    { 
	    	flag = false;
	    } 
	    if(navigator.userAgent.indexOf("MSIE 7.0")>0)
	    {
	    	flag = false;
	    } 
	    if(navigator.userAgent.indexOf("MSIE 8.0")>0)
	    {
	    	scrollHeight-=201;
	    } 
	    if(navigator.userAgent.indexOf("MSIE 9.0")>0)
	    {
	    	scrollHeight-=201;
	    } 
	 }else{
	     flag = false;
	 } */
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
	/*if(!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
		omTree.cancelSelectedNode();
		showRMenu("root", event.clientX, event.clientY);
	}else if (treeNode && !treeNode.noR) {
		omTree.selectNode(treeNode);
		if(treeNode.id=="root"){
			params={'id':treeNode.id,'name':encodeURIComponent(treeNode.name)};
		}else{
			params={'id':treeNode.id,'name':encodeURIComponent(treeNode.name),'pId':treeNode.getParentNode().id,'pName':encodeURIComponent(treeNode.getParentNode().name)};
		}
		showRMenu(treeNode.type, event.clientX, event.clientY);
	}*/
}


//展现右键菜单方法;
function showRMenu(type, x, y) {
	var contextMenuId = '';
	if(type=='ROOT'){
		return;
	}else if(type=='APP'){
		contextMenuId = 'appContextMenu';
	}else if(type=='PARENT_MENU'){
		contextMenuId = 'parentMenuContextMenu';
	}else if(type=='LEAF_MENU'){
		contextMenuId = 'leafMenuContextMenu';
	}else{
		return;
	}
	$('#'+contextMenuId).menu('show', {
		left:x,
		top: y
	});
	
	
	/*$("#rMenu ul").show();//zTree中的方法;
			if (type=="root") {
				$("#m_del").hide();
				$("#m_check").hide();
				$("#m_unCheck").hide();
			} else {
				$("#m_del").show();
				$("#m_check").show();
				$("#m_unCheck").show();
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

	$("body").bind("mousedown", onBodyMouseDown);*/
}

//右键增加应用;

function forCreateMenu(type){
	var pId = omTree.getSelectedNodes()[0].id;
	/*var funcGroupId= omTree.getSelectedNodes()[0].extendAttr1;*/
	var appId= omTree.getSelectedNodes()[0].extendAttr;
	var level = omTree.getSelectedNodes()[0].level;
	 var url = "../acMenu/forUpdate.do?pId="+pId+"&level="+level+"&appId="+appId;
	 var height = 500;
	 var width = 800;
	 document.getElementById('orgRightFrame').src=url;
}

//---------------------------------------------------------分界线---------------------------------------------------------------------
//右键删除应用；
function forDelete(type){
	var name = omTree.getSelectedNodes()[0].name;
//	if(type=='PARENT_MENU'){
	defaultConfirm("您确认要删除选中的菜单: "+name+"? 注意：将删除所有子菜单。",okHandlerParent);
}
function okHandlerParent(){
	var nodes = omTree.getSelectedNodes();
	$.ajax({
		type: "post",
		global:false,
		url:"../acMenu/delete.do",
		data:{id:nodes[0].id},
		dataType:"json",
		success: function(data) {
			status=data.status;
			if(status!=0){
				alert(" 操作成功！");
				var pnode=omTree.getNodeByParam("id",nodes[0].pId);
				omTree.reAsyncChildNodes(pnode, "refresh");
			}
		}
	});
}
//function okHandlerParent(){
//	var nodes = omTree.getSelectedNodes();
//	$.ajax({
//		type: "post",
//		global:false,
//		url:"../acMenu/delete.do",
//		data:{id:nodes[0].id},
//		dataType:"json",
//		success: function(data) {
//			status=data.status;
//			if(status!=0){
//				alert(" 操作成功！");
//				var pnode=omTree.getNodeByParam("id",nodes[0].pId);
//				omTree.reAsyncChildNodes(pnode, "refresh");
//			}
//		}
//	});
//}
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

//	iconTitle:false,
//	cover:true,
//	maxBtn:false,
//	xButton:true,
//	resize:true,
//	page:url
	});
//	dg.ShowDialog();
}

//ajax加载之前执行方法;
function zTreeBeforeAsync(treeId, treeNode) {
    return true;
};
//ajax加载成功后执行方法；
//function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
//	omTree.selectNode(omTree.getNodeByParam("id",selectId,treeNode));//选中的节点;*/
//};
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

//右键增加机构机构人员方法;
function addEmpOrg(){
	var pId=omTree.getSelectedNodes()[0].id;
	var pname=omTree.getSelectedNodes()[0].name;
	var level = omTree.getSelectedNodes()[0].level;
	 var url = "organization/addEmp.do?pId="+pId+"&pname="+pname+"&level="+level;
//	 window.frames.rightFrame.location.href = url;
	 document.getElementById('orgRightFrame').src=url;
	/* readStatus(url,"添加下级机构",width,height);
	url=getParamUrl('ShowReport.wx?PAGEID=om_employeeadd&REPORTID=report1',{"id":omTree.getSelectedNodes()[0].id});
	wx_winpage(url,{width:750,height:500,title:"增加机构人员"});
	var nodes = omTree.getSelectedNodes();
	if (nodes.length>0) {
		var node = nodes[0];
	}*/
}
//右键增加岗位人员方法;
function addEmpPosition(){
	var pId=omTree.getSelectedNodes()[0].id;
	var pname=omTree.getSelectedNodes()[0].name;
	var level=omTree.getSelectedNodes()[0].level;
	 var url = "organization/addPosiEmp.do?pId="+pId+"&pname="+pname+"&level="+level;
//	 window.frames.rightFrame.location.href = url;
	 document.getElementById('orgRightFrame').src=url;
}
//右键维护人员角色方法;
function operatorRole(){
	var pId=omTree.getSelectedNodes()[0].id;
	var pname=omTree.getSelectedNodes()[0].name;
	var url="ompartyrole/operatorRole.do?partyid="+pId;
	 readStatus(url,"维护人员角色",1100,480);
	var nodes = omTree.getSelectedNodes();
	if (nodes.length>0) {
		var node = nodes[0];
	}
}
//右键维护人员特殊权限方法;
function operatorSpecialFunc(){
	var operatorid=omTree.getSelectedNodes()[0].id;
	var pname=omTree.getSelectedNodes()[0].name;
	var url="ac_operfunc/operatorSpecialFunc.do?empId="+operatorid;
	 readStatus(url,"维护人员特殊权限",1100,500);
	var nodes = omTree.getSelectedNodes();
	if (nodes.length>0) {
		var node = nodes[0];
	}
}
//右键维护组织机构权限方法;
function orgRole(){
	var pId=omTree.getSelectedNodes()[0].id;
	var pname=omTree.getSelectedNodes()[0].name;
	var url="ompartyrole/orgRole.do?partyid="+pId+"&partytype=01";
	 readStatus(url,"维护机构权限",600,500);
	var nodes = omTree.getSelectedNodes();
	if (nodes.length>0) {
		var node = nodes[0];
	}
}

//右键维护岗位权限方法;
function posiRole(){
	var pId=omTree.getSelectedNodes()[0].id;
	var pname=omTree.getSelectedNodes()[0].name;
	var url="ompartyrole/orgRole.do?partyid="+pId+"&partytype=03";
	//wx_winpage(url,{width:1000,height:530,title:"维护人员权限",initsize:'max'});
	 readStatus(url,"维护岗位权限",600,480);
	var nodes = omTree.getSelectedNodes();
	if (nodes.length>0) {
		var node = nodes[0];
	}
}

//右键增加岗位方法;
function addOrgPosition(){
/*	id=omTree.getSelectedNodes()[0].iconSkin=="OpIcon02"?omTree.getSelectedNodes()[0].id:omTree.getSelectedNodes()[0].target;
	name=omTree.getSelectedNodes()[0].iconSkin=="OpIcon02"?omTree.getSelectedNodes()[0].name:omTree.getNodeByParam("id",id).name;
	manaposi=omTree.getSelectedNodes()[0].iconSkin=="OpIcon02"?"":omTree.getSelectedNodes()[0].id;*/
	var pId=omTree.getSelectedNodes()[0].id;
	var pname=omTree.getSelectedNodes()[0].name;
	var level = omTree.getSelectedNodes()[0].level;
	 var url = "organization/addChildPosition.do?pId="+pId+"&pname="+pname+"&level="+level;
	 var height = 500;
	 var width = 800;
//	 window.frames.rightFrame.location.href = url;
	 document.getElementById('orgRightFrame').src=url;
//	 readStatus(url,"添加下级岗位",width,height);
	
	/*url=getParamUrl('ShowReport.wx?PAGEID=om_position&REPORTID=report1',{"id":id,"orgname":name,"manaposi":manaposi});
	wx_winpage(url,{width:630,height:300,title:"增加下级岗位"});
	var nodes = omTree.getSelectedNodes();
	if (nodes.length>0) {
		var node = nodes[0];
	}*/
}
//右键岗位增加下级岗位方法;
function addPPosition(){
/*	id=omTree.getSelectedNodes()[0].iconSkin=="OpIcon02"?omTree.getSelectedNodes()[0].id:omTree.getSelectedNodes()[0].target;
	name=omTree.getSelectedNodes()[0].iconSkin=="OpIcon02"?omTree.getSelectedNodes()[0].name:omTree.getNodeByParam("id",id).name;
	manaposi=omTree.getSelectedNodes()[0].iconSkin=="OpIcon02"?"":omTree.getSelectedNodes()[0].id;*/
	var pId=omTree.getSelectedNodes()[0].id;
	var pname=omTree.getSelectedNodes()[0].name;
	var level = omTree.getSelectedNodes()[0].level;
	 var url = "organization/addPChildPosition.do?pId="+pId+"&pname="+pname+"&level="+level;
	 var height = 500;
	 var width = 800;
//	 window.frames.rightFrame.location.href = url;
	 document.getElementById('orgRightFrame').src=url;
//	 readStatus(url,"添加下级岗位",width,height);
	
	/*url=getParamUrl('ShowReport.wx?PAGEID=om_position&REPORTID=report1',{"id":id,"orgname":name,"manaposi":manaposi});
	wx_winpage(url,{width:630,height:300,title:"增加下级岗位"});
	var nodes = omTree.getSelectedNodes();
	if (nodes.length>0) {
		var node = nodes[0];
	}*/
}
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
/*function hideRMenu() {//zTree右键用到的方法;
	if (rMenu) rMenu.css({"visibility": "hidden"});
	$("body").unbind("mousedown", onBodyMouseDown);
}
function onBodyMouseDown(event){
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
		rMenu.css({"visibility" : "hidden"});
	}
}*/
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
	/*for (var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].name + ",";
	}
	if (v.length > 0)
		v = v.substring(0, v.length - 1);*/
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
	/*var pId=treeNode.id;
	if(treeNode.iconSkin=="OpIcon01"){//岗位；
		url="position/loadChildtree.do?pId="+pId;
	}else if(treeNode.iconSkin=="OpIcon02"||treeNode.iconSkin=="ORoot"){//机构;
		url="organization/loadChildtree.do?pId="+pId;
	}
    return treeNode.isParent?url:"";*/
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
