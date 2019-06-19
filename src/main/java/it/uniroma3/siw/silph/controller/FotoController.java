package it.uniroma3.siw.silph.controller;


import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.silph.model.Album;
import it.uniroma3.siw.silph.model.Foto;
import it.uniroma3.siw.silph.service.FotoService;

@Controller
//@RequestMapping("/pictures")
public class FotoController{

    @Autowired
    private FotoService fotoService;
    @Autowired
    private Funzionario funzionarioService;
    
    private ServletContext servletContext;

/*
    @RequestMapping(value="/photos/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPhoto(@PathVariable String id) throws IOException {
        if (id != null) {
            //TODO controlli sull'id
            Long idL = Long.parseLong(id);
            File file = new File(servletContext.getRealPath("/") + "/"
                    + "photos/" + idL + ".jpg");
           return IOUtils.toByteArray(new FileInputStream(file));
        }
        return null;

    }
	*/
    
	@RequestMapping("/fotografie/{id}")
	public String getPhoto(@PathVariable Long id, Model model) {
		if(id!=null) {
			model.addAttribute("foto", this.fotoService.getPhotoById(id));
			return "fotografia.html";
		}
		else {
			model.addAttribute("fotografie", this.fotoService.getAllPhotos());
			return "fotografie.html";
		}
	}
	
	
	/*the GET method is default, now I need a POST */
	@RequestMapping(method=RequestMethod.POST, value="/fotografie")
	public String addPhoto(@RequestBody Foto photo, Model model) {
		model.addAttribute("fotografia", new Foto());
		fotoService.addPhoto(photo);
		return "fotoForm.html";		
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/fotografie/{id}") // i want that particular id to change
	public void updatePhoto(@RequestBody Foto photo, @PathVariable Long id) {
		fotoService.updatePhoto(id, photo);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/fotografie/{id}")
	public void deletePhoto(@PathVariable Long id) {
		fotoService.deletePhoto(id);
	}
	
	//new
	@GetMapping("/foto")
	public String showForm(Model model, Foto foto) {
		List<Funzionario> funzionari = (List<Funzionario>) funzionarioService.findAll();
		List<Album> albums = (List<Album>) albumService.findAll();
		model.addAttribute("albums", albums);
		model.addAttribute("funzionari", funzionari);
		return "/Foto/formFoto";
	}
	
	@GetMapping("/fotoList")
	public String ListaFoto(List<Foto> fotografie){
		return"/Foto/listaFotografie";
	}
	
	@GetMapping("/mostraFoto")
	public String showFoto(Model model ,@RequestParam("id") Long id ){
		Foto foto = fotoService.getPhotoById(id);
		model.addAttribute("foto", foto);
		model.addAttribute("nomeAlbum",foto.getAlbum().getName());
		
		return "/Foto/ritornaFoto";
	}
	
	
}
