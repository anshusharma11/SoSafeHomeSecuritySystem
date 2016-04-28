package homesecurity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.text.DateFormatter;

import sosafe.dao.DayDao;
import sosafe.dao.ScheduleDao;
import sosafe.dao.ServiceDao;
import sosafe.model.Day;
import sosafe.model.Schedule;
import sosafe.model.Service;
import sosafe.model.Schedule.ScheduleType;
import sosafe.model.Time;

public class ScheduleCard extends JPanel{
	
	private static final long serialVersionUID = 5020997314853883492L;
	private final ServiceDao serviceDao = new ServiceDao(null);
	private final ScheduleDao scheduleDao = new ScheduleDao(null);
	private final DayDao dayDao = new DayDao(null);
	private Service service = null;
	private List<Schedule> schedules = null;
	private List<Day> days = null;
	
	private String serviceCode = null;
	
	public ScheduleCard() {
		//Get Service
    	List<Service> services = serviceDao.getServices();
    	if(services != null && services.size() > 0) {
    		service = services.get(0);
    		serviceCode = service.getServiceCode();
        	System.out.println("\nRetrieved service = " + service.getServiceCode());
    	}

    	//Get Schedules by Service Code
        schedules = scheduleDao.getSchedulesByServiceCode(service.getServiceCode());
        List<Day> generalDays = null;
        List<Day> specialDays = null;
        if (schedules != null && schedules.size() > 0) {
        	for(Schedule schedule : schedules) {
        		System.out.println("\nRetrieved schedules = " + schedule.getScheduleType());
        		if(schedule.getScheduleType().equals(ScheduleType.GENERAL)) {
        			generalDays = schedule.getDays();
        		} else {
        			specialDays = schedule.getDays();
        		}
        		System.out.println("\nRetrieved schedules.#days = " + schedule.getDays().size());
        	}
        }
        
        //Get all days
        days = dayDao.getDays();
        
		this.setLayout(null);
		JLabel activationSchedule = new JLabel("Activation Schedule:");
        JLabel timeLabel = new JLabel("Enter Time:");
        JLabel fromTimeLabel = new JLabel("From:");
        JLabel toTimeLabel = new JLabel("To:");
        JLabel fromTimeLabel1 = new JLabel("From:");
        JLabel toTimeLabel1 = new JLabel("To:");
        JButton saveBtn = new JButton("Save");
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 24); // 24 == 12 PM == 00:00:00
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        SpinnerDateModel model1 = new SpinnerDateModel();
        model1.setValue(calendar.getTime());

        SpinnerDateModel model2 = new SpinnerDateModel();
        model2.setValue(calendar.getTime());

        SpinnerDateModel model3 = new SpinnerDateModel();
        model3.setValue(calendar.getTime());

        SpinnerDateModel model4 = new SpinnerDateModel();
        model4.setValue(calendar.getTime());

        final JSpinner spinner1From = new JSpinner(model1);
        JSpinner.DateEditor editor1From = new JSpinner.DateEditor(spinner1From, "HH:mm:ss");
        DateFormatter formatter1From = (DateFormatter) editor1From.getTextField().getFormatter();
        formatter1From.setAllowsInvalid(false); // this makes what you want
        formatter1From.setOverwriteMode(true);
        spinner1From.setEditor(editor1From);

        final JSpinner spinner1To = new JSpinner(model2);
        JSpinner.DateEditor editor1To = new JSpinner.DateEditor(spinner1To, "HH:mm:ss");
        DateFormatter formatter1To = (DateFormatter) editor1To.getTextField().getFormatter();
        formatter1To.setAllowsInvalid(false); // this makes what you want
        formatter1To.setOverwriteMode(true);
        spinner1To.setEditor(editor1To);
        
        final JSpinner spinner2From = new JSpinner(model3);
        JSpinner.DateEditor editor2From = new JSpinner.DateEditor(spinner2From, "HH:mm:ss");
        DateFormatter formatter2From = (DateFormatter) editor2From.getTextField().getFormatter();
        formatter2From.setAllowsInvalid(false); // this makes what you want
        formatter2From.setOverwriteMode(true);
        spinner2From.setEditor(editor2From);
        
        final JSpinner spinner2To = new JSpinner(model4);
        JSpinner.DateEditor editor2To = new JSpinner.DateEditor(spinner2To, "HH:mm:ss");
        DateFormatter formatter2To = (DateFormatter) editor2To.getTextField().getFormatter();
        formatter2To.setAllowsInvalid(false); // this makes what you want
        formatter2To.setOverwriteMode(true);
        spinner2To.setEditor(editor2To);
        
