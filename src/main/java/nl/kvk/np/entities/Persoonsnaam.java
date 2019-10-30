package nl.kvk.np.entities;

import nl.kvk.np.datalayer.DatabaseUtil;
import nl.kvk.np.model.Melding;
import nl.kvk.np.model.FormeelObject;
import nl.kvk.np.semantics.OperatieOpEntiteit;
import nl.kvk.np.semantics.OperatieOpEntiteitClass;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author dbxwwe
 * @version 1.0
 * @created 13-dec-2018 12:54:06
 */

@Entity
@Table(name="Persoonsnaam")
@NamedQueries({
		@NamedQuery(name = "persoonsnaam.findAll", query = "SELECT n FROM Persoonsnaam n"),
		@NamedQuery(name = "persoonsnaam.findAllForPersoon",
				query = "SELECT n FROM Persoonsnaam n WHERE n.persoonsnummer = :persoonsnummerParameter ORDER BY n.id"),
		@NamedQuery(name = "persoonsnaam.findAllForChangekey",
				query = "SELECT n FROM Persoonsnaam n WHERE n.changekey = :changekeyParameter"),
})
@OperatieOpEntiteitClass(Betreft = {NatuurlijkPersoon.class})
public class Persoonsnaam extends FormeelObject {

	// public PersoonsnaamRegistratieRelatie m_PersoonsnaamRegistratieRelatie;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PERN_SEQ")
	@SequenceGenerator(name="PERN_SEQ", sequenceName =  "\"pern_pkey\"",allocationSize=1)
	private long id;

	@Column(name="GESLACHTSNAAM")
	private String geslachtsnaam;

	@Column(name="VOORNAMEN")
	private String voornamen;

	@Column(name="VOORVOEGSEL_GESLACHTSNAAM")
	private String voorvoegsel_geslachtsnaam;

	@Column(name="PERSOONSNUMMER")
	private String persoonsnummer;

	@Column(name="CHANGEKEY")
	private long changekey;

	public Persoonsnaam(){

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGeslachtsnaam() {
		return geslachtsnaam;
	}

	public void setGeslachtsnaam(String geslachtsnaam) {
		this.geslachtsnaam = geslachtsnaam;
	}

	public String getVoornamen() {
		return voornamen;
	}

	public void setVoornamen(String voornamen) {
		this.voornamen = voornamen;
	}

	public String getVoorvoegsel_geslachtsnaam() {
		return voorvoegsel_geslachtsnaam;
	}

	public void setVoorvoegsel_geslachtsnaam(String voorvoegsel_geslachtsnaam) {
		this.voorvoegsel_geslachtsnaam = voorvoegsel_geslachtsnaam;
	}

	public String getPersoonsnummer() {
		return persoonsnummer;
	}

	public void setPersoonsnummer(String persoonsnummer) {
		this.persoonsnummer = persoonsnummer;
	}

	public long getChangekey() {
		return changekey;
	}

	public void setChangekey(long changekey) {
		this.changekey = changekey;
	}

	public List<Persoonsnaam> getPersoonsnamen(String persoonsnummer) {
		String queryName = "persoonsnaam.findAllForPersoon";
		Query query = DatabaseUtil.getEntityManager().createNamedQuery(queryName);
		query.setParameter("persoonsnummerParameter", persoonsnummer);

		List<Persoonsnaam> persoonsnamen = (List<Persoonsnaam>) query.getResultList();

		return persoonsnamen;
	}

	public Persoonsnaam getPersoonsnaamByChangekey(long  changekey) {
		String queryName = "persoonsnaam.findAllForChangekey";
		Query query = DatabaseUtil.getEntityManager().createNamedQuery(queryName);
		query.setParameter("changekeyParameter", changekey);
		Persoonsnaam persoonsnaam = (Persoonsnaam) query.getSingleResult();
		return persoonsnaam;
	}

	@Override
	public Melding HaalOp(LocalDateTime peilmoment) {
		return null;
	}

	@Override
	public Melding Bewaar() {
		return null;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(id).append("\n");
		stringBuilder.append(geslachtsnaam).append("\n");
		stringBuilder.append(voornamen).append("\n");
		stringBuilder.append(voorvoegsel_geslachtsnaam).append("\n");
		stringBuilder.append(persoonsnummer).append("\n");
		stringBuilder.append(changekey).append("\n");

		return stringBuilder.toString();
	}

}