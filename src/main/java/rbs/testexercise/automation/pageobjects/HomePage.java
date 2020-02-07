package rbs.testexercise.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends PageBase {

    private final By signInButton = By.className( "login" );
    private final By emailField = By.id( "email" );
    private final By passwordField = By.id( "passwd" );
    private final By submitLoginButton = By.id( "SubmitLogin" );
    private static final By myAccountButton = By.className( "account" );
    private final By tshirtsPageButton = By.xpath( "//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']//li[3]/a[@title='T-shirts']" );
    private final By homeIcon = By.className( "icon-home" );

    public HomePage(WebDriver driver){
        super( driver );
    }

    public void loginAsUser() {

        waitForAndClick( signInButton );
        type( "cucumber26@gmail.com", emailField );
        type( "CucumberSalad47", passwordField );
        waitForAndClick( submitLoginButton);
        waitForVisibilityOf( myAccountButton, 10 );
        navigateToHomePage();


    }

    public void navigateToMyAccount( ){

        waitForAndClick( myAccountButton );
    }

    public void navigateToCategoryPage( ){

        waitForAndClick( tshirtsPageButton );
    }

    public void navigateToHomePage( ){

        waitForAndClick( homeIcon );
        waitForVisibilityOf( myAccountButton );


    }


}
