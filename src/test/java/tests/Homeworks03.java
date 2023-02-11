package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBase;

import java.util.Set;

public class Homeworks03 extends TestBase {

 @Test
    public void test1() throws InterruptedException {
     // Amazon sayfasina gidin
     Driver.getDriver().get(ConfigReader.getProperty("amazonUrl"));
    wait(2);

    // cookies kabul et
     Driver.getDriver().findElement(By.id("sp-cc-accept")).click();
     wait(2);

     // Dil bolumune git
     actions.moveToElement(Driver.getDriver().findElement(By.className("icp-nav-link-inner"))).perform();

     // English sec
     Driver.getDriver().findElement(By.xpath("//*[text()='English']")).click();
     wait(2);

     //Account& List linkinin uzerine gel

     WebElement link= Driver.getDriver().findElement(By.id("nav-link-accountList"));
     actions.moveToElement(link).perform();
     wait(2);
// Find a Gift secenegini tikla
     WebElement findAGift= Driver.getDriver().findElement(By.linkText("Find a Gift"));
     findAGift.click();
     wait(2);

     // Gifts For Everyone metnini al
     String metin= Driver.getDriver().findElement(By.xpath("(//*[@class='ktemvl-1 hSsmQw'])[2]")).getText();

     // Ebaya gitmek icin yeni bir sekme ac
     ((JavascriptExecutor)Driver.getDriver()).executeScript("window.open('https://www.ebay.com', '_blank');");
     wait(2);

     Set<String> handles = Driver.getDriver().getWindowHandles();

     Driver.getDriver().switchTo().window(handles.toArray()[1].toString());

     // Metni searchboxa yapistir ve arama tusuna bas
     Driver.getDriver().findElement(By.id("gh-ac")).sendKeys(metin, Keys.ENTER);
     wait(2);
     // cikan ilk urunun fiyatini al

    String expectedPrice= Driver.getDriver().findElement(By.xpath("(//*[@class='s-item__price'])[2]")).getText();
    String actualPrice = "$17.22";
     // Assertion
     Assert.assertEquals(actualPrice,expectedPrice);





 }
}
