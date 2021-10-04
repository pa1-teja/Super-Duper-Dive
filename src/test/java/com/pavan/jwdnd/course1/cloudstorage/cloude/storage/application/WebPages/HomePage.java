package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.WebPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(id="logout")
    private WebElement logout;

    private final WebDriver seleniumWebDriver;

    public HomePage(WebDriver seleniumWebDriver) {
        this.seleniumWebDriver = seleniumWebDriver;
        PageFactory.initElements(seleniumWebDriver, this);
    }

    public void logoutTestCase() {
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].click()", logout);
    }
}
