import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        flights.forEach(System.out::println);
        System.out.println("***");
        FlightFilter filter = new FlightFilterImpl(flights);

        List<Flight> flightsWithLandTimeTwoHours = filter.getFlightsWithLandTimeOverTwo();
        List<Flight> flightsForNow = filter.getFlightsForNow();
        List<Flight> flightsArrivalBeforeDepature = filter.getFlightsArrivalBeforeDeparture();

        flightsWithLandTimeTwoHours.forEach(System.out::println);
        flightsForNow.forEach(System.out::println);
        flightsArrivalBeforeDepature.forEach(System.out::println);
    }

}
