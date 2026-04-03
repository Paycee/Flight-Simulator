package mp230569.table;

import java.awt.*;

import mp230569.Listeners;
import mp230569.manager.AirportManager;
import mp230569.manager.FlightManager;
import mp230569.model.Airport;
import mp230569.timer.InactivityTimerThread;

public class AirportTable extends Panel{

	private AirportManager airportManager;
	private InactivityTimerThread timer;
	private FlightManager flightManager;
	private Listeners airportListener, flightListener;
	
	public AirportTable(AirportManager manager, FlightManager flightManager,InactivityTimerThread timer)
	{
		this.airportManager = manager;
		this.timer = timer;
		this.flightManager = flightManager;
		setLayout(new GridLayout(0, 4));
		
		refresh();	
	}
	
	public void refresh()
	{
		removeAll();
		for (int i = 0; i < airportManager.getAirports().size(); i++) {
			
			Airport a = airportManager.getAirports().get(i);
			
			Label name = new Label(a.getName());
			Label code = new Label(a.getCode());
			Label x = new Label(Integer.toString(a.getX()));
			Label y = new Label(Integer.toString(a.getY()));
				
			
			
			if(i % 2 == 0)
			{
				name.setBackground(Color.decode("#ADF1F7"));
				code.setBackground(Color.decode("#ADF1F7"));
				x.setBackground(Color.decode("#ADF1F7"));
				y.setBackground(Color.decode("#ADF1F7"));
			}else
			{
				name.setBackground(Color.decode("#CCD8D9"));
				code.setBackground(Color.decode("#CCD8D9"));
				x.setBackground(Color.decode("#CCD8D9"));
				y.setBackground(Color.decode("#CCD8D9"));
			}
			
            add(name);
            add(code);
            add(x);
            add(y);
           
        }
		
		revalidate();
		repaint();
	}
	
	public void setAirportListener(Listeners listener)
	{
		this.airportListener = listener;
	}
	
}
