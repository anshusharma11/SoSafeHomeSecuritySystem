package sosafe.model;

import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Hibernate Entity mapped to Schedule table.
 **/
@Entity
@Table(name = "Schedule")
public class Schedule {
  public enum ScheduleType {
    GENERAL(1),
    SPECIAL(2);

    private int number;
  
    private ScheduleType(int number) {
        this.number = number;
    }
  
    public int getScheduleType() {
        return number;
    }
  }

  private Long id; // ScheduleId
  private Service service;
  private ScheduleType scheduleType;

  private List<Day> days = new ArrayList<Day>();
  private Date fromTime;
  private Date toTime;

  public Schedule() {}

  public Schedule(
      final Service service, final ScheduleType scheduleType) {
    this.service = service;
    this.scheduleType = scheduleType;
  }

  public Schedule(
      final Service service, final ScheduleType scheduleType, final Date fromTime, final Date toTime) {
    this.service = service;
    this.scheduleType = scheduleType;
	this.fromTime = fromTime;
	this.toTime = toTime;
  }

  public Schedule(
      final Service service, final ScheduleType scheduleType, final Time fromTime, final Time toTime) {
    this.service = service;
    this.scheduleType = scheduleType;
	this.fromTime = fromTime.getDate();
	this.toTime = toTime.getDate();
  }

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "ScheduleId")
  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  /**
   * @return the scheduleType
   */
  @Column(name = "ScheduleType")
  @Enumerated(EnumType.STRING)
  public ScheduleType getScheduleType() {
    return scheduleType;
  }

  /**
   * @param scheduleType the scheduleType to set
   */
  public void setScheduleType(final ScheduleType scheduleType) {
    this.scheduleType = scheduleType;
  }

  /**
   * @return the service
   */
  @ManyToOne //(targetEntity=Service.class, cascade = CascadeType.ALL)
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
   * @return the fromTime
   */
  @Column(name = "FromTime")
  @Temporal(TemporalType.TIME)
  public Date getFromDateTime() {
    return fromTime;
  }

  /**
   * @param fromTime the from-date to set
   */
  public void setFromDateTime(final Date fromTime) {
		this.fromTime = fromTime;
  }

  /**
   * @return the toTime
   */
  @Column(name = "ToTime")
  @Temporal(TemporalType.TIME)
  public Date getToDateTime() {
    return toTime;
  }

  /**
   * @param toTime the to-date to set
   */
  public void setToDateTime(final Date toTime) {
		this.toTime = toTime;
  }

  /**
   * @return the fromTime
   */
  @Transient
  public Time getFromTime() {
    return new Time(fromTime);
  }

  /**
   * @param fromTime the from-date to set
   */
  public void setFromTime(final Time fromTime) {
		this.fromTime = fromTime.getDate();
  }

  /**
   * @return the toTime
   */
  @Transient
  public Time getToTime() {
    return new Time(toTime);
  }

  /**
   * @param toTime the to-date to set
   */
  public void setToTime(final Time toTime) {
		this.toTime = toTime.getDate();
  }

  /**
   * @return the days
   */
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "ScheduleDay", joinColumns = {@JoinColumn(name = "ScheduleId")},
      inverseJoinColumns = {@JoinColumn(name = "DayId")})
  public List<Day> getDays() {
    return days;
  }

  /**
   * @param days the days to set
   */
  public void setDays(final List<Day> days) {
    this.days = days;
  }

  /**
   * Adds a day to the Schedule and returns the Schedule object
   * @param day the day to be added
   */
  public Schedule addDay(final Day day) {
    this.days.add(day);
    return this;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Id = " + id);
    sb.append("service = " + service);
    sb.append("scheduleType = " + scheduleType);
    sb.append("fromTime = " + fromTime);
    sb.append("toTime = " + toTime);
    for (final Day day : days) {
      sb.append("day = " + day);
    }
    return sb.toString();
  }

	public boolean contains(Day dayNew) {
		if(dayNew != null) {
			if(days != null && days.size() > 0) {
				for(Day dayExisting : days) {
					if(dayNew.getName().equalsIgnoreCase(dayExisting.getName())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void removeDay(Day day) {
		for(Day dayExisting : days) {
			if(dayExisting.getName().equalsIgnoreCase(day.getName())) {
				days.remove(dayExisting);
				break;
			}
		}
	}
}