        JLabel groupDays = new JLabel("General Days:");
        activationSchedule.setBounds(50, 10, 200, 25);
        groupDays.setBounds(50, 60, 200, 25);
        final JCheckBox mondayCheck = new JCheckBox("Monday");
        mondayCheck.setBounds(100, 90, 200, 25);
        final JCheckBox tuesdayCheck = new JCheckBox("Tuesday");
        tuesdayCheck.setBounds(100, 120, 200, 25);
        final JCheckBox wednesdayCheck = new JCheckBox("Wednessday");
        wednesdayCheck.setBounds(100, 150, 200, 25);
        final JCheckBox thursdayCheck = new JCheckBox("Thrusday");
        thursdayCheck.setBounds(100, 180, 200, 25);
        final JCheckBox fridayCheck = new JCheckBox("Friday");
        fridayCheck.setBounds(100, 210, 200, 25);
        final JCheckBox saturdayCheck = new JCheckBox("Saturday");
        saturdayCheck.setBounds(100, 240, 200, 25);
        final JCheckBox sundayCheck = new JCheckBox("Sunday");
        sundayCheck.setBounds(100, 270, 200, 25);

        if(generalDays != null) {
	        for(Day day : generalDays) {
	        	if(day.getName().equalsIgnoreCase("MONDAY")) {
	        		mondayCheck.setSelected(true);
	        	} else if(day.getName().equalsIgnoreCase("TUESDAY")) {
	        		tuesdayCheck.setSelected(true);
	        	} else if(day.getName().equalsIgnoreCase("WEDNESDAY")) {
	        		wednesdayCheck.setSelected(true);
	        	} else if(day.getName().equalsIgnoreCase("THURSDAY")) {
	        		thursdayCheck.setSelected(true);
	        	} else if(day.getName().equalsIgnoreCase("FRIDAY")) {
	        		fridayCheck.setSelected(true);
	        	} else if(day.getName().equalsIgnoreCase("SATURDAY")) {
	        		saturdayCheck.setSelected(true);
	        	} else if(day.getName().equalsIgnoreCase("SUNDAY")) {
	        		sundayCheck.setSelected(true);
	        	}
	        }
        }
        
        JLabel specialDaysLabel = new JLabel("Special Days:");
        specialDaysLabel.setBounds(300, 60, 200, 25);
        final JCheckBox mondayCheck1 = new JCheckBox("Monday");
        mondayCheck1.setBounds(350, 90, 200, 25);
        final JCheckBox tuesdayCheck1 = new JCheckBox("Tuesday");
        tuesdayCheck1.setBounds(350, 120, 200, 25);
        final JCheckBox wednesdayCheck1 = new JCheckBox("Wednessday");
        wednesdayCheck1.setBounds(350, 150, 200, 25);
        final JCheckBox thursdayCheck1 = new JCheckBox("Thrusday");
        thursdayCheck1.setBounds(350, 180, 200, 25);
        final JCheckBox fridayCheck1 = new JCheckBox("Friday");
        fridayCheck1.setBounds(350, 210, 200, 25);
        final JCheckBox saturdayCheck1 = new JCheckBox("Saturday");
        saturdayCheck1.setBounds(350, 240, 200, 25);
        final JCheckBox sundayCheck1 = new JCheckBox("Sunday");
        sundayCheck1.setBounds(350, 270, 200, 25);
        timeLabel.setBounds(50,320, 200, 25);

        if(specialDays != null) {
	        for(Day day : specialDays) {
	        	if(day.getName().equalsIgnoreCase("MONDAY")) {
	        		mondayCheck1.setSelected(true);
	        	} else if(day.getName().equalsIgnoreCase("TUESDAY")) {
	        		tuesdayCheck1.setSelected(true);
	        	} else if(day.getName().equalsIgnoreCase("WEDNESDAY")) {
	        		wednesdayCheck1.setSelected(true);
	        	} else if(day.getName().equalsIgnoreCase("THURSDAY")) {
	        		thursdayCheck1.setSelected(true);
	        	} else if(day.getName().equalsIgnoreCase("FRIDAY")) {
	        		fridayCheck1.setSelected(true);
	        	} else if(day.getName().equalsIgnoreCase("SATURDAY")) {
	        		saturdayCheck1.setSelected(true);
	        	} else if(day.getName().equalsIgnoreCase("SUNDAY")) {
	        		sundayCheck1.setSelected(true);
	        	}
	        }
        }
        
        fromTimeLabel.setBounds(50,350, 200, 25);
        							spinner1From.setBounds(100, 350, 85, 25);
        toTimeLabel.setBounds(210,350, 200, 25);
        							spinner1To.setBounds  (230, 350, 85, 25);
        fromTimeLabel1.setBounds(330,350, 200, 25);
        							spinner2From.setBounds(370, 350, 85, 25);
        toTimeLabel1.setBounds(500,350, 350, 25);
        							spinner2To.setBounds  (520, 350, 85, 25);
        
