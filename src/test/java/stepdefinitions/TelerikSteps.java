package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.TelerikPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReUsableMethods;

import java.util.Arrays;


public class TelerikSteps {
    TelerikPage telerikPage= new TelerikPage();
    String sepet2;
    String sepet1;

    @Given("Kullanici sayfaya gider")
    public void kullanici_sayfaya_gider() throws InterruptedException {

        Driver.getDriver().get(ConfigReader.getProperty("telerikUrl"));
        Driver.wait(3);
    }
    @When("Kullanici Cookie icin gelen seceneklerden Accept and Close’a tiklar")
    public void kullanici_cookie_icin_gelen_seceneklerden_accept_and_close_a_tiklar() {
        try{
            telerikPage.acceptAndCloseButton.click();
        }catch (Exception e){}}
    @When("Kullanici ust menu bar dan arama simgesine tıklar")
    public void kullanici_ust_menu_bar_dan_arama_simgesine_tıklar() {

        telerikPage.searchButtonBar.click();

    }
    @And("Kullanici search box kutusuna {string} yazar")
    public void kullaniciSearchBoxKutusunaYazar(String arg0) {
        telerikPage.searchBox.sendKeys(arg0);
    }
    @When("Kullanici arama butonuna tiklar")
    public void kullanici_arama_butonuna_tiklar() {
        telerikPage.searchButton.click();
    }
    @Then("Arama sonucu cikan ilk secenegin ilgili {string} barindirir")
    public void aramaSonucuCikanIlkSeceneginIlgiliBarindirir(String arg0) throws InterruptedException {
        String[] arg1 =arg0.split((" "));
        SoftAssert softAssert= new SoftAssert();
        for (String arg: arg1) {
            softAssert.assertTrue(telerikPage.ilkAramaSonucText.getText().toLowerCase().contains(arg));
        }
//        softAssert.assertTrue(telerikPage.ilkAramaSonucText.getText().toLowerCase().contains(arg1[0]));
//        softAssert.assertTrue(telerikPage.ilkAramaSonucText.getText().toLowerCase().contains(arg1[1]));
        softAssert.assertAll();
        Driver.wait(5);
      //  Assert.assertTrue(telerikPage.ilkAramaSonucu.getText().contains(arg0));
    }
    @Then("Kullanıcı sayfayı kapatır.")
    public void kullanıcı_sayfayı_kapatır() {
        Driver.closeDriver();
    }


    @And("Kullanici bir paketi Buy Now ile sepete ekler")
    public void kullaniciBirPaketiBuyNowIleSepeteEkler() {
        telerikPage.buyNowButton.click();
    }



    @Then("Kullanici sepet simgesi uzerindeki rakami dogrular")
    public void kullaniciSepetSimgesiUzerindekiRakamiDogrular() {

        String expected=telerikPage.sepet.getText();
        String actual= "1";
        Assert.assertEquals(expected,actual);
    }

    @Given("kullanici {string} web sayfasina gider")
    public void kullaniciWebSayfasinaGider(String arg0) {
        Driver.getDriver().get(arg0);
    }

    @When("kullanici cookiesi kabul eder")
    public void kullaniciCookiesiKabulEder() {
        try{
            telerikPage.acceptAndCloseButton.click();
        }catch (Exception e){}
    }


    @And("kullanici BuyNow butonu ile sepete bir urun ekler")
    public void kullaniciBuyNowButonuIleSepeteBirUrunEkler() {
        telerikPage.buyNowButton.click();
    }

    @And("kullanici Pricing sayfasina geri doner")
    public void kullaniciPricingSayfasinaGeriDoner() throws InterruptedException {
        Driver.wait(3);
        Driver.getDriver().get(ConfigReader.getProperty("telerikUrl"));

        telerikPage.pricing.click();

    }

    @And("kullanici Buynow butonu ile bir urun daha ekler")
    public void kullaniciBuynowButonuIleBirUrunDahaEkler() {
        telerikPage.buyNowButton.click();
    }

    @And("kullanici ana sayfaya geri doner")
    public void kullaniciAnaSayfayaGeriDoner() {
        Driver.getDriver().get((ConfigReader.getProperty("telerikUrl")));
    }

    @And("kullanici sepete uzerindeki mevcut sayiyi alir")
    public void kullaniciSepeteUzerindekiMevcutSayiyiAlir() {

        sepet1= telerikPage.sepet.getText();
    }

    @And("kullanici sepete tekrar gider")
    public void kullaniciSepeteTekrarGider() {

        telerikPage.sepet.click();
    }

    @And("kullanici sepetteki urunlerden bir tanesini Delete linki ile siler")
    public void kullaniciSepettekiUrunlerdenBirTanesiniDeleteLinkiIleSiler() {
        telerikPage.acceptAndCloseButton.click();
        telerikPage.deleteItem.click();
    }

    @Then("kullanici sepet simgesi uzerindeki sayinin bir eksildigini kontrol eder")
    public void kullaniciSepetSimgesiUzerindekiSayininBirEksildiginiKontrolEder() {

        sepet2= telerikPage.sepet.getText();
        System.out.println(sepet1);
        System.out.println(sepet2);
        int ilkSayi=Integer.valueOf(sepet1);
        int ikinciSayi= Integer.valueOf(sepet2);

        Assert.assertEquals(ilkSayi - ikinciSayi, 1);

    }

    @And("kullanici Pricing linkine tiklar")
    public void kullaniciPricingLinkineTiklar() {

        telerikPage.pricing.click();
    }
}
