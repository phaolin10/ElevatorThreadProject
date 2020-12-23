package asansörthreadprojesi;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ismail
 */
public class Asansor implements Runnable {

    private final int kapasite = 10;
    public static int alınanYolcu;
    private boolean aktiflik;
    private String mod;
    private int kat;
    private int hedef;
    private String yön;
    private static ConcurrentLinkedQueue<ConcurrentHashMap<Integer, Integer>> asansorListe = new ConcurrentLinkedQueue<>();
    private static ConcurrentLinkedQueue<ConcurrentHashMap<Integer, Integer>> tmp = new ConcurrentLinkedQueue<>();

    private int birinciKatİnecek;
    private int ikinciKatİnecek;
    private int üçüncüKatİnecek;
    private int dördüncüKatİnecek;

    public static ConcurrentLinkedQueue<Integer> asansörKat = new ConcurrentLinkedQueue<>();

    public synchronized static ConcurrentLinkedQueue<ConcurrentHashMap<Integer, Integer>> getAsansorListe() {
        return asansorListe;
    }

    public synchronized static void setAsansorListe(ConcurrentLinkedQueue<ConcurrentHashMap<Integer, Integer>> asansorListe) {
        Asansor.asansorListe = asansorListe;
    }

    public Asansor(boolean aktiflik, int kat, String yön, String mod) {
        this.aktiflik = aktiflik;
        this.kat = kat;
        this.yön = yön;
        this.mod = mod;
    }

    public boolean isAktiflik() {
        return aktiflik;
    }

