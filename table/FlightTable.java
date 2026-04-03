package mp230569.table;

import java.awt.*;

import mp230569.manager.FlightManager;
import mp230569.model.Flight;

public class FlightTable extends Panel{

	private FlightManager flightManager;
	
	public FlightTable(FlightManager flightManager)
	{
		this.flightManager = flightManager;
		
		setLayout(new GridLayout(0, 4));
		
		refresh();
	}
	
	public void refresh()
	{
		removeAll();
		
		for (int i = 0; i < flightManager.getFlights().size(); i++) {
			
			Flight flight = flightManager.getFlights().get(i);
			
			Label origin = new Label(flight.getOrigin().getName());
			Label dest = new Label(flight.getDestination().getName());
			Label time = new Label(flight.getDepartureTime().toString());
			Label duration = new Label(Integer.toString(flight.getDurationTime()));
			
			if(i % 2 == 0)
			{
				origin.setBackground(Color.decode("#ADF1F7"));
				dest.setBackground(Color.decode("#ADF1F7"));
				time.setBackground(Color.decode("#ADF1F7"));
				duration.setBackground(Color.decode("#ADF1F7"));
			}else
			{
				origin.setBackground(Color.decode("#CCD8D9"));
				dest.setBackground(Color.decode("#CCD8D9"));
				time.setBackground(Color.decode("#CCD8D9"));
				duration.setBackground(Color.decode("#CCD8D9"));
			}
			
            add(origin);
            add(dest);
            add(time);
            add(duration);
			
		}
		
		revalidate();
		repaint();
	}
	
}
