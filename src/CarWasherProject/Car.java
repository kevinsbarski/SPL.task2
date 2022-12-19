package CarWasherProject;

public class Car extends Wehicle{

    public Car(double timeToWash) {
        super(timeToWash);
    }

    public String toString(){

            return "ID:"+Thread.currentThread().getId() + " - "+ getClass().getName();
    }

}
