import java.util.ArrayList;
import java.util.Random;
import java.util.Map;
import java.util.TreeMap;

public class Flight
{
	public enum Status {DELAYED, ONTIME, ARRIVED, INFLIGHT};
	public static enum Type {SHORTHAUL, MEDIUMHAUL, LONGHAUL};
	public static enum SeatType {ECONOMY, FIRSTCLASS, BUSINESS};

	private String flightNum;
	private String airline;
	private String origin, dest;
	private String departureTime;
	private Status status;
	private int flightDuration;
	protected Aircraft aircraft;
	protected int numPassengers;
	protected Type type;
	protected ArrayList<Passenger> manifest;
	protected TreeMap<String, Passenger> seatMap = new TreeMap<String, Passenger>();
	
	protected Random random = new Random();
	
	private String errorMessage = "";
	  
	public String getErrorMessage()
	{
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	public Flight()
	{
		this.flightNum = "";
		this.airline = "";
		this.dest = "";
		this.origin = "Toronto";
		this.departureTime = "";
		this.flightDuration = 0;
		this.aircraft = null;
		numPassengers = 0;
		status = Status.ONTIME;
		type = Type.MEDIUMHAUL;
		manifest = new ArrayList<Passenger>();
	}
	
	public Flight(String flightNum)
	{
		this.flightNum = flightNum;
	}
	
	public Flight(String flightNum, String airline, String dest, String departure, int flightDuration, Aircraft aircraft)
	{
		this.flightNum = flightNum;
		this.airline = airline;
		this.dest = dest;
		this.origin = "Toronto";
		this.departureTime = departure;
		this.flightDuration = flightDuration;
		this.aircraft = aircraft;
		numPassengers = 0;
		status = Status.ONTIME;
		type = Type.MEDIUMHAUL;
		manifest = new ArrayList<Passenger>();
	}
	
	public Type getFlightType()
	{
		return type;
	}
	
	public String getFlightNum()
	{
		return flightNum;
	}
	public void setFlightNum(String flightNum)
	{
		this.flightNum = flightNum;
	}
	public String getAirline()
	{
		return airline;
	}
	public void setAirline(String airline)
	{
		this.airline = airline;
	}
	public String getOrigin()
	{
		return origin;
	}
	public void setOrigin(String origin)
	{
		this.origin = origin;
	}
	public String getDest()
	{
		return dest;
	}
	public void setDest(String dest)
	{
		this.dest = dest;
	}
	public String getDepartureTime()
	{
		return departureTime;
	}
	public void setDepartureTime(String departureTime)
	{
		this.departureTime = departureTime;
	}
	
	public Status getStatus()
	{
		return status;
	}
	public void setStatus(Status status)
	{
		this.status = status;
	}
	public int getFlightDuration()
	{
		return flightDuration;
	}
	public void setFlightDuration(int dur)
	{
		this.flightDuration = dur;
	}
	
	public int getNumPassengers()
	{
		return numPassengers;
	}
	public void setNumPassengers(int numPassengers)
	{
		this.numPassengers = numPassengers;
	}
	
	public void assignSeat(Passenger p)
	{
		int seat = random.nextInt(aircraft.numEconomySeats);
		p.setSeat("ECO"+ seat);
	}
	
	public String getLastAssignedSeat()
	{
		if (!manifest.isEmpty())
			return manifest.get(manifest.size()-1).getSeat();
		return "";
	}

	public ArrayList<Passenger> getManifest(){
		return manifest;
	}
	
	public boolean seatsAvailable(String seatType)
	{
		if (!seatType.equalsIgnoreCase("ECO")) return false;
		return numPassengers < aircraft.numEconomySeats;
	}
	
	public boolean equals(Object other)
	{
		Flight otherFlight = (Flight) other;
		return this.flightNum.equals(otherFlight.flightNum);
	}
	
	public String toString()
	{
		 return airline + "\t Flight:  " + flightNum + "\t Dest: " + dest + "\t Departing: " + departureTime + "\t Duration: " + flightDuration + "\t Status: " + status;
	}

	public void reserveSeat(Passenger p) throws Exception{
		if(numPassengers>=aircraft.getNumSeats()){
			throw new FullFlightException("Flight " + flightNum + " is Full");
		}
		if(manifest.contains(p)){
			throw new DuplicatePassengerException("Duplicate Passenger " + p.getName() + " " + p.getPassport());
		}
		if(!aircraft.checkSeat(p.getSeat())){
			throw new SeatOccupiedException("Seat is currently occupied. Please find a different seat.");
		}
		aircraft.reserveSeatLayout(p.getSeat());
		manifest.add(p);
		seatMap.put(p.getSeat(),p);
		numPassengers=numPassengers+1;
	}
	
	public void cancelSeat(String name, String passport, String seat) throws Exception{
		Passenger p=new Passenger(name, passport);
		if(!manifest.contains(p)){
			throw new PassengerNotInManifestException(("Passenger: " + name + " Passport: " + passport + " Not Found"));
		}
		manifest.remove(p);
		seatMap.remove(seat);
		seatMap.put(seat, null);
		aircraft.cancelSeatLayout(seat);
		if(numPassengers>0){
			numPassengers=numPassengers-1;
		}
		System.out.println("Cancellation was a success");
	}
	public void printPassengerManifest(){
		for(Flight f:manifest){
			System.out.println(manifest);
		}
	}
	public void printSeats(){
		for(int x=0;x<aircraft.row;x++){
			for(int i=0;i<aircraft.column;i++){
				seatMap.put(aircraft.seatLayout[x][i],null);
				System.out.println(aircraft.seatLayout[x][i] + " ");
			}
		}
		System.out.println("\nXX = Occupied += First Class");
	}
	
}
class DuplicatePassengerException extends Exception{
	public DuplicatePassengerException(String errorMessage){
		super(errorMessage);
	}
}
class PassengerNotInManifestException extends Exception{
	public PassengerNotInManifestException(String errorMessage){
		super(errorMessage);
	}
}
class SeatOccupiedException extends Exception{
	public SeatOccupiedException(String errorMessage){
		super(errorMessage);
	}
}
