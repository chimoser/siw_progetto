package it.uniroma3.siw.silph.model;

import java.util.HashSet;
import java.util.Set;

public class Carrello {
	public static Carrello c;
	
	private Carrello() {
		this.foto = new HashSet<>();
	}
	
	private Set<Foto> foto;

	public static Carrello getCarrello() {
		if(Carrello.c == null) {
			Carrello.c = new Carrello();
			return Carrello.c;
		} else {
			return Carrello.c;
		}
	}

	public Set<Foto> getFoto() {
		return foto;
	}

	public void setFoto(Set<Foto> foto) {
		this.foto = foto;
	}
	
	public void addFoto(Foto fot) {
		this.foto.add(fot);
	}
	
	public void removeFoto(Foto fot) {
		this.foto.remove(fot);
	}
	
	public void cleanCarrello() {
		this.foto = new HashSet<>();
	}
	
	
	
}
