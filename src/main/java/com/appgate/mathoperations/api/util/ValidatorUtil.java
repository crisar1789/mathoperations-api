package com.appgate.mathoperations.api.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidatorUtil {
	
	public static void validateRequest(Object request) throws ConstraintViolationException {
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Set<ConstraintViolation<Object>> violations = validator.validate(request);
		
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
	}

}
