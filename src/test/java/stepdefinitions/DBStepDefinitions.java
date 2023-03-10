package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.ConfigReader;

import java.sql.*;

public class DBStepDefinitions {
    String url = ConfigReader.getProperty("db_url");
    String username = ConfigReader.getProperty("db_username");
    String password = ConfigReader.getProperty("db_password");

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @Given("user connects to the database")
    public void userConnectsToTheDatabase() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        resultSet = statement.executeQuery("SELECT * FROM urun");
    }

    @Then("toplam urun sayisini assert edin")
    public void toplamUrunSayisiniAssertEdin() throws SQLException {
        int urunCount = 0;
        while (resultSet.next()) {
            urunCount++;
        }
        System.out.println("Toplam urun sayisi: " + urunCount);
        Assert.assertEquals(urunCount, 5);

    }

    @Then("en yuksek fiyatli urunu assert edin")
    public void enYuksekFiyatliUrunuAssertEdin() {
    }

    @And("urun isimlerinin birini degistir")
    public void urunIsimlerininBiriniDegistir() {
    }

    @And("urun isimlerinin fiyat ortalamasini bul")
    public void urunIsimlerininFiyatOrtalamasiniBul() {
    }

    @And("sutun basliklarini degistirip tabloyu yazdir")
    public void sutunBasliklariniDegistiripTabloyuYazdir() {
    }

    @And("urun isimlerini buyuk harfle yazdir")
    public void urunIsimleriniBuyukHarfleYazdir() {
    }

    @And("urun isimlerini kucuk harfle yazdir")
    public void urunIsimleriniKucukHarfleYazdir() {
    }

    @And("urun isimlerinin ilk harfi buyuk olacak sekilde yazdir")
    public void urunIsimlerininIlkHarfiBuyukOlacakSekildeYazdir() {
    }

    @And("belli fiyatin altindaki urunleri isme ve fiyata gore azalan nitelikte sirala")
    public void belliFiyatinAltindakiUrunleriIsmeVeFiyataGoreAzalanNitelikteSirala() {
    }
}
