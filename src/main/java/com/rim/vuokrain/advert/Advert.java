package com.rim.vuokrain.advert;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Indexed
@Table(name = "ad")
public class Advert {
		
		@Id
		private long id;
		@NotEmpty
		private String province;
		@NotEmpty
		private String municipality;
		@NotEmpty
		private String department;
		@NotEmpty
		private String adType;
		@NotEmpty
		private String personOrCompany;
		@NotEmpty
		@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
		private String rubric;
		@NotEmpty
		@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
		private String messageText;
		@NotEmpty
		private String zipCode;
		@NotEmpty
		private String price;
		@NotEmpty
		private String name;
		@NotEmpty
		private String email;
		@NotEmpty
		private String telephone;
		private Date addedDate;
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
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
		
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		
		public String getAdType() {
			return adType;
		}
		public void setAdType(String adType) {
			this.adType = adType;
		}
		
		public String getPersonOrCompany() {
			return personOrCompany;
		}
		public void setPersonOrCompany(String personOrCompany) {
			this.personOrCompany = personOrCompany;
		}
		
		public String getRubric() {
			return rubric;
		}
		public void setRubric(String rubric) {
			this.rubric = rubric;
		}
		
		public String getMessageText() {
			return messageText;
		}
		public void setMessageText(String messageText) {
			this.messageText = messageText;
		}
		
		public String getZipCode() {
			return zipCode;
		}
		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}
		
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
				
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		
		public Date getAddedDate() {
			return addedDate;
		}
		public void setAddedDate(Date addedDate) {
			this.addedDate = addedDate;
		}
}
