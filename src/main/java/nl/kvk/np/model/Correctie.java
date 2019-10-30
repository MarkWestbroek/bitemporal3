package nl.kvk.np.model;

import nl.kvk.np.entities.Registratie;
import nl.kvk.np.semantics.OperatieOpEntiteit;
import nl.kvk.np.semantics.OperatieOpEntiteitClass;

/**
 * Een Correctie corrigeert de vastlegging van een of meer GegevensElementen.
 * Daarbij worden de gecorrigeerde GegevensElementen 'doorgehaald' <i>(zoals dat
 * heet)</i>. <u>De relatie tussen het gecorrigeerde en het corrigererende
 * GegevensElement (of GegevensElementen indien eenduidig samenhangend </u><i><u>-
 * lees: in een tabel -</u></i><u> vastgelegd) wordt vastgelegd.</u>
 * @author dbxwwe
 * @version 1.0
 * @created 13-dec-2018 12:53:52
 */
@OperatieOpEntiteitClass(Wijzigt = {Registratie.class})
public class Correctie extends Registratie {

	private Registratie m_Registratie;



	public Correctie(){

	}

}