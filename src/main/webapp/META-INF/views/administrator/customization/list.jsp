<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.customization.list.label.spamWords" path="spamWords" width="20%" />
	<acme:list-column code="administrator.customization.list.label.threshold" path="threshold" width="20%" />
</acme:list> 