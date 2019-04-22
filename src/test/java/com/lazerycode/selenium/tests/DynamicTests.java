package com.lazerycode.selenium.tests;

import com.applitools.eyes.MatchLevel;
import com.lazerycode.selenium.tests.base.DriverBase;
import org.testng.annotations.Test;

public class DynamicTests extends DriverBase {

    @Test
    public void testDynamicContent(){
        driver.get("https://www.google.com/");
        eyesManager.getEyes().setMatchLevel(MatchLevel.LAYOUT);
        eyesManager.validateWindow();
    }
}
