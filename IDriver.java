/**
 * Represents a single driver in the ride-sharing system.
 * Drivers are compared by ID.
 */
public interface IDriver extends IPerson, Comparable<IDriver> {

    /**
     * return the driver's vehicle plate number.
     */
    String getVehiclePlate();

    /**
     * Sets the driver's vehicle plate number.
     * The plate must follow the format of exactly 3 uppercase letters
     * followed by 4 digits (e.g., "ABC1234"). Implementations must
     * validate the format and throw IllegalArgumentException if it does
     * not match. Vehicle plate numbers must be unique across all drivers
     * in the system; enforcing that uniqueness is the responsibility of
     * IDriverList/IRideSharingSystem when a driver is added.
     */
    void setVehiclePlate(String vehiclePlate);

    //return the driver's vehicle type.
    VehicleType getVehicleType();

    //Sets the driver's vehicle type.
    void setVehicleType(VehicleType vehicleType);

    /**
     * Compares this driver with another driver based on driver ID.
     * Returns a negative integer, zero, or a positive integer as this
     * driver's ID is less than, equal to, or greater than the other
     * driver's ID. This ordering must be consistent with equality by ID.
     */
    @Override
    int compareTo(IDriver other);
}



public abstract class Ride implements IRide {
    private static int counter = 0;          // static: ONE counter shared by all rides
    private final int rideId;
    private String pickupLocation;
    private final IDateTime pickupTime;      // final + no setter: never changes
    private final IDateTime dropoffTime;     // final + no setter: never changes
    private String dropoffLocation;
    private IDriver driver;

    public Ride(String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime,
                String dropoffLocation, IDriver driver) {
        counter++;                           // 1 for the first ride, 2 for the second, ...
        rideId = counter;
        this.pickupLocation = pickupLocation;
        this.pickupTime = pickupTime;
        this.dropoffTime = dropoffTime;
        this.dropoffLocation = dropoffLocation;
        this.driver = driver;
    }

    public int getRideId() {
        return rideId;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public IDateTime getPickupTime() {
        return pickupTime;
    }

    public IDateTime getDropoffTime() {
        return dropoffTime;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public IDriver getDriver() {
        return driver;
    }

    public void setDriver(IDriver driver) {
        this.driver = driver;
    }

    // hasRider(riderId) is not written here: PrivateRide and SharedRide each write their own.

    // Rides are compared alphabetically by pickup location.
    public int compareTo(IRide other) {
        return pickupLocation.compareTo(other.getPickupLocation());
    }

    public String toString() {
        return "Ride " + rideId + " | " + pickupLocation + " " + pickupTime.format()
                + " -> " + dropoffLocation + " " + dropoffTime.format()
                + " | Driver " + driver.getId() + " " + driver.getName();
    }
}
