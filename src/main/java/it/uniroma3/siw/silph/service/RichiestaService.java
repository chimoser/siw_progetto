package it.uniroma3.siw.silph.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.silph.model.Richiesta;
import it.uniroma3.siw.silph.repository.RichiestaRepository;

@Service
public class RichiestaService {
	
	@Autowired
	private RichiestaRepository richiestaRepository;
	
	@Transactional
	public Richiesta inserisci(Richiesta richiesta) {
		return this.richiestaRepository.save(richiesta);
	}

	@Transactional
	public List<Richiesta> tutte(){
		return (List<Richiesta>) this.richiestaRepository.findAll();
	}

	public Richiesta richiestaPerId(Long id){
		return this.richiestaRepository.findById(id).get();
	}


}
