<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Omat tiedot</title>
</head>
<body>
<div align="center">
        <form:form method="post" commandName="userInfo">
        
            <table border="0">
                <tr>
                    <td colspan="2" align="center"><h2>Omat tiedot: </h2></td>
                </tr>
                
                <tr>
                    <td>Käyttäjä: </td>                    
                    <td>${userInfo.username}</td>  
                    <td><form:input type="hidden" path="username"/> </td>                
                </tr>
                
                <tr>
                    <td>Maakunta: </td>
                    <td><form:input path="province" /></td>
                    <td><form:errors path="province" /></td>
                </tr>
                
                <tr>
                    <td>Kunta: </td>
                    <td><form:input path="municipality" /></td>
                    <td><form:errors path="municipality" /></td>
                </tr>
                
                <tr>
                    <td>Henkilö vai yritys: </td>
                    <td>
                    	<form:radiobutton path="personOrCompany" value="person" />Yksityinen
                    	<form:radiobutton path="personOrCompany" value="company" />Yritys
                    </td>
                    <td><form:errors path="personOrCompany" /></td>
                </tr>
                
                <tr>
                    <td>Nimi: </td>
                    <td><form:input path="name" /></td>
                    <td><form:errors path="name" /></td>
                </tr>
                
                <tr>
                    <td>Osoite: </td>
                    <td><form:input path="address" /></td>
                </tr>
                
                <tr>
                    <td>Postinumero: </td>
                    <td><form:input path="zipCode" /></td>
                </tr>
                
                <tr>
                    <td>Puhelin: </td>
                    <td><form:input path="telephone" /></td>
                    <td><form:errors path="telephone" /></td>
                </tr>
                
                <tr>
                    <td>Sukupuoli: </td>
                    <td><form:input path="sex" /></td>
                </tr>
                
                <tr>
                    <td>Syntymäaika: </td>
                    <td><form:input path="birthdate" /></td>
                </tr>
                
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Tallenna" /></td>
                </tr>
            </table>
        </form:form>
        
        <h1>${successMessage}</h1>
        
    </div>
</body>
</html>