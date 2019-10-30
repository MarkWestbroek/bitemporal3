package nl.kvk.np.entities;

import nl.kvk.np.semantics.OperatieOpEntiteit;
import nl.kvk.np.semantics.OperatieOpEntiteitClass;

import javax.persistence.*;

/**
 * @author dbxwwe
 * @version 1.0
 * @created 13-dec-2018 12:54:07
 */
@Entity
@Table(name="Levensfase")
@NamedQueries({
		@NamedQuery(name = "Levensfase.findAll", query = "SELECT l FROM Levensfase l")
})
@OperatieOpEntiteitClass(BeschrijftAanvangVan = {NatuurlijkPersoon.class}, BeschrijftBeeindigingVan = {NatuurlijkPersoon.class})
public class Levensfase {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="LEVF_SEQ")
	@SequenceGenerator(name="LEVF_SEQ", sequenceName =  "\"levf_pkey\"",allocationSize=1)
	private long id;

	@Column(name="DATUMOVERLIJDEN")
	private String datumverlijden;

	@Column(name="GEBOORTEDATUM")
	private String geboortedatum;

	@Column(name="PERSOONSNUMMER")
	private String persoonnummer;

	public Levensfase(){

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDatumverlijden() {
		return datumverlijden;
	}

	public void setDatumverlijden(String datumverlijden) {
		this.datumverlijden = datumverlijden;
	}

	public String getGeboortedatum() {
		return geboortedatum;
	}

	public void setGeboortedatum(String geboortedatum) {
		this.geboortedatum = geboortedatum;
	}

	public String getPersoonnummer() {
		return persoonnummer;
	}

	public void setPersoonnummer(String persoonnummer) {
		this.persoonnummer = persoonnummer;
	}
}