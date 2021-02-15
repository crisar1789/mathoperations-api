package com.appgate.mathoperations.api.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidatorUtil {
	
	/**
	 * Método encargado de validar los atributos de un objeto
	 * @param request Objeto con los datos
	 * @throws ConstraintViolationException Si ocurre un error durante la validación
	 */
	public static void validateRequest(Object request) throws ConstraintViolationException {
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Set<ConstraintViolation<Object>> violations = validator.validate(request);
		
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
	}

}
