package tests;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.AllPages;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBase;
import java.io.FileInputStream;
import java.io.IOException;

public class Homework06 extends TestBase {

    AllPages allPages= new AllPages();

    @Test
    public void test01() throws IOException, InterruptedException {

//        Kullanıcı https://www.browserstack.com/users/sign_up sayfasına gider
        Driver.getDriver().get(ConfigReader.getProperty("excel_url"));

//        Kullanıcı Cookies seçeneğini Accept All seçeneği ile kabul eder.
        try{allPages.excelPage.acceptCookie.click();}
        catch (Exception e){}
//        Kullanıcı Proje içerisindeki EXCEL dosyasından aldığı kullanıcı adı ve soyadını Full name TextBox’ına yazar.
      //  allPages.excelPage.signIn.click();
        String path= ".\\src\\test\\java\\resources\\ExcelTest.xlsx";
        FileInputStream fis= new FileInputStream(path);
        Workbook workBook= WorkbookFactory.create(fis);
       String kAdi= workBook.getSheetAt(0).getRow(1).getCell(0).toString();
       allPages.excelPage.user_full_name.sendKeys(kAdi);

//        Kullanıcı Proje içerisindeki EXCEL dosyasındaki GEÇERSİZ EMAİLLER Sheet’inden aldığı kullanıcı
//        email adresini Business Email TextBox’ına yazar.
        String email= workBook.getSheet("GEÇERSİZ EMAİLLER").getRow(0).getCell(0).toString();
        allPages.excelPage.user_email_login.sendKeys(email);
//        Kullanıcı password textbox kutusuna tıklar.
//                INVALID olarak girilen MAIL adresini yarattığı hata mesajı “Invalid Email” validate(assert) edilir.
        allPages.excelPage.user_password.click();
        wait(5);
        Assert.assertTrue(allPages.excelPage.invalidEmail.isDisplayed());

    }

    @Test
    public void test02() throws IOException, InterruptedException {

//        Soru 2: NEGATİF SENARYO
//        Kullanıcı https://www.browserstack.com/users/sign_up  sayfasına gider
        Driver.getDriver().get(ConfigReader.getProperty("excel_url"));
//        Kullanıcı Cookies seçeneğini Accept All seçeneği ile kabul eder.
        try{allPages.excelPage.acceptCookie.click();}
        catch (Exception e){}
//                Kullanıcı Proje içerisindeki EXCEL dosyasından aldığı kullanıcı adı ve soyadını Full name TextBox’ına yazar.
        String path= ".\\src\\test\\java\\resources\\ExcelTest.xlsx";
        FileInputStream fis= new FileInputStream(path);
        Workbook workBook= WorkbookFactory.create(fis);
        String kAdi= workBook.getSheetAt(0).getRow(1).getCell(0).toString();
        allPages.excelPage.user_full_name.sendKeys(kAdi);
        wait(2);
//        Kullanıcı Proje içerisindeki EXCEL dosyasındaki GEÇERLİ EMAİLLER Sheet’inden aldığı kullanıcı email adresini Business Email TextBox’ına yazar.
        String email= workBook.getSheet("GEÇERLİ EMAİLLER").getRow(1).getCell(0).toString();
        allPages.excelPage.user_email_login.sendKeys(email);
        wait(2);
//        Kullanıcı password textbox kutusuna EXCEL dosyasındaki GEÇERSİZ PASSWORD Sheetinden aldığı 6
//        karakterden kısa password’ü yazar.
        String password= workBook.getSheet("GEÇERSİZ PASSWORD").getRow(0).getCell(0).toString();
        allPages.excelPage.user_password.sendKeys(password);
        wait(2);
//        Kullanıcı “I agrre...” Checkbox’ına tıklar.
        allPages.excelPage.agree.click();
        wait(2);
//        INVALID olarak girilen PASSWORD’ün  yarattığı hata mesajı “At least 6 characters”
//        validate(assert) edilir.

        Assert.assertTrue(allPages.excelPage.atleast.isDisplayed());
    }

    @Test
    public void test03() throws IOException, InterruptedException {

//        Soru 3: POZİTİF SENARYO
//        Kullanıcı https://www.browserstack.com/users/sign_up  sayfasına gider
        Driver.getDriver().get(ConfigReader.getProperty("excel_url"));
//        Kullanıcı Cookies seçeneğini Accept All seçeneği ile kabul eder.
        try{allPages.excelPage.acceptCookie.click();}
        catch (Exception e){}
//        Kullanıcı Proje içerisindeki EXCEL dosyasından aldığı kullanıcı adı ve soyadını Full name
//        TextBox’ına yazar.
        String path = ".\\src\\test\\java\\resources\\ExcelTest.xlsx";
        FileInputStream fis = new FileInputStream(path);
        Workbook workBook = WorkbookFactory.create(fis);
        String kAdi = workBook.getSheetAt(0).getRow(3).getCell(0).toString();
        allPages.excelPage.user_full_name.sendKeys(kAdi);
//        Kullanıcı Proje içerisindeki EXCEL dosyasındaki GEÇERLİ EMAİLLER Sheet’inden aldığı kullanıcı email adresini Business Email TextBox’ına yazar.
        String email = workBook.getSheet("GEÇERLİ EMAİLLER").getRow(1).getCell(0).toString();
        allPages.excelPage.user_email_login.sendKeys(email);
//        Kullanıcı password textbox kutusuna EXCEL dosyasındaki GEÇERLİ PASSWORD Sheetinden aldığı şifreyi yazar.
        String password = workBook.getSheet("GEÇERLİ PASSWORD").getRow(0).getCell(0).toString();
        allPages.excelPage.user_password.sendKeys(password);
//        Kullanıcı “I agrre...” Checkbox’ına tıklar.
        allPages.excelPage.agree.click();
//        Kullanıcı “Sign me up” butonuna tıklar.
        allPages.excelPage.user_submit.click();
//        Başarılı bir şekilde kayıt yapıldığı Validate edilir
        wait(5);

        try {
            Assert.assertEquals(allPages.excelPage.giris.getText(), "Hi Sehri! What are you looking to test?");

        } catch (Exception e) {
            Assert.assertTrue(allPages.excelPage.giris2.getText().contains("Quick question, for whom are you evaluating Live?"));
        }
    }}

