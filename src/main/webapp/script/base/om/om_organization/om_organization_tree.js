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
var curDragNodesParent;
var inShowNode;
var setting = {//树形配置;
	async: {
		enable: true,
		//url:getAsyncUrl,
		url:"queryTreeNodes.do",
		autoParam:["id=pId"],
		dataFilter: null
	},
	edit: {
		enable: true,
		showRemoveBtn: false,
		showRenameBtn: false,
		drag: {
			isCopy: true,
			isMove: true,
			prev:false,
			inner:true,
			next:false
			}
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
		onRightClick: OnRightClick,
		/*拖动回调*/
        beforeDrag: beforeDrag,
        beforeDrop: beforeDrop,
        onDrag: onDrag,
        onDrop: onDrop
        //,
//		beforeAsync:zTreeBeforeAsync,
//		onAsyncError: zTreeOnAsyncError,
//		//onAsyncSuccess:zTreeOnAsyncSuccess,
//		onRemove:zTreeOnRemove,
	}
};

//控制该节点是否可移动
function beforeDrag(treeId, treeNodes) {
	var id = treeNodes[0].id;
	if(id=='root'){
		 curDragNodes = null;
         curDragNodesParent=null;
		return false;
	}
    for (var i = 0, l = treeNodes.length; i < l; i++) {
        if (treeNodes[i].drag === false) {
            curDragNodes = null;
            curDragNodesParent=null;
            return false;
        } else if (treeNodes[i].parentId && treeNodes[i].getParent().drag === false) {
            curDragNodes = null;
            curDragNodesParent=null;
            return false;
        }
    }
    curDragNodes = treeNodes;
    curDragNodesParent = omTree.getNodeByParam("id", curDragNodes[0].pId, null);
    return true;
}
function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
	var id = treeNodes[0].id;
	var type=treeNodes[0].type;
	var targetType = targetNode.type;
	if(id=='root'){
		return false;
	}
	
	if(type=="ORG"){//组织机构级别;
		 if(targetType!="ORG"){//组织机构级别;
			 alert("组织机构只能移动到组织机构下!");
			 return false;
		}
	}else if(type=="POSI"){//岗位级别；
		 if(targetType=="EMP"){//人员级别;
			 alert("岗位不能移动到人员下!");
			 return false;
		}
	} else if (type=="EMP") {//人员级别
		if(targetType=="EMP"){
			 alert("人员不能移动或复制到人员下!");
			 return false;
		}
	}
	
    if(isCopy){//复制
    	if(type != "EMP"){
    		alert("机构人员以外不能复制!");
			return false;
    	}
    	if(targetType!="POSI"){
    		//暂时不允许一人多机构,同时人员不允许复制到人员下
    		alert("机构人员只能复制到岗位下!");
			return false;
    	}
    	if(targetType=="POSI"&&curDragNodesParent.type=="ORG"){
    		//暂时不允许一人多机构,同时人员不允许复制到人员下
    		alert("机构人员从机构只能移动到岗位，不能复制!");
			return false;
    	}
        if(!confirm("您确定要复制“"+treeNodes[0].name+"”到“"+targetNode.name+"”下吗？")) {
        	//不复制
        	return false;
        }
    }else{//移动
        if(!confirm("您确定要移动“"+treeNodes[0].name+"”到“"+targetNode.name+"”下吗？")) {
        	//不移动
        	return false;
        }
        if(targetType=="ORG"&&curDragNodesParent.type=="POSI"){
        	if(!confirm("人员变更机构，如果之前该人员存在多个岗位，将全部作废，确定要移动？")) {
            	//不移动
            	return false;
            }
        }
    }
    return true;
}
function onDrag(event, treeId, treeNodes) {
	
}
function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
	var id = treeNodes[0].id;
	var type=treeNodes[0].type;
	var fromId = curDragNodesParent.id;
	var fromType = curDragNodesParent.type;
	var toId = targetNode.id;
	var toType = targetNode.type;
	$.ajax({
		url: "adjustByTree.do", 
		data: { 
				id: id,
				type: type,
				fromId: fromId,
				fromType: fromType,
				toId: toId,
				toType: toType,
				isCopy:isCopy
				},
		type: 'POST',
		dataType:"json",
		success: function (data) {
			//loadTree("organization/loadtree.do");//加载树形菜单;
			if(data.flag){
				//loadTree("queryTreeNodes.do?init=init");
			}else{
				alert(data.msg);
			}
			
		}
	});

}

