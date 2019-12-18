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

<acme:form>
	
	<acme:form-hidden path="creationMoment"/>
	<acme:form-hidden path="status"/>	
	<acme:form-hidden path="job.reference"/>
	<acme:form-textbox code="worker.application.form.label.reference" path="reference"/>
	<acme:form-money code="worker.application.form.label.statement" path="statement"/>
	<acme:form-url code="worker.application.form.label.skills" path="skills"/>
	<acme:form-textarea code="worker.application.form.label.qualifications" path="qualifications"/>
	
	<acme:form-submit test="${command == 'create'}" 
		code="worker.application.form.button.create"
		action="/worker/application/create?jobId=${jobId}"
	/>
	<acme:form-return code="worker.application.form.button.return"/>

</acme:form>
