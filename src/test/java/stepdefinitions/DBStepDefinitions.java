package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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


    }

    @Then("toplam urun sayisini assert edin")
    public void toplamUrunSayisiniAssertEdin() throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM urun");
        int urunCount = 0;
        while (resultSet.next()) {
            urunCount++;
        }
        System.out.println("Toplam urun sayisi: " + urunCount);
        Assert.assertEquals(urunCount, 5);
    }

    @Then("en yuksek fiyatli urunu assert edin")
    public void enYuksekFiyatliUrunuAssertEdin() throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM urun order by price desc");
        resultSet.first();
        String urunIsmi= resultSet.getString("name");
        Assert.assertEquals(urunIsmi, "cilek");

    }

    @And("urun isimlerinin birini degistir")
    public void urunIsimlerininBiriniDegistir() throws SQLException {
        String yeniUrunIsmi="ayva";
        int control= statement.executeUpdate("update urun set name='kavun' where id=1;" );
        System.out.println(control);
        Assert.assertTrue(control==1);
    }

    @And("urun isimlerinin fiyat ortalamasini bul")
    public void urunIsimlerininFiyatOrtalamasiniBul() throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM urun");
        double toplam=0;
        while (resultSet.next()) {
            toplam+=resultSet.getDouble("price");
        }
        double ort= toplam/5;
        System.out.println(" Ortalama:"+ ort);

    }

    @And("sutun basliklarini degistirip tabloyu yazdir")
    public void sutunBasliklariniDegistiripTabloyuYazdir() throws SQLException {
        resultSet = statement.executeQuery("SELECT id AS URUN_ID, name AS URUN_ADI, price AS URUN_FIYAT FROM urun");
        ResultSetMetaData resultSetMetaData=resultSet.getMetaData();

        for (int i = 1; i <= 3; i++) {
            System.out.print(resultSetMetaData.getColumnName(i) + "\t");
        }
        System.out.println();
        System.out.println("********************************");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("URUN_ID")+"\t\t"+ resultSet.getString("URUN_ADI")+ "\t\t"+resultSet.getDouble("URUN_FIYAT"));
        }
    }

    @And("urun isimlerini buyuk harfle yazdir")
    public void urunIsimleriniBuyukHarfleYazdir() throws SQLException {
        resultSet = statement.executeQuery("SELECT Upper(name) AS urun_adi FROM urun");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("urun_adi"));
        }
    }

    @And("urun isimlerini kucuk harfle yazdir")
    public void urunIsimleriniKucukHarfleYazdir() throws SQLException {
        resultSet = statement.executeQuery("SELECT Lower(name) AS urun_adi FROM urun");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("urun_adi"));
        }
    }

    @And("urun isimlerinin ilk harfi buyuk olacak sekilde yazdir")
    public void urunIsimlerininIlkHarfiBuyukOlacakSekildeYazdir() throws SQLException {

                resultSet = statement.executeQuery("SELECT INITCAP(name) AS urun_adi FROM urun");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("urun_adi"));
        }
    }

    @And("belli fiyatin altindaki urunleri isme ve fiyata gore azalan nitelikte sirala")
    public void belliFiyatinAltindakiUrunleriIsmeVeFiyataGoreAzalanNitelikteSirala() throws SQLException {
        resultSet = statement.executeQuery("SELECT name,price FROM urun where price<30 order by name,price ASC");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("price")+ "   :   " + resultSet.getString("name"));
        }
    }

    @And("baglantilari kapat")
    public void baglantilariKapat() throws SQLException {
        statement.close();
        connection.close();
    }

    @When("employees isminde tablo olustur")
    public void employeesIsmindeTabloOlustur() throws SQLException {

        String sql= " CREATE TABLE employees \n" +
                "( \n" +
                " id number(9), \n" +
                " name varchar2(50), \n" +
                " state varchar2(50), \n" +
                " salary number(20), \n" +
                " company varchar2(20) \n" +
                ");";
        boolean tabloOlustuMu= statement.execute(sql);
        System.out.println(" Tablo Olustu mu?"+ tabloOlustuMu);
    }

    @And("tabloya verileri ekle")
    public void tabloyaVerileriEkle() throws SQLException {
        String sql= " INSERT INTO employees VALUES(123456789, 'Johnny Walk', 'New \n" +
                "Hampshire', 2500, 'IBM'); \n" +
                "INSERT INTO employees VALUES(234567891, 'Brian Pitt', 'Florida', 1500,\n" +
                "'LINUX'); \n" +
                "INSERT INTO employees VALUES(245678901, 'Eddie Murphy', 'Texas', \n" +
                "3000, 'WELLS FARGO'); \n" +
                "INSERT INTO employees VALUES(456789012, 'Teddy Murphy', 'Virginia', \n" +
                "1000, 'GOOGLE'); \n" +
                "INSERT INTO employees VALUES(567890124, 'Eddie Murphy', \n" +
                "'Massachuset', 7000, 'MICROSOFT'); \n" +
                "INSERT INTO employees VALUES(456789012, 'Brad Pitt', 'Texas', 1500, \n" +
                "'TD BANK'); \n" +
                "INSERT INTO employees VALUES(123456719, 'Adem Stone', 'New \n" +
                "Jersey', 2500, 'IBM');";

        boolean verilerEklendiMi= statement.execute(sql);
        System.out.println(" Tablo Olustu mu?"+ verilerEklendiMi);
    }

    @When("ID numarasinin sonu cift sayi olan satirlarin bilgilerini tablo olarak getirin")
    public void idNumarasininSonuCiftSayiOlanSatirlarinBilgileriniTabloOlarakGetirin() {

    }

    @And("Tablodaki toplam satir sayisini bulunuz")
    public void tablodakiToplamSatirSayisiniBulunuz() throws SQLException {
        resultSet= statement.executeQuery(" select count(*) as satirSayisi from employees");
        System.out.println(" Tablodaki toplam satir sayisi: "+ resultSet.getString("satirSayisi"));
    }

    @And("Tablodaki maksimum maasi bulunuz")
    public void tablodakiMaksimumMaasiBulunuz() {
    }

    @And("Tablodaki ikinci maksimum maasi bulunuz")
    public void tablodakiIkinciMaksimumMaasiBulunuz() {
    }

    @And("Tablodaki maksimum maasa sahip satirin tüm bilgilerini getiriniz")
    public void tablodakiMaksimumMaasaSahipSatirinTümBilgileriniGetiriniz() {
    }

    @And("Tablodaki maksimum ikinci maasa sahip satirin tum bilgilerini getiriniz")
    public void tablodakiMaksimumIkinciMaasaSahipSatirinTumBilgileriniGetiriniz() {
    }
}
