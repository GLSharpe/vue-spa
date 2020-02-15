package com.company;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Tests {
    private static PortalHomepage portalHomepage = new PortalHomepage();
    private static DriverBuilder driverBuilder = new DriverBuilder();
    private static ProductPage productPage = new ProductPage();
    private static WebDriver driver;
    private static List<String> productNames;


    @BeforeAll
    static void setUp(){
        driver = driverBuilder.getDriver();
    }

    //Verifies there are 3 products displayed on the portal homepage
    @Test
    @Order(1)
    void verifyThreeProductsReturned(){
        productNames = portalHomepage.getProductNames(driver);
        Assertions.assertEquals(3,productNames.size());
    }

    //Verifies the correct products are displayed and in the correct order
    @Test
    @Order(2)
    void verifyCorrectProductsReturned(){
        boolean sameProducts = portalHomepage.EXPECTEDPRODUCTNAMES.equals(productNames);
        Assertions.assertTrue(sameProducts);

    }

    //Verifies the products have the correct price attached to them
    @Test
    @Order(3)
    void verifyCorrectPricesReturned(){
        for (int i=0 ; i < portalHomepage.EXPECTEDPRODUCTNAMES.size() ; i++){
            String productPrice = portalHomepage.getProductPrice(driver,portalHomepage.EXPECTEDPRODUCTNAMES.get(i));
            Assertions.assertEquals(productPrice,portalHomepage.EXPECTEDPRODUCTPRICES.get(i));
        }
    }

    //Verifies the product page is displayed when you click on a product
    @Test
    @Order(4)
    void verifyProductPage(){
        portalHomepage.clickProductByNameText(driver,portalHomepage.EXPECTEDPRODUCTNAMES.get(0));
        Assertions.assertEquals(portalHomepage.EXPECTEDPRODUCTNAMES.get(0),productPage.getProductTitle(driver));
    }

    //Verifies there are 2 items in stock
    @Test
    @Order(5)
    void verifyOnlyTwoInStock(){
        Assertions.assertEquals(2,productPage.getInStockValue(driver));
    }

    //Verifies the add to cart button is visible
    @Test
    @Order(6)
    void verifyAddToCartButtonVisible(){
        Assertions.assertTrue(productPage.checkAddToCartVisible(driver));
    }

    //Verifies that the value in the header is updated when you add an item to the basket
    @Test
    @Order(7)
    void verifyInCartBasketUpdates(){
        int initialValue = productPage.getInCartValue(driver);
        productPage.clickAddToCart(driver);
        int valueAfterAddToBasket = productPage.getInCartValue(driver);
        Assertions.assertEquals(initialValue+1,valueAfterAddToBasket);
    }

    //Verify you can't add more items to your basket than there are items in stock
    @Test
    @Order(8)
    void verifyCartCapacityLimitedByStock(){
        int currentInStockValue = productPage.getInStockValue(driver);
        while (currentInStockValue !=0){
            productPage.clickAddToCart(driver);
            currentInStockValue--;
        }
        Assertions.assertEquals(0,currentInStockValue);
        int initialInCartValue = productPage.getInCartValue(driver);
        productPage.clickAddToCart(driver);
        int inCartValueAfterAddToBasket = productPage.getInCartValue(driver);
        Assertions.assertEquals(initialInCartValue,inCartValueAfterAddToBasket);

    }

    @AfterAll
    static void tearDown(){
        driver.close();
        driver.quit();
    }


}