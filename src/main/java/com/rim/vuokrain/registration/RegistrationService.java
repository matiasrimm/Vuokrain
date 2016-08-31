package com.rim.vuokrain.registration;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rim.vuokrain.additionalUserInfo.UserInfo;
import com.rim.vuokrain.additionalUserInfo.UserInfoRepository;
import com.rim.vuokrain.exception.DuplicateUsernameException;
import com.rim.vuokrain.registration.authorities.Authorities;
import com.rim.vuokrain.registration.authorities.AuthoritiesRepository;
import com.rim.vuokrain.registration.user.User;
import com.rim.vuokrain.registration.user.UserRepository;

@Component
public class RegistrationService {
	
	// try insert data submitted in the form to the database
	// data from the form is carried here by the UserRegisterDto 
    @Resource UserRepository userRepository;
    @Resource AuthoritiesRepository authoritiesRepository;
    @Resource UserInfoRepository userInfoRepository; 
    @Resource PasswordEncoder pEncoder;  
    
    @Transactional
    public void insert(RegistrationDto userRegisterDto) throws DuplicateUsernameException {
    	
    	String TEST_AUTHORITY = "TEST_AUTH";
    	String DUPLICATE_USERNAME_EXCEP = "Duplicates found with username: ";
    	
    	// the usernameExist -function for username duplicates checking
    	if (usernameExist(userRegisterDto.getUsername())) {
            throw new DuplicateUsernameException( DUPLICATE_USERNAME_EXCEP + userRegisterDto.getUsername());
        }    	 
    	
    	User user = new User();
    	user.setUsername(userRegisterDto.getUsername());
    	user.setPassword(pEncoder.encode(userRegisterDto.getPassword()));
    	user.setEnabled(false);
    	userRepository.insert(user);  
    	
    	Authorities authorities = new Authorities();
    	authorities.setUsername(userRegisterDto.getUsername());
    	authorities.setAuthority(TEST_AUTHORITY);
    	authoritiesRepository.insert(authorities); 
    	
    	UserInfo userInfo = new UserInfo();
    	userInfo.setAddress(userRegisterDto.getAddress());
    	userInfo.setBirthdate(userRegisterDto.getBirthdate());
    	userInfo.setMunicipality(userRegisterDto.getMunicipality());
    	userInfo.setName(userRegisterDto.getName());
    	userInfo.setPersonOrCompany(userRegisterDto.getPersonOrCompany());
    	userInfo.setProvince(userRegisterDto.getProvince());
    	userInfo.setSex(userRegisterDto.getSex());
    	userInfo.setTelephone(userRegisterDto.getTelephone());
    	userInfo.setUsername(userRegisterDto.getUsername());
    	userInfo.setZipCode(userRegisterDto.getZipCode());
    	userInfoRepository.insert(userInfo);
    }
    
    @Transactional
    public void updateUser(User user) {
    	userRepository.update(user);
    }
    
    public User findByUsername(String username) {
		User user = userRepository.findByUsername(username);	 		
		return user;
	}
    
    public List<String> findAuthoritiesListByUsername(String username) {		
		List<String> authoritiesList = authoritiesRepository.findAuthoritiesListByUsername(username);		
		return authoritiesList;
	}
        
    // the usernameExist Function for username duplicates checking
	private boolean usernameExist(String username) {
		// check if username exists in the database using usersRepository
		User user = userRepository.findByUsername(username);			
		if (user != null) {
			return true;
		}		
		return false;
	}	
}