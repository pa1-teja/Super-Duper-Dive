package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application;

import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.WebPages.LoginPage;
import com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.WebPages.NotesPage;
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
public class TestNotesUseCases {

    @LocalServerPort
    private int port;

    private WebDriver seleniumChromeWebDriver;

    private String BASE_URL;
    private SignUpPage signUpPage;
    private LoginPage loginPage;
    private NotesPage notesPage;


    String username = "kona";
    String password = "sai";
    String fstName = "pavan";
    String lstName = "teja";
    String title = "Test";
    String description = "TESTTTTTT";


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
        notesPage = new NotesPage(seleniumChromeWebDriver);

        SecureRandom random = new SecureRandom();

        String username = String.valueOf(random.nextInt(1000));
        String password = String.valueOf(random.nextInt(1000));
        seleniumChromeWebDriver
                .get(BASE_URL + "/signup");
        signUpPage.signUpTestCase (fstName, lstName, username, password);
        seleniumChromeWebDriver
                .get(BASE_URL + "/login");
        loginPage.testLoginCase(username, password);
        seleniumChromeWebDriver
                .get(BASE_URL + "/home?tabOption=notes");

    }

    @AfterEach
    public void afterEach() {
        if (seleniumChromeWebDriver
                != null) {

            seleniumChromeWebDriver
                    .quit();
        }
    }


    @Test
    public void testNewNote() {
        notesPage.CreateNoteTestCase(title, description);


        seleniumChromeWebDriver.get(BASE_URL + "/home?tabOption=notes");
        Assertions.assertEquals(title, notesPage.getTitleTestCase());
        Assertions.assertEquals(description, notesPage.getDescriptionTestCase());
    }

    //Write a test that edits an existing note and verifies that the changes are displayed.
    @Test
    public void testEditNote() {

        notesPage.CreateNoteTestCase(title, description);

        seleniumChromeWebDriver
                .get(BASE_URL + "/home?tabOption=notes");
        notesPage.EditNoteTestCase ("title_1234567890", "description-QWERTY");


        seleniumChromeWebDriver
                .get(BASE_URL + "/home?tabOption=notes");
        Assertions.assertEquals(title, notesPage.getTitleTestCase());
        Assertions.assertEquals(description, notesPage.getDescriptionTestCase());

    }

    //Write a test that deletes a note and verifies that the note is no longer displayed.
    @Test
    public void testDeleteNote() {

        notesPage.CreateNoteTestCase(title, description);


        seleniumChromeWebDriver.get(BASE_URL + "/home?tabOption=notes");
        notesPage.DeleteNoteTestCase();


        seleniumChromeWebDriver.get(BASE_URL + "/home?tabOption=notes");

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            notesPage.getTitleTestCase();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            notesPage.getDescriptionTestCase();
        });

    }

}
