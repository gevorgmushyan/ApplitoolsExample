package com.lazerycode.selenium.tests.base;

import com.lazerycode.selenium.config.DriverFactory;
import com.lazerycode.selenium.entities.UserAccount;
import com.lazerycode.selenium.listeners.ScreenshotListener;
import com.lazerycode.selenium.page_objects.CreateNewTodoPageObject;
import com.lazerycode.selenium.page_objects.HomePageObject;
import com.lazerycode.selenium.page_objects.LogInPageObject;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Listeners(ScreenshotListener.class)
public class DriverBase {
    protected WebDriver driver;
    protected static EyesManager eyesManager;

    private static List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<DriverFactory>());
    private static ThreadLocal<DriverFactory> driverFactoryThread;

    @AfterMethod(alwaysRun = true)
    public static void clearCookies() {
        try {
            driverFactoryThread.get().getStoredDriver().manage().deleteAllCookies();
        } catch (Exception ignored) {
            System.out.println("Unable to clear cookies, driver object is not viable...");
        }
    }

    @BeforeClass
    public void beforeClass() throws Exception {
        driver = getDriver();
        driver.manage().window().maximize();
        eyesManager = new EyesManager(driver, "The Internet");
    }

    @AfterClass
    public static void tearDown() {
        eyesManager.abort();
    }

    @BeforeSuite(alwaysRun = true)
    public static void instantiateDriverObject() {
        driverFactoryThread = ThreadLocal.withInitial(() -> {
            DriverFactory driverFactory = new DriverFactory();
            webDriverThreadPool.add(driverFactory);
            return driverFactory;
        });
    }

    public static RemoteWebDriver getDriver() throws Exception {
        return driverFactoryThread.get().getDriver();
    }

    @AfterSuite(alwaysRun = true)
    public static void closeDriverObjects() {
        for (DriverFactory driverFactory : webDriverThreadPool) {
            driverFactory.quitDriver();
        }
    }
}