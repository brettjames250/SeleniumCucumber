# brettjames-webautomation

## About
This test automation project contains BDD scenarios that test the user interface for the test website. The framework is based on Selenium Webdriver, CucumberJVM and utilises the Page Object Model.

- Test website - http://automationpractice.com/ 

## Usage
To run the tests from the IDE command line

```
mvn clean test
```
## Test Reports
Upon test completion, basic Cucumber reports can be accessed in the target folder

```
target/cucumber-reports/index.html
```
![Cucumber Report](cukereport.png)


## Framework Development
- Utilise Cucumber Datatables to parameterise tests - e.g purchase different products, pay by both methods
- Implementing Allure reports - useful as test volume and variety increases.
- Serenity BDD - detailed documentation of user stories / capabilities and features
- Full verification of each order - from costing to purchase


## Further Scenarios
- attempting login with invalid credentials
- costing multiple items, and removing from the basket
- test product page filters
- search bar testing
