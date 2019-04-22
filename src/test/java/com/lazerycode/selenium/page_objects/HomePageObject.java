package com.lazerycode.selenium.page_objects;

import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.lazerycode.selenium.tests.base.DriverBase.getDriver;
import static com.lazerycode.selenium.util.AssignDriver.initQueryObjects;
import static org.testng.Assert.assertTrue;

public class HomePageObject {

    private Query title = new Query().defaultLocator(By.cssSelector("div.page-header > h1"));
    private Query idList = new Query().defaultLocator(By.cssSelector("table > tbody > tr > td:nth-child(1)"));
    private Query titleList = new Query().defaultLocator(By.cssSelector("table > tbody > tr > td:nth-child(2)"));
    private Query dateList = new Query().defaultLocator(By.cssSelector("table > tbody > tr > td:nth-child(3)"));
    private Query priorityList = new Query().defaultLocator(By.cssSelector("table > tbody > tr > td:nth-child(4)"));
    private Query statusList = new Query().defaultLocator(By.cssSelector("table > tbody > tr > td:nth-child(5)"));
    private Query editButtons = new Query().defaultLocator(By.cssSelector("table > tbody > tr > td:nth-child(6) > a.btn-primary"));
    private Query deleteButtons = new Query().defaultLocator(By.cssSelector("table > tbody > tr > td:nth-child(6) > a.btn-danger"));
    private Query totalCount = new Query().defaultLocator(By.cssSelector("table > tfoot > tr > td:nth-child(1) > div > span"));
    private Query toDoCount = new Query().defaultLocator(By.cssSelector("table > tfoot > tr > td:nth-child(2) > div > span"));
    private Query doneCount = new Query().defaultLocator(By.cssSelector("table > tfoot > tr > td:nth-child(3) > div > span"));

    public HomePageObject() throws Exception {
        initQueryObjects(this, getDriver());
    }

    public boolean isHomePageDisplayed() {
        try {
            return getDriver().getCurrentUrl().equals("https://ancient-taiga-22967.herokuapp.com/user/todos")
                    && title.findWebElement().isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private String getElementContent(Query element) {
        return element.findWebElement().getAttribute("textContent");
    }

    private String getElementContent(Query elementList, int itemNum) {
        List<WebElement> elements = elementList.findWebElements();
        assertTrue(itemNum >= 0 && elements.size() > itemNum, "Bad index itemNum: " + itemNum);
        return elements.get(itemNum).getAttribute("textContent").trim();
    }

    private void clickOnElement(Query elementList, int itemNum) {
        List<WebElement> elements = elementList.findWebElements();
        assertTrue(itemNum >= 0 && elements.size() > itemNum, "Bad index itemNum: " + itemNum);
        elements.get(itemNum).click();
    }

    public String getId(int itemNum) {
        return getElementContent(idList, itemNum);
    }

    public String getTitle(int itemNum) {
        return getElementContent(titleList, itemNum);
    }

    public String getDate(int itemNum) {
        return getElementContent(dateList, itemNum);
    }

    public String getPriority(int itemNum) {
        return getElementContent(priorityList, itemNum);
    }

    public String getStatus(int itemNum) {
        return getElementContent(statusList, itemNum);
    }

    public String getTotalCount() {
        return getElementContent(totalCount);
    }

    public String getToDoCount() {
        return getElementContent(toDoCount);
    }

    public String getDoneCount() {
        return getElementContent(doneCount);
    }

    public int getTableItemsCount() {
        return titleList.findWebElements().size();
    }

    public void clickOnEditButton(int itemNum) {
        clickOnElement(editButtons, itemNum);
    }

    public void clickOnDeleteButton(int itemNum) {
        clickOnElement(deleteButtons, itemNum);
    }
}
