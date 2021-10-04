package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application;


import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.WebPages.HomePage;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.WebPages.LoginPage;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.WebPages.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestAuthenticationUseCases {

    @LocalServerPort
    private int port;

    private WebDriver seleniumChromeWebDriver;

    private String BASE_URL;

    @BeforeAll
    static void beforeAll(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach(){
        seleniumChromeWebDriver = new ChromeDriver();
        BASE_URL = "http://localhost:" + port;
    }

    @AfterEach
    public void afterEach(){
        if(seleniumChromeWebDriver != null)
            seleniumChromeWebDriver.quit();
    }

    @Test
    public void testInvalidUserUseCase(){
        seleniumChromeWebDriver.get(BASE_URL+"/login");

        LoginPage loginPage = new LoginPage(seleniumChromeWebDriver);

        loginPage.testLoginCase("username", "password");
        Assertions.assertEquals("Login", seleniumChromeWebDriver.getTitle());

        Assertions.assertTrue(loginPage.testInvalidCase());
    }

    @Test
    public void testSuccessfulLoginUseCase(){

        WebDriverWait wait = new WebDriverWait(seleniumChromeWebDriver, 10);
        String username = "kona";
        String password = "sai";
        String fstName = "pavan";
        String lstName = "teja";
        seleniumChromeWebDriver.get(BASE_URL + "/signup");

         SignUpPage signupPage = new SignUpPage(seleniumChromeWebDriver);
        signupPage.signUpTestCase(fstName, lstName, username, password);
        seleniumChromeWebDriver.get(BASE_URL + "/login");


        LoginPage loginPage = new LoginPage(seleniumChromeWebDriver);
        loginPage.testLoginCase(username, password);
        Assertions.assertEquals("Home", seleniumChromeWebDriver.getTitle());


        HomePage homePage = new HomePage(seleniumChromeWebDriver);
        homePage.logoutTestCase();
        wait.until(ExpectedConditions.titleContains("Login"));
        Assertions.assertTrue(loginPage.testLogoutCase());


        seleniumChromeWebDriver.get(BASE_URL + "/home");
        Assertions.assertNotEquals("Home", seleniumChromeWebDriver.getTitle());
    }
}
