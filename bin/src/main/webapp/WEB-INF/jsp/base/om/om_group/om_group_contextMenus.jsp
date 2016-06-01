<%@ page pageEncoding="UTF-8"%>
<div id="root" class="easyui-menu" style="width:120px;display:none;">
	<div id="createMenu" data-options="iconCls:'icon-plus'" onclick="javascript:forCreateMenu('ROOT');">增加工作组</div>
	<div class="menu-sep"></div>
	<div id="reload" data-options="iconCls:'icon-reload'" onclick="javascript:reloadNode();">刷新</div>
</div>
<!-- <div id="parentGroup" class="easyui-menu" style="width:120px;display:none;">
	<div id="createMenu" data-options="iconCls:'icon-plus'" onclick="javascript:forCreateMenu('parentGroup');">增加工作组</div>
	<div class="menu-sep"></div>
	<div id="reload" data-options="iconCls:'icon-reload'" onclick="javascript:reloadNode();">刷新</div>
</div> -->
<div id="group" class="easyui-menu" style="width:120px;display:none;">
	<div id="createMenu" data-options="iconCls:'icon-plus'" onclick="javascript:forCreateMenu('group');">增加工作组</div>
	<div class="menu-sep"></div>
	<div id="createMenu" data-options="iconCls:'icon-plus'" onclick="javascript:forAddEmp();">增加人员</div>
	<div class="menu-sep"></div>
	<div id="delete" data-options="iconCls:'icon-delete'" onclick="javascript:forDelete('leafGroup');">删除</div>
	<div class="menu-sep"></div>
	<div id="reload" data-options="iconCls:'icon-reload'" onclick="javascript:reloadNode();">刷新</div>
</div>

<div id="leaf" class="easyui-menu" style="width:120px;display:none;">
	<div id="delete" data-options="iconCls:'icon-delete'" onclick="javascript:forDelete('LEAF');">删除</div>
	<div class="menu-sep"></div>
	<div id="reload" data-options="iconCls:'icon-reload'" onclick="javascript:reloadNode();">刷新</div>
</div>

