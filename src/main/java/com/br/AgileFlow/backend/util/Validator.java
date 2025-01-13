package com.br.AgileFlow.backend.util;

public class Validator {
	
	public static Boolean passwordValidate(String password) {
		return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?.*\\W).{8,}$");
	}

}
