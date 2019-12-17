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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<acme:form>
	<acme:form-hidden path="hasDesc"/>
	<acme:form-textbox code="employer.job.form.label.reference" path="reference"/>
	<acme:form-textbox code="employer.job.form.label.title" path="title"/>
	<jstl:if test="${command != 'create' }">
		<jstl:if test="${command == 'update' && status == 'draft'}">
		<acme:form-textbox code="employer.job.form.label.status" path="status"/>
		</jstl:if>
		<acme:form-textbox code="employer.job.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="employer.job.form.label.salary" path="salary"/>
	<acme:form-url code="employer.job.form.label.link" path="link"/>
	
	<jstl:if test="${command == 'show' && hasDesc}">
<p>
	<a href="employer/descriptor/show?jobId=${id}"><spring:message code="employer.job.form.label.descriptor" /></a>
</p>

<p>
	<a href="employer/audit-record/list?jobId=${id}"><spring:message code="employer.job.form.label.auditRecord" /></a>
</p>

</jstl:if>


	<acme:form-submit test="${command == 'show' && status != 'published'}"
		code="employer.job.form.button.update"
		action="/employer/job/update"/>
	<acme:form-submit test="${command == 'show'}"
		code="employer.job.form.button.delete"
		action="/employer/job/delete"/>
	<acme:form-submit test="${command == 'create'}"
		code="employer.job.form.button.create"
		action="/employer/job/create"/>
	<acme:form-submit test="${command == 'update'}"
		code="employer.job.form.button.update"
		action="/employer/job/update"/>
	<acme:form-submit test="${command == 'delete'}"
		code="employer.job.form.button.delete"
		action="/employer/job/delete"/>
	<acme:form-return code="employer.job.form.button.return"/>

</acme:form>
