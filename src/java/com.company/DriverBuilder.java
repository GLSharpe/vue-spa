package com.company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverBuilder {

    public WebDriver getDriver(){
        System.getProperty("Dwebdriver.chrome.driver","C:\\Users\\George.Sharpe\\Documents\\Selenium\\chromedirver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://skyronic.github.io/vue-spa/#/");
        return driver;
    }
}
