package s23.Kuljetuskalusto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import s23.Kuljetuskalusto.domain.Ajoneuvo;
import s23.Kuljetuskalusto.domain.AjoneuvoRepository;
import s23.Kuljetuskalusto.domain.Tyyppi;
import s23.Kuljetuskalusto.domain.TyyppiRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class KuljetuskalustoRepositoryTests {

	@Autowired
	private AjoneuvoRepository arepository;
	
	@Autowired
	private TyyppiRepository trepository;
	
	@Test
	public void LuoAjoneuvo() {
		Tyyppi tyyppi = new Tyyppi ("Pakettiauto");
		trepository.save(tyyppi);
		Ajoneuvo ajoneuvo = new Ajoneuvo("AIK-678", "Mercedes-Benz", "Vito", 2020, tyyppi);
		arepository.save(ajoneuvo);
		
		// Hae tallennettu ajoneuvo ja tarkista, että se on tallennettu oikein.
		List<Ajoneuvo> savedAjoneuvot = arepository.findByMerkki("Mercedes-Benz");
		assertThat(savedAjoneuvot).isNotEmpty();
		assertThat(savedAjoneuvot.get(0).getMalli()).isEqualTo("Vito");
	}
	@Test
	public void PoistaAjoneuvo( ) {
		Tyyppi tyyppi = new Tyyppi ("Pakettiauto");
		trepository.save(tyyppi);
		Ajoneuvo ajoneuvo = new Ajoneuvo("AIK-678", "Mercedes-Benz", "Vito", 2020, tyyppi);
		arepository.save(ajoneuvo);
		
		// Poista tallennettu ajoneuvo ja tarkista, että se on poistettu onnistuneesti.
		arepository.delete(ajoneuvo);
		List<Ajoneuvo> deletedAjoneuvot = arepository.findByMalli("Vito");
		assertThat(deletedAjoneuvot).isEmpty();			
	}
	@Test
	public void EtsiAjoneuvoaRekisteritunnuksella() {
		Tyyppi tyyppi = new Tyyppi ("Pakettiauto");
		trepository.save(tyyppi);
		Ajoneuvo ajoneuvo = new Ajoneuvo("AIK-678", "Mercedes-Benz", "Vito", 2020, tyyppi);
		arepository.save(ajoneuvo);
		
		List<Ajoneuvo> savedAjoneuvot = arepository.findByRekisteritunnus("AIK-678");
		assertEquals(1, savedAjoneuvot.size()); // Tarkistetaan että tietokantaan on tallennettu yksi ajoneuvo.
        assertEquals("AIK-678", savedAjoneuvot.get(0).getRekisteritunnus()); // Tarkistetaan että rekisteritunnus on oikea.
	}
	@Test
	public void PalautaVirheJosAjoneuvoaEiOlemassa() {
		Optional<Ajoneuvo> ajoneuvo = arepository.findById((long)100);
		System.out.println("Ajoneuvo on " + ajoneuvo);
		assertThat(ajoneuvo).isNotEmpty();
	}
	
	
}
