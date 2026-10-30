public class DriverList implements IDriverList{
    private LinkedList<IDriver> drivers;

    public DriverList(){
        drivers = new LindeList<>();
    }
@Override 
    public boolean add(IDriver driver){
        
        if(drivers.empty()){
            drivers.insert(driver);
            return true;}
        
        drivers.findFirst();
        
        while(!drivers.last()){
            IDriver tmp = drivers.retrieve();
            if(tmp.getId() == driver.getId())
                return false;
           else if (tmp.getId() > driver.getId()) {
            drivers.update(driver);
            drivers.insert(tmp);
               return true;
}
            else
             drivers.findNext();   
            
        }
        
        IDriver tmp2 = drivers.retrieve();
        if(tmp2.getId() == driver.getId())
                return false;
           else if (tmp2.getId() > driver.getId()) {
            drivers.update(driver);
            drivers.insert(tmp2);
               return true;
    }
        drivers.insert(driver);
        return true;
    
}
