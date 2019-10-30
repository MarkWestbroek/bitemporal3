package nl.kvk.np.model;

import nl.kvk.np.semantics.OperatieOpEntiteit;
import nl.kvk.np.semantics.OperatieOpEntiteitClass;

import java.time.LocalDateTime;

/**
 * @author dbxwwe
 * @version 1.0
 * @created 13-dec-2018 12:53:49
 */
@OperatieOpEntiteitClass(Betreft = {FormeelObject.class})
public class Melding extends FormeelObject {


	public Melding(){

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