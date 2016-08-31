package com.rim.vuokrain.registration.user;

import java.util.List;

public interface UserRepository {
	
	public void insert(User user);

	public User findByUsername(String username);

	public List<User> findAllUsers();
	
	public void update(User user);
}