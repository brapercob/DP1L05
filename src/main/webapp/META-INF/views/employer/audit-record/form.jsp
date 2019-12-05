<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="employer.audit-record.form.title" path="title" />
	<acme:form-textbox code="employer.audit-record.form.status" path="status" />
	<acme:form-moment code="employer.audit-record.form.moment" path="creationMoment"/>
	<acme:form-textarea code="employer.audit-record.form.body" path="body"/>
	
	<acme:form-return code="employer.audit-record.form.return" />
</acme:form> 
