package homesecurity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sosafe.dao.SensorDao;
import sosafe.dao.ServiceDao;

import sosafe.model.Sensor;
import sosafe.model.Sensor.SensorType;
import sosafe.model.Service;
import sosafe.model.Service.ServiceType;

/**
 * Billing Card panel.
 */
public class BillingCard extends JPanel {
	private static final long serialVersionUID = 4781194932732888836L;
	private final ServiceDao serviceDao = new ServiceDao(null);
	//private final LocationDao locationDao = new LocationDao(null);
	private final SensorDao sensorDao = new SensorDao(null);

	private Service service = null;
	//private List<Location> locations = null;
	private List<Sensor> sensors = null;
	
	public BillingCard() {
		
		JPanel billPanel = new JPanel();
		//Get Service
    	List<Service> services = serviceDao.getServices();
    	if(services != null && services.size() > 0) {
    		service = services.get(0);
        	System.out.println("\nRetrieved service = " + service.getServiceCode());
    	}

    	//Get Locations by Service Code
    	//locations = locationDao.getLocationsByServiceId(service.getId());

    	//Get Sensors by Service Code
        sensors = sensorDao.getSensorsByServiceCode(service.getServiceCode());
        
        billPanel.setLayout(null);
        //billPanel.setBackground(Color.YELLOW);
        
        JLabel bill = new JLabel("Billing Information");
        bill.setBounds(50, 10, 200, 25);
        JLabel serviceIdLabel = new JLabel("Service control Id:");
        JTextField serviceIdField = new JTextField();
        serviceIdLabel.setBounds(90, 50, 200, 25);
        serviceIdField.setBounds(250, 50, 200, 25);
        serviceIdField.setText(service.getServiceCode());
        
        JLabel customerNameLabel = new JLabel("Customer Name");
        JTextField customerNameField = new JTextField();
        customerNameLabel.setBounds(90, 90, 200, 25);
        customerNameField.setBounds(250, 90, 200, 25);
        customerNameField.setText(service.getCustomerName());
        
        JLabel customeradressLabel = new JLabel("Customer Address");
        JTextField customeradressField = new JTextField();
        customeradressLabel.setBounds(90, 130, 200, 25);
        customeradressField.setBounds(250, 130, 200, 25);
        customeradressField.setText(service.getAddress());
        
        JLabel contactNumberLabel = new JLabel("Contact Number");
        JTextField contactNumberField = new JTextField();
        contactNumberLabel.setBounds(90, 170, 200, 25);
        contactNumberField.setBounds(250, 170, 200, 25);
        contactNumberField.setText(service.getTelephone());
        
        JLabel customerTelephoneLabel = new JLabel("Emergency Phone Number");
        JTextField customerTelephoneField = new JTextField();
        customerTelephoneLabel.setBounds(90, 210, 200, 25);
        customerTelephoneField.setBounds(250, 210, 200, 25);
        customerTelephoneField.setText(service.getEmergencyTelephonePrimary());
        
        JLabel coverageDateLabel = new JLabel("Coverage Date");
        coverageDateLabel.setBounds(90, 250, 200, 25);
        JLabel billFromDateLabel = new JLabel("From:");
        billFromDateLabel.setBounds(250, 250, 200, 25);
        JTextField billFromDateField = new JTextField();
        billFromDateField.setBounds(300, 250, 150, 30);
        billFromDateField.setText(new SimpleDateFormat("MM/dd/yyyy").format(service.getFromDate()));
        
        JLabel billToDateLabel = new JLabel("To:");
        billToDateLabel.setBounds(530, 250, 200, 25);
        JTextField billToDateField = new JTextField();
        billToDateField.setBounds(560, 250, 150, 30);
        billToDateField.setText(new SimpleDateFormat("MM/dd/yyyy").format(service.getToDate()));
        
        JLabel serviceTypeLabel = new JLabel("Service Type");
        serviceTypeLabel.setBounds(90, 290, 200, 25);
        JCheckBox serviecTypeMotionBtn = new JCheckBox("Break-In");
        serviecTypeMotionBtn.setBounds(250, 290, 90, 25);
        if(service.getServiceType() == ServiceType.BREAKIN || service.getServiceType() == ServiceType.BREAKIN_N_FIRE) {
        	serviecTypeMotionBtn.setSelected(true);
        }
        
        JCheckBox serviecTypeFireBtn = new JCheckBox("Fire");
        if(service.getServiceType() == ServiceType.FIRE || service.getServiceType() == ServiceType.BREAKIN_N_FIRE) {
        	serviecTypeFireBtn.setSelected(true);
        }
        
        serviecTypeFireBtn.setBounds(560, 290, 90, 25);
        JLabel countMotionSensorsLabel = new JLabel("No.of Motion Sensors");
        countMotionSensorsLabel.setBounds(90, 330, 200, 25);
        JTextField countMotionSensorsField = new JTextField();
        countMotionSensorsField.setBounds(250, 330, 200, 25);
        int motionSensors = 0;
        int temperatureSensors = 0;
        int motionSensorPrice = 0;
        int temperatureSensorPrice = 0;
        if(sensors != null) {
	        for(Sensor sensor : sensors) {
	        	if(sensor != null) {
		        	if(sensor.getSensorType() == SensorType.MOTION && sensor.isEnabled()) {
		        		motionSensors++;
		        		if(sensor.getPrice() != null) {
		        			motionSensorPrice = sensor.getPrice();
		        		}
		        	} else if(sensor.getSensorType() == SensorType.TEMPERATURE && sensor.isEnabled()) {
		        		temperatureSensors++;
		        		if(sensor.getPrice() != null) {
		        			temperatureSensorPrice = sensor.getPrice();
		        		}
		        	}
	        	}
	        }
        }
        countMotionSensorsField.setText(""+motionSensors);
        
        
        JLabel countTempSensorsLabel = new JLabel("No.of Temp.Sensors");
        countTempSensorsLabel.setBounds(90, 370, 200, 25);
        JTextField countTempSensorsField = new JTextField();
        countTempSensorsField.setBounds(250, 370, 200, 25);
        countTempSensorsField.setText(""+temperatureSensors);
        
        JLabel countTotalSensorsLabel = new JLabel("Total No.of Sensors");
        countTotalSensorsLabel.setBounds(90, 410, 200, 25);
        JTextField countTotalSensorsField = new JTextField();
        countTotalSensorsField.setBounds(250, 410, 200, 25);
        int total = temperatureSensors + motionSensors;
        countTotalSensorsField.setText(""+(total));
        
        JLabel pricePerMotionSensorsLabel = new JLabel("Fee per Sensor");
        pricePerMotionSensorsLabel.setBounds(90, 450, 200, 25);
        JTextField pricePerMotionSensorsField = new JTextField();
        pricePerMotionSensorsField.setBounds(250, 450, 200, 25);
        if(sensors != null && sensors.size() > 0 && sensors.get(0) != null && 
        		sensors.get(0).getPrice() != null) {
        	pricePerMotionSensorsField.setText(""+motionSensorPrice);
        }
        
        JLabel pricePerTempSensorsLabel = new JLabel("Fee per Temp. Sensors");
        pricePerTempSensorsLabel.setBounds(90, 450, 200, 25);
        JTextField pricePerTempSensorsField = new JTextField();
        pricePerTempSensorsField.setBounds(250, 450, 200, 25);
        if(sensors != null && sensors.size() > 0 && sensors.get(0) != null && 
        		sensors.get(0).getPrice() != null) {
        	pricePerTempSensorsField.setText(""+temperatureSensorPrice);
        } else {
        	pricePerTempSensorsField.setText("0");
        }
        
        JLabel totalChargeLabel = new JLabel("Total Charge");
        totalChargeLabel.setBounds(90, 490, 200, 25);
        JTextField totalChargeField = new JTextField();
        totalChargeField.setBounds(250, 490, 200, 25);
        totalChargeField.setText(""+((motionSensors * motionSensorPrice) + (temperatureSensors * temperatureSensorPrice)));
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year  = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day   = cal.get(Calendar.DAY_OF_MONTH);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");	
    	Calendar calendar = new GregorianCalendar(year, month, day, 17, 00, 00);
        
        JLabel billDueDateLabel = new JLabel("Due date");
        billDueDateLabel.setBounds(90, 530, 200, 25);
        JTextField billDueDateField = new JTextField();
        billDueDateField.setBounds(250, 530, 200, 25);
        billDueDateField.setText(sdf.format(calendar.getTime()));

        billPanel.setLayout(null);
        
        billPanel.add(bill);
        billPanel.add(serviceIdLabel);
        billPanel.add(serviceIdField);
        billPanel.add(customerNameLabel);
        billPanel.add(customerNameField);
        billPanel.add(customeradressField);
        billPanel.add(customeradressLabel);
        billPanel.add(contactNumberLabel);
        billPanel.add(contactNumberField);
        billPanel.add(customerTelephoneLabel);
        billPanel.add(customerTelephoneField);
        billPanel.add(coverageDateLabel);
        billPanel.add(billFromDateLabel);
        billPanel.add(billToDateLabel);
      
       billPanel.add(billFromDateField);
       billPanel.add(billToDateField);
       billPanel.add(serviceTypeLabel);
       billPanel.add(serviecTypeMotionBtn);
       billPanel.add(serviecTypeFireBtn);
       billPanel.add(countMotionSensorsLabel);
       billPanel.add(countMotionSensorsField);
       billPanel.add(countTempSensorsLabel);
       billPanel.add(countTempSensorsField);
       billPanel.add(countTotalSensorsLabel);
       billPanel.add(countTotalSensorsField);
       billPanel.add(pricePerMotionSensorsLabel);
       billPanel.add(pricePerMotionSensorsField);
       billPanel.add(totalChargeLabel);
       billPanel.add(totalChargeField);
       billPanel.add(billDueDateLabel);
       billPanel.add(billDueDateField);
   
       this.add(billPanel);

		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
        JButton printBillBtn = new JButton("Print");
        printBillBtn.setBounds(190, 590, 90, 25);
		
		this.add(printBillBtn);
		
		printBillBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//generate pdf
			}
		});
	}

}
