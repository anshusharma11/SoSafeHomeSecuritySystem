package sosafe.dao;

import sosafe.model.Event;
import sosafe.model.Sensor;

import java.util.List;

import javax.annotation.Nullable;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;

/**
 * Handles the Select, Create, Update, Delete & Filter operations for {@link Sensor} entity.
 */
public class SensorDao extends BaseDao {
  public SensorDao(@Nullable final EntityManagerFactory entityManagerFactory) {
    super(entityManagerFactory);
  }

  public List<Sensor> getSensors() {
    final EntityManager mgr = getEntityManager();
    try {
      return mgr.createQuery("select sensor from Sensor sensor", Sensor.class).getResultList();
    } finally {
      mgr.close();
    }
  }

  public List<Sensor> getSensorsByServiceCode(@Nullable final String serviceCode) {
    if (serviceCode == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      return mgr
          .createQuery(
              "select sensor from Sensor sensor where sensor.service.serviceCode = :serviceCode",
              Sensor.class)
          .setParameter("serviceCode", serviceCode)
          .getResultList();
    } finally {
      mgr.close();
    }
  }

  public List<Sensor> getSensorsByType(@Nullable final String sensorType) {
    if (sensorType == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      return mgr
          .createQuery(
              "select sensor from Sensor sensor where sensor.sensorType = :sensorType",
              Sensor.class)
          .setParameter("sensorType", sensorType)
          .getResultList();
    } finally {
      mgr.close();
    }
  }

  public List<Event> getSensorsByLocationCode(@Nullable final String locationCode) {
    if (locationCode == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      return mgr
          .createQuery(
              "select sensor from Sensor sensor where event.location.locationCode = :locationCode",
              Event.class)
          .setParameter("locationCode", locationCode)
          .getResultList();
    } finally {
      mgr.close();
    }
  }

  public Sensor getSensorBySensorCode(@Nullable final String sensorCode) {
    final EntityManager mgr = getEntityManager();
    try {
      return mgr
          .createQuery(
              "select sensor from Sensor sensor where sensor.sensorCode = :sensorCode",
              Sensor.class)
          .setParameter("sensorCode", sensorCode)
          .getSingleResult();
    } finally {
      mgr.close();
    }
  }

  public Sensor getSensor(final Long id) {
    if (id == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    Sensor sensor = null;
    try {
      sensor = mgr.find(Sensor.class, id);
    } finally {
      mgr.close();
    }
    return sensor;
  }

  public Sensor updateSensor(final Sensor sensor) {
    if (sensor == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      if (!containsSensor(sensor)) {
        throw new EntityNotFoundException("Object does not exist");
      }
      mgr.getTransaction().begin();
      mgr.merge(sensor);
      mgr.getTransaction().commit();
    } finally {
      mgr.close();
    }
    return sensor;
  }

  public Sensor insertSensor(final Sensor sensor) {
    if (sensor == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      if (containsSensor(sensor)) {
        throw new EntityExistsException("Object already exists");
      }
      mgr.getTransaction().begin();
      mgr.persist(sensor);
      mgr.getTransaction().commit();
    } finally {
      mgr.close();
    }
    return sensor;
  }

  public void removeSensor(final Long id) {
    if (id != null) {
      final EntityManager mgr = getEntityManager();
      try {
        final Sensor sensor = mgr.find(Sensor.class, id);
        mgr.getTransaction().begin();
        mgr.remove(sensor);
        mgr.getTransaction().commit();
      } finally {
        mgr.close();
      }
    }
  }

  public void removeAllSensors() {
    final EntityManager mgr = getEntityManager();
    try {
      mgr.getTransaction().begin();
      mgr.createQuery("delete from Sensor sensor").executeUpdate();
      mgr.getTransaction().commit();
    } finally {
      mgr.close();
    }
  }

  private boolean containsSensor(final Sensor sensor) {
    if (sensor == null) {
      return false;
    }
    if (sensor.getId() == null) {
      return false;
    }
    final EntityManager mgr = getEntityManager();
    boolean contains = true;
    try {
      final Sensor item = mgr.find(Sensor.class, sensor.getId());
      if (item == null) {
        contains = false;
      }
    } finally {
      mgr.close();
    }
    return contains;
  }
}
