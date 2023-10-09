package s23.Kuljetuskalusto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import s23.Kuljetuskalusto.domain.Ajoneuvo;
import s23.Kuljetuskalusto.domain.AjoneuvoRepository;
import s23.Kuljetuskalusto.domain.AppUser;
import s23.Kuljetuskalusto.domain.AppUserRepository;
import s23.Kuljetuskalusto.domain.Tyyppi;
import s23.Kuljetuskalusto.domain.TyyppiRepository;




@SpringBootApplication
public class KuljetuskalustoApplication {

	private static final Logger log = LoggerFactory.getLogger(KuljetuskalustoApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(KuljetuskalustoApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner ajoneuvoDemo(AjoneuvoRepository arepository, TyyppiRepository trepository, AppUserRepository urepository) {
        return (args) -> {

          
            log.info("Tallenna pari ajoneuvoa");
        
            // Luo 4 tyyppiä
            trepository.save(new Tyyppi("Vetoauto"));
            trepository.save(new Tyyppi("Perävaunu"));
            trepository.save(new Tyyppi("Puoliperävaunu"));
            trepository.save(new Tyyppi("Jakeluauto"));
            trepository.save(new Tyyppi("Traktori"));

            // Luo 2 ajoneuvoa ja tallentaa ne 
            arepository.save(new Ajoneuvo("XLK-987", "Volvo", "FH13 2019 500 6X2 Väliteli", 2019, trepository.findByNimi("Vetoauto").get(0)));
            arepository.save(new Ajoneuvo("GGG-169", "Mercedez-Benz", "Actros", 2020, trepository.findByNimi("Jakeluauto").get(0)));
            
            // Create users: admin/admin user/user
            log.info("Create users: admin/admin user/user");
            AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
            AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
            urepository.save(user1);
            urepository.save(user2);
     
        	log.info("fetch all ajoneuvot");
        	// Näyttää kaikki tallennetut ajoneuvot käyttämällä arepository.findAll() -metodia
        	for (Ajoneuvo ajoneuvo : arepository.findAll()) {
            // tulostaa ajoneuvot lokitiedostoon (log.info(ajoneuvo.toString())).
        		log.info(ajoneuvo.toString());
        }
        
    };
        
    } 
}



