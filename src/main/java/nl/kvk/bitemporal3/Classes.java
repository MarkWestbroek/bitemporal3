package nl.kvk.bitemporal3;

//import java.lang.annotation.*;

import java.time.*;

public abstract class FormeelObject
{
    enum FormeelObjectType{Registratie, Correctie};
    FormeelObjectType type; //het type

    FormeelObject[] correcties; //de correcties die er op dit object zijn geweest

    Doorhaling doorhaling; // optioneel

    // indien een registratie
    LocalDateTime opvoertijdstip; // afgeleid van Registratie: als het een registratie is
    LocalDateTime afvoertijdstip; // afgeleid van Registratie: optioneel als het een registratie is

    // indien een correctie
    LocalDateTime correctietijdstip; // afgeleid van Registratie: als het een correctie is

    Registratie registratie;
}

public class Doorhaling
{
    LocalDateTime doorhaaltijdstip; // afgeleid van Gebeurtenis: wanneer was deze doorhaling
    Registratie registratie;
}

//eigenlijk de Gebeurtenis
public class Registratie
{
    String gebeurtenisnummer;
}


public abstract class MaterieelObject
{
    Aanvang aanvang;
    Einde einde;
}

public class Aanvang extends FormeelObject
{
    DatumIncompleet datum;
}

public class Einde extends FormeelObject
{
    DatumIncompleet datum;
}

public class DatumIncompleet
{
    int dag;
    int maand;
    int jaar;
}


public class EntiteitBaseclass extends FormeelObject
{
    Entiteitgegevensobject gegevens;
}

public class EntiteitgegevensobjectBaseclass
{
}

@Entiteit (identificatiekolomnaam = "vestigingnummer")
@Tabel (naam = "Vestigingen")
public class Vestiging extends EntiteitBaseclass{

    @Identificatiekolom(naam = "vestigingnummer")
    String vestigingnummer;

    @Entiteitgegevens
    Vestiginggegevens vestiginggegevens;
}

@Entiteitgegevensobject (entiteit = "Vestiging")
public class Vestiginggegevens extends EntiteitgegevensobjectBaseclass
{


}

public class Handelsnaam
{

}


public class Handelsnaamgegevens
{
    @Kolom(naam = "handelsnaam")
    String handelsnaam;
}


