package it.uniroma3.siw.silph.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.silph.model.Foto;
import it.uniroma3.siw.silph.repository.FotoRepository;



@Service
public class FotoService {

	@Autowired
	private FotoRepository fotoRepository;
	
	public List<Foto> getAllPhotos(){
		List<Foto> photos = new ArrayList<>();
		fotoRepository.findAll().forEach(photos::add);
		return photos;
	}
	
	public Foto getPhoto(Long id) {
		Optional<Foto> t = fotoRepository.findById(id);
		if(t.isPresent())
			return t.get();
		else 
			return null;
	}
	
	public void addPhoto(Foto photo) {
		fotoRepository.save(photo);
	}
	
	public void updatePhoto(Long id, Foto photo) {
		fotoRepository.save(photo); //fa l'update
	}
	
	public void deletePhoto(Long id) {
		fotoRepository.deleteById(id);
	}
	
}
