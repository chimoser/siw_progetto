package it.uniroma3.siw.silph.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.silph.model.Fotografo;

public interface FotografoRepository extends CrudRepository<Fotografo, Long>{

	Fotografo save(Fotografo entity);

    List<Fotografo> findAll();

    void removeFotografoById(long id);

    Fotografo findById(long id);

    List<Fotografo> findByNomeIgnoreCaseContaining(String nome);

    List<Fotografo> findByCognomeIgnoreCaseContaining(String cognome);
    
}
