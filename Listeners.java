package mp230569;

import mp230569.model.Airport;
import mp230569.model.Flight;

public interface Listeners {
	public void airportAdded(Airport airport);
	public void flightAdded(Flight flight);
}
