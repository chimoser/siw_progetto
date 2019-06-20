package it.uniroma3.siw.silph.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.silph.model.Funzionario;
import it.uniroma3.siw.silph.service.FunzionarioService;
import it.uniroma3.siw.silph.service.FunzionarioValidator;


@Controller
public class FunzionarioController {
	
	@Autowired
	private FunzionarioValidator funzionarioValidator;
		
	@Autowired
	private FunzionarioService funzionarioService;
	
	@RequestMapping(value = "/funzionario", method = RequestMethod.POST)
	public String inserisciFunzionarioNelSistema(@Valid 
			@ModelAttribute("funzionario") Funzionario funzionario, 
			Model model, BindingResult bindingResult) {
		
		this.funzionarioValidator.validate(funzionario, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			this.funzionarioService.add(funzionario);
			model.addAttribute("funzionari", this.funzionarioService.findAll());	
			return "funzionari.html";
		}
		else 
			return "funzionarioForm.html";
	}		
}
