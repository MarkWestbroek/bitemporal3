package nl.kvk.bitemporal3;

//import java.lang.annotation.*;

public class Entiteit
{
    Entiteitgegevensobject gegevens;
}

public class Entiteitgegevensobject
{
}

@Entiteit (identificatiekolomnaam = "vestigingnummer")
@Tabel (naam = "Vestigingen")
public class Vestiging extends Entiteit{

    @Identificatiekolom(naam = "vestigingnummer")
    String vestigingnummer;

    @Entiteitgegevens
    Vestiginggegevens vestiginggegevens;
}

@Entiteitgegevensobject(entiteit = "Vestiging")
public class Vestiginggegevens
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


