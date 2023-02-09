package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.ConfigReader;
import utilities.Driver;

//    sign in => create a new account
//    1 AssertFalse ile negatif testcase yazımı (checkbox üzerinden)
//    1 AssertTrue veya Equal ile pozitif test yazımı.
//    1 Tane login test case oluşturulması.
//            Ayrıca bu testlerden negatif testcase'in basamaklarını yani steplerini manuel olarak da yazın lütfen.
//    İlgili testcase in içine yorum satırı olarak en üste ekleyin.
//    Testcaseleri yeni TestNG Framework üzerinde yazınız.

public class Homework02 {


@BeforeMethod
public void testBefore(){
    Driver.getDriver().get(ConfigReader.getProperty("url"));

}

@Test
public void testAssertFalse(){
//    1 AssertFalse ile negatif testcase yazımı (checkbox üzerinden)

    //Kullanici Sign in butonuna tiklar
    WebElement signInButton= Driver.getDriver().findElement(By.partialLinkText("Sign In"));
    signInButton.click();

    // Acilan sayfadan "Create a new account" linkine tiklar

    WebElement createButton= Driver.getDriver().findElement(By.partialLinkText("Create a new account"));
    createButton.click();

    //Kullanici ilgili alanlari doldurur

    WebElement firstName= Driver.getDriver().findElement(By.id("user[first_name]"));
    Faker faker= new Faker();
    String fakeFirstName= faker.name().firstName();
    firstName.sendKeys(fakeFirstName);


    WebElement lastName= Driver.getDriver().findElement(By.id("user[last_name]"));
    String fakeLastName= faker.name().lastName();
    lastName.sendKeys(fakeLastName);


    WebElement email= Driver.getDriver().findElement(By.id("user[email]"));
    String fakeEmail= faker.internet().emailAddress();
    email.sendKeys(fakeEmail);


    WebElement password= Driver.getDriver().findElement(By.id("user[password]"));
    String fakePassword= faker.internet().password();
    password.sendKeys(fakePassword);

    // Kullanici checkbox kutusunu isaretlemez


    // Kullanici Sign Up butonuna tiklar
    WebElement signUpButton= Driver.getDriver().findElement(By.xpath("//button[@type='submit']"));
    signUpButton.click();

    // Kullanici hata mesaji alir
    WebElement errorMessage=Driver.getDriver().findElement(By.xpath("//li[@class='form-error__list-item']"));

    // Hata mesaji aldigina dair dogrulama
    Assert.assertTrue(errorMessage.getText().contains("Terms must be accepted"));

    //Checkbox secilmedigine dair dogrulama
    WebElement checkbox= Driver.getDriver().findElement(By.id("user[terms]"));
    Assert.assertFalse(checkbox.isSelected());

}

@Test
public void testAssertTrue(){

    //    1 AssertTrue veya Equal ile pozitif test yazımı.

    //Kullanici Sign in butonuna tiklar
    WebElement signInButton= Driver.getDriver().findElement(By.partialLinkText("Sign In"));
    signInButton.click();

    // Acilan sayfadan "Create a new account" linkine tiklar

    WebElement createButton= Driver.getDriver().findElement(By.partialLinkText("Create a new account"));
    createButton.click();

    //Kullanici ilgili alanlari doldurur

    WebElement firstName= Driver.getDriver().findElement(By.id("user[first_name]"));
    Faker faker= new Faker();
    String fakeFirstName= faker.name().firstName();
    firstName.sendKeys(fakeFirstName);


    WebElement lastName= Driver.getDriver().findElement(By.id("user[last_name]"));
    String fakeLastName= faker.name().lastName();
    lastName.sendKeys(fakeLastName);


    WebElement email= Driver.getDriver().findElement(By.id("user[email]"));
    String fakeEmail= faker.internet().emailAddress();
    email.sendKeys(fakeEmail);


    WebElement password= Driver.getDriver().findElement(By.id("user[password]"));
    String fakePassword= faker.internet().password();
    password.sendKeys(fakePassword);


    // Kullanici checkbox kutusunu isaretlemez
    WebElement checkbox= Driver.getDriver().findElement(By.id("user[terms]"));
    checkbox.click();
    Assert.assertTrue(checkbox.isSelected());

    // Kullanici Sign Up butonuna tiklar
    WebElement signUpButton= Driver.getDriver().findElement(By.xpath("//button[@type='submit']"));
    signUpButton.click();

    // Kullanici basarili sekilde kaydolur
    WebElement dashboard= Driver.getDriver().findElement(By.xpath("//button[@class='dropdown__toggle-button']"));
    Assert.assertTrue(dashboard.getText().contains(fakeFirstName));

    dashboard.click();
    WebElement signOut= Driver.getDriver().findElement(By.partialLinkText("Sign Out"));
    signOut.click();

}

@Test
public void loginTest() throws InterruptedException {

    //Kullanici Sign in butonuna tiklar
    WebElement signInButton= Driver.getDriver().findElement(By.partialLinkText("Sign In"));
    signInButton.click();

    //Acilan sayfada ilgili alanlari doldurur
    WebElement email= Driver.getDriver().findElement(By.id("user[email]"));
    email.sendKeys(ConfigReader.getProperty("emailLogin"));


    WebElement password= Driver.getDriver().findElement(By.id("user[password]"));
    password.sendKeys(ConfigReader.getProperty("passwordLogin"));

    WebElement signUpButton= Driver.getDriver().findElement(By.xpath("//button[@type='submit']"));
    signUpButton.click();

    // Kullanici basarili sekilde kaydolur
    WebElement dashboard= Driver.getDriver().findElement(By.xpath("//button[@class='dropdown__toggle-button']"));
    Assert.assertTrue(dashboard.getText().contains(ConfigReader.getProperty("loginFirstName")));

    dashboard.click();
    WebElement signOut= Driver.getDriver().findElement(By.partialLinkText("Sign Out"));
    signOut.click();
}

@AfterMethod
public void testAfter(){
    Driver.closeDriver();
}
}
