package it.uniroma3.siw.silph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.silph.model.Album;
import it.uniroma3.siw.silph.model.Foto;
import it.uniroma3.siw.silph.model.Fotografo;
import it.uniroma3.siw.silph.model.Funzionario;
import it.uniroma3.siw.silph.repository.AlbumRepository;
import it.uniroma3.siw.silph.repository.FotoRepository;
import it.uniroma3.siw.silph.repository.FotografoRepository;
import it.uniroma3.siw.silph.repository.FunzionarioRepository;


@Component
public class DBPopulation implements ApplicationRunner {
	
	@Autowired
	private FunzionarioRepository funzionarioRepository;
	@Autowired
	private FotoRepository fotoRepository;
	@Autowired
	private FotografoRepository fotografoRepository;
	@Autowired
	private AlbumRepository albumRepository;
	
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
		String f2Password = new BCryptPasswordEncoder().encode("chipass");
	    f2.setPassword(f2Password);
		this.funzionarioRepository.save(f2);
		
		Funzionario f3= new Funzionario(3L, "Leo", "fax", "leo", new BCryptPasswordEncoder().encode("leo"), "ADMIN");
		this.funzionarioRepository.save(f3);
		
		
	/*	Album a1 = new Album("Foto Cani");
		albumRepository.save(a1);
		List<Album> lista1  = new ArrayList<Album>();
		lista1.add(a1);
		a1.setFotografo(fotog1);
		*/
		Fotografo fotog1 = new Fotografo("Gigi","Viola");
		Fotografo fotog2 = new Fotografo("Luca","Biuan");
		//fotog2.setAlbum(lista1);
		fotografoRepository.save(fotog1);
		fotografoRepository.save(fotog2);
		
		Album a1 = new Album("Foto Cani", "https://www.terranuova.it/var/terranuova/storage/images/news/attualita/cani-ecco-come-i-cuccioli-imparano-dall-uomo/1338268-1-ita-IT/Cani-ecco-come-i-cuccioli-imparano-dall-uomo_articleimage.jpg");
		a1.setFotografo(fotog1);
		albumRepository.save(a1);
		
		Album a2 = new Album("Foto Fiori","https://bonkaday.com/wp-content/uploads/2016/04/Fiori-colorati-2.jpg");
		a2.setFotografo(fotog2);
		albumRepository.save(a2);
		
		Foto p1 = new Foto("foto1", "https://arcaplanet-static.kxscdn.com/negozi/wp-content/uploads/2019/01/cimurro-cucciolo.jpg");
		p1.setFotografo(fotog1);
		p1.setAlbum(a1);
		this.fotoRepository.save(p1);
		
		Foto p2 = new Foto("foto2", "https://phifoundation.com/wp-content/uploads/2019/01/IMMAGINE1-ADOTTARE-UN-CANE.jpg");
		p2.setFotografo(fotog1);
		p2.setAlbum(a1);
		this.fotoRepository.save(p2);
	
		Foto p3 = new Foto("Fiori1", "https://www.settemuse.it/sfondi_piante_fiori/fiori2/fiori2_093.jpg");
		p3.setFotografo(fotog2);
		p3.setAlbum(a2);
		this.fotoRepository.save(p3);
		
		Foto p4 = new Foto("Fiori2", "https://wips.plug.it/cips/paginegiallecasa/cms/2018/09/piante-che-fioriscono-in-inverno.jpg");
		p4.setFotografo(fotog2);
		p4.setAlbum(a2);
		this.fotoRepository.save(p4);
		
		
	}
}