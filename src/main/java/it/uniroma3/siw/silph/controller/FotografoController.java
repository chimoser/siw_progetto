package it.uniroma3.siw.silph.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.silph.model.Fotografo;
import it.uniroma3.siw.silph.model.RicercaParole;
import it.uniroma3.siw.silph.service.AlbumService;
import it.uniroma3.siw.silph.service.FotografoService;

@Controller
public class FotografoController {
	
	@Autowired
	private FotografoService fotografoService;
	@Autowired
	private AlbumService albumService;
	
	@RequestMapping(value = "/fotografo", method = RequestMethod.GET)
	public String inserisciFotografoNelSistema(Model model) {
         model.addAttribute("fotografo",new Fotografo());
         return "/admin/formSaveFotografo";
	}
	
	@RequestMapping(value = "/fotografo", method = RequestMethod.POST)
	 public String checkAlbumInfo(@ModelAttribute("fotografo") Fotografo fotografo, 
				 Model model) {
		this.fotografoService.addFotografo(fotografo);
		model.addAttribute("fotografi", this.fotografoService.getFotografi());
		return "fotografi";
	}
	
	 @GetMapping(value="/fotografo/mostra")
	 public String getAlbums(Model model) {
		 model.addAttribute("fotografi", this.fotografoService.getFotografi());
		 return "fotografi.html";
	 }
	 
	 @GetMapping(value="/fotografo/{id}")
	 public String getAlbum(@PathVariable("id")Long id,Model model) {
		 model.addAttribute("fotografo", this.fotografoService.fotografoPerId(id));
		 model.addAttribute("albums", this.albumService.getAlbumsByPhotographer(this.fotografoService.fotografoPerId(id)));
		 return "fotografo.html";
	 }
	 
	 @RequestMapping(value="/cercaFotografoNomeCognome")
		public String cercaFotografoNomeCognome(Model model) {
			model.addAttribute("stringaRicerca", new RicercaParole());
			return "ricercaFotografoNomeCognome.html";
		}

		@RequestMapping(value="/cercaFotografoId")
		public String cercaFotografoId(Model model) {
			model.addAttribute("stringaRicerca", new RicercaParole());
			return "ricercaFotografoId.html";
		}



		@RequestMapping(value="/risultatiFotografoNomeCognome", method = RequestMethod.POST)
		public String risultatiFotografoNomeCognome(@ModelAttribute("stringaRicerca") RicercaParole sr, Model model, BindingResult br) {
			List<Fotografo> risultati;
			if(sr.getPrimaParola() == "" && sr.getSecondaParola()=="") {
				br.rejectValue("stringa1", "wrong");
				return "ricercaFotografoNomeCognome.html";
			}
			else if (sr.getPrimaParola()=="") {
				risultati=this.fotografoService.trovaFotografoPerCognome(sr.getSecondaParola());
			}else if (sr.getSecondaParola()=="") {
				risultati=this.fotografoService.trovaFotografoPerNome(sr.getPrimaParola());
			}else {
				risultati=this.fotografoService.trovaFotografoPerNomeCognome(sr.getPrimaParola(), sr.getSecondaParola());
			}
			if (risultati.size()==0) {
				br.rejectValue("stringa1", "wrong");
				return "ricercaFotografoNomeCognome.html";
			}else {
				model.addAttribute("risultati", risultati);
				return "listaFotografi.html";
			}
		}
		@RequestMapping(value="/risultatiFotografoId", method = RequestMethod.POST)
		public String risultatiFotografoId(@ModelAttribute("stringaRicerca") RicercaParole sr, Model model, BindingResult br) {
			Fotografo risultati;
			Long id;
			try {
				id = Long.parseLong(sr.getPrimaParola());
			} catch (NumberFormatException e) {
				br.rejectValue("stringa1", "wrong");
				return "ricercaFotografoId.html";
			}
			risultati = this.fotografoService.fotografoPerId(id);
			if (risultati == null) {
				br.rejectValue("stringa1", "wrong");
				return "ricercaFotografoId.html";
			}else {
				model.addAttribute("fotografo", risultati);
				return "fotografo.html";
			}
		}

		@RequestMapping(value="/fotografi")
		public String fotografi(Model model) {
			model.addAttribute("risultati", this.fotografoService.getFotografi());
			return "listaFotografi.html";

		}

		@RequestMapping(value="/fotografo/{id}", method=RequestMethod.GET)
		public String fotografo(@PathVariable("id") Long id, Model model) {
			Fotografo fotografo = this.fotografoService.fotografoPerId(id);
			if ( fotografo == null) {
				return "/fotografi";
			} else {
				model.addAttribute("fotografo", fotografo);
				return "fotografo.html";
			}
		}
}
