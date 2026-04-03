package mp230569.manager;

import java.io.File;
import java.io.IOException;
import java.util.List;

import mp230569.model.Airport;
import mp230569.model.Flight;

public interface IFileService {
	public void saveAirport(File file, AirportManager airportManager) throws IOException;
	public List<Airport> loadAirport(File file, AirportManager airportManager) throws IOException;
	
	public void saveFlight(File file, FlightManager flightManager) throws IOException;
	public List<Flight> loadFlight(File file, AirportManager airportManager) throws IOException;
}
