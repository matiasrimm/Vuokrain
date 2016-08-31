package com.rim.vuokrain.additionalUserInfo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class UserInfoService {

	@Resource UserInfoRepository userInfoRepository;
	
	public UserInfo findCurrentUsersInfo(String username) {
		UserInfo userInfo = userInfoRepository.findByUsername(username);
		return userInfo;
	}
	
	public void insert (UserInfo userInfo) {
		userInfoRepository.insert(userInfo);
	}
	
	public List<UserInfo> findAllUsersInfo() {
		List<UserInfo> userInfoList = userInfoRepository.findAllUserInfo();
		return userInfoList;
	}
}