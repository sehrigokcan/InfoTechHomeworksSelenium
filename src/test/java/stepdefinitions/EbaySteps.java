package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.EbayPage;
import utilities.ConfigReader;
import utilities.Driver;

public class EbaySteps {
    EbayPage ebayPage= new EbayPage();

    @Given("Kullanici ebay sitesine gider")
    public void kullanici_ebay_sitesine_gider() {
        Driver.getDriver().get(ConfigReader.getProperty("ebay_url"));
    }
    @When("Kullanici Cookies secenegini Accept All secenegi ile kabul eder")
    public void kullanici_cookies_secenegini_accept_all_secenegi_ile_kabul_eder() {
        try{
            ebayPage.acceptAllCookiesButton.click();
        }catch (Exception e){}

    }
    @When("Kullanici SearchBox kutusuna computer kelimesini yazar")
    public void kullanici_search_box_kutusuna_computer_kelimesini_yazar() {
        ebayPage.searchbox.sendKeys("computer");
    }
    @When("Kullanici arama butonuna basar")
    public void kullanici_arama_butonuna_basar() {
       ebayPage.searchButton.click();
    }
    @When("Kullanici filtre bolmesinden Windows10 secenegine tiklar")
    public void kullanici_filtre_bolmesinden_windows10_secenegine_tiklar() {
        ebayPage.w10link.click();
    }
    @Then("Cikan seceneklerden en usteki urunun Windows {int} ozelligi barindirdigi dogrulanir")
    public void cikan_seceneklerden_en_usteki_urunun_windows_ozelligi_barindirdigi_dogrulanir(Integer int1) throws InterruptedException {
        Assert.assertTrue(ebayPage.text.getText().contains("Windows 10"));
        Driver.wait(5);
    }
    @Then("Kullanici sayfayi kapatir")
    public void kullanici_sayfayi_kapatir() {
        Driver.closeDriver();
    }


}
