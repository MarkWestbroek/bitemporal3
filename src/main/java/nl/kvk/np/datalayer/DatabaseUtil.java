package nl.kvk.np.datalayer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DatabaseUtil {

    private static final EntityManager entityManager;
    private static EntityTransaction entityTransaction = null;

    static {
        entityManager = Persistence.createEntityManagerFactory("persoon-db").
                createEntityManager();
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public static void beginTransaction() {
        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
    }

    public static void endTransaction() {
        if (entityTransaction != null) {
            entityTransaction.commit();
        }
    }

    public static void closeEntityManager() {
        entityManager.close();
    }

}