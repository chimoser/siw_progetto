package it.uniroma3.siw.silph.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.silph.model.Album;
import it.uniroma3.siw.silph.model.Fotografo;
import it.uniroma3.siw.silph.repository.AlbumRepository;

@Service
public class AlbumService {

	@Autowired
	private AlbumRepository albumRepository;

	@Transactional
	public List<Album> getAll(){
		return (List<Album>) this.albumRepository.findAll();
	}

	@Transactional
	public Album getAlbumById(Long id) {
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

	public List<Album> getAlbumsByPhotographer(Fotografo fotografo) {
		return this.albumRepository.findByFotografo(fotografo);
	}

	@Transactional
	public List<Album> trovaAlbumNome(String nome){
		return albumRepository.findByName(nome);
	}
	

}
