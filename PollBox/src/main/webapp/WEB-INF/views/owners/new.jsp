<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>

<Head>
<script language="JavaScript" type="text/javascript">
	function setSelectedAuthority() {
		var selObj = document.getElementById('rolesSel');
		var inputObj = document.getElementById('authority');
	
		inputObj.value = selObj.options[selObj.selectedIndex].value;
	}
	
	function setSelectedLanguage() {
		var selObj = document.getElementById('languageSel');
		var inputObj = document.getElementById('language');
	
		inputObj.value = selObj.options[selObj.selectedIndex].value;
	}
</script>

</Head>

<c:if test="${!isSignup}">
<h2>
	Add a new owner to the account ${owner.account.name}
</h2>
</c:if>

<div>	
	<form:form id="profile_form" name="profile_form" modelAttribute="owner" method="POST">
		<table>
			<c:if test="${isSignup}">
    		<tr collspan="2">
      			<th align="left">Create New Account</th>
      		</tr>
		    <tr>
      			<td><label for="accountName">Account Name<span class="ast">*</span>:</label></td>
      			<td>
      				<form:input id="accountName" path="account.name" size="40" maxlength="20" cssErrorClass="errorBackground"/>
      				<span class="errorMessage"><form:errors path="account.name" /></span>
      			</td>
      		</tr>
      		</c:if>
    		<tr collspan="2">
      			<th align="left">Create your Poll ID</th>
      		</tr>
      		<tr>
      			<td><label for="firstName">First Name<span class="ast">*</span>:</label></td>
      			<td>
      				<form:input id="firstName" path="firstName" size="40" maxlength="40" cssErrorClass="errorBackground"/>
      				<span class="errorMessage"><form:errors path="firstName" /></span>	
      			</td>
      		</tr>
      		<tr>
      			<td><label for="lastName">Last Name<span class="ast">*</span>:</label></td>
      			<td>
      				<form:input id="lastName" path="lastName" size="40" maxlength="40" cssErrorClass="errorBackground"/>
      				<span class="errorMessage"><form:errors path="lastName" /></span>
      			</td>
      		</tr>
      		<tr>
      			<td><label for="email">Email Address<span class="ast">*</span>:</label></td>
      			<td>
      				<form:input id="email" path="email" size="40" maxlength="50" cssErrorClass="errorBackground"/>
      				<span class="errorMessage"><form:errors path="email" /></span>
      			</td>
      		</tr>
      		<tr>
      			<td><label for="username">Username<span class="ast">*</span>:</label></td>
      			<td>
      				<form:input id="username" path="username" size="40" maxlength="50" cssErrorClass="errorBackground"/>
      				<span class="errorMessage"><form:errors path="username" /></span>	
      			</td>
      		</tr>
      		<tr>
      			<td><label for="password1">Password<span class="ast">*</span>:</label></td>
      			<td>
      				<form:input id="password1" path="password" size="40" maxlength="255" cssErrorClass="errorBackground"/>
      				<span class="errorMessage"><form:errors path="password" /></span>
      			</td>
      		</tr>
      		<tr>
      			<td><label for="prassword2">Confirm Password<span class="ast">*</span>:</label></td>
      			<td><input id="prassword2" size="40" maxlength="255"/></td>
      		</tr>
      		<tr>
      			<td><label for="rolesSel">Authority<span class="ast">*</span>:</label></td>
      			<td>
					<select name="roles" id="rolesSel" onchange="setSelectedAuthority();" style="width: 266px;" <c:if test="${isSignup}">disabled</c:if>>
						<c:choose>
							<c:when test="${!isSignup}">
								<option value="" selected></option>
								<c:forEach var="role" items="${roles}">
									<option value="${role.code}">${role.description}</option>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<option value="${default_role.code}">${default_role.description}</option>
							</c:otherwise>
						</c:choose>
					</select>
      				<form:input type="hidden" id="authority" path="authority.authority" size="40" maxlength="20"/>
      			</td>
      		</tr>
      		<tr>
      			<td><label for="languageSel">Language<span class="ast">*</span>:</label></td>
      			<td>
					<select name="languages" id="languageSel" onchange="setSelectedLanguage();" style="width: 266px;">
						<c:forEach var="lang" items="${languages}">
							<option value="${lang.id}" <c:if test="${lang.id eq owner.language.id}">selected</c:if>>${lang.name}</option>
						</c:forEach>
					</select>
      				<form:input type="hidden" id="language" path="language.id" size="40" maxlength="20"/>
      			</td>
      		</tr>
      		<tr collspan="2">
      			<th align="left">Fill Out Your Profile (Optional) </th>
      		</tr>
      		<tr>
      			<td><label for="phoneNumber">Phone Number: </label></td>
      			<td>
      				<form:input id="phoneNumber" path="phoneNumber" size="40" maxlength="20" cssErrorClass="errorBackground"/>
      				<span class="errorMessage"><form:errors path="phoneNumber" /></span>
      			</td>
      		</tr>
      	</table>
      	<br>
      	<div>
			<input type="submit" value="Save Changes" />
			<input type="button" value="Cancel" onClick="window.location='<spring:url value="/account" htmlEscape="true" />'"/>
		</div>
	</form:form>
</div>

<script type="text/javascript">
	var pageUri = "/profile.view";
	MenuTabSelector.selectPage(pageUri); 
</script>