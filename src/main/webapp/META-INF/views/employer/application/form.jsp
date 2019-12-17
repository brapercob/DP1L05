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
	<acme:form-textbox code="employer.application.form.label.reference" path="reference"/>
	<acme:form-textbox code="employer.application.form.label.creationMoment" path="creationMoment"/>
	<acme:form-moment code="employer.application.form.label.status" path="status"/>
	<acme:form-money code="employer.application.form.label.statement" path="statement"/>
	<acme:form-url code="employer.application.form.label.skills" path="skills"/>
	<acme:form-textarea code="employer.application.form.label.qualifications" path="qualifications"/>
	<acme:form-textbox code="employer.application.form.label.job.reference" path="job.reference"/>
	
	<acme:form-textbox code="employer.application.form.label.justification" path="justification"/>
	
	<acme:form-select path="status" code="employer.application.form.button.check">
		<acme:form-option code="employer.application.form.button.accepted" value="accepted"/>
		<acme:form-option code="employer.application.form.button.rejected" value="rejected"/>
	</acme:form-select>
	
	<acme:form-submit code="employer.application.form.button.update" action="/employer/application/update"/>
	<acme:form-return code="employer.application.form.button.return"/>

</acme:form>
