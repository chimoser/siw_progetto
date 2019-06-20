package it.uniroma3.siw.silph.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.silph.model.Carrello;
import it.uniroma3.siw.silph.model.Foto;
import it.uniroma3.siw.silph.service.FotoService;

@Controller
public class CarrelloController {
		@Autowired
		FotoService fotoService;
	
		@RequestMapping(value = "/carrello/{id}", method = RequestMethod.GET)
		public String aggiungiAlCarrello(@PathVariable("id") Long id, HttpServletRequest request) {
			Carrello carrello = Carrello.getCarrello();
			Foto foto = this.fotoService.getPhotoById(id);
			if (foto == null) {
				return "redirect:/foto/mostra";
			}
			else {
				carrello.addFoto(foto);
				return "redirect:/foto/"+ foto.getId();
			}
		}
		
		@RequestMapping(value = "/rimuoviCarrello/{id}", method = RequestMethod.GET)
		public String rimuoviDalCarrello(@PathVariable("id") Long id, HttpServletRequest request) {
			Carrello carrello = Carrello.getCarrello();
			Foto foto = this.fotoService.getPhotoById(id);
			if (foto == null) {
				return "redirect:/carrello";
			}
			else {
				carrello.removeFoto(foto);
				return "redirect:/carrello";
			}
		}
		
		@RequestMapping(value="/carrello")
		public String mostraCarrello(Model model) {
			model.addAttribute("carrello",Carrello.getCarrello());
			return "carrello.html";
		}
}