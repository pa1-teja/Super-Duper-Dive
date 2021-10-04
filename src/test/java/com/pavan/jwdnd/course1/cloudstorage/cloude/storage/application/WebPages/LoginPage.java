package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.WebPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "login")
    private WebElement loginButton;

    @FindBy(id = "errorMessage")
    private WebElement errorMessage;

    @FindBy(id = "logoutMessage")
    private WebElement logoutMessage;

    private final WebDriver seleniumWebDriver;

    public LoginPage(WebDriver seleniumWebDriver) {
        this.seleniumWebDriver = seleniumWebDriver;

        PageFactory.initElements(seleniumWebDriver,this);
    }

    public void testLoginCase(String username, String password) {
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].value='" +username + "';", usernameField);
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].value='" +password + "';", passwordField);
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].click()", loginButton);
    }



    public boolean testInvalidCase() {
        return errorMessage.isDisplayed();
    }

    public boolean testLogoutCase() {
        return logoutMessage.isDisplayed();
    }

}
