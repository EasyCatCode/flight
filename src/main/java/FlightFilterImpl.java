import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class FlightFilterImpl implements FlightFilter {

    private final List<Flight> flights;

    public FlightFilterImpl(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public List<Flight> getFlightsWithLandTimeOverTwo() {
        return flights.stream().filter(flight -> !(flight.getSegments().size() > 1 && flight.getSegments().stream().reduce(ChronoUnit.HOURS.between(
                        flight.getSegments().get(0).getDepartureDate(),
                        flight.getSegments().get(flight.getSegments().size() - 1).getDepartureDate()),
                (period, s) -> {
                    long temp = Math.abs(ChronoUnit.HOURS.between(s.getArrivalDate(), s.getDepartureDate()));
                    return period - temp;
                },
                Long::sum) > 2)).collect(Collectors.toList());
    }

    @Override
    public List<Flight> getFlightsForNow() {
        return flights.stream().filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> !segment.getDepartureDate().isBefore(LocalDateTime.now())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> getFlightsArrivalBeforeDeparture() {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream().anyMatch(segment -> !segment.getArrivalDate()
                                .isBefore(segment.getDepartureDate()))).collect(Collectors.toList());
    }
}
