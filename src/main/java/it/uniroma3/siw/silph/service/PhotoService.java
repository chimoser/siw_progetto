package it.uniroma3.siw.silph.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.silph.model.Fotografia;
import it.uniroma3.siw.silph.repository.PhotoRepository;

@Service
public class PhotoService {

	@Autowired
	private PhotoRepository photoRepository;

	@Transactional
	public Fotografia inserisci(Fotografia fotografia) {
		return this.photoRepository.save(fotografia);
	}

	@Transactional
	public List<Fotografia> tutte(){
		return (List<Fotografia>) this.photoRepository.findAll();
	}

	public Fotografia photoPerId(Long id){
		return this.photoRepository.findById(id).get();
	}

}
