package it.uniroma3.siw.silph.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.silph.model.Foto;

public interface PhotoRepository extends CrudRepository<Foto, Long> {

}
