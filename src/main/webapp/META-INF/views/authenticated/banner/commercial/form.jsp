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
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<acme:form readonly="true">
	<fieldset>
	<legend>
		<acme:message code="authenticated.banner.commercial.banner" />
	</legend>
		<acme:form-textbox code="authenticated.banner.commercial.picture" path="picture" />
		<acme:form-textbox code="authenticated.banner.commercial.slogan" path="slogan" />
		<acme:form-textbox code="authenticated.banner.commercial.targetURL" path="targetURL" />
	</fieldset>

	<acme:form-return code="authenticated.banner.commercial.form.button.return" />

</acme:form>

