<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>

<div>
	<c:set var="isAuthenticated" value="false"/>
	<sec:authorize access="isAuthenticated()">
		<c:set var="isAuthenticated" value="true"/>
	</sec:authorize>
		
	<div id="header_logo">
	</div>
	<div id="header_menu" class="tabs">
		<ul class="nav">
			<li><a id="header-tab-home" title="<spring:message code="header.title.menu.home"/>" href="<spring:url value="/" htmlEscape="true" />"><spring:message code="header.label.menu.home"/></a></li>
	  		<c:choose>
	  			<c:when test="${isAuthenticated}">
			  		<c:if test="${owner != null}">
					<li><a id="header-tab-profile" title="<spring:message code="header.title.menu.profile"/>" href="<spring:url value="/owners/${owner.id}" htmlEscape="true" />"><spring:message code="header.label.menu.profile"/></a></li>
					</c:if>
					<li><a id="header-tab-account" title="<spring:message code="header.title.menu.account"/>" href="<spring:url value="/account" htmlEscape="true" />"><spring:message code="header.label.menu.account"/></a></li>	  			
	  			</c:when>
	  			<c:otherwise>
	  				<!-- Put some other tabs -->
	  			</c:otherwise>
	  		</c:choose>
		</ul>
	</div>
	<div class="header_menu_item_logout">
  		<c:choose>
  			<c:when test="${isAuthenticated}">
		  		<a href="<spring:url value="/j_spring_security_logout" htmlEscape="true" />" title="<spring:message code="header.title.menu.logout"/>"><spring:message code="header.label.menu.logout"/></a>
  				<c:if test="${owner != null}"><p>${owner.firstName}&nbsp;${owner.lastName}</p></c:if>
			</c:when>
			<c:otherwise>
				<a href="<spring:url value="/login" htmlEscape="true" />" title="<spring:message code="header.title.menu.login"/>"><spring:message code="header.label.menu.login"/></a><br>
				<div id="header_language_selection">
					<select id="languageSel" style="width: 100px; font-size: 80%;" title="<spring:message code="header.title.menu.language_select"/>">
						<c:forEach var="lang" items="${languages}">
							<option value="${lang.id}" <c:if test="${lang.code eq selectedLangCode}">selected</c:if> onclick="location.href='?lang=${lang.code}'">${lang.name}</option>
						</c:forEach>
					</select>

				</div>
			</c:otherwise>
		</c:choose>
	</div>  
	<div style="clear: both;"></div>
</div>

