package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application;


import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.WebPages.CredentialsPage;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.WebPages.LoginPage;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.WebPages.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.security.SecureRandom;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCredentialsUseCases {

    @LocalServerPort
    private int port;

    private WebDriver seleniumChromeWebDriver;

    private String BASE_URL;
    private SignUpPage signUpPage;
    private LoginPage loginPage;
    private CredentialsPage credentialsPage;


    String username = "kona";
    String password = "sai";
    String fstName = "pavan";
    String lstName = "teja";
    private String url = "http://localhost:8080/login";

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        seleniumChromeWebDriver = new ChromeDriver();
        BASE_URL = "http://localhost:" + port;

        signUpPage = new SignUpPage(seleniumChromeWebDriver);
        loginPage = new LoginPage(seleniumChromeWebDriver);
        credentialsPage = new CredentialsPage(seleniumChromeWebDriver);


        SecureRandom random = new SecureRandom();

        String username = String.valueOf(random.nextInt(1000));
        String password = String.valueOf(random.nextInt(1000));
        seleniumChromeWebDriver.get(BASE_URL + "/signup");
        signUpPage.signUpTestCase (fstName, lstName, username, password);
        seleniumChromeWebDriver.get(BASE_URL + "/login");
        loginPage.testLoginCase(username, password);
        seleniumChromeWebDriver.get(BASE_URL + "/home?tabOption=credentials");

    }

    @AfterEach
    public void afterEach() {
        if (seleniumChromeWebDriver != null) {

            seleniumChromeWebDriver.quit();
        }
    }


    @Test
    public void testNewCredential() {

        credentialsPage.CreateCredentialTestCase();
        credentialsPage.EditCredentialTestCase(url, username, password);

        seleniumChromeWebDriver.get(BASE_URL + "/home?tabOption=credentials");
        Assertions.assertEquals(url, credentialsPage.getUrlTestCase());
        Assertions.assertEquals(username, credentialsPage.getUsernameTestCase());
        Assertions.assertNotEquals(password, credentialsPage.getPasswordTestCase());
    }


    @Test
    public void testEditCredential() {
        credentialsPage.CreateCredentialTestCase();
        credentialsPage.EditCredentialTestCase(url, username, password);

        seleniumChromeWebDriver.get(BASE_URL + "/home?tabOption=credentials");
        credentialsPage.viewCredentialTestCase();
        Assertions.assertEquals(password, credentialsPage.getOriginalPasswordTestCase());

        username += "New";
        password += "New";
        credentialsPage.EditCredentialTestCase(url, username, password);

        seleniumChromeWebDriver.get(BASE_URL + "/home?tabOption=credentials");
        Assertions.assertEquals(username, credentialsPage.getUsernameTestCase());
        Assertions.assertNotEquals(password, credentialsPage.getPasswordTestCase());

    }

    //Write a test that deletes an existing set of credentials and verifies that the credentials are no longer displayed.
    @Test
    public void testDeleteCredential() {

        credentialsPage.CreateCredentialTestCase();
        credentialsPage.EditCredentialTestCase(url, username, password);


        seleniumChromeWebDriver.get(BASE_URL + "/home?tabOption=credentials");
        credentialsPage.deleteCredentialTestCase();


        seleniumChromeWebDriver.get(BASE_URL + "/home?tabOption=credentials");
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            credentialsPage.getUrlTestCase();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            credentialsPage.getUsernameTestCase();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            credentialsPage.getPasswordTestCase();
        });
    }
}
