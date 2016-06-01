<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>示例页面</title>
<%@ include file="../../../common/common_header.jsp"%>
<%@ include file="../../../common/printPage_header.jsp"%>
<script type="text/javascript">  
  /* $(document).ready(function() {
    $(".btnPrint").printPage();
  }); */
  function  a(){
	  $("#ddd").jqprint({
		     debug: false, //如果是true则可以显示iframe查看效果（iframe默认高和宽都很小，可以再源码中调大），默认是false
		     importCSS: true, //true表示引进原来的页面的css，默认是true。（如果是true，先会找$("link[media=print]")，若没有会去找$("link")中的css文件）
		     printContainer: true, //表示如果原来选择的对象必须被纳入打印（注意：设置为false可能会打破你的CSS规则）。
		     operaSupport: true//表示如果插件也必须支持歌opera浏览器，在这种情况下，它提供了建立一个临时的打印选项卡。默认是true
		});
	  }
  </script>
</head>
<body>

<div id="ddd">
<table>
<tr>
<td>test</td>
<td>test</td>
<td>test</td>
<td>test</td>
<td>test</td>
</tr>
<tr>
<td>test</td>
<td>test</td>
<td>test</td>
<td>test</td>
<td>test</td>
</tr>
<tr>
<td>test</td>
<td>test</td>
<td>test</td>
<td>test</td>
<td>test</td>
</tr>
<tr>
<td>test</td>
<td>test</td>
<td>test</td>
<td>test</td>
<td>test</td>
</tr>
<tr>
<td>test</td>
<td>test</td>
<td>test</td>
<td>test</td>
<td>test</td>
</tr>

</table>

</div>

<input type="button" onclick=" a()" value="打印"/>
</body>
<%-- <body>
   <a class="btnPrint" href="${ctx }/acRole/forQueryPage.do">打印一</a>
</body> --%>
</html>