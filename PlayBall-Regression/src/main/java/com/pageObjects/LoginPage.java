package com.pageObjects;

import com.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BaseClass {

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(name = "email")
    WebElement emailField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    WebElement enterButton;

    public void visitUrl(String website){
        driver.get(website);
        log.info("Visited "+website);
    }

    public void loginToThePortal(){
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(prop.getProperty("email"));
        log.info("Entered Email: "+prop.getProperty("email"));

        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(prop.getProperty("password"));
        log.info("Entered Password");

        wait.until(ExpectedConditions.elementToBeClickable(enterButton));
        enterButton.click();
        log.info("Clicked on Enter Button");
    }
}
