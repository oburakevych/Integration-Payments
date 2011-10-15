<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h2>
Owners
</h2>
<hr>
	<c:set var="owners" value="${ownerList.owners}" />
	
	<c:forEach var="owner" items="${owners}">
		<p><a href="owners/${owner.id}">${owner.firstName} &nbsp; ${owner.lastName}</a></p>
	</c:forEach>
