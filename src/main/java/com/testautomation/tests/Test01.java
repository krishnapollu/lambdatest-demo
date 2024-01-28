package com.testautomation.tests;

import com.testautomation.commons.DriverSetup;
import com.testautomation.pageobjects.LoginPage;

import org.testng.annotations.Test;

public class Test01 extends DriverSetup {
    
    @Test
    public void testCase01() throws InterruptedException{

        LoginPage lp = new LoginPage(driver);
        lp.login();
        lp.logout();
    }
}
