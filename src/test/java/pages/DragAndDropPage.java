package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class DragAndDropPage {

    public DragAndDropPage (){
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(xpath = "//span[text()='Alles accepteren']")
    public WebElement acceptAllCookiesButton;

    @FindBy(id="bank")
    public WebElement debitSideAccount;

    @FindBy(id="loan")
    public WebElement creditSideAccount;

    @FindBy(xpath = "(//li[@id='fourth'])[1]")
    public WebElement sourceValue50001;

    @FindBy(xpath = "(//li[@id='fourth'])[2]")
    public WebElement sourceValue50002;

    @FindBy(xpath = "//*[@id='bal3']/table//tr/td[2]")
    public WebElement debitMovement;

    @FindBy(xpath = "//*[@id='bal3']/table//tr/td[4]")
    public WebElement creditMovement;

    @FindBy(xpath = "//*[text()=' SALES ']")
    public WebElement sales;

    @FindBy(xpath = "//*[text()=' BANK ']")
    public WebElement bank;
    @FindBy(xpath = "(//li[@class='placeholder'])[2]")
    public WebElement targetInDepitAmountRight;

    @FindBy(linkText = "Perfect!")
    public WebElement perfectButton;
    @FindBy (id = "t7")
    public WebElement savedValueInTableDebitSide;


}
