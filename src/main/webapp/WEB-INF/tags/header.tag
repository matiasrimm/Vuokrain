<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/resources/css/advertForm.css" var="vuokrainCss"/>
<link href="${vuokrainCss}" rel="stylesheet"/>
<%-- JQuery --%>
<spring:url value="/resources/vendors/jquery/jquery-2.2.4.min.js" var="jQuery"/>
<script src="${jQuery}"></script>
<%-- Bootstrap --%>
<spring:url value="/resources/vendors/bootstrap/js/bootstrap.min.js" var="bootstrapJs"/>
<script src="${bootstrapJs}"></script>
<%-- JavascriptFixes --%>
<spring:url value="/resources/js/help.js" var="javascriptFixes"/>
<script src="${javascriptFixes}"></script>