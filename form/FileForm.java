package mp230569.form;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mp230569.Listeners;
import mp230569.manager.AirportManager;
import mp230569.manager.FileManager;
import mp230569.manager.FlightManager;
import mp230569.model.Airport;
import mp230569.model.Flight;
import mp230569.timer.InactivityTimerThread;


public class FileForm extends Panel{

	private AirportManager airportManager;
	private FlightManager flightManager;
	private Button addAirportButton, addFlightButton, saveAirportButton, saveFlightButton;
	private FileManager fileManager;
	private Listeners airportListener, flightListener;
	
	public FileForm(AirportManager airportManager, FlightManager flightManager, InactivityTimerThread timer)
	{
		this.flightManager = flightManager;
		this.airportManager = airportManager;
		
		setLayout(new GridLayout(6,6,10,10)); 
		
		// ------------------ ADD --------------
		
		Label airportLabel = new Label("Select airport file", Label.CENTER);
		Label flightLabel = new Label("Select flight file", Label.CENTER);
		
		airportLabel.setFont(new Font("", Font.ITALIC, 15));
		flightLabel.setFont(new Font("", Font.ITALIC, 15));
		
		add(airportLabel);
		add(flightLabel);
		
		addAirportButton = new Button("Add airports");
		addAirportButton.setBackground(Color.decode("#ADF1F7"));
		add(addAirportButton);
		
		addFlightButton = new Button("Add flights");
		addFlightButton.setBackground(Color.decode("#ADF1F7"));
		add(addFlightButton);
		
		Label fileAirportStatus = new Label("", Label.CENTER);
        add(fileAirportStatus);
        
        Label fileFlightStatus = new Label("", Label.CENTER);
        add(fileFlightStatus);
        
        // ----------------- SAVE ---------------
        
        Label saveAirportLabel = new Label("Select file", Label.CENTER);
        Label saveFlightLabel = new Label("Select file", Label.CENTER);
        
        saveAirportLabel.setFont(new Font("", Font.ITALIC, 15));
        saveFlightLabel.setFont(new Font("", Font.ITALIC, 15));
        
        add(saveAirportLabel);
        add(saveFlightLabel);
        
        saveAirportButton = new Button("Save airports");
        saveAirportButton.setBackground(Color.decode("#ADF1F7"));
		add(saveAirportButton);
		
		saveFlightButton = new Button("Save flights");
		saveFlightButton.setBackground(Color.decode("#ADF1F7"));
		add(saveFlightButton);
		
		Label saveAirportStatus = new Label("", Label.CENTER);
        add(fileAirportStatus);
        
        Label saveFlightStatus = new Label("", Label.CENTER);
        add(fileFlightStatus);
		
        
        // -------------- BUTTONS -----------------
        
        addAirportButton.addActionListener(e -> {
			
			timer.resetTimer();
			
			FileDialog fileDialog = new FileDialog((Frame) null, "Open file", FileDialog.LOAD);
			fileDialog.setVisible(true);
			
			String directory = fileDialog.getDirectory();
			String filename = fileDialog.getFile();
			
			if(filename != null)
			{
				List<Airport> airports = new ArrayList();
				
				File file = new File(directory, filename);
				
				fileManager = new FileManager();
				
				try {
					airports = fileManager.loadAirport(file, airportManager);
					
					for(Airport a : airports)
					{
						airportManager.addAirport(a);
						
						if (airportListener != null) {
					         airportListener.airportAdded(a); 
					    }
						fileAirportStatus.setText("");
					}
					
				}catch(IllegalArgumentException ia)
				{
					fileAirportStatus.setText("Error: " + ia.getMessage());
					fileAirportStatus.setForeground(Color.RED);
				}catch(IOException ex)
				{
					fileAirportStatus.setText("Error: " + ex.getMessage());
					fileAirportStatus.setForeground(Color.RED);
				}
			}
		});		
        
        
        addFlightButton.addActionListener(e -> {
			
			timer.resetTimer();
			
			FileDialog fileDialog = new FileDialog((Frame) null, "Open file", FileDialog.LOAD);
			fileDialog.setVisible(true);
			
			String directory = fileDialog.getDirectory();
			String filename = fileDialog.getFile();
			
			if(filename != null)
			{
				File file = new File(directory, filename);
				
				fileManager = new FileManager();
				
				List<Flight> flights = new ArrayList<Flight>();
				
				
				try {
					flights = fileManager.loadFlight(file, airportManager);
					
					for (Flight flight : flights) {
						flightManager.addFlight(flight);
						
						if (flightListener != null) {
							flightListener.flightAdded(flight);
					    }
						
						fileFlightStatus.setText("");
					}
					
				} catch (IOException ex) {
					fileFlightStatus.setText(ex.getMessage());
					fileFlightStatus.setForeground(Color.RED);
				} catch (IllegalArgumentException ia)
				{
					fileFlightStatus.setText(ia.getMessage());
					fileFlightStatus.setForeground(Color.RED);
				}
			}
			
		});
        
        saveAirportButton.addActionListener(e ->
        {
        	timer.resetTimer();
			
			FileDialog fileDialog = new FileDialog((Frame) null, "Open file", FileDialog.SAVE);
			fileDialog.setVisible(true);
			
			String directory = fileDialog.getDirectory();
			String filename = fileDialog.getFile();
			
			if(filename != null)
			{
				
				
				File file = new File(directory, filename);
				
				fileManager = new FileManager();
				
				try {
					
					fileManager.saveAirport(file, airportManager);
					
				}catch(IllegalArgumentException ia)
				{
					saveAirportStatus.setText("Error: " + ia.getMessage());
					saveAirportStatus.setForeground(Color.RED);
				}catch(IOException ex)
				{
					saveAirportStatus.setText("Error: " + ex.getMessage());
					saveAirportStatus.setForeground(Color.RED);
				}
			}
        });
        saveFlightButton.addActionListener(e ->
        {
        	timer.resetTimer();
			
			FileDialog fileDialog = new FileDialog((Frame) null, "Open file", FileDialog.SAVE);
			fileDialog.setVisible(true);
			
			String directory = fileDialog.getDirectory();
			String filename = fileDialog.getFile();
			
			if(filename != null)
			{
				File file = new File(directory, filename);
				
				fileManager = new FileManager();
				
				try {
					
					fileManager.saveFlight(file, flightManager);
					
				}catch(IllegalArgumentException ia)
				{
					saveFlightStatus.setText("Error: " + ia.getMessage());
					saveFlightStatus.setForeground(Color.RED);
				}catch(IOException ex)
				{
					saveFlightStatus.setText("Error: " + ex.getMessage());
					saveFlightStatus.setForeground(Color.RED);
				}
			}
        });
		
	}
	
	public void setAirportListener(Listeners listener) {
	    this.airportListener = listener;
	}
	
	public void setFlightListener(Listeners listener) {
	    this.flightListener = listener;
	}
	
}
