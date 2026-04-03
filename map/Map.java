package mp230569.map;

import java.awt.*;
import java.util.concurrent.BrokenBarrierException;

import mp230569.manager.AirportManager;
import mp230569.model.Airport;
import mp230569.timer.InactivityTimerThread;

public class Map extends Panel{
	
	private AirportManager airportManager;
	
	private MapPanel mapPanel;
	private Panel checkBoxPanel;
	private InactivityTimerThread timer;
	
	public Map(AirportManager airportManager, InactivityTimerThread timer)
	{
		this.airportManager = airportManager;
		this.timer = timer;
		
		setLayout(new BorderLayout());
		
		mapPanel = new MapPanel(airportManager, timer);
		add(mapPanel, BorderLayout.CENTER);
		
		checkBoxPanel = new Panel(new GridLayout(0, 1));
		add(checkBoxPanel, BorderLayout.EAST);
		
		refreshAirports();
		
	}
	
	
	
	public void refreshAirports()
	{
		checkBoxPanel.removeAll();
		
		for (Airport a : airportManager.getAirports()) {
			Checkbox cb = new Checkbox(a.getName(), false);
			
			cb.addItemListener(e -> mapPanel.toggleAirport(a, cb.getState()));
			checkBoxPanel.add(cb);
		}
		
		checkBoxPanel.revalidate();
		checkBoxPanel.repaint();
		
		mapPanel.repaint();
	}
	
	
}
