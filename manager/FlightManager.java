package mp230569.manager;

import java.util.ArrayList;
import java.util.List;

import mp230569.model.Airport;
import mp230569.model.Flight;

public class FlightManager implements IFlightRepository{
	private List<Flight> flights;
	
	public FlightManager() {
        flights = new ArrayList<>(); 
    }
	
	public void addFlight(Flight f)
	{
		this.flights.add(f);
	}
	
	public void removeFlight(Airport airport) {
	    flights.removeIf(flight ->
	        airport.equals(flight.getDestination()) || airport.equals(flight.getOrigin())
	    );
	}

	public List<Flight> getFlights() {
		return flights;
	}
	
}
