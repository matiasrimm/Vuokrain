package com.rim.vuokrain.additionalUserInfo;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "additionaluserinfo")
public class UserInfo {	
			
			@Id
			@NotEmpty
			private String username;
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