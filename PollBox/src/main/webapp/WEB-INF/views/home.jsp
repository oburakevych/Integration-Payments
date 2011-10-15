<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h2>
	<spring:message code="page.label.home.welcome"/>
	<sec:authorize access="isAuthenticated()">
		<c:if test="${owner != null}">
			back ${owner.firstName}&nbsp;${owner.lastName}
		</c:if>
	</sec:authorize>
</h2>

<p><spring:message code="page.label.home.cando"/></p>
	<ul>
		<sec:authorize access="isAnonymous()">
		<li><a href="login"><spring:message code="page.label.home.cando.login"/></a></li>
		<li><a href="signup"><spring:message code="page.label.home.cando.signup"/></a></li>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
		<li><a href="<spring:url value="/j_spring_security_logout" htmlEscape="true" />"><spring:message code="page.label.home.cando.logout"/></a></li>
		<c:if test="${owner != null}">
			<li><a href="owners/${owner.id}"><spring:message code="page.label.home.cando.view_profile"/></a></li>
			<li><a href="account"><spring:message code="page.label.home.cando.view_account"/></a></li>
		</c:if>
		</sec:authorize>
		<li><a href=""><spring:message code="page.label.home.cando.read_more"/></a></li>
	</ul>

<script type="text/javascript">
	var pageUri = "/home.view";
	MenuTabSelector.selectPage(pageUri); 
</script>