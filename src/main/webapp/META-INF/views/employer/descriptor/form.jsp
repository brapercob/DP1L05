<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="hasDuties"/>
	<acme:form-hidden path="isPublished"/>
	<acme:form-hidden path="jobId"/>
	<acme:form-textarea code="employer.descriptor.form.label.description" path="description"/>
	<jstl:if test="${command == 'show' && !hasDuties}">
	<p>
	<a href="employer/duty/create?descriptorId=${id}"><spring:message code="employer.descriptor.form.label.duties.create" /></a>
</p>
	</jstl:if>
	<jstl:if test="${command == 'show' && hasDuties}">
		<p>
		<a href="employer/duty/create?descriptorId=${id}"><spring:message code="employer.descriptor.form.label.duties.create" /></a>
		</p>

		<p>
		<a href="employer/duty/list?descriptorId=${id}"><spring:message code="employer.descriptor.form.label.duties" /></a>
		</p>
	</jstl:if>


<acme:form-submit test="${command == 'show' && !isPublished}"
		code="employer.descriptor.form.button.update"
		action="/employer/descriptor/update"/>
	<acme:form-submit test="${command == 'show' && !isPublished}"
		code="employer.descriptor.form.button.delete"
		action="/employer/descriptor/delete"/>
	<acme:form-submit test="${command == 'create'}"
		code="employer.descriptor.form.button.create"
		action="/employer/descriptor/create?jobId=${jobId}"/>
	<acme:form-submit test="${command == 'update'}"
		code="employer.descriptor.form.button.update"
		action="/employer/descriptor/update"/>
	<acme:form-submit test="${command == 'delete'}"
		code="employer.descriptor.form.button.delete"
		action="/employer/descriptor/delete"/>
	<acme:form-return code="employer.descriptor.form.button.return"/>

</acme:form>