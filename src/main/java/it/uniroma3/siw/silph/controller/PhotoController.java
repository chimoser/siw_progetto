package it.uniroma3.siw.silph.controller;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw.silph.service.PhotoService;

@Controller
//@RequestMapping("/pictures")
public class PhotoController{

    @Autowired
    PhotoService photoService;

    ServletContext servletContext;


    @RequestMapping(value="/photos/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
//    @ResponseBody
    public byte[] getPhoto(@PathVariable String id) throws IOException {
        if (id != null) {
            //TODO controlli sull'id
            Long idL = Long.parseLong(id);
            File file = new File(servletContext.getRealPath("/") + "/"
                    + "pictures/" + idL + ".jpg");
           return IOUtils.toByteArray(new FileInputStream(file));
        }
        return null;

    }

}