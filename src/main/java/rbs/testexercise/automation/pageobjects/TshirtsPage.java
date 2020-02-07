package rbs.testexercise.automation.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class TshirtsPage extends PageBase {


    private final By addToCartButton = By.xpath( "//a[@title='Add to cart']" );
    private final By proceedToCheckoutButton = By.xpath( "//a[@title='Proceed to checkout']" );


    public TshirtsPage(WebDriver driver) {
        super( driver );
    }

    public void selectTshirtInStock() {

        List<WebElement> availableTshirts = driver.findElements( By.xpath( "//span[@class='available-now']" ) );

        assertThat( availableTshirts ).withFailMessage( "There are no t-shirts in stock" ).isNotEmpty();

        availableTshirts.get( 0 ).click();
        waitForAndClick( addToCartButton );

    }


    public void proceedToCheckout() {
        waitForAndClick( proceedToCheckoutButton );
    }


}
