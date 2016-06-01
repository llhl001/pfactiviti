<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../../common/common_header.jsp"%>
<title>字典修改</title>
<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function save(id) {
		var validateRule = [
		        			{id:"TEXT",message:"字典项不能为空！"},
		        			{id:"VALUE",message:"字典值不能为空！"},
		        			{id:"IS_USE",message:"是否使用不能为空！"}
		               	];
		BaseUtils.showWaitMsg();
		var typeId = $("#TYPE_CODE").val();
		if(!formValidationFun(validateRule)) return;
		$.ajax({
					url : $("#roleForm").attr("action"),
					type : "post",
					data : $("#roleForm").serialize(),
					dataType : "json",
					success : function(data) {
						BaseUtils.hideWaitMsg();
						alert(data.msg);
						if (data.flag) {
							var url = '${ctx}/sysDict/rightQueryPage.do?id='+id+"&typeId="+typeId;
							parent.parent.document.getElementById('dictRightFrame').src=url;
							parent.layer.close(index);
						}
					}
				});
	}

</script>
</head>
<body>
<div class="right_con">
	<div class="form_box">
	<form id="roleForm" name="roleForm" action="${ctx }/sysDict/rightUpdate.do" method="post">
		<input type="hidden" id="TYPE_CODE" name="TYPE_ID" value="${record.TYPE_ID }" />
		<input type="hidden" name="DIC_ID" value="${record.DIC_ID }" />
		<table class="form_table" style="width:100%;">
            <tr>
                <th width="20%"><font class="redfont">*</font>&nbsp;字典项:</th>
                <td width="30%"><input type="text" class="forminput" id="TEXT" name="TEXT" value="${record.TEXT}"/></td>
                <th width="18%"><font class="redfont">*</font>&nbsp;字典值:</th>
                <td width="32%"><input type="text" class="forminput" id="VALUE" name="VALUE" value="${record.VALUE}"/></td>
            </tr>
            <tr>
                <th><font class="redfont">*</font>是否使用:</th>
                <td>
                	<select name="IS_USE" id="IS_USE" class="formselect">
						<option value="" >请选择...      </option>
						<option value='0' ${record.IS_USE=='0'?'selected':''}>否</option>
						<option value='1' ${record.IS_USE=='1'?'selected':''}>是</option>
                	</select>
                </td>
                <%-- <th><font class="redfont">*</font>&nbsp;父项:</th>
                <td><input type="text" class="forminput" style="width:89%" id="PID" name="PID" value="${record.PID}"/></td> --%>
            </tr>
			<tr>
            	<th></th>
                <td colspan="3"><input type="button" onclick="save('${record.TYPE_ID}')" class="formbtn1" value="确认保存" /></td>
            </tr>
		</table>
	</form>
	</div>
</div>
</body>
</html>