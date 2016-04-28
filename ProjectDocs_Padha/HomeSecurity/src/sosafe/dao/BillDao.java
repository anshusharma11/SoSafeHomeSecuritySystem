package sosafe.dao;

import sosafe.model.Bill;

import java.util.List;

import javax.annotation.Nullable;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;

/**
 * Handles the Select, Create, Update, Delete & Filter operations for {@link Bill} entity.
 */
public class BillDao extends BaseDao {
  public BillDao(@Nullable final EntityManagerFactory entityManagerFactory) {
    super(entityManagerFactory);
  }

  public List<Bill> getBills() {
    final EntityManager mgr = getEntityManager();
    try {
      return mgr.createQuery("select bill from Bill bill", Bill.class).getResultList();
    } finally {
      mgr.close();
    }
  }

  public List<Bill> getBillsByServiceCode(@Nullable final String serviceCode) {
    if (serviceCode == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      return mgr
          .createQuery(
              "select bill from Bill bill where bill.service.serviceCode = :serviceCode",
              Bill.class)
          .setParameter("serviceCode", serviceCode)
          .getResultList();
    } finally {
      mgr.close();
    }
  }

  public List<Bill> getBillsByMonth(@Nullable final String month) {
    if (month == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      return mgr.createQuery("select bill from Bill bill where bill.month = :month", Bill.class)
          .setParameter("month", month)
          .getResultList();
    } finally {
      mgr.close();
    }
  }

  public Bill getBillByBillCode(@Nullable final String billCode) {
    final EntityManager mgr = getEntityManager();
    try {
      return mgr.createQuery("select f from Bill f where f.billCode = :billCode", Bill.class)
          .setParameter("billCode", billCode)
          .getSingleResult();
    } finally {
      mgr.close();
    }
  }

  public Bill getBill(final Long id) {
    if (id == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    Bill bill = null;
    try {
      bill = mgr.find(Bill.class, id);
    } finally {
      mgr.close();
    }
    return bill;
  }

  public Bill updateBill(final Bill bill) {
    if (bill == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      if (!containsBill(bill)) {
        throw new EntityNotFoundException("Object does not exist");
      }
      mgr.getTransaction().begin();
      mgr.merge(bill);
      mgr.getTransaction().commit();
    } finally {
      mgr.close();
    }
    return bill;
  }

  public Bill insertBill(final Bill bill) {
    if (bill == null) {
      return null;
    }
    final EntityManager mgr = getEntityManager();
    try {
      if (containsBill(bill)) {
        throw new EntityExistsException("Object already exists");
      }
      mgr.getTransaction().begin();
      mgr.persist(bill);
      mgr.getTransaction().commit();
    } finally {
      mgr.close();
    }
    return bill;
  }

  public void removeBill(final Long id) {
    if (id != null) {
      final EntityManager mgr = getEntityManager();
      try {
        final Bill bill = mgr.find(Bill.class, id);
        mgr.getTransaction().begin();
        mgr.remove(bill);
        mgr.getTransaction().commit();
      } finally {
        mgr.close();
      }
    }
  }

  public void removeAllBills() {
    final EntityManager mgr = getEntityManager();
    try {
      mgr.getTransaction().begin();
      mgr.createQuery("delete from Bill bill").executeUpdate();
      mgr.getTransaction().commit();
    } finally {
      mgr.close();
    }
  }

  private boolean containsBill(final Bill bill) {
    if (bill == null) {
      return false;
    }
    if (bill.getId() == null) {
      return false;
    }
    final EntityManager mgr = getEntityManager();
    boolean contains = true;
    try {
      final Bill item = mgr.find(Bill.class, bill.getId());
      if (item == null) {
        contains = false;
      }
    } finally {
      mgr.close();
    }
    return contains;
  }
}
