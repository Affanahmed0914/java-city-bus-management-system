package citybusmanagement;
import java.util.ArrayList;
import java.util.List;

/**
 * Passenger model: stores passenger ID, name and tickets booked.
 */
public class Passenger {
    private String passengerID;
    private String name;
    private List<Ticket> bookedTickets; // tickets booked by this passenger

    // Parameterized constructor
    public Passenger(String passengerID, String name) {
        this.passengerID = passengerID;
        this.name = name;
        this.bookedTickets = new ArrayList<>();
    }

    // Accessors and mutators
    public String getPassengerID() { return passengerID; }
    public void setPassengerID(String passengerID) { this.passengerID = passengerID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Ticket> getBookedTickets() { return bookedTickets; }
    public void setBookedTickets(List<Ticket> bookedTickets) { this.bookedTickets = bookedTickets; }

    // Add a ticket to passenger
    public void addTicket(Ticket t) { bookedTickets.add(t); }

    // One-line display for lists
    public String toOneLine() {
        return "PassengerID: " + passengerID + " | Name: " + name + " | Tickets: " + bookedTickets.size();
    }

    @Override
    public String toString() {
        return toOneLine();
    }
}
