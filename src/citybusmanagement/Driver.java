package citybusmanagement;

/**
 * Driver model: stores driver ID, name, license number and assigned bus ID.
 */
public class Driver {
    private String driverID;
    private String name;
    private String licenseNumber;
    private String assignedBusID; // ID of bus assigned to this driver (nullable)

    // Parameterized constructor
    public Driver(String driverID, String name, String licenseNumber) {
        this.driverID = driverID;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.assignedBusID = null;
    }

    // Accessors and mutators
    public String getDriverID() { return driverID; }
    public void setDriverID(String driverID) { this.driverID = driverID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }

    public String getAssignedBusID() { return assignedBusID; }
    public void setAssignedBusID(String assignedBusID) { this.assignedBusID = assignedBusID; }

    // One-line display for lists
    public String toOneLine() {
        return "DriverID: " + driverID + " | Name: " + name +
                " | License: " + licenseNumber +
                " | AssignedBus: " + (assignedBusID == null ? "None" : assignedBusID);
    }

    @Override
    public String toString() {
        return toOneLine();
    }
}


