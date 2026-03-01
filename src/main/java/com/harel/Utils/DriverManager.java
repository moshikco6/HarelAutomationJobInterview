package com.harel.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverManager {

    private static WebDriver driver = null;
    private DriverManager() {}

    public static WebDriver getDriver() {
        if (driver == null){
            ChromeOptions options = buildChromeOptions();
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.get().implicitWait));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.get().pageLoadTimeout));
            return driver;
        }else
            return driver;
    }

    private static ChromeOptions buildChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        if (ConfigReader.get().headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-notifications");
        options.addArguments("--lang=he-IL");
        return options;
    }

    public static void quitDriver() {
            driver.quit();
   }
}

