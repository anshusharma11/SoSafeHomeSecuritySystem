package sosafe.dao;

import sosafe.model.Service;

import java.util.List;

import javax.annotation.Nullable;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

/**
 * Handles the Select, Create, Update, Delete & Filter operations for {@link Service} entity.
 */
public class ServiceDao extends BaseDao {
  public ServiceDao(@Nullable final EntityManagerFactory entityManagerFactory) {
    super(entityManagerFactory);
  }

  public List<Service> getServices() {
    final EntityManager mgr = getEntityManager();
    try {
      return mgr.createQuery("select service from Service service", Service.class).getResultList();
    } finally {
      mgr.close();
    }
  }

  public List<Service> getServicesWithRelatedRecords() {
    final EntityManager mgr = getEntityManager();
    List<Service> services = null;
    try {
      services =
          mgr.createQuery("select service from Service service", Service.class).getResultList();
    } finally {
      mgr.close();
    }
    return services;
  }

  public List<Service> getServicesWithBills() {
    final EntityManager mgr = getEntityManager();
    List<Service> services = null;
    try {
      services =
          mgr.createQuery("select service from Service service", Service.class).getResultList();
    } finally {
      mgr.close();
    }
    return services;
  }

  public List<Service> getServicesWithLocations() {
    final EntityManager mgr = getEntityManager();
    List<Service> services = null;
    try {
      services =
          mgr.createQuery("select service from Service service", Service.class).getResultList();
    } finally {
      mgr.close();
    }
    return services;
  }

  public List<Service> getServicesWithSchedules() {
    final EntityManager mgr = getEntityManager();
    List<Service> services = null;
    try {
      services =
          mgr.createQuery("select service from Service service", Service.class).getResultList();
    } finally {
      mgr.close();
    }
    return services;
  }

  public List<Service> getServices(@Nullable final String id) {
    if (id == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      return mgr
          .createQuery(
              "select service from Service service where service.id like :id", Service.class)
          .setParameter("id", wrapWildCards(id))
          .getResultList();
    } finally {
      mgr.close();
    }
  }

  public Service getServiceByServiceCode(@Nullable final String serviceCode) {
    final EntityManager mgr = getEntityManager();
    try {
      return mgr
          .createQuery(
              "select service from Service service where service.serviceCode = :serviceCode",
              Service.class)
          .setParameter("serviceCode", serviceCode)
          .getSingleResult();
    } catch (NoResultException nre){}
    finally {
      mgr.close();
    }
    return null;
  }

  public Service getService(final String id) {
    if (id == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    Service service = null;
    try {
      service = mgr.find(Service.class, id);
    } finally {
      mgr.close();
    }
    return service;
  }

  public Service getServiceWithRelatedRecords(final String id) {
    if (id == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    Service service = null;
    try {
      service = mgr.find(Service.class, id);
//      service.getBills().size();
//      service.getLocations().size();
//      service.getSchedules().size();
    } finally {
      mgr.close();
    }
    return service;
  }

  public Service updateService(final Service service) {
    if (service == null || service.getId() == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      if (!containsService(service)) {
        throw new EntityNotFoundException("Object does not exist");
      }
      mgr.getTransaction().begin();
      mgr.merge(service);
      mgr.getTransaction().commit();
    } finally {
      mgr.close();
    }
    return service;
  }

  public Service insertService(final Service service) {
    if (service == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      if (containsService(service)) {
        throw new EntityExistsException("Object already exists");
      }
      mgr.getTransaction().begin();
      mgr.persist(service);
      mgr.getTransaction().commit();
    } finally {
      mgr.close();
    }
    return service;
  }

  public void removeService(final Long id) {
    if (id != null) {
      final EntityManager mgr = getEntityManager();
      try {
        final Service service = mgr.find(Service.class, id);
        mgr.getTransaction().begin();
        mgr.remove(service);
        mgr.getTransaction().commit();
      } finally {
        mgr.close();
      }
    }
  }

  private boolean containsService(final Service service) {
    if (service == null || service.getId() == null) {
      return false;
    }
    final EntityManager mgr = getEntityManager();
    boolean contains = true;
    try {
      final Service item = mgr.find(Service.class, service.getId());
      if (item == null) {
        contains = false;
      }
    } finally {
      mgr.close();
    }
    return contains;
  }
}