        saveBtn.setBounds(350,420,  100, 25);

      this.add(activationSchedule);
      this.add(groupDays);
      this.add(specialDaysLabel);
      this.add(mondayCheck);
      this.add(tuesdayCheck);
      this.add(wednesdayCheck);
      this.add(thursdayCheck);
      this.add(fridayCheck);
      this.add(saturdayCheck);
      this.add(sundayCheck);
      this.add(mondayCheck1);
      this.add(tuesdayCheck1);
      this.add(wednesdayCheck1);
      this.add(thursdayCheck1);
      this.add(fridayCheck1);
      this.add(saturdayCheck1);
      this.add(sundayCheck1);
      
      this.add(timeLabel);
      this.add(fromTimeLabel);
      this.add(spinner1From);
      this.add(toTimeLabel);
      this.add(spinner1To);
      this.add(fromTimeLabel1);
      this.add(spinner2From);
      this.add(toTimeLabel1);
      this.add(spinner2To);
      
      this.add (saveBtn);
      
      saveBtn.addActionListener(new ActionListener(){
    	  //Service service = new Service();
    	 
          //@Override
          public void actionPerformed(ActionEvent event){
        	  	Calendar calendar = Calendar.getInstance();
        	  	calendar.setTime((Date) spinner1From.getValue());
        	  	int hh1From = calendar.get(Calendar.HOUR_OF_DAY);
        	  	int mm1From = calendar.get(Calendar.MINUTE);
        	  	int ss1From = calendar.get(Calendar.SECOND);

        	  	calendar.setTime((Date) spinner1To.getValue());
          	    int hh1To = calendar.get(Calendar.HOUR_OF_DAY);
        	    int mm1To = calendar.get(Calendar.MINUTE);
        	    int ss1To = calendar.get(Calendar.SECOND);
        	    
				Time fromTime1 = new Time(hh1From, mm1From, ss1From);
				Time toTime1 = new Time(hh1To, mm1To, ss1To);

				//serviceDao.updateService(service);
				List<Schedule> schedules = scheduleDao.getSchedulesByServiceId(service.getId());
				Schedule scheduleGeneral = null;
				Schedule scheduleSpecial = null;
				for(Schedule schedule : schedules) {
					if(schedule.getScheduleType() == ScheduleType.GENERAL) {
						scheduleGeneral = schedule;
					}
					if(schedule.getScheduleType() == ScheduleType.SPECIAL) {
						scheduleSpecial = schedule;
					}
				}
				if(scheduleGeneral == null) {
					scheduleGeneral = new Schedule(serviceDao.getServiceByServiceCode(serviceCode), ScheduleType.GENERAL, fromTime1, toTime1);
					scheduleDao.insertSchedule(scheduleGeneral);

					for(Schedule schedule : scheduleDao.getSchedulesByServiceId(service.getId())) {
						if(schedule.getScheduleType() == ScheduleType.GENERAL) {
							scheduleGeneral = schedule;
						}
					}
				}
				
				if(mondayCheck.isSelected()) {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("MONDAY")) {
							if(!scheduleGeneral.contains(day)) {
								scheduleGeneral.addDay(day);
							}
						}
					}
				} else  {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("MONDAY")) {
							if(!scheduleGeneral.contains(day)) {
								scheduleGeneral.removeDay(day);
							}
						}
					}
				}
				
				if(tuesdayCheck.isSelected()) {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("TUESDAY")) {
							if(!scheduleGeneral.contains(day)) {
								scheduleGeneral.addDay(day);
							}
						}
					}
				} else {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("TUESDAY")) {
							if(!scheduleGeneral.contains(day)) {
								scheduleGeneral.removeDay(day);
							}
						}
					}
				}
				
				if(wednesdayCheck.isSelected()) {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("WEDNESDAY")) {
							if(!scheduleGeneral.contains(day)) {
								scheduleGeneral.addDay(day);
							}
						}
					}
				} else {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("WEDNESDAY")) {
							if(!scheduleGeneral.contains(day)) {
								scheduleGeneral.removeDay(day);
							}
						}
					}
				}
				
				if(thursdayCheck.isSelected()) {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("THURSDAY")) {
							if(!scheduleGeneral.contains(day)) {
								scheduleGeneral.addDay(day);
							}
						}
					}
				} else {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("THURSDAY")) {
							if(!scheduleGeneral.contains(day)) {
								scheduleGeneral.removeDay(day);
							}
						}
					}
				}
				
				if(fridayCheck.isSelected()) {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("FRIDAY")) {
							if(!scheduleGeneral.contains(day)) {
								scheduleGeneral.addDay(day);
							}
						}
					}
				} else {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("FRIDAY")) {
							if(!scheduleGeneral.contains(day)) {
								scheduleGeneral.removeDay(day);
							}
						}
					}
				}
				
				if(saturdayCheck.isSelected()) {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("SATURDAY")) {
							if(!scheduleGeneral.contains(day)) {
								scheduleGeneral.addDay(day);
							}
						}
					}
				} else {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("SATURDAY")) {
							if(!scheduleGeneral.contains(day)) {
								scheduleGeneral.removeDay(day);
							}
						}
					}
				}
				
				if(sundayCheck.isSelected()) {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("SUNDAY")) {
							if(!scheduleGeneral.contains(day)) {
								scheduleGeneral.addDay(day);
							}
						}
					}
				} else {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("SUNDAY")) {
							if(!scheduleGeneral.contains(day)) {
								scheduleGeneral.removeDay(day);
							}
						}
					}
				}
				scheduleGeneral.setFromTime(fromTime1);
				scheduleGeneral.setFromTime(toTime1);
				scheduleDao.updateSchedule(scheduleGeneral);
				
        	  	calendar.setTime((Date) spinner2From.getValue());

        	  	calendar.setTime((Date) spinner2To.getValue());
				
				if(scheduleSpecial == null) {
					scheduleSpecial = new Schedule(serviceDao.getServiceByServiceCode(serviceCode), ScheduleType.SPECIAL, fromTime1, toTime1);
					scheduleDao.insertSchedule(scheduleSpecial);

					for(Schedule schedule : scheduleDao.getSchedulesByServiceId(service.getId())) {
						if(schedule.getScheduleType() == ScheduleType.SPECIAL) {
							scheduleSpecial = schedule;
						}
					}
				}
				
				if(mondayCheck1.isSelected()) {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("MONDAY")) {
							if(!scheduleSpecial.contains(day)) {
								scheduleSpecial.addDay(day);
							}
						}
					}
				} else {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("MONDAY")) {
							if(!scheduleSpecial.contains(day)) {
								scheduleSpecial.removeDay(day);
							}
						}
					}
				}
				
				if(tuesdayCheck1.isSelected()) {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("TUESDAY")) {
							if(!scheduleSpecial.contains(day)) {
								scheduleSpecial.addDay(day);
							}
						}
					}
				} else {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("TUESDAY")) {
							if(!scheduleSpecial.contains(day)) {
								scheduleSpecial.removeDay(day);
							}
						}
					}
				}
				
				if(wednesdayCheck1.isSelected()) {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("WEDNESDAY")) {
							if(!scheduleSpecial.contains(day)) {
								scheduleSpecial.addDay(day);
							}
						}
					}
				} else {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("WEDNESDAY")) {
							if(!scheduleSpecial.contains(day)) {
								scheduleSpecial.removeDay(day);
							}
						}
					}
				}
				
				if(thursdayCheck1.isSelected()) {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("THURSDAY")) {
							if(!scheduleSpecial.contains(day)) {
								scheduleSpecial.addDay(day);
							}
						}
					}
				} else {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("THURSDAY")) {
							if(!scheduleSpecial.contains(day)) {
								scheduleSpecial.removeDay(day);
							}
						}
					}
				}
				
				if(fridayCheck1.isSelected()) {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("FRIDAY")) {
							if(!scheduleSpecial.contains(day)) {
								scheduleSpecial.addDay(day);
							}
						}
					}
				} else {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("FRIDAY")) {
							if(!scheduleSpecial.contains(day)) {
								scheduleSpecial.removeDay(day);
							}
						}
					}
				}
				
				if(saturdayCheck1.isSelected()) {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("SATURDAY")) {
							if(!scheduleSpecial.contains(day)) {
								scheduleSpecial.addDay(day);
							}
						}
					}
				} else {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("SATURDAY")) {
							if(!scheduleSpecial.contains(day)) {
								scheduleSpecial.removeDay(day);
							}
						}
					}
				}
				
				if(sundayCheck1.isSelected()) {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("SUNDAY")) {
							if(!scheduleSpecial.contains(day)) {
								scheduleSpecial.addDay(day);
							}
						}
					}
				} else {
					for(Day day : days) {
						if(day.getName().equalsIgnoreCase("SUNDAY")) {
							if(!scheduleSpecial.contains(day)) {
								scheduleSpecial.removeDay(day);
							}
						}
					}
				}
				scheduleSpecial.setFromTime(fromTime1);
				scheduleSpecial.setFromTime(toTime1);
				
				scheduleDao.updateSchedule(scheduleSpecial);

          }
      });  
	}

    public class DateLabelFormatter extends AbstractFormatter {

        private static final long serialVersionUID = 2689799580235498501L;
		
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
                if(cal.getTime() != null) {
                	return dateFormatter.format(cal.getTime());
                }
            }

            return "";
        }

    }
}
