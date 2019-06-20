package it.uniroma3.siw.silph.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.silph.model.Album;
import it.uniroma3.siw.silph.model.Fotografo;

public interface AlbumRepository extends CrudRepository<Album, Long>{

    Album save(Album entity);

    List<Album> findByFotografo(Fotografo fotografo);

	public List<Album> findByName(String nome);

}
