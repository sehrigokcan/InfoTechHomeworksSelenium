package tests;

import com.github.dockerjava.api.model.Driver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework01 {
    WebDriver driver = new EdgeDriver();

    @Test
    public void testName() throws InterruptedException {

        driver.get("http://gcreddy.com/project/admin/login.php");
        driver.manage().window().maximize();
        Thread.sleep(3000);

//        Gerekli bilgiler:
//        http://gcreddy.com/project/admin/login.php
//        Username : gcreddy
//        Password : Temp@2020
//        Aşağıdaki manuel testcase’in steplerini otomate ediniz.

//        TESTCASE STEPLERİ:
//        1. Kullanıcı http://gcreddy.com/project/admin/login.php sayfasına gider.
//        2. Username textbox kutusuna kullanıcı adını girer.
        WebElement userName= driver.findElement(By.xpath("//input[@type='text']"));
        userName.sendKeys("gcreddy");

//        3. Password textbox kutusuna kullanıcı şifresini girer.
        WebElement password= driver.findElement(By.xpath("//*[@name='password']"));
        password.sendKeys("Temp@2020");
//        4. Login butonuna tıklar.
        driver.findElement(By.id("tdb1")).click();

//        5. Sol menü bar üzerniden Customers sekmesine tıklar.
        driver.findElement(By.linkText("Customers")).click();
        Thread.sleep(2000);
//        6. Açılan alt menüden Customers linkine tıklar.
        driver.findElement(By.xpath("//a[@href='http://gcreddy.com/project/admin/customers.php']")).click();
//        7. Sag menü kutusnudaki Edit butonuna tıklar.
        driver.findElement(By.id("tdb1")).click();
//        8. Personal bilgileri içindeki Firstname textbox kutsuna ismini girer.
        WebElement firstName=  driver.findElement(By.xpath("//*[@name='customers_firstname']"));
       firstName.clear();
       firstName.sendKeys("Sehri");
//        9. Personal bilgileri içindeki Lastname textbox kutsuna soyismini girer.
        WebElement lastname=  driver.findElement(By.xpath("//*[@name='customers_lastname']"));
        lastname.clear();
        lastname.sendKeys("Gokcan", Keys.TAB,"03/30/1991");
//        10.Personal bilgileri içindeki Date of Birth textbox kutsuna doğum tarihini
//        girer.
//        WebElement birthDate=  driver.findElement(By.id("customers_dob"));
//        birthDate.sendKeys("30/03/1991");
//        11.Personal bilgileri içindeki E-Mail Address textbox kutsuna e-mail adresini
//        girer.

        WebElement email=  driver.findElement(By.xpath("//*[@name='customers_email_address']"));
        driver.findElement(By.className("headerBarContent")).click();
        email.clear();
        email.sendKeys("sehrigokcan@gmail.com");

//        12.Company bilgileri içindeki Company name textbox kutusuna şirket ismini
//        girer.

        WebElement company=  driver.findElement(By.xpath("//*[@name='entry_company']"));
        company.clear();
        company.sendKeys("InfoTech");
//        13.Adres bilgileri içindeki Street textbox kutsuna sokak ismini girer.

        WebElement street=  driver.findElement(By.xpath("//*[@name='entry_street_address']"));
        street.clear();
        street.sendKeys("Koemeershoeve");
//        14.Adres bilgileri içindeki Suburb textbox kutsuna belediye ismini girer.
        WebElement suburb=  driver.findElement(By.xpath("//*[@name='entry_suburb']"));
        suburb.clear();
        suburb.sendKeys("Suburb");
//        15.Adres bilgileri içindeki Post Code textbox kutsuna posta kutusu numarasını
//        girer.
        WebElement postCode=  driver.findElement(By.xpath("//*[@name='entry_postcode']"));
        postCode.clear();
        postCode.sendKeys("78910");
//        16.Adres bilgileri içindeki City textbox kutsuna Şehir ismini girer.
        WebElement city=  driver.findElement(By.xpath("//*[@name='entry_city']"));
        city.clear();
        city.sendKeys("Wintelre");
//        17.Adres bilgileri içindeki State textbox kutsuna posta kutusu Eyalet ismini
//        girer.
        WebElement state=  driver.findElement(By.xpath("//*[@name='entry_state']"));
        state.clear();
        state.sendKeys("Eindhoven",Keys.TAB,"Netherlands");
//        18.Adres bilgileri içindeki Country dropdown menüsünden ülkesini seçer.

        driver.findElement(By.className("headerBarContent")).click();
//        19.Contact bilgileri içindeki Telephone Number textbox kutsuna telefon
//        numarasını girer.
        WebElement number=  driver.findElement(By.cssSelector("input[name='customers_telephone']"));
        number.clear();
        number.sendKeys("1234567");
//        20.Contact bilgileri içindeki Fax Number textbox kutsuna fax numarasını girer.
        WebElement fax=  driver.findElement(By.cssSelector("input[name='customers_fax']"));
        fax.clear();
        fax.sendKeys("1234567",Keys.TAB,"Unsubscribed");
//        21.Options bilgileri içindeki Newsletter dropdown menüsünden abonelik
//        durumunu seçer.

//        22.Save butonuna tıklar.
driver.findElement(By.id("tdb1")).click();
//        23.Sağ üst köşedeki SEARCH kutusuna kullanıcı ismini girer ve ENTER’a
//        basar.
        WebElement search= driver.findElement(By.cssSelector("input[name='search']"));
        search.sendKeys("Sehri",Keys.ENTER);
//        24.Tablodan çıkan kullanıcı ismi ve soy isminin doğruluğu teyit edilir.
        WebElement lName= driver.findElement(By.xpath("(//*[@class='dataTableContent'])[1]"));
        WebElement fName= driver.findElement(By.xpath("(//*[@class='dataTableContent'])[2]"));

        Assert.assertEquals(lName.getText(),"Gokcan");
        Assert.assertEquals(fName.getText(),"Sehri");
//        25.Sağ üst köşedeki Logoff tuşuna basılarak sayfadan çıkılır.
        Thread.sleep(2000);
        driver.findElement(By.linkText("Logoff")).click();

        driver.close();

    }
}
