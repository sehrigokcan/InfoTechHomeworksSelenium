Feature: Kullanici ebay sitesinden istenilen urunu ve ozelligine gore arama yapabilir

  @ebay
  Scenario: Kullanici computer aratip Windows 10 isletim sistemi secer secime uygun sonuclari gorur
    Given Kullanici ebay sitesine gider
    When Kullanici Cookies secenegini Accept All secenegi ile kabul eder
    And Kullanici SearchBox kutusuna computer kelimesini yazar
    And Kullanici arama butonuna basar
    And Kullanici filtre bolmesinden Windows10 secenegine tiklar
    Then Cikan seceneklerden en usteki urunun Windows 10 ozelligi barindirdigi dogrulanir
    And Kullanici sayfayi kapatir


