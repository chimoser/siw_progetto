package it.uniroma3.siw.silph.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.silph.model.Funzionario;
import it.uniroma3.siw.silph.repository.FunzionarioRepository;


@Service
public class FunzionarioService {
	
	@Autowired
	private FunzionarioRepository funzionarioRepository;
	
	@Autowired
	private  BCryptPasswordEncoder encoder;

	@Transactional
	public Funzionario inserisci(Funzionario funzionario) {
		funzionario.setPassword(encoder.encode(funzionario.getPassword()));
		return funzionarioRepository.save(funzionario);
	}
	
	@Transactional
	public List<Funzionario> tuttiFunzionari(){
		return (List<Funzionario>) funzionarioRepository.findAll();
	}
	
	public Funzionario funzionarioPerNome(String nome) {
		return this.funzionarioRepository.findByNome(nome);
	}
}
