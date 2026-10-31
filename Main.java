import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		RideSharingSystem system = new RideSharingSystem();

		system.loadRidersFromCSV("riders_100.csv");
		system.loadDriversFromCSV("drivers_30.csv");
		system.loadRidesFromCSV("rides_40.csv");
		
		int choice;

		do {

			System.out.println();
			System.out.println("1. List all riders.");
			System.out.println("2. Search for riders by home city.");
			System.out.println("3. Search for riders by name.");
			System.out.println("4. Add a rider.");
			System.out.println("5. List all drivers.");
			System.out.println("6. Search for drivers by vehicle type.");
			System.out.println("7. Add a driver.");
			System.out.println("8. List all rides alphabetically by pickup location.");
			System.out.println("9. Search for rides by pickup location.");
			System.out.println("10. Search for rides by rider name.");
			System.out.println("11. List the participants of a shared ride by pickup location.");
			System.out.println("12. Add a ride.");
			System.out.println("0. Exit.");

			System.out.print("Enter choice: ");

			choice = input.nextInt();
			input.nextLine();

			switch (choice) {

			case 1: {

				LinkedList<IRider> riders = system.getAllRiders();

				if (riders.empty()) {
					System.out.println("No riders found.");
				} else {

					riders.findFirst();

					while (true) {

						System.out.println(riders.retrieve());

						if (riders.last())
							break;

						riders.findNext();
					}
				}

				break;
			}

			case 2: {

				System.out.print("Enter home city: ");
				String city = input.nextLine();

				LinkedList<IRider> riders = system.searchRidersByHomeCity(city);

				if (riders.empty()) {
					System.out.println("No riders found.");
				} else {

					riders.findFirst();

					while (true) {

						System.out.println(riders.retrieve());

						if (riders.last())
							break;

						riders.findNext();
					}
				}

				break;
			}

			case 3: {

				System.out.print("Enter rider name: ");
				String name = input.nextLine();

				LinkedList<IRider> riders = system.searchRidersByName(name);

				if (riders.empty()) {
					System.out.println("No riders found.");
				} else {

					riders.findFirst();

					while (true) {

						System.out.println(riders.retrieve());

						if (riders.last())
							break;

						riders.findNext();
					}
				}

				break;
			}

			case 4: {

				try {

					System.out.print("Rider ID: ");
					int id = input.nextInt();
					input.nextLine();

					System.out.print("Name: ");
					String name = input.nextLine();

					System.out.print("Phone number: ");
					String phone = input.nextLine();

					System.out.print("Email: ");
					String email = input.nextLine();

					System.out.print("Home city: ");
					String city = input.nextLine();

					Rider rider = new Rider(id, name, phone, email, city);

					if (system.addRider(rider))
						System.out.println("Rider added successfully.");
					else
						System.out.println("Failed to add rider.");

				} catch (Exception e) {

					System.out.println("Invalid rider data.");
				}

				break;
			}

			case 5: {

				LinkedList<IDriver> drivers = system.getAllDrivers();

				if (drivers.empty()) {
					System.out.println("No drivers found.");
				} else {

					drivers.findFirst();

					while (true) {

						System.out.println(drivers.retrieve());

						if (drivers.last())
							break;

						drivers.findNext();
					}
				}

				break;
			}

			case 6: {

				try {

					System.out.print("Vehicle type (SEDAN, LUXURY_SEDAN, SUV, VAN): ");

					String typeText = input.nextLine().trim().toUpperCase().replace(" ", "_");

					VehicleType type = VehicleType.valueOf(typeText);

					LinkedList<IDriver> drivers = system.searchDriversByVehicleType(type);

					if (drivers.empty()) {
						System.out.println("No drivers found.");
					} else {

						drivers.findFirst();

						while (true) {

							System.out.println(drivers.retrieve());

							if (drivers.last())
								break;

							drivers.findNext();
						}
					}

				} catch (Exception e) {

					System.out.println("Invalid vehicle type.");
				}

				break;
			}

			case 7: {

				try {

					System.out.print("Driver ID: ");
					int id = input.nextInt();
					input.nextLine();

					System.out.print("Name: ");
					String name = input.nextLine();

					System.out.print("Phone number: ");
					String phone = input.nextLine();

					System.out.print("Vehicle plate: ");
					String plate = input.nextLine();

					System.out.print("Vehicle type (SEDAN, LUXURY_SEDAN, SUV, VAN): ");

					String typeText = input.nextLine().trim().toUpperCase().replace(" ", "_");

					VehicleType type = VehicleType.valueOf(typeText);

					Driver driver = new Driver(id, name, phone, plate, type);

					if (system.addDriver(driver))
						System.out.println("Driver added successfully.");
					else
						System.out.println("Failed to add driver.");

				} catch (Exception e) {

					System.out.println("Invalid driver data.");
				}

				break;
			}

			case 8: {

				LinkedList<IRide> rides = system.getAllRidesAlphabetically();

				if (rides.empty()) {
					System.out.println("No rides found.");
				} else {

					rides.findFirst();

					while (true) {

						System.out.println(rides.retrieve());

						if (rides.last())
							break;

						rides.findNext();
					}
				}

				break;
			}

			case 9: {

				System.out.print("Pickup location: ");
				String location = input.nextLine();

				LinkedList<IRide> rides = system.searchRidesByPickupLocation(location);

				if (rides.empty()) {
					System.out.println("No rides found.");
				} else {

					rides.findFirst();

					while (true) {

						System.out.println(rides.retrieve());

						if (rides.last())
							break;

						rides.findNext();
					}
				}

				break;
			}

			case 10: {

				System.out.print("Rider name: ");
				String name = input.nextLine();

				LinkedList<IRide> rides = system.searchRidesByRiderName(name);

				if (rides.empty()) {
					System.out.println("No rides found.");
				} else {

					rides.findFirst();

					while (true) {

						System.out.println(rides.retrieve());

						if (rides.last())
							break;

						rides.findNext();
					}
				}

				break;
			}

			case 11: {

				System.out.print("Pickup location: ");
				String location = input.nextLine();

				LinkedList<IRider> riders = system.getSharedRideParticipants(location);

				if (riders.empty()) {
					System.out.println("No participants found.");
				} else {

					riders.findFirst();

					while (true) {

						System.out.println(riders.retrieve());

						if (riders.last())
							break;

						riders.findNext();
					}
				}

				break;
			}

			case 12: {

				try {

					System.out.print("Ride type (PRIVATE/SHARED): ");

					String type = input.nextLine().trim().toUpperCase();

					System.out.print("Pickup location: ");
					String pickupLocation = input.nextLine();

					System.out.print("Pickup date/time (MM/DD/YYYY HH:MM): ");

					String pickupText = input.nextLine();

					System.out.print("Drop-off date/time (MM/DD/YYYY HH:MM): ");

					String dropoffText = input.nextLine();

					System.out.print("Drop-off location: ");
					String dropoffLocation = input.nextLine();

					System.out.print("Driver ID: ");
					int driverId = input.nextInt();
					input.nextLine();

					String[] pickupParts = pickupText.trim().split(" ");

					String[] pickupDate = pickupParts[0].split("/");

					String[] pickupClock = pickupParts[1].split(":");

					IDateTime pickupTime = new DateTime(Integer.parseInt(pickupDate[2]),
							Integer.parseInt(pickupDate[0]), Integer.parseInt(pickupDate[1]),
							Integer.parseInt(pickupClock[0]), Integer.parseInt(pickupClock[1]));

					String[] dropoffParts = dropoffText.trim().split(" ");

					String[] dropoffDate = dropoffParts[0].split("/");

					String[] dropoffClock = dropoffParts[1].split(":");

					IDateTime dropoffTime = new DateTime(Integer.parseInt(dropoffDate[2]),
							Integer.parseInt(dropoffDate[0]), Integer.parseInt(dropoffDate[1]),
							Integer.parseInt(dropoffClock[0]), Integer.parseInt(dropoffClock[1]));

					boolean success;

					if (type.equals("PRIVATE")) {

						System.out.print("Rider ID: ");
						int riderId = input.nextInt();
						input.nextLine();

						success = system.schedulePrivateRide(pickupLocation, pickupTime, dropoffTime, dropoffLocation,
								riderId, driverId);
					}

					else if (type.equals("SHARED")) {

						System.out.print("Number of riders: ");

						int numberOfRiders = input.nextInt();

						input.nextLine();

						if (numberOfRiders < 1) {
							System.out.println("Invalid number of riders.");
							break;
						}

						int[] riderIds = new int[numberOfRiders];

						for (int i = 0; i < numberOfRiders; i++) {

							System.out.print("Rider ID " + (i + 1) + ": ");

							riderIds[i] = input.nextInt();
						}

						input.nextLine();

						success = system.scheduleSharedRide(pickupLocation, pickupTime, dropoffTime, dropoffLocation,
								riderIds, driverId);
					}

					else {

						System.out.println("Invalid ride type.");

						break;
					}

					if (success)
						System.out.println("Ride scheduled successfully.");
					else
						System.out.println("Failed to schedule ride.");

				} catch (Exception e) {

					System.out.println("Invalid ride data.");

					input.nextLine();
				}

				break;
			}

			case 0:

				System.out.println("Goodbye.");
				break;

			default:

				System.out.println("Invalid choice.");
			}

		} while (choice != 0);

		input.close();
	}
}
