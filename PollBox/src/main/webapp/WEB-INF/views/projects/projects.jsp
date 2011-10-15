<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h2>
Projects
</h2>
<hr>
	<c:set var="projects" value="${projectList.projects}" />
	
	<c:forEach var="project" items="${projects}">
		<p><a href="projects/${project.id}">${project.name}</a></p>
	</c:forEach>

