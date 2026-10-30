public class DriverList implements IDriverList {
	private LinkedList<IDriver> drivers;

	public DriverList() {
		drivers = new LinkedList<>();
	}

	@Override
	public boolean add(IDriver driver) {

		if (drivers.empty()) {
			drivers.insert(driver);
			return true;
		}

		drivers.findFirst();

		while (!drivers.last()) {
			IDriver tmp = drivers.retrieve();
			if (tmp.getId() == driver.getId())
				return false;
			else if (tmp.getId() > driver.getId()) {
				drivers.update(driver);
				drivers.insert(tmp);
				return true;
			} else
				drivers.findNext();

		}

		IDriver tmp2 = drivers.retrieve();
		if (tmp2.getId() == driver.getId())
			return false;
		else if (tmp2.getId() > driver.getId()) {
			drivers.update(driver);
			drivers.insert(tmp2);
			return true;
		}
		drivers.insert(driver);
		return true;

	}

	@Override
	public IDriver findById(int id) {
		if (drivers.empty())
			return null;

		drivers.findFirst();
		while (!drivers.last()) {
			IDriver tmp = drivers.retrieve();
			if (tmp.getId() == id) {
				return tmp;
			}
			drivers.findNext();
		}
		IDriver tmp2 = drivers.retrieve();
		if (tmp2.getId() == id) {
			return tmp2;
		}
		return null;
	}

	@Override
	public LinkedList<IDriver> findByName(String name) {
		LinkedList<IDriver> list = new LinkedList<IDriver>();

		if (drivers.empty())
			return list;

		drivers.findFirst();
		while (!drivers.last()) {
			IDriver tmp = drivers.retrieve();
			if (tmp.getName().equals(name))
				list.insert(tmp);
			drivers.findNext();
		}
		IDriver tmp2 = drivers.retrieve();
		if (tmp2.getName().equals(name))
			list.insert(tmp2);
		return list;
	}

	@Override
	public IDriver findByVehiclePlate(String vehiclePlate) {
		if (drivers.empty())
			return null;

		drivers.findFirst();
		while (!drivers.last()) {
			IDriver tmp = drivers.retrieve();
			if (tmp.getVehiclePlate().equals(vehiclePlate)) {
				return tmp;
			}
			drivers.findNext();
		}
		IDriver tmp2 = drivers.retrieve();
		if (tmp2.getVehiclePlate().equals(vehiclePlate)) {
			return tmp2;
		}

		return null;
	}

	@Override
	public LinkedList<IDriver> findByVehicleType(VehicleType vehicleType) {
		LinkedList<IDriver> list = new LinkedList<IDriver>();

		if (drivers.empty())
			return list;

		drivers.findFirst();
		while (!drivers.last()) {
			IDriver tmp = drivers.retrieve();
			if (tmp.getVehicleType() == vehicleType) {
				list.insert(tmp);
			}
			drivers.findNext();
		}
		IDriver tmp2 = drivers.retrieve();
		if (tmp2.getVehicleType() == vehicleType)
			list.insert(tmp2);

		return list;
	}

	@Override
	public LinkedList<IDriver> getAll() {
		return drivers;
	}

	@Override
	public boolean removeById(int id) {
		if (drivers.empty())
			return false;

		drivers.findFirst();
		while (!drivers.last()) {
			IDriver tmp = drivers.retrieve();
			if (tmp.getId() == id) {
				drivers.remove();
				return true;
			}
			drivers.findNext();
		}
		IDriver tmp2 = drivers.retrieve();
		if (tmp2.getId() == id) {
			drivers.remove();
			return true;
		}
		return false;
	}

	@Override
	public boolean removeByVehiclePlate(String vehiclePlate) {
		if (drivers.empty())
			return false;

		drivers.findFirst();
		while (!drivers.last()) {
			IDriver tmp = drivers.retrieve();
			if (tmp.getVehiclePlate().equals(vehiclePlate)) {
				drivers.remove();
				return true;
			}
			drivers.findNext();
		}
		IDriver tmp2 = drivers.retrieve();
		if (tmp2.getVehiclePlate().equals(vehiclePlate)) {
			drivers.remove();
			return true;
		}
		return false;
	}

	@Override
	public int removeByName(String name) {
		int count = 0;
		if (drivers.empty())
			return count;

		drivers.findFirst();
		while (!drivers.last()) {
			IDriver tmp = drivers.retrieve();
			if (tmp.getName().equals(name)) {
				count++;
				drivers.remove();
			} else {
				drivers.findNext();
			}
		}
		IDriver tmp2 = drivers.retrieve();
		if (tmp2.getName().equals(name)) {
			count++;
			drivers.remove();
		}
		return count;
	}

	@Override
	public int removeByVehicleType(VehicleType vehicleType) {
		int count = 0;

		if (drivers.empty())
			return count;

		drivers.findFirst();
		while (!drivers.last()) {
			IDriver tmp = drivers.retrieve();
			if (tmp.getVehicleType() == vehicleType) {
				count++;
				drivers.remove();
			} else {
				drivers.findNext();
			}
		}

		IDriver tmp2 = drivers.retrieve();
		if (tmp2.getVehicleType() == vehicleType) {
			count++;
			drivers.remove();
		}
		return count;
	}

	@Override
	public int size() {
		int count = 0;
		if (drivers.empty())
			return count;

		drivers.findFirst();
		while (!drivers.last()) {
			count++;
			drivers.findNext();
		}
		count++;
		return count;
	}

}
