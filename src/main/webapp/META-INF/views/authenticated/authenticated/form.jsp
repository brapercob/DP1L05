<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.authenticated.form.label.name" path="identity.name"/>
	<acme:form-textbox code="authenticated.authenticated.form.label.surname" path="identity.surname"/>
	<acme:form-textbox code="authenticated.authenticated.form.label.username" path="userAccount.username"/>
	<acme:form-textbox code="authenticated.authenticated.form.label.email" path="identity.email"/>
	<acme:form-return code="authenticated.authenticated.form.button.return" />
</acme:form>
