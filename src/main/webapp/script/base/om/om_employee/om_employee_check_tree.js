var nodes;//节点;
var funcZTree;//树对象;
var offsetTree;//下拉树形;
var offzNodes;//下拉节点;
var rMenu;//右键菜单；
var selectId;//当前tree对象选中的id;
var selectNode;//当前tree对象选中的节点;
var params;//需要传递的参数；
var zNodes;//树形节点;
var curDragNodes, autoExpandNode;

var inShowNode;
var omEmployeeCheckTreeSetting = {//树形配置;
	check : {
		enable : true,
		chkboxType : {
			"Y" : "ps",
			"N" : "ps"
		}
	},
	/*async: {
		enable: false,
		//url:getAsyncUrl,
		url:"../acFunction/queryTreeNodes.do",
		autoParam:["id=pId"],
		dataFilter: null
	},
	async : {
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
	}
};

//初始化左边树形菜单;
function loadOmEmployeeCheckTreeWithParam(url,param){
	$.ajax({
		type: "post",
		url: url,
		dataType:"json",
		async : false,
		data : param,
		success: function(data) {
			zNodes=data;
			windowresize();//加载窗口大小高度;
			$.fn.zTree.init($("#zTree"), omEmployeeCheckTreeSetting, zNodes);
			funcZTree = $.fn.zTree.getZTreeObj("zTree");
		}
	});
}
function loadOmEmployeeCheckTree(url){
	$.ajax({
		type: "post",
		url: url,
		dataType:"json",
		success: function(data) {
			zNodes=data;
			windowresize();//加载窗口大小高度;
			$.fn.zTree.init($("#zTree"), omEmployeeCheckTreeSetting, zNodes);
			funcZTree = $.fn.zTree.getZTreeObj("zTree");

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
//重新刷新节点；
function reloadNode(){
	zTree.reAsyncChildNodes(selectNode, "refresh");
}
/*$(document).ready(function() {
	var scrollHeight = window.screen.height;
	var screenWidth = window.screen.width;
	if(navigator.userAgent.indexOf("MSIE")>0)
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
	 } 
	//$("#ac_menu_guid_jsp1").parents("table:first").css({"margin-top":"-10px","margin-left":"-10px"});
	$("#zTree").css({"height":$(window.parent.rightFrame).innerHeight()-50,"width":210});
	$("#orgRightFrame").css({"height":$(window.parent.rightFrame).innerHeight()-50,"width":window.screen.width-$(window.parent.leftFrame).innerWidth() - 235});
	loadTree("../acFunction/queryTreeNodes.do");//加载树形菜单;
});*/







//---------------------------------------------------------分界线---------------------------------------------------------------------


//
//
//
//function isParentJudge(treeId, treeNode){
//	alert(treeNode);
//
//	return true;
//}
//
//
////弹出子窗口
//function readStatus(url,title,width,height){
//	 $.dialog({
//	title:title,
//	id:'permissio',
//	width:width,
//	height:height,
//	content:'url:'+url
//
////	iconTitle:false,
////	cover:true,
////	maxBtn:false,
////	xButton:true,
////	resize:true,
////	page:url
//	});
////	dg.ShowDialog();
//}
//
////ajax加载之前执行方法;
//function zTreeBeforeAsync(treeId, treeNode) {
//    return true;
//};
////ajax加载成功后执行方法；
////function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
////	zTree.selectNode(zTree.getNodeByParam("id",selectId,treeNode));//选中的节点;*/
////};
////ajax加载失败时执行方法;
//function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
//    wx_error("系统出现异常:"+XMLHttpRequest);
//};
////删除节点方法;
//function zTreeOnRemove(event, treeId, treeNode){
//	var nodes=treeNode.getParentNode().children;
//	if(nodes.length<=0){
//		treeNode.getParentNode().iconSkin="icon10";
//		zTree.updateNode(treeNode.getParentNode());
//	}else{
//		zTree.selectNode(treeNode.getParentNode().children[0]);//选中的节点;*/
//	}
//}

//// 去除根节点打开关闭方法;
//function dblClickExpand(treeId, treeNode) {
//	return treeNode.level > 0;
//}
//
//
////右键增加节点方法;
//function rightAddNode(){
//	showIframeData('ShowReport.wx?PAGEID=acmenureport',params);
//}
//
////右键增加机构机构人员方法;
//function addEmpOrg(){
//	var pId=zTree.getSelectedNodes()[0].id;
//	var pname=zTree.getSelectedNodes()[0].name;
//	var level = zTree.getSelectedNodes()[0].level;
//	 var url = "organization/addEmp.do?pId="+pId+"&pname="+pname+"&level="+level;
////	 window.frames.rightFrame.location.href = url;
//	 document.getElementById('orgRightFrame').src=url;
//	/* readStatus(url,"添加下级机构",width,height);
//	url=getParamUrl('ShowReport.wx?PAGEID=om_employeeadd&REPORTID=report1',{"id":zTree.getSelectedNodes()[0].id});
//	wx_winpage(url,{width:750,height:500,title:"增加机构人员"});
//	var nodes = zTree.getSelectedNodes();
//	if (nodes.length>0) {
//		var node = nodes[0];
//	}*/
//}
////右键增加岗位人员方法;
//function addEmpPosition(){
//	var pId=zTree.getSelectedNodes()[0].id;
//	var pname=zTree.getSelectedNodes()[0].name;
//	var level=zTree.getSelectedNodes()[0].level;
//	 var url = "organization/addPosiEmp.do?pId="+pId+"&pname="+pname+"&level="+level;
////	 window.frames.rightFrame.location.href = url;
//	 document.getElementById('orgRightFrame').src=url;
//}
////右键维护人员角色方法;
//function operatorRole(){
//	var pId=zTree.getSelectedNodes()[0].id;
//	var pname=zTree.getSelectedNodes()[0].name;
//	var url="ompartyrole/operatorRole.do?partyid="+pId;
//	 readStatus(url,"维护人员角色",1100,480);
//	var nodes = zTree.getSelectedNodes();
//	if (nodes.length>0) {
//		var node = nodes[0];
//	}
//}
////右键维护人员特殊权限方法;
//function operatorSpecialFunc(){
//	var operatorid=zTree.getSelectedNodes()[0].id;
//	var pname=zTree.getSelectedNodes()[0].name;
//	var url="ac_operfunc/operatorSpecialFunc.do?empId="+operatorid;
//	 readStatus(url,"维护人员特殊权限",1100,500);
//	var nodes = zTree.getSelectedNodes();
//	if (nodes.length>0) {
//		var node = nodes[0];
//	}
//}
////右键维护组织机构权限方法;
//function orgRole(){
//	var pId=zTree.getSelectedNodes()[0].id;
//	var pname=zTree.getSelectedNodes()[0].name;
//	var url="ompartyrole/orgRole.do?partyid="+pId+"&partytype=01";
//	 readStatus(url,"维护机构权限",600,500);
//	var nodes = zTree.getSelectedNodes();
//	if (nodes.length>0) {
//		var node = nodes[0];
//	}
//}
//
////右键维护岗位权限方法;
//function posiRole(){
//	var pId=zTree.getSelectedNodes()[0].id;
//	var pname=zTree.getSelectedNodes()[0].name;
//	var url="ompartyrole/orgRole.do?partyid="+pId+"&partytype=03";
//	//wx_winpage(url,{width:1000,height:530,title:"维护人员权限",initsize:'max'});
//	 readStatus(url,"维护岗位权限",600,480);
//	var nodes = zTree.getSelectedNodes();
//	if (nodes.length>0) {
//		var node = nodes[0];
//	}
//}
//
////右键增加岗位方法;
//function addOrgPosition(){
///*	id=zTree.getSelectedNodes()[0].iconSkin=="OpIcon02"?zTree.getSelectedNodes()[0].id:zTree.getSelectedNodes()[0].target;
//	name=zTree.getSelectedNodes()[0].iconSkin=="OpIcon02"?zTree.getSelectedNodes()[0].name:zTree.getNodeByParam("id",id).name;
//	manaposi=zTree.getSelectedNodes()[0].iconSkin=="OpIcon02"?"":zTree.getSelectedNodes()[0].id;*/
//	var pId=zTree.getSelectedNodes()[0].id;
//	var pname=zTree.getSelectedNodes()[0].name;
//	var level = zTree.getSelectedNodes()[0].level;
//	 var url = "organization/addChildPosition.do?pId="+pId+"&pname="+pname+"&level="+level;
//	 var height = 500;
//	 var width = 800;
////	 window.frames.rightFrame.location.href = url;
//	 document.getElementById('orgRightFrame').src=url;
////	 readStatus(url,"添加下级岗位",width,height);
//	
//	/*url=getParamUrl('ShowReport.wx?PAGEID=om_position&REPORTID=report1',{"id":id,"orgname":name,"manaposi":manaposi});
//	wx_winpage(url,{width:630,height:300,title:"增加下级岗位"});
//	var nodes = zTree.getSelectedNodes();
//	if (nodes.length>0) {
//		var node = nodes[0];
//	}*/
//}
////右键岗位增加下级岗位方法;
//function addPPosition(){
///*	id=zTree.getSelectedNodes()[0].iconSkin=="OpIcon02"?zTree.getSelectedNodes()[0].id:zTree.getSelectedNodes()[0].target;
//	name=zTree.getSelectedNodes()[0].iconSkin=="OpIcon02"?zTree.getSelectedNodes()[0].name:zTree.getNodeByParam("id",id).name;
//	manaposi=zTree.getSelectedNodes()[0].iconSkin=="OpIcon02"?"":zTree.getSelectedNodes()[0].id;*/
//	var pId=zTree.getSelectedNodes()[0].id;
//	var pname=zTree.getSelectedNodes()[0].name;
//	var level = zTree.getSelectedNodes()[0].level;
//	 var url = "organization/addPChildPosition.do?pId="+pId+"&pname="+pname+"&level="+level;
//	 var height = 500;
//	 var width = 800;
////	 window.frames.rightFrame.location.href = url;
//	 document.getElementById('orgRightFrame').src=url;
////	 readStatus(url,"添加下级岗位",width,height);
//	
//	/*url=getParamUrl('ShowReport.wx?PAGEID=om_position&REPORTID=report1',{"id":id,"orgname":name,"manaposi":manaposi});
//	wx_winpage(url,{width:630,height:300,title:"增加下级岗位"});
//	var nodes = zTree.getSelectedNodes();
//	if (nodes.length>0) {
//		var node = nodes[0];
//	}*/
//}
////初始化方法;xml中onload事件;
//function pnodonload(){
//	zTree=window.parent.zTree;
//	var nodes =zTree.getSelectedNodes();
//	if (nodes.length>0) {
//		var node = nodes[0];
//		$("[value_name='parentsid']").attr("value",node.id);
//		$("#acmenureporttreenode_guid_report1_parentsname").attr("value",node.name);
//	}
//}
////右键删除机构节点；
//function delOrg(){
//	var nodes = zTree.getSelectedNodes();
//	var node=nodes[0];
//	$.dialog.confirm(
//			"您确认要删除选中的组织机构？<br>删除机构: "+node.name+"<br><br><font color='red'>注意：删除组织机构将删除该组织机构下所有子菜单。</font>",
//			function(){
//				okHandler();
//			}
//	);
//}
//function okHandler(){
//	var nodes = zTree.getSelectedNodes();
//	$.ajax({
//		type: "post",
//		global:false,
//		url:"organization/deleteOrg.do",
//		data:{orgid:nodes[0].id},
//		dataType:"json",
//		success: function(data) {
//			status=data.status;
//			if(status==0){
//				$.dialog({
//					title:'消息',
//					content: '操作成功！',
//					time:1
//				});
//				var pnode=zTree.getNodeByParam("id",nodes[0].pId);
//				zTree.reAsyncChildNodes(pnode, "refresh");
//			}
//		}
//	});
//}
////删除机构人员
//function delEmployee(){
//	var nodes = zTree.getSelectedNodes();
//	var node=nodes[0];
//	$.dialog.confirm(
//			"您确认要删除选中的菜单？<br>删除人员: "+node.name+"<br><br><font color='red'>注意：删除人员下所有子菜单。</font>",
//			function(){
//				okEmpHandler();
//			}
//	);
//	//wx_confirm("删除所选项,将同时删除所有可能的下级项,是否继续？","删除所选项"+node.name,400,200,okHandlerEmp);
//}
//function okEmpHandler(){
//	var nodes = zTree.getSelectedNodes();
//	$.ajax({
//		type: "post",
//		global:false,
//		url:"omemployee/deletemployee.do",
//		data:{empId:nodes[0].id,orgid:nodes[0].pId},
//		dataType:"json",
//		success: function(data) {
//			status=data.status;
//			if(status==0){
//				$.dialog({
//					title:'消息',
//					content: '操作成功！',
//					time:1
//				});
//				var pnode=zTree.getNodeByParam("id",nodes[0].pId);
//				zTree.reAsyncChildNodes(pnode, "refresh");
//			}
//		}
//	});
//}
////删除岗位;
//function delPosition(){
//	var nodes = zTree.getSelectedNodes();
//	var node=nodes[0];
//	$.dialog.confirm(
//			"您确认要删除选中的岗位？<br>删除岗位: "+node.name+"<br><br><font color='red'>注意：删除岗位将删除该岗位下所有子菜单。</font>",
//			function(){
//				okHandlerPosi();
//			}
//	);
//}
//function okHandlerPosi(){
//		var nodes = zTree.getSelectedNodes();
//		$.ajax({
//			type: "post",
//			global:false,
//			url:"position/deleteposi.do",
//			data:{positionid:nodes[0].id},
//			dataType:"json",
//			success: function(data) {
//				status=data.status;
//				if(status==0){
//					$.dialog({
//						title:'消息',
//						content: '操作成功！',
//						time:1
//					});
//					var pnode=zTree.getNodeByParam("id",nodes[0].pId);
//					zTree.reAsyncChildNodes(pnode, "refresh");
//				}
//			}
//		});
//}
////删除岗位人员;
//function delEmpPosi(){
//	var nodes = zTree.getSelectedNodes();
//	var node=nodes[0];
//	$.dialog.confirm(
//			"您确认要删除选中的岗位人员？<br>删除岗位: "+node.name+"<br><br><font color='red'>注意：删除岗位将删除该岗位下所有子菜单。</font>",
//			function(){
//				okHandlerEmp();
//			}
//	);
//}
//function okHandlerEmp(){
//		var nodes = zTree.getSelectedNodes();
//		$.ajax({
//			type: "post",
//			global:false,
//			url:"omemployee/deletemployee.do",
//			data:{empId:nodes[0].id,posid:nodes[0].pId},
//			dataType:"json",
//			success: function(data) { 
//				status=data.status;
//				
//				//status=data.status;
//				if(status==0){
//					$.dialog({
//						title:'消息',
//						content: '操作成功！',
//						time:1
//					});
//					var pnode=zTree.getNodeByParam("id",nodes[0].pId);
//					zTree.reAsyncChildNodes(pnode, "refresh");
//				}
//				
//				/*if(status==0){
//					wx_success("操作成功！");
//					var pnode=zTree.getNodeByParam("id",nodes[0].pId);
//					zTree.reAsyncChildNodes(pnode, "refresh");
//				}*/
//			}
//		});
//		
//}
//
///*function hideRMenu() {//zTree右键用到的方法;
//	if (rMenu) rMenu.css({"visibility": "hidden"});
//	$("body").unbind("mousedown", onBodyMouseDown);
//}
//function onBodyMouseDown(event){
//	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
//		rMenu.css({"visibility" : "hidden"});
//	}
//}*/
////跳转树形加载菜单;
//	function LoadTreeIframeData(url){
//		var Iframe = document.getElementById("treeframe");
//		Iframe.src=url;
//	}
////树形单击菜单在主iframe中展现方法;
//	function showIframeData(url,params){
//		if(isJson(params)){
//			url=getParamUrl(url,params);
//		}
//			var Iframe = document.getElementById("rightFrame");
//			Iframe.src=url;
//		
//	}
//	//获取参数url
//	function getParamUrl(url,params){
//		if(url.indexOf("?")>=0){
//			if(url.indexOf("?")!=url.length-1){
//				if(url.substring(url.indexOf("?")+1).length>0){
//					$.each(params,function(index,value){
//						url+="&"+index+"="+value;
//					});
//					return url;
//				}
//			}else{
//					$.each(params,function(index,value){
//						if(url.substring(url.indexOf("?")+1).length>0){
//							url+="&"+index+"="+value;
//						}else{
//							url+=index+"="+value;
//						}
//					});
//					return url;
//			}
//		}else{
//			url+="?";
//			url=getParamUrl(url,params);
//			return url;
//		}
//	}
//	//判断是否为json格式数据；
// isJson = function(obj){
//	var isjson = typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;
//	return isjson;
//}
////点击之前执行的方法;
//function beforeClick(treeId, treeNode) {
//	var funcZTree = $.fn.zTree.getZTreeObj("offsetTree");
//	zTree.checkNode(treeNode,treeNode.checked, false, true);
//	return false;
//}
////选中节点执行的方法;
//function onCheck(e, treeId, treeNode) {
//	var funcZTree = $.fn.zTree.getZTreeObj("offsetTree"), nodes = zTree.getCheckedNodes(true), v = "";
//	/*for (var i = 0, l = nodes.length; i < l; i++) {
//		v += nodes[i].name + ",";
//	}
//	if (v.length > 0)
//		v = v.substring(0, v.length - 1);*/
//	$("[oldvalue_name='parentsid']").attr("oldvalue",nodes[0].id);
//	$("#acmenureport_guid_report1_parentsname").val(nodes[0].name);
//	$("#acmenureport_guid_report1_parentsname").focus();
//}
////下拉菜单事件显示菜单；
//function showMenu() {
//	var nodeID=$("#[oldvalue_name='menuid']").attr("oldvalue");
//	initOffsetTree("acmenu/loadOffSetTree.do?nodeId="+nodeID);
//	var offsetObj = $("#acmenureport_guid_report1_parentsname");
//	var treeOffset = $("#acmenureport_guid_report1_parentsname").offset();
//	$("#menuContent").css({left : treeOffset.left + "px",top : treeOffset.top + offsetObj.outerHeight() + "px"}).slideDown("fast");
//	$("body").bind("mousedown", onBodyDown);
//}
////隐藏菜单;
//function hideMenu() {
//	$("#menuContent").fadeOut("fast");
//	$("body").unbind("mousedown", onBodyDown);
//}
////下拉树形菜单隐藏方法;
//function onBodyDown(event) {
//	if (!(event.target.id == "acmenureport_guid_acmenu_report1_parentsname"
//			|| event.target.id == "menuContent" || $(event.target)
//			.parents("#menuContent").length > 0)) {
//		hideMenu();
//	}
//}
////获取加载树形菜单路径;
//function getAsyncUrl(treeId,treeNode) {
//	/*var pId=treeNode.id;
//	if(treeNode.iconSkin=="OpIcon01"){//岗位；
//		url="position/loadChildtree.do?pId="+pId;
//	}else if(treeNode.iconSkin=="OpIcon02"||treeNode.iconSkin=="ORoot"){//机构;
//		url="organization/loadChildtree.do?pId="+pId;
//	}
//    return treeNode.isParent?url:"";*/
//	alert("inGetAsyncUrl");
//	return "";
//};
////初始化下拉菜单值;
//function initOffsetTree(url){
//	var offsetsetting = {
//	 check : {
//		enable : true,
//		chkStyle: "radio",
//		radioType: "level"
//	},
//	async : {
//		autoParam : ["id"],
//		enable : true,
//		type : "post",
//		otherParam : {
//			"name" : "admin",
//			"pwd" : "123"
//		},
//		url : ""
//	},
//	view : {
//		showTitle : true,
//		fontCss : {
//			color : "#104E8B"
//		},
//		dblClickExpand : dblClickExpand
//	},
//	data : {
//		key : {
//			children : "children",
//			name : "name",
//			title : ""
//		},
//		simpleData : {
//			enable : true,
//			idKey : "id",
//			pIdKey : "pId",
//			rootPId : ""
//		}
//	},
//	callback : {
//		beforeClick:beforeClick,
//		onCheck : onCheck
//	}
//};
//	$.ajax({
//		type: "post",
//		global:false,
//		url: url,
//		dataType:"json",
//		success: function(data) {
//			if(data!=null){
//				var pId=$("[oldvalue_name='parentsid']").attr("oldvalue");
//				if(pId!=0){
//					offzNodes=data;
//					$.fn.zTree.init($("#offsetTree"), offsetsetting, offzNodes);
//					offsetTree= $.fn.zTree.getZTreeObj("offsetTree");
//					var node=offsetTree.getNodeByParam("id",pId);
//					offsetTree.selectNode(node);
//				}
//			}
//		}
//	});
//}
