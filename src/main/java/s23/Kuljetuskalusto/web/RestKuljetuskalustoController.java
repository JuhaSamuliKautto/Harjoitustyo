package s23.Kuljetuskalusto.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import s23.Kuljetuskalusto.domain.Ajoneuvo;
import s23.Kuljetuskalusto.domain.AjoneuvoRepository;


public class RestKuljetuskalustoController {
	
	// @Autowired-annotaatio: Tällä annotaatiolla injektoidaan BookstoreRepository-riippuvuus, jota käytetään kirjojen tietokantatoimintoihin.
	@Autowired
		private AjoneuvoRepository arepository;
			
			// @GetMapping("/ajoneuvot"): Tämä annotaatio määrittää toiminnon, kun käyttäjä tekee GET-pyynnön osoitteeseen "/ajoneuvot".
			//Metodi hakee kaikki ajoneuvot tietokannasta arepository.findAll() -kutsulla ja palauttaa ne JSON-muodossa.
			// Tämä mahdollistaa kaikkien ajoneuvojen hakemisen REST-rajapinnan kautta.
			@GetMapping("/ajoneuvot")
			public List<Ajoneuvo> ajoneuvoListRest() {	
		        return (List<Ajoneuvo>) arepository.findAll();
			}
			
			// @GetMapping("/ajoneuvo/{id}"): Tämä annotaatio määrittää toiminnon, kun käyttäjä tekee GET-pyynnön osoitteeseen "/ajoneuvo/{id}", 
			// missä {id} korvataan ajoneuvon tunnisteella. Metodi hakee ajoneuvon tietokannasta annetun ajoneuvoId-parametrin perusteella arepository.findById(ajoneuvoId) -kutsulla 
			// ja palauttaa ajoneuvon tiedot JSON-muodossa. Tämä mahdollistaa yksittäisen ajoneuvon hakemisen REST-rajapinnan kautta.
			@GetMapping("/ajoneuvo/{id}")
		    public Optional<Ajoneuvo> findAjoneuvoRest(@PathVariable("id") Long ajoneuvoId) {	
		    	return arepository.findById(ajoneuvoId);
			} 
			
			// @PostMapping("/ajoneuvo"): Tämä annotaatio määrittää toiminnon, kun käyttäjä lähettää POST-pyynnön osoitteeseen "/ajoneuvo" ja antaa 
			// JSON-muotoisen kirjatiedon @RequestBody-annotaation avulla. Metodi tallentaa ajoneuvon tietokantaan käyttäen arepository.save(ajoneuvo) -kutsua 
			// ja palauttaa tallennetun ajoneuvon tiedot JSON-muodossa. Tämä mahdollistaa uuden ajoneuvon lisäämisen REST-rajapinnan kautta.
			@PostMapping("/ajoneuvo")
		    public Ajoneuvo save(@RequestBody Ajoneuvo ajoneuvo){
		        return arepository.save(ajoneuvo);
		        
			}	
	}
