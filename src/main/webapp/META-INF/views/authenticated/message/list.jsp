<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.message.list.label.moment" path="moment" width="20%" />
	<acme:list-column code="authenticated.message.list.label.title" path="title" width="20%" />
	<acme:list-column code="authenticated.message.list.label.tags" path="tags" width="20%" />

</acme:list>

<acme:form>
	<acme:form-return code="authenticated.message.form.button.return" />
</acme:form>
