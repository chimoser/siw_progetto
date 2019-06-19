package it.uniroma3.siw.silph.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.silph.model.Album;

@Controller
public class AlbumController {
	
	 @RequestMapping(value = "/album", method = RequestMethod.GET)
		public String inserisciAlbumNelSistema(Model model) {
	         model.addAttribute("album",new Album());
	         return "albumForm";
		}	

}
