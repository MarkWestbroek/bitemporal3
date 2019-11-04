package nl.kvk.bitemporal3;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//@Target({ElementType.class)
public @interface HRD_Entiteit {
    String tabel(); // de naam van de tabel
    String identificatiekolom(); // de naam van de identificatie kolom
    String typekolom(); // optioneel de naam van de kolom die het type aangeeft (bij MTI)
    String typediscriminator(); // optioneel de waarde van bovenstaande kolom die de het type aanduidt
    String entiteitsgegevenstabel(); // de naam van de tabel
    String entiteitsgegevenskolom(); // de naam van de kolom in the entiteitsgegevenstabel
}

//@Target({ElementType.class)
public @interface HRD_GegevensElement {
    String tabel(); // de naam van de tabel
    Tijdslijnvoorkomen tijdslijnvoorkomen(); // geen / formeel / materieel
    String tabel(); // de naam van de tabel
}

public enum Tijdslijnvoorkomen
{
    geen,
    formeel,
    materieel
}

//@Target({ElementType.class)
public @interface HRD_Relatie {
    String tabel(); // de naam van de tabel
    Tijdslijnvoorkomen tijdslijnvoorkomen(); // geen / formeel / materieel
    String bronkolom(); // de naam van de kolom met de FK naar de bron
    String doelkolom(); // de naam van de kolom met de FK naar het doel
    String typekolom(); // optioneel de naam van de kolom die het type aangeeft (bij MTI)
    String typediscriminator(); // optioneel de waarde van bovenstaande kolom die de het type aanduidt
}

//@Target({ElementType.METHOD)
public @interface HRD_Gegeven {
    String kolom(); // de naam van de kolom waarin het gegeven staat
    HRD_Gegevenstype type();
    String conversie(); // de conversiemethode om de waarde naar het veldtype van de methode te converteren
}

public enum HRD_Gegevenstype
{
    Numeriek,
    Alfanumeriek,
    Geld,
    Datum,
    DatumIncompleet,
    Tijdstip,
    JaNee,
    BSN,
    Emailadres,
    URL,
    GUID
}

public @interface HRD_Kolom
{
    String naam();
}

//? nodig?
public @interface HRD_Entiteitgegevens {
}

//?
public @interface Identificatiekolom{
    String naam();
}





