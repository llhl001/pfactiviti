<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>无标题文档</title>
<meta http-equiv="X-UA-Compatible" content="IE=8"/>
<%@ include file="../../../common/common_header.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$("#save").click(function(){
		
		var validateRule =
			[
			 {id:"oldPassword",message:"旧密码不能为空！"},
			 {id:"newPassword",message:"新密码不能为空！"},
			 {id:"repeatPassword",message:"重复密码不能为空！"}
			];
		if(!formValidationFun(validateRule)) return;
		if($('#newPassword').val()!=$('#repeatPassword').val()){
			BaseUtils.hideWaitMsg();
			alert("重复密码错误，请重新输入");
			$('#repeatPassword').val('');
			$('#repeatPassword').focus();
			return;
		}
	    $.ajax({
	        url: "${ctx }/acOperator/updatePassword.do",
	        type:"post",
	        dataType:"json",
	        data:  $("#changePassword").serialize(),
	        success: function(data) {
	        	BaseUtils.hideWaitMsg();
	        	alert(data.msg);
	        	if(data.flag){
	        		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		    		parent.layer.close(index);
	        	}
	        	
	        }
	    });
	});
	$("#reseta").click(function(){
		$("#resetbut").click();
	});
});
</script>
</head>
<body>
<form action="${ctx}/acOperator/updatePassword.do" method="post" id="changePassword">
<div class="right_con">
	<div class="form_box">
		<table class="form_table">
              <tr>
                <td align="right">原密码：</td>
                <td ><input type="password" class="forminput" id="oldPassword" name="oldPassword"/></td>
              </tr>
              <tr>
                <td align="right">新密码：</td>
                <td ><input type="password" class="forminput" id="newPassword" name="newPassword"/></td>
              </tr>
              <tr>
                <td align="right">重复密码：</td>
                <td ><input type="password" class="forminput" id="repeatPassword" name="repeatPassword"/></td>
              </tr>
	              <tr>
	                <td colspan="2" align="right">
	                	<input id="save" type="button" class="formbtn1" value="保存" />
<!-- 	                	<input id="reseta" type="button" class="formbtn1" value="重  置" />
 -->	                </td>
	              </tr>
	        </table>
	</div>
</div>
</form>
</body>
</html>