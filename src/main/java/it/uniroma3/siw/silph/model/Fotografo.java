package it.uniroma3.siw.silph.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Fotografo {

	@Id
	@GeneratedValue ( strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String cognome;
	
	@OneToMany(mappedBy="fotografo")
	private List<Foto> fotografie;
	
	@OneToMany(mappedBy="fotografo")
	private List<Album> album;

	public Fotografo() {
		
	}
	
	public Fotografo(Long id, String nome, String cognome) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.fotografie = new ArrayList<Foto>();
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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public List<Foto> getFotografie() {
		return fotografie;
	}

	public void setFotografie(List<Foto> fotografie) {
		this.fotografie = fotografie;
	}

	public List<Album> getAlbum() {
		return album;
	}

	public void setAlbum(List<Album> album) {
		this.album = album;
	}
	
	
}
