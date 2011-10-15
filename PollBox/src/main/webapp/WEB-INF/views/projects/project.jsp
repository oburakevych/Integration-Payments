<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>
${project.name}
</h2>
<hr>

<p>Description: ${project.description}</p>
<p>Date Created: <fmt:formatDate value="${project.dateCreated.time}" pattern="M/d/yyyy HH:mm" /></p>
