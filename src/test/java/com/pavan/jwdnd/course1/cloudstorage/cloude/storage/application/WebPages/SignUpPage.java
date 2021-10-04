package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.WebPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {

    @FindBy(name = "firstName")
    private WebElement firstName;

    @FindBy(name = "lastName")
    private WebElement lastName;

    @FindBy(name = "username")
    private WebElement userName;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name = "signUp")
    private WebElement submit;

    private final WebDriver seleniumWebDriver;

    public SignUpPage(WebDriver seleniumWebDriver) {
        this.seleniumWebDriver = seleniumWebDriver;
        PageFactory.initElements(seleniumWebDriver,this);
    }

    public void signUpTestCase(String firstName, String lastName, String username, String password) {
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].value='" + firstName + "';", this.firstName);
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].value='" + lastName + "';", this.lastName);
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].value='" + username + "';", this.userName);
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].value='" + password + "';", this.password);
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].click()",submit);
    }
}
