

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
private int nextRideId = 1;

private boolean hasConflict(IDateTime newPickup,
                            IDateTime newDropoff,
                            IRide oldRide) {

    return newPickup.compareTo(oldRide.getDropoffTime()) < 0
            && oldRide.getPickupTime().compareTo(newDropoff) < 0;
}

private boolean personHasConflict(IPerson person,
                                  IDateTime pickupTime,
                                  IDateTime dropoffTime) {

    LinkedList<IRide> history = person.getRideHistory();

    if (history.empty())
        return false;

    history.findFirst();

    while (true) {

        IRide ride = history.retrieve();

        if (hasConflict(pickupTime, dropoffTime, ride))
            return true;

        if (history.last())
            break;

        history.findNext();
    }

    return false;
}

private void addToHistory(IPerson person, IRide ride) {

    LinkedList<IRide> history = person.getRideHistory();

    if (history.empty()) {
        history.insert(ride);
        return;
    }

    history.findFirst();

    while (!history.last()) {
        history.findNext();
    }

    history.insert(ride);
}

@Override
public boolean schedulePrivateRide(String pickupLocation,
                                   IDateTime pickupTime,
                                   IDateTime dropoffTime,
                                   String dropoffLocation,
                                   int riderId,
                                   int driverId) {

    IRider rider = riderList.findById(riderId);

    if (rider == null)
        return false;

    IDriver driver = driverList.findById(driverId);

    if (driver == null)
        return false;

    if (pickupTime.compareTo(dropoffTime) >= 0)
        return false;

    if (personHasConflict(rider, pickupTime, dropoffTime))
        return false;

    if (personHasConflict(driver, pickupTime, dropoffTime))
        return false;

    PrivateRide ride = new PrivateRide(
            nextRideId,
            pickupLocation,
            pickupTime,
            dropoffTime,
            dropoffLocation,
            driver,
            rider
    );

    if (!rideList.addRide(ride))
        return false;

    addToHistory(rider, ride);
    addToHistory(driver, ride);

    nextRideId++;

    return true;
}

@Override
public boolean scheduleSharedRide(String pickupLocation,
                                  IDateTime pickupTime,
                                  IDateTime dropoffTime,
                                  String dropoffLocation,
                                  int[] riderIds,
                                  int driverId) {

    if (riderIds == null || riderIds.length == 0)
        return false;

    IDriver driver = driverList.findById(driverId);

    if (driver == null)
        return false;

    if (pickupTime.compareTo(dropoffTime) >= 0)
        return false;

    if (personHasConflict(driver, pickupTime, dropoffTime))
        return false;

    for (int i = 0; i < riderIds.length; i++) {

        IRider rider = riderList.findById(riderIds[i]);

        if (rider == null)
            return false;

        if (personHasConflict(rider, pickupTime, dropoffTime))
            return false;
    }

    SharedRide ride = new SharedRide(
            nextRideId,
            pickupLocation,
            pickupTime,
            dropoffTime,
            dropoffLocation,
            driver
    );

    for (int i = 0; i < riderIds.length; i++) {

        IRider rider = riderList.findById(riderIds[i]);

        if (!ride.addParticipant(rider))
            return false;
    }

    if (!rideList.addRide(ride))
        return false;

    for (int i = 0; i < riderIds.length; i++) {

        IRider rider = riderList.findById(riderIds[i]);

        addToHistory(rider, ride);
    }

    addToHistory(driver, ride);

    nextRideId++;

    return true;
}

@Override
public LinkedList<IRide> searchRidesByPickupLocation(String pickupLocation) {

    return rideList.findByPickupLocation(pickupLocation);
}

@Override
public LinkedList<IRide> searchRidesByRiderName(String riderName) {

    return rideList.findByRiderName(riderName);
}

@Override
public LinkedList<IRider> getSharedRideParticipants(String pickupLocation) {

    LinkedList<IRider> result = new LinkedList<IRider>();

    LinkedList<IRide> matches =
            rideList.findByPickupLocation(pickupLocation);

    if (matches.empty())
        return result;

    matches.findFirst();

    while (true) {

        IRide ride = matches.retrieve();

        if (ride instanceof ISharedRide) {

            ISharedRide sharedRide = (ISharedRide) ride;
            LinkedList<IRider> participants =
                    sharedRide.getParticipants();

            if (!participants.empty()) {

                participants.findFirst();

                while (true) {

                    result.insert(participants.retrieve());

                    if (participants.last())
                        break;

                    participants.findNext();
                }
            }

            return result;
        }

        if (matches.last())
            break;

        matches.findNext();
    }

    return result;
}

@Override
public LinkedList<IRide> getAllRidesAlphabetically() {

    return rideList.getAllAlphabetically();
}

private IDateTime parseDateTime(String value) {

    String[] parts = value.trim().split(" ");

    String[] date = parts[0].split("/");
    String[] time = parts[1].split(":");

    int month = Integer.parseInt(date[0]);
    int day = Integer.parseInt(date[1]);
    int year = Integer.parseInt(date[2]);

    int hour = Integer.parseInt(time[0]);
    int minute = Integer.parseInt(time[1]);

    return new DateTime(year, month, day, hour, minute);
}

@Override
public boolean loadRidesFromCSV(String ridesFilePath) {

    try {

        java.io.BufferedReader reader =
                new java.io.BufferedReader(
                        new java.io.FileReader(ridesFilePath)
                );

        String line;

        while ((line = reader.readLine()) != null) {

            line = line.trim();

            if (line.isEmpty())
                continue;

            String[] data = line.split(",");

            if (data.length != 7) {
                reader.close();
                return false;
            }

            String rideType = data[0].trim();
            String pickupLocation = data[1].trim();

            IDateTime pickupTime =
                    parseDateTime(data[2].trim());

            IDateTime dropoffTime =
                    parseDateTime(data[3].trim());

            String dropoffLocation = data[4].trim();

            int driverId =
                    Integer.parseInt(data[5].trim());

            String riderData = data[6].trim();

            if (rideType.equals("PRIVATE")) {

                int riderId =
                        Integer.parseInt(riderData);

                if (!schedulePrivateRide(
                        pickupLocation,
                        pickupTime,
                        dropoffTime,
                        dropoffLocation,
                        riderId,
                        driverId)) {

                    reader.close();
                    return false;
                }

            } else if (rideType.equals("SHARED")) {

                String[] ids = riderData.split(";");

                int[] riderIds = new int[ids.length];

                for (int i = 0; i < ids.length; i++) {
                    riderIds[i] =
                            Integer.parseInt(ids[i].trim());
                }

                if (!scheduleSharedRide(
                        pickupLocation,
                        pickupTime,
                        dropoffTime,
                        dropoffLocation,
                        riderIds,
                        driverId)) {

                    reader.close();
                    return false;
                }

            } else {

                reader.close();
                return false;
            }
        }

        reader.close();

        return true;

    } catch (Exception e) {

        return false;
    }
}
}
