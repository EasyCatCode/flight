import java.util.List;

public interface FlightFilter {

    List<Flight> getFlightsWithLandTimeOverTwo();
    List<Flight> getFlightsForNow();
    List<Flight> getFlightsArrivalBeforeDeparture();

}
