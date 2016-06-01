<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
<title>人员角色</title>
<%@ include file="../../../common/common_header.jsp"%>
 <script type="text/javascript">
$(function(){	
	var a=$(window).height();
	//a-=96;(a=a-96)
	a-=96;
	$(".center_right").css("height",a);
	//移到右边
	$('#add').click(function(){
		//先判断是否有选中
		if(!$("#select1 option").is(":selected")){			
			alert("请选择需要移动的选项");
		}
		//获取选中的选项，删除并追加给对方
		else{
			$('#select1 option:selected').appendTo('#select2');
		}	
	});
	
	//移到左边
	$('#remove').click(function(){
		//先判断是否有选中
		if(!$("#select2 option").is(":selected")){			
			alert("请选择需要移动的选项");
		}
		else{
			$('#select2 option:selected').appendTo('#select1');
		}
	});
	
	//全部移到右边
	$('#add_all').click(function(){
		//获取全部的选项,删除并追加给对方
		$('#select1 option').appendTo('#select2');
	});
	
	//全部移到左边
	$('#remove_all').click(function(){
		$('#select2 option').appendTo('#select1');
	});
	
	//双击选项
	$('#select1').dblclick(function(){ //绑定双击事件
		//获取全部的选项,删除并追加给对方
		$("option:selected",this).appendTo('#select2'); //追加给对方
	});
	
	//双击选项
	$('#select2').dblclick(function(){
		$("option:selected",this).appendTo('#select1');
	});
	//点击保存
	$('#save').click(function(){
		$("#select2 option").attr("selected","selected");
		/* if ($("#select2 option").length<=0) {
			alert("已授权角色不能为空！");
			return;
		} */
	    $.ajax({
	        url: "${ctx }/acOperatorRole/batchInsertByArray.do",
	        type:"post",
	        data:  $("#frm").serialize(),
	        success: function(data) {
	        	alert("添加完成!");
	        }
	    });
	});
	//点击检索
/* 	$('#search').click(function(){
		$("#select2 option").attr("selected","selected");
	    $.ajax({
	        url: "${ctx }/ompartyrole/searchOrgRole.do",
	        type:"post",
	        data:  $("#frm").serialize(),
	        success: function(data) {
	        		//var jsonObj=$.parseJSON(data[i]);在control里面已经是JSON了。就不需要转换了
 	        		var	optionstring="";
        			var jsonObj=data.acRoleSelect;
        			for(var i=0;i<jsonObj.length;i++){
        				optionstring+="<option value=\"" + jsonObj[i].value + "\">" + jsonObj[i].name + "</option>";
        			}
	        		$("#select1").empty();
	        		$("#select1").html(optionstring);
	        }
	    });
	}); */
});
</script>
</head>
<body>

<div class="right_con">
<form id="frm" name="frm" action="" method="post">
<table class="table_juese">
 		<tr >
 			<th width="260" align="center">未授权角色</th>
 			<th width="64" align="center"></th>
 			<th width="260" align="center">已授权角色</th>
 		</tr>
 		<tr class="selectbox">
			<td class="select-bar">
			    <select multiple="multiple" id="select1" name="selectleft">
			    <c:forEach var="s" items="${roleList}">
			        <option value="${s.ROLE_ID }">${s.ROLE_NAME }</option>
			    </c:forEach>
			    </select>
			</td>
			<td class="btn-bar">
		    <p><span id="add"><input type="button" class="btn_juese" value=">" title="移动选择项到右侧"/></span></p>
		    <p><span id="add_all"><input type="button" class="btn_juese" value=">>" title="全部移到右侧"/></span></p>
		    <p><span id="remove"><input type="button" class="btn_juese" value="<" title="移动选择项到左侧"/></span></p>
		    <p><span id="remove_all"><input type="button" class="btn_juese" value="<<" title="全部移到左侧"/></span></p>
			</td>
			<td class="select-bar">
		   	 <select multiple="multiple" id="select2" name="selectright">
			    <c:forEach var="s" items="${operatorRoleList}">
			        <option value="${s.ROLE_ID }">${s.ROLE_NAME }</option>
			    </c:forEach>
		   	 </select>
			</td>	
	</tr>
	<tr>
		<td colspan="3">
		<input type="button" value="保   存" id="save" class="formbtn1"/>
		<input type="hidden" name="operatorId" value="${operatorId }"/>
		</td>
 	</tr>
 	</table>
</form>
</div>
</body>
</html>