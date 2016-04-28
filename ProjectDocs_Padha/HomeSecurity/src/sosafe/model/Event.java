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
 * Hibernate Entity mapped to Event table.
 **/
@Entity
@Table(name = "Event")
public class Event {
  public enum EventType {
    BREAKIN(1),
    FIRE(2);

    private int number;
  
    private EventType(int number) {
        this.number = number;
    }
  
    public int getEventType() {
        return number;
    }
  }

  private Long id; // EventId
  private String eventCode;
  private Service service;
  private Location location;
  private EventType eventType;
  private Date timeOfOccurence;
  private Boolean alarmRaised;
  private Boolean callMade;
  private String callMadeToPhone;
  private Date firstCallMadeAt;
  private Integer numberOfCallsMade;
  private Boolean responseReceived;
  private Date responseReceivedAt;
  private String responseCode;

  public Event() {}

  public Event(final String eventCode, final Service service, final Location location,
      final EventType eventType, final Date timeOfOccurence, final Boolean alarmRaised,
      final Boolean callMade, final String callMadeToPhone, final Date firstCallMadeAt,
      final Integer numberOfCallsMade, final Boolean ResponseReceived,
      final Date ResponseReceivedAt, final String ResponseCode) {
    this.eventCode = eventCode;
    this.service = service;
    this.location = location;
    this.eventType = eventType;
    this.timeOfOccurence = timeOfOccurence;
    this.alarmRaised = alarmRaised;
    this.callMade = callMade;
    this.callMadeToPhone = callMadeToPhone;
    this.firstCallMadeAt = firstCallMadeAt;
    this.numberOfCallsMade = numberOfCallsMade;
    responseReceived = ResponseReceived;
    responseReceivedAt = ResponseReceivedAt;
    responseCode = ResponseCode;
  }

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "EventId")
  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  /**
   * @return the eventCode
   */
  @Column(name = "EventCode", nullable = false, unique = true)
  public String getEventCode() {
    return eventCode;
  }

  /**
   * @param eventCode the eventCode to set
   */
  public void setEventCode(final String eventCode) {
    this.eventCode = eventCode;
  }

  /**
   * @return the scheduleType
   */
  @Column(name = "EventType")
  @Enumerated(EnumType.STRING)
  public EventType getEventType() {
    return eventType;
  }

  /**
   * @param eventType the EventType to set
   */
  public void setEventType(final EventType eventType) {
    this.eventType = eventType;
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
   * @return the timeOfOccurence
   */
  @Column(name = "TimeOfOccurence")
  @Temporal(TemporalType.TIMESTAMP)
  public Date getTimeOfOccurence() {
    return timeOfOccurence;
  }

  /**
   * @param timeOfOccurence the timeOfOccurence to set
   */
  public void setTimeOfOccurence(final Date timeOfOccurence) {
    this.timeOfOccurence = timeOfOccurence;
  }

  /**
   * @return the alarmRaised
   */
  @Column(name = "AlarmRaised")
  public Boolean getAlarmRaised() {
    return alarmRaised;
  }

  /**
   * @param alarmRaised the alarmRaised to set
   */
  public void setAlarmRaised(final Boolean alarmRaised) {
    this.alarmRaised = alarmRaised;
  }

  /**
   * @return the callMade
   */
  @Column(name = "CallMade")
  public Boolean getCallMade() {
    return callMade;
  }

  /**
   * @param callMade the callMade to set
   */
  public void setCallMade(final Boolean callMade) {
    this.callMade = callMade;
  }

  /**
   * @return the callMadeToPhone
   */
  @Column(name = "CallMadeToPhone")
  public String getCallMadeToPhone() {
    return callMadeToPhone;
  }

  /**
   * @param callMadeToPhone the callMadeToPhone to set
   */
  public void setCallMadeToPhone(final String callMadeToPhone) {
    this.callMadeToPhone = callMadeToPhone;
  }

  /**
   * @return the firstCallMadeAt
   */
  @Column(name = "FirstCallMadeAt")
  @Temporal(TemporalType.TIMESTAMP)
  public Date getFirstCallMadeAt() {
    return firstCallMadeAt;
  }

  /**
   * @param firstCallMadeAt the firstCallMadeAt to set
   */
  public void setFirstCallMadeAt(final Date firstCallMadeAt) {
    this.firstCallMadeAt = firstCallMadeAt;
  }

  /**
   * @return the numberOfCallsMade
   */
  @Column(name = "NumberOfCallsMade")
  public Integer getNumberOfCallsMade() {
    return numberOfCallsMade;
  }

  /**
   * @param numberOfCallsMade the numberOfCallsMade to set
   */
  public void setNumberOfCallsMade(final Integer numberOfCallsMade) {
    this.numberOfCallsMade = numberOfCallsMade;
  }

  /**
   * @return the responseReceived
   */
  @Column(name = "ResponseReceived")
  public Boolean getResponseReceived() {
    return responseReceived;
  }

  /**
   * @param responseReceived the responseReceived to set
   */
  public void setResponseReceived(final Boolean responseReceived) {
    this.responseReceived = responseReceived;
  }

  /**
   * @return the responseReceivedAt
   */
  @Column(name = "ResponseReceivedAt")
  @Temporal(TemporalType.TIMESTAMP)
  public Date getResponseReceivedAt() {
    return responseReceivedAt;
  }

  /**
   * @param responseReceivedAt the responseReceivedAt to set
   */
  public void setResponseReceivedAt(final Date responseReceivedAt) {
    this.responseReceivedAt = responseReceivedAt;
  }

  /**
   * @return the responseCode
   */
  @Column(name = "ResponseCode")
  public String getResponseCode() {
    return responseCode;
  }

  /**
   * @param responseCode the responseCode to set
   */
  public void setResponseCode(final String responseCode) {
    this.responseCode = responseCode;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Id = " + id);
    sb.append("service = " + service);
    sb.append("location = " + location);
    sb.append("eventType = " + eventType);
    sb.append("timeOfOccurence = " + timeOfOccurence);
    sb.append("alarmRaised = " + alarmRaised);
    sb.append("callMade = " + callMade);
    sb.append("callMadeToPhone = " + callMadeToPhone);
    sb.append("firstCallMadeAt = " + firstCallMadeAt);
    sb.append("numberOfCallsMade = " + numberOfCallsMade);
    sb.append("responseReceived = " + responseReceived);
    sb.append("responseReceivedAt = " + responseReceivedAt);
    sb.append("responseCode = " + responseCode);
    return sb.toString();
  }
}
