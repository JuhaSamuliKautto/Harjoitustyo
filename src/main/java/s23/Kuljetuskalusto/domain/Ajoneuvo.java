package s23.Kuljetuskalusto.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


// Entiteetti edustaa taulua relaatiotietokannassa.
//@Entity-annotaatio: Tämä annotaatio merkitsee luokan JPA-entiteetiksi, 
//mikä tarkoittaa, että tätä luokkaa voidaan käyttää tietokannassa tallennettavien tietojen edustamiseen.
@Entity
public class Ajoneuvo {
	
	// @Id-annotaatio: Tämä annotaatio merkitsee id-kenttää tietokannan pääavaimena, mikä tarkoittaa, että jokainen ajoneuvo saa yksilöllisen tunnisteen.
	@Id
	
	// @GeneratedValue-annotaatio: Tämä annotaatio määrittelee, että id-kenttä generoidaan automaattisesti tietokannassa.
		// GenerationType.AUTO tarkoittaa, että tietokantajärjestelmä valitsee automaattisesti, miten tunnisteet generoidaan.
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	
	@NotBlank(message = "Pakollinen tieto")
	@Size(min=4,max=7, message = "Rekisteritunnuksen pitää olla vähintään 4 merkkiä ja enintään 7 merkkiä.")
	private String rekisteritunnus;
	
	@NotBlank(message = "Pakollinen tieto")
	@Size(min=2,max=20, message = "Merkin pitää olla vähintään 2 merkkiä ja enintään 20 merkkiä.")
	private String merkki;
	
	@NotBlank(message = "Pakollinen tieto")
	@Size(min=2,max=30, message = "Mallin pitää olla vähintään 2 merkkiä ja enintään 30 merkkiä.")
	private String malli;
	
	@Min(value = 1923, message = "Vuoden pitää olla vähintään 1900.")
	@Max(value = 2023, message = "Vuosi ei voi olla suurempi kuin 2023.")
	private int vuosi;
	
	
	
    @ManyToOne
    
    // @JoinColumn-annotaatio: Tämä annotaatio määrittelee liittämiskentän (tyyppi) tietokantataulussa.
    @JoinColumn(name = "tyyppiid")
    
	public Tyyppi tyyppi;

    public Ajoneuvo() {}

	public Ajoneuvo(String rekisteritunnus, String merkki, String malli, int vuosi, Tyyppi tyyppi) {
	
		this.rekisteritunnus = rekisteritunnus;
		this.merkki = merkki;
		this.malli = malli;
		this.vuosi = vuosi;
		this.tyyppi = tyyppi;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRekisteritunnus() {
		return rekisteritunnus;
	}

	public void setRekisteritunnus(String rekisteritunnus) {
		this.rekisteritunnus = rekisteritunnus;
	}

	public String getMerkki() {
		return merkki;
	}

	public void setMerkki(String merkki) {
		this.merkki = merkki;
	}

	public String getMalli() {
		return malli;
	}

	public void setMalli(String malli) {
		this.malli = malli;
	}

	public int getVuosi() {
		return vuosi;
	}

	public void setVuosi(int vuosi) {
		this.vuosi = vuosi;
	}

	public Tyyppi getTyyppi() {
		return tyyppi;
	}

	public void setTyyppi(Tyyppi tyyppi) {
		this.tyyppi = tyyppi;
	}

	@Override
	public String toString() {
		if (this.tyyppi != null)
			return "Ajoneuvo [id=" + id + ", rekisteritunnus=" + rekisteritunnus + ", merkki=" + merkki + ", malli=" + malli
				+ ", vuosi=" + vuosi + ", tyyppi=" + this.getTyyppi() + "]";
		else
			return "Ajoneuvo [id=" + id + ", rekisteritunnus=" + rekisteritunnus + ", merkki=" + merkki + ", malli=" + malli
					+ ", vuosi=" + vuosi + "]";
	}


}
