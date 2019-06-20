package it.uniroma3.siw.silph.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.silph.model.Fotografo;

@Controller
public class FotografoController {
	
	
	
	@RequestMapping(value = "/fotografo", method = RequestMethod.GET)
	public String inserisciFotografoNelSistema(Model model) {
         model.addAttribute("fotografo",new Fotografo());
         return "/admin/formSaveFotografo";
	}

}
