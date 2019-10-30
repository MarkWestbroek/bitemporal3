package nl.kvk.np;

import nl.kvk.np.datalayer.LogischeOperatiesPersoonDAO;
import nl.kvk.np.datalayer.PersoonDAO;
import nl.kvk.np.entities.*;
import nl.kvk.np.model.FormeelObject;
import nl.kvk.np.semantics.AnalyzeSemantics;

import java.time.LocalDateTime;

public class MainApplication {


    /*

    Deze Java-applicatie is gemaakt als 'ProofOfConcept', om zo snel mogelijk
    een werkende Java-applicatie op te zetten, waarin de registratie-logica
    bedacht en opgezet door Mark Westbroek en Erwin Straver is gerealiseerd.

    Alle focus is erop gezet om de correctie en verwijderingsactiews
    te implementeren. Het automatisch persisteren van de de relaties
    tussen entiteiten (niet via veld-referencies) moet nog worden
    gerealiseerd.

    Het eerste verbederpunt is om JPA volledig te gebruiken,
    en de relatie-referenties in de entity-klassen op te nemen.
    Dit voor een verbeterslag.

    Centraal in deze POC-applicatie staat de algoritmische verwerking van
    'changes', wijzigingen op de database gedurende de tijd.

     */


    PersoonDAO persoonDAO = new PersoonDAO();

    public static void main(String[] argv) {
        NatuurlijkPersoon natuurlijkPersoon = new NatuurlijkPersoon();
        System.out.println("aa");

        System.out.println("\nSemantic Annotations:");
        new AnalyzeSemantics().findAnnotation();
        System.out.println("\n");

        System.out.println("natuurlijkPersoon instanceof FormeelObject: " + (natuurlijkPersoon instanceof FormeelObject));

        // natuurlijkPersoon.setId(1);
        natuurlijkPersoon.setPersoonsnummer("987654321");


        opvoerPersoon_UseCase();

        correctiePersoonsnaam_UseCase();

        System.out.println("Print opgevoerd en gecorriceerde naam - peildatum + 1 jaar");
        System.out.println(getPersoonsnaamOpPeildatum(1).toString());

        System.out.println("Print opgevoerd en gecorriceerde naam, met alle verwerkingen");
        System.out.println(getPersoonsnaamHuidig().toString());


        verwijderenPersoonsnaam_UseCase();

        System.out.println("Print afgevoerde naam, met toekomst erin verwerkt");
        System.out.println(getPersoonsnaamHuidig().toString());

        System.out.println("Print 'afgevoerde' naam, NOG BESTAAND - peildatum + 1 jaar");
        System.out.println(getPersoonsnaamOpPeildatum(1).toString());

        System.out.println("Print 'afgevoerde' naam, NOG BESTAAND - peildatum + 2 jaar");
        System.out.println(getPersoonsnaamOpPeildatum(2).toString());


        // new PersoonDAO().saveNatuurlijkPersoon(natuurlijkPersoon, false);

    }

    public static boolean opvoerPersoon_UseCase() {
        LogischeOperatiesPersoonDAO logischeOperatiesPersoonDAO = new LogischeOperatiesPersoonDAO();
        NatuurlijkPersoon natuurlijkPersoon = new NatuurlijkPersoon();
        natuurlijkPersoon.setPersoonsnummer("10987654321");

        Persoonsnaam persoonsnaam = new Persoonsnaam();
        persoonsnaam.setPersoonsnummer(natuurlijkPersoon.getPersoonsnummer());
        persoonsnaam.setGeslachtsnaam("van Jansen");
        persoonsnaam.setVoornamen("Lodewijk");
        persoonsnaam.setVoorvoegsel_geslachtsnaam ("");
        persoonsnaam.setChangekey(1);

        Herkenning herkenning = new Herkenning();
        herkenning.setAnummer("4455");
        herkenning.setBsn("887766554433");
        herkenning.setPersoonnummer(natuurlijkPersoon.getPersoonsnummer());

        Levensfase levensfase = new Levensfase();
        levensfase.setPersoonnummer(natuurlijkPersoon.getPersoonsnummer());
        levensfase.setDatumverlijden("");
        levensfase.setGeboortedatum("02051985");

        Staatsburgerschap staatsburgerschap = new Staatsburgerschap();
        staatsburgerschap.setPersoonsnummer(natuurlijkPersoon.getPersoonsnummer());
        staatsburgerschap.setNationaliteit("Nederlands");



        return logischeOperatiesPersoonDAO.registratiePersoon(
                natuurlijkPersoon,
                persoonsnaam,
                herkenning,
                levensfase,
                staatsburgerschap,
                "0011",
                "AB_JUR_44",
                LocalDateTime.now().toString().substring(0,19));





    }

    public static boolean correctiePersoonsnaam_UseCase() {
        LogischeOperatiesPersoonDAO logischeOperatiesPersoonDAO = new LogischeOperatiesPersoonDAO();
        NatuurlijkPersoon natuurlijkPersoon = new NatuurlijkPersoon();
        natuurlijkPersoon.setPersoonsnummer("10987654321");

        Persoonsnaam persoonsnaam = new Persoonsnaam();
        persoonsnaam.setPersoonsnummer(natuurlijkPersoon.getPersoonsnummer());
        persoonsnaam.setChangekey(1);
        persoonsnaam.setGeslachtsnaam("Janzen");
        persoonsnaam.setVoornamen("Lodewijk");
        persoonsnaam.setVoorvoegsel_geslachtsnaam ("");

        return logischeOperatiesPersoonDAO.correctieNaamVanPersoon(natuurlijkPersoon,
                persoonsnaam,"JK_A87", LocalDateTime.now().plusYears(2).toString().substring(0,19));

    }

    private static Persoonsnaam getPersoonsnaamHuidig() {
        LogischeOperatiesPersoonDAO logischeOperatiesPersoonDAO = new LogischeOperatiesPersoonDAO();
        return logischeOperatiesPersoonDAO.getPersoonsnaamOpPeildatum("10987654321",null);
    }

    private static Persoonsnaam getPersoonsnaamOpPeildatum(int plusJaar) {
        LogischeOperatiesPersoonDAO logischeOperatiesPersoonDAO = new LogischeOperatiesPersoonDAO();
        return logischeOperatiesPersoonDAO.getPersoonsnaamOpPeildatum("10987654321",LocalDateTime.now().plusYears(plusJaar).toString().substring(0,19));
    }

    public static boolean verwijderenPersoonsnaam_UseCase() {
        LogischeOperatiesPersoonDAO logischeOperatiesPersoonDAO = new LogischeOperatiesPersoonDAO();
        NatuurlijkPersoon natuurlijkPersoon = new NatuurlijkPersoon();
        natuurlijkPersoon.setPersoonsnummer("10987654321");

        logischeOperatiesPersoonDAO.verwijderenVanNaamVanPersoon(
                natuurlijkPersoon,
                "CC_VERW",
                "20230801");

        return true;
    }

}
