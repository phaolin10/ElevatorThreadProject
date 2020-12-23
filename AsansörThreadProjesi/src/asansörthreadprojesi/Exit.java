/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asansörthreadprojesi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ismail
 */
public class Exit implements Runnable {

    /*  public static ConcurrentLinkedQueue<Integer> inenBirinciKatKuyruk = new ConcurrentLinkedQueue<>();
    public static ConcurrentLinkedQueue<Integer> inenİkinciKatKuyruk = new ConcurrentLinkedQueue<>();
    public static ConcurrentLinkedQueue<Integer> inenÜçüncüKatKuyruk = new ConcurrentLinkedQueue<>();
    public static ConcurrentLinkedQueue<Integer> inenDördüncüKatKuyruk = new ConcurrentLinkedQueue<>();
     */
    public static ConcurrentLinkedQueue<Integer> inenDiziBir = new ConcurrentLinkedQueue<>();
    public static ConcurrentLinkedQueue<Integer> inenDiziİki = new ConcurrentLinkedQueue<>();
    public static ConcurrentLinkedQueue<Integer> inenDiziÜç = new ConcurrentLinkedQueue<>();
    public static ConcurrentLinkedQueue<Integer> inenDiziDört = new ConcurrentLinkedQueue<>();
    
    public static ConcurrentLinkedQueue<ConcurrentHashMap<Integer, Integer>> inenBirinciKatKuyruk = new ConcurrentLinkedQueue<>();
    public static ConcurrentLinkedQueue<ConcurrentHashMap<Integer, Integer>> inenİkinciKatKuyruk = new ConcurrentLinkedQueue<>();
    public static ConcurrentLinkedQueue<ConcurrentHashMap<Integer, Integer>> inenÜçüncüKatKuyruk = new ConcurrentLinkedQueue<>();
    public static ConcurrentLinkedQueue<ConcurrentHashMap<Integer, Integer>> inenDördüncüKatKuyruk = new ConcurrentLinkedQueue<>();

    public static int birinciKatSayı;
    public static int ikinciKatSayı;
    public static int üçüncüKatSayı;
    public static int dördüncüKatSayı;

    public static int birinciKatKuyrukSayı;
    public static int ikinciKatKuyrukSayı;
    public static int üçüncüKatKuyrukSayı;
    public static int dördüncüKatKuyrukSayı;

    private Random random = new Random();

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Exit.class.getName()).log(Level.SEVERE, null, ex);
            }
            int randomSayi = random.nextInt(4) + 1;
            int kat = random.nextInt(5) + 1;
            switch (kat) {

                case 1: {
                    if (birinciKatSayı > 0) {
                        if (birinciKatSayı < 5) {
                            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<>();
                            concurrentHashMap.put(birinciKatSayı, 0);
                            inenBirinciKatKuyruk.add(concurrentHashMap);
                            inenDiziBir.add(birinciKatSayı);
                            birinciKatKuyrukSayı += birinciKatSayı;
                            birinciKatSayı -= birinciKatSayı;
                        } else if (birinciKatSayı >= 5) {
                            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<>();
                            concurrentHashMap.put(randomSayi, 0);
                            inenBirinciKatKuyruk.add(concurrentHashMap);
                            inenDiziBir.add(randomSayi);
                            birinciKatKuyrukSayı += randomSayi;
                            birinciKatSayı -= randomSayi;
                        }
                        System.out.println("1den çıkan" + inenBirinciKatKuyruk);

                    }

                }
                case 2: {
                    if (ikinciKatSayı > 0) {
                        if (ikinciKatSayı < 5) {
                            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<>();
                            concurrentHashMap.put(ikinciKatSayı, 0);
                            inenİkinciKatKuyruk.add(concurrentHashMap);
                            inenDiziİki.add(ikinciKatSayı);
                            ikinciKatKuyrukSayı += ikinciKatSayı;
                            ikinciKatSayı -= ikinciKatSayı;
                        } else if (ikinciKatSayı >= 5) {
                            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<>();
                            concurrentHashMap.put(randomSayi, 0);
                            inenİkinciKatKuyruk.add(concurrentHashMap);
                            inenDiziİki.add(randomSayi);
                            ikinciKatKuyrukSayı += randomSayi;
                            ikinciKatSayı -= randomSayi;
                        }
                        System.out.println("2den çıakn" + inenİkinciKatKuyruk);

                    }

                }
                case 3: {
                    if (üçüncüKatSayı > 0) {
                        if (üçüncüKatSayı < 5) {
                            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<>();
                            concurrentHashMap.put(üçüncüKatSayı, 0);
                            inenÜçüncüKatKuyruk.add(concurrentHashMap);
                             inenDiziÜç.add(üçüncüKatSayı);
                            üçüncüKatKuyrukSayı += üçüncüKatSayı;
                            üçüncüKatSayı -= üçüncüKatSayı;
                        } else if (üçüncüKatSayı >= 5) {
                            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<>();
                            concurrentHashMap.put(randomSayi, 0);
                            inenÜçüncüKatKuyruk.add(concurrentHashMap);
                             inenDiziÜç.add(randomSayi);
                            üçüncüKatKuyrukSayı += randomSayi;
                            üçüncüKatSayı -= randomSayi;
                        }
                        System.out.println("3den çıkan" + inenÜçüncüKatKuyruk);

                    }

                }
                case 4: {
                    if (dördüncüKatSayı > 0) {
                        if (dördüncüKatSayı < 5) {
                            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<>();
                            concurrentHashMap.put(dördüncüKatSayı, 0);
                            inenDördüncüKatKuyruk.add(concurrentHashMap);
                            inenDiziDört.add(dördüncüKatSayı);
                            dördüncüKatKuyrukSayı += dördüncüKatSayı;
                            dördüncüKatSayı -= dördüncüKatSayı;
                        } else if (dördüncüKatSayı >= 5) {
                            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<>();
                            concurrentHashMap.put(randomSayi, 0);
                            inenDördüncüKatKuyruk.add(concurrentHashMap);
                            inenDiziDört.add(randomSayi);
                            dördüncüKatKuyrukSayı += randomSayi;
                            dördüncüKatSayı -= randomSayi;
                        }
                        System.out.println("4den çıkan" + inenDördüncüKatKuyruk);

                    }

                }
            }
        }

    }

}
