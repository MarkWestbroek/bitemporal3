package nl.kvk.np.entities;

import nl.kvk.np.semantics.OperatieOpEntiteit;
import nl.kvk.np.semantics.OperatieOpEntiteitClass;

import javax.annotation.Generated;
import javax.persistence.*;

/**
 * @author dbxwwe
 * @version 1.0
 * @created 13-dec-2018 12:54:07
 */
@Entity
@Table(name="Herkenning")
@NamedQueries({
		@NamedQuery(name = "Herkenning.findFromPersoonsNummer", query = "SELECT h FROM Herkenning h")
})
@OperatieOpEntiteitClass(Betreft = {Registratie.class})
public class Herkenning {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="HERK_SEQ")
	@SequenceGenerator(name="HERK_SEQ", sequenceName =  "\"herk_pkey\"",allocationSize=1)
	private long id;

	@Column(name="ANUMMER")
	private String anummer;

	@Column(name="BSN")
	private String bsn;

	@Column(name="PERSOONSNUMMER")
	private String persoonnummer;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAnummer() {
		return anummer;
	}

	public void setAnummer(String anummer) {
		this.anummer = anummer;
	}

	public String getBsn() {
		return bsn;
	}

	public void setBsn(String bsn) {
		this.bsn = bsn;
	}

	public String getPersoonnummer() {
		return persoonnummer;
	}

	public void setPersoonnummer(String persoonnummer) {
		this.persoonnummer = persoonnummer;
	}

	public Herkenning(){

	}

}