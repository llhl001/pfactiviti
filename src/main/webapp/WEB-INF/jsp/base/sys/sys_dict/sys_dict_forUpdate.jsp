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
	function save() {
		var validateRule = [
		        			{id:"TYPE_NAME",message:"字典名称不能为空！"},
		        			{id:"TYPE_CODE",message:"字典编码不能为空！"},
		        			{id:"IS_USE",message:"是否使用不能为空！"}
		               	];
		BaseUtils.showWaitMsg();
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
							parent.$('#queryForm').submit();
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
	<form id="roleForm" name="roleForm" action="${ctx }/sysDict/update.do" method="post">
		<input type="hidden" name="DIC_TYPE_ID" value="${record.DIC_TYPE_ID }" />
		<table class="form_table">
            <tr>
                <th width="15%"><font class="redfont">*</font>&nbsp;字典名称:</th>
                <td width="35%"><input type="text" class="forminput" style="width:89%" id="TYPE_NAME" name="TYPE_NAME" value="${record.TYPE_NAME}"/></td>
                <th width="15%"><font class="redfont">*</font>&nbsp;字典编码:</th>
                <td width="35%"><input type="text" class="forminput" style="width:89%" id="TYPE_CODE" name="TYPE_CODE" value="${record.TYPE_CODE}"/></td>
            </tr>
            <tr>
                <th><font class="redfont">*</font>&nbsp;是否使用:</th>
                <td>
                	<select name="IS_USE" id="IS_USE" class="formselect">
						<option value="" >请选择...      </option>
						<option value='0' ${record.IS_USE=='0'?'selected':''}>否</option>
						<option value='1' ${record.IS_USE=='1'?'selected':''}>是</option>
                	</select>
                </td>
                <th></th>
                <td></td>
            </tr>
			<tr>
            	<th></th>
                <td colspan="3"><input type="button" onclick="save()" class="formbtn1" value="确认保存" /></td>
            </tr>
		</table>
	</form>
	</div>
</div>
</body>
</html>