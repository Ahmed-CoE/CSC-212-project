
public class DriverList implements IDriverList{

	private LinkedList <IDriver> drivers;
	
	public DriverList() {
		drivers = new LinkedList<IDriver>();
	}
	
	@Override
	public boolean add(IDriver driver) {
		boolean exist = false;
		drivers.findFirst();
		
		while(drivers.last){
			IDriver tmp = drivers.retrieve();

		if(tmp.getID() == driver.getID(){
				exist = true;
				break}

			else if(tmp.getID() < driver.getID())
				drivers.findNext();

			else{
				IDriver e = drivers.retrieve();
				drivers.update(tmp);
				drivers.insert(e);
			}
	}

}
