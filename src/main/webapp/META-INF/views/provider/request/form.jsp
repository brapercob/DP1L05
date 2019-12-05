<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="provider.request.list.label.ticker" path="ticker" />
	<acme:form-textbox code="provider.request.list.label.title" path="title" />
	<acme:form-textarea code="provider.request.list.label.description" path="description" />
	<jstl:if test="${command != 'create' }">
	<acme:form-moment code="provider.request.list.label.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>
	<acme:form-moment code="provider.request.list.label.deadline" path="deadline"/>
	<acme:form-double code="provider.request.list.label.reward" path="reward"/>
	<acme:form-checkbox code="provider.request.form.label.confirmation" path="confirmation"/>
	
	
	<acme:form-submit test="${command == 'create' }"
		code="provider.request.form.button.create"
		action="/provider/request_/create"/>
	<acme:form-return code="authenticated.request.form.button.return" />
</acme:form> 
