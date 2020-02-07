Feature: Web Automation Exercise

  Scenario: Order a T-shirt and verify the order
    Given a user logs into their account
    When user navigates to tshirt category page
    And adds a tshirt to the basket
    Then proceeds through checkout
    And makes payment by "bank-wire"
    And user navigates to my account
    And the order is verified

  Scenario: Update a users personal information
    Given a user logs into their account
    When user navigates to my account
    And user navigates to personal information
    Then user first name is changed
    And the name change is verified