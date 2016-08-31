<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>

<html>
<head>
	<title>Aloitus</title>
</head>
<body>

	<sec:authorize access="isAuthenticated()">    
	    <c:url var="logoutUrl" value="/logout"/>
	      <form class="form-inline" action="${logoutUrl}" method="post">
	          <input type="submit" value="Log out" />
	          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	      </form>
	            <h1>Logged in as: <sec:authentication property="principal.username" /></h1>      
	</sec:authorize>

	<h1>
		<a href="newAdvert">Uusi Ilmoitus</a>
	</h1>
	<h1>
		<a href="userRegister">Rekisteröidy</a>
	</h1>
	<h1>
		<a href="login">Kirjaudu Sisään</a>
	</h1>
	
	<h1>
		<a href="forgot">Unohditko salasanasi</a>
	</h1>
	
	<h1>
		<a href="edit">Omat tiedot</a>
	</h1>

	<form method="POST">
		<input type="text" name="s" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="submit" value="Submit" />
	</form>
	
	<c:choose>
	    <c:when test="${empty adList}">
	        <h1> Ei ilmoituksia. </h1>
	    </c:when>
	    <c:otherwise>
	        <h1>Ilmoitukset</h1>
        	<table border="1">	        	
				<c:forEach var="ad" items="${adList}" varStatus="status">
		        	<tr>
		        		<td>${status.index + 1}</td>
						<td>${ad.id}</td>						
						<td>${ad.rubric}</td>
						<td>${ad.messageText}</td>																						
							<spring:url value="/advert/${ad.id}" var="detailUrl" />
						<td><button onclick="location.href='${detailUrl}'"> DetailView </button></td>	
		        	</tr>
				</c:forEach>	        	
        	</table>
	    </c:otherwise>
	</c:choose>
</body>
</html>
