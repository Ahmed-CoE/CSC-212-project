public class RideList implements IRideList {

    private LinkedList<IRide> rides;
    private int count;

    public RideList() {
        rides = new LinkedList<IRide>();
        count = 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean addRide(IRide ride) {

        if (ride == null)
            return false;

        if (rides.empty()) {
            rides.insert(ride);
            count++;
            return true;
        }

        LinkedList<IRide> newList = new LinkedList<IRide>();

        rides.findFirst();

        boolean inserted = false;

        while (true) {

            IRide currentRide = rides.retrieve();

            if (!inserted &&
                    ride.compareTo(currentRide) < 0) {

                newList.insert(ride);
                inserted = true;
            }

            newList.insert(currentRide);

            if (rides.last())
                break;

            rides.findNext();
        }

        if (!inserted) {
            newList.insert(ride);
        }

        rides = newList;

        count++;

        return true;
    }

    @Override
    public boolean removeRideById(int rideId) {

        if (rides.empty())
            return false;

        rides.findFirst();

        while (true) {

            if (rides.retrieve().getRideId() == rideId) {

                rides.remove();
                count--;

                return true;
            }

            if (rides.last())
                break;

            rides.findNext();
        }

        return false;
    }

    @Override
    public LinkedList<IRide> getAllAlphabetically() {

        LinkedList<IRide> result = new LinkedList<IRide>();

        if (rides.empty())
            return result;

        rides.findFirst();

        while (true) {

            result.insert(rides.retrieve());

            if (rides.last())
                break;

            rides.findNext();
        }

        return result;
    }

    @Override
    public LinkedList<IRide> findByPickupLocation(
            String pickupLocation) {

        LinkedList<IRide> result = new LinkedList<IRide>();

        if (rides.empty())
            return result;

        rides.findFirst();

        while (true) {

            IRide ride = rides.retrieve();

            if (ride.getPickupLocation().equals(pickupLocation)) {
                result.insert(ride);
            }

            if (rides.last())
                break;

            rides.findNext();
        }

        return result;
    }

    @Override
    public LinkedList<IRide> findByRiderName(
            String riderFullName) {

        LinkedList<IRide> result = new LinkedList<IRide>();

        if (rides.empty())
            return result;

        rides.findFirst();

        while (true) {

            IRide ride = rides.retrieve();

            if (ride instanceof IPrivateRide) {

                IPrivateRide privateRide =
                        (IPrivateRide) ride;

                IRider rider = privateRide.getRider();

                if (rider != null &&
                        rider.getName().equals(riderFullName)) {

                    result.insert(ride);
                }
            }

            else if (ride instanceof ISharedRide) {

                ISharedRide sharedRide =
                        (ISharedRide) ride;

                LinkedList<IRider> participants =
                        sharedRide.getParticipants();

                if (!participants.empty()) {

                    participants.findFirst();

                    while (true) {

                        IRider rider =
                                participants.retrieve();

                        if (rider.getName()
                                .equals(riderFullName)) {

                            result.insert(ride);
                            break;
                        }

                        if (participants.last())
                            break;

                        participants.findNext();
                    }
                }
            }

            if (rides.last())
                break;

            rides.findNext();
        }

        return result;
    }
}