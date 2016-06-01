<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ attribute name="typeCode" type="java.lang.String" required="true" description="字典code"%>
<%@ attribute name="defaultValue" type="java.lang.String" description="默认选中"%>
<%@ attribute name="style" type="java.lang.String" description="默认选中"%>
<%@ attribute name="cls" type="java.lang.String" description="默认选中"%>
<%@ attribute name="name" type="java.lang.String" description="默认选中"%>
<select style="${style}" class="${cls}" name="${name}" id="${name}" >
	<option value="" >请选择...      </option>
	<c:if test="${not empty typeCode}">
		<c:forEach items="${fns:getDictList(typeCode)}" var='dict'>
			<option value='${dict.VALUE}' ${defaultValue==dict.VALUE?'selected':''}>${dict.TEXT}</option>
		</c:forEach>
	</c:if>
</select>