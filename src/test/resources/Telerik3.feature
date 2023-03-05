Feature: Kullanicinin sepete urun eklemesi ve silmesi sonucu sepet simgesi uzerindeki urun sayisinin
  degismesini gormeli

  Background:
    Given kullanici "https://www.telerik.com" web sayfasina gider

  @ikinci
  Scenario: Kullanici sepetteki iki urunden bir tanesini siler ve sepet simgesi uzerindeki sayinin bir eksildigini gorur
    When kullanici cookiesi kabul eder
    And kullanici Pricing linkine tiklar
    And kullanici BuyNow butonu ile sepete bir urun ekler
    And kullanici Pricing sayfasina geri doner
    And kullanici Buynow butonu ile bir urun daha ekler
    And kullanici ana sayfaya geri doner
    And kullanici sepete uzerindeki mevcut sayiyi alir
    And kullanici sepete tekrar gider
    And kullanici sepetteki urunlerden bir tanesini Delete linki ile siler
    And kullanici ana sayfaya geri doner
    Then kullanici sepet simgesi uzerindeki sayinin bir eksildigini kontrol eder