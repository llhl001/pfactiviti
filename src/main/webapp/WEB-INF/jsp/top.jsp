<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>左侧界面</title>
<%@ include file="common/common_header.jsp"%>
<script type="text/javascript" src="${ctx}/component/jQuery/jquery.cxscroll.min.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".clearfix li a").click(function(){
		$(".clearfix li a.on").removeClass("on")
		$(this).addClass("on");
	})	
	
	$('#logout').click(function(){
		window.top.location.replace('${ctx}/login.do');
		window.parent.location='${ctx}/login.do'
	});
	
	$('#changePassword').click(function(){
		window.parent.rightFrame.defaultDialog("修改密码", "${ctx}/acOperator/forUpdatePassword.do", "400px", "300px");
	});
	
	$("#pic_list_1").cxScroll();
})	
</script>
</head>
<body>
	<div class="header_box">
		<div class="logo float_l">
			<a href="${ctx }/main.do" target="_parent"><img src="${ctx}/skin/default/images/logo.png" width="200" height="50" /></a>
			<%-- <a href="${ctx }/main.do" target="_parent"><img src="${ctx}/skin/default/images/logo.png" title="系统首页" /></a> --%>
		</div>
		<div id="pic_list_1" class="head_ctrl">
		<div class="box menu_box">
    	<ul class="list clearfix">
    			<li>
    				<a class="on" href="${ctx }/main.do" target="_parent">
						<img src="${ctx}/skin/default/images/icon01.png" width="45" height="45"  title="平台首页" />
						<p>平台首页</p>
					</a>
				</li>
		      	<c:forEach items="${topMenuList}" var="k">
		      		<li>
						<a class="router" href="${ctx }/left.do?pId=${k.MENU_ID}&pName=${k.MENU_LABEL}" target="leftFrame">
							<c:if test="${k.MENU_CSS!=null&&k.MENU_CSS!=''}">
							<img src="${ctx}/skin/default/images/${k.MENU_CSS}" width="45" height="45"  title="${k.MENU_LABEL}" />
							</c:if>
							<p>${k.MENU_LABEL}</p>
						</a>
					</li>
				 </c:forEach>
			</ul>
    </div>
		</div>
		<div class="head_r float_r">
    	<p><!-- <a href="">帮助</a> | <a href="">关于</a> |  --><a href="#" id="changePassword">修改密码</a> | <a href="#" id="logout">退出</a></p>
        <div class="head_radmin">
        	<div>${loginOperator.OPERATOR_NAME }<!-- <a href="">消息</a><i>5</i> --></div>
        </div>
    </div>
	</div>
</body>
</html>