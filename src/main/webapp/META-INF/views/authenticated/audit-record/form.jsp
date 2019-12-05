<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.audit-records.form.title" path="title" />
	<acme:form-textbox code="authenticated.audit-records.form.status" path="status" />
	<acme:form-moment code="authenticated.audit-records.form.moment" path="creationMoment"/>
	<acme:form-textarea code="authenticated.audit-records.form.body" path="body"/>
	
	<acme:form-return code="authenticated.audit-records.form.return" />
</acme:form> 
