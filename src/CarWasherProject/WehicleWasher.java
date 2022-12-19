package CarWasherProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WehicleWasher {
    //Static fields
    static ArrayList<Wehicle> waitingList = new ArrayList<>();
    static ArrayList<Wehicle> suvList = new ArrayList<>();
    static ArrayList<Wehicle> truckList = new ArrayList<>();
    static ArrayList<Wehicle> carList = new ArrayList<>();
    static ArrayList<Wehicle> miniBusList = new ArrayList<>();
    static Object lock = new Object();
    static long startTime = System.currentTimeMillis();
    //Helper functions
    public static synchronized int getNumOfTotalVehicleWashed(){
        return carList.size() + truckList.size() + suvList.size() + miniBusList.size();
    }

    public static void insertDoneVehicle(Wehicle v){
        if(v instanceof Car) {
            carList.add(v);
        }
        if(v instanceof SUV) {
            suvList.add(v);
        }
        if(v instanceof Truck) {
            truckList.add(v);
        }
        if(v instanceof MiniBus) {
            miniBusList.add(v);
        }
    }

    public static synchronized long getSystemTime(){
        return System.currentTimeMillis() - startTime;
    }

    public static double nextTime(float randomTime,float lambda){
        try{
            return (-1*(Math.log(randomTime)))/lambda;
        }
        catch(Exception e){
            return (-1*(Math.log(randomTime)))/1.5;
        }

    }

    public static void generateVehicles(float lambdaArrival,float lambdaWash) throws InterruptedException {

        Random r = new Random();
        double nextNewCar = nextTime(r.nextFloat(1),lambdaArrival);
        double timeToWash = nextTime(r.nextFloat(1),lambdaWash);
        Thread.sleep((long)nextNewCar);
//        Thread.sleep(1000);
        switch(r.nextInt(4)){
//        switch(1){
            case 0://CarWasherProject.Car
                waitingList.add(new Car(timeToWash));
                Thread t1 = new Thread(waitingList.get(waitingList.size()-1));
                t1.start();
                break;
            case 1://CarWasherProject.SUV
                waitingList.add(new SUV(timeToWash));
                Thread t2 = new Thread(waitingList.get(waitingList.size()-1));
                t2.start();
                break;
            case 2://CarWasherProject.MiniBus
                waitingList.add(new MiniBus(timeToWash));
                Thread t3 = new Thread(waitingList.get(waitingList.size()-1));
                t3.start();
                break;
            case 3://CarWasherProject.Truck
                waitingList.add(new Truck(timeToWash));
                Thread t4 = new Thread(waitingList.get(waitingList.size()-1));
                t4.start();
                break;
            default:
                System.out.println("should not be here");
                break;
        }
    }

    //TicTacToeProject.Main
    public static void main(String[] args) throws IOException {
        WehicleLogger.init();
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter number of washing stands:");
        int numberOfWashingStands = s.nextInt();
        System.out.println("Please enter number of vehicles to wash:");
        int numberOfCarsToWash = s.nextInt();
        System.out.println("Please enter average time between car arrivals:");
        float lambdaArrival = s.nextFloat();
        System.out.println("Please enter average time between car washes:");
        float lambdaWash = s.nextFloat();
        Wehicle[] washingStandsArr = new Wehicle[numberOfWashingStands];
        int numOfGen = 0;
        try {
            while(getNumOfTotalVehicleWashed() < numberOfCarsToWash) {

                generateVehicles(lambdaArrival, lambdaWash);

                Thread.sleep(1000);
                for (int i = 0; i < numberOfWashingStands; i++) {
                    //if queue not empty we dequeue from waiting list and insert into washing stand, and we notify the thread that
                    // he can proceed into the wash phase
                    if (waitingList.size() > 0) {

                        if (washingStandsArr[i] == null) {
                            washingStandsArr[i] = waitingList.remove(0);
                            synchronized (washingStandsArr[i].getLock()) {
                                washingStandsArr[i].getLock().notifyAll();
                            }

                        } else if (washingStandsArr[i].isFinished) {
                            insertDoneVehicle(washingStandsArr[i]);
                            synchronized (washingStandsArr[i].getLock()) {
                                washingStandsArr[i].getLock().notifyAll();
                            }
                            washingStandsArr[i] = null;
                        }
                    }
                }
            }
            double carWashAvg = 0;
            double suvWashAvg = 0;
            double busWashAvg = 0;
            double truckWashAvg = 0;
            for(Wehicle c : carList){
                carWashAvg += c.washTime;
            }
            for(Wehicle c : suvList){
                suvWashAvg += c.washTime;
            }
            for(Wehicle c : miniBusList){
                busWashAvg += c.washTime;
            }
            for(Wehicle c : truckList){
                truckWashAvg += c.washTime;
            }
            System.out.println("Waiting for log file writings to be done");
            Thread.sleep(5000);
            System.out.println(getNumOfTotalVehicleWashed());
            System.out.println("CarWasherProject.Car average wash time in sec:"+carWashAvg/carList.size()/1000);
            System.out.println("CarWasherProject.SUV average wash time in sec:"+suvWashAvg/suvList.size()/1000);
            System.out.println("CarWasherProject.Truck average wash time in sec:"+truckWashAvg/truckList.size()/1000);
            System.out.println("Minibus average wash time in sec:"+busWashAvg/miniBusList.size()/1000);
            WehicleLogger.close();
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
