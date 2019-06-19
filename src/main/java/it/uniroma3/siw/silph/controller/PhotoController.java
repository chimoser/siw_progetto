package it.uniroma3.siw.silph.controller;


import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.silph.model.Foto;
import it.uniroma3.siw.silph.service.PhotoService;

@Controller
//@RequestMapping("/pictures")
public class PhotoController{

    @Autowired
    PhotoService photoService;

    ServletContext servletContext;


   /* @RequestMapping(value="/photos/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getPhoto(@PathVariable String id) throws IOException {
        if (id != null) {
            //TODO controlli sull'id
            Long idL = Long.parseLong(id);
            File file = new File(servletContext.getRealPath("/") + "/"
                    + "pictures/" + idL + ".jpg");
           return IOUtils.toByteArray(new FileInputStream(file));
        }
        return null;

    }*/
    
    @RequestMapping(value = "/fotografia", method = RequestMethod.POST)
	public void inserisciFotografiaNelSistema(Foto foto, 
			Model model, BindingResult bindingResult) {
				
			this.photoService.inserisci(foto);
	}		
    
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
	}

}