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
	private String name;
	private String descrizione;
	private String cover;
	
	@ManyToOne
	private Fotografo fotografo;
	@OneToMany
	private List<Foto> fotografie;
	
	public Album() {
		
	}
	
	public Album(String name,String cover) {
		super();
		this.name = name;	
		this.cover=cover;
	}
	
	public Album(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.descrizione = description;
		
		this.fotografie = new LinkedList<Foto>();
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}
	
	
}
