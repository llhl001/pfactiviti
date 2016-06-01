<%@ page pageEncoding="UTF-8"%>
<div id="appContextMenu" class="easyui-menu" style="width:120px;display:none;">
	<div id="createMenu" data-options="iconCls:'icon-plus'" onclick="javascript:forCreateMenu('APP');">增加菜单</div>
	<div class="menu-sep"></div>
	<div id="reload" data-options="iconCls:'icon-reload'" onclick="javascript:reloadNode();">刷新</div>
</div>

<div id="parentMenuContextMenu" class="easyui-menu" style="width:120px;display:none;">
	<div id="createMenu" data-options="iconCls:'icon-plus'" onclick="javascript:forCreateMenu('PARENT_MENU');">增加菜单</div>
	<div class="menu-sep"></div>
	<div id="delete" data-options="iconCls:'icon-delete'" onclick="javascript:forDelete('PARENT_MENU');">删除</div>
	<div class="menu-sep"></div>
	<div id="reload" data-options="iconCls:'icon-reload'" onclick="javascript:reloadNode();">刷新</div>
</div>

<div id="leafMenuContextMenu" class="easyui-menu" style="width:120px;display:none;">
	<div id="delete" data-options="iconCls:'icon-delete'" onclick="javascript:forDelete('LEAF_MENU');">删除</div>
	<div class="menu-sep"></div>
	<div id="reload" data-options="iconCls:'icon-reload'" onclick="javascript:reloadNode();">刷新</div>
</div>

