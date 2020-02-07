package rbs.testexercise.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountPage extends PageBase {

    private final By myOrders = By.xpath( "//span[contains(text(),'Order history and details')]" );
    private final By personalInfo = By.xpath( "//span[contains(text(),'My personal information')]" );
    private String finalOrderPrice;


    public AccountPage(WebDriver driver) {
        super( driver );

    }


    public void clickPersonalInfo() {
        waitForAndClick( personalInfo );
    }

    public void clickOrders() {
        waitForAndClick( myOrders );
    }

    public boolean verifyOrderReference() {

        String orderReference = PaymentPage.getOrder().getOrderReference();
        WebElement orderRef = driver.findElement( By.xpath( "//a[contains(text(),'" + orderReference + "')]" ) );
        orderRef.click();

        return orderRef.isDisplayed();


    }

    public String getFinalOrderPrice() {

        By finalOrderPriceElement = By.xpath( "//tr[@class='totalprice item']//span" );

        waitForVisibilityOf( finalOrderPriceElement, 10 );

        return finalOrderPrice = getText( finalOrderPriceElement );
    }
}
