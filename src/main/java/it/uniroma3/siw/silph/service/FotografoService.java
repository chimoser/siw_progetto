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
	public Fotografo fotografoPerId(Long id){
		return fotografoRepository.findById(id).get();
	}
	

}
