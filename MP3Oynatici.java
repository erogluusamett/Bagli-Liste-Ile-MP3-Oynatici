package mp3player;

import java.util.Random;
import java.util.Scanner;

class Sarki {
    String adi;
    String sanatci;
    String tur;
    int sure;
    Sarki sonraki;

    public Sarki(String adi, String sanatci, String tur, int sure) {
        this.adi = adi;
        this.sanatci = sanatci;
        this.tur = tur;
        this.sure = sure;
        this.sonraki = null;
    }
}

class MP3Player {
    private Sarki bas;

    public MP3Player() {
        this.bas = null;
    }

    public void sarkiEkle(String adi, String sanatci, String tur, int sure) {
        Sarki yeniSarki = new Sarki(adi, sanatci, tur, sure);

        if (bas == null || sanatci.compareTo(bas.sanatci) < 0) {
            yeniSarki.sonraki = bas;
            bas = yeniSarki;
        } else {
            Sarki temp = bas;
            while (temp.sonraki != null && sanatci.compareTo(temp.sonraki.sanatci) > 0) {
                temp = temp.sonraki;
            }
            yeniSarki.sonraki = temp.sonraki;
            temp.sonraki = yeniSarki;
        }
    }

    public void sarkiSil(String adi) {
        Sarki dummy = new Sarki("", "", "", 0);
        dummy.sonraki = bas;
        Sarki temp = dummy;
        
        while (temp.sonraki != null && !temp.sonraki.adi.equals(adi)) {
            temp = temp.sonraki;
        }

        if (temp.sonraki != null) {
            temp.sonraki = temp.sonraki.sonraki;
            bas = dummy.sonraki;  
        } else {
            System.out.println("Şarkı bulunamadı: " + adi);
        }
    }

    public void sarkilariListele() {
    System.out.println("---------- ŞARKILAR ----------");
    Sarki temp = bas;
    while (temp != null) {
        System.out.println("Şarkı Adı: " + temp.adi);
        System.out.println("Sanatçı: " + temp.sanatci);
        System.out.println("Tür: " + temp.tur);
        System.out.println("Süre: " + temp.sure + " sn\n");
        temp = temp.sonraki;
    }
    System.out.println("-------------------------------");
}


    public void siraliListele(int siralamaTuru) {
        Sarki siraliListe = null;

        switch (siralamaTuru) {
            case 1:
                siraliListe = siraliListele(bas, 1);
                System.out.println("---------- SANATÇIYA GÖRE SIRALI ŞARKILAR ----------");
                break;
            case 2:
                siraliListe = siraliListele(bas, 2);
                System.out.println("---------- TÜRE GÖRE SIRALI ŞARKILAR ----------");
                break;
            case 3:
                siraliListe = siraliListele(bas, 3);
                System.out.println("---------- SÜREYE GÖRE SIRALI ŞARKILAR ----------");
                break;
        }

        Sarki temp = siraliListe;
        while (temp != null) {
            System.out.println("Şarkı Adı: " + temp.adi);
            System.out.println("Sanatçı: " + temp.sanatci);
            System.out.println("Tür: " + temp.tur);
            System.out.println("Süre: " + temp.sure + " sn\n");
            temp = temp.sonraki;
        }
        System.out.println("---------------------------------------------");
    }

    private Sarki siraliListele(Sarki bas, int siralamaTuru) {
        Sarki sirali = null;
        Sarki temp = bas;

        while (temp != null) {
            Sarki yeniSarki = new Sarki(temp.adi, temp.sanatci, temp.tur, temp.sure);
            sirali = siraliEkle(sirali, yeniSarki, siralamaTuru);
            temp = temp.sonraki;
        }

        return sirali;
    }

    private Sarki siraliEkle(Sarki bas, Sarki yeniSarki, int siralamaTuru) {
        if (bas == null || karsilastir(bas, yeniSarki, siralamaTuru) > 0) {
            yeniSarki.sonraki = bas;
            return yeniSarki;
        } else {
            Sarki temp = bas;
            while (temp.sonraki != null && karsilastir(temp.sonraki, yeniSarki, siralamaTuru) < 0) {
                temp = temp.sonraki;
            }
            yeniSarki.sonraki = temp.sonraki;
            temp.sonraki = yeniSarki;
            return bas;
        }
    }

    private int karsilastir(Sarki sarki1, Sarki sarki2, int siralamaTuru) {
        switch (siralamaTuru) {
            case 1:
                return sarki1.sanatci.compareTo(sarki2.sanatci);
            case 2:
                return sarki1.tur.compareTo(sarki2.tur);
            case 3:
                return Integer.compare(sarki1.sure, sarki2.sure);
            default:
                return 0;
        }
    }

    public void rasgeleCal() {
        if (bas == null) {
            System.out.println("Çalınacak şarkı bulunamadı.");
            return;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(1000) % sarkiSayisi();

        Sarki temp = bas;
        for (int i = 0; i < randomIndex; i++) {
            temp = temp.sonraki;
        }

        System.out.println("Çalıyor: " + temp.adi);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Şarkı bitti.");
    }

    private int sarkiSayisi() {
        int count = 0;
        Sarki temp = bas;
        while (temp != null) {
            count++;
            temp = temp.sonraki;
        }
        return count;
    }
}

public class MP3Oynatici {
    public static void main(String[] args) {
        MP3Player mp3Player = new MP3Player();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MP3 Player Menu ---");
            System.out.println("1. Şarkı Ekle");
            System.out.println("2. Şarkı Sil");
            System.out.println("3. Şarkıları Listele");
            System.out.println("4. Şarkıları Sıralı Listele");
            System.out.println("5. Rasgele Şarkı Çal");
            System.out.println("0. Çıkış");

            System.out.print("Bir seçenek girin: ");
            int secim = scanner.nextInt();
            scanner.nextLine();  

            switch (secim) {
                case 1:
                    System.out.print("Şarkı Adı: ");
                    String adi = scanner.nextLine();
                    System.out.print("Sanatçı: ");
                    String sanatci = scanner.nextLine();
                    System.out.print("Tür: ");
                    String tur = scanner.nextLine();
                    System.out.print("Süre: ");
                    int sure = scanner.nextInt();
                    mp3Player.sarkiEkle(adi, sanatci, tur, sure);
                    System.out.println("Şarkı eklendi.");
                    break;
                case 2:
                    System.out.print("Silinecek Şarkı Adı: ");
                    String silinecekAdi = scanner.nextLine();
                    mp3Player.sarkiSil(silinecekAdi);
                    break;
                case 3:
                    mp3Player.sarkilariListele();
                    break;
                case 4:
                    System.out.println("\n--- Sıralama Seçenekleri ---");
                    System.out.println("1. Sanatçıya Göre Sıralı Listele");
                    System.out.println("2. Tür'e Göre Sıralı Listele");
                    System.out.println("3. Süre'ye Göre Sıralı Listele");
                    System.out.print("Bir sıralama türü seçin: ");
                    int siralamaTuru = scanner.nextInt();
                    mp3Player.siraliListele(siralamaTuru);
                    break;
                case 5:
                    mp3Player.rasgeleCal();
                    break;
                case 0:
                    System.out.println("Programdan çıkılıyor.");
                    System.exit(0);
                default:
                    System.out.println("Geçersiz seçenek! Tekrar deneyin.");
            }
        }
    }
}


