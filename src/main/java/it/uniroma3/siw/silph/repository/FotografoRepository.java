package it.uniroma3.siw.silph.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.silph.model.Fotografo;

public interface FotografoRepository extends CrudRepository<Fotografo, Long>{

	List<Fotografo> findByNome(String nome);
	List<Fotografo> findByNomeAndCognome(String nome, String Cognome);
	List<Fotografo> findByCognome(String cognome);
	List<Fotografo> findByIdAndNomeAndCognome(Long id, String nome, String cognome);
}
