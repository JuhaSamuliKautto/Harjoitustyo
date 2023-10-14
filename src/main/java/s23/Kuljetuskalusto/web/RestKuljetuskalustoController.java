package s23.Kuljetuskalusto.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import s23.Kuljetuskalusto.domain.Ajoneuvo;
import s23.Kuljetuskalusto.domain.AjoneuvoRepository;
//import s23.Kuljetuskalusto.domain.TyyppiRepository;

@RestController
public class RestKuljetuskalustoController {
	

	
	// @Autowired-annotaatio: Tällä annotaatiolla injektoidaan AjoneuvoRepository-riippuvuus, jota käytetään ajoneuvojen tietokantatoimintoihin.
	@Autowired
		private AjoneuvoRepository arepository;
	
	/*@Autowired 
		private TyyppiRepository trepository;*/
	
	
	// @GetMapping("/ajoneuvot"): Tämä annotaatio määrittää toiminnon, kun käyttäjä tekee GET-pyynnön osoitteeseen "/ajoneuvot".
			//Metodi hakee kaikki ajoneuvot tietokannasta arepository.findAll() -kutsulla ja palauttaa ne JSON-muodossa.
			// Tämä mahdollistaa kaikkien ajoneuvojen hakemisen REST-rajapinnan kautta.
			@GetMapping("/ajoneuvot")
			public List<Ajoneuvo> getAjoneuvot() {
		        return (List<Ajoneuvo>) arepository.findAll();
			}
			
			
			// @GetMapping("/ajoneuvot/{id}"): Tämä annotaatio määrittää toiminnon, kun käyttäjä tekee GET-pyynnön osoitteeseen "/ajoneuvo/{id}", 
			// missä {id} korvataan ajoneuvon tunnisteella. Metodi hakee ajoneuvon tietokannasta annetun ajoneuvoId-parametrin perusteella arepository.findById(ajoneuvoId) -kutsulla 
			// ja palauttaa ajoneuvon tiedot JSON-muodossa. Tämä mahdollistaa yksittäisen ajoneuvon hakemisen REST-rajapinnan kautta.
			@GetMapping("/ajoneuvot/{id}")
		    public Optional<Ajoneuvo> findAjoneuvoRest(@PathVariable("id") Long ajoneuvoId) {	
		    	return arepository.findById(ajoneuvoId);
			} 
			
			// @PostMapping("/ajoneuvot"): Tämä annotaatio määrittää toiminnon, kun käyttäjä lähettää POST-pyynnön osoitteeseen "/ajoneuvot" ja antaa 
			// JSON-muotoisen kirjatiedon @RequestBody-annotaation avulla. Metodi tallentaa ajoneuvon tietokantaan käyttäen arepository.save(ajoneuvo) -kutsua 
			// ja palauttaa tallennetun ajoneuvon tiedot JSON-muodossa. Tämä mahdollistaa uuden ajoneuvon lisäämisen REST-rajapinnan kautta.
			@PostMapping("/ajoneuvot")
			public Ajoneuvo postAjoneuvo(@RequestBody Ajoneuvo ajoneuvo) {
			    return arepository.save(ajoneuvo);
			}
			
			// muokkaa autoa
			@PutMapping("/ajoneuvot/{id}")
			public Ajoneuvo putAjoneuvo(@PathVariable Long id, @RequestBody Ajoneuvo editedAjoneuvo) {
				return arepository.save(editedAjoneuvo);	
			}
			
			// poista auto
			@DeleteMapping("/ajoneuvot/{id}")
			  public void deleteAjoneuvo(@PathVariable Long id) {
			      arepository.deleteById(id);
			  }
				
		}

