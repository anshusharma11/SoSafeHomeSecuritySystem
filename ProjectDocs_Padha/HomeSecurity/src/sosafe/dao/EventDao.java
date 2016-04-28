package sosafe.dao;

import sosafe.model.Event;

import java.util.List;

import javax.annotation.Nullable;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;

/**
 * Handles the Select, Create, Update, Delete & Filter operations for {@link Event} entity.
 */
public class EventDao extends BaseDao {
  public EventDao(@Nullable final EntityManagerFactory entityManagerFactory) {
    super(entityManagerFactory);
  }

  public List<Event> getEvents() {
    final EntityManager mgr = getEntityManager();
    try {
      return mgr.createQuery("select event from Event event", Event.class).getResultList();
    } finally {
      mgr.close();
    }
  }

  public List<Event> getEventsByServiceCode(@Nullable final String serviceCode) {
    if (serviceCode == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      return mgr
          .createQuery(
              "select event from Event event where event.service.serviceCode = :serviceCode",
              Event.class)
          .setParameter("serviceCode", serviceCode)
          .getResultList();
    } finally {
      mgr.close();
    }
  }

  public List<Event> getEventsByLocationCode(@Nullable final String locationCode) {
    if (locationCode == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      return mgr
          .createQuery(
              "select event from Event event where event.location.locationCode = :locationCode",
              Event.class)
          .setParameter("locationCode", locationCode)
          .getResultList();
    } finally {
      mgr.close();
    }
  }

  public Event getEventByEventCode(@Nullable final String eventCode) {
    final EntityManager mgr = getEntityManager();
    try {
      return mgr
          .createQuery(
              "select event from Event event where event.eventCode = :eventCode", Event.class)
          .setParameter("eventCode", eventCode)
          .getSingleResult();
    } finally {
      mgr.close();
    }
  }

  public Event getEvent(final Long id) {
    if (id == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    Event ev = null;
    try {
      ev = mgr.find(Event.class, id);
    } finally {
      mgr.close();
    }
    return ev;
  }

  public Event updateEvent(final Event ev) {
    if (ev == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      if (!containsEvent(ev)) {
        throw new EntityNotFoundException("Object does not exist");
      }
      mgr.getTransaction().begin();
      mgr.merge(ev);
      mgr.getTransaction().commit();
    } finally {
      mgr.close();
    }
    return ev;
  }

  public Event insertEvent(final Event ev) {
    if (ev == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      if (containsEvent(ev)) {
        throw new EntityExistsException("Object already exists");
      }
      mgr.getTransaction().begin();
      mgr.persist(ev);
      mgr.getTransaction().commit();
    } finally {
      mgr.close();
    }
    return ev;
  }

  public void removeEvent(final Long id) {
    if (id != null) {
      final EntityManager mgr = getEntityManager();
      try {
        final Event ev = mgr.find(Event.class, id);
        mgr.getTransaction().begin();
        mgr.remove(ev);
        mgr.getTransaction().commit();
      } finally {
        mgr.close();
      }
    }
  }

  public void removeAllEvents() {
    final EntityManager mgr = getEntityManager();
    try {
      mgr.getTransaction().begin();
      mgr.createQuery("delete from Event ev").executeUpdate();
      mgr.getTransaction().commit();
    } finally {
      mgr.close();
    }
  }

  private boolean containsEvent(final Event ev) {
    if (ev == null) {
      return false;
    }
    if (ev.getId() == null) {
      return false;
    }
    final EntityManager mgr = getEntityManager();
    boolean contains = true;
    try {
      final Event item = mgr.find(Event.class, ev.getId());
      if (item == null) {
        contains = false;
      }
    } finally {
      mgr.close();
    }
    return contains;
  }
}
