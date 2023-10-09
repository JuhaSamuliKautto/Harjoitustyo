package s23.Kuljetuskalusto.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import s23.Kuljetuskalusto.domain.Ajoneuvo;
import s23.Kuljetuskalusto.domain.AjoneuvoRepository;
import s23.Kuljetuskalusto.domain.TyyppiRepository;

@Controller
public class KuljetuskalustoController {

	
	// @Autowired-annotaatio: Tällä annotaatiolla injektoidaan riippuvuudet AjoneuvoRepository- ja TyyppiRepository-luokista.
	@Autowired
	private AjoneuvoRepository arepository;
	@Autowired
	private TyyppiRepository trepository;
	
	@GetMapping("/login")
    public String login() {
        return "login"; // Palauta kirjautumissivun nimi (ilman .html-päätettä)
    }
	@GetMapping("/kalustolista")
	public String kalustolista(Model model) {
		
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		model.addAttribute("name", username);
		
		model.addAttribute("ajoneuvot", arepository.findAll() );
		model.addAttribute("tyypit", trepository.findAll());
		return "kalustolista";
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/lisaaajoneuvo")
	public String lisaaAjoneuvoForm(Model model) {
		model.addAttribute("ajoneuvo", new Ajoneuvo() );
		model.addAttribute("tyypit", trepository.findAll());
		return "lisaaajoneuvo";
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/lisaaajoneuvo")
	public String lisaaajoneuvo(@Valid Ajoneuvo newAjoneuvo, BindingResult bindingResult, Model model) {
	       if (bindingResult.hasErrors()) {
	        	model.addAttribute("tyypit", trepository.findAll());
	        	return "lisaaajoneuvo";
	       }
	        arepository.save(newAjoneuvo);
	        return "redirect:/kalustolista";
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/delete/{id}")
	public String deleteAjoneuvo(@PathVariable Long id) {
		arepository.deleteById(id);
	     return "redirect:../kalustolista";
	 }
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/muokkaaajoneuvoa/{id}")
	public String muokkaaAjoneuvoaForm(@PathVariable("id") Long id, Model model) {
	    Ajoneuvo ajoneuvo = arepository.findById(id).orElse(null);
	    if (ajoneuvo != null) {
	        model.addAttribute("ajoneuvo", ajoneuvo);
	        model.addAttribute("tyypit", trepository.findAll());
	        return "muokkaaajoneuvoa";
	    } else {
	        
	        return "redirect:/kalustolista";
	    }
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/muokkaaajoneuvoa/{id}")
	public String muokkaaAjoneuvoa(@PathVariable Long id, @Valid @ModelAttribute Ajoneuvo editedAjoneuvo, BindingResult bindingResult, Model model) {
	   Ajoneuvo existingAjoneuvo = arepository.findById(id).orElse(null);

	        if (existingAjoneuvo != null) {
	        	if (bindingResult.hasErrors()) {
	                model.addAttribute("tyypit", trepository.findAll());
	                return "muokkaaajoneuvoa";
	            }
	        	
	            existingAjoneuvo.setRekisteritunnus(editedAjoneuvo.getRekisteritunnus());
	            existingAjoneuvo.setMerkki(editedAjoneuvo.getMerkki());
	            existingAjoneuvo.setMalli(editedAjoneuvo.getMalli());
	            existingAjoneuvo.setVuosi(editedAjoneuvo.getVuosi());
	            existingAjoneuvo.setTyyppi(editedAjoneuvo.getTyyppi());
	            
	            arepository.save(existingAjoneuvo);

	            return "redirect:/kalustolista";
	        }

	        return "redirect:/error";
	}

}
