
public class Driver extends Person implements IDriver {
	private String vehiclePlate;
	private VehicleType vehicleType;
	
	public Driver(String name , int id, String PhoneNumber, String vehiclePlate, VehicleType vehicleType) {
		super(name , id, PhoneNumber);
		this.setVehiclePlate(vehiclePlate);
		this.vehicleType = vehicleType;
	}
	
	@Override
	public String getVehiclePlate() {
		return vehiclePlate;
	}
	
	@Override
	public void setVehiclePlate(String vehiclePlate) {
		if( vehiclePlate != null && vehiclePlate.length() == 7) {		
			for(int i = 0; i< 7; i++) {
				char symbol = vehiclePlate.charAt(i);
				if(i< 3  && (symbol < 'A' || symbol > 'Z')) {
					throw new IllegalArgumentException();
				}
				else if(i>2 && !Character.isDigit(symbol)){
					throw new IllegalArgumentException();
					
				}
				
			}
			
			this.vehiclePlate = vehiclePlate;
			
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	
	@Override
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	@Override
	public int compareTo(IDriver other) {
		int idForFirstDriver = super.getId();
		int idForSecondDriver = other.getId();
		int result = (idForFirstDriver - idForSecondDriver);
		return result;
	}

}











