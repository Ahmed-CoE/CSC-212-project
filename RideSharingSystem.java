

import java.io.*;
import java.util.Scanner;  

public class RideSharingSystem implements IRideSharingSystem {
    private RiderList riders;
    private DriverList drivers;
    private RideList rides;

    public RideSharingSystem() {
        riders = new RiderList();
        drivers = new DriverList();
        rides = new RideList();
    }

	
public boolean loadDriversFromCSV(String driversFilePath) {
        try {
            Scanner file = new Scanner(new File(driversFilePath));
            while (file.hasNextLine()) {
                String[] f = file.nextLine().split(",");
                try {
                    VehicleType type = VehicleType.valueOf(f[4]);  // "SUV" -> VehicleType.SUV
                    addDriver(new Driver(Integer.parseInt(f[0]), f[1], f[2], f[3], type));
                } catch (Exception e) {
                    // a wrong line (bad plate, unknown type, ...) is skipped
                }
            }
            file.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

  
 @Override
public boolean addDriver(IDriver driver) {
    if (driver == null)
        return false;

    if (drivers.findById(driver.getId()) != null)
        return false;

    if (drivers.findByVehiclePlate(driver.getVehiclePlate()) != null)
        return false;

    return drivers.add(driver);
}



  public IDriver searchDriverById(int driverId) {
        return drivers.findById(driverId);
    }

  public IDriver searchDriverByVehiclePlate(String vehiclePlate) {
        return drivers.findByVehiclePlate(vehiclePlate);
    }

  public LinkedList<IDriver> searchDriversByVehicleType(VehicleType vehicleType) {
        return drivers.findByVehicleType(vehicleType);
    }

  public LinkedList<IDriver> getAllDrivers() {
        return drivers.getAll();
    }

  public boolean removeDriver(int driverId) {
        IDriver driver = drivers.findById(driverId);
        if (driver == null)
            return false;
        LinkedList<IRide> h = driver.getRideHistory();
        while (!h.empty()) {                    
            h.findFirst();
            deleteRide(h.retrieve());
        }
        return drivers.removeById(driverId);
    }

    private void deleteRide(IRide ride) {
        rides.removeRideById(ride.getRideId());
        removeFromHistory(ride.getDriver(), ride);
        if (ride instanceof IPrivateRide) {
            IPrivateRide privateRide = (IPrivateRide) ride;
            removeFromHistory(privateRide.getRider(), ride);
        } else {
            ISharedRide sharedRide = (ISharedRide) ride;
            LinkedList<IRider> list = sharedRide.getParticipants();
            if (!list.empty()) {
                list.findFirst();
                while (!list.last()) {
                    removeFromHistory(list.retrieve(), ride);
                    list.findNext();
                }
                removeFromHistory(list.retrieve(), ride);    
            }
        }
    }

    
    private void removeFromHistory(IPerson person, IRide ride) {
        LinkedList<IRide> h = person.getRideHistory();
        if (h.empty())
            return;
        h.findFirst();
        while (!h.last()) {
            if (h.retrieve().getRideId() == ride.getRideId()) {
                h.remove();
                return;
            }
            h.findNext();
        }
        if (h.retrieve().getRideId() == ride.getRideId())    
            h.remove();
    }

	

  
  







  
  

 
  
  
  
  
  @Override
	public boolean addRider(IRider rider) {
	    if (rider == null)
	        return false;

	    return riders.add(rider);
	}

	@Override
	public IRider searchRiderById(int riderId) {
	    return riders.findById(riderId);
	}

	@Override
	public IRider searchRiderByEmail(String email) {
	    return riders.findByEmail(email);
	}

	@Override
	public LinkedList<IRider> searchRidersByName(String fullName) {
	    return riders.findByName(fullName);
	}

	@Override
	public LinkedList<IRider> searchRidersByHomeCity(String homeCity) {
	    return riders.findByHomeCity(homeCity);
	}

	@Override
	public LinkedList<IRider> getAllRiders() {
	    return riders.getAll();
	}
	
	@Override
	public boolean removeRider(int riderId) {

	    if (riders.findById(riderId) == null)
	        return false;

	    LinkedList<IRide> allRides = rides.getAllAlphabetically();

	    if (!allRides.empty()) {

	        allRides.findFirst();

	        while (true) {

	            IRide ride = allRides.retrieve();

	            if (ride.hasRider(riderId)) {

	                if (ride instanceof IPrivateRide) {

	                    rides.removeRideById(ride.getRideId());

	                } else if (ride instanceof ISharedRide) {

	                    ISharedRide sharedRide = (ISharedRide) ride;

	                    sharedRide.removeParticipantById(riderId);

	                    if (sharedRide.isEmpty()) {
	                        rides.removeRideById(ride.getRideId());
	                    }
	                }
	            }

	            if (allRides.last())
	                break;

	            allRides.findNext();
	        }
	    }

	    return riders.removeById(riderId);
	}
	
	@Override
	public boolean loadRidersFromCSV(String ridersFilePath) {

	    try (java.io.BufferedReader reader =
	            new java.io.BufferedReader(
	                    new java.io.FileReader(ridersFilePath))) {

	        String line;

	        while ((line = reader.readLine()) != null) {

	            if (line.trim().isEmpty())
	                continue;

	            String[] data = line.split(",", -1);

	            if (data.length != 5)
	                return false;

	            int id = Integer.parseInt(data[0].trim());

	            String name = data[1].trim();
	            String email = data[2].trim();
	            String phoneNumber = data[3].trim();
	            String homeCity = data[4].trim();

	            IRider rider = new Rider(id,name,phoneNumber,email, homeCity);

	            if (!addRider(rider))
	                return false;
	        }

	        return true;

	    } catch (java.io.IOException | IllegalArgumentException e) {
	        return false;
	    }
	}

}
