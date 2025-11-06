package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import util.GlobalVariables;
import util.Helpers;

public class CalendarHomePage extends Helpers {

    protected IOSDriver driver;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"Calendar\"")
    private RemoteWebElement calendarHomePageContainer;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"addevent-button\"")
    private RemoteWebElement addEventButton;

    @iOSXCUITFindBy(accessibility = "calendars-button")
    private RemoteWebElement calendarsButton;

    @iOSXCUITFindBy(accessibility = "2025")
    private RemoteWebElement backButton;

    public CalendarHomePage(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Calendar home page is loaded")
    public boolean calendarHomePageLoaded() {
        return new WebDriverWait(driver, GlobalVariables.shortTimeout).until(ExpectedConditions.visibilityOf(calendarHomePageContainer)).isDisplayed();
    }

    @Step("Created event on timeslot: {timeSlot}")
    public void createEventOnTimeslot(String timeSlot) {
        WebElement element = driver.findElement(AppiumBy.accessibilityId(timeSlot));
        scrollTo(driver, element, Directions.DOWN, 5);
        longPress(driver, element);
    }

    @Step("Opened Add new event page by clicking + button")
    public void clickAddEventButton() {
        addEventButton.click();
    }


    public boolean isEventAdded() {
        LocalDate eventDate = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM", Locale.ENGLISH);
        String dayName = eventDate.format(formatter);
        String xpath = "//XCUIElementTypeButton[@name='" + dayName + "']";
        RemoteWebElement dayButton = (RemoteWebElement) driver.findElement(By.xpath(xpath));
        String value = dayButton.getAttribute("value");
        return value != null && !value.equals("0");
    }

    public void switchToMonthView() {
        backButton.click();
    }

    public void openCalendarsPage() {
        calendarsButton.click();
    }
}
