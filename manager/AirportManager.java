package mp230569.manager;

import java.util.ArrayList;
import java.util.List;

import mp230569.model.Airport;

public class AirportManager implements IAirportRepository{

	
	private List<Airport> airports = new ArrayList<Airport>();;
	
	
	public AirportManager() {
        airports = new ArrayList<>();
    }
	
	public void addAirport(Airport a)
	{
		this.airports.add(a);
	}
	
	public void removeAirport(String code)
	{
		airports.removeIf(a -> a.getCode().equals(code));
	}
	
	public Airport findAirport(String code)
	{
		for (Airport airport : this.airports) {
			if(airport.getCode().equals(code))
			{
				return airport;
			}
		}
		
		return null;
	}
	
	public boolean isAirportExists(String code)
	{
		for (Airport airport : this.airports) {
			if(airport.getCode().equals(code))
			{
				return true;
			}
		}
		
		return false;
	}

	public List<Airport> getAirports() {
		return airports;
	}

	
	
	
}
