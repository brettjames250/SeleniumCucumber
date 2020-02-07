package rbs.testexercise.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import rbs.testexercise.automation.helpers.Order;

public class PaymentPage extends PageBase {

    private final By bankWireButton = By.className( "bankwire" );
    private final By chequeButton = By.className( "cheque" );
    private final By confirmOrderButton = By.xpath( "//button[@class='button btn btn-default button-medium']" );
    private final By orderDetails = By.className( "box" );

    private static Order order;


    public PaymentPage(WebDriver driver) {
        super( driver );

    }

    public void selectPaymentType(String paymentType) {

        switch (paymentType) {

            case "bank-wire":
                waitForAndClick( bankWireButton );
                break;

            case "cheque":
                waitForAndClick( chequeButton );
                break;

        }


    }

    public void confirmOrder() {

        waitForAndClick( confirmOrderButton );
        logOrderReference();



    }

    private void logOrderReference() {

        waitForVisibilityOf( orderDetails );
        String deets = driver.findElement( By.className( "box" ) ).getText();

        int beforeRef = deets.indexOf( "order reference " );
        int afterRef = deets.indexOf( " in the subject" );

        String orderReference = deets.substring( beforeRef + 16, afterRef );

        order = new Order( orderReference );


    }

  public  static Order getOrder() {
        return order;
    }


}
