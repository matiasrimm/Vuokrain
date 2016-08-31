package com.rim.vuokrain.forgotpassword.passwordtoken;

import java.util.List;

public interface PasswordTokenRepository {

		public void insert(PasswordToken passwordToken);
		
		public PasswordToken findTokenByToken(String token);
		
		public PasswordToken findTokenByUsername(String username);
		
		public List<PasswordToken> findAllTokens();
}
