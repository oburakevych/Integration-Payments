<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Status</title>
</head>
<body>
<h1>
${status.name}
</h1>
<hr>
<p>Description: ${status.description}</p>
</body>
</html>
