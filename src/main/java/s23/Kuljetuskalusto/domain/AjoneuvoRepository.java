package s23.Kuljetuskalusto.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


//@RepositoryRestResource-annotaatio: Tämä annotaatio on Spring Data RESTin käyttämä annotaatio, joka osoittaa, että tätä rajapintaa voidaan käyttää REST-palveluna.
//Se mahdollistaa entiteettien hallinnan HTTP-pyyntöjen avulla.
@RepositoryRestResource


//CrudRepository<Ajoneuvo, Long>: Tämä rajapinta laajentaa CrudRepository-rajapintaa, ja se on geneerinen.
//Se määrittää Ajoneuvo-luokan entiteetin tyypiksi ja Long-tyypiksi pääavaimen (id) tyyppi.
public interface AjoneuvoRepository extends CrudRepository<Ajoneuvo, Long> {
	
	// List<Ajoneuvo> findByTitle(@Param("rekisteritunnus") String Rekisteritunnus): Tämä on mukautettu metodi, joka on määritelty rajapinnassa.
		// Metodi mahdollistaa ajoneuvojen hakemisen tietokannasta rekisteritunnuksen perusteella.
	List<Ajoneuvo> findByRekisteritunnus(@Param("rekisteritunnus") String Rekisteritunnus);
	List<Ajoneuvo> findByMerkki(@Param("merkki") String Merkki);
	
}
