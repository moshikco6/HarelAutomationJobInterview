package com.harel.Tests;

import com.harel.Utils.DriverManager;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;


public abstract class BaseTest {


    @BeforeMethod
    public void setupDriver() {
        DriverManager.getDriver();
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        DriverManager.quitDriver();
    }


}
