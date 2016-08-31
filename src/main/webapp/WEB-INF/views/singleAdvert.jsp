<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ilmoituksen lisätiedot</title>
</head>
<body>

	<h1>Tiedot</h1>
	
	<c:forEach var="picture" items="${pictures}" varStatus="status">
		<img src="${picture.url}" style="width:304px;height:228px;">
	</c:forEach>
	
	<h2>id</h2>
	<p> ${ad.id}</p>
	<h2>kunta</h2>
	<p>${ad.province}</p>
	<h2>kaupunki</h2>
	<p>${ad.municipality}</p>
	<h2>osasto</h2>
	<p>${ad.department}</p>
	<h2>ilmoitustyyppi</h2>
	<p>${ad.adType}</p>
	<h2>yksityinen vai yritys </h2>
	<p>${ad.personOrCompany}</p>
	<h2>otsikko</h2>
	<p>${ad.rubric}</p>
	<h2>teksti</h2>
	<p>${ad.messageText}</p>
	<h2>postinumero</h2>
	<p>${ad.zipCode}</p>
	<h2>hinta</h2>
	<p>${ad.price}</p>
	<h2>nimi</h2>
	<p>${ad.name}</p>
	<h2>sähköposti</h2>
	<p>${ad.email}</p>
	<h2>puhelin</h2>
	<p>${ad.telephone}</p>
	<h2>lisätty</h2>
	<p>${ad.addedDate}</p>	

</body>
</html>