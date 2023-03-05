package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class TelerikPage {
    public TelerikPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy (linkText = "Delete Item")
    public WebElement deleteItem;

    @FindBy (id="onetrust-accept-btn-handler")
    public WebElement acceptAndCloseButton;

    @FindBy (xpath="//*[@id='js-tlrk-nav-drawer-button']")
    public WebElement ustbarButton;

    @FindBy (id="js-tlrk-nav-search-link")
    public WebElement searchButtonBar;

    @FindBy (xpath = "//*[@type='search']")
    public WebElement searchBox;

    @FindBy (className = "TK-Tag-Input-Button")
    public WebElement searchButton;

    @FindBy (xpath = "//*[@id='js-results-wrapper']/div[1]/div")
    public WebElement ilkAramaSonucu;


    @FindBy (xpath = "//*[@id='js-results-wrapper']/div[3]/ul/li[1]/h4/a")
    public WebElement ilkAramaSonucText;

    @FindBy (linkText = "Pricing")
    public WebElement pricing;

    @FindBy (xpath = "//*[@class='Pricings-button']/th/div/a")
    public WebElement buyNowButton;

    @FindBy (xpath = "//*[@id='js-tlrk-nav-drawer']/ul[2]/li[2]/a/span[2]")
    public WebElement sepet;
}
