package homesecurity;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import sosafe.dao.LocationDao;
import sosafe.dao.SensorDao;
import sosafe.dao.ServiceDao;
import sosafe.model.Location;
import sosafe.model.Sensor;
import sosafe.model.Service;
import sosafe.model.Sensor.SensorType;

public class SimulationCard extends JPanel{
	
	private static final long serialVersionUID = -6762137180877553618L;
	protected static final SimulationCard SoundUtils = null;
	private JPanel panel2;

	private final ServiceDao serviceDao = new ServiceDao(null);
	private final LocationDao locationDao = new LocationDao(null);
	private final SensorDao sensorDao = new SensorDao(null);
	private List<Sensor> sensors = null;
	private List<Integer> breakInSensorIds = new ArrayList<Integer>();
	private List<Integer> fireSensorIds = new ArrayList<Integer>();
	private Service service = null;
	private List<Location> locations = null;
	
	JButton room1BreakIn = null;
	JButton room2BreakIn = null;
	JButton room3BreakIn = null;
	JButton room4BreakIn = null;
	JButton room1Fire = null;
	JButton room2Fire = null;
	JButton room3Fire = null;
	JButton room4Fire = null;

	Sensor room1MotionSensor;
	Sensor room1TemperatureSensor;
	Sensor room2MotionSensor;
	Sensor room2TemperatureSensor;
	Sensor room3MotionSensor;
	Sensor room3TemperatureSensor;
	Sensor room4MotionSensor;
	Sensor room4TemperatureSensor;
	
	// create a new timer with a delay of 500ms that uses Blinker
    final Timer blinkBreakInTimer;
    final Timer blinkFireTimer;
	
