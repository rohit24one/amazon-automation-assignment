package com.amazon.testcases;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.amazon.base.BaseTest;
import com.amazon.pages.CartPage;
import com.amazon.pages.HomePage;
import com.amazon.pages.ProductPage;
import com.amazon.pages.SearchResultsPage;
import com.amazon.util.ConfigReader;



public class AmazonTest extends BaseTest {

    @Test
    public void testAmazonFlow() throws InterruptedException {

        HomePage home = new HomePage(driver);
        home.open(ConfigReader.get("url"));

        home.searchProduct(ConfigReader.get("searchProduct"));

        SearchResultsPage results = new SearchResultsPage(driver);
        Assert.assertTrue(results.isResultsDisplayed(), "No search results");

        Thread.sleep(3000); // temporary wait

        results.selectProduct(ConfigReader.get("expectedProduct"));

        Thread.sleep(3000);  

        ProductPage product = new ProductPage(driver);
        product.switchToNewTab();

        Assert.assertTrue(driver.getTitle().toLowerCase().contains("589"),
                "Product page not opened");

        product.selectQuantity(2);
        product.addToCart();

        
        CartPage cart = new CartPage(driver);
        cart.openCart();
        
        String subtotal = cart.getSubtotal();
        System.out.println("Cart Subtotal: " + subtotal);

        Assert.assertFalse(subtotal.isEmpty(), "Cart subtotal not displayed");

        Assert.assertTrue(cart.verifyItem("Smart Tank 589"), "Wrong product");
        Assert.assertEquals(cart.getQuantity(), "2");
    }
}