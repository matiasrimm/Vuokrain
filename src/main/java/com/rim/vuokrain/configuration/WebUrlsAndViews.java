package com.rim.vuokrain.configuration;

public class WebUrlsAndViews {

	/*
	 * EditUserInfoController
	 * */	
	public static final String EDITUSERINFO_MAP  = "/edit";
	public static final String EDITUSERINFO_VIEW  = "editUserInfo";
	
	/*
	 * ForgotPasswordController
	 * */	
	public static final String FORGOTPASS_MAP  = "/forgot";
	public static final String FORGOTPASS_VIEW  = "forgotPassword";
	
	public static final String PASSWORDSENTSUCCESSFULLY_VIEW  = "forgotPasswordSentSuccess";
	
	public static final String CHANGEFORGOTPASS_MAP  = "/confirmnewpass";	
	public static final String CHANGEFORGOTPASS_VIEW  = "forgotPasswordChange";
	
	public static final String PASSWORDCHANGEDSUCCESFULLY_VIEW  = "forgotPasswordChangedSuccess";
	
	/*
	 * HomePageController
	 * */	
	public static final String HOME_MAP  = "/";
	public static final String HOME_VIEW  = "home";
	
	/*
	 * NewAdvertController
	 * */	
	public static final String NEWADVERT_MAP  = "/newAdvert";
	public static final String NEWADVERT_VIEW  = "newAdvert";
	
	public static final String NEWADVERTSUCCESS_VIEW = "newAdvertSuccess";
	
	/*
	 * RegisterationController
	 * */	
	
	public static final String REGISTERATION_MAP = "/userRegister";
	public static final String REGISTERATION_VIEW = "userRegister";
	
	public static final String REGISTERATIONEMAILSENT_VIEW = "userRegisterResult";
	
	public static final String CONFIRMREGISTERATION_MAP = "/confirmreg";
	public static final String THANKSFORREGISTERATION_VIEW = "userRegisterThanks";
	
	/*
	 * ResendVerificationController
	 * */	
	public static final String RESENDREGISTERATIONTOKEN_MAP = "/resendRegToken";
	public static final String REGISTERATIONTOKENRESENT_VIEW = "registerationTokenResent";	
	
	/*
	 * SingleAdvertController
	 * */	
	public static final String SINGLE_ADVERT_MAP  = "/advert/{id}";
	public static final String SINGLE_ADVERT_VIEW  = "singleAdvert";
	
	/*
	 * Login / Logout
	 * */
	public static final String LOGIN_MAP  = "/login";
	public static final String LOGOUT_MAP  = "/logout";
		
	
	/*
	 * ERRORS
	 * */	
	public static final String NOSUCHTOKEN_ERROR  = "errorNoToken";
	public static final String TOKENEXPIRED_ERROR  = "errorTokenExpired";
	public static final String EMAILFAILED_ERROR = "errorEmailFailed";
}
