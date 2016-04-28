package homesecurity;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sosafe.dao.EventDao;
import sosafe.dao.LocationDao;
import sosafe.dao.ServiceDao;
import sosafe.model.Event;
import sosafe.model.Event.EventType;
import sosafe.model.Location;
import sosafe.model.Sensor.SensorType;
import sosafe.model.Service;
import sosafe.model.Time;

public class EventCard extends JPanel{
	
	private static final long serialVersionUID = 5058219394115243680L;
	JPanel panel1 = null;
	JPanel panel2 = null;
	JPanel panel3 = null;
	JPanel panel4 = null;
	JPanel panel5 = null;
	JPanel panel6 = null;
	JPanel panel7 = null;
	JPanel panel8 = null;
	JPanel panel9 = null;
	JPanel panel10 = null;
	JPanel panel11 = null;
	
	JTextField sID = null;
	JTextField building = null;
	JTextField t1 = null;
	JTextField timeField = null;
	JTextField dateField = null;
	JTextField alarmStatus = null;
	JTextField callTime = null;
	JTextField callNumber = null;
	JTextField resReceived = null;
	JTextField resTime = null;
	JTextField resCode = null;
	
	private final ServiceDao serviceDao = new ServiceDao(null);
	private final LocationDao locationDao = new LocationDao(null);
	private final EventDao eventDao = new EventDao(null);

	private Service service = null;
	private List<Location> locations = null;
	
	public EventCard(){
		
		//Get Service
    	List<Service> services = serviceDao.getServices();
    	if(services != null && services.size() > 0) {
    		service = services.get(0);
        	System.out.println("\nRetrieved service = " + service.getServiceCode());
    	}

    	//Get Locations by Service Code
    	locations = locationDao.getLocationsByServiceId(service.getId());
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		JPanel serviceID = createIDPanel();
		JPanel location = createLocationPanel();
		JPanel type = createTypePanel();
		JPanel time = createTimePanel();
		JPanel date = createDatePanel();
		JPanel alarm = createAlarmPanel();
		JPanel timeCall = createCallPanel();
		JPanel numCall = createNumCallsPanel();
		JPanel response = createResponsePanel();
		final JPanel resTime = createResTimePanel();
		final JPanel resCode = createResCodePanel();
		JPanel action = createActionPanel();

		this.add(serviceID);
		this.add(location);
		this.add(type);
		this.add(time);
		this.add(date);
		this.add(alarm);
		this.add(timeCall);
		this.add(numCall);
		this.add(response);
		this.add(resTime);
		this.add(resCode);
		this.add(action);
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

		JButton save = new JButton("Get Event Details");
		save.setBounds(190, 590, 90, 25);
		
		save.addActionListener(new ActionListener() {
			// @Override
			public void actionPerformed(ActionEvent event) {

				eventDao.removeAllEvents();

				 sID.setText("");
				 building.setText("");
				 t1.setText("");
				 timeField.setText("");
				 dateField.setText("");
				 alarmStatus.setText("");
				 callTime.setText("");
				 callNumber.setText("");
				 resReceived.setText("");

				 panel1.revalidate();
				 panel2.revalidate();
				 panel3.revalidate();
				 panel4.revalidate();
				 panel5.revalidate();
				 panel6.revalidate();
				 panel7.revalidate();
				 panel8.revalidate();
				 panel9.revalidate();
				 panel10.revalidate();
				 panel11.revalidate();
				 
				for(int i=0; i<5; i++) {
					Toolkit.getDefaultToolkit().beep();
					try {
						Thread.sleep(1000); // 10 seconds
					} catch (InterruptedException e) {}
				}
				Event eventNew = new Event();
				eventNew.setEventCode("EVENT00"+new Random().nextInt(1000+1));
				eventNew.setService(service);
				if(locations != null && locations.size() > 0) {
					eventNew.setLocation(locations.get(new Random().nextInt(locations.size())));
				}
				if(0 == new Random().nextInt(2)) {
					eventNew.setEventType(EventType.BREAKIN);
				} else {
					eventNew.setEventType(EventType.FIRE);
				}
				eventNew.setTimeOfOccurence(new Date(new Date().getTime() - 300000));// Event occured 5 min ago
				eventNew.setAlarmRaised(true);
				eventNew.setCallMade(true);
				
				int callMadeToPhone = new Random().nextInt(99999999+1)+10000000;
				eventNew.setCallMadeToPhone(""+callMadeToPhone);
				eventNew.setFirstCallMadeAt(new Date(new Date().getTime() - 240000));//First call made 4 min ago
				eventNew.setNumberOfCallsMade(new Random().nextInt(10)+1);
				eventNew.setResponseReceived(true);
				eventNew.setResponseReceivedAt(new Date(new Date().getTime() - 240000));//Response received 1 min ago
				eventNew.setResponseCode("RES00"+new Random().nextInt(1000+1)+1);

				eventDao.insertEvent(eventNew);
				 
				 sID.setText(eventNew.getService().getServiceCode());
				 building.setText(eventNew.getService().getBuildingName());
				 t1.setText(eventNew.getEventType().name());
				 timeField.setText(new Time(eventNew.getTimeOfOccurence()).toString());
				 if(eventNew.getTimeOfOccurence() != null) {
				 	dateField.setText(new SimpleDateFormat("MM/dd/yyyy").format(eventNew.getTimeOfOccurence()));
				}
				 if(eventNew.getAlarmRaised()) {
					 alarmStatus.setText("YES");
				 } else {
					 alarmStatus.setText("NO");
				 }
				 if(eventNew.getFirstCallMadeAt() != null) {
					 callTime.setText(new SimpleDateFormat("MM/dd/yyyy").format(eventNew.getFirstCallMadeAt()));
				 }
				 callNumber.setText(eventNew.getCallMadeToPhone());
				 if(eventNew.getResponseReceived()) {
					 resReceived.setText("YES");
				 } else {
					 resReceived.setText("NO");
				 }

				 panel1.revalidate();
				 panel2.revalidate();
				 panel3.revalidate();
				 panel4.revalidate();
				 panel5.revalidate();
				 panel6.revalidate();
				 panel7.revalidate();
				 panel8.revalidate();
				 panel9.revalidate();
				 panel10.revalidate();
				 panel11.revalidate();
			}
		});
		this.add(save);
	}
	
