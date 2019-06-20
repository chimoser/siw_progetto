
package it.uniroma3.siw.silph.controller;


import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.silph.model.Album;
import it.uniroma3.siw.silph.model.Foto;
import it.uniroma3.siw.silph.model.Funzionario;
import it.uniroma3.siw.silph.service.AlbumService;
import it.uniroma3.siw.silph.service.FotoService;
import it.uniroma3.siw.silph.service.FotografoService;
import it.uniroma3.siw.silph.service.FunzionarioService;

@Controller
@RequestMapping("/foto")
public class FotoController{

	@Autowired
	private FotoService fotoService;
	@Autowired
	private FotografoService fotografoService;


	//usato da fotografie.html
	@RequestMapping(value="/{id}")
	public String getPhoto(@PathVariable("id") Long id, Model model) {
		if(id!=null) {
			model.addAttribute("foto", this.fotoService.getPhotoById(id));
			return "fotografia.html";
		}
		else {
			model.addAttribute("fotografie", this.fotoService.getAllPhotos());
			return "fotografie.html";
		}
	}

	//usato nella home
	@GetMapping(value="/mostra")
	public String getPhotos(Model model) {
		model.addAttribute("fotografie", this.fotoService.getAllPhotos());
		return "fotografie.html";
	}	


	@RequestMapping(method=RequestMethod.PUT, value="/{id}") // i want that particular id to change
	public void updatePhoto(@RequestBody Foto photo, @PathVariable Long id) {
		fotoService.updatePhoto(id, photo);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public void deletePhoto(@PathVariable Long id) {
		fotoService.deletePhoto(id);
	}

	/*the GET method is default, now I need a POST */
	@RequestMapping(method=RequestMethod.POST, value="/salva")
	public String addPhoto(@RequestBody Foto photo, Model model) {
		//model.addAttribute("fotografia", new Foto());
		fotoService.addPhoto(photo);
		model.addAttribute("fotografia", photo);
		return "admin/mostraFoto.html";		
	}

	 @RequestMapping(value = "/save", method = RequestMethod.GET)
		public String inserisciFotoNelSistema(Model model) {
			model.addAttribute("fotografi", fotografoService.getFotografi());
			model.addAttribute("foto",new Foto());
	         return "admin/formSaveFoto";
		}
	 
	 @RequestMapping(value = "/save", method = RequestMethod.POST)
	 public String checkAlbumInfo(@ModelAttribute("foto") Foto foto, 
				 Model model) {
		 this.fotoService.addPhoto(foto);
		 model.addAttribute("fotografie", this.fotoService.getAllPhotos());
		 return "fotografie";
	}
				

}