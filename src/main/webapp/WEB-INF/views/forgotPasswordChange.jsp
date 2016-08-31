<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Syötä uusi salasana</title>
</head>
<body>
<div align="center">
        <form:form method="post" modelAttribute="passwordDto">
        
            <table border="0">
                <tr>
                    <td colspan="2" align="center"><h2>Syötä uusi salasana: </h2></td>
                </tr>
                <tr>
                    <td>Salasana: </td>
                    <td><form:input path="password" /></td>
                    <td><form:errors path="password" /></td>
                    <td><form:errors path="" /></td>
                </tr> 
                                <tr>
                    <td>Salasana Uudestaan: </td>
                    <td><form:input path="matchingPassword" /></td>
                    <td><form:errors path="matchingPassword" /></td>
                    <td><form:errors path="" /></td>
                </tr> 
                  
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Register" /></td>
                </tr>
            </table>
            
            <form:hidden path="email" />
            
        </form:form>
    </div>
</body>
</html>