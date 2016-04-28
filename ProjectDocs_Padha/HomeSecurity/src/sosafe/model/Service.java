package sosafe.model;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Hibernate Entity mapped to Service table.
 **/
@Entity
@Table(name = "Service")
public class Service {
  public enum ServiceType {
    BREAKIN(1),
    FIRE(2),
    BREAKIN_N_FIRE(3);

    private int number;
  
    private ServiceType(int number) {
        this.number = number;
    }
  
    public int getServiceType() {
        return number;
    }
  }

  private Long id; // ServiceId

  private String serviceCode;
  private String customerName;
  private String buildingName;
  private String address;
  private String telephone;
  private ServiceType serviceType;
  private String emergencyTelephonePrimary;
  private String emergencyTelephoneSecondary;
  private Date fromDate;
  private Date toDate;

//  private List<Bill> bills = new ArrayList<Bill>();
//  private List<Location> locations = new ArrayList<Location>();
//  private List<Schedule> schedules = new ArrayList<Schedule>();
//  private List<Sensor> sensors = new ArrayList<Sensor>();
//  private List<Event> events = new ArrayList<Event>();

  public Service() {}

  public Service(final String serviceCode, final String customerName, final String buildingName,
      final String address, final String telephone, final ServiceType serviceType,
      final String emergencyTelephonePrimary, final String emergencyTelephoneSecondary,
      final Date fromDate, final Date toDate) {
	  
    this.serviceCode = serviceCode;
    this.customerName = customerName;
    this.buildingName = buildingName;
    this.address = address;
    this.telephone = telephone;
    this.serviceType = serviceType;
    this.emergencyTelephonePrimary = emergencyTelephonePrimary;
    this.emergencyTelephoneSecondary = emergencyTelephoneSecondary;
    this.fromDate = fromDate;
    this.toDate = toDate;
  }

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "ServiceId")
  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  @Column(name = "ServiceCode", nullable = false, unique = true)
  public String getServiceCode() {
    return serviceCode;
  }

  public void setServiceCode(final String serviceCode) {
    this.serviceCode = serviceCode;
  }

  /**
   * @return the customerName
   */
  @Column(name = "CustomerName")
  public String getCustomerName() {
    return customerName;
  }

  /**
   * @param customerName the customerName to set
   */
  public void setCustomerName(final String customerName) {
    this.customerName = customerName;
  }

  /**
   * @return the buildingName
   */
  @Column(name = "BuildingName")
  public String getBuildingName() {
    return buildingName;
  }

  /**
   * @param buildingName the buildingName to set
   */
  public void setBuildingName(final String buildingName) {
    this.buildingName = buildingName;
  }

  /**
   * @return the address
   */
  @Column(name = "Address")
  public String getAddress() {
    return address;
  }

  /**
   * @param address the address to set
   */
  public void setAddress(final String address) {
    this.address = address;
  }

  /**
   * @return the telephone
   */
  @Column(name = "Telephone")
  public String getTelephone() {
    return telephone;
  }

  /**
   * @param telephone the telephone to set
   */
  public void setTelephone(final String telephone) {
    this.telephone = telephone;
  }

  /**
   * @return the serviceType
   */
  @Column(name = "ServiceType")
  @Enumerated(EnumType.STRING)
  public ServiceType getServiceType() {
    return serviceType;
  }

  /**
   * @param serviceType the serviceType to set
   */
  public void setServiceType(final ServiceType serviceType) {
    this.serviceType = serviceType;
  }

  /**
   * @return the emergencyTelephonePrimary
   */
  @Column(name = "EmergencyTelephonePrimary")
  public String getEmergencyTelephonePrimary() {
    return emergencyTelephonePrimary;
  }

  /**
   * @param emergencyTelephonePrimary the emergencyTelephonePrimary to set
   */
  public void setEmergencyTelephonePrimary(final String emergencyTelephonePrimary) {
    this.emergencyTelephonePrimary = emergencyTelephonePrimary;
  }

  /**
   * @return the emergencyTelephoneSecondary
   */
  @Column(name = "EmergencyTelephoneSecondary")
  public String getEmergencyTelephoneSecondary() {
    return emergencyTelephoneSecondary;
  }

  /**
   * @param emergencyTelephoneSecondary the emergencyTelephoneSecondary to set
   */
  public void setEmergencyTelephoneSecondary(final String emergencyTelephoneSecondary) {
    this.emergencyTelephoneSecondary = emergencyTelephoneSecondary;
  }

//  /**
//   * @return services
//   */
//  @OneToMany(targetEntity = Bill.class, mappedBy = "service",cascade={CascadeType.ALL},orphanRemoval=true)
//  public List<Bill> getBills() {
//    return bills;
//  }
//
//  /**
//   * @return services
//   */
//  @OneToMany(targetEntity = Location.class, mappedBy = "service",cascade={CascadeType.ALL},orphanRemoval=true)
//  public List<Location> getLocations() {
//    return locations;
//  }
//
//  /**
//   * @return services
//   */
//  @OneToMany(targetEntity = Schedule.class, mappedBy = "service") //,cascade={CascadeType.ALL},orphanRemoval=true)
//  public List<Schedule> getSchedules() {
//    return schedules;
//  }
//
//  /**
//   * @return the sensors
//   */
//  @OneToMany(targetEntity = Bill.class, mappedBy = "service",cascade={CascadeType.ALL},orphanRemoval=true)
//  public List<Sensor> getSensors() {
//    return sensors;
//  }
//
//  /**
//   * @return the events
//   */
//  @OneToMany(targetEntity = Event.class, mappedBy = "service",cascade={CascadeType.ALL},orphanRemoval=true)
//  public List<Event> getEvents() {
//    return events;
//  }
//
//  /**
//   * @param bills the bills to set
//   */
//  public void setBills(final List<Bill> bills) {
//    this.bills = bills;
//  }
//
//  /**
//   * @param locations the locations to set
//   */
//  public void setLocations(final List<Location> locations) {
//    this.locations = locations;
//  }
//
//  /**
//   * @param schedules the schedules to set
//   */
//  public void setSchedules(final List<Schedule> schedules) {
//    this.schedules = schedules;
//  }
//
//  /**
//   * @param sensors the sensors to set
//   */
//  public void setSensors(final List<Sensor> sensors) {
//    this.sensors = sensors;
//  }
//
//  /**
//   * @param events the events to set
//   */
//  public void setEvents(final List<Event> events) {
//    this.events = events;
//  }

  /**
   * @return the fromDate
   */
  @Column(name = "FromDate")
  @Temporal(TemporalType.TIMESTAMP)
  public Date getFromDate() {
    return fromDate;
  }

  /**
   * @param fromDate the from-date to set
   */
  public void setFromDate(final Date fromDate) {
    this.fromDate = fromDate;
  }

  /**
   * @return the toDate
   */
  @Column(name = "ToDate")
  @Temporal(TemporalType.TIMESTAMP)
  public Date getToDate() {
    return toDate;
  }

  /**
   * @param toDate the to-date to set
   */
  public void setToDate(final Date toDate) {
    this.toDate = toDate;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("id = " + id);
    sb.append("serviceCode = " + serviceCode);
    sb.append("customerName = " + customerName);
    sb.append("buildingName = " + buildingName);
    sb.append("address = " + address);
    sb.append("telephone = " + telephone);
    sb.append("serviceType = " + serviceType);
    sb.append("emergencyTelephonePrimary = " + emergencyTelephonePrimary);
    sb.append("emergencyTelephoneSecondary = " + emergencyTelephoneSecondary);
    return sb.toString();
  }
}
