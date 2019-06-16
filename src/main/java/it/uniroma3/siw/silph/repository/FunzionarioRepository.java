package it.uniroma3.siw.silph.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.silph.model.Funzionario;

public interface FunzionarioRepository extends CrudRepository<Funzionario, Long>{

	public Funzionario findByNome(String nome);
}
