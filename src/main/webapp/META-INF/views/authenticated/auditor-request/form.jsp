<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.auditorRequest.list.label.firm" path="firm" />
	<acme:form-textbox code="authenticated.auditorRequest.list.label.statement" path="statement"/>
	
	<acme:form-return code="authenticated.auditorRequest.form.button.return" />
	<acme:form-submit code="authenticated.auditorRequest.form.button.create" action="/authenticated/auditor-request/create"/>
</acme:form>