var funcZTree;//树对象;
var zNodes;//树形节点;
var acFunctionCheckTreeSetting = {//树形配置;
	check : {
		enable : true,
		chkboxType : {
			"Y" : "ps",
			"N" : "ps"
		}
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
			rootPId : "-1"
		}
	}
};

var acFunctionRadioTreeSetting = {//树形配置;
		check : {
			enable : true,
			chkboxType : {
				"Y" : "ps",
				"N" : "ps"
			}
		},
		check: {
			enable: true,
			chkStyle: "radio",
			radioType: "all"
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
				rootPId : "-1"
			}
		}
	};

//初始化左边树形菜单;
function loadAcFunctionCheckTreeWithParam(url,param,type){
	$.ajax({
		type: "post",
		url: url,
		dataType:"json",
		async : false,
		data : param,
		success: function(data) {
			zNodes=data;
			windowresize();//加载窗口大小高度;
			var setting = acFunctionCheckTreeSetting;
			if(type='CHECK'){
				
			}else if(type='RADIO'){
				setting = acFunctionRadioTreeSetting;
				for(var i=0;i<zNodes.length;i++){
					if(zNodes[i].isParent){
						zNodes[i].nocheck = true;
					}
				}
			}
			$.fn.zTree.init($("#zTree"), setting, zNodes);
			funcZTree = $.fn.zTree.getZTreeObj("zTree");
		}
	});
}
function loadAcFunctionCheckTree(url){
	$.ajax({
		type: "post",
		url: url,
		dataType:"json",
		success: function(data) {
			zNodes=data;
			windowresize();//加载窗口大小高度;
			var setting = acFunctionCheckTreeSetting;
			if(type='CHECK'){
				
			}else if(type='RADIO'){
				setting = acFunctionRadioTreeSetting;
			}
			$.fn.zTree.init($("#zTree"), setting, zNodes);
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

