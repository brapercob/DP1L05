<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.perezBulletin.form.label.title" path="title" />
	<acme:form-textarea code="anonymous.perezBulletin.form.label.content" path="content" />

	<acme:form-submit code="anonymous.perezBulletin.form.button.create" action="/anonymous/perez-bulletin/create" />
	<acme:form-return code="anonymous.perezBulletin.form.button.return" />
</acme:form>