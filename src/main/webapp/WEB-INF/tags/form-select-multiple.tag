
<%@tag language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
 
<%@attribute name="path" required="true" type="java.lang.String"%>
<%@attribute name="code" required="true" type="java.lang.String"%>
<%@attribute name="readonly" required="false" type="java.lang.Boolean"%>
<%@attribute name="multiple" required="false" type="java.lang.String"%>

<jstl:if test="${readonly == null}">
	<jstl:set var="readonly" value="false"/>
</jstl:if>


<div class="form-group">
	<label for="${path}">
		<acme:message code="${code}"/>
	</label>
	<select ${multiple} id="${path}" name="${path}" class="selectpicker show-tick">
  		<jsp:doBody/>
	</select>		
	<acme:form-errors path="${path}"/>			
</div>
