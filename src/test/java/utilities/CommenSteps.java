package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class CommenSteps {
    /**
     * This method clears the textbox and sends another text
     *
     * @param element
     * @param text
     */
    public static void sendText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    /**
     * This method click in an element and has wait implemented on it
     *
     * @param element
     */
    public static void click(WebElement element) {
        waitForClickablility(element,10);
        element.click();
    }

    public static byte[] takeScreenshot(String filename) {
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);

        File file = ts.getScreenshotAs(OutputType.FILE);
        // create destination as : filepath + filename + timestamp + .png
        String destination = System.getProperty("user.dir") + "/test-output/Screenshots/" + filename + date + ".png";

        try {
            FileUtils.copyFile(file, new File(destination));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return picBytes;
    }


    /**
     * Switches to new window by the exact title. Returns to original window if target title not found
     * @param targetTitle
     */
    public static void switchToWindow(String targetTitle) {
        String origin = Driver.getDriver().getWindowHandle();
        for (String handle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(handle);
            if (Driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.getDriver().switchTo().window(origin);
    }

    /**
     * Moves the mouse to given element
     *
     * @param element on which to hover
     */
    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    /**
     * return a list of string from a list of elements
     *
     * @param list of webelements
     * @return list of string
     */
    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }

    /**
     * Extracts text from list of elements matching the provided locator into new List<String>
     *
     * @param locator
     * @return list of strings
     */
    public static List<String> getElementsText(By locator) {

        List<WebElement> elems = Driver.getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();

        for (WebElement el : elems) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }

    /**
     * Performs a pause
     *
     * @param seconds
     */
    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Waits for the provided element to be visible on the page
     *
     * @param element
     * @param timeToWaitInSec
     * @return
     */
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for element matching the locator to be visible on the page
     *
     * @param locator
     * @param timeout
     * @return
     */
    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for provided element to be clickable
     *
     * @param element
     * @param timeout
     * @return
     */
    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    /**
     * Waits for element matching the locator to be clickable
     *
     * @param locator
     * @param timeout
     * @return
     */
    public static WebElement waitForClickability(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * waits for backgrounds processes on the browser to complete
     *
     * @param timeOutInSeconds
     */
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) Driver.getDriver()).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeOutInSeconds));
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    /**
     * Verifies whether the element matching the provided locator is displayed on page
     *
     * @param by
     * @throws AssertionError if the element matching the provided locator is not found or not displayed
     */
    public static void verifyElementDisplayed(By by) {
        try {
            Assert.assertTrue( Driver.getDriver().findElement(by).isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + by);

        }
    }

    /**
     * Verifies whether the element matching the provided locator is NOT displayed on page
     *
     * @param by
     * @throws AssertionError the element matching the provided locator is displayed
     */
    public static void verifyElementNotDisplayed(By by) {
        try {
            Assert.assertFalse( Driver.getDriver().findElement(by).isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();

        }
    }


    /**
     * Verifies whether the element is displayed on page
     *
     * @param element
     * @throws AssertionError if the element is not found or not displayed
     */
    public static void verifyElementDisplayed(WebElement element) {
        try {
            Assert.assertTrue(element.isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + element);

        }
    }

    /**
     * This methods casts the driver to a JavascriptExecutor and returns it
     *
     * @return
     */
    public static JavascriptExecutor getJSObject() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        return js;
    }
    /**
     * Clicks on an element using JavaScript
     *
     * @param element
     */
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }



    /**
     * Scrolls down to an element using JavaScript
     *
     * @param element
     */
    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * This method will scroll the page down based on the passed pixel parameter
     *
     * @param pixel
     */
    public static void scrollDownByPixels(int pixel) {
        getJSObject().executeScript("window.scrollBy(0," + pixel + ")");
    }

    /**
     * This method will scroll the page up based on the passed pixel parameter
     *
     * @param pixel
     */
    public static void scrollUpByPixels(int pixel) {
        getJSObject().executeScript("window.scrollBy(0,-" + pixel + ")");
    }

    /**
     * Performs double click action on an element
     *
     * @param element
     */
    public static void doubleClick(WebElement element) {
        new Actions(Driver.getDriver()).doubleClick(element).build().perform();
    }

    /**
     * Changes the HTML attribute of a Web Element to the given value using JavaScript
     *
     * @param element
     * @param attributeName
     * @param attributeValue
     */
    public static void setAttribute(WebElement element, String attributeName, String attributeValue) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attributeName, attributeValue);
    }

    /**
     * Highlighs an element by changing its background and border color
     * @param element
     */
    public static void highlight(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        waitFor(1);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].removeAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }

    /**
     * Checks or unchecks given checkbox
     *
     * @param element
     * @param check
     */
    public static void selectCheckBox(WebElement element, boolean check) {
        if (check) {
            if (!element.isSelected()) {
                element.click();
            }
        } else {
            if (element.isSelected()) {
                element.click();
            }
        }
    }

    /**
     * This method checks if radio/checkbox is enabled and then clicks on the
     * element that has the value we want
     *
     *
     * @param listElement
     * @param value
     */
    public static void clickRadioOrCheckbox(List<WebElement> listElement, String value) {
        String actualValue;

        for (WebElement el : listElement) {
            actualValue = el.getAttribute("value").trim();
            if (el.isEnabled() && actualValue.equals(value)) {
                el.click();
                break;
            }
        }
    }

    /**
     * This method checks if the text is found in the dropdown element and only then
     * it selects it
     *
     * @param element
     * @param textToSelect
     */
    public static void selectDropdown(WebElement element, String textToSelect) {
        try {
            Select select = new Select(element);

            List<WebElement> options = select.getOptions();

            for (WebElement el : options) {
                if (el.getText().equals(textToSelect)) {
                    select.selectByVisibleText(textToSelect);
                    break;
                }
            }
        } catch (UnexpectedTagNameException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method checks if the index is valid and only then selects it
     *
     * @param element
     * @param index
     */
    public static void selectDropdown(WebElement element, int index) {

        try {
            Select select = new Select(element);

            int size = select.getOptions().size();

            if (size > index) {
                select.selectByIndex(index);
            }
        } catch (UnexpectedTagNameException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method accepts alerts and catches exception if alert in not present
     *
     */
    public static void acceptAlert() {
        try {
            Alert alert = Driver.getDriver().switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method will dismiss the alert after checking if alert is present
     *
     */
    public static void dismissAlert() {
        try {


            Alert alert = Driver.getDriver().switchTo().alert();
            alert.dismiss();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns the alert text. If no alert is present exception is
     * caught and null is returned.
     *
     * @return
     */
    public static String getAlertText() {
        String alertText = null;

        try {
            Alert alert = Driver.getDriver().switchTo().alert();
            alertText = alert.getText();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }

        return alertText;
    }

    /**
     * This method send text to the alert. NoAlertPresentException is handled.
     *
     * @param text
     */
    public static void sendAlertText(String text) {
        try {
            Alert alert = Driver.getDriver().switchTo().alert();
            alert.sendKeys(text);
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method switches to a frame by using name or id
     *
     * @param nameOrId
     */
    public static void switchToFrame(String nameOrId) {
        try {
            Driver.getDriver().switchTo().frame(nameOrId);
        } catch (NoSuchFrameException e) {
            e.printStackTrace();
        }
    }

    /**
     * attempts to click on provided element until given time runs out
     *
     * @param element
     * @param timeout
     */
    public static void clickWithTimeOut(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                waitFor(1);
            }
        }
    }

    /**
     * This method switches to a frame by using an index
     *
     * @param index
     */
    public static void switchToFrame(int index) {
        try {
            Driver.getDriver().switchTo().frame(index);
        } catch (NoSuchFrameException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method switches to a frame by using a WebElement
     *
     * @param element
     */
    public static void switchToFrame(WebElement element) {
        try {
            Driver.getDriver().switchTo().frame(element);
        } catch (NoSuchFrameException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method switches focus to a child window
     *
     */
    public static void switchToChildWindow() {
        String mainWindow = Driver.getDriver().getWindowHandle();
        Set<String> windows = Driver.getDriver().getWindowHandles();

        for (String window : windows) {
            if (!window.equals(mainWindow)) {
                Driver.getDriver().switchTo().window(window);
            }
        }

    }

    /**
     * executes the given JavaScript command on given web element
     *
     * @param element
     */
    public static void executeJScommand(WebElement element, String command) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript(command, element);

    }

    /**
     * executes the given JavaScript command on given web element
     *
     * @param command
     */
    public static void executeJScommand(String command) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript(command);

    }


    /**
     * This method will recover in case of exception after unsuccessful the click,
     * and will try to click on element again.
     *
     * @param by
     * @param attempts
     */


    public static void clickWithWait(By by, int attempts) {
        int counter = 0;
        //click on element as many as you specified in attempts parameter
        while (counter < attempts) {
            try {
                //selenium must look for element again
                clickWithJS(Driver.getDriver().findElement(by));
                //if click is successful - then break
                break;
            } catch (WebDriverException e) {
                //if click failed
                //print exception
                //print attempt
                e.printStackTrace();
                ++counter;
                //wait for 1 second, and try to click again
                waitFor(1);
            }
        }
    }

    /**
     *  checks that an element is present on the DOM of a page. This does not
     *    * necessarily mean that the element is visible.
     * @param by
     * @param time
     */
    public static void waitForPresenceOfElement(By by, long time) {
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(time)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static String getCurrentDate(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    /**
     * this method take format of date/time (yyyy-MM-dd or hh:mm a)(yyyy-MM-dd hh:mm:ss a)
     * two date as a string and return
     * two date in date list as date format
     *  @param format/type
     *  @param date1/time
     *  @param date2/time
     */
    public static List<Date> convertStringToDate(String format, String date1, String date2) throws ParseException {
        List<Date> dates= new ArrayList<>();
        DateFormat df = new SimpleDateFormat(format);
        Date d1 = df.parse(date1);
        Date d2 = df.parse(date2);
        dates.add(d1);
        dates.add(d2);
        return dates;
    }

    /**
     * Method to return the current time stamp in a String
     *
     * @return
     */
    public static String getTimeStamp() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");

        return sdf.format(date.getTime());

    }

    /**
     *  this method bring return message that is not exist in HTML
     * @param inputBox
     * @return
     */
    public static String getPlease_fill_in_this_field_Message(WebElement inputBox){
        String message =inputBox.getAttribute("validationMessage");
        return message;
    }
}
