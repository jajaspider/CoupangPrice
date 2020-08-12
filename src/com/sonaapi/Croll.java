package com.sonaapi;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Croll {
    private static WebDriver driver;
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_PATH = "chromedriver";
    private String itemcode;

    public Croll(String itemcode) {
        this.itemcode = itemcode;

        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        driver = new ChromeDriver(chromeOptions);
//        driver = new ChromeDriver();
        // url = https://www.coupang.com/vp/products/7215502
        String url = "https://www.coupang.com/vp/products/"+this.itemcode;
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            WebElement itemnametag = driver.findElement(By.className("prod-buy-header__title"));
            String itemname = itemnametag.getText();
            System.out.println(itemname);

            WebElement itemstatustag = driver.findElement(By.className("oos-label"));
            String itemstatus = itemstatustag.getText();
            System.out.println(itemstatus);
        }
        catch(NoSuchElementException e){
            System.out.println("재고있음");
        }
        catch(Exception e){
            e.printStackTrace();
        }



        driver.quit();
    }
}
