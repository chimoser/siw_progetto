package it.uniroma3.siw.silph.controller;


import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.silph.model.Album;
import it.uniroma3.siw.silph.model.Foto;
import it.uniroma3.siw.silph.model.Fotografo;
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
	private FunzionarioService funzionarioService;
	@Autowired
	private AlbumService albumService;

	private ServletContext servletContext;
	private FotografoService fotografoService;

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
	/*		

    @RequestMapping(value ="/photos/{id}", method = RequestMethod.GET)
    public String ritornaLaPaginaConStudenteCorrispondenteAIdS (@PathVariable ("id") Long id, Model model) {
		if (id!=null) {
			model.addAttribute("photo", this.photoService.photoPerId(id));
			return "photo.html";
		}
		else {
			model.addAttribute("photos", this.photoService.tutte());
			return "photos.html";
		}			
	}*/

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String inserisciFotoNelSistema(Model model) {
		model.addAttribute("foto",new Foto());
		return "admin/formSaveFoto";
	}	

	@RequestMapping("/{id}")
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
	@RequestMapping(value="/mostra/?",method=RequestMethod.GET)
	public String inserisciFoto(@PathVariable ("id") Long id, Model model) {
			model.addAttribute("foto", this.fotoService.getPhotoById(id));
			return "admin/mostraFoto";	
	}	
	/*public String mostraFoto(@RequestParam("file") MultipartFile file, @Valid Foto photo,BindingResult bindingResult, 
			@RequestParam long fotografo, @RequestParam long album, Model model) {	
		if(bindingResult.hasErrors() || file.isEmpty()) {
			if(file.isEmpty())
				model.addAttribute("imgNonInserita",true);
			model.addAttribute("fotografi", fotografoService.tuttiFotografi());
			model.addAttribute("albums", albumService.getAll());
			return "admin/formSaveFoto";
		}
		Fotografo f=fotografoService.fotografoPerId(fotografo);
		photo.setFotografo(f);
		Album a = albumService.getAlbum(album);
		photo.setAlbum(a);
		model.addAttribute("fotografia", new Foto());
		fotoService.save(photo, file);
		return "admin/mostraFoto";
	}*/


	@RequestMapping(method=RequestMethod.PUT, value="/{id}") // i want that particular id to change
	public void updatePhoto(@RequestBody Foto photo, @PathVariable Long id) {
		fotoService.updatePhoto(id, photo);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public void deletePhoto(@PathVariable Long id) {
		fotoService.deletePhoto(id);
	}


	public String showForm(Model model, Foto foto) {
		List<Funzionario> funzionari = (List<Funzionario>) funzionarioService.tuttiFunzionari();
		List<Album> albums = (List<Album>) albumService.getAll();
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
		model.addAttribute("nomeAlbum",foto.getAlbum().getNome());

		return "/Foto/ritornaFoto";
	}


}
