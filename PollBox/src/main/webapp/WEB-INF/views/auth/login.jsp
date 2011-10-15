<%@ taglib uri="http://java.sun.com/jsp/jstl/core"    prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"     prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring" %>

<h2>Login with Username and Password</h2>
<div id="login_frame">
	<div id="login_form" class="centered">
		<form name='f' action="<spring:url value="/j_spring_security_check" htmlEscape="true" />" method='POST'>
			<c:if test="${login_error eq 'true'}">
			<span class="errorMessage"><spring:message code="LoginError.login.error"/></span>
			</c:if>
			<table>
			    <tr><td>User:</td><td><input type='text' name='j_username' value=''></td></tr>
			    <tr><td>Password:</td><td><input type='password' name='j_password'/></td></tr>
			    <tr><td colspan='2'><input name="submit" type="submit"/></td></tr>
			    <tr><td colspan='2'><input name="reset" type="reset"/></td></tr>
			</table>
		</form>
	</div>
	
	<script type="text/javascript">
		document.f.j_username.focus();
	</script>
</div>