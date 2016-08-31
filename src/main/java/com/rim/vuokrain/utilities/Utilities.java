package com.rim.vuokrain.utilities;

import java.sql.Date;
import java.util.Calendar;

public class Utilities {
	
	public static Date getCurrentDate() {
		
		Calendar cal = Calendar.getInstance();
		Date date = new Date(cal.getTime().getTime());		   	
		return date;
	}
	
}
