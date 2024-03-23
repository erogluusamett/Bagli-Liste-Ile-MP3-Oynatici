**MP3 OYNATICI**

Bu proje, Java dilinde bağlı liste kullanarak bir MP3 oynatıcı uygulamasını içerir. Kullanıcı, şarkı ekleyebilir, şarkı silebilir, şarkıları listelebilir, sıralı olarak şarkıları listelebilir ve rasgele bir şarkı çalabilir.

**FONKSİYONLAR**

*1-Şarkı Ekle:* Kullanıcıdan alınan şarkı bilgilerini kullanarak yeni şarkıları oynatıcıya ekler.

*2-Şarkı Sil:* Kullanıcıdan alınan şarkı adını kullanarak bir şarkıyı oynatıcıdan siler.

*3-Şarkıları Listele:* Oynatıcıdaki tüm şarkıları listeler.

*4-Sıralı Şarkıları* Listele: Şarkıları sanatçıya göre, türe göre veya süreye göre sıralayarak listeler.

*5-Rasgele Şarkı Çal:* Oynatıcıdaki şarkılardan rasgele birini seçerek çalar.


**ÖRNEK KULLANIM**
```java

public class MP3Oynatici {
    public static void main(String[] args) {
        // MP3 oynatıcı oluşturulur
        MP3Player mp3Player = new MP3Player();
        Scanner scanner = new Scanner(System.in);

        // Kullanıcı ile etkileşim başlatılır
        // Kullanıcı, menü seçeneklerini kullanarak oynatıcıyı kontrol eder
    }
}
````

**KATKIDA BULUNMA**

Bu proje açık kaynaklıdır ve katkıda bulunmaya açıktır. Eğer bir hata bulursanız veya bir özellik eklemek isterseniz, lütfen bir pull isteği gönderin ya da bir issue açın.
