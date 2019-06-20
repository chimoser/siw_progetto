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
import it.uniroma3.siw.silph.model.Funzionario;
import it.uniroma3.siw.silph.service.AlbumService;
import it.uniroma3.siw.silph.service.FotoService;
import it.uniroma3.siw.silph.service.FotografoService;
import it.uniroma3.siw.silph.service.FunzionarioService;

@Controller
//@RequestMapping("/foto")
public class FotoController{

    @Autowired
    private FotoService fotoService;
    @Autowired
    private FotografoService fotografoService;
    @Autowired
    private FunzionarioService funzionarioService;
    @Autowired
    private AlbumService albumService;
    
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
    
    @RequestMapping(value ="foto/save", method =RequestMethod.GET)
	public String showForm(Model model) {
		//model.addAttribute("albums", albumService.getAll());
		//model.addAttribute("funzionari", funzionarioService.tuttiFunzionari());
        model.addAttribute("foto",new Foto());
		return "admin/formSaveFoto";
	}
    
	@GetMapping("foto/{id}")
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
	
	@GetMapping(value= {"foto/mostra"})
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
	
	@RequestMapping(value ="foto/salva", method =RequestMethod.GET)
	public String showForm(Model model, Foto foto) {
		List<Funzionario> funzionari = (List<Funzionario>) funzionarioService.findAll();
		List<Album> albums = (List<Album>) albumService.getAll();
		model.addAttribute("albums", albums);
		model.addAttribute("funzionari", funzionari);
		return "/admin/formSaveFoto";
	}
	
	@GetMapping("/fotoList")
	public String ListaFoto(List<Foto> fotografie){
		return"fotografie";
	}
	
	@GetMapping("/mostraFoto")
	public String showFoto(Model model ,@RequestParam("id") Long id ){
		Foto foto = fotoService.getPhotoById(id);
		model.addAttribute("foto", foto);
		model.addAttribute("nomeAlbum",foto.getAlbum().getNome());
		
		return "/admin/mostraFoto";
	}
	
	/*the GET method is default, now I need a POST */
	@RequestMapping(method=RequestMethod.POST, value="/salva")
	public String addPhoto(@RequestBody Foto photo, Model model) {
		//model.addAttribute("fotografia", new Foto());
		fotoService.addPhoto(photo);
		model.addAttribute("fotografia", photo);
		return "admin/mostraFoto.html";		
	}
	
	
	//SILVIA
	 @RequestMapping(value = "/inserisci")
		public String inserisciFotografiaNelSistema(Model model) {
			model.addAttribute("fotografi", fotografoService.getFotografi());
	        model.addAttribute("fotografia",new Foto());
	        return "admin/formSaveFoto";
		}			
	    
	  /*  @RequestMapping(value ="/foto/{id}", method = RequestMethod.GET)
	    public String ritornaLaPaginaConStudenteCorrispondenteAIdS (@PathVariable ("id") Long id, Model model) {
			if (id!=null) {
				model.addAttribute("photo", this.fotoService.getPhotoById(id));
				return "photo.html";
			}
			else {
				model.addAttribute("photos", this.fotoService.getAllPhotos());
				return "fotografie.html";
			}			
		}
	*/
}
