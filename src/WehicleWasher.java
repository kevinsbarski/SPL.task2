import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WehicleWasher {
    static ArrayList<Wehicle> waitingList = new ArrayList<Wehicle>();
    static ArrayList<Wehicle> gettingWashedList = new ArrayList<Wehicle>();
    static ArrayList<Wehicle> suvList = new ArrayList<Wehicle>();
    static ArrayList<Wehicle> truckList = new ArrayList<Wehicle>();
    static ArrayList<Wehicle> carList = new ArrayList<Wehicle>();
    static ArrayList<Wehicle> miniBusList = new ArrayList<Wehicle>();
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter number of washing stands:");
        int numberOfWashingStands = s.nextInt();
        System.out.println("Please enter number of vehicles to wash:");
        int numberOfCarsToWash = s.nextInt();
        System.out.println("Please enter average time between car arrivals:");
        float lambdaArrivale = s.nextFloat();
        System.out.println("Please enter average time between car washes:");
        float lambdaWash = s.nextFloat();
        try {
            generateVehicles(lambdaArrivale);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static double nextTime(float randomTime,float lambda){
        try{
            return (-1*(Math.log(randomTime)))/lambda;
        }
        catch(Exception e){
            return (-1*(Math.log(randomTime)))/1.5;
        }

    }
    public static void goToWehicleWasher(){

    }
    public static void generateVehicles(float lambdaArrivale) throws InterruptedException {
        boolean notFinished = true;
        Random r = new Random();
        while(notFinished){
            double nextTime = nextTime(r.nextFloat(1),lambdaArrivale);
//            Thread.sleep((long)nextTime*1000);
            Thread.sleep(1000);

            switch(r.nextInt(4)){
                case 0://Car
                    System.out.println("Car");
                    waitingList.add(new Car());
                    break;
                case 1://SUV
                    System.out.println("SUV");
                    waitingList.add(new SUV());
                    break;
                case 2://MiniBus
                    System.out.println("MiniBus");
                    waitingList.add(new MiniBus());
                    break;
                case 3://Truck
                    System.out.println("Truck");
                    waitingList.add(new Truck());
                    break;
                default:
                    System.out.println("should not be here");
                    break;
            }
//            notFinished = false;
        }
    }
}
