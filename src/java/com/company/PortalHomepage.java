package com.company;

import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

class PortalHomepage {

    final List<String> EXPECTEDPRODUCTNAMES = Lists.newArrayList("iPad 4 Mini", "H&M T-Shirt White", "Charli XCX - Sucker CD");
    final List<String> EXPECTEDPRODUCTPRICES = Lists.newArrayList("$ 500.01", "$ 10.99", "$ 19.99");

    private By productNameText = By.xpath("//div[@class='product']/a");

    private void waitForElement(WebDriver driver, By element){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
    }

    List<String> getProductNames(WebDriver driver) {
        waitForElement(driver,productNameText);
        List<WebElement> products = driver.findElements(productNameText);
        return getTextFromProducts(products);
    }

    private List<String> getTextFromProducts(List<WebElement> products) {
        List<String> productNames = new ArrayList<>();
        for (WebElement element : products) {
            String productText = element.getText();
            productNames.add(productText);
        }
        return productNames;
    }

    String getProductPrice(WebDriver driver, String productName) {
        waitForElement(driver,productNameText);
        return  driver.findElement(By.xpath("//div[a[text()='"+productName+"']]/span")).getText();
    }

    void clickProductByNameText(WebDriver driver, String productName){
        waitForElement(driver,productNameText);
        driver.findElement(By.linkText(productName)).click();
    }
}


