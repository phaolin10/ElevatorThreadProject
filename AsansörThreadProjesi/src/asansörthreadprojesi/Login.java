package asansörthreadprojesi;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ismail
 */
public class Login implements Runnable {

    private Random random = new Random();
    public static String girişKuyrukText;
    public static ConcurrentLinkedQueue<Integer> girenDizi = new ConcurrentLinkedQueue<>();
    public static ConcurrentLinkedQueue<Integer> girenKat = new ConcurrentLinkedQueue<>();
    public static ConcurrentLinkedQueue<Integer> inenDizi = new ConcurrentLinkedQueue<>();
    
    public static ConcurrentLinkedQueue<ConcurrentHashMap<Integer, Integer>> girişYapan = new ConcurrentLinkedQueue<>();

    public synchronized static ConcurrentLinkedQueue<ConcurrentHashMap<Integer, Integer>> getGirişYapan() {
        return girişYapan;
    }

    public synchronized static void setGirişYapan(ConcurrentLinkedQueue<ConcurrentHashMap<Integer, Integer>> girişYapan) {
        Login.girişYapan = girişYapan;
    }
    public static int girişKuyrukSayı;

    @Override
    public synchronized void run() { 
        
             while (true) {
            int girişSayı = random.nextInt(10) + 1;
            int kat = random.nextInt(4) + 1;
            girişKuyrukSayı += girişSayı;
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<>();
            concurrentHashMap.put(girişSayı, kat);
            girişYapan.add(concurrentHashMap);
            girenDizi.add(girişSayı);
            girenKat.add(kat);
            inenDizi.add(girişSayı);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        
       
    }

}
