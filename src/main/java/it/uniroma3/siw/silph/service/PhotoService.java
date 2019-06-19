package it.uniroma3.siw.silph.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.silph.model.Foto;
import it.uniroma3.siw.silph.repository.PhotoRepository;

@Service
public class PhotoService {

	@Autowired
	private PhotoRepository photoRepository;

	@Transactional
	public void inserisci(Foto fotografia) {
			this.photoRepository.save(fotografia);
	}

	@Transactional
	public List<Foto> tutte(){
		return (List<Foto>) this.photoRepository.findAll();
	}

	public Foto photoPerId(Long id){
		return this.photoRepository.findById(id).get();
	}

}
