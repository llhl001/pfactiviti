<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>左侧界面</title>
<%@ include file="common/common_header.jsp"%>

<script type="text/javascript">
$(function(){
	var a=$(window).height();
	//a-=96;(a=a-96)
	$(".center_left").css("height",a);
	$(".ul_left li div a").each(function(i) {
		$(this).click(function(){
			$(".ul_left li div a").removeClass("on");
			$(this).addClass("on");
			var i = $(this).index();
			g = "center_right" + i +".html";
			$("#center_right",parent.document.body).attr("src",g);
		})
    });
	
	//导航切换
	$(".ul_left .a_left2").click(function(){
		var $parent = $(this).parent();
		$(".ul_left>li.on").not($parent).removeClass("on open").find('.div_left').hide();
		
		$parent.addClass("on");
		if(!!$(this).next('.div_left').size()){
			if($parent.hasClass("open")){
				$parent.removeClass("open").find('.div_left').hide();
			}else{
				$parent.addClass("open").find('.div_left').show();	
			}
		}
	});
	
	// 三级菜单点击
	$('.div_left a').click(function(e) {
		$(".div_left a.on").removeClass("on")
		$(this).addClass("on");
	});
	
	$('.a_left1').click(function(){
		var $ul = $(this).next('ul');
		$('.left_menu_box').find('.ul_left').slideUp();
		if($ul.is(':visible')){
			$(this).next('.ul_left').slideUp();
		}else{
			$(this).next('.ul_left').slideDown();
		}
	});
})	
</script>

</head>
<body>
<div class="center_left">
	<div class="left_tit clearfix"><i></i>${pName}</div>
    <c:forEach items="${leftMenuList}" var="menuRec" varStatus="status">
    	<c:if test="${menuRec.MENU_LEVEL eq '2'}">
    		<div id="${menuRec.MENU_ID}" class="left_menu_box">
    			<a href="javascript:void(0);" class="a_left1 clearfix on"><i></i>${menuRec.MENU_LABEL}</a>
    			<ul class="ul_left" style="display: block;">
    			<c:forEach items="${leftMenuList}" var="menuRec1" varStatus="status">
    				<c:if test="${menuRec1.PARENT_ID eq menuRec.MENU_ID}">
    					
    					<c:if test="${menuRec1.IS_LEAF eq '1'}">
    						<li>
				            	<a class="a_left2 clearfix" href="${ctx}/${menuRec1.MENU_ACTION}${menuRec1.PARAMETER==''?'':menuRec1.PARAMETER}" target="rightFrame" >
				            		<i></i>${menuRec1.MENU_LABEL}
				            	</a>
				            </li>
    					</c:if>
    					<c:if test="${menuRec1.IS_LEAF eq '0'}">
    						<li>
            					<a class="a_left2 clearfix" href="javascript:void(0);"><i></i>${menuRec1.MENU_LABEL}</a>
            					<div class="div_left" style="display: none;">
            					<c:forEach items="${leftMenuList}" var="menuRec2" varStatus="status">
					                	<a href="${ctx}/${menuRec2.MENU_ACTION}${menuRec2.PARAMETER==''?'':menuRec2.PARAMETER}" target="rightFrame">${menuRec2.MENU_LABEL}</a>
            							<c:remove var="menuRec2"/>
            					</c:forEach>
            					</div>
            					
            				</li>
    					</c:if>
    					
    				</c:if>
    				<c:remove var="menuRec1"/>
    			</c:forEach>
    			</ul>
    		</div>
    	</c:if>
    	<c:remove var="menuRec"/>
    </c:forEach>
    <%-- 
    <div class="left_menu_box">
    	<a href="javascript:void(0);" class="a_left1 clearfix on"><i></i>组织管理</a>
    	<ul class="ul_left" style="display: block;">
            <li>
            	<a class="a_left2 clearfix" href="${ctx}/omOrganization/forMain.do" target="rightFrame" ><i></i>机构人员</a>
            </li>
            <li>
            	<a class="a_left2 clearfix" href="${ctx}/omGroup/forMain.do" target="rightFrame"><i></i>工作组</a>
            </li>
         </ul>
    </div>
    
    <div class="left_menu_box">
    	<a href="javascript:void(0);" class="a_left1 clearfix on"><i></i>权限管理</a>
    	<ul class="ul_left" style="display: block;">
            <li>
            	<a class="a_left2 clearfix" href="${ctx}/acApplication/forMain.do" target="rightFrame" ><i></i>应用功能管理</a>
            </li>
            <li>
            	<a class="a_left2 clearfix" href="${ctx}/acMenu/forMain.do" target="rightFrame" ><i></i>菜单管理</a>
            </li>
            <li>
            	<a class="a_left2 clearfix" href="${ctx}/acRole/forQueryPage.do" target="rightFrame" ><i></i>角色管理</a>
            </li>
         </ul>
    </div>
    
    <div class="left_menu_box">
        <a href="javascript:void(0);" class="a_left1 clearfix on"><i></i>待办事项</a>
        <ul class="ul_left">
            <li>
            	<a class="a_left2 clearfix" href="javascript:void(0);"><i></i>2级菜单</a>
                <div class="div_left" style="display: none;">
                	<a href="javascript:void(0);">3级菜单</a>
                    <a href="javascript:void(0);">3级菜单</a>
                	<a href="javascript:void(0);">3级菜单</a>
                    <a href="javascript:void(0);">3级菜单</a>
                    <a href="javascript:void(0);">3级菜单</a>
                </div>
            </li>
            <li>
            	<a class="a_left2 clearfix" href="javascript:void(0);"><i></i>2级菜单</a>
                <div class="div_left" style="display: none;">
                	<a href="javascript:void(0);">3级菜单</a>
                    <a href="javascript:void(0);">3级菜单</a>
                	<a href="javascript:void(0);">3级菜单</a>
                    <a href="javascript:void(0);">3级菜单</a>
                    <a href="javascript:void(0);">3级菜单</a>
                </div>
            </li>
            <li>
            	<a class="a_left2 clearfix" href="javascript:void(0);"><i></i>2级菜单</a>
                <div class="div_left" style="display: none;">
                	<a href="javascript:void(0);">3级菜单</a>
                    <a href="javascript:void(0);">3级菜单</a>
                	<a href="javascript:void(0);">3级菜单</a>
                    <a href="javascript:void(0);">3级菜单</a>
                    <a href="javascript:void(0);">3级菜单</a>
                </div>
            </li>
        </ul>
    </div> --%>
</div>   
</body>
</html>