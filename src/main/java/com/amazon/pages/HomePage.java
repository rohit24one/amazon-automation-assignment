package com.amazon.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;

    By searchBox = By.id("twotabsearchtextbox");
    By searchBtn = By.id("nav-search-submit-button");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public void searchProduct(String product) {
        driver.findElement(searchBox).sendKeys(product);
        driver.findElement(searchBtn).click();
    }
}