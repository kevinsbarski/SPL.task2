public class MiniBus extends Wehicle{
    public MiniBus(double timeToWash) {
        super(timeToWash);
    }

    public String toString(){
        return "ID:"+Thread.currentThread().getId() + " - "+ getClass().getName();
    }

}
