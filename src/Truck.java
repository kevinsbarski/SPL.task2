public class Truck extends Wehicle{
    public String toString(){
        return getClass().getName();
    }
    @Override
    public void run() {
        WehicleWasher.goToWehicleWasher();
    }
}
