package sosafe.dao;

import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Base DAO for accessing objects from SQL.
 */
abstract class BaseDao {
  protected final EntityManagerFactory entityManagerFactory;

  protected BaseDao(@Nullable EntityManagerFactory entityManagerFactory) {
    if (entityManagerFactory == null) {
      final EMF emf = new EMF();
      entityManagerFactory = emf.get();
    }
    this.entityManagerFactory = entityManagerFactory;
  }

  protected EntityManager getEntityManager() {
    return entityManagerFactory.createEntityManager();
  }

  protected static String wrapWildCards(final String name) {
    return "%" + name + "%";
  }

  public String getDatabaseName() {
    String dbName =
        entityManagerFactory.getProperties().get("javax.persistence.jdbc.url").toString();
    dbName = dbName.substring(dbName.lastIndexOf('/') + 1);
    if (dbName.contains("?")) {
      dbName = dbName.substring(0, dbName.indexOf('?'));
    }
    return dbName;
  }
}
