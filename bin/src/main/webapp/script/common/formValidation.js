//验证结果提示信息
function _return(message,result,id){
	if(result){
		$("#"+id).css("border", "1px solid #CCC");
		//$("#messageSpan").html("");
	}else{
		alert(message);
		$("#"+id).css("border", "1px solid #FE8E09");
		$("#"+id).focus();
		//$("#messageSpan").html(message);
	}
}

//正则表达式验证
function regexValidate(contentType,content,template){
	var formatStr = "";
	if(contentType == "number"){
		//数字
		formatStr = /^[0-9]+$/;
	}else if(contentType == "money"){
		//两位小数的数字
		formatStr = /^[0-9]+(\.[0-9]{1,2})?$/;
	}else if(contentType == "IP"){
		//验证IP地址格式
		 formatStr= /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
	}else if(contentType == "email"){
		//验证邮箱
		formatStr = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]/;
	}else if(contentType == "postalCode"){
		//验证邮编(只能为六位)
		formatStr=/^\d{6}$/;
	}else if(contentType == "IDnumber"){
		//验证身份证(15位或18位，如果是15位，必需全是数字。如果是18位，最后一位可以是数字或字母Xx，其余必需是数字)
		formatStr=/^(\d{15}$|^\d{18}$|^\d{17}[Xx])$/;
	}else if(contentType == "mobilePhone"){
		//移动电话验证(长度11位及号段)
		formatStr=/^[1]([3][0-9]{1}|59|58|88|89|56)[0-9]{8}$/;
	}else if(contentType == "officePhone"){
		//座机验证
		formatStr=/d{3}-d{8}|d{4}-d{7}|d{4}-d{8}/;
	}else if(contentType == "lessThanThisTime" || contentType == "greaterThanThisTime"){
		//时间范围验证
		var currentDate = template != null && template != "" ? new Date(template.replace(/-/g,"/").replace(/年/g,"/").replace(/月/g,"/").replace(/日/g,"/")) : new Date();//如果template不为空，获取template指定时间;否则获取当前时间
		var currentDateStr = currentDate.getFullYear()+"/"+(currentDate.getMonth()+1)+"/"+currentDate.getDate();
		content = content.replace(/-/g,"/").replace(/年/g,"/").replace(/月/g,"/").replace(/日/g,"/");
		if(contentType == "lessThanThisTime"){
			//小于当前时间
			return new Date(content) < new Date(currentDateStr);
		}else if(contentType == "greaterThanThisTime"){
			//大于当前时间
			return new Date(content) > new Date(currentDateStr);
		}
	}
	return (formatStr.test(content) && (content.length > 0));
}
//获取单项格式验证结果
function formValidationFun(validateRule){
	BaseUtils.showWaitMsg();
	var jsonObj = eval(validateRule);
	var id = "";
	var message = "";
	var allowNull = "";
	var template = "";
	var result = false;
	for(var i=0;i<jsonObj.length;i++){
		id = jsonObj[i].id;//表单项Id
		message = jsonObj[i].message;//格式错误时的提示信息
		contentType = jsonObj[i].contentType;//内容类型,决定验证格式(默认普通字符串varchar)
		(contentType == null || contentType == "") ? contentType = "varchar" : contentType == contentType;//
		allowNull = jsonObj[i].allowNull;//是否允许为空(默认不允许为空)
		(allowNull == null || allowNull == "") ? allowNull = "false" : allowNull == allowNull;
		template = jsonObj[i].template;//使用指定格式来验证，(目前仅用于时间范围验证，默认为当前时间,格式:"2015/09/10 00:00:00"或"2015-09-10 00:00:00")
		var str = $("#"+id).val();
		if(str == "" || str == null){
			if(allowNull == "true"){
				result = true;
			}else if(allowNull == "false"){
				result = false;
			}
		}else{
			if(contentType == "varchar"){
				result = true;
			}else{
				result = regexValidate(contentType,str,template);
			}
		}
		_return(message,result,id);
		if(!result){
			BaseUtils.hideWaitMsg();
			return result;
		}
	}
	if(window.confirm("您确认要保存吗？")){
		return true;
	}else{
		BaseUtils.hideWaitMsg();
		return false;
	}
}