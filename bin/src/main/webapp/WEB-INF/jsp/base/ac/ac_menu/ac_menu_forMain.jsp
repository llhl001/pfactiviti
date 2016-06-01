<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>organization main page</title>
<%@ include file="../../../common/common_header.jsp"%>
<%@ include file="../../../common/zTree_header.jsp"%>
<%@ include file="../../../common/contextMenu_header.jsp"%>
<script type="text/javascript" src="${ctx}/script/base/ac/ac_menu/ac_menu_tree.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<div class="location_box"><b>位置：</b><a href="">首页</a>&nbsp;&gt;&nbsp;<a href="">权限管理</a>&nbsp;&gt;&nbsp;<span>菜单管理</span></div>
<table border="0" cellpadding="0" cellspacing="0">
	<tr>
		<!-- left tree -->
		<td width="20%"  align="left" valign="top" style="BORDER-RIGHT: #999999 1px dashed">
			<div id="treeDiv" style="background-color:#f3f4f5">
				<ul id="omTree" class="ztree" style="overflow:auto;"></ul>
			</div>
		</td>
		<!-- right content -->
		<td   align="center" valign="top" width="100%">
			<iframe id="orgRightFrame" style="width:100%" name="orgRightFrame" src=""   frameborder="0" ></iframe>
		</td>
	</tr>
</table>
<%@include file="ac_menu_contextMenus.jsp" %>
</body>
</html>