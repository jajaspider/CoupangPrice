package com.sonaapi;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Croll {
    private static WebDriver driver;
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_PATH = "chromedriver";
    private String itemcode;

    public Croll(String itemcode) {
        this.itemcode = itemcode;

        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        driver = new ChromeDriver();
        // url = https://www.coupang.com/vp/products/7215502
        String url = "https://www.coupang.com/vp/products/"+this.itemcode;
        driver.get(url);
        WebElement itemnametag = driver.findElement(By.className("prod-buy-header__title"));
        String itemname = itemnametag.getText();
        System.out.println(itemname);

        driver.quit();
    }
}
