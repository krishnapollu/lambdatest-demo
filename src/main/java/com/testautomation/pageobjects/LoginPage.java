package com.testautomation.pageobjects;

import com.testautomation.commons.DriverSetup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends Page{
    
    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void login(){

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(driver.findElement(By.id("inventory_container")).isDisplayed(), 
            "Login Success");
    }

    public void logout() throws InterruptedException{

        Thread.sleep(600);
        driver.findElement(By.xpath("//button[contains(text(), 'Open Menu')]")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();
        Assert.assertTrue(driver.findElement(By.id("user-name")).isDisplayed(), 
            "Logout Success");
    }
}
