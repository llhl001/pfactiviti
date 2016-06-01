<%@ page pageEncoding="UTF-8"%>
<div id="rootContextMenu" class="easyui-menu" style="width:120px;display:none;">
	<div id="createApplication" data-options="iconCls:'icon-plus'" onclick="javascript:forCreateApplication();">新增应用</div>
	<div class="menu-sep"></div>
	<div id="reload" data-options="iconCls:'icon-reload'" onclick="javascript:reloadNode();">刷新</div>
</div>
<div id="appContextMenu" class="easyui-menu" style="width:120px;display:none;">
	<div id="createFuncGroup" data-options="iconCls:'icon-plus'" onclick="javascript:forCreateFuncGroup('APP');">增加下级功能组</div>
	<div class="menu-sep"></div>
	<div id="delete" data-options="iconCls:'icon-delete'" onclick="javascript:forDelete('APP');">删除</div>
	<div class="menu-sep"></div>
	<div id="reload" data-options="iconCls:'icon-reload'" onclick="javascript:reloadNode();">刷新</div>
</div>

<div id="funcGroupContextMenu" class="easyui-menu" style="width:120px;display:none;">
	<div id="createFuncGroup" data-options="iconCls:'icon-plus'" onclick="javascript:forCreateFuncGroup('FUNC_GROUP');">增加下级功能组</div>
	<div class="menu-sep"></div>
	<div id="createFunction" data-options="iconCls:'icon-position'" onclick="javascript:forCreateFunction();">增加资源</div>
	<div class="menu-sep"></div>
	<div id="delete" data-options="iconCls:'icon-delete'" onclick="javascript:forDelete('FUNC_GROUP');">删除</div>
	<div class="menu-sep"></div>
	<div id="reload" data-options="iconCls:'icon-reload'" onclick="javascript:reloadNode();">刷新</div>
</div>

<div id="functionContextMenu" class="easyui-menu" style="width:120px;display:none;">
	<div id="delete" data-options="iconCls:'icon-delete'" onclick="javascript:forDelete('FUNCTION');">删除</div>
	<div class="menu-sep"></div>
	<div id="reload" data-options="iconCls:'icon-reload'" onclick="javascript:reloadNode();">刷新</div>
</div>
