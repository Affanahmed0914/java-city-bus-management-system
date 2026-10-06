package citybusmanagement;
import java.util.ArrayList;
import java.util.List;

/**
 * Bus model: stores bus ID, capacity, assigned driver/route IDs and current tickets.
 */
public class Bus {
    private String busID;
    private int capacity;
    private String assignedDriverID; // ID of assigned driver (nullable)
    private String assignedRouteID;  // ID of assigned route (nullable)
    private List<Ticket> currentTickets; // tickets booked on this bus

    // Parameterized constructor
    public Bus(String busID, int capacity) {
        this.busID = busID;
        this.capacity = capacity;
        this.assignedDriverID = null;
        this.assignedRouteID = null;
        this.currentTickets = new ArrayList<>();
    }

    // Accessors and mutators
    public String getBusID() { return busID; }
    public void setBusID(String busID) { this.busID = busID; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public String getAssignedDriverID() { return assignedDriverID; }
    public void setAssignedDriverID(String assignedDriverID) { this.assignedDriverID = assignedDriverID; }

    public String getAssignedRouteID() { return assignedRouteID; }
    public void setAssignedRouteID(String assignedRouteID) { this.assignedRouteID = assignedRouteID; }

    public List<Ticket> getCurrentTickets() { return currentTickets; }
    public void setCurrentTickets(List<Ticket> currentTickets) { this.currentTickets = currentTickets; }

    // Add a ticket to this bus
    public void addTicket(Ticket t) { currentTickets.add(t); }

    // Remove a ticket by ticketID
    public boolean removeTicket(String ticketID) {
        return currentTickets.removeIf(t -> t.getTicketID().equals(ticketID));
    }

    // Check seat availability
    public boolean isSeatAvailable() {
        return currentTickets.size() < capacity;
    }

    // Get a list of occupied seat numbers
    public List<Integer> getOccupiedSeats() {
        List<Integer> seats = new ArrayList<>();
        for (Ticket t : currentTickets) seats.add(t.getSeatNumber());
        return seats;
    }

    // One-line display used by lists
    public String toOneLine() {
        return "BusID: " + busID + " | Capacity: " + capacity +
                " | Driver: " + (assignedDriverID == null ? "None" : assignedDriverID) +
                " | Route: " + (assignedRouteID == null ? "None" : assignedRouteID) +
                " | Tickets: " + currentTickets.size();
    }

    @Override
    public String toString() {
        return toOneLine();
    }
}
