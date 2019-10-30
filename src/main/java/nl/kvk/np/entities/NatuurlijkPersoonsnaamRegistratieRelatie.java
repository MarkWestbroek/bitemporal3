package nl.kvk.np.entities;

import nl.kvk.np.datalayer.DatabaseUtil;
import nl.kvk.np.model.MaterieelObject;
import nl.kvk.np.model.RepresentatieRegistratieRelatie;
import nl.kvk.np.semantics.OperatieOpEntiteit;
import nl.kvk.np.semantics.OperatieOpEntiteitClass;

import javax.persistence.*;
import java.util.List;

/**
 * @author dbxwwe
 * @version 1.0
 * @created 13-dec-2018 12:54:04
 */
@Entity
@Table(name="NatuurlijkPersoonsnaamRegistratieRelatie")
@NamedQueries({
		@NamedQuery(name = "natuurlijkpersoonsnaamregistratierelatie.findAll", query = "SELECT n FROM NatuurlijkPersoonsnaamRegistratieRelatie n"),
		@NamedQuery(name = "natuurlijkpersoonsnaamregistratierelatie.findPerGebeurtenisId",
				query = "SELECT n FROM NatuurlijkPersoonsnaamRegistratieRelatie n WHERE n.gebeurtenisnummer = :gebeurtenisnummerParameter"),
		@NamedQuery(name = "natuurlijkpersoonsnaamregistratierelatie.findPerPersoonsChangekey",
				query = "SELECT n FROM NatuurlijkPersoonsnaamRegistratieRelatie n WHERE n.changekey = :changekeyParameter")

})
@OperatieOpEntiteitClass(Betreft = {MaterieelObject.class,NatuurlijkPersoon.class})
public class NatuurlijkPersoonsnaamRegistratieRelatie extends RepresentatieRegistratieRelatie {


	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NPRR_SEQ")
	@SequenceGenerator(name = "NPRR_SEQ", sequenceName = "\"nprr_pkey\"", allocationSize = 1)
	private long id;

	@Column(name = "MUTATIETYPE")
	private String mutatietype;

	@Column(name = "GEBEURTENISNUMMER")
	private String gebeurtenisnummer;

	@Column(name = "CHANGEKEY")
	private long changekey;

	public NatuurlijkPersoonsnaamRegistratieRelatie() {

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

	public long getChangekey() {
		return changekey;
	}

	public void setChangekey(long changekey) {
		this.changekey = changekey;
	}

	public NatuurlijkPersoonsnaamRegistratieRelatie getFirstNatuurlijkPersoonsnaamRegistratieRelaties(String gebeurtenisnummer) {
		String queryName = "natuurlijkpersoonsnaamregistratierelatie.findPerGebeurtenisId";
		Query query = DatabaseUtil.getEntityManager().createNamedQuery(queryName);
		query.setParameter("gebeurtenisnummerParameter", gebeurtenisnummer);

		List<NatuurlijkPersoonsnaamRegistratieRelatie> list = query.getResultList();
		if (list == null || list.isEmpty()) {
			return null;
		}
		NatuurlijkPersoonsnaamRegistratieRelatie natuurlijkPersoonsnaamRegistratieRelatie = (NatuurlijkPersoonsnaamRegistratieRelatie) query.getSingleResult();

		return natuurlijkPersoonsnaamRegistratieRelatie;
	}

	public NatuurlijkPersoonsnaamRegistratieRelatie getNatuurlijkPersoonsnaamRegistratieRelatiePerChangekey(long changekey) {
		String queryName = "natuurlijkpersoonsnaamregistratierelatie.findPerPersoonsChangekey";
		Query query = DatabaseUtil.getEntityManager().createNamedQuery(queryName);
		query.setParameter("changekeyParameter", changekey);

		NatuurlijkPersoonsnaamRegistratieRelatie natuurlijkPersoonsnaamRegistratieRelatie = (NatuurlijkPersoonsnaamRegistratieRelatie) query.getSingleResult();

		return natuurlijkPersoonsnaamRegistratieRelatie;
	}
}