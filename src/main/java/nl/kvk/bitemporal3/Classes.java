package nl.kvk.bitemporal3;

//import java.lang.annotation.*;

public class EntiteitBaseclass
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


