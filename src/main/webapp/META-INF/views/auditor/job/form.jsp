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
	<acme:form-textbox code="auditor.job.form.label.reference" path="reference"/>
	<acme:form-textbox code="auditor.job.form.label.title" path="title"/>
	<acme:form-moment code="auditor.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="auditor.job.form.label.salary" path="salary"/>
	<acme:form-url code="auditor.job.form.label.link" path="link"/>
<p>
	<a href="auditor/descriptor/show?jobId=${id}"><spring:message code="auditor.job.form.label.descriptor" /></a>
</p>

<p>
	<a href="auditor/audit-record/list?jobId=${id}"><spring:message code="auditor.job.form.label.auditRecord" /></a>
</p>

<p>
	<a href="auditor/audit-record/create?jobId=${id}"><spring:message code="auditor.job.form.label.newAuditRecord" /></a>
</p>
	
	<acme:form-return code="auditor.job.form.button.return"/>

</acme:form>
