<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Linkki Lähetetty</title>
</head>
<body>
<div align="center">
        <table border="0">
            <tr>
                <td colspan="2" align="center"><h2>Registration Succeeded!</h2></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <h3>Kiitos rekisteröitymisestä. Linkki lähetetty. Alla tietoja:</h3>
                </td>
            </tr>
            <tr>
                <td>Nimi: </td>
                <td>${userRegisterDto.username}</td>
            </tr>   
            <tr>
                <td>Salasana: </td>
                <td>${userRegisterDto.password}</td>
            </tr> 
        </table>
    </div>
</body>
</html>