	public SimulationCard(){

		//Get Service
    	List<Service> services = serviceDao.getServices();
    	if(services != null && services.size() > 0) {
    		service = services.get(0);
        	System.out.println("\nRetrieved service = " + service.getServiceCode());
    	}

    	//Get Locations by Service Code
    	locations = locationDao.getLocationsByServiceId(service.getId());

    	//Get Sensors by Service Code
        sensors = sensorDao.getSensorsByServiceCode(service.getServiceCode());
        for(Sensor sensor : sensors) {
        	if(sensor.isEnabled() && sensor.getSensorType() == SensorType.MOTION) {
        		if(sensor.getId().intValue() == 1 || sensor.getId().intValue() == 2) {
        			breakInSensorIds.add(0);
        		}
        		if(sensor.getId().intValue() == 3 || sensor.getId().intValue() == 4) {
        			breakInSensorIds.add(1);
        		}
        		if(sensor.getId().intValue() == 5 || sensor.getId().intValue() == 6) {
        			breakInSensorIds.add(2);
        		}
        		if(sensor.getId().intValue() == 7 || sensor.getId().intValue() == 8) {
        			breakInSensorIds.add(3);
        		}
        	} else if(sensor.isEnabled() && sensor.getSensorType() == SensorType.TEMPERATURE) {
        		if(sensor.getId().intValue() == 1 || sensor.getId().intValue() == 2) {
        			fireSensorIds.add(0);
        		}
        		if(sensor.getId().intValue() == 3 || sensor.getId().intValue() == 4) {
        			fireSensorIds.add(1);
        		}
        		if(sensor.getId().intValue() == 5 || sensor.getId().intValue() == 6) {
        			fireSensorIds.add(2);
        		}
        		if(sensor.getId().intValue() == 7 || sensor.getId().intValue() == 8) {
        			fireSensorIds.add(3);
        		}
            }
        }
        
    	if(locations != null & locations.size() > 0) {
        	System.out.println("SimulationCard : locations.size() = "+ locations.size());
	   		 if(locations.get(0) != null && locations.get(0).getLocationName() != null) {
		   			room1BreakIn = new JButton(locations.get(0).getLocationName());
		   			room1Fire = new JButton(locations.get(0).getLocationName());
		   			room1BreakIn.setFont(new Font("Arial", Font.PLAIN, 30));
		   			room1Fire.setFont(new Font("Arial", Font.PLAIN, 30));
	   		 } else {
	   			 room1BreakIn = new JButton("Bed Room");
	   			 room1Fire = new JButton("Bed Room");
	   		 }
	   		 
			 if(locations.get(1) != null && locations.get(1).getLocationName() != null) {
					room2BreakIn = new JButton(locations.get(1).getLocationName());
					room2Fire = new JButton(locations.get(1).getLocationName());
			 } else {
				 room2BreakIn = new JButton("Living Room");
				 room2Fire = new JButton("Living Room");
			 }
			 
			 if(locations.get(2) != null && locations.get(2).getLocationName() != null) {
					room3BreakIn = new JButton(locations.get(2).getLocationName());
					room3Fire = new JButton(locations.get(2).getLocationName());
			 } else {
				 room3BreakIn = new JButton("Kitchen");
				 room3Fire = new JButton("Kitchen");
			 }
			 
			 if(locations.get(3) != null && locations.get(3).getLocationName() != null) {
					room4BreakIn = new JButton(locations.get(3).getLocationName());
					room4Fire = new JButton(locations.get(3).getLocationName());
			 } else {
				 room4BreakIn = new JButton("Bath Room");
				 room4Fire = new JButton("Bath Room");
			 }
    	}
		room1BreakIn.setFont(new Font("Arial", Font.PLAIN, 30));
		room2BreakIn.setFont(new Font("Arial", Font.PLAIN, 30));
		room3BreakIn.setFont(new Font("Arial", Font.PLAIN, 30));
		room4BreakIn.setFont(new Font("Arial", Font.PLAIN, 30));
		room1Fire.setFont(new Font("Arial", Font.PLAIN, 30));
		room2Fire.setFont(new Font("Arial", Font.PLAIN, 30));
		room3Fire.setFont(new Font("Arial", Font.PLAIN, 30));
		room4Fire.setFont(new Font("Arial", Font.PLAIN, 30));

		if(locations.get(0).getSensors().get(0).getSensorType() == SensorType.MOTION) {
			room1MotionSensor = locations.get(0).getSensors().get(0);
		} else {
			room1TemperatureSensor = locations.get(0).getSensors().get(0);
		}
		if(locations.get(0).getSensors().get(1).getSensorType() == SensorType.MOTION) {
			room1MotionSensor = locations.get(0).getSensors().get(1);
		} else {
			room1TemperatureSensor = locations.get(0).getSensors().get(1);
		}

		if(locations.get(1).getSensors().get(0).getSensorType() == SensorType.MOTION) {
			room2MotionSensor = locations.get(1).getSensors().get(0);
		} else {
			room2TemperatureSensor = locations.get(1).getSensors().get(0);
		}
		if(locations.get(1).getSensors().get(1).getSensorType() == SensorType.MOTION) {
			room2MotionSensor = locations.get(1).getSensors().get(1);
		} else {
			room2TemperatureSensor = locations.get(1).getSensors().get(1);
		}

		if(locations.get(2).getSensors().get(0).getSensorType() == SensorType.MOTION) {
			room3MotionSensor = locations.get(2).getSensors().get(0);
		} else {
			room3TemperatureSensor = locations.get(2).getSensors().get(0);
		}
		if(locations.get(2).getSensors().get(1).getSensorType() == SensorType.MOTION) {
			room3MotionSensor = locations.get(2).getSensors().get(1);
		} else {
			room3TemperatureSensor = locations.get(2).getSensors().get(1);
		}

		if(locations.get(3).getSensors().get(0).getSensorType() == SensorType.MOTION) {
			room4MotionSensor = locations.get(3).getSensors().get(0);
		} else {
			room4TemperatureSensor = locations.get(3).getSensors().get(0);
		}
		if(locations.get(3).getSensors().get(1).getSensorType() == SensorType.MOTION) {
			room4MotionSensor = locations.get(3).getSensors().get(1);
		} else {
			room4TemperatureSensor = locations.get(3).getSensors().get(1);
		}
		
		panel2 = createPanel2();
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(panel2);

		blinkBreakInTimer = new Timer(500, new ActionListener(){
	         int count = 0;
	         int maxTime = 10000;
	         boolean on = false;
	         String message = "Calling Emergency Telephone Number......"+service.getEmergencyTelephonePrimary();

	        public void actionPerformed(ActionEvent e) {
	        	int TIMER_DELAY = 1000;
	            if (count * TIMER_DELAY >= maxTime) {
	            	room1BreakIn.setBackground(null);
	               ((Timer) e.getSource()).stop();
	               EventQueue.invokeLater(new Runnable(){
                       @Override
                       public void run() {
		                    JOptionPane op = new JOptionPane(message,JOptionPane.INFORMATION_MESSAGE);
		                    JDialog dialog = op.createDialog("Break");
		                    dialog.setAlwaysOnTop(true);
		                    dialog.setModal(true);
		                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);      
		                    dialog.setVisible(true);
                       }
                   });
	               count = 0;
	            }
	            count++;
	            
	        	int random = -1;
	        	try {
		        	random = SimulationCard.getRandomList(breakInSensorIds);
	        	}catch(java.lang.IllegalArgumentException excp) {}
	            // blink the label background on and off
	        	if(room1MotionSensor.isEnabled() && random == 0) {
	        		room1BreakIn.setBackground( on ? Color.BLUE : null);
	        	} else if(room2MotionSensor.isEnabled() && random == 1) {
	        		room2BreakIn.setBackground( on ? Color.BLUE : null);
	        	} else if(room3MotionSensor.isEnabled() && random == 2) {
	        		room3BreakIn.setBackground( on ? Color.BLUE : null);
	        	} else if(room4MotionSensor.isEnabled() && random == 3) {
	        		room4BreakIn.setBackground( on ? Color.BLUE : null);
	        	}
	            on = !on;
	    	    try {
	                tone(5000,100);
                } catch (LineUnavailableException e1) {
	                e1.printStackTrace();
                }
	    	    try {
	                Thread.sleep(100);
                } catch (InterruptedException e1) {
	                e1.printStackTrace();
                }
	        }
	    });
		
