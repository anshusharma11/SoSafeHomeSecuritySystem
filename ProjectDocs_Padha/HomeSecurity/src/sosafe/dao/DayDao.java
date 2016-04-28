package sosafe.dao;

import sosafe.model.Day;

import java.util.List;

import javax.annotation.Nullable;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;

/**
 * Handles the Select, Create, Update, Delete & Filter operations for {@link Day} entity.
 */
public class DayDao extends BaseDao {
  public DayDao(@Nullable final EntityManagerFactory entityManagerFactory) {
    super(entityManagerFactory);
  }

  public List<Day> getDays() {
    final EntityManager mgr = getEntityManager();
    try {
      return mgr.createQuery("select day from Day day", Day.class).getResultList();
    } finally {
      mgr.close();
    }
  }

  public Day getDayByName(@Nullable final String name) {
    final EntityManager mgr = getEntityManager();
    try {
      return mgr.createQuery("select day from Day day where day.name = :name", Day.class)
          .setParameter("name", name)
          .getSingleResult();
    } finally {
      mgr.close();
    }
  }

  public Day getDay(final Long id) {
    if (id == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    Day day = null;
    try {
      day = mgr.find(Day.class, id);
    } finally {
      mgr.close();
    }
    return day;
  }

  public Day updateDay(final Day day) {
    if (day == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      if (!containsDay(day)) {
        throw new EntityNotFoundException("Object does not exist");
      }
      mgr.getTransaction().begin();
      mgr.merge(day);
      mgr.getTransaction().commit();
    } finally {
      mgr.close();
    }
    return day;
  }

  public Day insertDay(final Day day) {
    if (day == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      if (containsDay(day)) {
        throw new EntityExistsException("Object already exists");
      }
      mgr.getTransaction().begin();
      mgr.persist(day);
      mgr.getTransaction().commit();
    } finally {
      mgr.close();
    }
    return day;
  }

  public void removeDay(final Long id) {
    if (id != null) {
      final EntityManager mgr = getEntityManager();
      try {
        final Day day = mgr.find(Day.class, id);
        mgr.getTransaction().begin();
        mgr.remove(day);
        mgr.getTransaction().commit();
      } finally {
        mgr.close();
      }
    }
  }

  public void removeAllDays() {
    final EntityManager mgr = getEntityManager();
    try {
      mgr.getTransaction().begin();
      mgr.createQuery("delete from Day day").executeUpdate();
      mgr.getTransaction().commit();
    } finally {
      mgr.close();
    }
  }

  private boolean containsDay(final Day day) {
    if (day == null) {
      return false;
    }
    if (day.getId() == null) {
      return false;
    }
    final EntityManager mgr = getEntityManager();
    boolean contains = true;
    try {
      final Day item = mgr.find(Day.class, day.getId());
      if (item == null) {
        contains = false;
      }
    } finally {
      mgr.close();
    }
    return contains;
  }
}
