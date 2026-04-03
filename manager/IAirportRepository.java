package mp230569.manager;

import mp230569.model.Airport;

public interface IAirportRepository {
	
	void addAirport(Airport a);
	void removeAirport(String code);
	Airport findAirport(String code);
	
}
