package homesecurity;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import sosafe.dao.LocationDao;
import sosafe.dao.SensorDao;
import sosafe.dao.ServiceDao;
import sosafe.model.Event;
import sosafe.model.Location;
import sosafe.model.Sensor;
import sosafe.model.Service;

public class ConfigureCard extends JPanel{
	
	private static final long serialVersionUID = 7109702161947440589L;
	
	private final ServiceDao serviceDao = new ServiceDao(null);
	private final LocationDao locationDao = new LocationDao(null);
	private final SensorDao sensorDao = new SensorDao(null);

	private Service service = null;
	private List<Location> locations = null;
	private List<Sensor> sensors = null;
	private List<Event> events = null;
	
	private DefaultTableModel tableLocationSensor;
	JTable table = null;
	final JPanel panel2 = new JPanel();
	final JPanel configurationPanel = new JPanel();

	final Object columnNames[] = { "Room", "Sensor Type", "Sensor Code", "Enable"};
	
	public ConfigureCard(){
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
        
    	//Get Events by Service Code
        List<Location> tempLocations = new ArrayList<Location>();
        if(locations != null) {
        	System.out.println("#locations.size() = "+locations.size());
	        for(Location location : locations) {
	            if(events != null) {
		            for(Event event : events) {
		            	if(location.getId() == event.getLocation().getId()) {
		            		location.addEvent(event);
		            	}
		            }
	            }
	            tempLocations.add(location);
	        }
	        locations = tempLocations;
	        for(Location location : locations) {
	        	if(location.getSensors() != null && location.getSensors().size() > 0) {
	            	for (Sensor sensor : location.getSensors()) {
	                	System.out.println("Location ID = "+ location.getId() +" has Sensor = "+ sensor.getId());
	            	}
	        	} else {
	            	System.out.println("Location ID = "+ location.getId());
	        	}
	        }
	    	System.out.println("#locations.size() = "+locations.size());
        }
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		JPanel building = createBuildingPanel();
		JPanel rooms = createRoomsPanel();
		this.add(building);
		this.add(rooms);
	}
	
	public JPanel createBuildingPanel(){
		JPanel panel1 = new JPanel();
		JLabel buildingLabel = new JLabel("Building");
		JTextField bName = new JTextField(10);
		if(service.getBuildingName() != null) {
			bName.setText(service.getBuildingName());
		}
		panel1.add(buildingLabel);
		panel1.add(bName);
		return panel1;
	}
	
