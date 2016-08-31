<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="vuokrain" tagdir="/WEB-INF/tags" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Jätä Ilmoitus</title>
<vuokrain:header/>

</head>
<body>
<div align="center">

        <form:form method="post" commandName="advertFileBackerWrap" enctype="multipart/form-data">        	
            <table border="0">
                <tr>
                    <td colspan="2" align="center"><h2>Jätä Ilmoitus</h2></td>
                </tr>
                
                <tr>
                    <td>Maakunta: </td>
                    <td><form:input path="advert.province" /></td>
                    <td><form:errors path="advert.province" /></td>
                </tr>
                <tr>
                    <td>Kunta: </td>
                    <td><form:input path="advert.municipality" /></td>
                    <td><form:errors path="advert.municipality" /></td>
                </tr>
                <tr>
                    <td>Osasto: </td>
                    <td><form:select path="advert.department">
   						
						<option value="0">«Valitse osasto»</option>						
						<option value="2000" style="background-color:#dcdcc3;font-weight:bold;" id="cat2000">-- AJONEUVOT JA TARVIKKEET --</option> 
						<option value="2010" id="cat2010">Autot</option> 						
						<option value="2040" id="cat2040">Autotarvikkeet ja varaosat</option> 						
						<option value="2020" id="cat2020">Matkailuajoneuvot</option> 						
						<option value="2090" id="cat2090">Matkailuajoneuvojen tarvikkeet</option> 						
						<option value="2030" id="cat2030">Moto</option> 						
						<option value="2070" id="cat2070">Mototarvikkeet ja varaosat</option> 						
						<option value="2080" id="cat2080">Työkoneet ja kalusto</option> 						
						<option value="2050" id="cat2050">Veneet</option> 						
						<option value="2060" id="cat2060">Venetarvikkeet ja varaosat</option> 						
						<option value="1000" style="background-color:#dcdcc3;font-weight:bold;" id="cat1000">-- ASUNNOT JA TONTIT --</option> 
						<option value="1010" id="cat1010">Asunnot</option> 						
						<option value="1020" id="cat1020">Loma-asunnot</option> 						
						<option value="1030" id="cat1030">Tontit ja maatilat</option> 						
						<option value="1040" id="cat1040">Autotallit ja varastot</option> 						
						<option value="1050" id="cat1050">Ulkomaan asunnot</option> 						
						<option value="3000" style="background-color:#dcdcc3;font-weight:bold;" id="cat3000">-- KOTI JA ASUMINEN --</option> 						
						<option value="3010" id="cat3010">Kodinkoneet</option> 						
						<option value="3100" id="cat3100">Keittiötarvikkeet ja astiat</option> 						
						<option value="3020" id="cat3020">Sisustus ja huonekalut</option> 						
						<option value="3040" id="cat3040">Piha ja puutarha</option> 						
						<option value="3050" id="cat3050">Vaatteet ja kengät</option> 						
						<option value="3060" id="cat3060">Asusteet ja kellot</option> 						
						<option value="3070" id="cat3070">Lastenvaatteet ja kengät</option> 						
						<option value="3080" id="cat3080">Lastentarvikkeet ja lelut</option> 						
						<option value="3090" id="cat3090">Rakentaminen ja remontointi</option> 						
						<option value="4000" style="background-color:#dcdcc3;font-weight:bold;" id="cat4000">-- VAPAA-AIKA JA HARRASTUKSET --</option> 						
						<option value="4010" id="cat4010">Urheilu ja ulkoilu</option> 						
						<option value="4150" id="cat4150">Pyöräily ja tarvikkeet</option> 
						<option value="4070" id="cat4070">Musiikki ja soittimet</option> 
						<option value="4050" id="cat4050">Metsästys</option> 						
						<option value="4055" id="cat4055">Kalastus</option> 						
						<option value="4030" id="cat4030">Elokuvat</option> 						
						<option value="4130" id="cat4130">Kirjat ja lehdet</option> 						
						<option value="4040" id="cat4040">Lemmikkieläimet</option> 						
						<option value="4060" id="cat4060">Hevoset ja hevosurheilu</option>						
						<option value="4090" id="cat4090">Matkat</option>						
						<option value="4080" id="cat4080">Keräily</option>						
						<option value="4170" id="cat4170">Käsityöt</option>						
						<option value="4100" id="cat4100">Valokuvaus</option> 						
						<option value="4190" id="cat4190">Muut harrastukset</option> 						
						<option value="5000" style="background-color:#dcdcc3;font-weight:bold;" id="cat5000">-- ELEKTRONIIKKA --</option>
						<option value="5010" id="cat5010">Puhelimet ja tarvikkeet</option> 						
						<option value="5020" id="cat5020">Viihde-elektroniikka</option>						
						<option value="5030" id="cat5030">Tietotekniikka</option>						
						<option value="6000" style="background-color:#dcdcc3;font-weight:bold;" id="cat6000">-- LIIKETOIMINTA JA TYÖPAIKAT --</option> 
						<option value="6010" id="cat6010">Vapaat työpaikat</option> 						
						<option value="6020" id="cat6020">CV</option> 						
						<option value="6030" id="cat6030">Palvelut</option> 						
						<option value="6040" id="cat6040">Maatalous</option> 						
						<option value="6050" id="cat6050">Rakennuspalvelut</option> 
						<option value="6060" id="cat6060">Liikkeille ja yrityksille</option> 
						<option value="7000" style="background-color:#dcdcc3;font-weight:bold;" id="cat7000">-- MUUT --</option> 
						<option value="7010" id="cat7010">Muut</option> 
							
					</form:select></td>
                    <td><form:errors path="advert.department" /></td>
                </tr>
                <tr>
                    <td>Ilmoitustyyppi: </td>
                    <td>
                    	<form:radiobutton path="advert.adType" value="sell" />Myydään
                    	<form:radiobutton path="advert.adType" value="buy" />Ostetaan
                    	<form:radiobutton path="advert.adType" value="giveaway" />Annetaan
                    </td>
                    <td><form:errors path="advert.adType" /></td>
                </tr>
                <tr>
                    <td>Henkilö vai yritys: </td>
                    <td>
                    	<form:radiobutton path="advert.personOrCompany" value="person" />Yksityinen
                    	<form:radiobutton path="advert.personOrCompany" value="company" />Yritys
                    </td>
                    <td><form:errors path="advert.personOrCompany" /></td>
                </tr>
                 <tr>
                    <td>Otsikko: </td>
                    <td><form:input path="advert.rubric" /></td>
                    <td><form:errors path="advert.rubric" /></td>
                </tr>
                 <tr>
                    <td>Ilmoitusteksti: </td>
                    <td><form:input path="advert.messageText" /></td>
                    <td><form:errors path="advert.messageText" /></td>
                </tr>
                <tr>
                    <td>Postinumero: </td>
                    <td><form:input path="advert.zipCode" /></td>
                    <td><form:errors path="advert.zipCode" /></td>
                </tr>
                <tr>
                    <td>Hinta: </td>
                    <td><form:input path="advert.price" /></td>
                    <td><form:errors path="advert.price" /></td>
                </tr>
                <tr>                	
                    <td>Kuvat: 
					<td>
					<form:input path="fileBacker.multipartFile" type="file" id="file" class="inputfile" />
					</td>
					<td><form:errors path="fileBacker.multipartFile" /></td>														
				</tr>
				<tr id="input-tr" class="hideaddmorepicture">
					<td id="input-td">Lisäkuvat: </td>					
				</tr>
                <tr>
                    <td>Nimi: </td>
                    <td><form:input path="advert.name" /></td>
                    <td><form:errors path="advert.name" /></td>
                </tr>
                <tr>
                    <td>Sähköposti: </td>
                    <td><form:input path="advert.email" /></td>
                    <td><form:errors path="advert.email" /></td>
                </tr>
                <tr>
                    <td>Puhelinnumero: </td>
                    <td><form:input path="advert.telephone" /></td>
                    <td><form:errors path="advert.telephone" /></td>
                </tr>
                
				<tr>
                    <td colspan="2" align="center"><input type="submit" value="Lähetä" /></td>
                </tr>
            </table>          
           
           	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
           
          </form:form>          
    </div>
</body>
<vuokrain:footer/>
</html>