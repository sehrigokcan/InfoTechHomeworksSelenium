package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class ExcelPage {
    public ExcelPage() {
        PageFactory.initElements(Driver.getDriver(), this);    }

    @FindBy (id="accept-cookie-notification")
    public WebElement acceptCookie;

    @FindBy (id="user_submit")
    public WebElement user_submit;


    @FindBy (id="user_email_login")
    public WebElement user_email_login;

    @FindBy (id="user_password")
    public WebElement user_password;

    @FindBy (id="user_full_name")
    public WebElement user_full_name;

    @FindBy (id="tnc_checkbox")
    public WebElement agree;


    @FindBy (xpath = "//*[text()='Invalid Email']")
    public WebElement invalidEmail;

    @FindBy (xpath = "//*[text()='At least 6 characters']")
    public WebElement atleast;

    @FindBy (xpath = "//*[text()='Hi Sehri! What are you looking to test?']")
    public WebElement giris;

    @FindBy (xpath = "//*[text()='\n" +
            "          Quick question, for whom are you evaluating Live?\n" +
            "        ']")
    public WebElement giris2;

}

