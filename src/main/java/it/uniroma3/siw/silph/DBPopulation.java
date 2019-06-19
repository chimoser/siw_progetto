package it.uniroma3.siw.silph;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.silph.model.Funzionario;
import it.uniroma3.siw.silph.repository.FunzionarioRepository;


@Component
public class DBPopulation implements ApplicationRunner {
	
	@Autowired
	private FunzionarioRepository funzionarioRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.deleteAll();
		this.addAll();
	}
	
	public void deleteAll() {
		System.out.print("Cancello tutto");
		this.funzionarioRepository.deleteAll();
	}
	
	public void addAll() throws IOException, InterruptedException {
		System.out.print("Aggiungo funzionari");
		
		Funzionario f1 = new Funzionario(1L,"Silvia", "Martini", "sm", null, "ADMIN");
		String f1Password = new BCryptPasswordEncoder().encode("sm");
	    f1.setPassword(f1Password);
		this.funzionarioRepository.save(f1);

		Funzionario f2 = new Funzionario(2L,"Chiara", "Moser", "chiaramoser", null, "ADMIN");
		String f2Password = new BCryptPasswordEncoder().encode("chimospass");
	    f2.setPassword(f2Password);
		this.funzionarioRepository.save(f2);
		
		Funzionario f3= new Funzionario(3L, "Leo", "fax", "leo", new BCryptPasswordEncoder().encode("leo"), "ADMIN");
		this.funzionarioRepository.save(f3);
	}
}
