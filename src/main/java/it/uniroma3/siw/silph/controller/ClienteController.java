package it.uniroma3.siw.silph.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.silph.model.Cliente;
import it.uniroma3.siw.silph.model.Richiesta;
import it.uniroma3.siw.silph.service.ClienteValidator;
import it.uniroma3.siw.silph.service.RichiestaService;


@Controller
public class ClienteController {
	
	@Autowired
	private ClienteValidator clienteValidator;
	@Autowired
	private RichiestaService richiestaService;
	
	@RequestMapping(value="/richiestaCliente", method = RequestMethod.GET)
	public String ritornaLaPaginaPerInserireNuovaRichiesta (@Valid @ModelAttribute("richiesta") Richiesta richiesta, 
			Model model, BindingResult bindingResult) {
		
		this.clienteValidator.validate(richiesta.getCliente(), bindingResult);
		
		if (!bindingResult.hasErrors()) {
			this.richiestaService.inserisci(richiesta);
			model.addAttribute("richieste",this.richiestaService.tutte());
			return "richieste.html";
		}
		else
			return "clienteForm.html";

	}
	
	@RequestMapping(value ="/richiesta/{id}", method = RequestMethod.GET)
	
	public String ritornaLaPaginaConStudenteCorrispondenteAIdS (@PathVariable ("id") Long id, Model model) {
		if (id!=null) {
			model.addAttribute("studente", this.richiestaService.richiestaPerId(id));
			return "richiesta.html";
		}
		else {
			model.addAttribute("studenti", this.richiestaService.tutte());
			return "richieste.html";
		}			
	}


	@RequestMapping("/addRichiesta")
	public String addStudente (Model model) {
		model.addAttribute("cliente", new Cliente());
		return "clienteform.html";
	}
}
