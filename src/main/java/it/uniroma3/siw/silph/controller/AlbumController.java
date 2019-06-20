package it.uniroma3.siw.silph.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.silph.model.Album;
import it.uniroma3.siw.silph.model.RicercaParole;
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

	@RequestMapping(value = "/album", method = RequestMethod.POST)
	 public String checkAlbumInfo(@ModelAttribute("album") Album album, 
				 Model model) {

		this.albumService.addAlbum(album);
		 model.addAttribute("albums", this.albumService.getAll());
		 return "albums";
	}
	 
	 @GetMapping(value="/album/mostra")
	 public String getAlbums(Model model) {
		 model.addAttribute("albums", this.albumService.getAll());
		 return "albums.html";
	 }
	 
	// @GetMapping(value="/album/{id}")
	 @RequestMapping(value = "/album{id}", method = RequestMethod.POST)

	 public String getAlbum(@PathVariable("id")Long id,Model model) {
		 model.addAttribute("album", this.albumService.getAlbumById(id));
		 model.addAttribute("fotografie", this.fotoService.getPhotosByAlbum(this.albumService.getAlbumById(id)));
		 return "album.html";
	 }
	 
	 @RequestMapping(value = "/ricercaAlbum")
		public String ricercaAlbum() {
			return "ricercaAlbum.html";
		}
		
		@RequestMapping(value = "/cercaAlbumPerNome")
		public String ricercaAlbumPerNome(Model model) {
			model.addAttribute("stringaRicerca", new RicercaParole());
			return "ricercaAlbumNome";
		}
		
		@RequestMapping(value = "/cercaAlbumPerId")
		public String ricercaAlbumPerId(Model model) {
			model.addAttribute("stringaRicerca", new RicercaParole());
			return "ricercaAlbumId";
		}
		
		@RequestMapping(value = "/risultatiAlbumId", method = RequestMethod.POST)
		public String risultatoRicercaPerId(@Valid @ModelAttribute("stringaRicerca") RicercaParole stringaRicerca,Model model, BindingResult bd) {
			
			Long id = 0L;
			
			try {
				id = Long.parseLong(stringaRicerca.getPrimaParola());
			}catch (NumberFormatException e) {
				bd.rejectValue("stringa1", "wrong");
				return "ricercaAlbumId.html";
			}
			
			Album album = this.albumService.getAlbumById(id);
			
			if(album!=null) {
				model.addAttribute("album", album);
				return "album.html";
			}
			else {
				bd.rejectValue("stringa1", "wrong");
				return "ricercaAlbumId.html";
			}
		}
		
		@RequestMapping(value = "/risultatiAlbumNome", method = RequestMethod.POST)
		public String risultatoRicercaNome(@Valid @ModelAttribute("stringaRicerca") RicercaParole stringaRicerca, Model model, BindingResult bd) {
			
			List<Album> albums = this.albumService.trovaAlbumNome(stringaRicerca.getPrimaParola());
			if(!albums.isEmpty()) {
				model.addAttribute("risultati", albums);
				return "albums.html"; 
			}
			else {
				bd.rejectValue("stringa1", "wrong");
				return "ricercaAlbumNome.html";
			}
		}
		
}
