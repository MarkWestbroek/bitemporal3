package nl.kvk.np.model;

import nl.kvk.np.entities.Registratie;
import nl.kvk.np.semantics.OperatieOpEntiteit;
import nl.kvk.np.semantics.OperatieOpEntiteitClass;

import java.time.LocalDateTime;

/**
 * @author dbxwwe
 * @version 1.0
 * @created 13-dec-2018 12:53:53
 */
@OperatieOpEntiteitClass(BeschrijftBeeindigingVan = {FormeelObject.class})
public class Einde extends FormeelObject {

	private LocalDateTime datum;
	public EindeRegistratieRelatie m_EindeRegistratieRelatie;

	public Einde(){

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