	public JPanel createRoomsPanel(){
		// Get Location names
        List<String> locationNames = new ArrayList<String>();
        if (locations != null && locations.size() > 0) {
        	for(Location location : locations) {
        		System.out.println("\nRetrieved location = " + location.getLocationCode());
        		locationNames.add(location.getLocationName());
        	}
        }
		
		tableLocationSensor = new DefaultTableModel(generateRows(), columnNames);
		tableLocationSensor.fireTableDataChanged();
		table = new JTable(tableLocationSensor);
		table.revalidate();
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

		// Gets details of the selected record
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			// Handler for list selection changes
			@Override
		 	public void valueChanged( ListSelectionEvent event ){
				if (!event.getValueIsAdjusting()) {
					final int[] selectedRow = table.getSelectedRows();
					if (selectedRow.length > 0) {
				 		// See if this is a valid table selection
						if( event.getSource() == table.getSelectionModel()
										&& event.getFirstIndex() >= 0 )
						{
							// Get the data model for this table
							TableModel model = (TableModel) table.getModel();
		
							// Determine the selected item
							String string = (String) model.getValueAt(
													table.getSelectedRow(),
													table.getSelectedColumn() );
		
							// Display the selected item
							System.out.println( "Value selected = " + string );
							
							configurationPanel.removeAll();
							configurationPanel.add(createSensorsPanel());
							configurationPanel.revalidate();
						}
					}
				}
		 	}
		});
		
		panel2.setLayout(new FlowLayout());
		//panel2.add(table);
		panel2.add(new JScrollPane(table));
		panel2.add(configurationPanel);
		return panel2;
		
	}

	/**
	 * @return
	 */
    private Object[][] generateRows() {
	    // Add table
		final Object rowData[][] = new String [locations.size()*2] [5];
		
		int i = 0;
        if (locations != null && locations.size() > 0) {
        	for(Location location : locations) {
        		if(location.getSensors() != null) {
	        		if(location.getSensors().size() == 0) {
	        			if(location.getLocationName() != null) {
	        				rowData [i][0] = location.getLocationName();
	        			}
	        		} else if(location.getSensors().size() == 1) {
	        			if(location.getLocationName() != null) {
	        				rowData [i][0] = location.getLocationName();
	        			}
	    				for(Sensor sensor : location.getSensors()) {
	    					if (sensor.getSensorType() != null) {
	    						rowData [i][1] = sensor.getSensorType().name();
	    					}
	    					rowData [i][2] = sensor.getSensorCode();
	    					if(sensor.isEnabled() != null) {
	    						rowData [i][3] = sensor.isEnabled().toString();
	    					}
	    					i++;
	    				}
	        		} else if(location.getSensors().size() == 2) {
	        			rowData [i][0] = location.getLocationName();
	        			rowData [i+1][0] = location.getLocationName();
	
	        			if(location.getSensors() != null) {
		    				for(Sensor sensor : location.getSensors()) {
		    					if(sensor.getSensorType() != null) {
		    						rowData [i][1] = sensor.getSensorType().name();
		    					}
		    					rowData [i][2] = sensor.getSensorCode();
		    					if(sensor.isEnabled() != null) {
		    						rowData [i][3] = sensor.isEnabled().toString();
		    					}
		    					i++;
		    				}
	        			}
	        		}
	        	}
        	}
		}
	    return rowData;
    }
	
	//Panel for sensor types
	public JPanel createSensorsPanel(){
		// Get the data model for this table
		TableModel model = (TableModel) table.getModel();

		final JCheckBox motionChkBx = new JCheckBox("Motion sensor");
		final JCheckBox tempChkBx = new JCheckBox("Temperature sensor");
		final JTextField locationField = new JTextField(10);
		final JTextField sensorCodeField = new JTextField(10);

		// Determine the selected item
		final String locationName = (String) model.getValueAt(table.getSelectedRow(), 0);
		final String sensorType = (String) model.getValueAt(table.getSelectedRow(), 1);
		final String sensorCode = (String) model.getValueAt(table.getSelectedRow(), 2);
		final String enabled = (String) model.getValueAt(table.getSelectedRow(), 3);

		if(locationName != null) {
			locationField.setText(""+locationName);
		} else {
			locationField.setText("");
		}
		if(sensorCode != null) {
			sensorCodeField.setText(""+sensorCode);
		} else {
			sensorCodeField.setText("");
		}
		
		JPanel panel3 = new JPanel();
		if(sensorType != null) {
			if(sensorType.equalsIgnoreCase("MOTION")) {
				if(enabled.equalsIgnoreCase("true")) {
					motionChkBx.setSelected(true);
				}
				panel3.add(motionChkBx);
			} else if(sensorType.equalsIgnoreCase("TEMPERATURE")) {
				if(enabled.equalsIgnoreCase("true")) {
					tempChkBx.setSelected(true);
				}
				panel3.add(tempChkBx);
			}
		}

		panel3.add(locationField);
		panel3.add(sensorCodeField);
		Timer t = new Timer(300, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        t.start();

		JButton saveBtn = new JButton("Save");
		panel3.add(saveBtn);

		saveBtn.addActionListener(new ActionListener() {
			// @Override
			public void actionPerformed(ActionEvent event) {
				for(Sensor sensor : sensors) {
					if(sensor.getSensorCode().equalsIgnoreCase(sensorCode)) {
						sensor.setSensorCode(sensorCodeField.getText());
						if(sensorType != null && sensorType.equalsIgnoreCase("MOTION")) {
							sensor.setEnabled(motionChkBx.isSelected());
						} else if(sensorType != null && sensorType.equalsIgnoreCase("TEMPERATURE")) {
							sensor.setEnabled(tempChkBx.isSelected());
						}
						sensorDao.updateSensor(sensor);
					}
				}
				for(Location location : locations) {
					if(location.getLocationName() != null && locationName != null && 
							location.getLocationName().equalsIgnoreCase(locationName)) {
						location.setLocationName(locationField.getText());
						locationDao.updateLocation(location);
					}
				}


		    	locations = locationDao.getLocationsByServiceId(service.getId());
		        sensors = sensorDao.getSensorsByServiceCode(service.getServiceCode());
		        
				tableLocationSensor = new DefaultTableModel(generateRows(), columnNames);
				tableLocationSensor.fireTableDataChanged();
				table = new JTable(tableLocationSensor);

				// Gets details of the selected record
				table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

					// Handler for list selection changes
					@Override
				 	public void valueChanged( ListSelectionEvent event ){
						if (!event.getValueIsAdjusting()) {
							final int[] selectedRow = table.getSelectedRows();
							if (selectedRow.length > 0) {
						 		// See if this is a valid table selection
								if( event.getSource() == table.getSelectionModel()
												&& event.getFirstIndex() >= 0 )
								{
									// Get the data model for this table
									TableModel model = (TableModel) table.getModel();
				
									// Determine the selected item
									String string = (String) model.getValueAt(
															table.getSelectedRow(),
															table.getSelectedColumn() );
				
									// Display the selected item
									System.out.println( "Value selected = " + string );
									
									configurationPanel.removeAll();
									configurationPanel.add(createSensorsPanel());
									configurationPanel.revalidate();
								}
							}
						}
				 	}
				});
				((AbstractTableModel)table.getModel()).fireTableDataChanged();      
				table.revalidate();
				panel2.remove(0);
				panel2.add(new JScrollPane(table), 0);
				panel2.revalidate();
			}
		});
		
		return panel3;
	}
}
