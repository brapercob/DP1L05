
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.company-record.form.label.companyName" path="companyName" readonly="true"/>
	<acme:form-textbox code="authenticated.company-record.form.label.sector" path="sector" readonly="true" />
	<acme:form-textbox code="authenticated.company-record.form.label.CEOName" path="CEOName" readonly="true"/>
	<acme:form-textbox code="authenticated.company-record.form.label.activitiesDescription" path="activitiesDescription" readonly="true"/>
	<acme:form-textbox code="authenticated.company-record.form.label.website" path="website" readonly="true"/>
	<acme:form-textbox code="authenticated.company-record.form.label.phone" path="phone" readonly="true"/>
	<acme:form-textbox code="authenticated.company-record.form.label.email" path="email" readonly="true"/>
	<acme:form-textbox code="authenticated.company-record.form.label.incorporation" path="incorporation" readonly="true"/>
	<acme:form-textbox code="authenticated.company-record.form.label.stars" path="stars" readonly="true"/>
	
	<acme:form-return code="authenticated.company-record.form.label.return"/>
</acme:form>