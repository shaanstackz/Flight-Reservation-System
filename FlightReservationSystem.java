import java.util.ArrayList;
import java.util.Scanner;

// Flight System for one single day at YYZ (Print this in title) Departing flights!!


public class FlightReservationSystem
{
	public static void main(String[] args)
	{
		FlightManager manager = new FlightManager();

		ArrayList<Reservation> myReservations = new ArrayList<Reservation>();	// my flight reservations

		ArrayList<Flight> manifest = new ArrayList<Flight>();


		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		while (scanner.hasNextLine())
		{
			String inputLine = scanner.nextLine();
			if (inputLine == null || inputLine.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}

			Scanner commandLine = new Scanner(inputLine);
			String action = commandLine.next();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}

			else if (action.equals("Q") || action.equals("QUIT"))
				return;

			else if (action.equalsIgnoreCase("LIST"))
			{
				manager.printAllFlights(); 
			}
			else if (action.equalsIgnoreCase("RES"))
			{
				String flightNum = null;
				String passengerName = "";
				String passport = "";
				String seatType = "";

				if (commandLine.hasNext())
				{
					flightNum = commandLine.next();
				}
				if (commandLine.hasNext())
				{
					passengerName = commandLine.next();
				}
				if (commandLine.hasNext())
				{
					passport = commandLine.next();
				}
				if (commandLine.hasNext())
				{
					seatType = commandLine.next();

					Reservation res = manager.reserveSeatOnFlight(flightNum, passengerName, passport, seatType);
					if (res != null)
					{
						myReservations.add(res);
						res.print();
					}
					else
					{
						System.out.println(manager.getErrorMessage());
					}
				}
			}
			else if (action.equalsIgnoreCase("CANCEL"))
			{
				Reservation res = null;
				String flightNum = null;
				String passengerName = "";
				String passport = "";
				String seatType = "";

				if (commandLine.hasNext())
				{
					flightNum = commandLine.next();
				}
				if (commandLine.hasNext())
				{
					passengerName = commandLine.next();
				}
				if (commandLine.hasNext())
				{
					passport = commandLine.next();
				
					int index = myReservations.indexOf(new Reservation(flightNum, passengerName, passport));
					if (index >= 0)
					{
						manager.cancelReservation(myReservations.get(index));
						myReservations.remove(index);
					}
					else
						System.out.println("Reservation on Flight " + flightNum + " Not Found");
				}
			}
			else if (action.equalsIgnoreCase("SEATS"))
			{
				String flightNum = "";
				String seatType = "";
				
				if (commandLine.hasNext())
				{
					flightNum = commandLine.next();
				}
				if (commandLine.hasNext())
				{
					seatType = commandLine.next();

					if (manager.seatsAvailable(flightNum, seatType)) // "FCL" or "ECO"
						System.out.println("Seats are available");
					else
						System.out.println("XX");
				}
			}
			//else if(action.equalsIgnoreCase("PASMAN")){
				//for(Flight pasman : manifest)
					//pasman.print();
			//}
			else if (action.equalsIgnoreCase("MYRES"))
			{
				for (Reservation myres : myReservations)
					myres.print();
			}
			else if (action.equalsIgnoreCase("SORTBYDEP"))
			{
				manager.sortByDeparture();
			}
			else if (action.equalsIgnoreCase("SORTBYDUR"))
			{
				manager.sortByDuration();
			}
			else if (action.equalsIgnoreCase("CRAFT"))
			{
				manager.printAllAircraft();
			}
			else if (action.equalsIgnoreCase("SORTCRAFT"))
			{
				manager.sortAircraft();
			}
			System.out.print("\n>");
		}
	}


}

