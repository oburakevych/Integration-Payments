<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Statuses</title>
</head>
<body>
<h1>
Statuses
</h1>
<hr>
	<c:set var="statuses" value="${statusList.statuses}" />
	
	<c:forEach var="status" items="${statuses}">
		<p>${status.name}</p>
	</c:forEach>
</body>
</html>
