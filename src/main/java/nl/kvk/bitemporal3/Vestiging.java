package nl.kvk.bitemporal3;

@HRD_Entiteit (
        tabel = "Vestigingen",
        identificatiekolom = "vestigingnummer",
        entiteitsgegevenstabel = "Vestigingsgegevens")
public class Vestiging extends EntiteitBaseclass{

    @Identificatiekolom(naam = "vestigingnummer")
    String vestigingnummer;

    // @Entiteitgegevens
    // Vestiginggegevens vestiginggegevens;
}
