package it.uniroma3.siw.silph.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.silph.model.Album;
import it.uniroma3.siw.silph.model.Foto;
import it.uniroma3.siw.silph.repository.FotoRepository;

@Service
public class FotoService {

	@Autowired
	private FotoRepository fotoRepository;
	
	@Transactional
	public List<Foto> getAllPhotos(){
		List<Foto> photos = new ArrayList<>();
		fotoRepository.findAll().forEach(photos::add);
		return photos;
	}
	
	@Transactional
	public Foto getPhotoById(Long id) {
		Optional<Foto> t = fotoRepository.findById(id);
		if(t.isPresent())
			return t.get();
		else 
			return null;
	}
	
	@Transactional
	public Foto getPhotoByNome(String nome) {
		return this.fotoRepository.findByNome(nome);
	}
	
	@Transactional
	public List<Foto> getPhotosByAlbum(Album album){
		return this.fotoRepository.findByAlbum(album);
	}
	
	@Transactional
	public void addPhoto(Foto photo) {
		fotoRepository.save(photo);
	}
	
	@Transactional
	public void updatePhoto(Long id, Foto photo) {
		fotoRepository.save(photo); //fa l'update
	}
	
	@Transactional
	public void deletePhoto(Long id) {
		fotoRepository.deleteById(id);
	}
	
}
