package sosafe.model;

import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Hibernate Entity mapped to Location table.
 **/
@Entity
@Table(name = "Location")
public class Location {
  private Long id; // LocationId

  private String locationCode;
  private String locationName;
  private Service service;
  private List<Sensor> sensors = new ArrayList<Sensor>();
  private List<Event> events = new ArrayList<Event>();

  public Location() {}

  public Location(final String locationCode, final String locationName, final Service service) {
    this.locationCode = locationCode;
    this.locationName = locationName;
    this.service = service;
  }

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "LocationId")
  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  /**
   * @return the locationCode
   */
  @Column(name = "LocationCode", nullable = false, unique = true)
  public String getLocationCode() {
    return locationCode;
  }

  /**
   * @param locationCode the locationCode to set
   */
  public void setLocationCode(final String locationCode) {
    this.locationCode = locationCode;
  }

  /**
   * @return the locationName
   */
  @Column(name = "LocationName")
  public String getLocationName() {
    return locationName;
  }

  /**
   * @param locationName the locationName to set
   */
  public void setLocationName(final String locationName) {
    this.locationName = locationName;
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
   * @param service the Service to set
   */
  public void setService(final Service service) {
    this.service = service;
  }

  /**
   * @return services
   */
  @OneToMany(targetEntity = Sensor.class, mappedBy = "location")
  public List<Sensor> getSensors() {
    return sensors;
  }

  /**
   * @param sensors the Sensors to set
   */
  public void setSensors(final List<Sensor> sensors) {
    this.sensors = sensors;
  }

  /**
   * Adds sensors to its list.
   * @param sensors the Sensors to be added
   */
  @Transient
  public void addSensor(final Sensor sensor) {
    sensors.add(sensor);
  }

  /**
   * Adds events to its list.
   * @param events the Events to be added
   */
  @Transient
  public void addEvent(final Event event) {
    events.add(event);
  }

  /**
   * @return services
   */
  @OneToMany(targetEntity = Event.class, mappedBy = "location")
  public List<Event> getEvents() {
    return events;
  }

  /**
   * @param events the Events to set
   */
  public void setEvents(final List<Event> events) {
    this.events = events;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Id = " + id);
    sb.append("locationName = " + locationName);
    sb.append("serviceCode = " + service.getServiceCode());
    return sb.toString();
  }
}
