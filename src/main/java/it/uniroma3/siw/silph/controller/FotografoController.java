package it.uniroma3.siw.silph.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.silph.model.Fotografo;
import it.uniroma3.siw.silph.service.AlbumService;
import it.uniroma3.siw.silph.service.FotografoService;

@Controller
public class FotografoController {
	
	@Autowired
	private FotografoService fotografoService;
	@Autowired
	private AlbumService albumService;
	
	@RequestMapping(value = "/fotografo", method = RequestMethod.GET)
	public String inserisciFotografoNelSistema(Model model) {
         model.addAttribute("fotografo",new Fotografo());
         return "/admin/formSaveFotografo";
	}
	
	@RequestMapping(value = "/fotografo", method = RequestMethod.POST)
	 public String checkAlbumInfo(@ModelAttribute("fotografo") Fotografo fotografo, 
				 Model model) {
		this.fotografoService.addFotografo(fotografo);
		model.addAttribute("fotografi", this.fotografoService.getFotografi());
		return "fotografi";
	}
	
	 @GetMapping(value="/fotografo/mostra")
	 public String getAlbums(Model model) {
		 model.addAttribute("fotografi", this.fotografoService.getFotografi());
		 return "fotografi.html";
	 }
	 
	 @GetMapping(value="/fotografo/{id}")
	 public String getAlbum(@PathVariable("id")Long id,Model model) {
		 model.addAttribute("fotografo", this.fotografoService.fotografoPerId(id));
		 model.addAttribute("albums", this.albumService.getAlbumsByPhotographer(this.fotografoService.fotografoPerId(id)));
		 return "fotografo.html";
	 }

}
