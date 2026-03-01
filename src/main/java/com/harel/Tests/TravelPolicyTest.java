package com.harel.Tests;

import com.harel.Utils.ConfigReader;
import com.harel.Utils.DriverManager;
import com.harel.Utils.GeneralAction;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TravelPolicyTest extends BaseTest{
    ConfigReader configReader;
    GeneralAction generalAction = new GeneralAction();
    @Test
    public void TravelPolicyFlow()
    {
        DriverManager.getDriver().get(ConfigReader.get().URL);
        generalAction.Click("firstTimePurchase",true);
        Assert.assertTrue(generalAction.GetTitle().contains("בחרו יעד נסיעה"),
                "The Test Is Not In Current Page");
        generalAction.RandomClick("direcrion");
        generalAction.Click("nextToPickDates",true);
        Assert.assertTrue(generalAction.GetTitle().contains("בחירת תאריכי נסיעה"),
                "The Test Is Not In Current Page");
        generalAction.SetText("startDateInput", LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy")));
        generalAction.PickDate(LocalDate.now().plusDays(30-1),false);
        Assert.assertTrue(generalAction.GetText("totalDays").contains("30"),
                "The Total Days Display"+ generalAction.GetText("totalDays") + "Extend 30 Days");
        generalAction.Click("moveToPassDetails",true);
        Assert.assertTrue(generalAction.GetTitle().contains("מילוי פרטי הנוסעים"),
                "The Test Is Not In Current Page");
    }
}
