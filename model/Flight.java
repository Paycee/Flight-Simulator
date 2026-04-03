package mp230569.model;

import java.time.LocalTime;

public class Flight {
	private Airport origin;
	private Airport destination;
	private LocalTime departureTime;
	private int durationTime;
	

	public Flight(Airport origin, Airport destination, LocalTime departureTime, int durationTime) {
		this.origin = origin;
		this.destination = destination;
		this.departureTime = departureTime;
		this.durationTime = durationTime;
	}


	public Airport getOrigin() {
		return origin;
	}


	public void setOrigin(Airport origin) {
		this.origin = origin;
	}


	public Airport getDestination() {
		return destination;
	}


	public void setDestination(Airport destination) {
		this.destination = destination;
	}


	public LocalTime getDepartureTime() {
		return departureTime;
	}


	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}


	public int getDurationTime() {
		return durationTime;
	}


	public void setDurationTime(int durationTime) {
		this.durationTime = durationTime;
	}


	@Override
	public String toString() {
		return "Flight [origin=" + origin.getName() + ", destination=" + destination.getName() + ", departureTime=" + departureTime
				+ ", durationTime=" + durationTime + "]";
	}

	
}
