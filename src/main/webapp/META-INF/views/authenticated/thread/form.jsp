<%@page language="java"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.thread.form.label.title" path="title" />

	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="authenticated.thread.form.label.moment" path="moment" readonly="true" />
	</jstl:if>

	<jstl:if test="${command == 'show'}">
		<acme:form-textbox code="authenticated.thread.form.label.participants" path="participants" />
	</jstl:if>


	<jstl:if test="${command == 'show'}">
		<p>
			<a href="authenticated/message/list?threadId=${id}"><spring:message code="authenticated.thread.form.label.messages" /></a>
		</p>
	</jstl:if>
	
	<jstl:if test="${command == 'show'}">
		<p>
			<a href="authenticated/authenticated/list_involved?threadId=${id}"><spring:message code="authenticated.thread.form.label.involved" /></a>
		</p>
	</jstl:if>

	<jstl:if test="${command == 'show'}">
		<p>
			<a href="authenticated/message/create?threadId=${id}"> <spring:message code="authenticated.thread.form.label.messages.post" /></a>
		</p>
	</jstl:if>

	<acme:form-submit test="${command == 'create'}" code="authenticated.thread.form.button.create"
		action="/authenticated/thread/create" />

	<acme:form-return code="authenticated.thread.form.button.return" />
</acme:form>
