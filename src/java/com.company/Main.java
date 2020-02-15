package com.company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Main {
    static PortalHomepage portalHomepage = new PortalHomepage();
    static DriverBuilder driverBuilder = new DriverBuilder();

    public static void main(String[] args) {
        WebDriver driver = driverBuilder.getDriver();
        List<String> x = portalHomepage.getProductNames(driver);
        System.out.println(x);
        driver.close();
        driver.quit();

    }
}
