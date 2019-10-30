package nl.kvk.np.model;

/**
 * Het soort {Representatie}-Registratie-relatie.
 * 
 * E�n van de volgende soorten:
 * <ul>
 * 	<li>betreft</li>
 * 	<li>aanvang</li>
 * 	<li>be�indiging</li>
 * </ul>
 * @author dbxwwe
 * @version 1.0
 * @created 13-dec-2018 12:54:09
 */
public enum SoortRepresentatieRegistratieRelatie {
;

	/**
	 * De registratie betreft een representatie.
	 */
	private int betreft;
	/**
	 * De registratie doet een representatie aanvangen,
	 */
	private int aanvang;
	/**
	 * De registratie zorgt ervoor dat de geldigheid van een representatie be�indigd
	 * wordt.
	 */
	private int beeindiging;
}