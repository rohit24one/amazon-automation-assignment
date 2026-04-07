package com.amazon.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.Set;

public class ProductPage {

    WebDriver driver;

    By quantity = By.id("quantity");
    By addToCart = By.id("add-to-cart-button");

    // UPDATED LOCATORS
    By subtotal1 = By.id("attach-accessory-cart-subtotal");
    By subtotal2 = By.id("attach-subtotal");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToNewTab() {
        for (String win : driver.getWindowHandles()) {
            driver.switchTo().window(win);
        }
    }

    public void selectQuantity(int qty) {

        try {
            Thread.sleep(2000); 

            Select select = new Select(driver.findElement(quantity));
            select.selectByValue(String.valueOf(qty));

        } catch (Exception e) {
            System.out.println("Quantity selection failed");
        }
    }

    public void addToCart() {

        try {
            Thread.sleep(3000); // wait for page load

            WebElement addBtn = driver.findElement(By.id("add-to-cart-button"));

            // scroll to button
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", addBtn);

            Thread.sleep(1000);

            // click using JS (MOST RELIABLE)
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", addBtn);

            System.out.println("Add to cart clicked");

        } catch (Exception e) {
            System.out.println("Add to cart failed");
        }
    }

    public boolean isSubtotalVisible() {

        try {
            Thread.sleep(4000); // wait for cart popup

            if (driver.findElements(By.id("attach-accessory-cart-subtotal")).size() > 0) {
                return driver.findElement(By.id("attach-accessory-cart-subtotal")).isDisplayed();
            }

            if (driver.findElements(By.id("attach-subtotal")).size() > 0) {
                return driver.findElement(By.id("attach-subtotal")).isDisplayed();
            }

            return false;

        } catch (Exception e) {
            return false;
        }
    }
    
    public String getSubtotalText() {

        try {
            Thread.sleep(4000);

            if (driver.findElements(By.id("attach-accessory-cart-subtotal")).size() > 0) {
                return driver.findElement(By.id("attach-accessory-cart-subtotal")).getText();
            }

            if (driver.findElements(By.id("attach-subtotal")).size() > 0) {
                return driver.findElement(By.id("attach-subtotal")).getText();
            }

            return "";

        } catch (Exception e) {
            return "";
        }
    }
}