package it.uniroma3.siw.silph.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Richiesta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String email;
	
	@ManyToMany
	private Set<Foto> fotografie;
	
	public Richiesta() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Set<Foto> getFotografie() {
		return fotografie;
	}

	public void setFotografie(Set<Foto> fotografie) {
		this.fotografie = fotografie;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
