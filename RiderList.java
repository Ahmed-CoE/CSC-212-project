package phase1;

public class RiderList implements IRiderList {

	    private LinkedList<IRider> riders;

	    public RiderList() {
	        riders = new LinkedList<IRider>();
	    }

	    @Override
	    public boolean add(IRider rider) {

	        if (rider == null)
	            return false;

	        if (riders.empty()) {
	            riders.insert(rider);
	            return true;
	        }

	        riders.findFirst();

	        while (true) {

	            if (riders.retrieve().getId() == rider.getId())
	                return false;

	            if (riders.last())
	                break;

	            riders.findNext();
	        }

	        riders.findFirst();

	        if (rider.getId() < riders.retrieve().getId()) {
	            LinkedList<IRider> temp = new LinkedList<IRider>();
	            temp.insert(rider);

	            while (true) {
	                temp.insert(riders.retrieve());

	                if (riders.last())
	                    break;

	                riders.findNext();
	            }

	            riders = temp;
	            return true;
	        }

	        while (!riders.last()) {

	            IRider current = riders.retrieve();

	            riders.findNext();

	            if (rider.getId() < riders.retrieve().getId()) {
	                riders.findFirst();

	                while (riders.retrieve() != current)
	                    riders.findNext();

	                riders.insert(rider);
	                return true;
	            }
	        }

	        riders.insert(rider);
	        return true;
	    }

	    @Override
	    public IRider findById(int riderId) {

	        if (riders.empty())
	            return null;

	        riders.findFirst();

	        while (true) {

	            if (riders.retrieve().getId() == riderId)
	                return riders.retrieve();

	            if (riders.last())
	                break;

	            riders.findNext();
	        }

	        return null;
	    }

	    @Override
	    public LinkedList<IRider> findByName(String fullName) {

	        LinkedList<IRider> result = new LinkedList<IRider>();

	        if (riders.empty())
	            return result;

	        riders.findFirst();

	        while (true) {

	            if (riders.retrieve().getName().equals(fullName))
	                result.insert(riders.retrieve());

	            if (riders.last())
	                break;

	            riders.findNext();
	        }

	        return result;
	    }

	    @Override
	    public IRider findByEmail(String email) {

	        if (riders.empty())
	            return null;

	        riders.findFirst();

	        while (true) {

	            if (riders.retrieve().getEmail().equals(email))
	                return riders.retrieve();

	            if (riders.last())
	                break;

	            riders.findNext();
	        }

	        return null;
	    }

	    @Override
	    public LinkedList<IRider> findByHomeCity(String homeCity) {

	        LinkedList<IRider> result = new LinkedList<IRider>();

	        if (riders.empty())
	            return result;

	        riders.findFirst();

	        while (true) {

	            if (riders.retrieve().getHomeCity().equals(homeCity))
	                result.insert(riders.retrieve());

	            if (riders.last())
	                break;

	            riders.findNext();
	        }

	        return result;
	    }

	    @Override
	    public LinkedList<IRider> getAll() {

	        LinkedList<IRider> result = new LinkedList<IRider>();

	        if (riders.empty())
	            return result;

	        riders.findFirst();

	        while (true) {

	            result.insert(riders.retrieve());

	            if (riders.last())
	                break;

	            riders.findNext();
	        }

	        return result;
	    }

	    @Override
	    public boolean removeById(int riderId) {

	        if (riders.empty())
	            return false;

	        riders.findFirst();

	        while (true) {

	            if (riders.retrieve().getId() == riderId) {
	                riders.remove();
	                return true;
	            }

	            if (riders.last())
	                break;

	            riders.findNext();
	        }

	        return false;
	    }

	    @Override
	    public boolean removeByEmail(String email) {

	        if (riders.empty())
	            return false;

	        riders.findFirst();

	        while (true) {

	            if (riders.retrieve().getEmail().equals(email)) {
	                riders.remove();
	                return true;
	            }

	            if (riders.last())
	                break;

	            riders.findNext();
	        }

	        return false;
	    }

	    @Override
	    public int removeByName(String fullName) {

	        int count = 0;

	        if (riders.empty())
	            return count;

	        riders.findFirst();

	        while (!riders.empty()) {

	            if (riders.retrieve().getName().equals(fullName)) {
	                riders.remove();
	                count++;
	            } else {
	                if (riders.last())
	                    break;

	                riders.findNext();
	            }
	        }

	        return count;
	    }

	    @Override
	    public int removeByHomeCity(String homeCity) {

	        int count = 0;

	        if (riders.empty())
	            return count;

	        riders.findFirst();

	        while (!riders.empty()) {

	            if (riders.retrieve().getHomeCity().equals(homeCity)) {
	                riders.remove();
	                count++;
	            } else {
	                if (riders.last())
	                    break;

	                riders.findNext();
	            }
	        }

	        return count;
	    }

	    @Override
	    public int size() {

	        if (riders.empty())
	            return 0;

	        int count = 0;

	        riders.findFirst();

	        while (true) {

	            count++;

	            if (riders.last())
	                break;

	            riders.findNext();
	        }

	        return count;
	    }
	}