    public void setAktiflik(boolean aktiflik) {
        this.aktiflik = aktiflik;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public int getKat() {
        return kat;
    }

    public void setKat(int kat) {
        this.kat = kat;
    }

    public int getHedef() {
        return hedef;
    }

    public void setHedef(int hedef) {
        this.hedef = hedef;
    }

    public String getYön() {
        return yön;
    }

    public void setYön(String yön) {
        this.yön = yön;
    }

    @Override
    public synchronized void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.aktiflik == true) { // önce asansörlerin aktiflik durumuna bak.aktifse mod durumuna bak. çalışıyorsa yön durumuna bak.yukarı gidiyorsa 1. kattaki kuyruktan 
                // yolcuları almaya başla. aşağı gidiyorsa 4. kattan yolcuları almaya başla.

                if (this.yön == "yukarı") {// yukarı yöne gidecekken burası çalışacak.
                    if (this.mod == "beklemede") { // yolcu al

                        while (alınanYolcu <= kapasite) {

                            if (Login.girenDizi.isEmpty() == false) {
                                if (alınanYolcu + Login.girenDizi.element() <= kapasite) {

                                    asansorListe.offer(Login.girişYapan.poll());
                                    /*   tmp = Login.getGirişYapan();
                                    Login.setGirişYapan(tmp);*/
                                    Login.girişKuyrukSayı -= Login.girenDizi.element();
                                    alınanYolcu += Login.girenDizi.poll();
                                    if (Login.girenKat.element() == 1) {
                                        birinciKatİnecek += Login.inenDizi.remove();
                                    }
                                    if (Login.girenKat.element() == 2) {
                                        ikinciKatİnecek += Login.inenDizi.remove();
                                    }
                                    if (Login.girenKat.element() == 3) {
                                        üçüncüKatİnecek += Login.inenDizi.remove();
                                    }
                                    if (Login.girenKat.element() == 4) {
                                        dördüncüKatİnecek += Login.inenDizi.remove();
                                    }

                                    if (Login.girenKat.element() > this.hedef) {

                                        this.hedef = Login.girenKat.element();
                                    }
                                    Login.girenKat.poll();

                                    System.out.println("bir" + birinciKatİnecek + " iki" + ikinciKatİnecek + " üç" + üçüncüKatİnecek + " dört" + dördüncüKatİnecek);

                                } else {
                                    System.out.println("çalışmaya geçti");
                                    this.mod = "çalışıyor";
                                    break;

                                }

                            }

                        }

                    } else { // asansörün çalıştığı kısım.
                        System.out.println("burda");
                        for (int i = 1; i <= this.hedef; i++) {

                            switch (i) {
                                case 1: {
                                    Exit.birinciKatSayı += birinciKatİnecek;
                                    birinciKatİnecek = 0;
                                    this.kat = i;
                                }
                                case 2: {
                                    Exit.ikinciKatSayı += ikinciKatİnecek;
                                    ikinciKatİnecek = 0;
                                    this.kat = i;
                                }
                                case 3: {
                                    Exit.üçüncüKatSayı += üçüncüKatİnecek;
                                    üçüncüKatİnecek = 0;
                                    this.kat = i;
                                }
                                case 4: {
                                    Exit.dördüncüKatSayı += dördüncüKatİnecek;
                                    dördüncüKatİnecek = 0;
                                    this.kat = i;
                                }
                            }
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Asansor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            asansorListe.clear();
                            this.alınanYolcu = 0;
                            this.kat = this.hedef;
                            this.mod = "beklemede";
                            this.yön = "aşağı";
                            this.hedef = 0;
                        }
                    }
                } else { // aşağı yöndeyken burası çalışacak
                    if (this.mod == "beklemede") {

                        switch (this.kat) {
                            case 1: {
                                while (alınanYolcu <= kapasite) {
                                    if (Exit.inenDiziBir.isEmpty() == false) {
                                        if (alınanYolcu + Exit.inenDiziBir.element() <= kapasite) {
                                            asansorListe.offer(Exit.inenBirinciKatKuyruk.poll());
                                            alınanYolcu += Exit.inenDiziBir.element();
                                            Exit.birinciKatKuyrukSayı -= Exit.inenDiziBir.poll();
                                            if (Exit.inenBirinciKatKuyruk.isEmpty() == true) {
                                                this.mod = "çalışıyor";
                                                break;
                                            }

                                        } else {
                                            this.mod = "çalışıyor";
                                            break;
                                        }

                                    } else {
                                        this.mod = "çalışıyor";
                                        break;
                                    }

                                }
                            }
                            case 2: {
                                while (alınanYolcu <= kapasite) {
                                    if (Exit.inenDiziİki.isEmpty() == false) {
                                        if (alınanYolcu + Exit.inenDiziİki.element() <= kapasite) {
                                            asansorListe.offer(Exit.inenİkinciKatKuyruk.poll());
                                            alınanYolcu += Exit.inenDiziİki.element();
                                            Exit.ikinciKatKuyrukSayı -= Exit.inenDiziİki.poll();
                                            if (Exit.inenİkinciKatKuyruk.isEmpty() == true) {
                                                this.mod = "çalışıyor";
                                                break;
                                            }

                                        } else {
                                            this.mod = "çalışıyor";
                                            break;
                                        }

                                    } else {
                                        this.mod = "çalışıyor";
                                        break;
                                    }
                                }

                            }
                            case 3: {
                                while (alınanYolcu <= kapasite) {
                                    if (Exit.inenDiziÜç.isEmpty() == false) {
                                        if (alınanYolcu + Exit.inenDiziÜç.element() <= kapasite) {
                                            asansorListe.offer(Exit.inenÜçüncüKatKuyruk.poll());
                                            alınanYolcu += Exit.inenDiziÜç.element();
                                            Exit.üçüncüKatKuyrukSayı -= Exit.inenDiziÜç.poll();
                                            if (Exit.inenÜçüncüKatKuyruk.isEmpty() == true) {
                                                this.mod = "çalışıyor";
                                                break;
                                            }

                                        } else {
                                            this.mod = "çalışıyor";
                                            break;
                                        }

                                    } else {
                                        this.mod = "çalışıyor";
                                        break;
                                    }
                                }

                            }
                            case 4: {
                                while (alınanYolcu <= kapasite) {
                                    if (Exit.inenDiziDört.isEmpty() == false) {
                                        if (alınanYolcu + Exit.inenDiziDört.element() <= kapasite) {
                                            asansorListe.offer(Exit.inenDördüncüKatKuyruk.poll());
                                            alınanYolcu += Exit.inenDiziDört.element();
                                            Exit.dördüncüKatKuyrukSayı -= Exit.inenDiziDört.poll();
                                            if (Exit.inenDördüncüKatKuyruk.isEmpty() == true) {
                                                this.mod = "çalışıyor";
                                                break;
                                            }

                                        } else {
                                            this.mod = "çalışıyor";
                                            break;
                                        }

                                    } else {
                                        this.mod = "çalışıyor";
                                        break;
                                    }
                                }

                            }
                        }

                    } else { // asansörün aşağı yönde çalıştığı kısım
                        System.out.println("girdi");
                        System.out.println("bu katta" + this.kat);
                        for (int i = this.kat; i >= 0; i--) {
                            if (i == 0) {
                                asansorListe.clear();
                                this.yön = "yukarı";
                                this.mod = "beklemede";
                                this.hedef = 0;
                                alınanYolcu = 0;
                                this.kat = 0;

                            }

                            if (i == 1) {
                                this.kat = 1;
                            }
                            if (i == 2) {
                                this.kat = 2;
                            }
                            if (i == 3) {
                                this.kat = 3;
                            }

                        }
                    }
                }

            }

        }

    }

}
