<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- easyUI的右键菜单 -->
<link rel="stylesheet" type="text/css" href="${ctx}/component/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/component/easyui/themes/icon.css" />
<script type="text/javascript" src="${ctx}/component/easyui/jquery.easyui.min.js" ></script>
<%--
<!--使用示例-->

	<script type="text/javascript">
		$(function(){
			$('#rm').bind("contextmenu",function(e){  
		    	e.preventDefault();
				$('#rMenu').menu('show', {
					left : e.pageX,
					top : e.pageY
				}); 
		    });
		});
	</script>


<div id="rm" style="width:400px;height:800px;background:blue"></div>

<div id="rMenu" class="easyui-menu" style="width:120px;display:none;">
	<div id="addjigou" data-options="iconCls:'icon-plus'" onclick="javascript:addChildOrg();">增加下级机构</div>
	<div class="menu-sep"></div>
	<div id="addgangwei" data-options="iconCls:'icon-position'" onclick="javascript:addOrgPosition();">增加下级岗位</div>
	<div class="menu-sep"></div>
	<div id="addpgangwei" data-options="iconCls:'icon-position'" onclick="javascript:addPPosition();">增加下级岗位</div>
</div>


 --%>

