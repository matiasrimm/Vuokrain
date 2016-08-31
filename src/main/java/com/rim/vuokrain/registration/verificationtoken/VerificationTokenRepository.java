package com.rim.vuokrain.registration.verificationtoken;

import java.util.List;

public interface VerificationTokenRepository {

	public void insert(VerificationToken verificationToken);
	
	public VerificationToken findTokenByToken(String token);
	
	public VerificationToken findTokenByUsername(String username);

	public List<VerificationToken> findAllTokens();
}
