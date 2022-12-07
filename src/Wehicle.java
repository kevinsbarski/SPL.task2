public abstract class Wehicle implements Runnable{
    boolean isFinished;

    public Wehicle() {
        this.isFinished = false;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
