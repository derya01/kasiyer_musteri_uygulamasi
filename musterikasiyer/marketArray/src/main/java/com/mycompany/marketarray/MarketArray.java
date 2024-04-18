/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.marketarray;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

class Queue {

    int len; // Kuyruğun boyutu
    int fore; // Dizinin öndeki imleci
    int back; // Dizinin arka imleci
    int dizi[]; // Elemanların eklendiği dizi
    int elemanSayisi;

    public Queue(int N) {
        this.len = N;
        this.dizi = new int[this.len];
        this.fore = -1;
        this.back = 0;

    }

    public void put(int nesne) { // Kuyruğa ekleme
        if (!dolumu()) {
            this.fore = (this.fore + 1) % this.len;
            this.dizi[this.fore] = nesne;
            elemanSayisi++;
        } else {
            System.out.println("Kuyruk dolu");
        }
    }

    public int pop() { // Kuyruktan çıkarma
        if (!bosmu()) {
            int temp = this.dizi[this.back];
            this.back = (this.back + 1) % this.len;
            elemanSayisi--;
            return temp;

        } else {
            System.out.println("Kuyruk boş");
            return -1;
        }
    }

    public boolean bosmu() {
        if (elemanSayisi == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean dolumu() {
        if (elemanSayisi == this.len) {
            return true;
        } else {
            return false;
        }
    }

}

class Product {

    String name;
    int barcode;

    public Product(String name, int barcode) {
        this.name = name;
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return "Barkod: " + barcode + ", Ürün Adı: " + name;
    }
}

class Customer {

    int customerid;

    public int random() {
        Random rand = new Random();
        int numCustomers = rand.nextInt(11);
        return numCustomers;
    }

}

public class MarketArray {

    static Product[] foodProducts = new Product[20];
    static Product[] hygieneProducts = new Product[20];
    static Product[] goods = new Product[20];
    static Stack<Integer> sepet = new Stack<Integer>();
    static Scanner scanner = new Scanner(System.in);
    static int sepeteklead, sepetcikarad, sayacfood = 0, sayachy = 0, sayacgoods = 0;
    static Queue customerque = new Queue(10);
    static Customer musteri = new Customer();

    //---------------------------------------------------------------
    public static void kuyrugamusterieklevecikar() {
        while (true) {
            int numCustomers = musteri.random();
            for (int i = 0; i < numCustomers; i++) {
                customerque.put(i);
                System.out.println("Müşteri sıraya girdi");
            }
            kuyruktancikar();

            try {
                Thread.sleep(3000);
                System.out.println("devam etmek için d ye basın");
                String kontrol = scanner.nextLine();
                if (!kontrol.equals("d")) {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //---------------------------------------------------------------
    public static void kuyruktancikar() {
        try {
            Thread.sleep(1000);
            customerque.pop();
            System.out.println("müsteri sıradan çıktı");
        } catch (InterruptedException ex) {
            Logger.getLogger(MarketArray.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //---------------------------------------------------------------
    public static void sepeteekle(Stack<Integer> sepet) {
        System.out.println("eklemek istediğiniz ürün numarası: ");
        sepeteklead = scanner.nextInt();
        sepet.add(sepeteklead);
    }

    //---------------------------------------------------------------
    public static void sepettencikar(Stack<Integer> sepet) {
        System.out.println("listeniz:");
        for (int i = 0; i < sepet.size(); i++) {
            System.out.println(sepet.get(i));
        }
        System.out.println("cikarmak istediğiniz ürün sırası: ");
        if (sepet.isEmpty()) {
            System.out.println("sepetiniz boş");
        } else {
            sepetcikarad = scanner.nextInt();
            sepet.removeElementAt(sepetcikarad - 1);
        }

    }

    //---------------------------------------------------------------
    public static void urunekle(int category) {
        System.out.print("Eklemek istediğiniz ürünün adını girin: ");
        String name = scanner.nextLine();
        int barcode = 0;
        switch (category) {
            case 1:
                foodProducts[sayacfood] = new Product(name, sayacfood);
                sayacfood++;
                break;
            case 2:

                hygieneProducts[sayachy] = new Product(name, sayachy);
                sayachy++;
                break;
            case 3:
                goods[sayacgoods] = new Product(name, sayacgoods);
                sayacgoods++;
                break;
            default:
                System.out.println("Geçersiz kategori numarası.");
                break;
        }
        System.out.println("Ürün başarıyla eklendi.");
    }

    //---------------------------------------------------------------
    public static void urunsil(int category) {
        Product[] selectedCategory = null;
        switch (category) {
            case 1:
                selectedCategory = foodProducts;
                break;
            case 2:
                selectedCategory = hygieneProducts;
                break;
            case 3:
                selectedCategory = goods;
                break;
            default:
                System.out.println("Geçersiz kategori numarası.");
                return;
        }

        System.out.print("Çıkarmak istediğiniz ürünün barkod numarasını girin: ");
        int barcode = scanner.nextInt();
        scanner.nextLine();

        int indexToRemove = -1;
        for (int i = 0; i < selectedCategory.length; i++) {
            if (selectedCategory[i].barcode == barcode) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove != -1) {
            selectedCategory[indexToRemove] = null;
            System.out.println("Ürün başarıyla çıkarıldı.");
        } else {
            System.out.println("Belirtilen barkod numarasına sahip bir ürün bulunamadı.");
        }
    }

    //---------------------------------------------------------------
    public static void urunguncelle(int category, String urunadi, int index) {
        int inex = index - 1;
        switch (category) {
            case 1:
                foodProducts[inex] = new Product(urunadi, inex);
                break;
            case 2:
                hygieneProducts[inex] = new Product(urunadi, inex);
                break;
            case 3:
                goods[inex] = new Product(urunadi, inex);
                break;
            default:
                System.out.println("Geçersiz kategori numarası.");
                return;
        }

    }

    //---------------------------------------------------------------
    public static void urunlerigoster() {
        System.out.println("Besin Ürünleri:");
        for (Product product : foodProducts) {
            int sayac = 0;
            if (product == null) {
                System.out.print("");
            } else {
                System.out.println(sayac + " - " + product);
            }
            
        }

        System.out.println("\nTemizlik ve Hijyen Ürünleri:");
        for (Product product : hygieneProducts) {
            int sayac = foodProducts.length + 1;
            if (product == null) {
                System.out.print("");
            } else {
                System.out.println(sayac + " - " + product);
            }
        }

        System.out.println("\nEşya Ürünleri:");
        for (Product product : goods) {
            int sayac = foodProducts.length + hygieneProducts.length + 1;
            if (product == null) {
                System.out.print("");
            } else {
                System.out.println(sayac + " - " + product);
            }
        }
    }

    //---------------------------------------------------------------
    public static void main(String[] args) {

        System.out.println("müşteri iseniz 1 kasa görevlisi iseniz 2 ye basın");
        int rolkontrol = scanner.nextInt();
        if (rolkontrol == 1) {
            boolean kont = true;
            while (kont) {
                System.out.println("sepetinize ürün eklemek için 1 e ürün çıkarmak için 2 ye basın çıkış için 0 a basın");
                int sec = scanner.nextInt();
                if (sec == 0) {
                    System.out.println("çıkış yapılıyor");
                    System.exit(sec);
                } else {
                    switch (sec) {
                        case 1:
                            sepeteekle(sepet);
                            break;
                        case 2:
                            sepettencikar(sepet);
                            break;
                        default:
                            System.out.println("yanlış karakter");
                            break;
                    }
                }

            }
        } else if (rolkontrol == 2) {

            while (true) {
                System.out.println("\nHangi işlemi yapmak istersiniz? Çıkmak için 0'a basın:");
                System.out.println("1 - Ürün Ekleme\n2 - Ürün Çıkarma\n3 - Ürün Güncelleme\n4 - Müşteri ve kasa işlemleri\n5 - ürünleri listeleme");
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 0) {
                    break;
                }
                switch (choice) {
                    case 1:
                        System.out.println("Eklemek istediğiniz kategoriyi seçin:");
                        System.out.println("1 - Besin\n2 - Temizlik ve Hijyen\n3 - Eşyalar");
                        int kategori = scanner.nextInt();
                        scanner.nextLine();
                        urunekle(kategori);
                        break;
                    case 2:
                        System.out.println("listeniz:");
                        urunlerigoster();
                        System.out.println("Çıkarmak istediğiniz ürünün barkod numarası:");
                        System.out.println("1 - Besin\n2 - Temizlik ve Hijyen\n3 - Eşyalar");
                        int silinen = scanner.nextInt();
                        scanner.nextLine();
                        urunsil(silinen);
                        break;
                    case 3:
                        System.out.println("listeniz:");
                        urunlerigoster();
                        System.out.println("Güncellemek istediğiniz kategoriyi seçin:");
                        System.out.println("1 - Besin\n2 - Temizlik ve Hijyen\n3 - Eşyalar");
                        int guncellenen = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("urununuzun sirasi: ");
                        int index = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("eklemek istediğiniz urunun adi: ");
                        String urunadi = scanner.nextLine();
                        urunguncelle(guncellenen, urunadi, index);
                        break;
                    case 4:
                        kuyrugamusterieklevecikar();
                        break;
                    case 5:
                        urunlerigoster();
                        break;
                    default:
                        System.out.println("Geçersiz işlem numarası.");
                        break;
                }
            }
        } else {
            System.out.println("yanlış tuş");
        }
        System.out.println("Programdan çıkılıyor...");
    }
}
