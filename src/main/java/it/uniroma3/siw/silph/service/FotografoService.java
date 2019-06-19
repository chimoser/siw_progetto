package it.uniroma3.siw.silph.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.silph.model.Fotografo;
import it.uniroma3.siw.silph.repository.FotografoRepository;

@Service
public class FotografoService {

	@Autowired
	private FotografoRepository fotografoRepository;
	
	@Transactional
	public List<Fotografo> getFotografi(){
		List<Fotografo> fotografi = new LinkedList<>();
		this.fotografoRepository.findAll().forEach(fotografi::add);
		return fotografi;
	}
	
	@Transactional
	public Fotografo getFotografo(Long id) {
		Optional<Fotografo> f = this.fotografoRepository.findById(id);
		if( f.isPresent() )
			return f.get();
		else 
			return null;
	}
	
	@Transactional
	public void addFotografo(Fotografo fotografo) {
		this.fotografoRepository.save(fotografo);
	}
	
	@Transactional
	public void updateFotografo(Fotografo fotografo) {
		this.fotografoRepository.save(fotografo);
	}
	
	@Transactional
	public void deleteFotografo(Long id) {
		this.fotografoRepository.deleteById(id);
	}
	
}
