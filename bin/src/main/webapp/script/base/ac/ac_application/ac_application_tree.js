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
			rootPId : "-1"
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
		url="forUpdate.do";
	}else if(treeNode.type=="FUNC_GROUP"){
		url="../acFuncGroup/forUpdate.do";
	}else{
		url="../acFunction/forUpdate.do";
	}
	
	if(treeNode.id!="root"){
		url+="?id="+ treeNode.id;
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
		contextMenuId = 'rootContextMenu';
	}else if(type=='APP'){
		contextMenuId = 'appContextMenu';
	}else if(type=='FUNC_GROUP'){
		contextMenuId = 'funcGroupContextMenu';
	}else if(type=='FUNCTION'){
		contextMenuId = 'functionContextMenu';
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
function forCreateApplication(){
	
	var pId=omTree.getSelectedNodes()[0].id;
	//var pname=omTree.getSelectedNodes()[0].name;
	var level = omTree.getSelectedNodes()[0].level;
	 var url = "forUpdate.do";
	 var height = 500;
	 var width = 800;
//	 window.frames.rightFrame.location.href = url;
	 document.getElementById('orgRightFrame').src=url;
//	 readStatus(url,"添加下级机构",width,height);
}


function forCreateFuncGroup(type){
	var appId = omTree.getSelectedNodes()[0].extendAttr;
	var funcGroupId= omTree.getSelectedNodes()[0].extendAttr1;
	var level = omTree.getSelectedNodes()[0].level;
	 var url = "../acFuncGroup/forUpdate.do?funcGroupId="+funcGroupId+"&appId="+appId+"&level="+level;
	 var height = 500;
	 var width = 800;
	 document.getElementById('orgRightFrame').src=url;
}

function forCreateFunction(type){
	 var funcGroupId= omTree.getSelectedNodes()[0].extendAttr1;
	 var url = "../acFunction/forUpdate.do?funcGroupId="+funcGroupId;
	 var height = 500;
	 var width = 800;
	 document.getElementById('orgRightFrame').src=url;
}

//重新刷新节点；
function reloadNode(){
	omTree.reAsyncChildNodes(selectNode, "refresh");
}


//右键删除应用；
function forDelete(type){
	var name = omTree.getSelectedNodes()[0].name;
	if(type=='APP'){
		defaultConfirm("您确认要删除选中的应用: "+name+"? <font color='red'>注意：将删除所有子项及其关联关系,且该操作不可逆。</font>",okHandlerApplication);
	}else if(type=='FUNC_GROUP'){
		defaultConfirm("您确认要删除选中的功能组: "+name+"? <font color='red'>注意：将删除所有子项及其关联关系,且该操作不可逆。</font>",okHandlerFuncGroup);
	}else if(type=='FUNCTION'){
		defaultConfirm("您确认要删除选中的功能: "+name+"? <font color='red'>注意：将删除所有关联关系,且该操作不可逆。</font>",okHandlerFunction);
	}
	
}
function okHandlerApplication(){
	var nodes = omTree.getSelectedNodes();
//	var id = nodes[0].id;
	$.ajax({
		type: "post",
		global:false,
		url:"../acApplication/delete.do",
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
function okHandlerFuncGroup(){
	var nodes = omTree.getSelectedNodes();
	$.ajax({
		type: "post",
		global:false,
		url:"../acFuncGroup/delete.do",
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
function okHandlerFunction(){
	var nodes = omTree.getSelectedNodes();
	$.ajax({
		type: "post",
		global:false,
		url:"../acFunction/delete.do",
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
//---------------------------------------------------------分界线---------------------------------------------------------------------
