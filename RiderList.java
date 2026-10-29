package phase1;

import java.util.LinkedList;
import java.util.Objects;

public class RiderList implements IRiderList {

	private LinkedList<IRider> riders;

	public RiderList() {
		riders = new LinkedList<IRider>();
	}

	@Override
	public boolean add(IRider rider) {

		if (rider == null)
			return false;

		int i = 0;

		while (i < riders.size()) {

			if (riders.get(i).getId() == rider.getId())
				return false;

			if (riders.get(i).getId() > rider.getId()) {
				riders.add(i, rider);
				return true;
			}

			i++;
		}

		riders.add(rider);
		return true;
	}

	@Override
	public IRider findById(int riderId) {

		for (int i = 0; i < riders.size(); i++) {

			if (riders.get(i).getId() == riderId)
				return riders.get(i);
		}

		return null;
	}

	@Override
	public LinkedList<IRider> findByName(String fullName) {

		LinkedList<IRider> result = new LinkedList<IRider>();

		for (int i = 0; i < riders.size(); i++) {

			if (Objects.equals(riders.get(i).getName(), fullName))
				result.add(riders.get(i));
		}

		return result;
	}

	@Override
	public IRider findByEmail(String email) {

		for (int i = 0; i < riders.size(); i++) {

			if (Objects.equals(riders.get(i).getEmail(), email))
				return riders.get(i);
		}

		return null;
	}

	@Override
	public LinkedList<IRider> findByHomeCity(String homeCity) {

		LinkedList<IRider> result = new LinkedList<IRider>();

		for (int i = 0; i < riders.size(); i++) {

			if (Objects.equals(riders.get(i).getHomeCity(), homeCity))
				result.add(riders.get(i));
		}

		return result;
	}

	@Override
	public LinkedList<IRider> getAll() {
		return new LinkedList<IRider>(riders);
	}

	@Override
	public boolean removeById(int riderId) {

		for (int i = 0; i < riders.size(); i++) {

			if (riders.get(i).getId() == riderId) {
				riders.remove(i);
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean removeByEmail(String email) {

		for (int i = 0; i < riders.size(); i++) {

			if (Objects.equals(riders.get(i).getEmail(), email)) {
				riders.remove(i);
				return true;
			}
		}

		return false;
	}

	@Override
	public int removeByName(String fullName) {

		int count = 0;
		int i = 0;

		while (i < riders.size()) {

			if (Objects.equals(riders.get(i).getName(), fullName)) {
				riders.remove(i);
				count++;
			} else {
				i++;
			}
		}

		return count;
	}

	@Override
	public int removeByHomeCity(String homeCity) {

		int count = 0;
		int i = 0;

		while (i < riders.size()) {

			if (Objects.equals(riders.get(i).getHomeCity(), homeCity)) {
				riders.remove(i);
				count++;
			} else {
				i++;
			}
		}

		return count;
	}

	@Override
	public int size() {
		return riders.size();
	}
}