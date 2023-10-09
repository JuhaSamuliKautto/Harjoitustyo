package s23.Kuljetuskalusto.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Tyyppi {

	@Id
	
	// @GeneratedValue-annotaatio: Tämä annotaatio määrittelee, että id-kenttä generoidaan automaattisesti tietokannassa.
		// GenerationType.AUTO tarkoittaa, että tietokantajärjestelmä valitsee automaattisesti, miten tunnisteet generoidaan.
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long tyyppiid;
	private String nimi;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tyyppi")
	private List<Ajoneuvo> ajoneuvot;
	
	public Tyyppi() {}

	public Tyyppi(String nimi) {
		super();
		this.nimi = nimi;
	}

	public long getTyyppiid() {
		return tyyppiid;
	}

	public void setTyyppiid(long tyyppiid) {
		this.tyyppiid = tyyppiid;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public List<Ajoneuvo> getAjoneuvot() {
		return ajoneuvot;
	}

	public void setAjoneuvot(List<Ajoneuvo> ajoneuvot) {
		this.ajoneuvot = ajoneuvot;
	}

	@Override
	public String toString() {
		return "Tyyppi [tyyppiid=" + tyyppiid + ", nimi=" + nimi + "]";
	}

}
