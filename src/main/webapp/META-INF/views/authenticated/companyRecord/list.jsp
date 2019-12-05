<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.company-record.list.label.companyName" path="companyName" width="10%"/>
	<acme:list-column code="authenticated.company-record.list.label.sector" path="sector" width="10%"/>
	<acme:list-column code="authenticated.company-record.list.label.CEOName" path="CEOName" width="10%"/>
	<acme:list-column code="authenticated.company-record.list.label.activitiesDescription" path="activitiesDescription" width="20%"/>
	<acme:list-column code="authenticated.company-record.list.label.website" path="website" width="10%"/>
	<acme:list-column code="authenticated.company-record.list.label.phone" path="phone" width="10%"/>
	<acme:list-column code="authenticated.company-record.list.label.email" path="email" width="10%"/>
	<acme:list-column code="authenticated.company-record.list.label.incorporation" path="incorporation" width="10%"/>
	<acme:list-column code="authenticated.company-record.list.label.stars" path="stars" width="10%"/>
</acme:list>