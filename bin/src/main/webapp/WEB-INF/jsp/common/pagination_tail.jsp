<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
$(function(){
	//pageNow('1');
	loadOddCss()
});
function loadOddCss(){
	$("tr:odd").addClass("odd");
	$("tr").mouseover(function(){
		$(this).addClass("color1");
	}).mouseout(function(){
		$(this).removeClass("color1");
	});
}
function pageNow(pageNow){
	 var fy=$("#queryForm").serialize(); //对表彰数据进行序列化
	 var f=$("#queryForm").attr("action");//获取表单action的属性值
	 var pCount = parseInt("${pageView.pageCount}");
	if(pageNow < 1){
		alert(" 已 经 是 第 一 页 啦  ！");
		return false ;
	}else if(pCount < pageNow){
		alert(" 没 有 下 一 页 啦 ！");
		return false ;
	}else{
		//window.location.href=f+"?pageNow="+pageNow+"&"+fy;
		var url="";
		if(f.indexOf('?')>0){
			url = f+"&pageNow="+pageNow;
		} else {
			url = f+"?pageNow="+pageNow;
		}
		document.forms[0].action=url;
		document.forms[0].submit();
		return false;
	};
}
</script>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dsdgy">
	<tr>
		<td align="left">&nbsp;&nbsp;总记录数:${pageView.rowCount}条
			|每页显示:${pageView.pageSize}条 | 总页数:${pageView.pageCount}页</td>
		<td align="right">
			<p class="pages_p">
				<a href="javascript:void(0);" onclick="return pageNow('1');">首页</a>
				<a href="javascript:void(0);" onclick="return pageNow('${pageView.pageNow - 1}');">上一页</a>
				<c:forEach begin="${pageView.pageindex.startindex}"
					end="${pageView.pageindex.endindex}" var="key">
					<c:if test="${pageView.pageNow==key}">
						&nbsp;<span class="current" style="color: red;font-size: 20px;">
							${key}</span>
					</c:if>
					<c:if test="${pageView.pageNow!=key}">
						&nbsp;<a href="javascript:void(0);" onclick="return pageNow('${key}');">${key}</a>
					</c:if>
				</c:forEach>
				<a href="javascript:void(0);" onclick="return pageNow('${pageView.pageNow + 1}');">下一页</a>
				<a href="javascript:void(0);" onclick="return pageNow('${pageView.pageCount}');">尾页</a>
			</p>
		</td>
	</tr>
</table>