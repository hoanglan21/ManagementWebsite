package com.fsoft.cme.exception;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateMethod {
	public static boolean checkNumericString(String str) {
		for(int i = 0; i < str.length(); i ++) {
			if(str.charAt(i) < '0' || str.charAt(i) > '9') {
				return false;
			}
		}
		return true;
	}
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static boolean validateEmail(String emailStr) {
		   Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
		   return matcher.find();
	}
	
	public static boolean validateUsername(String username) {
		for(int i = 0; i < username.length(); i ++) {
			if(username.charAt(i) == ' ' ) {
				return false;
			}
		}
		return true;
	}
}
