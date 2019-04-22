package com.lazerycode.selenium.page_objects;

import com.lazerycode.selenium.entities.Priority;
import com.lazerycode.selenium.entities.ToDoItem;
import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import static com.lazerycode.selenium.tests.base.DriverBase.getDriver;
import static com.lazerycode.selenium.util.AssignDriver.initQueryObjects;

public class CreateNewTodoPageObject {

    private Query titleInput = new Query().defaultLocator(By.id("title"));
    private Query dateInput = new Query().defaultLocator(By.id("dueDate"));
    private Query prioritySelect = new Query().defaultLocator(By.id("priority"));
    private Query createButton = new Query().defaultLocator(By.cssSelector("#createTodoForm button.btn.btn-primary"));
    private Query cancelButton = new Query().defaultLocator(By.cssSelector("#createTodoForm button:nth-child(2)"));

    public CreateNewTodoPageObject() throws Exception {
        initQueryObjects(this, getDriver());
    }

    public void setTitle(String title) {
        titleInput.findWebElement().clear();
        titleInput.findWebElement().sendKeys(title);
    }

    public void setDate(String date) throws Exception {
        getDriver().executeScript("arguments[0].value = '" + date + "'",
                dateInput.findWebElement());
    }

    public void setPriority(Priority priority) {
        Select select = new Select(prioritySelect.findWebElement());
        select.selectByValue(priority.name());
    }

    public void clickOnCreateButton() {
        createButton.findWebElement().click();
    }

    public void clickOnCancelButton() {
        cancelButton.findWebElement().click();
    }

    public ToDoItem createTodo(ToDoItem item) throws Exception {
        setTitle(item.getTitle());
        setDate(item.getDate());
        setPriority(item.getPriority());

        clickOnCreateButton();
        return item;
    }

    public ToDoItem createTodo() throws Exception{
        ToDoItem item = ToDoItem.getToDoItem();
        return createTodo(item);
    }
}
