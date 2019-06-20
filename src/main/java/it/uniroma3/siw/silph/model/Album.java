package it.uniroma3.siw.silph.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Album {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String descrizione;
	
	@ManyToOne
	private Fotografo fotografo;
	@OneToMany
	private List<Foto> fotografie;
	
	public Album() {
		
	}
	
	public Album(String name) {
		super();
		this.nome = name;	
	}
	
	public Album(Long id, String name, String description) {
		super();
		this.id = id;
		this.nome = name;
		this.descrizione = description;
		this.fotografie = new LinkedList<Foto>();
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
	public void setNome(String name) {
		this.nome = name;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String description) {
		this.descrizione = description;
	}

	public Fotografo getFotografo() {
		return fotografo;
	}

	public void setFotografo(Fotografo fotografo) {
		this.fotografo = fotografo;
	}

	public List<Foto> getFotografie() {
		return fotografie;
	}

	public void setFotografie(List<Foto> fotografie) {
		this.fotografie = fotografie;
	}
	
	
}
