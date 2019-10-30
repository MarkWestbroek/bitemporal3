package nl.kvk.np.entities;

import nl.kvk.np.datalayer.DatabaseUtil;
import nl.kvk.np.model.MaterieelObject;
import nl.kvk.np.model.Melding;
import nl.kvk.np.model.MaterieleEntiteit;
import nl.kvk.np.semantics.OperatieOpEntiteit;
import nl.kvk.np.semantics.OperatieOpEntiteitClass;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author dbxwwe
 * @version 1.0
 * @created 13-dec-2018 12:54:03
 */
@Entity
@Table(name="NatuurlijkPersoon")
@NamedQueries({
		@NamedQuery(name = "natuurlijkpersoon.findAll", query = "SELECT n FROM NatuurlijkPersoon n"),
		@NamedQuery(name = "natuurlijkpersoon.findPersoonViaPersoonsnummer",
				query = "SELECT n FROM NatuurlijkPersoon n WHERE n.persoonsnummer = :persoonsnummerParameter"),
})
@OperatieOpEntiteitClass(Betreft = {MaterieelObject.class})
public class NatuurlijkPersoon extends MaterieleEntiteit {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="NPERS_SEQ")
	@SequenceGenerator(name="NPERS_SEQ", sequenceName =  "\"npers_pkey\"",allocationSize=1)
	private long id;

	@Column(name="PERSOONSNUMMER")
	private String persoonsnummer;

	// public NatuurlijkPersoonRegistratieRelatie m_NatuurlijkPersoonRegistratieRelatie;

	public NatuurlijkPersoon() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPersoonsnummer() {
		return persoonsnummer;
	}

	public void setPersoonsnummer(String persoonsnummer) {
		this.persoonsnummer = persoonsnummer;
	}

	public NatuurlijkPersoon getNatuurlijkPersoon(String persoonsnummer) {
		String queryName = "natuurlijkpersoon.findPersoonViaPersoonsnummer";
		Query query = DatabaseUtil.getEntityManager().createNamedQuery(queryName);
		query.setParameter("persoonsnummerParameter", persoonsnummer);
		query.executeUpdate();

		NatuurlijkPersoon natuurlijkPersoon = (NatuurlijkPersoon) query.getSingleResult();

		return natuurlijkPersoon;
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