package sosafe.dao;

import sosafe.model.Bill;
import sosafe.model.Bill.Month;
import sosafe.model.Day;
import sosafe.model.Event;
import sosafe.model.Event.EventType;
import sosafe.model.Location;
import sosafe.model.Schedule;
import sosafe.model.Schedule.ScheduleType;
import sosafe.model.Sensor;
import sosafe.model.Sensor.SensorType;
import sosafe.model.Service;
import sosafe.model.Service.ServiceType;
import sosafe.model.Time;

import java.util.Date;
import java.util.List;

/**
 * Tester for DAO and model objects
 */
public class TestDao {
  public static void main(final String[] args) {
	  
    final DayDao dayDao = new DayDao(null);
    final ServiceDao serviceDao = new ServiceDao(null);
    final ScheduleDao scheduleDao = new ScheduleDao(null);
    final LocationDao locationDao = new LocationDao(null);
    final BillDao billDao = new BillDao(null);
    final SensorDao sensorDao = new SensorDao(null);
    final EventDao eventDao = new EventDao(null);

    // Data for Service object
    final String serviceCode = "SERVICE003"; // Unique
    final String customerName = "SCU";
    final String buildingName = "Library";
    final String address = "1234 ABCD XYZ 123456";
    final String telephone = "(123) 456 7890";
    final ServiceType serviceType = ServiceType.BREAKIN;
    final String emergencyTelephonePrimary = "(123) 456 7890";
    final String emergencyTelephoneSecondary = "(123) 456 7890";
    final Date fromDate = new Date();
    final Date toDate = new Date();

    //Data for Schedule object
    final ScheduleType scheduleType = ScheduleType.SPECIAL;
    
    // Data for Location object
    final String locationCode = "LOCATION003"; // Unique
    final String locationName = "Room 3"; // Unique

    // Data for Bill object
    final String billCode = "BILL003"; // Unique
    final Month month = Month.AUGUST;
    final String year = "2015";
    final Date dueDate = new Date();
    final Integer lateFeePerMonth = 5;

    // Data for Sensor object
    final String sensorCode = "SENSOR003"; // Unique
    final SensorType sensorType = SensorType.MOTION;
    final Boolean enabled = true;
    final Integer price = 100;

    // Data for Event object
    final String eventCode = "EVENT003"; // Unique
    final EventType eventType = EventType.BREAKIN;
    final Date timeOfOccurence = new Date();
    final Boolean alarmRaised = true;
    final Boolean callMade = true;
    final String callMadeToPhone = "(123) 456 7890";
    final Date firstCallMadeAt = new Date();
    final Integer numberOfCallsMade = 2;
    final Boolean ResponseReceived = true;
    final Date ResponseReceivedAt = new Date();
    final String ResponseCode = "SCU123";

    // Data for Day object
    final String dayName = "THURSDAY";// Unique


    // ############################ DELETING ############################

    // DELETE all Bills
    List<Bill> bills = null;
    try {
    	bills = billDao.getBills();
    }catch(javax.persistence.NoResultException e) {}
    if (bills != null && bills.size() > 0) {
    	for(Bill bill : bills) {
    		System.out.println("\n\n================================= Bill ===============================");
    		System.out.println("\nDeleting bill = " + bill.getBillCode());
    		billDao.removeBill(bill.getId());
    	}
    }

    // DELETE all Sensors
    List<Sensor> sensors = null;
    try {
    	sensors  = sensorDao.getSensors();
    }catch(javax.persistence.NoResultException e) {}
    if (sensors != null && sensors.size() > 0) {
    	for(Sensor sensor : sensors) {
    		System.out.println("\n\n================================== Sensor ==============================");
    		System.out.println("\nDeleting sensor = " + sensor.getSensorCode());
    		sensorDao.removeSensor(sensor.getId());
    	}
    }

    // DELETE all Events
    List<Event> events = null;
    try {
    	events  = eventDao.getEvents();
    }catch(javax.persistence.NoResultException e) {}
    if (events != null && events.size() > 0) {
    	for(Event event : events) {
    		System.out.println("\n\n================================= Event ===============================");
    		System.out.println("\nDeleting event = " + event.getEventCode());
    		eventDao.removeEvent(event.getId());
    	}
    }

    // DELETE all Days
    List<Day> days = null;
    try {
    	days  = dayDao.getDays();
    }catch(javax.persistence.NoResultException e) {}
    if (days != null && days.size() > 0) {
    	for(Day day : days) {
    		System.out.println("\n\n================================= Day ===============================");
    		System.out.println("\nDeleting day = " + day.getName());
    		dayDao.removeDay(day.getId());
    	}
    }

    // DELETE all Schedules
    List<Schedule> schedules = null;
    try {
    	schedules  = scheduleDao.getSchedules();
    }catch(javax.persistence.NoResultException e) {}
    if (schedules != null && schedules.size() > 0) {
    	for(Schedule schedule : schedules) {
    		System.out.println("\n\n================================= Schedule ===============================");
    		System.out.println("\nDeleting schedule = " + schedule.getScheduleType());
    		scheduleDao.removeSchedule(schedule.getId());
    	}
    }

    // DELETE all Locations
    List<Location> locations = null;
    try {
    	locations  = locationDao.getLocations();
    }catch(javax.persistence.NoResultException e) {}
    if (locations != null && locations.size() > 0) {
    	for(Location location : locations) {
    		System.out.println("\n\n================================ Location ================================");
    		System.out.println("\nDeleting location = " + location.getLocationCode());
    		locationDao.removeLocation(location.getId());
    	}
    }

    // DELETE all Services
    List<Service> services = null;
    try {
    	services  = serviceDao.getServices();
    }catch(javax.persistence.NoResultException e) {}
    if (services != null && services.size() > 0) {
    	for(Service service : services) {
    		System.out.println("\n\n================================ Service ================================");
    		System.out.println("\nDeleting service = " + service.getServiceCode());
    		serviceDao.removeService(service.getId());
    	}
    }

    // ############################ INSERTING ############################


    // ############################ Service ############################

  	// INSERT a Service
  	final Service serviceNew = new Service(serviceCode, customerName, buildingName, address,
		telephone, serviceType, emergencyTelephonePrimary, emergencyTelephoneSecondary, fromDate, toDate);
	serviceDao.insertService(serviceNew);
	System.out.println("\n\n================================== Service ==============================");
	System.out.println("\nInserted a Service");

    // RETRIEVE a Service
    Service serviceJustCreated = null;
    try {
    	serviceJustCreated = serviceDao.getServiceByServiceCode(serviceCode);
        if (serviceJustCreated != null) {
            System.out.println("\nRetrieved service = " + serviceJustCreated.getServiceType());
          }
    }catch(javax.persistence.NoResultException e) {}


    // ############################ Location ############################
    // INSERT a Location
    final Location locationNew = new Location(locationCode, locationName, serviceJustCreated);
    locationDao.insertLocation(locationNew);
	System.out.println("\n\n================================= Location ===============================");
    System.out.println("\nInserted a Location");

    // RETRIEVE a Location
    Location locationJustCreated = null;
    try {
    	locationJustCreated = locationDao.getLocationByLocationCode(locationCode);
        if (locationJustCreated != null) {
            System.out.println("\nRetrieved location = " + locationJustCreated.getLocationCode());
          }
    }catch(javax.persistence.NoResultException e) {}

    // ############################ Bill ############################
    
    // INSERT a Bill
    final Bill billNew = new Bill(billCode, serviceJustCreated, month, year, dueDate, lateFeePerMonth);
    billDao.insertBill(billNew);
	System.out.println("\n\n================================= Bill ===============================");
    System.out.println("\nInserted a Bill");

    // RETRIEVE a Bill
    Bill billJustCreated = null;
    try {
    	billJustCreated = billDao.getBillByBillCode(billCode);
        if (billJustCreated != null) {
            System.out.println("\nRetrieved bill = " + billJustCreated.getBillCode());
          }
    }catch(javax.persistence.NoResultException e) {}

    // ############################ Sensor ############################
    // INSERT a Sensor
    final Sensor sensorNew = new Sensor(sensorCode, serviceJustCreated, locationJustCreated, sensorType, enabled, price);
    sensorDao.insertSensor(sensorNew);
	System.out.println("\n\n================================ Sensor ================================");
    System.out.println("\nInserted a Sensor");

    // RETRIEVE a Sensor
    Sensor sensorJustCreated = null;
    try {
    	sensorJustCreated = sensorDao.getSensorBySensorCode(sensorCode);
        if (sensorJustCreated != null) {
            System.out.println("\nRetrieved sensor = " + sensorJustCreated.getSensorCode());
          }
    }catch(javax.persistence.NoResultException e) {}


    // ############################ Event ############################
    // INSERT an Event
    final Event eventNew = new Event(eventCode, serviceJustCreated, locationJustCreated, eventType, timeOfOccurence,
        alarmRaised, callMade, callMadeToPhone, firstCallMadeAt, numberOfCallsMade,
        ResponseReceived, ResponseReceivedAt, ResponseCode);
    eventDao.insertEvent(eventNew);
	System.out.println("\n\n=============================== Event =================================");
    System.out.println("\nInserted an Event");

    // RETRIEVE an Event
    Event eventJustCreated = null;
    try {
    	eventJustCreated = eventDao.getEventByEventCode(eventCode);
        if (eventJustCreated != null) {
            System.out.println("\nRetrieved event = " + eventJustCreated.getEventCode());
          }
    }catch(javax.persistence.NoResultException e) {}

    // ############################ Day ############################
    
    // INSERT a Day
    final Day dayNew = new Day(dayName);
  	dayDao.insertDay(dayNew);
	System.out.println("\n\n================================== Day ==============================");
  	System.out.println("\nInserted a Day");
  
  	// RETRIEVE a Day
	  System.out.println("\nRetrieved Day = " + dayDao.getDayByName("SUNDAY"));
	  System.out.println("\nRetrieved Day = " + dayDao.getDayByName("MONDAY"));
	  System.out.println("\nRetrieved Day = " + dayDao.getDayByName("TUESDAY"));
	  System.out.println("\nRetrieved Day = " + dayDao.getDayByName("WEDNESDAY"));
	  System.out.println("\nRetrieved Day = " + dayDao.getDayByName("THURSDAY"));
	  System.out.println("\nRetrieved Day = " + dayDao.getDayByName("FRIDAY"));
	  System.out.println("\nRetrieved Day = " + dayDao.getDayByName("SATURDAY"));
  	
    // ############################ Schedule ############################
    
    // INSERT a Schedule
    final Schedule scheduleNew = new Schedule(serviceJustCreated, scheduleType, new Time(1,1,1).getDate(), new Time(2,2,2).getDate());
    scheduleNew.addDay(dayDao.getDayByName("SUNDAY"));
    scheduleNew.addDay(dayDao.getDayByName("MONDAY"));
    scheduleNew.addDay(dayDao.getDayByName("TUESDAY"));
    scheduleNew.setFromDateTime(new Time(01,02,03).getDate());
    scheduleNew.setToDateTime(new Time(04,05,06).getDate());
    scheduleDao.insertSchedule(scheduleNew);
	System.out.println("\n\n================================ Schedule ================================");
    System.out.println("\nInserted a Schedule");

    // RETRIEVE a Schedule
    Schedule scheduleJustCreated = null;
    try {
    	scheduleJustCreated = scheduleDao.getSchedulesByServiceCode(serviceCode).get(0);
        if (scheduleJustCreated != null) {
            System.out.println("\nRetrieved schedule = " + scheduleJustCreated.getScheduleType());
            System.out.println("\nRetrieved schedule: from time = " + scheduleJustCreated.getFromTime());
            System.out.println("\nRetrieved schedule: to time   = " + scheduleJustCreated.getToTime());
        }
    }catch(javax.persistence.NoResultException e) {}
  }
}
