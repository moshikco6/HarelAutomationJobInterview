package com.harel.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class GeneralAction {
    private static final int DEFAULT_TIMEOUT_SEC = ConfigReader.get().implicitWait;
    public static WebElement waitForClickable(WebElement element) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT_SEC))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForVisible(WebElement element) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT_SEC))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public String GetTitle(){
        return DriverManager.getDriver().getTitle();
    }
    public void Click(String element) {
        Click(element, false);
    }

    public void Click(String element, boolean newPage) {
        WebDriver driver = DriverManager.getDriver();
        if (newPage){
            String previousTitle = driver.getTitle();
            waitForClickable(GetElement(element)).click();
            new WebDriverWait( driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SEC))
                    .until(ExpectedConditions.not(ExpectedConditions.titleIs(previousTitle)));
        }
        else
            waitForClickable(GetElement(element)).click();
    }
    public void Click(WebElement element, boolean newPage) {
        WebDriver driver = DriverManager.getDriver();
        if (newPage){
            String previousTitle = driver.getTitle();
            waitForClickable(element).click();
            new WebDriverWait( driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SEC))
                    .until(ExpectedConditions.not(ExpectedConditions.titleIs(previousTitle)));
        }
        else
            waitForClickable(element).click();
    }
    public void RandomClick(String element){
        List<WebElement> list = GetListOfElements(element);
        int randomNum =  new Random().nextInt(list.size());
        WebElement randomElement = list.get(randomNum);
        waitForClickable(randomElement).click();
    }
    public void SetText(String element, String text){
        waitForVisible(GetElement(element)).sendKeys(text);
    }

    public String GetText(String element){
        return waitForVisible(GetElement(element)).getText();
    }
    private WebElement GetElement(String element){
        WebDriver driver = DriverManager.getDriver();
        String selector = ConfigReader.get(element + ".element");
        return switch (ConfigReader.get(element + ".by")) {
            case "css"   -> driver.findElement(By.cssSelector(selector));
            case "xpath" -> driver.findElement(By.xpath(selector));
            default      -> throw new IllegalArgumentException("Unsupported locator type for: " + element);
        };
    }

    private List<WebElement> GetListOfElements(String element){
        WebDriver driver = DriverManager.getDriver();
        String selector = ConfigReader.get(element + ".element");
        return switch (ConfigReader.get(element + ".by")) {
            case "css"   -> driver.findElements(By.cssSelector(selector));
            case "xpath" -> driver.findElements(By.xpath(selector));
            default      -> throw new IllegalArgumentException("Unsupported locator type for: " + element);
        };
    }

    public void PickDate(LocalDate date, Boolean newPage){
        WebDriver driver = DriverManager.getDriver();
        Click(driver.findElement(By.cssSelector("[data-hrl-bo=\""+ date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"\"]")),newPage);
    }
}