		blinkFireTimer = new Timer(500, new ActionListener(){
	         int count = 0;
	         int maxTime = 5000;
	         boolean on = false;
	         String message = "Calling Emergency Telephone Number......"+service.getEmergencyTelephonePrimary();

	        public void actionPerformed(ActionEvent e) {
	        	int TIMER_DELAY = 1000;
	            if (count * TIMER_DELAY >= maxTime) {
	            	room1BreakIn.setBackground(null);
	               ((Timer) e.getSource()).stop();
	               EventQueue.invokeLater(new Runnable(){
                       @Override
                       public void run() {
		                    JOptionPane op = new JOptionPane(message,JOptionPane.INFORMATION_MESSAGE);
		                    JDialog dialog = op.createDialog("Break");
		                    dialog.setAlwaysOnTop(true);
		                    dialog.setModal(true);
		                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);      
		                    dialog.setVisible(true);
                       }
                   });
	               count = 0;
	            }
	            count++;
	            
	        	int random = -1;
	        	try {
		        	random = SimulationCard.getRandomList(fireSensorIds);
	        	}catch(java.lang.IllegalArgumentException excp) {}
	            // blink the label background on and off
	        	if(room1TemperatureSensor.isEnabled() && random == 0) {
	        		room1Fire.setBackground( on ? Color.RED : null);
	        	} else if(room2TemperatureSensor.isEnabled() && random == 1) {
	        		room2Fire.setBackground( on ? Color.RED : null);
	        	} else if(room3TemperatureSensor.isEnabled() && random == 2) {
	        		room3Fire.setBackground( on ? Color.RED : null);
	        	} else if(room4TemperatureSensor.isEnabled() && random == 3) {
	        		room4Fire.setBackground( on ? Color.RED : null);
	        	}

	        	room4Fire.setBackground( on ? Color.RED : null);
	            on = !on;
	    	    try {
	                tone(5000,100);
                } catch (LineUnavailableException e1) {
	                e1.printStackTrace();
                }
	    	    try {
	                Thread.sleep(100);
                } catch (InterruptedException e1) {
	                e1.printStackTrace();
                }
	        }
	    });
	    
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		JButton breakinSimulationStartButton = new JButton("Start Break-In Simulation");
		this.add(breakinSimulationStartButton);
		breakinSimulationStartButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				breakInSensorIds = new ArrayList<Integer>();
		        for(Sensor sensor : sensors) {
		        	if(sensor.isEnabled() && sensor.getSensorType() == SensorType.MOTION) {
		        		if(sensor.getId().intValue() == 1 || sensor.getId().intValue() == 2) {
		        			breakInSensorIds.add(0);
		        		}
		        		if(sensor.getId().intValue() == 3 || sensor.getId().intValue() == 4) {
		        			breakInSensorIds.add(1);
		        		}
		        		if(sensor.getId().intValue() == 5 || sensor.getId().intValue() == 6) {
		        			breakInSensorIds.add(2);
		        		}
		        		if(sensor.getId().intValue() == 7 || sensor.getId().intValue() == 8) {
		        			breakInSensorIds.add(3);
		        		}
		        	} else if(sensor.isEnabled() && sensor.getSensorType() == SensorType.TEMPERATURE) {
		        		if(sensor.getId().intValue() == 1 || sensor.getId().intValue() == 2) {
		        			fireSensorIds.add(0);
		        		}
		        		if(sensor.getId().intValue() == 3 || sensor.getId().intValue() == 4) {
		        			fireSensorIds.add(1);
		        		}
		        		if(sensor.getId().intValue() == 5 || sensor.getId().intValue() == 6) {
		        			fireSensorIds.add(2);
		        		}
		        		if(sensor.getId().intValue() == 7 || sensor.getId().intValue() == 8) {
		        			fireSensorIds.add(3);
		        		}
		            }
		        }
				blinkBreakInTimer.start();
			}
		});
		
		JButton fireSimulationStartButton = new JButton("Start Fire Simulation");
		this.add(fireSimulationStartButton);
		fireSimulationStartButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				fireSensorIds = new ArrayList<Integer>();
		        for(Sensor sensor : sensors) {
		        	if(sensor.isEnabled() && sensor.getSensorType() == SensorType.MOTION) {
		        		if(sensor.getId().intValue() == 1 || sensor.getId().intValue() == 2) {
		        			breakInSensorIds.add(0);
		        		}
		        		if(sensor.getId().intValue() == 3 || sensor.getId().intValue() == 4) {
		        			breakInSensorIds.add(1);
		        		}
		        		if(sensor.getId().intValue() == 5 || sensor.getId().intValue() == 6) {
		        			breakInSensorIds.add(2);
		        		}
		        		if(sensor.getId().intValue() == 7 || sensor.getId().intValue() == 8) {
		        			breakInSensorIds.add(3);
		        		}
		        	} else if(sensor.isEnabled() && sensor.getSensorType() == SensorType.TEMPERATURE) {
		        		if(sensor.getId().intValue() == 1 || sensor.getId().intValue() == 2) {
		        			fireSensorIds.add(0);
		        		}
		        		if(sensor.getId().intValue() == 3 || sensor.getId().intValue() == 4) {
		        			fireSensorIds.add(1);
		        		}
		        		if(sensor.getId().intValue() == 5 || sensor.getId().intValue() == 6) {
		        			fireSensorIds.add(2);
		        		}
		        		if(sensor.getId().intValue() == 7 || sensor.getId().intValue() == 8) {
		        			fireSensorIds.add(3);
		        		}
		            }
		        }
				blinkFireTimer.start();
			}
		});
		
		JButton simulationStopButton = new JButton("Stop Simulation");
		
		this.add(simulationStopButton);
		
		simulationStopButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				blinkBreakInTimer.stop();
				blinkFireTimer.stop();
			}
		});
		
	}
	
	//Create panel 2 which contains the two panels for simulation
	public JPanel createPanel2(){
		JPanel simulationPanel = new JPanel();
		simulationPanel.setLayout(new GridLayout(1, 2));
		JPanel breakInPanel = createBreakInPanel();
		breakInPanel.setBorder(new TitledBorder(new EtchedBorder(), "Break-in simulation"));
		JPanel firePanel = createFirePanel();
		firePanel.setBorder(new TitledBorder(new EtchedBorder(), "Fire simulation"));
		simulationPanel.add(breakInPanel);
		simulationPanel.add(firePanel);
		return simulationPanel;
	}
	
	//Break in simulation panel
	public JPanel createBreakInPanel(){
		
		JPanel breakIn = new JPanel();
		final JPanel alarmPanel = new JPanel();
		breakIn.add(alarmPanel);
		
		JPanel mapPanel = new JPanel();
		mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.Y_AXIS));
		
		JPanel rooms = new JPanel();
		rooms.setLayout(new GridLayout(2,2));
		rooms.setMaximumSize(new Dimension(800, 800));

		room1BreakIn.setOpaque(true);
		room1BreakIn.setPreferredSize(new Dimension(200, 200));
		rooms.add(room1BreakIn);
		room2BreakIn.setPreferredSize(new Dimension(200, 200));
		rooms.add(room2BreakIn);
		room3BreakIn.setPreferredSize(new Dimension(200, 200));
		rooms.add(room3BreakIn);
		room4BreakIn.setPreferredSize(new Dimension(200, 200));
		rooms.add(room4BreakIn);

		mapPanel.add(rooms);		
		breakIn.add(mapPanel);
	      
		return breakIn;
		
	}
	
	//Fire simulation panel
	public JPanel createFirePanel(){
		
		JPanel firePanel = new JPanel();
		final JPanel alarmFire = new JPanel();
	    firePanel.add(alarmFire);
		
		JPanel mapPanel = new JPanel();
		mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.Y_AXIS));
		
		JPanel rooms = new JPanel();
		rooms.setLayout(new GridLayout(2, 2));
		rooms.setMaximumSize(new Dimension(800, 800));

		room1Fire.setPreferredSize(new Dimension(200, 200));
		rooms.add(room1Fire);
		room2Fire.setPreferredSize(new Dimension(200, 200));
		rooms.add(room2Fire);
		room3Fire.setPreferredSize(new Dimension(200, 200));
		rooms.add(room3Fire);
		room4Fire.setPreferredSize(new Dimension(200, 200));
		rooms.add(room4Fire);

		mapPanel.add(rooms);		
		firePanel.add(mapPanel);
		
		return firePanel;
		
	}
	
	//Create panel 3 which contains the logout button
	public JPanel createPanel3(){
		JPanel exitPanel = new JPanel();
		JButton exitButton = new JButton("Exit");
		exitPanel.add(exitButton);
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			}
		});
		return exitPanel;
	}

	public static int getRandomList(List<Integer> list) {

	    //0-4
	    int index = ThreadLocalRandom.current().nextInt(list.size());		
	    System.out.println("\nIndex :" + index );
	    return list.get(index);
	    
	}
	
	public static float SAMPLE_RATE = 8000f;

	  public static void tone(int hz, int msecs) 
	     throws LineUnavailableException 
	  {
	     tone(hz, msecs, 1.0);
	  }

	  public static void tone(int hz, int msecs, double vol)
	      throws LineUnavailableException 
	  {
	    byte[] buf = new byte[1];
	    AudioFormat af = 
	        new AudioFormat(
	            SAMPLE_RATE, // sampleRate
	            8,           // sampleSizeInBits
	            1,           // channels
	            true,        // signed
	            false);      // bigEndian
	    SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
	    sdl.open(af);
	    sdl.start();
	    for (int i=0; i < msecs*8; i++) {
	      double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
	      buf[0] = (byte)(Math.sin(angle) * 127.0 * vol);
	      sdl.write(buf,0,1);
	    }
	    sdl.drain();
	    sdl.stop();
	    sdl.close();
	  }
	 
}


