package sosafe.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Hibernate Entity mapped to Day table.
 **/
@Entity
@Table(name = "Day")
public class Day {
  private Long id; // DayId

  private String name;

  public Day() {}

  public Day(final String name) {
    this.name = name;
  }

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "DayId")
  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  /**
   * @return the name
   */
  @Column(name = "Name", nullable = false, unique = true)
  public String getName() {
    return name;
  }

  /**
   * @param name the DayName to set
   */
  public void setName(final String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Id = " + id);
    sb.append("name = " + name);
    return sb.toString();
  }
}
