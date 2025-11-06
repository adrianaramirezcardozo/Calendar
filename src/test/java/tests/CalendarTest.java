package tests;

import data.CalendarTestData;

import org.testng.Assert;
import org.testng.annotations.Test;

import util.DriverSetup;

public class CalendarTest extends DriverSetup {

    @Test(testName = "Calendar test", dataProviderClass = CalendarTestData.class, dataProvider = "calendarData")
    public void calendarTest(String timeSlot, String title) {

        Assert.assertTrue(calendarHomePage.calendarHomePageLoaded(), "Calendar home page is not loaded");

        calendarHomePage.createEventOnTimeslot(timeSlot);
        Assert.assertTrue(newEventPage.newEventPageLoaded(), "New Event page is not loaded");
        Assert.assertFalse(newEventPage.isAddButtonEnabled(), "Add button is enabled");

        newEventPage.enterTitle(title);
        Assert.assertTrue(newEventPage.isAddButtonEnabled(), "Add button is disabled");
    }
}
