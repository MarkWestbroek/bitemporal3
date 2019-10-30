package nl.kvk.np.model;

import java.time.LocalDateTime;

/**
 * @author dbxwwe
 * @version 1.0
 * @created 13-dec-2018 12:53:57
 */
public abstract class FormeelObject {

	public RepresentatieRegistratieRelatie m_RepresentatieRegistratieRelatie;

	public FormeelObject(){

	}


	/**
	 * 
	 * @param actie
	 */
	public FormeelObject(Object actie){

	}

	/**
	 * 
	 * @param peilmoment
	 */
	public abstract Melding HaalOp(LocalDateTime peilmoment);

	public abstract Melding Bewaar();

}