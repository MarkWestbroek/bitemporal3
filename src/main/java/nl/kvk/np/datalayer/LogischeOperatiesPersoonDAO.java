package nl.kvk.np.datalayer;

import nl.kvk.np.entities.*;

import java.time.LocalDateTime;
import java.util.List;

public class LogischeOperatiesPersoonDAO extends PersoonDAO {


    public boolean registratiePersoon(
            NatuurlijkPersoon natuurlijkPersoon,
            Persoonsnaam persoonsnaam,
            Herkenning herkenning,
            Levensfase levensfase,
            Staatsburgerschap staatsburgerschap,
            String gebeurtenisnummer,
            String juridischgebeurtenisnummer,
            String registratietijdstip) {

            /* Query q = entityManager.createNamedQuery("SELECT g FROM gebeurtenis g WHERE g.gebeurtenisnummer=" + gebeurtenisnummer.trim());
            Registratie r =  (Registratie) q.getSingleResult();
            if (r != null) {
                return false;
            }
            */

        beginTr();


        entityManager.persist(natuurlijkPersoon);
        persoonsnaam.setPersoonsnummer(natuurlijkPersoon.getPersoonsnummer());
        endTr();
        beginTr();
        entityManager.persist(persoonsnaam);
        levensfase.setPersoonnummer(natuurlijkPersoon.getPersoonsnummer());
        entityManager.persist(levensfase);
        staatsburgerschap.setPersoonsnummer(natuurlijkPersoon.getPersoonsnummer());
        entityManager.persist(staatsburgerschap);

        herkenning.setPersoonnummer(natuurlijkPersoon.getPersoonsnummer());
        entityManager.persist(herkenning);



        Registratie registratie = new Registratie();
        registratie.setGebeurtenisnummer(natuurlijkPersoon.getPersoonsnummer()+ "_g");
        registratie.setInitielegebeurtenisnummer(registratie.getGebeurtenisnummer());
        registratie.setJuridischgebeurtenisnummer(juridischgebeurtenisnummer);
        registratie.setGebeurtenistype(Registratietype.REGISTRATIE.name());
        registratie.setSysteemtijdstip(LocalDateTime.now().toString().substring(0,19));
        registratie.setRegistratietijdstip(registratietijdstip);
        entityManager.persist(registratie);

        endTr();
        beginTr();

        NatuurlijkPersoonsnaamRegistratieRelatie natuurlijkPersoonsnaamRegistratieRelatie = new
                NatuurlijkPersoonsnaamRegistratieRelatie();
        natuurlijkPersoonsnaamRegistratieRelatie.setGebeurtenisnummer(registratie.getGebeurtenisnummer());
        natuurlijkPersoonsnaamRegistratieRelatie.setMutatietype(Mutatietype.OPVOER.name());
        natuurlijkPersoonsnaamRegistratieRelatie.setChangekey(persoonsnaam.getChangekey());
        entityManager.persist(natuurlijkPersoonsnaamRegistratieRelatie);

        NatuurlijkPersoonRegistratieRelatie natuurlijkPersoonRegistratieRelatie = new NatuurlijkPersoonRegistratieRelatie();
        natuurlijkPersoonRegistratieRelatie.setGebeurtenisnummer(registratie.getGebeurtenisnummer());
        natuurlijkPersoonRegistratieRelatie.setMutatietype(Mutatietype.OPVOER.name());
        natuurlijkPersoonRegistratieRelatie.setPersoonsnummer(natuurlijkPersoon.getPersoonsnummer());
        entityManager.persist(natuurlijkPersoonRegistratieRelatie);


        endTr();




        return true;
    }


