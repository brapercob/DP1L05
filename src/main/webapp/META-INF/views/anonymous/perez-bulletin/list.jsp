<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.perezBulletin.list.label.moment" path="moment" width="20%" />
	<acme:list-column code="anonymous.perezBulletin.list.label.title" path="title" width="20%" />
	<acme:list-column code="anonymous.perezBulletin.list.label.content" path="content" width="60%" />
</acme:list>