package it.uniroma3.siw.silph.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.silph.model.Album;
import it.uniroma3.siw.silph.service.AlbumService;
import it.uniroma3.siw.silph.service.FotoService;

@Controller
public class AlbumController {
	
	@Autowired
	private AlbumService albumService;
	@Autowired
	private FotoService fotoService;
	
	 @RequestMapping(value = "/album", method = RequestMethod.GET)
		public String inserisciAlbumNelSistema(Model model) {
	         model.addAttribute("album",new Album());
	         return "/admin/formSaveAlbum";
		}	

	 @GetMapping(value="/album/mostra")
	 public String getAlbums(Model model) {
		 model.addAttribute("albums", this.albumService.getAll());
		 return "albums.html";
	 }
	 
	 @GetMapping(value="/album/{id}")
	 public String getAlbum(@PathVariable("id")Long id,Model model) {
		 model.addAttribute("album", this.albumService.getAlbumById(id));
		 model.addAttribute("fotografie", this.fotoService.getPhotosByAlbum(this.albumService.getAlbumById(id)));
		 return "album.html";
	 }
}
