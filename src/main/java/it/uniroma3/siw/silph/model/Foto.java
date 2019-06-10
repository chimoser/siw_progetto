package it.uniroma3.siw.silph.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Foto {

	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private Long id;
	private String nome;
	
	@ManyToOne
	private Fotografo fotografo;

	public Foto() {
		
	}
	
	public Foto(Long id, String nome, Fotografo fotografo) {
		super();
		this.id = id;
		this.nome = nome;
		this.fotografo = fotografo;
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
	
	
}