//单击菜单方法;
function meunClick(event, treeId, treeNode) {
	/*var nodes = omTree.getSelectedNodes();*/
	selectNode = treeNode;
	inShowNode = treeNode;
	var url= "";
	if(treeNode.id=="root"){//根节点；
		return;
	}else if(treeNode.type=="ORG"){//组织机构级别;
		url="forUpdate.do";
	}else if(treeNode.type=="POSI"){//岗位级别；
		url="../omPosition/forUpdate.do";
	}else{//人员级别；
		url="../omEmployee/forUpdate.do";
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
		async:false,
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
	loadTree("queryTreeNodes.do?init=init");//加载树形菜单;
	
	//omTree = $.fn.zTree.getZTreeObj("omTree");
	
	var selectedNode = omTree.getSelectedNodes();  
	var nodes = omTree.getNodes();  
	omTree.expandNode(nodes[0], true);  
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
	if(selectNode.id=='root'){
		$('#rootContextMenu').menu('show', {
			left:x,
			top: y
		});
	}else if(type=='ORG'){
		$('#orgContextMenu').menu('show', {
			left:x,
			top: y
		});
	}else if(type=='POSI'){
		$('#posiContextMenu').menu('show', {
			left:x,
			top: y
		});
	}else if(type=='EMP'){
		$('#empContextMenu').menu('show', {
			left:x,
			top: y
		});
	}
	
	
	
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

//右键增加下级机构方法;
function forCreateChildOrg(){
	
	var pId=omTree.getSelectedNodes()[0].id;
	//var pname=omTree.getSelectedNodes()[0].name;
	var level = omTree.getSelectedNodes()[0].level;
	 var url = "forUpdate.do?pId="+pId+"&level="+level;
	 var height = 500;
	 var width = 800;
//	 window.frames.rightFrame.location.href = url;
	 document.getElementById('orgRightFrame').src=url;
//	 readStatus(url,"添加下级机构",width,height);
}


function forCreateChildPosi(type){
	var orgId = omTree.getSelectedNodes()[0].extendAttr;
	var posiId= omTree.getSelectedNodes()[0].extendAttr1;
	var level = omTree.getSelectedNodes()[0].level;
	 var url = "../omPosition/forUpdate.do?orgId="+orgId+"&posiId="+posiId+"&level="+level;
	 var height = 500;
	 var width = 800;
	 document.getElementById('orgRightFrame').src=url;
}

function forCreateChildEmp(type){
	var orgId = omTree.getSelectedNodes()[0].extendAttr;
	var posiId= omTree.getSelectedNodes()[0].extendAttr1;
	var level = omTree.getSelectedNodes()[0].level;
	 var url = "../omEmployee/forUpdate.do?orgId="+orgId+"&posiId="+posiId+"&level="+level;
	 var height = 500;
	 var width = 800;
	 document.getElementById('orgRightFrame').src=url;
}

//维护人员角色
function forOperatorRole(){
	
	var id=omTree.getSelectedNodes()[0].id;
	var url="../acOperatorRole/forUpdate.do?empId="+id;
	defaultDialog('角色分配',url,'650px','480px');
	var nodes = omTree.getSelectedNodes();
	if (nodes.length>0) {
		var node = nodes[0];
	}
}
//维护特殊权限
function forOperFunc(){
	var id=omTree.getSelectedNodes()[0].id;
	defaultDialog('功能分配','../acFunction/forCheckTree.do?TYPE=AC_OPERATOR&OPERATOR_ID='+id,'300px','400px');
}

//删除
function forDelete(type){
	var id=selectNode.id;
	var pId=selectNode.pId;
	var url = "";
	if(type=='ORG'){
		url="../omOrganization/delete.do?id="+id;
	}else if(type=='POSI'){
		url="../omPosition/delete.do?id="+id;
	}else if(type=='EMP'){
		url="../omEmployee/delete.do?id="+id+"&pId="+pId;
	}
	
	defaultConfirm("确定要删除？<font color='red'>该操作不可逆，并删除所有关联项！</font> ",(function(){
		defaultAjax(url, "", deleteCallBackFunction);
	}));
	
}

function deleteCallBackFunction(data){
	alert(data.msg);
	if(data.flag){
		var pnode=omTree.getNodeByParam("id",selectNode.pId);
		omTree.reAsyncChildNodes(pnode, "refresh");
	}
}
//重新刷新节点；
function reloadNode(){
	omTree.reAsyncChildNodes(selectNode, "refresh");
}

//---------------------------------------------------------分界线---------------------------------------------------------------------
