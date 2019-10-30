package nl.kvk.np.datalayer;

import nl.kvk.np.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersoonDAO {

    protected EntityManager entityManager;

    // Literarture: https://thoughts-on-java.org/how-to-join-unrelated-entities/

    public boolean saveRegistratie(Registratie registratie, boolean update) {
        try {
            beginTr();
            if (!update) {
                entityManager.persist(registratie);
            } else {
                entityManager.merge(registratie);
            }
            endTr();
        } catch (Exception e) {
            System.out.println("Cannot save Registratie-Gebeurtenis [" + e + "]");
            return false;
        }
        return true;
    }

    public boolean saveNatuurlijkPersoonsnaamRegistratieRelatie(NatuurlijkPersoonsnaamRegistratieRelatie natuurlijkPersoonsnaamRegistratieRelatie, boolean update) {
        try {
            beginTr();
            if (!update) {
                entityManager.persist(natuurlijkPersoonsnaamRegistratieRelatie);
            } else {
                entityManager.merge(natuurlijkPersoonsnaamRegistratieRelatie);
            }
            endTr();
        } catch (Exception e) {
            System.out.println("Cannot save NatuurlijkPersoonsnaamRegistratieRelatie [" + e + "]");
            return false;
        }
        return true;
    }

    public boolean saveNatuurlijkPersoon(NatuurlijkPersoon natuurlijkPersoon, boolean update) {
        try {
            beginTr();
            if (!update) {
                entityManager.persist(natuurlijkPersoon);
            } else {
                entityManager.merge(natuurlijkPersoon);
            }
            endTr();
        } catch (Exception e) {
            System.out.println("Cannot save NatuurlijkPersoon [" + e + "]");
            return false;
        }
        return true;
    }

    public boolean saveNatuurlijkPersoonRegistratieRelatie(NatuurlijkPersoonRegistratieRelatie natuurlijkPersoonRegistratieRelatie, boolean update) {
        try {
            beginTr();
            if (!update) {
                entityManager.persist(natuurlijkPersoonRegistratieRelatie);
            } else {
                entityManager.merge(natuurlijkPersoonRegistratieRelatie);
            }
            endTr();
        } catch (Exception e) {
            System.out.println("Cannot save natuurlijkPersoonRegistratieRelatie [" + e + "]");
            return false;
        }
        return true;
    }

    public boolean savePersoonsnaam(Persoonsnaam persoonsnaam, boolean update) {
        try {
            beginTr();
            if (!update) {
                entityManager.persist(persoonsnaam);
            } else {
                entityManager.merge(persoonsnaam);
            }
            endTr();
        } catch (Exception e) {
            System.out.println("Cannot save Persoonsnaam [" + e + "]");
            return false;
        }
        return true;
    }

    public boolean saveStaatsburgerschap(Staatsburgerschap staatsburgerschap, boolean update) {
        try {
            beginTr();
            if (!update) {
                entityManager.persist(staatsburgerschap);
            } else {
                entityManager.merge(staatsburgerschap);
            }
            endTr();
        } catch (Exception e) {
            System.out.println("Cannot save Staatsburgerschap [" + e + "]");
            return false;
        }
        return true;
    }

    public boolean saveHerkenning(Herkenning herkenning, boolean update) {
        try {
            beginTr();
            if (!update) {
                entityManager.persist(herkenning);
            } else {
                entityManager.merge(herkenning);
            }
            endTr();
        } catch (Exception e) {
            System.out.println("Cannot save Herkenning [" + e + "]");
            return false;
        }
        return true;
    }

    public boolean saveLevensfase(Levensfase levensfase, boolean update) {
        try {
            beginTr();
            if (!update) {
                entityManager.persist(levensfase);
            } else {
                entityManager.merge(levensfase);
            }
            endTr();
        } catch (Exception e) {
            System.out.println("Cannot save Levensfase [" + e + "]");
            return false;
        }
        return true;
    }

    protected void beginTr() {
        DatabaseUtil.beginTransaction();
        entityManager = DatabaseUtil.getEntityManager();
    }

    protected void endTr() {
        DatabaseUtil.endTransaction();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

}
