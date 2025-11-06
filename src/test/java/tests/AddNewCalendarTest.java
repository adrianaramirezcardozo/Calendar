package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import util.DriverSetup;

public class AddNewCalendarTest extends DriverSetup {

    @Test(testName = "Add new calendar test")
    public void addNewCalendarTest() {

        //Open the Calendar app (already installed on iOS simulators), validate the application is started.
        Assert.assertTrue(calendarHomePage.calendarHomePageLoaded(), "Calendar home page is not loaded");

        //Click Calendars.
        calendarHomePage.openCalendarsPage();

        //Click on Add Calendar and select Add Calendar.
        calendarsPage.addNewCalendar();
        calendarsPage.openAddCalendarMenu();

        //Add name for Calendar and change colour to Blue.
        newCalendarPage.enterNewCalendarTitle("Test");
        newCalendarPage.openColorSelector();
        newCalendarPage.selectBlueColor();
        newCalendarPage.returnToAddCalendarPage();

        //Click on Done button.
        newCalendarPage.addNewCalendarCreated();

        //Validate that newly created calendar is displayed in Calendars.
        //calendarsPage.openNewCalendarsCreated().
        Assert.assertTrue(calendarsPage.isTitleDisplayed("Test"), "Title is not displayed");

        //Click on (i) button next to newly created calendar and delete the calendar.
        calendarsPage.openInfoCalendarInfo("Test");
        newCalendarPage.deleteCalendar();
        //it seems that Confirm delete Calendar is behaving as popup so .setAutoAcceptAlerts(true); will accept

        //Validate only Calendar is displayed now.
        Assert.assertTrue(calendarsPage.isTitleDisplayed("Calendar"), "Title Calendar is not displayed");

        //Click on Done.
        calendarsPage.clickDoneButton();

        //Validate Calendar screen is loaded.
        Assert.assertTrue(calendarHomePage.calendarHomePageLoaded(), "Calendar home page is not loaded");

    }
}
