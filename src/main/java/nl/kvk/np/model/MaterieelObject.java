package nl.kvk.np.model;

import nl.kvk.np.model.Aanvang;
import nl.kvk.np.model.Einde;
import nl.kvk.np.model.FormeelObject;
import nl.kvk.np.semantics.OperatieOpEntiteit;
import nl.kvk.np.semantics.OperatieOpEntiteitClass;

/**
 * @author dbxwwe
 * @version 1.0
 * @created 13-dec-2018 12:53:59
 */
@OperatieOpEntiteitClass(BeschrijftAanvangVan = {FormeelObject.class}, BeschrijftBeeindigingVan = {FormeelObject.class})
public abstract class MaterieelObject extends FormeelObject {

	public Aanvang m_Aanvang;
	public Einde m_Einde;

	public MaterieelObject(){

	}

}