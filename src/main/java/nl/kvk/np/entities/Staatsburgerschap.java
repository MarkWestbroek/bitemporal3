package nl.kvk.np.entities;

import nl.kvk.np.model.Melding;
import nl.kvk.np.model.MaterieelObject;
import nl.kvk.np.model.StaatsburgerschapRegistratieRelatie;
import nl.kvk.np.semantics.OperatieOpEntiteit;
import nl.kvk.np.semantics.OperatieOpEntiteitClass;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author dbxwwe
 * @version 1.0
 * @created 13-dec-2018 12:54:11
 */
@Entity
@Table(name="Staatsburgerschap")
@NamedQueries({
		@NamedQuery(name = "staatsburgerschap.findAll", query = "SELECT n FROM Staatsburgerschap n")
})
@OperatieOpEntiteitClass(Betreft = {NatuurlijkPersoon.class})
public class Staatsburgerschap extends MaterieelObject {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SBSCH_SEQ")
	@SequenceGenerator(name="SBSCH_SEQ", sequenceName =  "\"sbsch_pkey\"",allocationSize=1)
	private long id;

	@Column(name="NATIONALITEIT")
	private String nationaliteit;

	@Column(name="PERSOONSNUMMER")
	private String persoonsnummer;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNationaliteit() {
		return nationaliteit;
	}

	public void setNationaliteit(String nationaliteit) {
		this.nationaliteit = nationaliteit;
	}

	public String getPersoonsnummer() {
		return persoonsnummer;
	}

	public void setPersoonsnummer(String persoonsnummer) {
		this.persoonsnummer = persoonsnummer;
	}

	// public StaatsburgerschapRegistratieRelatie m_StaatsburgerschapRegistratieRelatie;

	public Staatsburgerschap(){

	}

	@Override
	public Melding HaalOp(LocalDateTime peilmoment) {
		return null;
	}

	@Override
	public Melding Bewaar() {
		return null;
	}
}