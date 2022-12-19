package CarWasherProject;

public class Truck extends Wehicle{
    public Truck(double timeToWash) {
        super(timeToWash);
    }

    public String toString() {
            return "ID:"+Thread.currentThread().getId() + " - "+ getClass().getName();
    }
}
