package com.testautomation.commons;

import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class DriverSetup {
    
    protected RemoteWebDriver driver;

    @BeforeTest
    @Parameters({"appURL", "LTusername", "LTaccessKey"})
    public void setup(String app_url, String lt_username, String lt_accessKey){

        try{
            TestParameters.lt_username = System.getProperty("LTusername");
            TestParameters.lt_accessKey = System.getProperty("LTaccessKey");
        }catch(Exception e){
            TestParameters.lt_username = lt_username;
            TestParameters.lt_accessKey = lt_accessKey;
        }

        System.out.println(TestParameters.lt_username);
        System.out.println(TestParameters.lt_accessKey);

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("121.0");
        
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", TestParameters.lt_username);
        ltOptions.put("accessKey", TestParameters.lt_accessKey);
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("build", "test-build"+ System.currentTimeMillis());
        ltOptions.put("project", "lambdatest-demo");
        ltOptions.put("smartUI.project", "lambdatest-demo");
        ltOptions.put("console", "true");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        
        browserOptions.setCapability("LT:Options", ltOptions);
        
        try{
            driver = new RemoteWebDriver(new URL("https://" + TestParameters.lt_username + ":" + TestParameters.lt_accessKey + "@hub.lambdatest.com/wd/hub"), browserOptions);
            driver.get(app_url);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }


}
