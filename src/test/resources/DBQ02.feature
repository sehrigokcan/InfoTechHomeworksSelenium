
Feature: Database Query

  Background:
    Given user connects to the database

    Scenario: Tablo olusturma ve kayit ekleme
      When employees isminde tablo olustur
      Then tabloya verileri ekle

  @dbQuery2
      Scenario: Sorgular
        When ID numarasinin sonu cift sayi olan satirlarin bilgilerini tablo olarak getirin
        Then ID numarasinin sonu cift sayi olan satirlarin bilgilerini tablo olarak getirin
        And Tablodaki toplam satir sayisini bulunuz
        And Tablodaki maksimum maasi bulunuz
        And Tablodaki ikinci maksimum maasi bulunuz
        And Tablodaki maksimum maasa sahip satirin tüm bilgilerini getiriniz
        And Tablodaki maksimum ikinci maasa sahip satirin tum bilgilerini getiriniz


  Scenario: baglantilari kapat
    And baglantilari kapat
