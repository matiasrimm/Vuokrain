package com.rim.vuokrain.integration.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.rim.vuokrain.additionalUserInfo.UserInfo;
import com.rim.vuokrain.additionalUserInfo.UserInfoService;
import com.rim.vuokrain.configuration.AppConfiguration;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static com.rim.vuokrain.configuration.AppConfiguration.TEST_PROFILE;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles( TEST_PROFILE )
@ContextConfiguration(classes = AppConfiguration.class)
@WebAppConfiguration
public class UserInfoServiceTests {
	
	private String TestUsername = "teodore@testaaja.fi";
	private String TestProvince = "Pirkanmaa";
	private String TestMunicipality = "Tampere";
	private String TestPersonOrCompany = "person";
	private String TestName = "teodore testaaja";
	private String TestAddress = "testikatu 1";
	private String TestZipCode = "33340";
	private String TestTelephone = "0401234567";
	private String TestSex = "M";
	private String TestBirthdate = "01.01.1910";
	
	private String InsertedUsername = "toni@testaaja.fi";
	private String InsertedProvince = "Kunta";
	private String InsertedMunicipality = "Kaupunki";
	private String InsertedPersonOrCompany = "Person";
	private String InsertedName = "Name";
	private String InsertedAddress = "Address";
	private String InsertedZipCode = "33340";
	private String InsertedTelephone = "112";
	private String InsertedSex = "M";
	private String InsertedBirthdate = "08.08.1999";
		
	@Resource UserInfoService userInfoService;
	
	@Test
	public void shouldFindUserInfoByName() {
		
		UserInfo uInfo = userInfoService.findCurrentUsersInfo(TestUsername);
		
		assertThat(uInfo.getUsername()).isEqualTo(TestUsername);
		assertThat(uInfo.getProvince()).isEqualTo(TestProvince);
		assertThat(uInfo.getMunicipality()).isEqualTo(TestMunicipality);
		assertThat(uInfo.getPersonOrCompany()).isEqualTo(TestPersonOrCompany);
		assertThat(uInfo.getName()).isEqualTo(TestName);
		assertThat(uInfo.getAddress()).isEqualTo(TestAddress);
		assertThat(uInfo.getZipCode()).isEqualTo(TestZipCode);
		assertThat(uInfo.getTelephone()).isEqualTo(TestTelephone);
		assertThat(uInfo.getSex()).isEqualTo(TestSex);
		assertThat(uInfo.getBirthdate()).isEqualTo(TestBirthdate);
	}
		
	@Test
	@Transactional
	public void shouldInsertUserInfo() {
		
		List<UserInfo> uInfoList = userInfoService.findAllUsersInfo();
        int found = uInfoList.size();
		
		UserInfo insertUserInfo = new UserInfo();		
		insertUserInfo.setUsername(InsertedUsername);
		insertUserInfo.setAddress(InsertedAddress);
		insertUserInfo.setBirthdate(InsertedBirthdate);
		insertUserInfo.setMunicipality(InsertedMunicipality);
		insertUserInfo.setName(InsertedName);
		insertUserInfo.setPersonOrCompany(InsertedPersonOrCompany);
		insertUserInfo.setProvince(InsertedProvince);
		insertUserInfo.setSex(InsertedSex);
		insertUserInfo.setTelephone(InsertedTelephone);
		insertUserInfo.setUsername(InsertedUsername);
		insertUserInfo.setZipCode(InsertedZipCode);
		
		userInfoService.insert(insertUserInfo);		
		
		UserInfo uInfo = userInfoService.findCurrentUsersInfo(InsertedUsername);		
		assertThat(uInfo.getUsername()).isEqualTo(InsertedUsername);
		assertThat(uInfo.getProvince()).isEqualTo(InsertedProvince);
		assertThat(uInfo.getMunicipality()).isEqualTo(InsertedMunicipality);
		assertThat(uInfo.getPersonOrCompany()).isEqualTo(InsertedPersonOrCompany);
		assertThat(uInfo.getName()).isEqualTo(InsertedName);
		assertThat(uInfo.getAddress()).isEqualTo(InsertedAddress);
		assertThat(uInfo.getZipCode()).isEqualTo(InsertedZipCode);
		assertThat(uInfo.getTelephone()).isEqualTo(InsertedTelephone);
		assertThat(uInfo.getSex()).isEqualTo(InsertedSex);
		assertThat(uInfo.getBirthdate()).isEqualTo(InsertedBirthdate);
		
		uInfoList = userInfoService.findAllUsersInfo();        
		assertThat(uInfoList.size()).isEqualTo( found + 1 );		
	}
}