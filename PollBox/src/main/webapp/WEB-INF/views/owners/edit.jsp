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
		var inputResolver = document.getElementById('langResolver');
	
		inputObj.value = selObj.options[selObj.selectedIndex].value;
		inputResolver.value = selObj.options[selObj.selectedIndex].lang;
	}
</script>

</Head>

<h2>
	${owner.firstName} &nbsp; ${owner.lastName}
</h2>

<div>
	<spring:url value="/owners/${owner.id}" htmlEscape="true" var="actionURL"/> 
	
	<form:form id="profile_form" action="${actionURL}" modelAttribute="owner" method="PUT">
		<table>
    		<tr collspan="2">
      			<th align="left">Account Info</th>
      		</tr>
      		<tr>
      			<td>
	      			<label for="accountName">Name<span class="ast">*</span>:</label>
      			</td>
      			<td>
      				<form:input id="accountName" path="account.name" size="40" maxlength="20" cssErrorClass="errorBackground"/>
      				<span class="errorMessage"><form:errors path="account.name" /></span>
      			</td>
      		</tr>
      		<tr>
      			<td>
      				<label>Created<span class="ast">*</span>:</label></td>
      			<td>
      				<fmt:formatDate value="${owner.account.dateCreated.time}" pattern="d/M/yyyy" />
      			</td>
      		</tr>
      		<tr>
      			<td>
      				<label for="accountStatus">Status<span class="ast">*</span>:</label>
      			</td>
      			<td>
					<div id="edit_accountStatus">
						<form:select name="accountStatus" id="accountStatus" path="account.status.id" cssStyle="width: 266px;" cssErrorClass="errorBackground">
							<c:forEach var="status" items="${accountStatuses}">
								<form:option value="${status.id}">${status.name}</form:option>
							</c:forEach>
						</form:select>
						<span class="errorMessage"><form:errors path="account.status.id" /></span>
					</div>
      			</td>
      		</tr>
      		<tr collspan="2">
      			<th align="left">Account Owner</th>
      		</tr>
      		<tr>
      			<td>
      				<label>Username<span class="ast">*</span>:</label></td>
      			<td>
      				<div id="username">${owner.username}</div>
      			</td>
      		</tr>
      		<tr>
      			<td>
      				<label for="firstName">First Name<span class="ast">*</span>:</label>
      			</td>
      			<td>
      				<form:input id="firstName" path="firstName" size="40" maxlength="40" cssErrorClass="errorBackground"/>
      				<span class="errorMessage"><form:errors path="firstName" /></span>
      			</td>
      		</tr>
      		<tr>
      			<td>
      				<label for="lastName">Last Name<span class="ast">*</span>:</label></td>
      			<td>
      				<form:input id="lastName" path="lastName" size="40" maxlength="40" cssErrorClass="errorBackground"/>
					<span class="errorMessage"><form:errors path="lastName" /></span>
      			</td>
      		</tr>
      		<tr>
      			<td>
      				<label for="email">Email Address<span class="ast">*</span>:</label></td>
      			<td>
      				<form:input id="email" path="email" size="40" maxlength="255" cssErrorClass="errorBackground"/>
      				<span class="errorMessage"><form:errors path="email" /></span>
      			</td>
      		</tr>
      		<tr>
      			<td><label for="languageSel">Language<span class="ast">*</span>:</label></td>
      			<td>
					<select name="languages" id="languageSel" onchange="setSelectedLanguage();" style="width: 266px;">
						<c:forEach var="lang" items="${languages}">
							<option value="${lang.id}" lang="${lang.code}" <c:if test="${lang.id eq owner.language.id}">selected</c:if>>${lang.name}</option>
						</c:forEach>
					</select>
      				<form:input type="hidden" id="language" path="language.id" size="40" maxlength="20"/>
      				<input type="hidden" id="langResolver" name="lang" value="${owner.language.code}"/>
      			</td>
      		</tr>
      		<tr collspan="2">
      			<th align="left">Personal Info: </th>
      		</tr>
      		<tr>
      			<td>Phone Number: </td>
      			<td>
      				<form:input id="phoneNumber" path="phoneNumber" size="40" maxlength="20" cssErrorClass="errorBackground"/>
      				<span class="errorMessage"><form:errors path="phoneNumber" /></span>
      			</td>
      		</tr>
      	</table>
		<br>
		<div>
			<p class="acknowledgementMessage">It is recommended to logout and login again to allow all changes to apply</p>
			<input type="submit" value="Save Changes" />
			<input type="button" value="Cancel" onClick="location.href='${actionURL}'"/>
		</div>
	</form:form>
</div>

<script type="text/javascript">
	var pageUri = "/profile.view";
	MenuTabSelector.selectPage(pageUri); 
</script>