    public boolean correctieNaamVanPersoon(
            NatuurlijkPersoon natuurlijkPersoon,
            Persoonsnaam persoonsnaam,
            String juridischgebeurtenisnummer,
            String registratietijdstip) {

        beginTr();

        persoonsnaam.setPersoonsnummer(natuurlijkPersoon.getPersoonsnummer());



        NatuurlijkPersoonsnaamRegistratieRelatie natuurlijkPersoonsnaamRegistratieRelatie = new NatuurlijkPersoonsnaamRegistratieRelatie();
        NatuurlijkPersoonsnaamRegistratieRelatie natuurlijkPersoonsnaamRegistratieRelatie1 = natuurlijkPersoonsnaamRegistratieRelatie.getNatuurlijkPersoonsnaamRegistratieRelatiePerChangekey(persoonsnaam.getChangekey());

        Registratie registratie = new Registratie();
        Registratie registratie2 = registratie.getRegistratie(natuurlijkPersoonsnaamRegistratieRelatie1.getGebeurtenisnummer());

        String initieelGebeurtenisNummer = registratie2.getInitielegebeurtenisnummer();

        Registratie registratie3 = new Registratie();
        registratie3.setGebeurtenisnummer(initieelGebeurtenisNummer + "_gg");
        registratie3.setInitielegebeurtenisnummer(initieelGebeurtenisNummer);
        registratie3.setJuridischgebeurtenisnummer(juridischgebeurtenisnummer);
        registratie3.setGebeurtenistype(Registratietype.CORRECTIE.name());
        registratie3.setSysteemtijdstip(LocalDateTime.now().toString().substring(0,19));
        registratie3.setRegistratietijdstip(registratietijdstip);
        entityManager.persist(registratie3);

        endTr();
        beginTr();

        persoonsnaam.setChangekey(persoonsnaam.getChangekey() + 1);
        entityManager.persist(persoonsnaam);

        endTr();
        beginTr();

        NatuurlijkPersoonsnaamRegistratieRelatie natuurlijkPersoonsnaamRegistratieRelatie2 = new
                NatuurlijkPersoonsnaamRegistratieRelatie();
        natuurlijkPersoonsnaamRegistratieRelatie2.setGebeurtenisnummer(registratie3.getGebeurtenisnummer());
        natuurlijkPersoonsnaamRegistratieRelatie2.setMutatietype(Mutatietype.BETREFT.name());
        natuurlijkPersoonsnaamRegistratieRelatie2.setChangekey(persoonsnaam.getChangekey());
        entityManager.persist(natuurlijkPersoonsnaamRegistratieRelatie2);

        endTr();

        return true;

    }

    public boolean verwijderenVanNaamVanPersoon(
            NatuurlijkPersoon natuurlijkPersoon,
            String juridischgebeurtenisnummer,
            String registratietijdstip) {

        beginTr();

        NatuurlijkPersoonRegistratieRelatie natuurlijkPersoonRegistratieRelatie = new NatuurlijkPersoonRegistratieRelatie();
        List<NatuurlijkPersoonRegistratieRelatie> natuurlijkPersoonRegistratieRelatieList =
                natuurlijkPersoonRegistratieRelatie.getPersoonsgebeutenissen(natuurlijkPersoon.getPersoonsnummer());


        Registratie registratie = new Registratie();
        Registratie registratie2 = registratie.getRegistratie(natuurlijkPersoonRegistratieRelatieList.get(0).getGebeurtenisnummer());

        String initieelGebeurtenisNummer = registratie2.getInitielegebeurtenisnummer();

        Registratie registratie3 = new Registratie();
        registratie3.setGebeurtenisnummer(initieelGebeurtenisNummer + "_gg_ggg");
        registratie3.setInitielegebeurtenisnummer(initieelGebeurtenisNummer);
        registratie3.setJuridischgebeurtenisnummer(juridischgebeurtenisnummer);
        registratie3.setGebeurtenistype(Registratietype.VERWIJDERING.name());
        registratie3.setSysteemtijdstip(LocalDateTime.now().plusYears(3).toString().substring(0,19));
        registratie3.setRegistratietijdstip(registratietijdstip);
        entityManager.persist(registratie3);

        endTr();
        beginTr();

        NatuurlijkPersoonRegistratieRelatie natuurlijkPersoonRegistratieRelatie1 = new
                NatuurlijkPersoonRegistratieRelatie();
        natuurlijkPersoonRegistratieRelatie1.setGebeurtenisnummer(registratie3.getGebeurtenisnummer());
        natuurlijkPersoonRegistratieRelatie1.setMutatietype(Mutatietype.AFVOER.name());
        natuurlijkPersoonRegistratieRelatie1.setPersoonsnummer(natuurlijkPersoon.getPersoonsnummer());
        entityManager.persist(natuurlijkPersoonRegistratieRelatie1);

        endTr();

        return true;

    }

