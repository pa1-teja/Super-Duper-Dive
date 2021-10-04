package com.pavan.jwdnd.course1.cloudstorage.cloude.storage.application.WebPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotesPage {

    @FindBy(id="newNoteButton")
    private WebElement newNote;

    @FindBy(id="note-title")
    private WebElement noteTitle;

    @FindBy(id="note-description")
    private WebElement description;

    @FindBy(id="saveChangesButton")
    private WebElement submit;

    @FindBy(id="eachNoteTitle")
    private WebElement listItemNoteTitle;

    @FindBy(id="eachNoteDesc")
    private WebElement listItemNoteDescription;

    @FindBy(id="noteEditButton")
    private WebElement listItemNoteEditBtn;

    @FindBy(id="noteDeleteButton")
    private WebElement listItemNoteDeleteBtn;

    private final WebDriver seleniumWebDriver;

    public NotesPage(WebDriver seleniumWebDriver) {
        this.seleniumWebDriver = seleniumWebDriver;
        PageFactory.initElements(seleniumWebDriver, this);
    }

    public void CreateNoteTestCase(String title, String description) {
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].click()", newNote);
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].value='" + title + "';", noteTitle);
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].value='" + description + "';", this.description);
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].click()", submit);
    }

    public String getTitleTestCase() {
        return listItemNoteTitle.getAttribute("innerHTML");
    }

    public String getDescriptionTestCase() {
        return listItemNoteDescription.getAttribute("innerHTML");
    }

    public void EditNoteTestCase(String title, String description) {
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].click()", listItemNoteEditBtn);
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].value='" + title + "';", listItemNoteTitle);
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].value='" + description + "';", listItemNoteDescription);
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].click()", submit);
    }

    public void DeleteNoteTestCase() {
        ((JavascriptExecutor) seleniumWebDriver).executeScript("arguments[0].click()", listItemNoteDeleteBtn);
    }
}
