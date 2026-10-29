public class DriverList implements IDriverList {

    private LinkedList<IDriver> drivers;

    public DriverList() {
        drivers = new LinkedList<IDriver>();
    }

    @Override
    public boolean add(IDriver driver) {

        if (driver == null || driver.getVehiclePlate() == null)
            return false;

        if (drivers.empty()) {
            drivers.insert(driver);
            return true;
        }

        // Check for duplicate ID and vehicle plate.
        drivers.findFirst();

        while (true) {
            IDriver current = drivers.retrieve();

            if (current.getId() == driver.getId())
                return false;

            if (current.getVehiclePlate().equals(driver.getVehiclePlate()))
                return false;

            if (drivers.last())
                break;

            drivers.findNext();
        }

        // Insert at the beginning if the new ID is the smallest.
        drivers.findFirst();
        IDriver first = drivers.retrieve();

        if (driver.getId() < first.getId()) {
            drivers.update(driver);
            drivers.insert(first);
            return true;
        }

        // Find the correct position to preserve ascending ID order.
        drivers.findFirst();

        while (true) {
            IDriver current = drivers.retrieve();

            if (driver.getId() < current.getId()) {
                // The previous node is the insertion point.
                drivers.findFirst();

                while (drivers.retrieve().getId() != current.getId()) {
                    drivers.findNext();
                }

                // This branch is only reached if the current node is
                // not the first node, handled by the first-node check above.
                return false;
            }

            if (drivers.last()) {
                drivers.insert(driver);
                return true;
            }

            drivers.findNext();

            IDriver next = drivers.retrieve();

            if (driver.getId() < next.getId()) {
                drivers.insert(driver);
                return true;
            }
        }
    }

    @Override
    public IDriver findById(int driverId) {

        if (drivers.empty())
            return null;

        drivers.findFirst();

        while (true) {
            IDriver current = drivers.retrieve();

            if (current.getId() == driverId)
                return current;

            if (drivers.last())
                break;

            drivers.findNext();
        }

        return null;
    }

    @Override
    public LinkedList<IDriver> findByName(String fullName) {

        LinkedList<IDriver> result = new LinkedList<IDriver>();

        if (fullName == null || drivers.empty())
            return result;

        drivers.findFirst();

        while (true) {
            IDriver current = drivers.retrieve();

            if (fullName.equals(current.getName()))
                result.insert(current);

            if (drivers.last())
                break;

            drivers.findNext();
        }

        return result;
    }

    @Override
    public IDriver findByVehiclePlate(String vehiclePlate) {

        if (vehiclePlate == null || drivers.empty())
            return null;

        drivers.findFirst();

        while (true) {
            IDriver current = drivers.retrieve();

            if (vehiclePlate.equals(current.getVehiclePlate()))
                return current;

            if (drivers.last())
                break;

            drivers.findNext();
        }

        return null;
    }

    @Override
    public LinkedList<IDriver> findByVehicleType(VehicleType vehicleType) {

        LinkedList<IDriver> result = new LinkedList<IDriver>();

        if (vehicleType == null || drivers.empty())
            return result;

        drivers.findFirst();

        while (true) {
            IDriver current = drivers.retrieve();

            if (current.getVehicleType() == vehicleType)
                result.insert(current);

            if (drivers.last())
                break;

            drivers.findNext();
        }

        return result;
    }

    @Override
    public LinkedList<IDriver> getAll() {

        LinkedList<IDriver> result = new LinkedList<IDriver>();

        if (drivers.empty())
            return result;

        drivers.findFirst();

        while (true) {
            result.insert(drivers.retrieve());

            if (drivers.last())
                break;

            drivers.findNext();
        }

        return result;
    }

    @Override
    public boolean removeById(int driverId) {

        if (drivers.empty())
            return false;

        drivers.findFirst();

        while (true) {
            if (drivers.retrieve().getId() == driverId) {
                drivers.remove();
                return true;
            }

            if (drivers.last())
                break;

            drivers.findNext();
        }

        return false;
    }

    @Override
    public boolean removeByVehiclePlate(String vehiclePlate) {

        if (vehiclePlate == null || drivers.empty())
            return false;

        drivers.findFirst();

        while (true) {
            if (vehiclePlate.equals(drivers.retrieve().getVehiclePlate())) {
                drivers.remove();
                return true;
            }

            if (drivers.last())
                break;

            drivers.findNext();
        }

        return false;
    }

    @Override
    public int removeByName(String fullName) {

        if (fullName == null || drivers.empty())
            return 0;

        int count = 0;
        drivers.findFirst();

        while (!drivers.empty()) {
            boolean isLast = drivers.last();

            if (fullName.equals(drivers.retrieve().getName())) {
                drivers.remove();
                count++;

                if (isLast)
                    break;
            } else {
                if (isLast)
                    break;

                drivers.findNext();
            }
        }

        return count;
    }

    @Override
    public int removeByVehicleType(VehicleType vehicleType) {

        if (vehicleType == null || drivers.empty())
            return 0;

        int count = 0;
        drivers.findFirst();

        while (!drivers.empty()) {
            boolean isLast = drivers.last();

            if (drivers.retrieve().getVehicleType() == vehicleType) {
                drivers.remove();
                count++;

                if (isLast)
                    break;
            } else {
                if (isLast)
                    break;

                drivers.findNext();
            }
        }

        return count;
    }

    @Override
    public int size() {

        if (drivers.empty())
            return 0;

        int count = 0;
        drivers.findFirst();

        while (true) {
            count++;

            if (drivers.last())
                break;

            drivers.findNext();
        }

        return count;
    }
}
