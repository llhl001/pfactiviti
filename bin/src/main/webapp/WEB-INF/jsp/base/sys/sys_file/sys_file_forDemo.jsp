<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>上传文件示例</title>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ include file="../../../common/common_header.jsp"%>
<%@ include file="../../../common/uploadify_header.jsp"%>
<script type="text/javascript">
//下载地址示例
//http://localhost:8080/filePath/temp/platform/文件名.txt
$(function() {
	initDefaultUploadButton('uploadFile',120,30);
});
function initDefaultUploadButton(buttonId,width,height,buttonText){
	buttonText
    $("#"+buttonId).uploadify({
    	'fileObjName' : 'multipartFiles',
        width         : width,
        height        : height,
        swf           : '${ctx}/component//uploadify/uploadify.swf',
        uploader      : '${ctx}/sysFile/batchUpload.do',
        buttonClass   :'formbtn1',
        buttonText	  :buttonText
    });
}
<%-- $(function() {
	$("#uploadFile").uploadify({
	    'auto': true,
		'buttonText':'选择文件',
		'buttonImg':'${ctx}/component/uploadify/img/btnUploadImg.jpg',
		'fileSizeLimit':'10MB',
		'height':30,
		'width':60,
		'method':'post',
		'multi':true,
		'queueID':'fileQueue',
		'queueSizeLimit':5,//队列最多显示的任务数量
		'swf' : '${ctx}/component/uploadify/uploadify.swf',
		'uploader' : '${ctx}/xtdoc/uploadFile.do;jsessionid=<%=request.getSession().getId()%>',
    	'uploadLimit':100,//最大上传文件数量
    	'onUploadSuccess':function(file,data,response){
    		var jsonResult = eval('('+data+')');
    		var fileId = jsonResult.id;
    		var filePath = "${ctx}"+"/uploadFiles/"+jsonResult.filepath+"/"+jsonResult.filerename;
    		var fileName = file.name;
    		
    		var htmlCode = "<li id='"+fileId+"'>";
        	htmlCode += "<a style='margin-right:8px;' href='"+filePath+"'>"+fileName+"</a>";
        	htmlCode += "<a style='color:#E6162D' href='javascript:deleteFile(\""+fileId+"\");'>删除</a>";
        	htmlCode += "</li>";
        	$("#annexListDIV ul").append(htmlCode);
        	
			$("#proofMaterial").val($("#proofMaterial").val()+","+fileId+",");
			$("#proofMaterial").val(stringFormat($("#proofMaterial").val()));
    		
			/*$("#fileInfo").append("<tr id='"+jsonResult.id+"'><td>"+file.name+"</td> <td>"+jsonResult.uploadTime+
    				"</td> <td><a class='red' href=\"javascript:deleteTR('" +jsonResult.id+"','"+jsonResult.filepath+"','"+jsonResult.filerename
    						+"');\">删除</a></td><input type='hidden' name='docid' value='" +jsonResult.id+"'></tr>"); */
    	},
    	'onComplete': function(event, queueID, fileObj, response, data) {//当单个文件上传完成后触发
        },
    	'onUploadError':function(file, errorCode, errorMsg, errorString){
    		alert(file.name+ "  " + errorMsg+"  " +errorMsg+"  "+errorString);
    	},
    	'onCancel':function(file){
    	}
     });
}); --%>
</script>

</head>
<body>
<div class="right_con">
	<div class="form_box">
	<form id="menuForm" name="menuForm" action="${ctx }/omGroup/update.do" method="post">
		<table class="form_table">
            <tr>
									<td align="right">文件：</td>
									<td colspan="3">
										<div class="clearfix">
									    	<div style="width:60px;float:left;padding-top:4px;"><input type="file" name="file" id="uploadFile"/></div>
										</div>
								    	<div id="annexListDIV" class="annexListDIV">
								    		<ul class="clearfix">
									    		<%-- <c:forEach var="doc" items="${record.docList}" varStatus="status">
									    			<li id='${doc.docid }'>
									    				<a style="margin-right:8px;" href="${ctx }/${doc.doclocation}">${doc.docname}</a>
									    				<a style="color:#E6162D" href="javascript:deleteFile('${doc.docid}');">删除</a>
									    			</li>
									    		</c:forEach> --%>
								    		</ul>
								    	</div>
<%-- 										<input id="proofMaterial" name="proofMaterial" value="${record.proofMaterial}" class="asinput" type="hidden" />
 --%>									</td>
								</tr>
		</table>
	</form>
	</div>
</div>
</body>
</html>
