<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.offer.list.label.ticker" path="ticker" />
	<acme:form-textbox code="authenticated.offer.list.label.title" path="title" />
	<acme:form-textarea code="authenticated.offer.list.label.text" path="text" />
	<acme:form-moment code="authenticated.offer.list.label.deadline" path="deadline"/>
	<acme:form-money code="authenticated.offer.list.label.rangeFrom" path="rangeFrom"/>
	<acme:form-money code="authenticated.offer.list.label.rangeTo" path="rangeTo"/>
	
	<acme:form-return code="authenticated.offer.form.button.return" />
</acme:form>