package nl.kvk.np.entities;

import nl.kvk.np.datalayer.DatabaseUtil;
import nl.kvk.np.datalayer.Mutatietype;
import nl.kvk.np.model.MaterieelObject;
import nl.kvk.np.model.Melding;
import nl.kvk.np.model.MaterieleEntiteit;
import nl.kvk.np.semantics.OperatieOpEntiteit;
import nl.kvk.np.semantics.OperatieOpEntiteitClass;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author dbxwwe
 * @version 1.0
 * @created 13-dec-2018 12:54:03
 */
@Entity
@Table(name="NatuurlijkPersoonRegistratieRelatie")
@NamedQueries({
		@NamedQuery(name = "natuurlijkpersoonregistratierelatie.findAll", query = "SELECT n FROM NatuurlijkPersoonRegistratieRelatie n"),
		@NamedQuery(name = "natuurlijkpersoonregistratierelatie.findOpPersoonsnummer",
				query = "SELECT n FROM NatuurlijkPersoonRegistratieRelatie n WHERE n.persoonsnummer = :persoonsnummerParameter")
})
@OperatieOpEntiteitClass(Betreft={MaterieelObject.class,NatuurlijkPersoon.class})
public class NatuurlijkPersoonRegistratieRelatie extends MaterieleEntiteit {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="NPG_SEQ")
	@SequenceGenerator(name="NPG_SEQ", sequenceName =  "\"npg_pkey\"",allocationSize=1)
	private long id;

	@Column(name="MUTATIETYPE")
	private String mutatietype;

	@Column(name="GEBEURTENISNUMMER")
	private String gebeurtenisnummer;

	@Column(name="PERSOONSNUMMER")
	private String persoonsnummer;

	// public NatuurlijkPersoonsnaamRegistratieRelatie m_NatuurlijkPersoonsnaamRegistratieRelatie;

	public NatuurlijkPersoonRegistratieRelatie() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMutatietype() {
		return mutatietype;
	}

	public void setMutatietype(String mutatietype) {
		this.mutatietype = mutatietype;
	}

	public String getGebeurtenisnummer() {
		return gebeurtenisnummer;
	}

	public void setGebeurtenisnummer(String gebeurtenisnummer) {
		this.gebeurtenisnummer = gebeurtenisnummer;
	}

	public String getPersoonsnummer() {
		return persoonsnummer;
	}

	public void setPersoonsnummer(String persoonsnummer) {
		this.persoonsnummer = persoonsnummer;
	}

	public List<NatuurlijkPersoonRegistratieRelatie> getPersoonsgebeutenissen(String persoonsnummer) {
		String queryName = "natuurlijkpersoonregistratierelatie.findOpPersoonsnummer";
		Query query = DatabaseUtil.getEntityManager().createNamedQuery(queryName);
		query.setParameter("persoonsnummerParameter", persoonsnummer);

		List<NatuurlijkPersoonRegistratieRelatie> natuurlijkPersoonRegistratieRelatieList = (List<NatuurlijkPersoonRegistratieRelatie>) query.getResultList();

		return natuurlijkPersoonRegistratieRelatieList;
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