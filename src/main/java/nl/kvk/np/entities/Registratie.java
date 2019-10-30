package nl.kvk.np.entities;

import nl.kvk.np.datalayer.DatabaseUtil;
import nl.kvk.np.semantics.OperatieOpEntiteit;
import nl.kvk.np.semantics.OperatieOpEntiteitClass;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author dbxwwe
 * @version 1.0
 * @created 13-dec-2018 12:54:07
 */
@Entity
@Table(name="Registratie")
@NamedQueries({
		@NamedQuery(name = "registratie.findAll", query = "SELECT n FROM Registratie n"),
		@NamedQuery(name = "registratie.findGebeurtenis",
				query = "SELECT r FROM Registratie r WHERE r.gebeurtenisnummer = :gebeurtenisnummerParameter"),
		@NamedQuery(name = "registratie.findGebeurtenissen",
				query = "SELECT r FROM Registratie r WHERE r.initielegebeurtenisnummer = :initielegebeurtenisnummerParameter")
})

@OperatieOpEntiteitClass(BeschrijftAanvangVan = {NatuurlijkPersoon.class}, BeschrijftBeeindigingVan = {NatuurlijkPersoon.class}, Betreft = {NatuurlijkPersoon.class})
public class Registratie {

	@Id
	@Column(name="GEBEURTENISNUMMER")
	private String gebeurtenisnummer;

	@Column(name="INITIELEGEBEURTENISNUMMER")
	private String initielegebeurtenisnummer;

	@Column(name="GEBEURTENISTYPE")
	private String gebeurtenistype;

  	@Column(name="JURIDISCHGEBEURTENISNUMMER")
	private String juridischgebeurtenisnummer;

  	@Column(name="REGISTRATIETIJDSTIP")
	private String registratietijdstip;

  	@Column(name="SYSTEEMTIJDSTIP")
	private String systeemtijdstip;

	public Registratie(){

	}

	public String getGebeurtenisnummer() {
		return gebeurtenisnummer;
	}

	public void setGebeurtenisnummer(String gebeurtenisnummer) {
		this.gebeurtenisnummer = gebeurtenisnummer;
	}

	public String getInitielegebeurtenisnummer() {
		return initielegebeurtenisnummer;
	}

	public void setInitielegebeurtenisnummer(String initielegebeurtenisnummer) {
		this.initielegebeurtenisnummer = initielegebeurtenisnummer;
	}

	public String getGebeurtenistype() {
		return gebeurtenistype;
	}

	public void setGebeurtenistype(String gebeurtenistype) {
		this.gebeurtenistype = gebeurtenistype;
	}

	public String getJuridischgebeurtenisnummer() {
		return juridischgebeurtenisnummer;
	}

	public void setJuridischgebeurtenisnummer(String juridischgebeurtenisnummer) {
		this.juridischgebeurtenisnummer = juridischgebeurtenisnummer;
	}

	public String getRegistratietijdstip() {
		return registratietijdstip;
	}

	public void setRegistratietijdstip(String registratietijdstip) {
		this.registratietijdstip = registratietijdstip;
	}

	public String getSysteemtijdstip() {
		return systeemtijdstip;
	}

	public void setSysteemtijdstip(String systeemtijdstip) {
		this.systeemtijdstip = systeemtijdstip;
	}

	public List<Registratie> getRegistraties(String gebeurtenisnummer) {
		String queryName = "registratie.findGebeurtenissen";
		Query query = DatabaseUtil.getEntityManager().createNamedQuery(queryName);
		query.setParameter("initielegebeurtenisnummerParameter", gebeurtenisnummer);

		List<Registratie> registraties = (List<Registratie>) query.getResultList();

		return registraties;
	}

	public Registratie getRegistratie(String gebeurtenisnummer) {
		String queryName = "registratie.findGebeurtenis";
		Query query = DatabaseUtil.getEntityManager().createNamedQuery(queryName);
		query.setParameter("gebeurtenisnummerParameter", gebeurtenisnummer);

		Registratie registratie = (Registratie) query.getSingleResult();

		return registratie;
	}

}