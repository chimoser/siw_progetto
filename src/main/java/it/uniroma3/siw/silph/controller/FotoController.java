package it.uniroma3.siw.silph.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.silph.model.Foto;
import it.uniroma3.siw.silph.service.FotoService;

@RestController
//@RequestMapping("/pictures")
public class FotoController{

    @Autowired
    private FotoService photoService;

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
    
	@RequestMapping("/photos")
	public List<Foto> getAllPhotos(){
		return photoService.getAllPhotos();
	}
	
	@RequestMapping("/photos/{id}")
	public Foto getPhoto(@PathVariable Long id) {
		return photoService.getPhoto(id);
	}
	
	/*the GET method is default, now I need a POST */
	@RequestMapping(method=RequestMethod.POST, value="/photos")
	public void addPhoto(@RequestBody Foto photo) {
		photoService.addPhoto(photo);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/photos/{id}") // i want that particular id to change
	public void updatePhoto(@RequestBody Foto photo, @PathVariable Long id) {
		photoService.updatePhoto(id, photo);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/photos/{id}")
	public void deletePhoto(@PathVariable Long id) {
		photoService.deletePhoto(id);
	}
	
	
}
