package CarWasherProject;
/*
Kevin Sbarski 324589480
Amit Sherman 209284017
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WehicleLogger {
    static Object lock = new Object();
    static FileWriter log;
    public synchronized  static void init() throws IOException {
        log = new FileWriter("log.txt");
    }
    public static void writeToLog(String msg) throws IOException {
        synchronized (lock) {
            log.write(msg+'\n');
        }

    }

    public static void close() throws IOException {
        log.close();
    }
}
