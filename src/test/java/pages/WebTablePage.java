package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class WebTablePage {


    public WebTablePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (xpath = "//*[@id='table1']/tbody/tr[4]/td[5]")
    public WebElement tablesatir4sutun5;

    @FindBy (xpath = "//*[@id='table1']/thead/tr")
    public List<WebElement> table1baslik;

    @FindBy (xpath = "//*[@id='table2']/thead/tr")
    public List<WebElement> table2baslik;

    @FindBy (xpath = "//*[@id='table2']/tbody/tr[3]/td[4]")
    public WebElement table2satir3veri4;

}
