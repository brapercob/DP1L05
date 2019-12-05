<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.challenge.list.label.title" path="title" />
	<acme:form-textarea code="authenticated.challenge.list.label.description" path="description" />
	<acme:form-moment code="authenticated.challenge.list.label.deadline" path="deadline" width="10%" />
	<acme:form-textbox code="authenticated.challenge.list.label.goldGoal" path="goldGoal"/>
	<acme:form-double code="authenticated.challenge.list.label.goldReward" path="goldReward"/>
	<acme:form-textbox code="authenticated.challenge.list.label.silverGoal" path="silverGoal"/>
	<acme:form-double code="authenticated.challenge.list.label.silverReward" path="silverReward"/>
	<acme:form-textbox code="authenticated.challenge.list.label.bronzeGoal" path="bronzeGoal"/>
	<acme:form-double code="authenticated.challenge.list.label.bronzeReward" path="bronzeReward"/>
	<acme:form-return code="authenticated.challenge.form.button.return" />
</acme:form> 