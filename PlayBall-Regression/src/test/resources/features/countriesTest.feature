Feature: Countries and cities tests

  @hide-country
  Scenario: [Positive] Should be possible hide the Country
    Given Visit website "https://www.playball-qa.fun/"
    When Login to the portal
    Then Verify that Successfully logged in to the portal
    When Clicks on "Countries & cities"
    Then Verify that "Countries" page is displayed
    When Hide "Qatar" Country
    Then Verify that "Qatar" Country shows "Hidden" status