
Feature: Database Query

  Background:
    Given user connects to the database

  @dbQuery
    Scenario: Toplam ürün sayısını ASSERT edin
      Then toplam urun sayisini assert edin


      Scenario: Fiyatı en yüksek ürün ASSERT edin.
        Then en yuksek fiyatli urunu assert edin

      Scenario: Urün isimlerinden birini değiştirin
        And urun isimlerinin birini degistir

      Scenario: Urünlerin fiyat ortalamasını bulup yazdırın
        And urun isimlerinin fiyat ortalamasini bul


    Scenario: Urünlerin tablo olarak yazdırırken tablodaki sütunların başlıklarını farklı yazdırın.
      And sutun basliklarini degistirip tabloyu yazdir

      Scenario: Urun isimlerini hepsi büyük harf olacak şekilde yazdırın.
        And urun isimlerini buyuk harfle yazdir


      Scenario: Urün isimlerini hepsi küçük harf olacak şekilde yazdırın.
        And urun isimlerini kucuk harfle yazdir

      Scenario: Ürün isimlerinin birinci harfi büyük olacak şekilde yazdırın.
        And urun isimlerinin ilk harfi buyuk olacak sekilde yazdir

      Scenario: Belli bir fiyatın altındaki ürünleri isime ve fiyata göre azalan nitelikte sıralayın
        And belli fiyatin altindaki urunleri isme ve fiyata gore azalan nitelikte sirala


