# SoSafeHomeSecuritySystem
Home security system  developed using Java,j2EE, JPA,Swing, DAO.

1.0 Introduction
SoSafe Home Security Services offer microprocessor-based security systems for homes and large commercial properties. Currently they offer two types of services:
SoSafe Security Systems and service to protect homes against “break-ins” or an illegal entry and 
SoSafe Fire Alarm Systems service to protect homes against fires. 

1.1 A System Overview
The security system can be installed in a large commercial building to monitor one or more areas in the building. For the purpose of this discussion, a commercial building can be divided into sections, where each section is divided into rooms.
The system uses appropriate sensors, located at specific locations throughout the buildings to detect a break-in and set off an alarm/call a monitoring service through a phone-line. Similarly, the system provides sensors for fire detection and calls the monitoring service. In future, SoSafe plans to expand their services to monitoring the safety of senior citizens living by themselves.

When a client/customer (building management) buys the services of a security system, the client can choose to include all or some of the sections of the building. Accordingly, the sensors will be installed in those areas. 
The system consists of sensors (motion sensors and temperature sensors) located at various places in a building. Each sensor is associated with an unique number and its location. 
The system provides a wall-mounted control panel (with a display panel and function keys), using which the customer can configure the system, monitor and change the configuration. For example, the customer can selectively enable/disable specific (or all) sensors.
The system (with security system or fire alarm system or both) can be programmed by the customer, to telephone a monitoring agency when a situation is detected. 
A master password is programmed for arming and disarming the system. The system can be programmed for arming the fire alarms separately from security alarms. The control panel’s display shows if the system is on, off, malfunctioning and/or if certain specific sensors are inactive. 
The system can be activated by turning on the start button or using a timer and a schedule. The customer programs the system to be activated and deactivated according to a schedule of times and days. For example, the system can be scheduled to be activated at 10 pm and deactivated at 7 pm from Monday through Friday; the system can be configured to have a different schedule on the weekends. Or the system can be on a specific schedule for a period of time (on a vacation schedule, for example).
The system can be configured to set off an alarm if a break-in is detected or a fire is detected or if the system is malfunctioning. Optionally, the system can call a monitoring-service (up to 2 telephone numbers can be input at the time of arming the system). The call is made to the telephone numbers given, identifying the time of the call, identification of the service number, location and the nature of the problem. The call is made once every minute until a response to the call is made by entering a response code into the system. The system also logs information about the problem detected, when the call is made and the time of response (noted by the response code being entered). A customer can selectively activate some or all of the sensors, the default case being all the sensors are activated. 

2.0 What you must do: Using Object-Oriented techniques design and implement a simulation of SoSafe Home Security System to offer the functionality described below.
 A homeowner/customer should be able to:

2.1 Configure the system to enable the customer to select the areas for security or fire alarm sensors
The system should display the layout of the building either graphically or textually to enable customer/home-owner to select the areas for sensor installation. A building (identified by a building id) is divided into sub areas where each subarea is identified by an area id. Sensors can be installed in the sub areas for sensor coverage.
A graphical layout showing a drawing of the building area with the subareas with ids.
A textual display showing the building and the subareas with ids.
The system should enable the customer to select (by click and select on the graphical display, for example) or by prompts and entering the ids of the areas from the console.
Once the selections are made for sensor coverage, the information is stored for the system to be configured in step 2.

2.2 Configure the system to enable the customer set a schedule 
In this step, the customer interacts with the graphical control panel to set a password, configure the schedule for the sensors to be activated and enables (activates) the sensors selected in Step 1. 

2.3 Create a Bill for the Customer
Based on the selections made by the customer, a bill is generated.  A separate bill is generated for each service (security, fire alarm etc) that the customer orders.
Common information in all the bills
Service Contract Id
Customer name
Address of the property
Contact Numbers (two numbers to call in case of emergency)
Customer Phone or email (serves as id)
Effective dates (of coverage)
Information specific to Security Customers
Pricing details: Customers are charged per each sensor installed in a sub-area. 
Total cost
Information specific to Fire Alarm Customers
Pricing details: Same as above
Total cost

3.0 Simulating a break-in or a fire (this step is really not part of the security system but is required in the demo to show the functionality of the system).
Once the customer configures and activates the security-system, the system continuously monitors for any break-ins or fire threats. In order to simulate a break-in (or a fire), you must provide a way on the GUI to show a break-in has occurred in a particular area of the building. For example, you may provide a pull-down menu with links showing the locations in the building where sensors are installed. When you select a link, it is considered that a break-in has occurred in that particular location. Now, all the events that must follow when the break in has occurred should be demonstrated.

4.0 Project documentation:
4.1 Part 1: The Analysis and Design documents 
The Analysis and Design document should include the following:
Three important Use-Cases to illustrate the functionality of the system
CRC cards for the most important classes 
Class Diagrams. 
Sequence Diagrams for the three Use cases. 
A State transition diagram that is relevant 
The Logical partitioning of the system into packages
NOTE: UML notation is to be used. 



