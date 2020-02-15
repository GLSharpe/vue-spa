package com.company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverBuilder {

    public WebDriver getDriver(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://skyronic.github.io/vue-spa/#/");
        return driver;
    }
}
