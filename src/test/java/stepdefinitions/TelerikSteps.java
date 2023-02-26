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

    @Given("Kullanici sayfaya gider")
    public void kullanici_sayfaya_gider() throws InterruptedException {

        Driver.getDriver().get(ConfigReader.getProperty("telerikUrl"));
        Driver.wait(3);
    }
    @When("Kullanici Cookie icin gelen seceneklerden Accept and Close’a tiklar")
    public void kullanici_cookie_icin_gelen_seceneklerden_accept_and_close_a_tiklar() {
        try{
            telerikPage.acceptAndCloseButton.click();
        }catch (Exception e){}

    }
    @When("Kullanici ust menu bar dan arama simgesine tıklar")
    public void kullanici_ust_menu_bar_dan_arama_simgesine_tıklar() {
        telerikPage.ustbarButton.click();
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
    public void aramaSonucuCikanIlkSeceneginIlgiliBarindirir(String arg0) {
        String[] arg1 =arg0.split((" "));
        SoftAssert softAssert= new SoftAssert();
        softAssert.assertTrue(telerikPage.ilkAramaSonucText.getText().toLowerCase().contains(arg1[0]));
        softAssert.assertTrue(telerikPage.ilkAramaSonucText.getText().toLowerCase().contains(arg1[1]));
        softAssert.assertAll();

      //  Assert.assertTrue(telerikPage.ilkAramaSonucu.getText().contains(arg0));
    }
    @Then("Kullanıcı sayfayı kapatır.")
    public void kullanıcı_sayfayı_kapatır() {
        Driver.closeDriver();
    }



}
