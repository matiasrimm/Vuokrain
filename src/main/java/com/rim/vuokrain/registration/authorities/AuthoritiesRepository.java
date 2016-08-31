package com.rim.vuokrain.registration.authorities;

import java.util.List;

public interface AuthoritiesRepository {

	public void insert(Authorities authorities);

	public List<String> findAuthoritiesListByUsername(String username);
	
	public List<Authorities> findAllAuthorities();
}
