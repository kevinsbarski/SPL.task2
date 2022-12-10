import java.io.IOException;

public abstract class Wehicle implements Runnable {
    boolean isFinished;
    double timeToWash;
    Object lock;
    double washTime;

    public Wehicle(double timeToWash) {
        this.isFinished = false;
        this.timeToWash = timeToWash;
        lock = new Object();
        washTime = 0;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public Object getLock() {
        return lock;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    @Override
    public void run() {
        synchronized (lock) {
            try {
                arrival();
                lock.wait();
                wash();
                lock.wait();
                leave();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    Thread.currentThread().stop();

    }

    public void arrival() throws IOException {
        washTime = WehicleWasher.getSystemTime();
        WehicleLogger.writeToLog("Time since system started:" + washTime);
        System.out.println("Time since system started:" + washTime);
        WehicleLogger.writeToLog("Arrival of:");
        System.out.println("Arrival of:");
        WehicleLogger.writeToLog(this.toString());
        System.out.println(this);

    }

    public void wash() throws InterruptedException, IOException {
        double time = WehicleWasher.getSystemTime();
        WehicleLogger.writeToLog("Time since system started:" + time);
        WehicleLogger.writeToLog("Washing of:");
        WehicleLogger.writeToLog(this.toString());
        System.out.println("Time since system started:" + time);
        System.out.println("Washing of:");
        System.out.println(this);
        Thread.sleep((long) timeToWash);
        this.isFinished = true;

    }

    public void leave() throws IOException {
        double finishTime = WehicleWasher.getSystemTime();
        washTime = finishTime - washTime;
        WehicleLogger.writeToLog("Time since system started:" + washTime);
        WehicleLogger.writeToLog("Washing of:");
        WehicleLogger.writeToLog(this.toString());
        System.out.println("Time since system started:" + finishTime);
        System.out.println("departure of:");
        System.out.println(this);

    }
}
