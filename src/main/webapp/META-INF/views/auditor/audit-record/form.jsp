<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-select code="auditor.audit-records.form.status" path="status">
		<acme:form-option code="auditor.audit-records.form.status.draft" value="draft"/>
		<acme:form-option code="auditor.audit-records.form.status.published" value="published"/>
	</acme:form-select>
	<acme:form-textbox code="auditor.audit-records.form.title" path="title" />
	<acme:form-textarea code="auditor.audit-records.form.body" path="body"/>
	
	<acme:form-submit test="${command == 'create'}" 
		code="auditor.audit-record.form.create" 
		action="/auditor/audit-record/create?jobId=${jobId}"
	/>
	<acme:form-return code="auditor.audit-records.form.return" />
</acme:form> 
