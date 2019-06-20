package it.uniroma3.siw.silph.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.silph.model.Album;
import it.uniroma3.siw.silph.model.Foto;
import it.uniroma3.siw.silph.model.Fotografo;

public interface FotoRepository extends CrudRepository<Foto, Long>  {

	public Foto findByNome(String nome);
	
	Foto save(Foto entity);

    List<Foto> findAll();

    void removeFotoById(long id);

    List<Foto> findByNomeIgnoreCaseContaining(String nome);

    Optional<Foto> findById(Long id);

    List<Foto> findByFotografo(Fotografo fotografo);

    List<Foto> findByAlbum(Album album);
}