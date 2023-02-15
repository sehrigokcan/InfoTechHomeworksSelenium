package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WebTablePage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBase;

import java.util.List;

public class Homework04 extends TestBase {


WebTablePage webTablePage= new WebTablePage();
    @Test
    public void test01() throws InterruptedException {
//        Soru 1:
//    Kullanıcı https://demo.guru99.com/test/web-table-element.php sayfasına gider
//    Kullanıcı Tablonun 4’üncü satır 5’üncü sütundaki verinin doğruluğunu kontrol
//    eder.
//            ExpectedValue = http://www.timconway.com

        Driver.getDriver().get(ConfigReader.getProperty("webTableUrl"));
        wait(2);
        String actualValue = webTablePage.tablesatir4sutun5.getText();
        String expectedValue ="http://www.timconway.com";

        Assert.assertEquals(expectedValue, actualValue);

    }

    @Test
    public void test02() throws InterruptedException {
        //    Soru 2:
//    Kullanıcı https://demo.guru99.com/test/web-table-element.php sayfasına gider
//    Birinci tablonun tüm başlıklarının ikinci tablonun tüm başlıkları ile aynı olduğunu
//doğrular.
//        (Doğrulamayı manuel olarak tek tek değil toplu veya döngüsel bir doğrulama
//    şeklinde yapınız.)

        Driver.getDriver().get(ConfigReader.getProperty("webTableUrl"));
        wait(2);

        List<WebElement> table1basliklar= webTablePage.table1baslik;
        List<WebElement> table2basliklar= webTablePage.table2baslik;

        for (int i=0; i<table1basliklar.size();i++) {
            Assert.assertEquals(table1basliklar.get(i).getText(), table2basliklar.get(i).getText());
            System.out.println(table1basliklar.get(i).getText()+ "/n" +table2basliklar.get(i).getText());
        }

    }

    @Test
    public void test03() throws InterruptedException {
//        Soru 3:
//    Kullanıcı https://demo.guru99.com/test/web-table-element.php Sayfasına gider
//    Kullanıcı 2’inci tabloda 3’üncü satır 4’üncü veriyi XPATH ile locate eder. Değerin
//    doğruluğunu kontrol eder.
//        ExpectedValue = $100.00

        Driver.getDriver().get(ConfigReader.getProperty("webTableUrl"));
        wait(2);

        String actualValue = webTablePage.table2satir3veri4.getText();
        String expectedValue ="$100.00";

        Assert.assertEquals(expectedValue, actualValue);
    }
}
