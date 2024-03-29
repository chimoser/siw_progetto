package it.uniroma3.siw.silph.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Foto {

	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private Long id;
	private String nome;
	private String link;
	
	@ManyToMany(mappedBy="fotografie")
	private List<Richiesta> richieste;
	
	public Foto(String nome, String link) {
		super();
		this.nome = nome;
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@ManyToOne
	private Fotografo fotografo;

	@ManyToOne
	private Album album;
	
	public Foto() {
		
	}
	
	public Foto(Long id, String nome, Fotografo fotografo,Album album) {
		super();
		this.id = id;
		this.nome = nome;
		this.fotografo = fotografo;
		this.album = album;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Fotografo getFotografo() {
		return fotografo;
	}

	public void setFotografo(Fotografo fotografo) {
		this.fotografo = fotografo;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public List<Richiesta> getRichieste() {
		return richieste;
	}

	public void setRichieste(List<Richiesta> richieste) {
		this.richieste = richieste;
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Foto f = (Foto) obj;
		return this.id.equals(f.getId());
	}
	
			
}
