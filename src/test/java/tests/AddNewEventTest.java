package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import util.DriverSetup;
import util.GlobalVariables;

public class AddNewEventTest extends DriverSetup {

    @Test(testName = "Add new event test")
    public void addNewEventTest() {

        //Open the Calendar app (already installed on iOS simulators), validate the application is started.
        Assert.assertTrue(calendarHomePage.calendarHomePageLoaded(), "Calendar home page is not loaded");

        //Click + button in the top right corner to create a new event.
        calendarHomePage.clickAddEventButton();

        Assert.assertTrue(newEventPage.newEventPageLoaded(), "New Event page is not loaded");

        //Send a GET request to https://bored-api.appbrewery.com/random and the activity value response should be a name for the newly created event.
        GlobalVariables.responseActivityValue = restAssuredUtility.getActivityValue("activity");
        newEventPage.enterTitle(GlobalVariables.responseActivityValue);
        Assert.assertEquals(newEventPage.getCommentText(), GlobalVariables.responseActivityValue);

        //For Starts select any date and any time - you have to change the date and time to something.
        newEventPage.chooseStartDayTomorrow();
        newEventPage.chooseStartHour("20", "05");

        //For Ends select a date and time which is + 1 day from what you have chose in Starts.
        //newEventPage.chooseFinishDay();

        //Add travel time of 30 minutes.
        newEventPage.chooseTravelTime("30 minutes");

        //Click and verify that when you enable All-day date field is only visible (or hour field is hidden) in Starts and Ends.
        newEventPage.configureAllDay();
        Assert.assertTrue(newEventPage.isHourPickerHidden(), "Hour picker is not hidden");

        //Click on Add button.
        newEventPage.addNewEventCreated();

        //Switch to Month view (click back).
        newEventPage.switchToMonthView("November");

        //Verify that event is added for the dates selected
        Assert.assertTrue(calendarHomePage.isEventAdded(), "New event is not displayed");
    }
}