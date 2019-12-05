<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.thread.form.label.title" path="title" />
	<acme:form-moment code="authenticated.thread.form.label.moment" path="moment" />
	<acme:form-textbox code="authenticated.thread.form.label.participants" path="participants" />
	<p>
		<a href="authenticated/message/list?threadId=${id}"><spring:message code="authenticated.thread.form.label.messages" /></a>
	</p>
	<acme:form-return code="authenticated.thread.form.button.return" />
</acme:form>
