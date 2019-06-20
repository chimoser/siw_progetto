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

import it.uniroma3.siw.silph.model.Album;
import it.uniroma3.siw.silph.model.Cliente;
import it.uniroma3.siw.silph.model.Richiesta;
import it.uniroma3.siw.silph.service.ClienteService;
import it.uniroma3.siw.silph.service.ClienteValidator;
import it.uniroma3.siw.silph.service.FotoService;
import it.uniroma3.siw.silph.service.RichiestaService;


@Controller
public class ClienteController {
	
	@Autowired
	private ClienteValidator clienteValidator;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private RichiestaService richiestaService;
	@Autowired
	private FotoService fotoService;
	
	@RequestMapping(value = "/richiestaForm", method = RequestMethod.GET)
	public String showRichiestaForm(Model model) {
		 model.addAttribute("fotografie", this.fotoService.getAllPhotos());
         model.addAttribute("richiesta",new Richiesta());
         return "formSaveRichiesta";
	}
	
	@RequestMapping(value = "/associaCliente", method = RequestMethod.GET)
	public String showClienteForm(Model model) {
         model.addAttribute("cliente",new Cliente());
         return "clienteForm";
	}
	
	@RequestMapping(value="/cliente", method = RequestMethod.GET)
	public String ritornaLaPaginaPerInserireNuovaRichiesta (@Valid @ModelAttribute("cliente") Cliente cliente, Long id, 
			Model model, BindingResult bindingResult) {
		
		this.clienteValidator.validate(cliente, bindingResult);
		
		if (!bindingResult.hasErrors()) {
			this.clienteService.inserisci(cliente);
			model.addAttribute("clienti",this.clienteService.tutte());
			model.addAttribute("richiesta", this.richiestaService.richiestaPerId(id));
			return "richiesta.html";
		}
		else
			return "clienteForm.html";

	}
	
	/*@RequestMapping(value ="/richiesta/{id}", method = RequestMethod.GET)	
	public String showRichiesta (@PathVariable ("id") Long id, Model model) {
			model.addAttribute("richiesta", this.richiestaService.richiestaPerId(id));
			return "richiesta.html";
	}*/


	/*@RequestMapping("/addCliente")
	public String addCliente (Model model) {
		model.addAttribute("cliente", new Cliente());
		return "clienteform.html";
	}*/
}
