package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class ProductPage {
    private By productTitle = By.xpath("//div[@class='product-title']");
    private By inStockValue = By.xpath("//div[@class='inventory']");
    private By addToCartButton = By.xpath("//button[@class='add-button']");
    private By inCartValue = By.xpath("//a[@href='#/cart']");

    private void waitForElement(WebDriver driver, By element){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
    }

    String getProductTitle(WebDriver driver){
        waitForElement(driver,productTitle);
        return driver.findElement(productTitle).getText();
    }

    int getInStockValue (WebDriver driver){
        waitForElement(driver,inStockValue);
        String fullInStockText = driver.findElement(inStockValue).getText();
        return Integer.parseInt(fullInStockText.substring(fullInStockText.length()-1));
    }

    boolean checkAddToCartVisible(WebDriver driver){
        waitForElement(driver,addToCartButton);
        return true;
    }

    int getInCartValue(WebDriver driver){
        waitForElement(driver,inCartValue);
        String fullInCartValue = driver.findElement(inCartValue).getText();
        return Integer.parseInt(fullInCartValue.substring(fullInCartValue.length()-2,fullInCartValue.length()-1));
    }

    void clickAddToCart(WebDriver driver){
        waitForElement(driver,addToCartButton);
        driver.findElement(addToCartButton).click();
    }
}

