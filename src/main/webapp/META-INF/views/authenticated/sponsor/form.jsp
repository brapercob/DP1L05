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
	<acme:form-textbox code="authenticated.sponsor.form.label.orgName" path="organizationName"/>
	<acme:form-textbox path="holder" code="authenticated.sponsor.form.label.holder"/>
	<acme:form-textbox path="brand" code="authenticated.sponsor.form.label.brand"/>
	<acme:form-textbox code="authenticated.sponsor.form.label.creditCardNumber" path="creditCardNumber" placeholder="Example of creditcard (379354508162306)"/>
	<acme:form-integer path="expirationMonth" code="authenticated.sponsor.form.label.expMonth"/>
	<acme:form-integer path="expirationYear" code="authenticated.sponsor.form.label.expYear"/>

	
	<acme:form-submit test="${command == 'create'}" code="authenticated.sponsor.form.button.create" action="/authenticated/sponsor/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.sponsor.form.button.update" action="/authenticated/sponsor/update"/>
	<acme:form-return code="authenticated.sponsor.form.button.return"/>
</acme:form>
