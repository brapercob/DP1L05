<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.auditorRequest.list.label.firm" path="firm" />
	<acme:form-textbox code="administrator.auditorRequest.list.label.statement" path="statement"/>
	
		<acme:form-submit test="${command == 'show'}"
		code="administrator.auditorRequest.form.button.update"
		action="/administrator/auditor-request/update"/>
	<acme:form-submit test="${command == 'show'}"
		code="administrator.auditorRequest.form.button.delete"
		action="/administrator/auditor-request/delete"/>
	<acme:form-submit test="${command == 'create'}"
		code="administrator.auditorRequest.form.button.create"
		action="/administrator/auditor-request/create"/>
	<acme:form-submit test="${command == 'update'}"
		code="administrator.auditorRequest.form.button.update"
		action="/administrator/auditor-request/update"/>
	<acme:form-submit test="${command == 'delete'}"
		code="administrator.auditorRequest.form.button.delete"
		action="/administrator/auditor-request/delete"/>
	<acme:form-return code="administrator.auditorRequest.form.button.return" />
</acme:form>