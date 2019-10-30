package nl.kvk.bitemporal3;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface Object {
}

public @interface HistorischObject {
}

public @interface Entiteit {
    String identificatiekolomnaam(); // de naam van de identificatie kolom
}

public @interface Entiteitgegevensobject {
    String entiteit(); // de entiteit waar deze gegevens bij horen
}

//@Target({ElementType.class)
public @interface Tabel {
    String naam(); // de naam van de tabel
}

//@Target({ElementType.METHOD)
public @interface Kolom {
    String naam(); // de naam van de kolom
    String conversie(); // de conversiemethode om de waarde naar het veldtype van de methode te converteren
}

public @interface Entiteitgegevens {
}

public @interface Identificatiekolom{
    String naam();
}





