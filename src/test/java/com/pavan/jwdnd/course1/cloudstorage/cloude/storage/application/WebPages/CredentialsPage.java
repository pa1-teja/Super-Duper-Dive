package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.WebPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CredentialsPage {

    @FindBy(id="newCredentialButton")
    private WebElement newCredential;

    @FindBy(id="credential-url")
    private WebElement url;

    @FindBy(id="credential-username")
    private WebElement username;

    @FindBy(id="credential-password")
    private WebElement password;

    @FindBy(id="credentialSubmit")
    private WebElement credentialSubmit;

    @FindBy(id="eachCredentialUrl")
    private WebElement credentialListItemUrl;

    @FindBy(id="eachCredentialUsername")
    private WebElement credentialListItemUsername;

    @FindBy(id="eachCredentialPassword")
    private WebElement credentialListItemPassword;

    @FindBy(id="credentialEditButton")
    private WebElement credentialListItemEditBtn;

    @FindBy(id="credentialDeleteButton")
    private WebElement credentialListItemDeleteBtn;

    private WebDriver seleniumWebDriver;

    public CredentialsPage(WebDriver seleniumWebDriver) {
        this.seleniumWebDriver = seleniumWebDriver;
        PageFactory.initElements(seleniumWebDriver, this);
    }

    public void CreateCredentialTestCase() {
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].click()", newCredential);
    }

    public void EditCredentialTestCase(String url, String username, String password) {
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].value='" + url + "';", this.url);
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].value='" + username + "';", this.username);
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].value='" + password + "';", this.password);
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].click()", credentialSubmit);
    }

    public String getUrlTestCase() {
        return credentialListItemUrl.getAttribute("innerHTML");
    }

    public String getUsernameTestCase() {
        return credentialListItemUsername.getAttribute("innerHTML");
    }

    public String getPasswordTestCase() {
        return credentialListItemPassword.getAttribute("innerHTML");
    }

    public void viewCredentialTestCase() {
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].click()", credentialListItemEditBtn);
    }

    public String getOriginalPasswordTestCase() {
        return password.getAttribute("value");
    }

    public void deleteCredentialTestCase() {
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].click()", credentialListItemDeleteBtn);
    }

}
