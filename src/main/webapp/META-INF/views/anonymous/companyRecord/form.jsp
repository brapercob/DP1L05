
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="anonymous.company-record.form.label.companyName" path="companyName"/>
	<acme:form-textbox code="anonymous.company-record.form.label.sector" path="sector" />
	<acme:form-textbox code="anonymous.company-record.form.label.CEOName" path="CEOName"/>
	<acme:form-textbox code="anonymous.company-record.form.label.activitiesDescription" path="activitiesDescription"/>
	<acme:form-textbox code="anonymous.company-record.form.label.website" path="website"/>
	<acme:form-textbox code="anonymous.company-record.form.label.phone" path="phone"/>
	<acme:form-textbox code="anonymous.company-record.form.label.email" path="email"/>
	<acme:form-textbox code="anonymous.company-record.form.label.incorporation" path="incorporation"/>
	<acme:form-textbox code="anonymous.company-record.form.label.stars" path="stars"/>

	<acme:form-return code="anonymous.company-record.form.label.return"/>
</acme:form>