package sosafe.model;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Hibernate Entity mapped to Bill table.
 **/
@Entity
@Table(name = "Bill")
public class Bill {

	  private Long id; // BillId
	  private String billCode;
	  private Service service;
	  private Month month;
	  private String year;
	  private Date dueDate;
	  private Integer lateFeePerMonth;
	  
  public enum Month {
    JANUARY(1),
    FEBRUARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12);

    private int number;
  
    private Month(int number) {
        this.number = number;
    }
  
    public int getMonth() {
        return number;
    }
  }

  public Bill() {}

  public Bill(final String billCode, final Service service, final Month month, final String year,
      final Date dueDate, final Integer lateFeePerMonth) {
    this.billCode = billCode;
    this.service = service;
    this.month = month;
    this.year = year;
    this.dueDate = dueDate;
    this.lateFeePerMonth = lateFeePerMonth;
  }

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "BillId")
  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  /**
   * @return the billCode
   */
  @Column(name = "BillCode", nullable = false, unique = true)
  public String getBillCode() {
    return billCode;
  }

  /**
   * @param billCode the billCode to set
   */
  public void setBillCode(final String billCode) {
    this.billCode = billCode;
  }

  /**
   * @return the service
   */
  @ManyToOne
  @JoinColumn(name = "ServiceId")
  public Service getService() {
    return service;
  }

  /**
   * @param service the service to set
   */
  public void setService(final Service service) {
    this.service = service;
  }

  /**
   * @return the month
   */
  @Column(name = "Month")
  @Enumerated(EnumType.STRING)
  public Month getMonth() {
    return month;
  }

  /**
   * @param month the month to set
   */
  public void setMonth(final Month month) {
    this.month = month;
  }

  /**
   * @return the year
   */
  @Column(name = "Year")
  public String getYear() {
    return year;
  }

  /**
   * @param year the year to set
   */
  public void setYear(final String year) {
    this.year = year;
  }

  /**
   * @return the dueDate
   */
  @Column(name = "DueDate")
  @Temporal(TemporalType.TIMESTAMP)
  public Date getDueDate() {
    return dueDate;
  }

  /**
   * @param dueDate the dueDate to set
   */
  public void setDueDate(final Date dueDate) {
    this.dueDate = dueDate;
  }

  /**
   * @return the lateFeePerMonth
   */
  @Column(name = "LateFeePerMonth")
  public Integer getLateFeePerMonth() {
    return lateFeePerMonth;
  }

  /**
   * @param lateFeePerMonth the lateFeePerMonth to set
   */
  public void setLateFeePerMonth(final Integer lateFeePerMonth) {
    this.lateFeePerMonth = lateFeePerMonth;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Id = " + id);
    sb.append("service = " + service);
    sb.append("month = " + month);
    sb.append("year = " + year);
    sb.append("dueDate = " + dueDate);
    sb.append("lateFeePerMonth = " + lateFeePerMonth);
    return sb.toString();
  }
}
