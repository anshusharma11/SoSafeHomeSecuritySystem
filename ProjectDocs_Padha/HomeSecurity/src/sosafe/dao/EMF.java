package sosafe.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Entity Manager Factory tied to META_INF/persistence.xml
 */
public final class EMF {
  private static Map<String, String> properties;

  static {
    properties = new HashMap<String, String>();
    //  if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
    //    properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.GoogleDriver");
    //    properties.put("javax.persistence.jdbc.url", System.getProperty("cloudsql.url"));
    //  } else {
    //    properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
    //    properties.put("javax.persistence.jdbc.url", System.getProperty("cloudsql.url.dev"));
    //  }
  }

  private final EntityManagerFactory emfInstance;

  public EMF() {
    emfInstance = Persistence.createEntityManagerFactory("SoSafe", properties);
  }

  public EntityManagerFactory get() {
    return emfInstance;
  }
}
