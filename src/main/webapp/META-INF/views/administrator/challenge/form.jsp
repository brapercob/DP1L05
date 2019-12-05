<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.challenge.list.label.title" path="title" />
	<acme:form-textarea code="administrator.challenge.list.label.description" path="description" />
	<acme:form-moment code="administrator.challenge.list.label.deadline" path="deadline"/>
	<acme:form-textbox code="administrator.challenge.list.label.goldGoal" path="goldGoal"/>
	<acme:form-double code="administrator.challenge.list.label.goldReward" path="goldReward"/>
	<acme:form-textbox code="administrator.challenge.list.label.silverGoal" path="silverGoal"/>
	<acme:form-double code="administrator.challenge.list.label.silverReward" path="silverReward"/>
	<acme:form-textbox code="administrator.challenge.list.label.bronzeGoal" path="bronzeGoal"/>
	<acme:form-double code="administrator.challenge.list.label.bronzeReward" path="bronzeReward"/>
	
	<acme:form-submit test="${command == 'show'}"
		code="administrator.challenge.form.button.update"
		action="/administrator/challenge/update"/>
	<acme:form-submit test="${command == 'show'}"
		code="administrator.challenge.form.button.delete"
		action="/administrator/challenge/delete"/>
	<acme:form-submit test="${command == 'create'}"
		code="administrator.challenge.form.button.create"
		action="/administrator/challenge/create"/>
	<acme:form-submit test="${command == 'update'}"
		code="administrator.challenge.form.button.update"
		action="/administrator/challenge/update"/>
	<acme:form-submit test="${command == 'delete'}"
		code="administrator.challenge.form.button.delete"
		action="/administrator/challenge/delete"/>
	<acme:form-return 
		code="administrator.challenge.form.button.return" />
</acme:form> 