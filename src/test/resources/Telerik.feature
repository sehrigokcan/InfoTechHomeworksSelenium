Feature: Kullanici aradigi kelimeye iliskin sonuclarla karsilasir

  @telerik
  Scenario Outline: Kullanici verilen degerlere gore yapilan aramalara uygun sonuclar verir
    Given Kullanici sayfaya gider
    When Kullanici Cookie icin gelen seceneklerden Accept and Close’a tiklar
    And Kullanici ust menu bar dan arama simgesine tıklar
    And Kullanici search box kutusuna "<deger>" yazar
    And Kullanici arama butonuna tiklar
    Then Arama sonucu cikan ilk secenegin ilgili "<deger>" barindirir
    And Kullanıcı sayfayı kapatır.

    Examples:
      | deger        |
      | keyword test|
      | data testing|
