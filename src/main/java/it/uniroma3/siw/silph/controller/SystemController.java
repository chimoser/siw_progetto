package it.uniroma3.siw.silph.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw.silph.model.Foto;

@Controller
public class SystemController {

	@RequestMapping(value="/i")
	public String paginaIniziale(Model model) {
		Foto f = new Foto();
		f.setNome("ciaooo");
		f.setLink("http://www.patriaindipendente.it/wp-content/uploads/2017/07/magritte--768x531.jpg");
		model.addAttribute("foto", f);
		return "prova.html";
	}
}
