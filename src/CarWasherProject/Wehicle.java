/*
Kevin Sbarski 324589480
Amit Sherman 209284017
 */
package CarWasherProject;

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
                System.out.println("GoodBye ");
//                e.printStackTrace();
            }
        }
    }

    public void arrival() throws IOException {
        washTime = WehicleWasher.getSystemTime();
        WehicleLogger.writeToLog("Time since system started:" + washTime/1000+'\n'+"Arrival of:\n"+this.toString());
//        CarWasherProject.WehicleLogger.writeToLog("Time since system started:" + washTime);
        System.out.println("Time since system started:" + washTime/1000);
//        CarWasherProject.WehicleLogger.writeToLog("Arrival of:");
        System.out.println("Arrival of:");
//        CarWasherProject.WehicleLogger.writeToLog(this.toString());
        System.out.println(this);

    }

    public void wash() throws InterruptedException, IOException {
        double time = WehicleWasher.getSystemTime();
        WehicleLogger.writeToLog("Time since system started:" + time/1000+'\n'+"Washing of:\n"+this.toString());
//        CarWasherProject.WehicleLogger.writeToLog("Washing of:");
//        CarWasherProject.WehicleLogger.writeToLog(this.toString());
        System.out.println("Time since system started:" + time/1000);
        System.out.println("Washing of:");
        System.out.println(this);
        Thread.sleep((long) timeToWash);
        this.isFinished = true;

    }

    public void leave() throws IOException {
        double finishTime = WehicleWasher.getSystemTime();
        washTime = finishTime - washTime;
        WehicleLogger.writeToLog("Time since system started:" + washTime/1000+'\n'+"departure of:\n"+this.toString());
        System.out.println("Time since system started:" + finishTime/1000);
        System.out.println("departure of:");
        System.out.println(this);

    }
}
