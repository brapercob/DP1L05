<%@page language="java"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	
	<jstl:if test="${command != 'update'}">
	<acme:form-textbox code="authenticated.thread.form.label.title" path="title" />
	</jstl:if>
	
	<jstl:if test="${command == 'show'}">
		<acme:form-moment code="authenticated.thread.form.label.moment" path="moment" readonly="true" />
	</jstl:if>

	
	<jstl:if test="${command != 'show'}">
		<spring:message code="authenticated.thread.form.label.participants" />
		<div style="overflow-y: scroll; width: 50%; height: 50%; max-height: 100px; border: #000000 4px solid;">
			<c:forEach items="${participants}" var="participant">
				<acme:form-checkbox code="${participant.userAccount.username}" path="${participant.id}" />
				<input type="hidden" value="on" name="_${participant.id}" />
			</c:forEach>
		</div>
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
			<a href="authenticated/authenticated/list_involved?threadId=${id}"><spring:message
					code="authenticated.thread.form.label.involved" /></a>
		</p>
	</jstl:if>
	
	<jstl:if test="${command == 'show'}">
		<p>
			<a href="authenticated/thread/update?id=${id}"> <spring:message code="authenticated.thread.form.label.update.participants" /></a>
		</p>
	</jstl:if>

	<jstl:if test="${command == 'show'}">
		<p>
			<a href="authenticated/message/create?threadId=${id}"> <spring:message code="authenticated.thread.form.label.messages.post" /></a>
		</p>
	</jstl:if>
	<hr />

	<acme:form-submit test="${command == 'create'}" code="authenticated.thread.form.button.create"
		action="/authenticated/thread/create" />
	<acme:form-submit test="${command == 'update'}" code="authenticated.thread.form.button.update"
	action="/authenticated/thread/update" />

	<acme:form-return code="authenticated.thread.form.button.return" />
</acme:form>
