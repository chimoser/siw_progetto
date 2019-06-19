package it.uniroma3.siw.silph.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.silph.model.Funzionario;


@Component
public class FunzionarioValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Funzionario.class.equals(aClass);
	}	

	@Override
	public void validate(Object o, Errors error) {
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "id", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "pwd", "required");	
	}
}