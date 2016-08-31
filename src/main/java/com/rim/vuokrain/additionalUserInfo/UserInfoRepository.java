package com.rim.vuokrain.additionalUserInfo;

import java.util.List;

public interface UserInfoRepository {

	public void insert(UserInfo userInfo);

	public UserInfo findByUsername(String username) ;

	public List<UserInfo> findAllUserInfo();
}