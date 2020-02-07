package rbs.testexercise.automation.testrunner;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import rbs.testexercise.automation.pageobjects.*;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

public class Steps {


    private TshirtsPage tshirtsPage;
    private AccountPage accountPage;
    private HomePage homePage;
    private WebDriver driver;
    private PersonalInfoPage personalInfoPage;
    private CartSummaryPage cartSummaryPage;

    @Before
    public void setUp() {

        String operatingSystem = System.getProperty( "os.name" ).toLowerCase();
        setDriverProperty( operatingSystem );


        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get( "http://automationpractice.com" );
        homePage = new HomePage( driver );

    }


    @Given("^a user logs into their account$")
    public void userLogsIntoAccount() {

        homePage.loginAsUser();

    }


    @When("^user navigates to tshirt category page$")
    public void navigatesToTshirtPage() {

        homePage.navigateToCategoryPage();


    }

    @When("^adds a tshirt to the basket$")
    public void addATshirtToTheBasket() {

        tshirtsPage = new TshirtsPage( driver );
        tshirtsPage.selectTshirtInStock();


    }

    @Then("^proceeds through checkout$")
    public void proceedThroughCheckout() {

        tshirtsPage.proceedToCheckout();

        cartSummaryPage = new CartSummaryPage( driver );

        cartSummaryPage.proceedfromSummary();
        cartSummaryPage.acceptTermsAndConditions();

    }

    @Then("^makes payment by \"([^\"]*)\"$")
    public void makesPaymentBy(String paymentType) {

        PaymentPage paymentPage = new PaymentPage( driver );

        paymentPage.selectPaymentType( paymentType );
        paymentPage.confirmOrder();


    }


    @Then("^user first name is changed$")
    public void usersFirstNameIsChanged() {

        personalInfoPage = new PersonalInfoPage( driver );
        personalInfoPage.changeFirstName();
        personalInfoPage.confirmAndSave();


    }

    @And("^the name change is verified$")
    public void nameChangeIsVerified() {

        homePage.navigateToHomePage();
        homePage.navigateToMyAccount();
        accountPage.clickPersonalInfo();

        assertThat( personalInfoPage.getOldName() ).withFailMessage( "The first name has not been changed" )
                .isNotEqualTo( personalInfoPage.getNewName() );
    }

    @Then("^the order is verified$")
    public void orderIsVerified() {

        accountPage = new AccountPage( driver );

        accountPage.clickOrders();
        accountPage.verifyOrderReference();

        assertTrue( accountPage.verifyOrderReference() );
        assertThat( cartSummaryPage.getTotalCostedPrice() ).withFailMessage( "The costed price does not match the final order price" )
                .isEqualTo( accountPage.getFinalOrderPrice() );


    }

    @And("^user navigates to personal information$")
    public void userNavigatesToPersonalInformation() {

        accountPage = new AccountPage( driver );
        accountPage.clickPersonalInfo();
    }

    @When("^user navigates to my account$")
    public void userNavigatesToMyAccount() {

        driver.get( "http://automationpractice.com" );
        homePage.navigateToMyAccount();
    }

    @After
    public void afterSteps() {
        driver.quit();
    }

    private void setDriverProperty(String os) {

        if (os.contains( "mac" )) {

            System.setProperty( "webdriver.chrome.driver", System.getProperty( "user.dir" ) + "\\chromedriver" );

        } else {

            System.setProperty( "webdriver.chrome.driver", System.getProperty( "user.dir" ) + "\\chromedriver.exe" );

        }

    }


}
