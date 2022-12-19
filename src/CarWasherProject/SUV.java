/*
Kevin Sbarski 324589480
Amit Sherman 209284017
 */
package CarWasherProject;

public class SUV extends Wehicle{


    public SUV(double timeToWash) {
        super(timeToWash);
    }
    public String toString(){
        return "ID:"+Thread.currentThread().getId() + " - "+ getClass().getName();
    }
}
