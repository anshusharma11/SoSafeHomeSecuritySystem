package homesecurity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import sosafe.dao.*;
import sosafe.model.*;
import sosafe.model.Service.ServiceType;

public class ServiceCard extends JPanel {
	private static final long serialVersionUID = -7927016054931544392L;
	private JTextField customerField;
	private JTextField buildingField;
	private JTextField addressField;
	private JCheckBox breakIn;
	private JCheckBox fire;
	private JTextField primaryPhoneField;
	private JTextField secondaryPhoneField;
	private JButton saveBtn;

    private final ServiceDao serviceDao = new ServiceDao(null);
	private Service service = null;
	
	public ServiceCard() {
		this.setLayout(null);

    	//Get Service
    	List<Service> services = serviceDao.getServices();
    	if(services != null && services.size() > 0) {
    		service = services.get(0);
        	System.out.println("\nRetrieved service = " + service.getServiceCode());
    	}
    	
    	// Customer Name
		JLabel customerLabel = new JLabel("Customer Name:");
		customerLabel.setBounds(50, 100, 200, 25);

		customerField = new JTextField();
		customerField.setBounds(360, 100, 120, 25);

		if(service.getCustomerName() != null) {
			customerField.setText(service.getCustomerName());
		}
    	
    	// Building
		JLabel buildingLabel = new JLabel("Building Name:");
		buildingLabel.setBounds(50, 150, 200, 25);

		buildingField = new JTextField();
		buildingField.setBounds(360, 150, 120, 25);

		if(service.getBuildingName() != null) {
			buildingField.setText(service.getBuildingName());
		}
    	
    	// Address
		JLabel addressLabel = new JLabel("Address Name:");
		addressLabel.setBounds(50, 200, 200, 25);

		addressField = new JTextField();
		addressField.setBounds(360, 200, 120, 25);

		if(service.getAddress() != null) {
			addressField.setText(service.getAddress());
		}
		
		// Service Type
		JLabel serviceLabel = new JLabel("Service Type:");
		serviceLabel.setBounds(50, 250, 200, 25);
		breakIn = new JCheckBox("Break-In");
		breakIn.setBounds(280, 250, 100, 25);
		fire = new JCheckBox("fire");
		fire.setBounds(380, 250, 100, 25);
		if (service.getServiceType().equals(ServiceType.BREAKIN)) {
			breakIn.setSelected(true);
		} else if (service.getServiceType().equals(ServiceType.FIRE)) {
			fire.setSelected(true);
		} else if (service.getServiceType().equals(ServiceType.BREAKIN_N_FIRE)) {
			breakIn.setSelected(true);
			fire.setSelected(true);
		}
		
		//Phone numbers
		JLabel emerygencyPhone = new JLabel("Emergency Phone Number:");
		emerygencyPhone.setBounds(50, 300, 200, 25);

		JLabel primaryPhoneLabel = new JLabel("Primary No.");
		primaryPhoneLabel.setBounds(280, 300, 100, 25);

		primaryPhoneField = new JTextField();
		primaryPhoneField.setBounds(360, 300, 120, 25);

		JLabel secondaryPhoneLabel = new JLabel("Secondary No.");
		secondaryPhoneLabel.setBounds(500, 300, 200, 25);

		secondaryPhoneField = new JTextField();
		secondaryPhoneField.setBounds(600, 300, 120, 25);

		if(service.getEmergencyTelephonePrimary() != null) {
			primaryPhoneField.setText(service.getEmergencyTelephonePrimary());
		}
		if(service.getEmergencyTelephoneSecondary() != null) {
			secondaryPhoneField.setText(service.getEmergencyTelephoneSecondary());
		}
		
        JLabel fromDateLabel = new JLabel("From:");
        JLabel toDateLabel = new JLabel("To:");
      fromDateLabel.setBounds(230, 350, 100, 25);
      toDateLabel.setBounds(500, 350, 100, 25);

      Date fromdate = service.getFromDate();
      Calendar cal1 = Calendar.getInstance();
      cal1.setTime(fromdate);
      int yearFrom  = cal1.get(Calendar.YEAR);
      int monthFrom = cal1.get(Calendar.MONTH);
      int dayFrom   = cal1.get(Calendar.DAY_OF_MONTH);
      
      Date todate = service.getToDate();
      Calendar cal2 = Calendar.getInstance();
      cal2.setTime(todate);
      int yearTo  = cal2.get(Calendar.YEAR);
      int monthTo = cal2.get(Calendar.MONTH);
      int dayTo   = cal2.get(Calendar.DAY_OF_MONTH);
        
      UtilDateModel modelScheduleFrom = new UtilDateModel();
      modelScheduleFrom.setDate(yearFrom, monthFrom, dayFrom);
      modelScheduleFrom.setSelected(true);
      // Need this...
      Properties pScheduleFrom = new Properties();
      pScheduleFrom.put("text.today", "Today");
      pScheduleFrom.put("text.month", "Month");
      pScheduleFrom.put("text.year", "Year");
      JDatePanelImpl datePanel = new JDatePanelImpl(modelScheduleFrom, pScheduleFrom);
      // Don't know about the formatter, but there it is...
      final JDatePickerImpl scheduleDatePickerFrom = new JDatePickerImpl(datePanel, new DateLabelFormatter());
      scheduleDatePickerFrom.setBounds(280, 350, 150, 35);
      
      UtilDateModel modelScheduleTo = new UtilDateModel();
      modelScheduleTo.setDate(yearTo, monthTo, dayTo);
      modelScheduleTo.setSelected(true);
      // Need this...
      Properties pScheduleTo = new Properties();
      pScheduleTo.put("text.today", "Today");
      pScheduleTo.put("text.month", "Month");
      pScheduleTo.put("text.year", "Year");
      JDatePanelImpl scheduleDatePanelTo = new JDatePanelImpl(modelScheduleTo, pScheduleTo);
      // Don't know about the formatter, but there it is...
      final JDatePickerImpl scheduleDatePickerTo = new JDatePickerImpl(scheduleDatePanelTo, new DateLabelFormatter());
      scheduleDatePickerTo.setBounds(530, 350, 150, 35);
      
		//Save
		saveBtn = new JButton("Save");
		saveBtn.setBounds(400, 400, 100, 25);

		this.add(customerLabel);
		this.add(customerField);
		this.add(buildingLabel);
		this.add(buildingField);
		this.add(addressLabel);
		this.add(addressField);
		this.add(serviceLabel);
		this.add(breakIn);
		this.add(fire);
		this.add(emerygencyPhone);
		this.add(primaryPhoneLabel);
		this.add(primaryPhoneField);
		this.add(secondaryPhoneLabel);
		this.add(secondaryPhoneField);
		this.add(fromDateLabel);
		this.add(scheduleDatePickerFrom);
		this.add(toDateLabel);
		this.add(scheduleDatePickerTo);

		this.add(saveBtn);

		saveBtn.addActionListener(new ActionListener() {
			// @Override
			public void actionPerformed(ActionEvent event) {
				
				service.setCustomerName(customerField.getText());
				service.setBuildingName(buildingField.getText());
				service.setAddress(addressField.getText());
				if(breakIn.isSelected() && !fire.isSelected()) {
					service.setServiceType(ServiceType.BREAKIN);
				} else if(fire.isSelected() && !breakIn.isSelected()) {
					service.setServiceType(ServiceType.FIRE);
				} else if(breakIn.isSelected() && fire.isSelected()) {
					service.setServiceType(ServiceType.BREAKIN_N_FIRE);
				}
				service.setEmergencyTelephonePrimary(primaryPhoneField.getText());
				service.setEmergencyTelephoneSecondary(secondaryPhoneField.getText());

				Date fromDate = (Date) scheduleDatePickerFrom.getModel().getValue();
				Date toDate = (Date) scheduleDatePickerTo.getModel().getValue();
				service.setFromDate(fromDate);
				service.setToDate(toDate);
				serviceDao.updateService(service);
			}
		});
	}

    public class DateLabelFormatter extends AbstractFormatter {
		private static final long serialVersionUID = 7684375604600696666L;
		private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }
    }
}
