package nl.kvk.np.model;

import nl.kvk.np.entities.Registratie;
import nl.kvk.np.semantics.OperatieOpEntiteit;
import nl.kvk.np.semantics.OperatieOpEntiteitClass;

/**
 * Een ongedaanmaking maakt een Registratie of Correctie ongedaan. Dit betekent
 * dat ook de nieuwe of gewijzigde {Representaties} ongedaan gemaakt worden. Dat
 * deze op de formele tijdslijn hebben bestaan (geregistreerd geweest zijn) blijft
 * uiteraard bekend.  N.B.: wanneer een registratie een of meer {Representaties}
 * had beeindigd, moeten ook deze beeindigingen weer ongedaan gemaakt worden!
 * <i>Praktisch gezien is het nu in het handelsregister zo dat er (in het kort
 * gezegd) in plaats van de registratie (of correctie) ongedaan te maken, er een
 * herstel-registratie wordt geconstrueerd, die alle gegevens bevat van voor de
 * ongedaan gemaakte registratie.</i>
 * @author dbxwwe
 * @version 1.0
 * @created 13-dec-2018 12:54:04
 */
@OperatieOpEntiteitClass(Wijzigt = {Registratie.class})
public class Ongedaanmaking extends Registratie {

	private Registratie m_Registratie;



	public Ongedaanmaking(){

	}

}