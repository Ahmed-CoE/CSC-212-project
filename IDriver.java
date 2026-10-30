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



// The rides, kept in alphabetical order of pickup location.
// We are the IMPLEMENTER here: we work directly on the nodes (head, p, p.next).
public class RideList implements IRideList {
    private Node<IRide> head;
    private int size;

    public RideList() {
        head = null;
        size = 0;
    }

    // Same idea as RiderList.add, but the key is the pickup location (compareTo of Ride).
    public boolean addRide(IRide ride) {
        Node<IRide> tmp = new Node<IRide>(ride);
        // rule: empty list, or the new location comes first -> new head
        if (head == null || ride.compareTo(head.data) < 0) {
            tmp.next = head;
            head = tmp;
        } else {
            Node<IRide> p = head;
            // <= : a new ride goes AFTER the rides that have the same location
            while (p.next != null && p.next.data.compareTo(ride) <= 0)
                p = p.next;
            tmp.next = p.next;
            p.next = tmp;
        }
        size++;
        return true;
    }

    // The ride ID is unique: remove at most one ride.
    public boolean removeRideById(int rideId) {
        if (head == null)
            return false;
        if (head.data.getRideId() == rideId) {
            head = head.next;
            size--;
            return true;
        }
        Node<IRide> p = head;
        while (p.next != null) {
            if (p.next.data.getRideId() == rideId) {
                p.next = p.next.next;
                size--;
                return true;
            }
            p = p.next;
        }
        return false;
    }

    // The list is already in alphabetical order, so one walk is enough: O(n).
    public LinkedList<IRide> getAllAlphabetically() {
        LinkedList<IRide> result = new LinkedList<IRide>();
        Node<IRide> p = head;
        while (p != null) {
            result.insert(p.data);
            p = p.next;
        }
        return result;
    }

    public LinkedList<IRide> findByPickupLocation(String pickupLocation) {
        LinkedList<IRide> result = new LinkedList<IRide>();
        Node<IRide> p = head;
        while (p != null) {
            if (p.data.getPickupLocation().equalsIgnoreCase(pickupLocation))
                result.insert(p.data);
            p = p.next;
        }
        return result;
    }

    public LinkedList<IRide> findByRiderName(String riderFullName) {
        LinkedList<IRide> result = new LinkedList<IRide>();
        Node<IRide> p = head;
        while (p != null) {
            if (hasRiderNamed(p.data, riderFullName))
                result.insert(p.data);
            p = p.next;
        }
        return result;
    }

    // Is a rider with this full name on the ride?
    private boolean hasRiderNamed(IRide ride, String name) {
        if (ride instanceof IPrivateRide) {                       // a private ride: one rider
            IPrivateRide privateRide = (IPrivateRide) ride;
            return privateRide.getRider().getName().equals(name);
        }
        ISharedRide sharedRide = (ISharedRide) ride;     // a shared ride: walk its riders
        LinkedList<IRider> riders = sharedRide.getParticipants();
        if (riders.empty())
            return false;
        riders.findFirst();
        while (!riders.last()) {
            if (riders.retrieve().getName().equals(name))
                return true;
            riders.findNext();
        }
        return riders.retrieve().getName().equals(name);         // the last rider
    }

    public int size() {
        return size;
    }
}
