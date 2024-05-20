package com.pageObjects;

import com.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CountriesPage extends BaseClass {

    public CountriesPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//button[text()=\"Hide\"]")
    WebElement hideCountry;

    @FindBy(css = ".table__row.table__title")
    WebElement tableTitle;

    @FindBy(xpath = "//tbody/tr[1]")
    WebElement tableFirstRow;

    public void hideCountry(String country){
        List<WebElement> allCountries = driver.findElements(By.xpath("//tbody/tr/td[1]"));
        for(int i=0;i<allCountries.size();i++){
            if(allCountries.get(i).getText().equalsIgnoreCase(country)){
                String status = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[9]")).getText();
                if(status.equalsIgnoreCase("Opened")){
                    wait.until(ExpectedConditions.visibilityOf(hideCountry));
                    wait.until(ExpectedConditions.elementToBeClickable(hideCountry));
                    hideCountry.click();
                    log.info("Clicked On Hide Country");
                }else {
                    log.info("Country already Hidden");
                }
            }
        }
    }

    public boolean isCountryDisplayedWithStatus(String country, String status){
        wait.until(ExpectedConditions.visibilityOf(tableTitle));
        wait.until(ExpectedConditions.visibilityOf(tableFirstRow));
        List<WebElement> allCountries = driver.findElements(By.xpath("//tbody/tr/td[1]"));
        for(int i=0;i<allCountries.size();i++) {
            if (allCountries.get(i).getText().equalsIgnoreCase(country)) {
                String statusActual = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[9]")).getText();
                return statusActual.equalsIgnoreCase(status);
            }
        }
        return false;
    }
}
