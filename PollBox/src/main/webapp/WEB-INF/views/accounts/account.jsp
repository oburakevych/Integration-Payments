<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>

<h2>
${account.name}
</h2>

<div>
		<table>
    		<tr collspan="2">
      			<th align="left">Account Info</th>
      		</tr>
      		<tr>
      			<td>Created: </td>
      			<td>
      				<div id="accountDateCreated"><fmt:formatDate value="${account.dateCreated.time}" pattern="d/M/yyyy" /></div>
      			</td>
      		</tr>
      		<tr>
      			<td>Status: </td>
      			<td>
      				<div id="view_accountStatus">${account.status.name}</div>
      			</td>
      		</tr>
      		<tr collspan="2">
      			<th align="left">Account Owners</th>
      		</tr>
      		<tr>
      			<c:set var="ownersSize" value="${fn:length(account.owners)}" />
      			<c:set var="colunmIndex" value="0" />
      			<c:forEach var="accountOwner" items="${account.owners}"  varStatus="status">
      				<c:set var="colunmIndex" value="${colunmIndex + 1}" />
      				<td><a href="<spring:url value="/owners/${accountOwner.id}" htmlEscape="true" />">${accountOwner.firstName}&nbsp;${accountOwner.lastName}</a></td>
      				<c:if test="${(colunmIndex eq 2) && (ownersSize > (status.index+1))}">
      					<c:set var="colunmIndex" value="0" />
      					</tr>
      					<tr>
      				</c:if>
      				<c:if test="${ownersSize eq (status.index+1)}">
						<c:if test="${((status.index % 2) eq 0)}">
							<td>&nbsp;</td>
						</c:if>
					</c:if>	
      				</td>
      			</c:forEach>
      		</tr>
      		<tr>
      			<td><br><div id="add_btn"><input type="button" value="Add Owner" onClick="window.location='<spring:url value="/owners/new" htmlEscape="true" />'"/></div></td>
      			<td></td>
      		</tr>
	</table>
</div>

<script type="text/javascript">
	var pageUri = "/account.view";
	MenuTabSelector.selectPage(pageUri); 
</script>