package it.uniroma3.siw.silph.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {

	public MainController() {
		super();
	}

	@RequestMapping(value = { "/", "/index"}, method = RequestMethod.GET)
	public String index(Model model) {
		return "home";
	}
	
	/*@RequestMapping(value = { "/admin/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = details.getAuthorities().iterator().next().getAuthority();
        model.addAttribute("username", details.getUsername());
        model.addAttribute("role", role);

        return "/admin/welcome";
    }*/
	
	@RequestMapping(value = { "/admin/welcome" }, method = RequestMethod.GET)
    public String welcome(Model model) {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = details.getAuthorities().iterator().next().getAuthority();     // get first authority
        model.addAttribute("username", details.getUsername());
        model.addAttribute("role", role);

        return "admin/welcome";
    }
	
	@RequestMapping(value="/admin")
	public String admin(){
		return "admin/welcome";
	}
	
	@RequestMapping(value={"/login"})
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/403")
	public String Error403(){
		return "error";
	}
	
	/*@RequestMapping(value = { "/admin/welcome" }, method = RequestMethod.GET)
    public String admin(Model model) {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = details.getAuthorities().iterator().next().getAuthority();
        model.addAttribute("username", details.getUsername());
        model.addAttribute("role", role);

        return "/welcome";
    }*/
}
