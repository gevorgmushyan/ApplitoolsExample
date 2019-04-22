package com.lazerycode.selenium.page_objects;

import com.lazerycode.selenium.tests.base.DriverBase;
import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;

import static com.lazerycode.selenium.util.AssignDriver.initQueryObjects;

public class LogInPageObject {

    private Query emailInput = new Query().defaultLocator(By.id("email"));
    private Query passwordInput = new Query().defaultLocator(By.id("password"));
    private Query signInButton = new Query().defaultLocator(By.cssSelector("#loginForm  div.form-actions > button"));

    private Query alertMessage = new Query().defaultLocator(By.cssSelector("div.alert.alert-error > strong"));
    private Query emailError = new Query().defaultLocator(By.cssSelector("#email\\2e errors"));
    private Query passError = new Query().defaultLocator(By.cssSelector("#password\\2e errors"));

    private Query registerLink = new Query().defaultLocator(By.cssSelector("#loginForm a"));


    public LogInPageObject() throws Exception {
        initQueryObjects(this, DriverBase.getDriver());
    }

    public void setEmail(String email) {
        emailInput.findWebElement().clear();
        emailInput.findWebElement().sendKeys(email);
    }

    public void setPassword(String password) {
        passwordInput.findWebElement().clear();
        passwordInput.findWebElement().sendKeys(password);
    }

    public void clickOnSingInButton() {
        signInButton.findWebElement().click();
    }

    private String getElementText(Query element) {
        return element.findWebElement().getAttribute("textContent");
    }

    public String getAlertErrorMessage() {
        return getElementText(alertMessage);
    }

    public String getEmailErrorMessage() {
        return getElementText(emailError);
    }

    public String getPasswordErrorMessage() {
        return getElementText(passError);
    }

    private boolean doseElementDisplayed(Query element) {
        if (element.findWebElements().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean doseAlertErrorMessageDisplayed(String message) {
        return doseElementDisplayed(alertMessage);
    }

    public boolean doseEmailErrorMessageDisplayed() {
        return doseElementDisplayed(emailError);
    }

    public boolean dosePasswordErrorMessageDisplayed() {
        return doseElementDisplayed(passError);
    }

    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickOnSingInButton();
    }
}
