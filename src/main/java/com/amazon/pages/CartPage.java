package com.amazon.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    WebDriver driver;

    By goToCart = By.xpath("//a[contains(@href,'/cart') or @id='attach-view-cart-button']");
    By itemName = By.xpath("//span[contains(@class,'sc-product-title')]");
    By quantity = By.xpath("//span[@data-a-selector='value']");
    By cartSubtotal = By.xpath("//span[@id='sc-subtotal-amount-activecart']//span");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openCart() {

        try {
            Thread.sleep(3000); // wait for popup

            driver.findElement(goToCart).click();

        } catch (Exception e) {
            // fallback: open cart directly
            driver.get("https://www.amazon.in/gp/cart/view.html");
        }
    }

    public boolean verifyItem(String expected) {

        String name = driver.findElement(itemName).getText();
        System.out.println("Cart Item: " + name);

        return name.contains(expected);
    }

    public String getQuantity() {

        try {
            String qty = driver.findElement(quantity).getText();
            System.out.println("Cart Quantity: " + qty);
            return qty;

        } catch (Exception e) {
            return "";
        }
    }
    
    public String getSubtotal() {

        try {
            Thread.sleep(3000);

            String value = driver.findElement(cartSubtotal).getText();
            System.out.println("Fetched Subtotal: " + value);

            return value;

        } catch (Exception e) {
            return "";
        }
    }
}