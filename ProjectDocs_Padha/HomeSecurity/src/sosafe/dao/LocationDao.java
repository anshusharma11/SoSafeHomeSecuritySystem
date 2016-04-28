package sosafe.dao;

import sosafe.model.Location;

import java.util.List;

import javax.annotation.Nullable;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;

/**
 * Handles the Select, Create, Update, Delete & Filter operations for {@link Location} entity.
 */
public class LocationDao extends BaseDao {
  public LocationDao(@Nullable final EntityManagerFactory entityManagerFactory) {
    super(entityManagerFactory);
  }

  public List<Location> getLocations() {
    final EntityManager mgr = getEntityManager();
    try {
      return mgr.createQuery("select location from Location location", Location.class)
          .getResultList();
    } finally {
      mgr.close();
    }
  }

  public List<Location> getLocationsWithBills() {
    final EntityManager mgr = getEntityManager();
    List<Location> locations = null;
    try {
      locations =
          mgr.createQuery("select location from Location location", Location.class).getResultList();
      //      for (final Location location : locations) {
      //        location.getBill().size();
      //      }
    } finally {
      mgr.close();
    }
    return locations;
  }

  public List<Location> getLocationsByLocationName(@Nullable final String locationName) {
    if (locationName == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    List<Location> locations = null;
    try {
      locations =
          mgr.createQuery(
              "select location from Location location where location.locationName = :locationName",
              Location.class)
          .setParameter("locationName", locationName)
          .getResultList();
	  	for(Location location : locations) {
	        System.out.println("#Sensors : "+location.getSensors().size());
	        System.out.println("#Events : "+location.getEvents().size());
		}
    } finally {
      mgr.close();
    }
    return locations;
  }

  public List<Location> getLocationsByServiceId(@Nullable final Long seviceId) {
    if (seviceId == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    List<Location> locations = null;
    try {
    	locations = mgr.createQuery(
              "select location from Location location where location.service.id = :seviceId",
              Location.class)
          .setParameter("seviceId", seviceId)
          .getResultList();
    	for(Location location : locations) {
            System.out.println("#Sensors : "+location.getSensors().size());
            System.out.println("#Events : "+location.getEvents().size());
    	}
    } finally {
      mgr.close();
    }
    return locations;
  }

  public List<Location> getLocationByServiceCode(@Nullable final String seviceCode) {
    if (seviceCode == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    List<Location> locations = null;
    try {
    	locations =
          mgr.createQuery(
                 "select location from Location location where location.service.seviceCode = :seviceCode",
                 Location.class)
              .setParameter("seviceCode", seviceCode)
              .getResultList();
    	for(Location location : locations) {
            System.out.println("#Sensors : "+location.getSensors().size());
            System.out.println("#Events : "+location.getEvents().size());
    	}
    } finally {
      mgr.close();
    }
    return locations;
  }

  public Location getLocationByLocationCode(@Nullable final String locationCode) {
    final EntityManager mgr = getEntityManager();
    Location location = null;
    try {
      location =
          mgr
          .createQuery(
              "select location from Location location where location.locationCode = :locationCode",
              Location.class)
          .setParameter("locationCode", locationCode)
          .getSingleResult();
        System.out.println("#Sensors : "+location.getSensors().size());
        System.out.println("#Events : "+location.getEvents().size());
    } finally {
      mgr.close();
    }
    return location;
  }

  public Location getLocation(final Long id) {
    if (id == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    Location location = null;
    try {
      location = mgr.find(Location.class, id);
    } finally {
      mgr.close();
    }
    return location;
  }

  public Location updateLocation(final Location location) {
    if (location == null || location.getId() == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      if (!containsLocation(location)) {
        throw new EntityNotFoundException("Object does not exist");
      }
      mgr.getTransaction().begin();
      mgr.merge(location);
      mgr.getTransaction().commit();
    } finally {
      mgr.close();
    }
    return location;
  }

  public Location insertLocation(final Location location) {
    if (location == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      if (containsLocation(location)) {
        throw new EntityExistsException("Object already exists");
      }
      mgr.getTransaction().begin();
      mgr.persist(location);
      mgr.getTransaction().commit();
    } finally {
      mgr.close();
    }
    return location;
  }

  public void removeLocation(final Long id) {
    if (id != null) {
      final EntityManager mgr = getEntityManager();
      try {
        final Location location = mgr.find(Location.class, id);
        mgr.getTransaction().begin();
        mgr.remove(location);
        mgr.getTransaction().commit();
      } finally {
        mgr.close();
      }
    }
  }

  private boolean containsLocation(final Location location) {
    if (location == null || location.getId() == null) {
      return false;
    }
    final EntityManager mgr = getEntityManager();
    boolean contains = true;
    try {
      final Location item = mgr.find(Location.class, location.getId());
      if (item == null) {
        contains = false;
      }
    } finally {
      mgr.close();
    }
    return contains;
  }
}
