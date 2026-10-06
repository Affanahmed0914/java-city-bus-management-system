package citybusmanagement;
/**
 * Ticket model: manages ticket ID, passenger reference, bus ID, seat and fare.
 */
public class Ticket {
    private static int counter = 1; // auto-increment for ticket IDs
    private String ticketID;
    private Passenger passenger;
    private String busID;
    private int seatNumber;
    private double fare;

    // Parameterized constructor (ticketID auto-generated)
    public Ticket(Passenger passenger, String busID, int seatNumber, double fare) {
        this.ticketID = "T" + counter++;
        this.passenger = passenger;
        this.busID = busID;
        this.seatNumber = seatNumber;
        this.fare = fare;
    }

    // Accessors and mutators
    public String getTicketID() { return ticketID; }

    public Passenger getPassenger() { return passenger; }
    public void setPassenger(Passenger passenger) { this.passenger = passenger; }

    public String getBusID() { return busID; }
    public void setBusID(String busID) { this.busID = busID; }

    public int getSeatNumber() { return seatNumber; }
    public void setSeatNumber(int seatNumber) { this.seatNumber = seatNumber; }

    public double getFare() { return fare; }
    public void setFare(double fare) { this.fare = fare; }

    // One-line display for ticket
    public String toOneLine() {
        return "Ticket ID: " + ticketID +
                " | Passenger: " + (passenger == null ? "N/A" : passenger.getName()) +
                " | BusID: " + busID + " | Seat: " + seatNumber + " | Fare: " + fare;
    }

    @Override
    public String toString() {
        return toOneLine();
    }
}
