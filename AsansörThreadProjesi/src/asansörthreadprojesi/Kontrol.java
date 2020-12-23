/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asansörthreadprojesi;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ismail
 */
public class Kontrol implements Runnable {

    public static Asansor asansor1 = new Asansor(true, 0, "yukarı", "beklemede");
    Thread asansorThread1 = new Thread(asansor1);

    public static Asansor2 asansor2 = new Asansor2(false, 0, "yukarı", "beklemede");
    Thread asansorThread2 = new Thread(asansor2);

    public static Asansor3 asansor3 = new Asansor3(false, 0, "yukarı", "beklemede");
    Thread asansorThread3 = new Thread(asansor3);

    public static Asansor4 asansor4 = new Asansor4(false, 0, "yukarı", "beklemede");
    Thread asansorThread4 = new Thread(asansor4);

    public static Asansor5 asansor5 = new Asansor5(false, 0, "yukarı", "beklemede");
    Thread asansorThread5 = new Thread(asansor5);

    @Override
    public void run() {
        asansorThread1.start();
        asansorThread2.start();
        asansorThread3.start();
        asansorThread4.start();
        asansorThread5.start();
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (Exit.birinciKatKuyrukSayı + Exit.ikinciKatKuyrukSayı + Exit.üçüncüKatKuyrukSayı + Exit.dördüncüKatKuyrukSayı + Login.girişKuyrukSayı > 20) {
                asansor2.setAktiflik(true);
            } else if (Exit.birinciKatKuyrukSayı + Exit.ikinciKatKuyrukSayı + Exit.üçüncüKatKuyrukSayı + Exit.dördüncüKatKuyrukSayı + Login.girişKuyrukSayı <= 10) {
                asansor2.setAktiflik(false);
            }

            if (Exit.birinciKatKuyrukSayı + Exit.ikinciKatKuyrukSayı + Exit.üçüncüKatKuyrukSayı + Exit.dördüncüKatKuyrukSayı + Login.girişKuyrukSayı > 40) {
                asansor3.setAktiflik(true);
            } else if (Exit.birinciKatKuyrukSayı + Exit.ikinciKatKuyrukSayı + Exit.üçüncüKatKuyrukSayı + Exit.dördüncüKatKuyrukSayı + Login.girişKuyrukSayı <= 10) {
                asansor3.setAktiflik(false);
            }

            if (Exit.birinciKatKuyrukSayı + Exit.ikinciKatKuyrukSayı + Exit.üçüncüKatKuyrukSayı + Exit.dördüncüKatKuyrukSayı + Login.girişKuyrukSayı > 60) {
                asansor4.setAktiflik(true);
            } else if (Exit.birinciKatKuyrukSayı + Exit.ikinciKatKuyrukSayı + Exit.üçüncüKatKuyrukSayı + Exit.dördüncüKatKuyrukSayı + Login.girişKuyrukSayı <= 10) {
                asansor4.setAktiflik(false);
            }

            if (Exit.birinciKatKuyrukSayı + Exit.ikinciKatKuyrukSayı + Exit.üçüncüKatKuyrukSayı + Exit.dördüncüKatKuyrukSayı + Login.girişKuyrukSayı > 80) {
                asansor5.setAktiflik(true);
            } else if (Exit.birinciKatKuyrukSayı + Exit.ikinciKatKuyrukSayı + Exit.üçüncüKatKuyrukSayı + Exit.dördüncüKatKuyrukSayı + Login.girişKuyrukSayı <= 10) {
                asansor5.setAktiflik(false);
            }

        }

    }

}
