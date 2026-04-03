package mp230569.manager;

import mp230569.model.Airport;
import mp230569.model.Flight;

public interface IFlightRepository {
	void addFlight(Flight f);
	void removeFlight(Airport airport);
}
