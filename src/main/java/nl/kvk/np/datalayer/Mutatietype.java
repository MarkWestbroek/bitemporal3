package nl.kvk.np.datalayer;

import nl.kvk.np.Constants;
import nl.kvk.np.entities.NatuurlijkPersoonRegistratieRelatie;
import nl.kvk.np.entities.NatuurlijkPersoonsnaamRegistratieRelatie;
import nl.kvk.np.semantics.OperatieOpEntiteit;
import nl.kvk.np.semantics.OperatieOpEntiteitClass;

@OperatieOpEntiteitClass(TypeWijziging = {NatuurlijkPersoonRegistratieRelatie.class, NatuurlijkPersoonsnaamRegistratieRelatie.class})
public enum Mutatietype {

    OPVOER("Opvoer"),
    AFVOER("Afvoer"),
    BETREFT("Betreft");

    private String text;

    private Mutatietype(String text) {
        this.text = text;
    }

}
