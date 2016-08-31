package com.rim.vuokrain.registration;

import org.hibernate.validator.constraints.NotEmpty;

import com.rim.vuokrain.validation.EmailSyntax;
import com.rim.vuokrain.validation.PasswordMatch;

@PasswordMatch
public class RegistrationDto {
	
	// matching password for serverSide existence checking
		
	@EmailSyntax
	private String username;	
	@NotEmpty	
	private String password;	
	@NotEmpty	
	private String matchingPassword;
	@NotEmpty
	private String province;
	@NotEmpty
	private String municipality;
	@NotEmpty
	private String personOrCompany;
	@NotEmpty
	private String name;
	
	private String address;
	
	private String zipCode;
	@NotEmpty
	private String telephone;
	
	private String sex;
	
	private String birthdate;
		
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMatchingPassword() {
		return matchingPassword;
	}
	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	public String getPersonOrCompany() {
		return personOrCompany;
	}
	public void setPersonOrCompany(String personOrCompany) {
		this.personOrCompany = personOrCompany;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
}