package it.uniroma3.siw.silph.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.silph.model.Fotografo;
import it.uniroma3.siw.silph.repository.FotografoRepository;

@Service
public class FotografoService {
	
	@Autowired
	private FotografoRepository fotografoRepository;
	
	@Transactional
	public List<Fotografo> getFotografi(){
		return (List<Fotografo>) fotografoRepository.findAll();
	}
	
	@Transactional
	public void addFotografo(Fotografo fotografo) {
		this.fotografoRepository.save(fotografo);

	}
	
	@Transactional
	public Fotografo fotografoPerId(Long id){
		return fotografoRepository.findById(id).get();
	}
	@Transactional
	public List<Fotografo> trovaFotografoPerNome(String nomeFotografo){
		return this.fotografoRepository.findByNome(nomeFotografo);
	}
	
	@Transactional
	public List<Fotografo> trovaFotografoPerCognome(String cognomeFotografo){
		return this.fotografoRepository.findByCognome(cognomeFotografo);
	}
	
	@Transactional
	public List<Fotografo> trovaFotografoPerNomeCognome(String nomeFotografo, String cognomeFotografo){
		return this.fotografoRepository.findByNomeAndCognome(nomeFotografo, cognomeFotografo);
	}
	

}
