<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.message.form.label.title" path="title" />
	<acme:form-moment code="authenticated.message.form.label.moment" path="moment" />
	<acme:form-textbox code="authenticated.message.form.label.tags" path="tags" />
	<acme:form-textarea code="authenticated.message.form.label.body" path="body"/>

	<acme:form-return code="authenticated.message.form.button.return" />
</acme:form> 
