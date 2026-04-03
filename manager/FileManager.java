package mp230569.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import mp230569.model.Airport;
import mp230569.model.Flight;

public class FileManager implements IFileService{
	@Override
	public void saveAirport(File file, AirportManager airportManager) throws IOException{
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
		{
			List<Airport> airports = new ArrayList();
			airports = airportManager.getAirports();
			for (Airport airport : airports) {
				String str = airport.getName() + "," + airport.getCode() + "," + airport.getX() + "," + airport.getY();
				System.out.println(str);
				writer.write(str);
				writer.newLine();
			}
		}
		
	}

	@Override
	public List<Airport> loadAirport(File file, AirportManager airportManager) throws IOException{
		List<Airport> airports = new ArrayList();
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file)))
		{
			String line;
			while((line = reader.readLine()) != null)
			{
				String name = line.split(",")[0].trim();
				String code = line.split(",")[1].trim();
				String x = line.split(",")[2].trim();
				String y = line.split(",")[3].trim();
				
				if(validateAirport(name, code, x, y, airportManager))
				{
					airports.add(new Airport
							(
									name,
									code,
									Integer.parseInt(x),
									Integer.parseInt(y)
							));
				}
			}
		}
		
		return airports;
	}

	@Override
	public void saveFlight(File file, FlightManager flightManager) throws IOException{
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
		{
			List<Flight> flights = new ArrayList<Flight>();
			flights = flightManager.getFlights();
			for (Flight flight : flights) {
				String str = flight.getOrigin().getCode()+ "," +flight.getDestination().getCode()+ "," +flight.getDepartureTime()+ "," +flight.getDurationTime();
				System.out.println(str);
				writer.write(str);
				writer.newLine();
			}
		}
	}

	@Override
	public List<Flight> loadFlight(File file, AirportManager airportManager) throws IOException{
		
		List<Flight> flights = new ArrayList<>();
		try(BufferedReader reader = new BufferedReader(new FileReader(file)))
		{
			String line;
			while((line = reader.readLine()) != null)
			{
				String origin = line.split(",")[0].trim();
				String dest = line.split(",")[1].trim();
				String time = line.split(",")[2].trim();
				String duration = line.split(",")[3].trim();
				
				Airport originAirport = airportManager.findAirport(origin);
				Airport destAirport = airportManager.findAirport(dest);

				
				
				if(validateFlight(origin, dest, time, duration))
				{
					if(originAirport == null)
					    throw new IllegalArgumentException("Origin airport not found: " + origin);
					if(destAirport == null)
					    throw new IllegalArgumentException("Destination airport not found: " + dest);

					flights.add(new Flight(originAirport, destAirport, LocalTime.parse(time), Integer.parseInt(duration)));
				}
			}
		}
		
		return flights;
		
	}
	
	private boolean validateAirport(String name, String code, String x, String y, AirportManager airportManager)
	{
		boolean valid = true;
		
		if(name.isEmpty())
			throw new IllegalArgumentException("Origin cannot be empty");
		if(code.isEmpty())
			throw new IllegalArgumentException("Origin cannot be dest");
		if(x.isEmpty())
			throw new IllegalArgumentException("Origin cannot be time");
		if(y.isEmpty())
			throw new IllegalArgumentException("Origin cannot be duration");
		
		if(airportManager.isAirportExists(code))
			throw new IllegalArgumentException("Airport already exists");
		
		if(!isNumber(x) || !isNumber(y))
			throw new NumberFormatException("X and Y coordinates must be numbers");
		else if(Integer.parseInt(x) < -90 || Integer.parseInt(x) > 90 ||
				Integer.parseInt(y) < -90 || Integer.parseInt(y) > 90)
			throw new IllegalArgumentException("Coordinates must be between -90 and 90");
		
		
		
		return valid;
	}
	
	
	private boolean validateFlight(String origin, String dest, String time, String duration)
	{
		boolean valid = true;
		
		if(origin.isEmpty())
			throw new IllegalArgumentException("Origin cannot be empty");
		if(dest.isEmpty())
			throw new IllegalArgumentException("Origin cannot be dest");
		if(time.isEmpty())
			throw new IllegalArgumentException("Origin cannot be time");
		if(duration.isEmpty())
			throw new IllegalArgumentException("Origin cannot be duration");
		
		if(origin.equals(dest))
			throw new IllegalArgumentException("Origin airport cannot be dest airport");
		
		if(!isNumber(duration))
			throw new NumberFormatException("Duration must be number");
		else if(Integer.parseInt(duration) <= 0)
			throw new IllegalArgumentException("Duration time must be greater than zero");
		
		if(!isLocalTime(time))
			throw new IllegalArgumentException("Time must be in HH:MM format");
		
		
		
		return valid;
	}
	
	private boolean isNumber(String num)
	{
		try
		{
			Integer.parseInt(num);
			return true;
		}catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	private boolean isLocalTime(String time)
	{
		try
		{
			LocalTime.parse(time);
			return true;
		}catch(DateTimeParseException e)
		{
			return false;
		}
	}
	
}
