package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.*;

import java.util.ArrayList;
import java.util.List;

public class Homework05 extends TestBase {
    AllPages allPages= new AllPages();

    @Test
    public void dragAndDrop() throws InterruptedException {
        Driver.getDriver().get("https://demo.guru99.com/test/drag_drop.html");
        Driver.getDriver().switchTo().frame("gdpr-consent-notice");
        allPages.dragAndDropPage.acceptAllCookiesButton.click();

        Driver.wait(2);

        actions.dragAndDrop(allPages.dragAndDropPage.sourceValue50001, allPages.dragAndDropPage.targetInDepitAmountRight).build().perform();
        Driver.wait(2);

        actions.clickAndHold(allPages.dragAndDropPage.bank).moveToElement(allPages.dragAndDropPage.debitSideAccount).release().build().perform();
        Driver.wait(2);

        actions.dragAndDrop(allPages.dragAndDropPage.sourceValue50001, allPages.dragAndDropPage.targetInDepitAmountRight).build().perform();
        Driver.wait(2);

        actions.clickAndHold(allPages.dragAndDropPage.sales).moveToElement(allPages.dragAndDropPage.creditSideAccount).release().build().perform();
        Driver.wait(2);

        SoftAssert softAssert= new SoftAssert();

        String expectedValue = "5000";
        String actualValue = allPages.dragAndDropPage.debitSideAccount.getText();
        softAssert.assertEquals(actualValue, expectedValue);

        String actualValue2 = allPages.dragAndDropPage.creditSideAccount.getText();
        softAssert.assertEquals(expectedValue, actualValue2);

        softAssert.assertAll();

        Assert.assertTrue(allPages.dragAndDropPage.perfectButton.isDisplayed());


    }

    @Test
    public void test02() {
        Driver.getDriver().get(ConfigReader.getProperty("webTableUrl"));

        List<Double> degerler= new ArrayList<Double>();
        for (int i=1;i<=4;i++) {
            String deger= String.valueOf(Driver.getDriver().findElement(By.xpath(ReUsableMethods.findLocationOfElementInSpecificTableCell(1,i,4))).getText());
            String yeniDeger= deger.substring(1,deger.indexOf("."));
            System.out.println(yeniDeger);

            Double degerD= Double.valueOf(yeniDeger) ;
            if(degerD>50) {
                degerler.add(degerD);
            }
        }

        System.out.println(degerler);
        Assert.assertTrue(degerler.contains(100.0));
    }
}
