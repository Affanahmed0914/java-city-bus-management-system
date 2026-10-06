package citybusmanagement;
import java.util.*;

public class CityBusManagementSystem {
    private static List<Bus> buses = new ArrayList<>(); //list of all buses
    private static List<Route> routes = new ArrayList<>(); //list of all routes
    private static List<Driver> drivers = new ArrayList<>(); //list of all drivers
    private static List<Passenger> passengers = new ArrayList<>();  //list of all passengers
    private static List<Ticket> tickets = new ArrayList<>(); //list of all tickets
    private static final double FARE = 5.0; // fixed fare per ticket
    private static Scanner sc = new Scanner(System.in);  //scanner for user input
    // Simple in-memory sign-up/login
    private static Map<String, String> users = new HashMap<>();
    public static void main(String[] args) {
        System.out.println("=== Welcome to City Bus Management System ===");
        // Sign-up first
        signUp();
        // Login process
        boolean loggedIn = false;
        while (!loggedIn) {  //login loop
            System.out.print("Username: ");
            String username = sc.nextLine().trim();
            System.out.print("Password: ");
            String password = sc.nextLine().trim();
            if (users.containsKey(username) && users.get(username).equals(password)) {  //check login
                loggedIn = true;
                System.out.println("Login successful!\n");  // login success message
            } else {
                System.out.println("Invalid username or password. Try again.\n");  //Failed login message
            }
        }
        // Main menu loop
        boolean running = true;
        while (running) {
            printMenu(); // menu on separate lines
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1": addBus(); break;  //add a new bus
                case "2": printBus(); break; //print bus details
                case "3": listBuses(); break;  //list all buses
                case "4": addRoute(); break;  //add a new route
                case "5": printRoute(); break;  //print route details
                case "6": listRoutes(); break;  //list all routes
                case "7": addDriver(); break;  //add a new driver
                case "8": printDriver(); break;  //prints driver details
                case "9": listDrivers(); break;  //list all drivers
                case "10": addPassenger(); break;  //add a new passenger
                case "11": bookTicket(); break;  //book ticket for passenger
                case "12": printPassengerOrTicket(); break;  //print passenger or ticket details
                case "13": assignDriverToBus(); break; //assign a driver to a bus
                case "14": assignBusToRoute(); break;  //assign a bus to a route
                case "15": removeBus(); break; //remove a bus
                case "16": removeRoute(); break;  //removes a route
                case "17": removeDriver(); break;  //removes a driver
                case "18": removePassenger(); break;  //removes a passenger
                case "20": faresReport(); break; //generates fares collected by each bus
                case "0": running = false; System.out.println("Exiting..."); break; //exits program
                default: System.out.println("Invalid option."); break;  //error message for incorrect input
            }
            System.out.println();
        }
        sc.close();  //close scanner
    }
    // ------------------- Sign-up -------------------
    private static void signUp() {
        System.out.println("\n=== Sign Up ===");
        System.out.print("Create username: ");
        String username = sc.nextLine().trim();
        System.out.print("Create password: ");
        String password = sc.nextLine().trim();
        users.put(username, password);  //save username/password
        System.out.println("Sign-up successful! Please login.\n");
    }
    // ------------------- Print Menu -------------------
    private static void printMenu() {  //display main menu options
        System.out.println("=== Main Menu ===");
        System.out.println("1. Add Bus");
        System.out.println("2. Print Bus Details by ID");
        System.out.println("3. List All Buses");
        System.out.println("4. Add Route");
        System.out.println("5. Print Route Details by ID");
        System.out.println("6. List All Routes");
        System.out.println("7. Add Driver");
        System.out.println("8. Print Driver Details by ID");
        System.out.println("9. List All Drivers");
        System.out.println("10. Add Passenger");
        System.out.println("11. Book Ticket for Passenger");
        System.out.println("12. Print Passenger or Ticket Details");
        System.out.println("13. Assign Driver to Bus");
        System.out.println("14. Assign Bus to Route");
        System.out.println("15. Remove Bus");
        System.out.println("16. Remove Route");
        System.out.println("17. Remove Driver");
        System.out.println("18. Remove Passenger");
        System.out.println("20. Fares Collected per Bus");
        System.out.println("0. Exit");
        System.out.print("Enter option: ");
    }

    // ------------------- Bus methods -------------------
    private static void addBus() {
        System.out.print("Bus ID: ");
        String id = sc.nextLine().trim();
        if (findBus(id) != null) {
            System.out.println("Bus exists.");
            return;
        }
        System.out.print("Capacity: ");
        int cap = Integer.parseInt(sc.nextLine().trim());
        buses.add(new Bus(id, cap));
        System.out.println("Bus added."); //adds bus to list
    }
    private static void printBus() {
        System.out.print("Bus ID: ");
        Bus b = findBus(sc.nextLine().trim());
        System.out.println(b == null ? "Not found." : b.toOneLine()); //show bus details
    }
    private static void listBuses() {
        if (buses.isEmpty()) {
            System.out.println("No buses.");
            return;
        }
        buses.forEach(bus -> System.out.println(bus.toOneLine()));  //list all buses
    }

    // ------------------- Route methods -------------------
    private static void addRoute() {
        System.out.print("Route ID: ");
        String id = sc.nextLine().trim();
        if (findRoute(id) != null) {
            System.out.println("Route exists.");
            return;
        }
        System.out.print("Start: ");
        String start = sc.nextLine().trim();
        System.out.print("End: ");
        String end = sc.nextLine().trim();
        System.out.print("Stops (comma separated): ");
        String[] stops = sc.nextLine().trim().split("\\s*,\\s*");
        routes.add(new Route(id, start, end, Arrays.asList(stops)));
        System.out.println("Route added.");  //adds route to list
    }
    private static void printRoute() {
        System.out.print("Route ID: ");
        Route r = findRoute(sc.nextLine().trim());
        System.out.println(r == null ? "Not found." : r.toOneLine());  //prints routes details
    }
    private static void listRoutes() {
        if (routes.isEmpty()) {
            System.out.println("No routes.");
            return;
        }
        routes.forEach(r -> System.out.println(r.toOneLine()));  //lists all routes
    }

    // ------------------- Driver methods -------------------
    private static void addDriver() {
        System.out.print("Driver ID: ");
        String id = sc.nextLine().trim();
        if (findDriver(id) != null) {
            System.out.println("Driver exists.");
            return;
        }
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        System.out.print("License: ");
        String lic = sc.nextLine().trim();
        drivers.add(new Driver(id, name, lic));
        System.out.println("Driver added.");  //adds driver to list
    }
    private static void printDriver() {
        System.out.print("Driver ID: ");
        Driver d = findDriver(sc.nextLine().trim());
        System.out.println(d == null ? "Not found." : d.toOneLine()); //prints driver details
    }
    private static void listDrivers() {
        if (drivers.isEmpty()) {
            System.out.println("No drivers.");
            return;
        }
        drivers.forEach(d -> System.out.println(d.toOneLine())); //list all drivers
    }

    // ------------------- Passenger & Ticket methods -------------------
    private static void addPassenger() {
        System.out.print("Passenger ID: ");
        String id = sc.nextLine().trim();
        if (findPassenger(id) != null) {
            System.out.println("Passenger exists.");
            return;
        }
        System.out.print("Name: ");
        passengers.add(new Passenger(id, sc.nextLine().trim()));
        System.out.println("Passenger added.");  //adds passenger
    }
    private static void bookTicket() {
        System.out.print("Passenger ID: ");
        Passenger p = findPassenger(sc.nextLine().trim());
        if (p == null) {
            System.out.println("Passenger not found.");
            return;
        }
        System.out.print("Bus ID: ");
        Bus b = findBus(sc.nextLine().trim());
        if (b == null) {
            System.out.println("Bus not found.");
            return;
        }
        if (!b.isSeatAvailable()) {
            System.out.println("Bus full.");
            return;
        }
        List<Integer> occupied = b.getOccupiedSeats();
        int seat = -1;
        for (int s = 1; s <= b.getCapacity(); s++) {
            if (!occupied.contains(s)) {
                seat = s;
                break;
            }
        }
        Ticket t = new Ticket(p, b.getBusID(), seat, FARE);
        p.addTicket(t);
        b.addTicket(t);
        tickets.add(t);
        System.out.println("Ticket booked: " + t.toOneLine());  //books ticket for passenger
    }
    private static void printPassengerOrTicket() {
        System.out.print("Enter P for Passenger, T for Ticket: ");
        String type = sc.nextLine().trim().toUpperCase();
        if (type.equals("P")) {
            System.out.print("Passenger ID: ");
            Passenger p = findPassenger(sc.nextLine().trim());
            if (p == null) {
                System.out.println("Not found.");
                return;
            }
            System.out.println(p.toOneLine());
            if (!p.getBookedTickets().isEmpty())
                p.getBookedTickets().forEach(t -> System.out.println(t.toOneLine()));
        } else if (type.equals("T")) {
            System.out.print("Ticket ID: ");
            Ticket t = findTicket(sc.nextLine().trim());
            System.out.println(t == null ? "Not found." : t.toOneLine());
        } else {
            System.out.println("Invalid selection.");
        }
    }

    // ------------------- Assignments -------------------
    private static void assignDriverToBus() {
        System.out.print("Driver ID: ");
        Driver d = findDriver(sc.nextLine().trim());
        System.out.print("Bus ID: ");
        Bus b = findBus(sc.nextLine().trim());
        if (d == null || b == null) {
            System.out.println("Not found.");
            return;
        }
        if (b.getAssignedDriverID() != null)
            findDriver(b.getAssignedDriverID()).setAssignedBusID(null);
        if (d.getAssignedBusID() != null)
            findBus(d.getAssignedBusID()).setAssignedDriverID(null);
        b.setAssignedDriverID(d.getDriverID());
        d.setAssignedBusID(b.getBusID());
        System.out.println("Driver assigned.");
    }
    private static void assignBusToRoute() {
        System.out.print("Bus ID: ");
        Bus b = findBus(sc.nextLine().trim());
        System.out.print("Route ID: ");
        Route r = findRoute(sc.nextLine().trim());
        if (b == null || r == null) {
            System.out.println("Not found.");
            return;
        }
        b.setAssignedRouteID(r.getRouteID());
        System.out.println("Bus assigned to route.");
    }

    // ------------------- Removal -------------------
    private static void removeBus() {
        System.out.print("Bus ID: ");
        Bus b = findBus(sc.nextLine().trim());
        if (b == null) {
            System.out.println("Not found.");
            return;
        }
        for (Ticket t : new ArrayList<>(b.getCurrentTickets())) {
            t.getPassenger().getBookedTickets().removeIf(tt -> tt.getTicketID().equals(t.getTicketID()));
            tickets.removeIf(tt -> tt.getTicketID().equals(t.getTicketID()));
        }
        if (b.getAssignedDriverID() != null)
            findDriver(b.getAssignedDriverID()).setAssignedBusID(null);
        buses.remove(b);
        System.out.println("Bus removed.");
    }
    private static void removeRoute() {
        System.out.print("Route ID: ");
        Route r = findRoute(sc.nextLine().trim());
        if (r == null) {
            System.out.println("Not found.");
            return;
        }
        for (Bus b : buses)
            if (r.getRouteID().equals(b.getAssignedRouteID()))
                b.setAssignedRouteID(null);
        routes.remove(r);
        System.out.println("Route removed.");
    }
    private static void removeDriver() {
        System.out.print("Driver ID: ");
        Driver d = findDriver(sc.nextLine().trim());
        if (d == null) {
            System.out.println("Not found.");
            return;
        }
        if (d.getAssignedBusID() != null)
            findBus(d.getAssignedBusID()).setAssignedDriverID(null);
        drivers.remove(d);
        System.out.println("Driver removed.");
    }
    private static void removePassenger() {
        System.out.print("Passenger ID: ");
        Passenger p = findPassenger(sc.nextLine().trim());
        if (p == null) {
            System.out.println("Not found.");
            return;
        }
        for (Ticket t : new ArrayList<>(p.getBookedTickets())) {
            Bus b = findBus(t.getBusID());
            if (b != null)
                b.removeTicket(t.getTicketID());
            tickets.removeIf(tt -> tt.getTicketID().equals(t.getTicketID()));
        }
        passengers.remove(p);
        System.out.println("Passenger removed.");
    }

    // ------------------- Fares report -------------------
    private static void faresReport() {
        if (buses.isEmpty()) {
            System.out.println("No buses.");
            return;
        }
        for (Bus b : buses) {
            double total = 0;
            for (Ticket t : b.getCurrentTickets())
                total += t.getFare();
            System.out.println(b.getBusID() + " -> Collected: " + total + " AED | Occupied: " + b.getCurrentTickets().size());
        }
    }
    // ------------------- Helper find methods -------------------
    private static Bus findBus(String id) {
        for (Bus b : buses)
            if (b.getBusID().equals(id))
                return b;
        return null;
    }
    private static Route findRoute(String id) {
        for (Route r : routes)
            if (r.getRouteID().equals(id))
                return r;
        return null;
    }
    private static Driver findDriver(String id) {
        for (Driver d : drivers)
            if (d.getDriverID().equals(id))
                return d;
        return null;
    }
    private static Passenger findPassenger(String id) {
        for (Passenger p : passengers)
            if (p.getPassengerID().equals(id))
                return p;
        return null;
    }
    private static Ticket findTicket(String id) {
        for (Ticket t : tickets)
            if (t.getTicketID().equals(id))
                return t;
        return null;
    }
}


