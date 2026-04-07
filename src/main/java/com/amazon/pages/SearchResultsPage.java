package com.amazon.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class SearchResultsPage {

    WebDriver driver;

    By results = By.xpath("//div[@data-component-type='s-search-result']");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isResultsDisplayed() {
        return driver.findElements(results).size() > 0;
    }

    public void selectProduct(String productName) throws InterruptedException {

        List<WebElement> items = driver.findElements(
            By.xpath("//div[@data-component-type='s-search-result']//h2"));

        for (WebElement item : items) {

            String title = item.getText();
            System.out.println("Found: " + title);

            if (title.toLowerCase().contains(productName.toLowerCase())) {

                // Scroll to element
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
                Thread.sleep(1000);

                // Click using Actions (MOST RELIABLE)
                Actions actions = new Actions(driver);
                actions.moveToElement(item).click().perform();

                System.out.println("Clicked product");
                return;
            }
        }

        throw new RuntimeException("Product not found: " + productName);
    }
}