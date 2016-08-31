<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Salasanan palautus.</title>
</head>
<body>
<div align="center">
        <form:form method="post" commandName="emailDto">
        
            <table border="0">
                <tr>
                    <td colspan="2" align="center"><h2>Salasanan palautus.</h2></td>
                </tr>
                <tr>
                    <td>Syötä sähköpostiosoite: </td>
                    <td><form:input path="email" /></td>
                    <td><form:errors path="email" /></td>
                </tr>       
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Lähetä" /></td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
</html>