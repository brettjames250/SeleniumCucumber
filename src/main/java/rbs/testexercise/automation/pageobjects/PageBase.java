package rbs.testexercise.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class PageBase {

    protected final WebDriver driver;


    PageBase(WebDriver driver) {
        this.driver = driver;
    }


    public void moveToElement(WebElement element) {

        Actions actions = new Actions( driver );
        actions.moveToElement( element );
        actions.perform();
    }


    protected void waitForAndClick(By locator) {
        waitForClickabilityOf( locator, 10 );
        find( locator ).click();
    }

    protected void waitForClickabilityOf(By locator, Integer... timeout) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                waitFor( ExpectedConditions.elementToBeClickable( locator ),
                        (timeout.length > 0 ? timeout[0] : null) );
            } catch (StaleElementReferenceException ignored) {
            }

            attempts++;
        }

    }

    protected void type(String inputText, By locator) {
        waitForVisibilityOf( locator, 10 );
        find( locator ).sendKeys( inputText );
    }

    protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor( ExpectedConditions.visibilityOfElementLocated( locator ),
                        (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null) );
                break;
            } catch (StaleElementReferenceException ignored) {
            }
            attempts++;
        }
    }

    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeout) {
        timeout = timeout != null ? timeout : 5;
        WebDriverWait wait = new WebDriverWait( driver, timeout );
        wait.until( condition );
    }


    private WebElement find(By locator) {
        return driver.findElement( locator );
    }


    protected void click(By locator) {

        find( locator ).click();
    }

    protected void clear(By locator) {
        find( locator ).clear();

    }

    protected String getText(By locator) {
        return find( locator ).getText();

    }

    protected String getAttributeValue(By locator) {
        return find( locator ).getAttribute( "value" );

    }

    protected void rightClick(By locator) {

        Actions actions = new Actions( driver );
        WebElement elementLocator = find( locator );
        actions.contextClick( elementLocator ).perform();
    }

    Boolean waitForIsDisplayed(WebElement locator, Integer... timeout) {
        try {
            waitFor( ExpectedConditions.visibilityOfElementLocated( (By) locator ),
                    (timeout.length > 0 ? timeout[0] : null) );
        } catch (org.openqa.selenium.TimeoutException exception) {
            System.out.println( "The expected element: " + locator + "was not found on the page" );
            return false;
        }
        return true;
    }

}
