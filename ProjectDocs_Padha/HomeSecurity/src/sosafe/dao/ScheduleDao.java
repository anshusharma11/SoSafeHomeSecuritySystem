package sosafe.dao;

import sosafe.model.Schedule;
import sosafe.model.Schedule.ScheduleType;

import java.util.List;

import javax.annotation.Nullable;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

/**
 * Handles the Select, Create, Update, Delete & Filter operations for {@link Schedule} entity.
 */
public class ScheduleDao extends BaseDao {
  public ScheduleDao(@Nullable final EntityManagerFactory entityManagerFactory) {
    super(entityManagerFactory);
  }

  public List<Schedule> getSchedules() {
    final EntityManager mgr = getEntityManager();
    try {
      return mgr.createQuery("select schedule from Schedule schedule", Schedule.class)
          .getResultList();
    } finally {
      mgr.close();
    }
  }

  public List<Schedule> getSchedulesByServiceId(@Nullable final Long serviceId) {
    if (serviceId == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      return mgr
          .createQuery(
              "select schedule from Schedule schedule where schedule.service.id = :serviceId",
              Schedule.class)
          .setParameter("serviceId", serviceId)
          .getResultList();
    } finally {
      mgr.close();
    }
  }

  public List<Schedule> getSchedulesByServiceCode(@Nullable final String serviceCode) {
    if (serviceCode == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      return mgr
          .createQuery(
              "select schedule from Schedule schedule where schedule.service.serviceCode = :serviceCode",
              Schedule.class)
          .setParameter("serviceCode", serviceCode)
          .getResultList();
    } finally {
      mgr.close();
    }
  }

  public List<Schedule> getSchedulesByType(@Nullable final String scheduleType) {
    if (scheduleType == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      return mgr
          .createQuery(
              "select schedule from Schedule schedule where schedule.scheduleType = :scheduleType",
              Schedule.class)
          .setParameter("scheduleType", scheduleType)
          .getResultList();
    } finally {
      mgr.close();
    }
  }

  public Schedule getSchedulesByServiceAndType(@Nullable final Long serviceId, 
		  @Nullable final ScheduleType scheduleType) {
    if (scheduleType == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      return mgr
          .createQuery(
              "select schedule from Schedule schedule where " +
              "schedule.service.id = :serviceId and " +
              "schedule.scheduleType = :scheduleType",
              Schedule.class)
              .setParameter("serviceId", serviceId)
              .setParameter("scheduleType", scheduleType)
          .getSingleResult();
    } catch (NoResultException nre){}
    finally {
      mgr.close();
    }
    return null;
  }

  public Schedule getSchedule(final Long id) {
    if (id == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    Schedule schedule = null;
    try {
      schedule = mgr.find(Schedule.class, id);
    } finally {
      mgr.close();
    }
    return schedule;
  }

  public Schedule updateSchedule(final Schedule schedule) {
    if (schedule == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      if (!containsSchedule(schedule)) {
        throw new EntityNotFoundException("Object does not exist");
      }
      mgr.getTransaction().begin();
      mgr.merge(schedule);
      mgr.getTransaction().commit();
    } finally {
      mgr.close();
    }
    return schedule;
  }

  public Schedule insertSchedule(final Schedule schedule) {
    if (schedule == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      if (containsSchedule(schedule)) {
        throw new EntityExistsException("Object already exists");
      }
      mgr.getTransaction().begin();
      mgr.persist(schedule);
      mgr.getTransaction().commit();
    } finally {
      mgr.close();
    }
    return schedule;
  }

  public void removeSchedule(final Long id) {
    if (id != null) {
      final EntityManager mgr = getEntityManager();
      try {
        final Schedule schedule = mgr.find(Schedule.class, id);
        mgr.getTransaction().begin();
        mgr.remove(schedule);
        mgr.getTransaction().commit();
      } finally {
        mgr.close();
      }
    }
  }

  public void removeAllSchedules() {
    final EntityManager mgr = getEntityManager();
    try {
      mgr.getTransaction().begin();
      mgr.createQuery("delete from Schedule schedule").executeUpdate();
      mgr.getTransaction().commit();
    } finally {
      mgr.close();
    }
  }

  private boolean containsSchedule(final Schedule schedule) {
    if (schedule == null) {
      return false;
    }
    if (schedule.getId() == null) {
      return false;
    }
    final EntityManager mgr = getEntityManager();
    boolean contains = true;
    try {
      final Schedule item = mgr.find(Schedule.class, schedule.getId());
      if (item == null) {
        contains = false;
      }
    } finally {
      mgr.close();
    }
    return contains;
  }
}
