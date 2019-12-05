
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.company-record.form.label.companyName" path="companyName"/>
	<acme:form-textbox code="administrator.company-record.form.label.sector" path="sector" />
	<acme:form-textbox code="administrator.company-record.form.label.CEOName" path="CEOName"/>
	<acme:form-textbox code="administrator.company-record.form.label.activitiesDescription" path="activitiesDescription"/>
	<acme:form-textbox code="administrator.company-record.form.label.website" path="website"/>
	<acme:form-textbox code="administrator.company-record.form.label.phone" path="phone"/>
	<acme:form-textbox code="administrator.company-record.form.label.email" path="email"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="administrator.company-record.form.label.incorporation" path="incorporation"/>
	</jstl:if>
	<acme:form-textbox code="administrator.company-record.form.label.stars" path="stars"/>
	
	<acme:form-submit test="${command == 'show'}" 
		code="administrator.company-record.form.button.update" 
		action="/administrator/company-record/update"/>
	<acme:form-submit test="${command == 'show'}" 
		code="administrator.company-record.form.button.delete" 
		action="/administrator/company-record/delete"/>
	<acme:form-submit test="${command == 'create'}" 
		code="administrator.company-record.form.button.create" 
		action="/administrator/company-record/create"/>
	<acme:form-submit test="${command == 'update'}" 
		code="administrator.company-record.form.button.update" 
		action="/administrator/company-record/update"/>
	<acme:form-submit test="${command == 'delete'}" 
		code="administrator.company-record.form.button.delete" 
		action="/administrator/company-record/delete"/>
	<acme:form-return 
		code="administrator.company-record.form.label.return"/>
</acme:form>