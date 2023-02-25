package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class EbayPage {

    public EbayPage (){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(id = "gdpr-banner-accept")
    public WebElement acceptAllCookiesButton;

    @FindBy (id= "gh-ac")
    public WebElement searchbox;


    @FindBy (id= "gh-btn")
    public WebElement searchButton;


    @FindBy (className= "srp-carousel-list__item-link--truncated-small-item")
    public WebElement w10link;

    @FindBy (xpath= "(//*[@class='s-item__link'])[2]")
    public WebElement text;

}
