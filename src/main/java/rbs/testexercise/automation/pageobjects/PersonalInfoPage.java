package rbs.testexercise.automation.pageobjects;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalInfoPage extends PageBase {

    private final By firstNameField = By.id( "firstname" );
    private final By oldPassword = By.id( "old_passwd" );
    private final By saveButton = By.xpath( "//span[contains(text(),'Save')]" );

    private String oldName;
    private String newName;


    public PersonalInfoPage(WebDriver driver) {
        super( driver );

    }

    public String getOldName() {
        return oldName;
    }

    public void changeFirstName() {

        oldName = getAttributeValue( firstNameField );


        clear( firstNameField );
        type( generateNewFirstName(), firstNameField );

    }

    public void confirmAndSave() {

        type( "CucumberSalad47", oldPassword );
        waitForAndClick( saveButton );


    }

    public String getNewName() {

        return newName = getAttributeValue( firstNameField );


    }


    private String generateNewFirstName() {

        Person person = Fairy.create().person();
        //  Person person = fairy.person();
        return newName = person.getFirstName();

    }
}
