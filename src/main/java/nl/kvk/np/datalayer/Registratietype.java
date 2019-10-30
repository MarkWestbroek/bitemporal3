package nl.kvk.np.datalayer;

import nl.kvk.np.entities.Registratie;
import nl.kvk.np.semantics.OperatieOpEntiteit;
import nl.kvk.np.semantics.OperatieOpEntiteitClass;

@OperatieOpEntiteitClass(TypeWijziging = {Registratie.class})
public enum Registratietype {

    CORRECTIE("Correctie"),
    VERWIJDERING("Verwijdering"),
    ONGEDAANMAKING("Ongedaanmaking"),
    REGISTRATIE("Registratie");

    private String text;

    private Registratietype(String text) {
        this.text = text;
    }

}