    public Persoonsnaam getPersoonsnaamOpPeildatum(String persoonsnummer, String peildatum) {

        NatuurlijkPersoonRegistratieRelatie natuurlijkPersoonRegistratieRelatie = new NatuurlijkPersoonRegistratieRelatie();
        List<NatuurlijkPersoonRegistratieRelatie> natuurlijkPersoonRegistratieRelatieList = natuurlijkPersoonRegistratieRelatie.getPersoonsgebeutenissen(persoonsnummer);


        Registratie registratie = new Registratie();

        Registratie registratie2 = registratie.getRegistratie(natuurlijkPersoonRegistratieRelatieList.get(0).getGebeurtenisnummer());


        List<Registratie> regs = registratie.getRegistraties(registratie2.getInitielegebeurtenisnummer());


        Persoonsnaam persoonsnaamQ = new Persoonsnaam();

        int index = 0;
        for (Registratie registratieNu : regs) {
            String regNu = registratieNu.getRegistratietijdstip();
            if ((peildatum != null) && (! peildatum.isEmpty()) &&  (registratieNu.getRegistratietijdstip().compareTo(peildatum) > 0)) {
                break;
            }
            NatuurlijkPersoonsnaamRegistratieRelatie natuurlijkPersoonsnaamRegistratieRelatie3 = new
                    NatuurlijkPersoonsnaamRegistratieRelatie();

            NatuurlijkPersoonsnaamRegistratieRelatie natuurlijkPersoonsnaamRegistratieRelatie4 =
                    natuurlijkPersoonsnaamRegistratieRelatie3.getFirstNatuurlijkPersoonsnaamRegistratieRelaties(registratieNu.getGebeurtenisnummer());
            Persoonsnaam persoonsnaam = null;
            if (natuurlijkPersoonsnaamRegistratieRelatie4 != null) {
                persoonsnaam = persoonsnaamQ.getPersoonsnaamByChangekey(natuurlijkPersoonsnaamRegistratieRelatie4.getChangekey());
            }
            int i = 0;

            if (Registratietype.valueOf(registratieNu.getGebeurtenistype()) == Registratietype.REGISTRATIE) {
                persoonsnaamQ.setPersoonsnummer(persoonsnaam.getPersoonsnummer());
                persoonsnaamQ.setVoorvoegsel_geslachtsnaam(persoonsnaam.getVoorvoegsel_geslachtsnaam());
                persoonsnaamQ.setVoornamen(persoonsnaam.getVoornamen());
                persoonsnaamQ.setId(persoonsnaam.getId());
                persoonsnaamQ.setGeslachtsnaam(persoonsnaam.getGeslachtsnaam());
                persoonsnaamQ.setChangekey(persoonsnaam.getChangekey());
            } else if (Registratietype.valueOf(registratieNu.getGebeurtenistype()) == Registratietype.CORRECTIE) {
                persoonsnaamQ.setPersoonsnummer(persoonsnaam.getPersoonsnummer());
                persoonsnaamQ.setVoorvoegsel_geslachtsnaam(persoonsnaam.getVoorvoegsel_geslachtsnaam());
                persoonsnaamQ.setVoornamen(persoonsnaam.getVoornamen());
                persoonsnaamQ.setId(persoonsnaam.getId());
                persoonsnaamQ.setGeslachtsnaam(persoonsnaam.getGeslachtsnaam());
                persoonsnaamQ.setChangekey(persoonsnaam.getChangekey());
            } else if (Registratietype.valueOf(registratieNu.getGebeurtenistype()) == Registratietype.ONGEDAANMAKING) {
                persoonsnaamQ.setPersoonsnummer(persoonsnaam.getPersoonsnummer());
                persoonsnaamQ.setVoorvoegsel_geslachtsnaam(persoonsnaam.getVoorvoegsel_geslachtsnaam());
                persoonsnaamQ.setVoornamen(persoonsnaam.getVoornamen());
                persoonsnaamQ.setId(persoonsnaam.getId());
                persoonsnaamQ.setGeslachtsnaam(persoonsnaam.getGeslachtsnaam());
                persoonsnaamQ.setChangekey(persoonsnaam.getChangekey());
            } else if (Registratietype.valueOf(registratieNu.getGebeurtenistype()) == Registratietype.VERWIJDERING) {
                persoonsnaamQ.setPersoonsnummer("----------");
                persoonsnaamQ.setVoorvoegsel_geslachtsnaam("--");
                persoonsnaamQ.setVoornamen("---");
                persoonsnaamQ.setId(0);
                persoonsnaamQ.setGeslachtsnaam("-----");
                persoonsnaamQ.setChangekey(0);
            }
            ++index;
        }

        return persoonsnaamQ;
    }

}
