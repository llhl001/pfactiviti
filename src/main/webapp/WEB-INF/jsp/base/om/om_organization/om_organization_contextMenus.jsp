<%@ page pageEncoding="UTF-8"%>
<div id="rootContextMenu" class="easyui-menu" style="width:120px;display:none;">
	<div id="createChildOrg" data-options="iconCls:'icon-plus'" onclick="javascript:forCreateChildOrg();">增加下级机构</div>
	<div class="menu-sep"></div>
	<div id="reload" data-options="iconCls:'icon-reload'" onclick="javascript:reloadNode();">刷新</div>
</div>

<div id="orgContextMenu" class="easyui-menu" style="width:120px;display:none;">
	<div id="createChildOrg" data-options="iconCls:'icon-plus'" onclick="javascript:forCreateChildOrg();">增加下级机构</div>
	<div class="menu-sep"></div>
	<div id="createChildPosi" data-options="iconCls:'icon-position'" onclick="javascript:forCreateChildPosi('ORG');">增加下级岗位</div>
	<div class="menu-sep"></div>
	<div id="createChildPosi" data-options="iconCls:'icon-position'" onclick="javascript:forCreateChildEmp('ORG');">增加人员</div>
	<div class="menu-sep"></div>
	<div id="delete" data-options="iconCls:'icon-delete'" onclick="javascript:forDelete('ORG');">删除</div>
	<div class="menu-sep"></div>
	<div id="reload" data-options="iconCls:'icon-reload'" onclick="javascript:reloadNode();">刷新</div>
</div>

<div id="posiContextMenu" class="easyui-menu" style="width:120px;display:none;">
	<div id="createChildPosi" data-options="iconCls:'icon-position'" onclick="javascript:forCreateChildPosi('POSI');">增加下级岗位</div>
	<div class="menu-sep"></div>
	<div id="createChildPosi" data-options="iconCls:'icon-position'" onclick="javascript:forCreateChildEmp('POSI');">增加人员</div>
	<div class="menu-sep"></div>
	<div id="delete" data-options="iconCls:'icon-delete'" onclick="javascript:forDelete('POSI');">删除</div>
	<div class="menu-sep"></div>
	<div id="reload" data-options="iconCls:'icon-reload'" onclick="javascript:reloadNode();">刷新</div>
</div>

<div id="empContextMenu" class="easyui-menu" style="width:120px;display:none;">
	<div id="weihuemp" data-options="iconCls:'icon-pencil'" onclick="javascript:forOperatorRole();">维护人员角色</div>
	<div class="menu-sep"></div>
	<div id="weihuspequanxian" data-options="iconCls:'icon-pencil'" onclick="javascript:forOperFunc();">维护特殊权限</div>
	<div class="menu-sep"></div>
	<div id="delete" data-options="iconCls:'icon-delete'" onclick="javascript:forDelete('EMP');">删除</div>
	<div class="menu-sep"></div>
	<div id="reload" data-options="iconCls:'icon-reload'" onclick="javascript:reloadNode();">刷新</div>
</div>
