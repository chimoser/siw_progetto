package it.uniroma3.siw.silph.service;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.silph.model.Funzionario;


@Component
public class RichiestaValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClazz) {
		return Funzionario.class.equals(aClazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","required");
	}

}