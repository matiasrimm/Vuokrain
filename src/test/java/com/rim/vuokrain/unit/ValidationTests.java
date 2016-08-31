package com.rim.vuokrain.unit;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import com.rim.vuokrain.forgotpassword.EmailDto;
import com.rim.vuokrain.forgotpassword.ForgotPasswordDto;
import com.rim.vuokrain.registration.RegistrationDto;

import static com.rim.vuokrain.configuration.AppConfiguration.EMAIL_VALIDATION_ERROR;
import static com.rim.vuokrain.configuration.AppConfiguration.PASSWORD_VALIDATION_ERROR;
import static com.rim.vuokrain.configuration.AppConfiguration.NEWPASSWORD_VALIDATION_ERROR;

import static org.junit.Assert.assertEquals;

public class ValidationTests {

	private static Validator validator;
	
	private String TEST_PASSWORD = "testpass";
	private String TEST_FAIL_PASSWORD = "testpsass";
	private String TEST_EMAIL = "test@mail.com";
	private String RANDOM_TEST_STRING = "blablablublu";

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void shouldFindNoPointEmailBadSyntax() {
		EmailDto eDto = new EmailDto();
		eDto.setEmail("nopoint@com");

		Set<ConstraintViolation<EmailDto>> constraintViolations =
	      validator.validate( eDto );

		assertEquals( 1, constraintViolations.size() );
		assertEquals( EMAIL_VALIDATION_ERROR,
	         constraintViolations.iterator().next().getMessage()
				);
	}
	
	@Test
	public void shouldFindNoAtEmailBadSyntax() {
		EmailDto eDto = new EmailDto();
		eDto.setEmail("no_atemail.com");

		Set<ConstraintViolation<EmailDto>> constraintViolations =
	      validator.validate( eDto );

		assertEquals( 1, constraintViolations.size() );
		assertEquals( EMAIL_VALIDATION_ERROR,
	         constraintViolations.iterator().next().getMessage()
				);
	}
	
	@Test
	public void twoPasswordsShouldMatch() {
		RegistrationDto rDto = new RegistrationDto();
		rDto.setUsername( TEST_EMAIL );
		rDto.setPassword( TEST_PASSWORD );		
		rDto.setMatchingPassword( TEST_FAIL_PASSWORD );
		rDto.setProvince( RANDOM_TEST_STRING );
		rDto.setProvince( RANDOM_TEST_STRING );
		rDto.setMunicipality( RANDOM_TEST_STRING );
		rDto.setPersonOrCompany( RANDOM_TEST_STRING );
		rDto.setName( RANDOM_TEST_STRING );
		rDto.setTelephone( RANDOM_TEST_STRING );
		
		Set<ConstraintViolation<RegistrationDto>> constraintViolations =
	      validator.validate( rDto );

		assertEquals( 1, constraintViolations.size() );
		assertEquals( PASSWORD_VALIDATION_ERROR,
	         constraintViolations.iterator().next().getMessage()
				);
	}
	
	@Test
	public void twoNewPasswordsShouldMatch() {
		ForgotPasswordDto fpDto = new ForgotPasswordDto();
		fpDto.setEmail( TEST_EMAIL );
		fpDto.setPassword( TEST_PASSWORD );		
		fpDto.setMatchingPassword( TEST_FAIL_PASSWORD );

		Set<ConstraintViolation<ForgotPasswordDto>> constraintViolations =
	      validator.validate( fpDto );

		assertEquals( 1, constraintViolations.size() );
		assertEquals( NEWPASSWORD_VALIDATION_ERROR,
	         constraintViolations.iterator().next().getMessage()
				);
	}	
}
