package it.uniroma3.siw.silph.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import it.uniroma3.siw.silph.model.Album;
import it.uniroma3.siw.silph.repository.AlbumRepository;

@Service
public class AlbumService {

	private AlbumRepository albumRepository;
	
	@Transactional
	public List<Album> getAll(){
		List<Album> albums = new ArrayList<>();
		albumRepository.findAll().forEach(albums::add);
		return albums;
	}
	
	@Transactional
	public Album getAlbum(Long id) {
		Optional<Album> a = this.albumRepository.findById(id);
		if( a.isPresent() )
			return a.get();
		else
			return null;
	}
	
	@Transactional
	public void addAlbum(Album album) {
		this.albumRepository.save(album);
		
	}
	
	@Transactional
	public void updateAlbum(Album album) {
		this.albumRepository.save(album);
	}
	
	@Transactional
	public void deleteAlbum(Long id) {
		this.albumRepository.deleteById(id);
	}
	
	
}
