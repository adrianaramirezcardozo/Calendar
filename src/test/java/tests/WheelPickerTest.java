package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.DriverSetup;

@Epic("Wheel picker epic")
@Feature("Wheel picker feature")
public class WheelPickerTest extends DriverSetup {

    @Severity(SeverityLevel.CRITICAL)
    @Description("Create new event wheel picker functionality")
    @Story("Successful change of wheel picker value")
    @Test(testName = "Wheel picker test")
    public void wheelPickerTest() {

        Assert.assertTrue(calendarHomePage.calendarHomePageLoaded(), "Calendar home page is not loaded");

        calendarHomePage.createEventOnTimeslot("05:00");
        Assert.assertTrue(newEventPage.newEventPageLoaded(), "New Event page is not loaded");
        Assert.assertFalse(newEventPage.isAddButtonEnabled(), "Add button is enabled");

        newEventPage.enterTitle("Test");
        newEventPage.chooseStartHour("20", "05");
        Assert.assertTrue(newEventPage.isAddButtonEnabled(), "Add button is disabled");
    }
}
