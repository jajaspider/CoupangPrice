package com.sonaapi;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Croll {
    private int convertstringtoint(String pricedata){
        String intStr = pricedata.replaceAll("[^0-9]", "");
        return Integer.parseInt(intStr);
    }

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
//         url = https://www.coupang.com/vp/products/7215502
        String url = "https://www.coupang.com/vp/products/"+this.itemcode;
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // 제품명 출력
        WebElement itemnametag = driver.findElement(By.className("prod-buy-header__title"));
        String itemname = itemnametag.getText();
        System.out.println(itemname);
        try {
            // 재고가없으면, 재고없음 출력 / 만약 Element를 못찾는다면 catch문으로 이동하여 재고없음 출력
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
        // 상품 원래 가격 출력
        WebElement itempricetag = driver.findElement(By.className("origin-price"));
        String itemprice = itempricetag.getText();
        System.out.println(convertstringtoint(itemprice));

        // 할인 후 상품 가격 출력
        WebElement itemsalepricetag = driver.findElement(By.xpath("//*[@id=\"contents\"]/div[1]/div/div[3]/div[5]/div[1]/div/div[2]/span[1]/strong"));
        String itemsaleprice = itemsalepricetag.getText();
        System.out.println(convertstringtoint(itemsaleprice));




        driver.quit();
    }
}