	//Service ID panel
	public JPanel createIDPanel(){
		 panel2 = new JPanel();
		JLabel serID = new JLabel("Service Code #: ");
		sID = new JTextField(10);
		sID.setText(service.getServiceCode());
		panel2.add(serID);
		panel2.add(sID);
		serID.setBounds(90, 50, 200, 25);
		sID.setBounds(250, 50, 200, 25);
		return panel2;
	}
	
	
	//Location panel
	public JPanel createLocationPanel(){
		 panel1 = new JPanel(new FlowLayout());
		JLabel loc = new JLabel("Location: ");
		building = new JTextField(10);
		if(service != null) {
			building.setText(service.getBuildingName());
		}
		JTextField room = new JTextField(10);
		if(locations != null && locations.size() > 0) {
			room.setText(locations.get(0).getLocationName());
		}
		panel1.add(loc);
		panel1.add(building);
		panel1.add(room);
		return panel1;
	}
	
	//Simulation type panel
	public JPanel createTypePanel(){
		 panel3 =  new JPanel();
		JLabel type = new JLabel("Threat type: ");
		t1 = new JTextField(10);
		if(locations != null && locations.size() > 0 && locations.get(0).getSensors() != null 
				&& locations.get(0).getSensors().size() > 0) {
			if(locations.get(0).getSensors().get(0).getSensorType() == SensorType.MOTION) {
				t1.setText("BREAK-IN detected");
			} else {
				t1.setText("FIRE detected");
			}
		}
		panel3.add(type);
		panel3.add(t1);
		return panel3;
	}
	
	//Time panel
	public JPanel createTimePanel(){
		 panel4 = new JPanel();
		JLabel time = new JLabel("Time: ");
		timeField = new JTextField(10);
		timeField.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
		panel4.add(time);
		panel4.add(timeField);
		return panel4;
	}
	
	//Date panel
	public JPanel createDatePanel(){
		 panel5 = new JPanel();
		JLabel date = new JLabel("Date: ");
		dateField = new JTextField(10);
		dateField.setText(new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
		panel5.add(date);
		panel5.add(dateField);
		return panel5;
	}
	
	//Alarm panel
	public JPanel createAlarmPanel(){
		 panel6 = new JPanel();
		JLabel alarm = new JLabel("Alarm Raised: ");
		alarmStatus = new JTextField(10);
		alarmStatus.setText("RAISED!");
		panel6.add(alarm);
		panel6.add(alarmStatus);
		return panel6;
	}
	
	//Time of first call panel
	public JPanel createCallPanel(){
		 panel7 = new JPanel();
		JLabel call = new JLabel("Time of first call: ");
		callTime = new JTextField(10);
		callTime.setText(new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
		panel7.add(call);
		panel7.add(callTime);
		return panel7;
	}
	
	//Number of calls made panel
	public JPanel createNumCallsPanel(){
		 panel8 = new JPanel();
		JLabel callNum = new JLabel("Number of calls made: ");
		callNumber = new JTextField(10);
		callNumber.setText(""+new Random().nextInt(5)+1);
		panel8.add(callNum);
		panel8.add(callNumber);
		return panel8;
	}
	
	//Response received panel
	public JPanel createResponsePanel(){
		 panel9 = new JPanel();
		JLabel response = new JLabel("Response received: ");
		resReceived = new JTextField(10);
		resReceived.setText("RECEIVED!");
		panel9.add(response);
		panel9.add(resReceived);
		return panel9;
	}
	
	//Response time panel
	public JPanel createResTimePanel(){
		 panel10 = new JPanel();
		JLabel res2 = new JLabel("Time of response: ");
		resTime = new JTextField(15);
		resTime.setText(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()));
		panel10.add(res2);
		panel10.add(resTime);
		return panel10;
	}
	
	//Response code panel
	public JPanel createResCodePanel(){
		 panel11 = new JPanel();
		JLabel res3 = new JLabel("Response code: ");
		resCode = new JTextField(10);
		resCode.setText(""+new Random().nextInt(10000)+1);
		panel11.add(res3);
		panel11.add(resCode);
		return panel11;
	}
	
	//Panel for saving or seeing the list
	public JPanel createActionPanel(){
		JPanel panel12 = new JPanel();
		return panel12;
	}

}
