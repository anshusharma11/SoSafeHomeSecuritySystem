package homesecurity;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

import sosafe.dao.BillDao;
import sosafe.dao.EventDao;
import sosafe.dao.LocationDao;
import sosafe.dao.ScheduleDao;
import sosafe.dao.SensorDao;
import sosafe.dao.ServiceDao;
import sosafe.model.Bill;
import sosafe.model.Event;
import sosafe.model.Location;
import sosafe.model.Schedule;
import sosafe.model.Sensor;
import sosafe.model.Service;

public class MainUserGUI {
	
	private JFrame guiFrame;
	private CardLayout cards;
	private JPanel cardPanel;
	private JTextField userText;

	private final ServiceDao serviceDao = new ServiceDao(null);
	private final ScheduleDao scheduleDao = new ScheduleDao(null);
	private final LocationDao locationDao = new LocationDao(null);
	private final BillDao billDao = new BillDao(null);
	private final SensorDao sensorDao = new SensorDao(null);
	private final EventDao eventDao = new EventDao(null);

	private Service service = null;
	private List<Location> locations = null;
	private List<Sensor> sensors = null;
	private List<Event> events = null;
	private List<Schedule> schedules = null;
	private List<Bill> bills = null;
	    
    public MainUserGUI(){ 
        guiFrame = new JFrame();
        
        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        guiFrame.setTitle("SoSafe Home Security");
        guiFrame.setSize(900,800);
      
        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
        guiFrame.setLayout(new BorderLayout());
        
        //creating a border to highlight the JPanel areas
        Border outline = BorderFactory.createLineBorder(Color.black);
        
        // This panel will contain one button to enable you
        // to switch through the cards
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(outline);
        
        JButton serviceCardButton = new JButton("Service");
        serviceCardButton.setActionCommand("Service");
        
        JButton configureCardButton = new JButton("Configure");
        configureCardButton.setActionCommand("Configure");
        
        JButton scheduleCardButton = new JButton("Schedule");
        scheduleCardButton.setActionCommand("Schedule");
        
        JButton simulationCardButton = new JButton("Monitor");
        simulationCardButton.setActionCommand("Simulation");
        
        JButton eventCardButton = new JButton("Event");
        eventCardButton.setActionCommand("Event");
        
        JButton billCardButton = new JButton("Bill");
        billCardButton.setActionCommand("Bill");
        
        JButton closeButton = new JButton("Close");
        closeButton.setActionCommand("Close");
        
        serviceCardButton.addActionListener(new ActionListener(){
            //@Override
            public void actionPerformed(ActionEvent event){
            	//navigate to the Service component
            	cards.show(cardPanel, "Service");
            }
        });
        
        configureCardButton.addActionListener(new ActionListener(){
            //@Override
            public void actionPerformed(ActionEvent event){
            	//navigate to the Configure component
            	cards.show(cardPanel, "Configure");
            }
        });
        
        scheduleCardButton.addActionListener(new ActionListener(){
            //@Override
            public void actionPerformed(ActionEvent event){
            	//navigate to the Schedule component
            	cards.show(cardPanel, "Schedule");
            }
        });
        
        simulationCardButton.addActionListener(new ActionListener(){
            //@Override
            public void actionPerformed(ActionEvent event){
            	//navigate to the Simulation component
            	cards.show(cardPanel, "Simulation");
            }
        });
        
        eventCardButton.addActionListener(new ActionListener(){
            //@Override
            public void actionPerformed(ActionEvent event){
            	//navigate to the Event component
            	cards.show(cardPanel, "EventCard");
            }
        });
        
        billCardButton.addActionListener(new ActionListener(){
            //@Override
            public void actionPerformed(ActionEvent event){
            	//navigate to the Bill component
            	cards.show(cardPanel, "Bill");
            }
        });
        
        closeButton.addActionListener(new ActionListener(){
            //@Override
            public void actionPerformed(ActionEvent event){
            	//navigate to the Bill component
            	guiFrame.dispose();
            }
        });
        
        buttonPanel.add(serviceCardButton);
        buttonPanel.add(configureCardButton);
        buttonPanel.add(scheduleCardButton);
        buttonPanel.add(simulationCardButton);
        buttonPanel.add(eventCardButton);
        buttonPanel.add(billCardButton);
        buttonPanel.add(closeButton);
        
        guiFrame.add(buttonPanel,BorderLayout.SOUTH);
       
        
        // Create Panels for switching
        cards = new CardLayout();
        cardPanel = new JPanel();
        
        // set the card layout
        cardPanel.setLayout(cards);
        cards.show(cardPanel, "Layout");
        
        JPanel loginCard = new JPanel();
        loginCard.setBackground(Color.cyan);
        loginCard.setBackground(Color.GREEN);
        loginCard.setLayout(null);
        JLabel userLabel = new JLabel("User");
		userLabel.setBounds(300, 200, 80, 25);
		loginCard.add(userLabel);

		userText = new JTextField(20);
		userText.setBounds(400, 200, 160, 25);
		loginCard.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(300, 250, 80, 25);
		loginCard.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(400, 250, 160, 25);
		loginCard.add(passwordText);

		JButton loginButton = new JButton("login");
		loginButton.setBounds(400, 300, 80, 25);
		loginCard.add(loginButton);
		loginButton.setActionCommand("login");
	        
	        
		loginButton.addActionListener(new ActionListener(){
            //@Override
            public void actionPerformed(ActionEvent event){
            	if(userText.getText().equalsIgnoreCase(service.getCustomerName())) {
	            	//navigate to the next component
	            	cards.show(cardPanel, "Service");
	                // cards.next(cardPanel);
            	}
            }
        });
     
        JPanel configureCard = new ConfigureCard();
        configureCard.setBackground(Color.RED);
        
        JPanel simulationCard = new SimulationCard();
        JPanel eventCard = new EventCard();
        JPanel scheduleCard = new ScheduleCard();
        JPanel billingCard = new BillingCard();

        JPanel serviceCard = new ServiceCard();
        
        cardPanel.add(loginCard, "Login Screen");
        cardPanel.add(configureCard, "Configure");
        cardPanel.add(simulationCard, "Simulation");
        cardPanel.add(eventCard, "EventCard");  
        cardPanel.add(scheduleCard, "Schedule");
        cardPanel.add(billingCard, "Bill");
       
        cardPanel.add(serviceCard, "Service");
             
        guiFrame.add(buttonPanel,BorderLayout.SOUTH);
        guiFrame.add(cardPanel,BorderLayout.CENTER);
        guiFrame.setVisible(true);
        
        // Center the frame on the screen
	    Toolkit toolkit = guiFrame.getToolkit();
	    Dimension size = toolkit.getScreenSize();
	    guiFrame.setLocation(size.width/2 - guiFrame.getWidth()/2,size.height/2 - guiFrame.getHeight()/2);
	    guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    // Code to map UI with DB starts here
	    try {
	    	//Get Service
	    	List<Service> services = serviceDao.getServices();
	    	if(services != null && services.size() > 0) {
	    		service = services.get(0);
	        	System.out.println("\nRetrieved service = " + service.getServiceCode());
	    	}
	    	//Get Locations by Service Code
	    	locations = locationDao.getLocationsByServiceId(service.getId());
	        if (locations != null && locations.size() > 0) {
	        	for(Location location : locations) {
	        		System.out.println("\nRetrieved location = " + location.getLocationCode());
	        	}
	        }
	    	//Get Sensors by Service Code
	        sensors = sensorDao.getSensorsByServiceCode(service.getServiceCode());
	        if (sensors != null && sensors.size() > 0) {
	        	for(Sensor sensor : sensors) {
	        		System.out.println("\nRetrieved sensors = " + sensor.getSensorCode());
	        	}
	        }
	    	//Get Events by Service Code
	        events = eventDao.getEventsByServiceCode(service.getServiceCode());
	        if (events != null && events.size() > 0) {
	        	for(Event event : events) {
	        		System.out.println("\nRetrieved events = " + event.getEventCode());
	        	}
	        }
	    	//Get Bills by Service Code
	        bills = billDao.getBillsByServiceCode(service.getServiceCode());
	        if (bills != null && bills.size() > 0) {
	        	for(Bill bill : bills) {
	        		System.out.println("\nRetrieved bills = " + bill.getBillCode());
	        	}
	        }
	    	//Get Schedules by Service Code
	        schedules = scheduleDao.getSchedulesByServiceCode(service.getServiceCode());
	        if (schedules != null && schedules.size() > 0) {
	        	for(Schedule schedule : schedules) {
	        		System.out.println("\nRetrieved schedules = " + schedule.getScheduleType());
	        	}
	        }
	        
	    } catch(javax.persistence.NoResultException e) {}
    }
    
	public static void main(String[] args) {   
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainUserGUI();
            }
        });
    }

	public String getUserName() {
        return userText.getText();
    }

	public void setUserText(JTextField userText) {
        this.userText = userText;
    }
}
