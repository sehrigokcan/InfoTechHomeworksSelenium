Feature: Kullanici sepete urun ekleyebilir

  @telerik2
  Scenario: Kullanici sepete ekledigi urunun fiyatini gorebilir

    Given Kullanici sayfaya gider
    When Kullanici Cookie icin gelen seceneklerden Accept and Close’a tiklar
    And Kullanici Pricing linkine tiklar
    And Kullanici bir paketi Buy Now ile sepete ekler
    And Kullanici sayfaya gider
    Then Kullanici sepet simgesi uzerindeki rakami dogrular

