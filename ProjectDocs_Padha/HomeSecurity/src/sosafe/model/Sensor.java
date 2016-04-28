package sosafe.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Hibernate Entity mapped to Sensor table.
 **/
@Entity
@Table(name = "Sensor")
public class Sensor {
  public enum SensorType {
    MOTION(1),
    TEMPERATURE(2);

    private int number;
  
    private SensorType(int number) {
        this.number = number;
    }
  
    public int getSensorType() {
        return number;
    }
  }

  private Long id; // SensorId
  private String sensorCode;
  private Service service;
  private Location location;
  private SensorType sensorType;
  private Boolean enabled;
  private Integer price;

  public Sensor() {}

  public Sensor(final String sensorCode, final Service service, final Location location,
      final SensorType sensorType, final Boolean enabled, final Integer price) {
    this.sensorCode = sensorCode;
    this.service = service;
    this.location = location;
    this.sensorType = sensorType;
    this.enabled = enabled;
    this.price = price;
  }

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "SensorId")
  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  /**
   * @return the sensorCode
   */
  @Column(name = "SensorCode", nullable = false, unique = true)
  public String getSensorCode() {
    return sensorCode;
  }

  /**
   * @param sensorCode the sensorCode to set
   */
  public void setSensorCode(final String sensorCode) {
    this.sensorCode = sensorCode;
  }

  /**
   * @return the sensorType
   */
  @Column(name = "SensorType")
  @Enumerated(EnumType.STRING)
  public SensorType getSensorType() {
    return sensorType;
  }

  /**
   * @param sensorType the sensorType to set
   */
  public void setSensorType(final SensorType sensorType) {
    this.sensorType = sensorType;
  }

  /**
   * @return the enabled
   */
  @Column(name = "Enabled")
  public Boolean isEnabled() {
    return enabled;
  }

  /**
   * @param enabled the enabled to set
   */
  public void setEnabled(final Boolean enabled) {
    this.enabled = enabled;
  }

  /**
   * @return the location
   */
  @ManyToOne
  @JoinColumn(name = "LocationId")
  public Location getLocation() {
    return location;
  }

  /**
   * @param location the location to set
   */
  public void setLocation(final Location location) {
    this.location = location;
  }

  /**
   * @return the price
   */
  @Column(name = "Price")
  public Integer getPrice() {
    return price;
  }

  /**
   * @param price the price to set
   */
  public void setPrice(final Integer price) {
    this.price = price;
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

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Id = " + id);
    sb.append("sensorType = " + sensorType);
    sb.append("enabled = " + enabled);
    sb.append("location = " + location);
    sb.append("price = " + price);
    sb.append("service = " + service);
    return sb.toString();
  }
}
