package com.StepDefinitions;

import com.Utils.Common;
import com.base.BaseClass;
import com.pageObjects.CountriesPage;
import com.pageObjects.DashboardPage;
import com.pageObjects.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class countriesTestStepDefinitions extends BaseClass {

    LoginPage loginPage = new LoginPage(driver);
    Common common = new Common();
    DashboardPage dashboardPage = new DashboardPage(driver);
    CountriesPage countriesPage = new CountriesPage(driver);

    @Given("Visit website {string}")
    public void visitWebsite(String website) {
        loginPage.visitUrl(website);
    }

    @When("Login to the portal")
    public void loginToThePortal() {
        loginPage.loginToThePortal();
    }

    @Then("Verify that Successfully logged in to the portal")
    public void verifyThatSuccessfullyLoggedInToThePortal() {
        common.assertionTrue(dashboardPage.isDashboardTitleDisplayed(), "Dashboard page is displayed");
    }

    @When("Clicks on {string}")
    public void clicksOn(String menuOption) {
        dashboardPage.clickOnMenu(menuOption);
    }

    @Then("Verify that {string} page is displayed")
    public void verifyThatPageIsDisplayed(String menuOption) {
        common.assertionTrue(dashboardPage.isPageTitleDisplayed(menuOption), "Page title is displayed");
    }

    @When("Hide {string} Country")
    public void hideCountry(String country) {
        countriesPage.hideCountry(country);
    }

    @Then("Verify that {string} Country shows {string} status")
    public void verifyThatCountryShowsStatus(String country, String status) {
        common.assertionTrue(countriesPage.isCountryDisplayedWithStatus(country, status), "Country with it's correct status is displayed");
    }
}
