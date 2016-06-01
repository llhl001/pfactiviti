<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="s" uri="http://www.springframework.org/tags" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %><%-- ${fns:xxxx}方法标签 --%>
<%@ taglib prefix="bgt" uri="/WEB-INF/tlds/bgt.tld" %><%-- 后台Java控制标签 --%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %><%-- JSP控制标签 --%>

<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<%-- <meta charset="utf-8"> --%>
<!-- Bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

<link href="${ctx}/skin/default/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skin/default/css/other.css" rel="stylesheet" type="text/css" />
<%-- <link href="${ctx}/skin/default/css/select.css" rel="stylesheet" type="text/css" /> --%>
<%-- <link href="${ctx}/skin/default/css/css.css" rel="stylesheet" type="text/css" /> --%>
<%-- <link href="${ctx}/skin/default/css/reset.css" rel="stylesheet" type="text/css" /> --%>

<!-- Bootstrap -->
<link href="${ctx}/component/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${ctx}/component/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/component/jQuery/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/skin/default/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${ctx}/skin/default/js/select-ui.min.js"></script>
<script type="text/javascript" src="${ctx}/skin/default/editor/kindeditor.js"></script>
<!-- 页面dialog\Tip工具 -->
<script type="text/javascript" src="${ctx}/component/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/component/layer/extend/layer.ext.js"></script>

<script type="text/javascript" src="${ctx}/component/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/script/common/commonUtils.js" ></script>
<script type="text/javascript" src="${ctx}/script/common/formValidation.js" ></script>

<!-- Bootstrap -->
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="${ctx}/component/bootstrap/js/html5shiv.min.js"></script>
      <script src="${ctx}/component/bootstrap/js/respond.min.js"></script>
<![endif]-->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${ctx}/component/bootstrap/js/bootstrap.min.js"></script>
