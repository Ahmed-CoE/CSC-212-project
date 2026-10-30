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

    @Override
    public IDriver findById(int id){
        if(drivers.empty())
           return null;
        
        drivers.findFirst();
        while(!drivers.last()){
            IDriver  tmp = drivers.retrieve();
            if(tmp.getId() == id){
               return tmp;}
            drivers.findNext();
        }
         IDriver  tmp2 = drivers.retrieve();
            if(tmp2.getId() == id){
               return tmp2;}
        return null;
    }

    @Override
    public LinkedList<IDriver> findByName(String name){
        LinkedList <IDriver> list = new LinkedList <IDriver>();

         if(drivers.empty())
            return list;
        
        drivers.findFirst();
        while(!drivers.last()){
           IDriver tmp = drivers.retrieve();
            if(tmp.getName().equals(name))
                list.insert(tmp);
            drivers.findNext();
        }
         IDriver tmp2 = drivers.retrieve();
            if(tmp2.getName().equals(name))
                list.insert(tmp2);
        return list;
    }

    @Override
    public IDriver findByVehiclePlate(Stirng vehiclePlate){
        if(drivers.empty)
            return null;

        drivers.findFirst();
        while(!drivers.last()){
            IDriver tmp = drivers.retrieve();
            if(tmp.gset
        }
        
    }















    
