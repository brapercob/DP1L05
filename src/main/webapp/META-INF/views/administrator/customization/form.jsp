<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.customization.form.label.spamWords" path="spamWords" />
	<acme:form-textbox code="administrator.customization.form.label.threshold" path="threshold" />
	
	<acme:form-submit test="${command == 'show'}"
		code="administrator.customization.form.button.update" 
		action="/administrator/customization/update"/>
	<acme:form-submit test="${command == 'update'}"
		code="administrator.customization.form.button.update" 
		action="/administrator/customization/update"/>
	<acme:form-return code="administrator.customization.form.button.return" />
</acme:form> 