package com.lazerycode.selenium.page_objects;

import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;

import static com.lazerycode.selenium.tests.base.DriverBase.getDriver;
import static com.lazerycode.selenium.util.AssignDriver.initQueryObjects;

public class DeletePopUpPageObject {
    private Query popUpForm = new Query().defaultLocator(By.cssSelector("div.modal"));
    private Query popUpMessage = new Query().defaultLocator(By.cssSelector("div.modal p"));
    private Query cancelButton = new Query().defaultLocator(By.cssSelector("div.modal form > a"));
    private Query confirmButton = new Query().defaultLocator(By.cssSelector("div.modal form > button"));

    public DeletePopUpPageObject() throws Exception {
        initQueryObjects(this, getDriver());
    }

    public boolean isPopUpOpened() {
        return popUpForm.findWebElement().isDisplayed();
    }

    public String getPopUpMessage() {
        return popUpMessage.findWebElement().getAttribute("textContent").trim();
    }

    public void clickOnCancelButton() {
        cancelButton.findWebElement().click();
    }

    public void clickOnConfirmButton() {
        confirmButton.findWebElement().click();
    }
}
