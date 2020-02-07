package rbs.testexercise.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartSummaryPage extends PageBase {

    private final By proceedfromSummaryButton = By.xpath( "//p[@class='cart_navigation clearfix']//span[contains(text(),'Proceed to checkout')]" );
    private final By processAdressButton = By.xpath( "//button[@name='processAddress']//span[contains(text(),'Proceed to checkout')]" );
    private final By proccessCarrierButton = By.xpath( "//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]" );
    private final By totalCostedPrice = By.id( "total_price" );
    private String totalCostedPriceAmount;


    public CartSummaryPage(WebDriver driver) {
        super( driver );

    }

    public String getTotalCostedPrice() {
        return totalCostedPriceAmount;
    }

    public void proceedfromSummary() {

        totalCostedPriceAmount = getText( totalCostedPrice );

        waitForAndClick( proceedfromSummaryButton );
        waitForAndClick( processAdressButton );


    }

    public void acceptTermsAndConditions() {

        WebElement checkbox = driver.findElement( By.xpath( "//div[@class='checker']//span" ) );

        boolean isCheched = checkbox.getAttribute( "class" ).contains( "checked" );

        if (!isCheched) {

            checkbox.click();
        }

        waitForAndClick( proccessCarrierButton );


    }


}
