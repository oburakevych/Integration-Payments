<%@ taglib prefix="tiles"  uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="Cache-Control" content="no-store"/>
	<meta http-equiv="Pragma" content="no-cache"/>
	<meta http-equiv="Expires" content="0"/>

	<title><tiles:insertAttribute name="title" ignore="true" /></title>

	<link rel="stylesheet" type="text/css" media="all" href="<spring:url value="/resources/css/basic_layout.css" htmlEscape="true" />" />
	<link rel="stylesheet" type="text/css" media="all" href="<spring:url value="/resources/css/auth.css" htmlEscape="true" />" />
	<link rel="stylesheet" type="text/css" media="all" href="<spring:url value="/resources/css/owners.css" htmlEscape="true" />" />
	
	<script type="text/javascript" src="<spring:url value="/resources/javascript/EventHandler.js" htmlEscape="true" />"></script>
	<script type="text/javascript" src="<spring:url value="/resources/javascript/CommonUtils.js" htmlEscape="true" />"></script>
	<script type="text/javascript" src="<spring:url value="/resources/javascript/MenuTabSelector.js" htmlEscape="true" />"></script>
</head>

<body>
	<div id="container_frame">
		<div id="header_frame">
			<!-- HEADER -->
			<tiles:insertAttribute name="header" />
		</div>
		<div id="menu_frame">
			<!-- LEFT MENU -->
			<tiles:insertAttribute name="left_menu" />
		</div>
		<div id="content_frame">
			<!-- CONTENT -->
			<tiles:insertAttribute name="content" />
		</div>
		<div id="footer_frame">
			<!-- FOOTER -->
			<tiles:insertAttribute name="footer" />
		</div>
		<div style="clear: both;"></div>

	</div>

</body>
</html>