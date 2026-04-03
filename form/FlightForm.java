package mp230569.form;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import mp230569.Listeners;
import mp230569.manager.AirportManager;
import mp230569.manager.FlightManager;
import mp230569.model.Airport;
import mp230569.model.Flight;
import mp230569.timer.InactivityTimerThread;

public class FlightForm extends Panel {
    private Choice originChoice, destChoice;
    private TextField timeField, durationField;
    private Button addButton;
    private AirportManager airportManager;
    private FlightManager flightManager;
    private Listeners listener;
    
    private Label airportStatus, timeStatus, durationStatus;

    public FlightForm(FlightManager flightManager, AirportManager airportManager, InactivityTimerThread timer) {
        this.flightManager = flightManager;
        this.airportManager = airportManager;

        setLayout(new GridLayout(5, 3, 10, 10));

        add(new Label("Origin airport", Label.CENTER) {{ setFont(new Font("", Font.ITALIC, 15)); }});
        originChoice = new Choice();
        add(new Label());
        add(originChoice);

        add(new Label("Destination airport", Label.CENTER) {{ setFont(new Font("", Font.ITALIC, 15)); }});
        destChoice = new Choice();
        airportStatus = new Label();
        add(airportStatus);
        add(destChoice);

        add(new Label("Time (HH:MM)", Label.CENTER) {{ setFont(new Font("", Font.ITALIC, 15)); }});
        timeField = new TextField();
        timeStatus = new Label();
        add(timeStatus);
        add(timeField);

        add(new Label("Duration", Label.CENTER) {{ setFont(new Font("", Font.ITALIC, 15)); }});
        durationField = new TextField();
        durationStatus = new Label();
        add(durationStatus);
        add(durationField);

        add(new Label());
        addButton = new Button("Add flight") {{ setFont(new Font("", Font.ITALIC, 15)); }};
        addButton.setBackground(Color.decode("#ADF1F7"));
        add(addButton);
        add(new Label());

        
        addButton.addActionListener(e -> {
        	timer.resetTimer();
            
            if(validateFlight())
            {
        		String originCode = originChoice.getSelectedItem().split("-")[1]; 
           		String destCode = destChoice.getSelectedItem().split("-")[1];
                
           		Airport origin = airportManager.findAirport(originCode);
           		Airport dest = airportManager.findAirport(destCode);

                Flight flight = new Flight(origin, dest,
                        LocalTime.parse(timeField.getText()),
                        Integer.parseInt(durationField.getText()));

                flightManager.addFlight(flight);

                if (listener != null) {
					 listener.flightAdded(flight);
			    }
                
                timeField.setText("");
                durationField.setText("");

            }
            
        });
        
        timeField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				timer.resetTimer();
			}
		});
        
        durationField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				timer.resetTimer();
			}
		});
    }

    public void refreshAirports() {
        originChoice.removeAll();
        destChoice.removeAll();
        for (Airport a : airportManager.getAirports()) {
            originChoice.add(a.getName() + "-" + a.getCode());
            destChoice.add(a.getName() + "-" + a.getCode());
        }
    }
    
    public void setFlightListener(Listeners listener)
    {
    	this.listener = listener;
    }
    
    private boolean validateFlight()
    {
    	boolean valid = true;
    	
    	String originValid = originChoice.getSelectedItem().split("-")[1].trim();
    	String destValid = destChoice.getSelectedItem().split("-")[1].trim();
    	
    	if(originValid.equals(destValid))
    	{
    		airportStatus.setText("Origin airport cannot be dest airport");
    		airportStatus.setForeground(Color.RED);
    		valid = false;
    	}else
    		airportStatus.setText("");
    	
    	try {
    		LocalTime.parse(timeField.getText().trim());
    		timeStatus.setText("");
    	
    	}catch(DateTimeParseException e)
    	{
    		timeStatus.setText("Time must be in HH:MM format");
    		timeStatus.setForeground(Color.RED);
    		valid = false;
    	}
    	
    	try
    	{
    		int duration = Integer.parseInt(durationField.getText());
    		if(duration <= 0)
    		{
    			durationStatus.setText("Duration time must be greater than zero");
    	        durationStatus.setForeground(Color.RED);
    	        valid = false;
    		}else
    			durationStatus.setText("");
    		
    	}catch (NumberFormatException ex) {
	        durationStatus.setText("Duration time must be number");
	        durationStatus.setForeground(Color.RED);
	        valid = false;
	    }
    	
    	return valid;
    }
}
