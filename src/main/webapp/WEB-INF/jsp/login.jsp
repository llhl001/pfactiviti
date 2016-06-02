<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录系统</title>
<link href="${ctx}/skin/default/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skin/default/css/other.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/component/jQuery/jquery.min.js"></script>
<script src="${ctx}/skin/default/js/cloud.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/script/common/commonUtils.js" ></script>
<script language="javascript">

	
	$(function() {
		$('.loginbox').css({
			'position' : 'absolute',
			'left' : ($(window).width() - 692) / 2
		});
		$(window).resize(function() {
			$('.loginbox').css({
				'position' : 'absolute',
				'left' : ($(window).width() - 692) / 2
			});
		})
	});

	$(document).ready(function() {
		init();
		$("#loginbtn").submit();
		$("#loginbtn").click(function() {
			submitForm();
		});
	});
	var bikky = document.cookie;
	var today = new Date();
	var expiry = new Date(today.getTime() + 7 * 24 * 60 * 60 * 1000);
	function setCookie(name, value) {
		if (value != null && value != "")
			document.cookie = name + "=" + escape(value) + "; expires="
					+ expiry.toGMTString();
		bikky = document.cookie;
	}
	function getCookie(name) {
		var index = bikky.indexOf(name + "=");
		if (index == -1)
			return null;
		index = bikky.indexOf("=", index) + 1;
		var endstr = bikky.indexOf(";", index);
		if (endstr == -1)
			endstr = bikky.length;
		return unescape(bikky.substring(index, endstr));
	}
	function setall() {
		if ($("#remember").is(':checked')) {
			var userId = $("input[id='userId']").val();
			setCookie("userId", userId);
			var password = $("input[id='password']").val();
			setCookie("password", password);
		} else {
			document.cookie = "userId=" + escape(userId) + "; expires="
					+ today.toGMTString();
			document.cookie = "password=" + escape(password) + "; expires="
					+ today.toGMTString();
		}

	}
	function init() {
		var userId = getCookie("userId");
		if (userId != null && userId != "null") {
			$("input[id='userId']").val(userId);
		} else {
			$("#password").val("请输入用户名");
		}
		var password = getCookie("password");
		if (password != null && password != "null") {
			//IE不支持TYPE的变更，所以把控件中的type设成password
			//$("#password").attr('type', 'password');
			$("#password").val(password);
		} else {
			$("#password").val("1");
		}
	}
	function submitForm() {
		var userId = $("input[id='userId']");
		if ($.trim(userId.val()) == "" || userId.val() == "请输入用户名") {
			alert("请输入用户名!");
			userId.focus();
			return;
		}
		var password = $("input[id='password']");
		if ($.trim(password.val()) == "" || password.attr('type') == "text") {
			alert("请输入密码!");
			password.focus();
			return;
		}
		setall();
		var url = "<%=request.getContextPath()%>"+"/loginCheck.do"; 
	//BaseUtils.showWaitMsg();
	$.ajax({
		type: "post",
		async:true,
		global:false,
		url: url,
		dataType:"json",
		data:{"userId":$.trim(userId.val()),"password":$.trim(password.val())},
		success: function(data) {
			//BaseUtils.hideWaitMsg();
			
			/* var ret = jQuery.parseJSON(data); */
			if(data.flag){
				//BaseUtils.showWaitMsg();
				window.location.href="main.do";
			}else{
				alert(data.msg);
			}
		}
	});
}

$(document).keydown(function(e){ 
    var ev = document.all ? window.event : e;
    if(ev.keyCode==13) {
    	submitForm();
    }
});
</script>

</head>

<%-- <body style="background-color:#1c77ac; background-image:url(${ctx}/skin/default/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
 --%>
 <body style="background:url(${ctx}/skin/default/images/light.png) no-repeat center top #0298BD; overflow:hidden;">


    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  

<div class="login_top clearfix">
	<div class="logintop_l float_l">欢迎登录后台管理界面平台</div>
    <!-- <div class="logintop_r float_r"><a href="#">回首页</a><a href="#">帮助</a><a href="#">关于</a></div> -->
</div>

<div class="login_box">
	<div class="login_tit"></div>
    <div class="login_con">
    	<form action="">
    	<table>
        	<tr>
                <td><input id="userId" name="" type="text" class="loginuser" onclick="JavaScript:this.value=''" value="superadmin"/></td>
            </tr>
        	<tr>
                <td><input id="password" name="" type="password" class="loginpwd" onfocus="" value="1"/></td>
                <script type="text/javascript">
                	function pwFocus(){
                		$('#password').value('');
                		//IE不支持TYPE的变更，所以把控件中的type设成password
                		//$('#password').attr('type','password')
                	}
                </script>
            </tr>
        	<tr>
                <td>
                	<input id="loginbtn" name="" type="button" class="btnlogin" value="登录"  />
                    <span style="display:none"><input id="remember" name="" type="checkbox" value="" />记住账号密码一周</span>
                    <!-- <span><a href="#">忘记密码？</a></span> -->
                </td>
            </tr>
        </table>
        </form>
    </div>
</div>
<div class="loginbm">版权所有  山东省亿云信息技术有限公司</div>



</body>

</html>
