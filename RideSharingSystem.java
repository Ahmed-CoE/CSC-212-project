package lst;

public class RideSharingSystem {
 
  
  
  
  
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

	            IRider rider = new Rider(
	                    name,
	                    id,
	                    phoneNumber,
	                    email,
	                    homeCity
	            );

	            if (!addRider(rider))
	                return false;
	        }

	        return true;

	    } catch (java.io.IOException | IllegalArgumentException e) {
	        return false;
	    }
	}

}
