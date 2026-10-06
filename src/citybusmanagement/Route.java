package citybusmanagement;
import java.util.ArrayList;
import java.util.List;

/**
 * Route model: stores route ID, start/end points and list of stops.
 */
public class Route {
    private String routeID;
    private String startPoint;
    private String endPoint;
    private List<String> stops;

    // Parameterized constructor
    public Route(String routeID, String startPoint, String endPoint, List<String> stops) {
        this.routeID = routeID;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        // copy list to avoid external modification
        this.stops = new ArrayList<>(stops);
    }

    // Accessors and mutators
    public String getRouteID() { return routeID; }
    public void setRouteID(String routeID) { this.routeID = routeID; }

    public String getStartPoint() { return startPoint; }
    public void setStartPoint(String startPoint) { this.startPoint = startPoint; }

    public String getEndPoint() { return endPoint; }
    public void setEndPoint(String endPoint) { this.endPoint = endPoint; }

    public List<String> getStops() { return new ArrayList<>(stops); }
    public void setStops(List<String> stops) { this.stops = new ArrayList<>(stops); }

    // One-line display for route
    public String toOneLine() {
        return "RouteID: " + routeID + " | " + startPoint + " -> " + endPoint +
                " | Stops: " + String.join(", ", stops);
    }

    @Override
    public String toString() {
        return toOneLine();
    }
}

