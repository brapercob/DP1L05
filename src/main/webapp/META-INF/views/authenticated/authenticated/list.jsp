<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.authenticated.list.label.name" path="identity.name" width="20%" />
	<acme:list-column code="authenticated.authenticated.list.label.surname" path="identity.surname" width="20%" />
	<acme:list-column code="authenticated.authenticated.list.label.username" path="userAccount.username" width="20%" />
	<acme:list-column code="authenticated.authenticated.list.label.email" path="identity.email" width="20%" />

</acme:list>
<acme:form>
	<acme:form-return code="authenticated.authenticated.form.button.return" />
</acme:form>


