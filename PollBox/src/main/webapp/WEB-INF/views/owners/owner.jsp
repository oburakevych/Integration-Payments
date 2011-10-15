<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>


<h2>
	${owner.firstName} &nbsp; ${owner.lastName}
</h2>

<div id="profile_form" >
		<table>
    		<tr collspan="2">
      			<th align="left">Account Info</th>
      		</tr>
      		<tr>
      			<td>
	      			Name:
      			</td>
      			<td><div id="view_accountName">${owner.account.name}</div>
      			</td>
      		</tr>
      		<tr>
      			<td>
      				Created:
      			<td>
      				<div id="accountDateCreated"><fmt:formatDate value="${owner.account.dateCreated.time}" pattern="d/M/yyyy" /></div>
      			</td>
      		</tr>
      		<tr>
      			<td>
      				Status:
      			</td>
      			<td>
      				<div id="view_accountStatus">${owner.account.status.name}</div>
      			</td>
      		</tr>
      		<tr collspan="2">
      			<th align="left">Account Owner</th>
      		</tr>
      		<tr>
      			<td>
      				Username:
      			<td>
      				<div id="username">${owner.username}</div>
      			</td>
      		</tr>
      		<tr>
      			<td>
      				First Name:
      			</td>
      			<td>
      				<div id="view_firstName">${owner.firstName}</div>
      			</td>
      		</tr>
      		<tr>
      			<td>
      				Last Name:
      			<td>
      				<div id="view_lastName">${owner.lastName}</div>
      			</td>
      		</tr>
      		<tr>
      			<td>
      				Email Address:
      			<td>
      				<div id="view_email">${owner.email}</div>
      			</td>
      		</tr>
      		<tr>
      			<td>
      				Language:
      			<td>
      				<div id="view_language">${owner.language.name}</div>
      			</td>
      		</tr>
      		<tr collspan="2">
      			<th align="left">Personal Info: </th>
      		</tr>
      		<tr>
      			<td>Phone Number: </td>
      			<td>
      				<div id="view_phoneNumber">${owner.phoneNumber}</div>
      			</td>
      		</tr>
      	</table>
      	<br>
      	<div id="view_btn"><input type="button" value="Edit Profile" onclick="location.href='<spring:url value="/owners/${owner.id}/update" htmlEscape="true" />'"/></div>
</div>

<script type="text/javascript">
	var pageUri = "/profile.view";
	MenuTabSelector.selectPage(pageUri); 
</script>