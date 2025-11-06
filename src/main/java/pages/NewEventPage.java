package pages;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import io.qameta.allure.Step;

import util.GlobalVariables;

public class NewEventPage {

    protected IOSDriver driver;

    int todayDay = LocalDate.now().getDayOfMonth();

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`name == \"Title\"`]")
    private RemoteWebElement titleTextField;

    @iOSXCUITFindBy(accessibility = "add-button")
    private RemoteWebElement addButton;

    public static String startDayPicker(String day) {
        return "//XCUIElementTypeStaticText[@name=\"" + day + "\"]";
    }

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeCell[contains(@name, 'Starts')]//XCUIElementTypeButton)[1]")
    private RemoteWebElement startsDatePickerButton;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeCell[contains(@name, 'Ends')]//XCUIElementTypeButton)[1]")
    private RemoteWebElement endsDatePickerButton;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeCell[contains(@name, 'Starts')]/XCUIElementTypeButton/XCUIElementTypeButton)[2]")
    private RemoteWebElement startsHourPickerButton;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypePickerWheel)[1]")
    private RemoteWebElement hourPicker;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypePickerWheel)[2]")
    private RemoteWebElement minutePicker;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name=\"Travel Time\"]/XCUIElementTypeOther[1]/XCUIElementTypeOther")
    private RemoteWebElement travelTime;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSwitch[@name='All-day']")
    private RemoteWebElement allDayToggle;

    public NewEventPage(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @Step("New event page is loaded")
    public boolean newEventPageLoaded() {
        return new WebDriverWait(driver, GlobalVariables.shortTimeout).until(ExpectedConditions.visibilityOf(titleTextField)).isDisplayed();
    }

    @Step("Add button is enabled/disabled")
    public boolean isAddButtonEnabled() {
        return addButton.isEnabled();
    }

    @Step("Entering title: {title}")
    public void enterTitle(String title) {
        titleTextField.clear();
        titleTextField.sendKeys(title);
    }
    @Step("Title entered")
    public void enterRandomTitle(String text) {
        titleTextField.clear();
        titleTextField.sendKeys(text);
    }
    public String getCommentText() {
        return titleTextField.getText();
    }

    @Step("Choosing starting hour: {hour}, minute: {minutes}" )
    public void chooseStartHour(String hour, String minutes) {
        startsHourPickerButton.click();
        new WebDriverWait(driver, GlobalVariables.shortTimeout).until(ExpectedConditions.visibilityOf(hourPicker)).sendKeys(hour);
        minutePicker.sendKeys(minutes);
    }

    public void chooseStartDay(String day) {
        startsDatePickerButton.click();
        String xpath = "//XCUIElementTypeStaticText[@name=\"" + day + "\"]";
        RemoteWebElement dayElement = (RemoteWebElement) new WebDriverWait(driver, GlobalVariables.shortTimeout)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        dayElement.click();
        startsDatePickerButton.click();
    }
    public void chooseStartDayTomorrow() {
        int tomorrowDay = LocalDate.now().plusDays(1).getDayOfMonth();
        chooseStartDay(String.valueOf(tomorrowDay));
    }
    public void chooseFinishDay() {
        endsDatePickerButton.click();
        LocalDate finishDate = LocalDate.now().plusDays(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM", Locale.ENGLISH);
        String finishDateString = finishDate.format(formatter);

        String xpath = "//XCUIElementTypeButton[@name=\"" + finishDateString + "\"]";
        RemoteWebElement finishDayElement = (RemoteWebElement) new WebDriverWait(driver, GlobalVariables.longTimeout)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        finishDayElement.click();
    }
    public void chooseTravelTime(String time) {
        travelTime.click();
        String xpath = "//XCUIElementTypeButton[@name='" + time + "' and @label='" + time + "']";
        new WebDriverWait(driver, GlobalVariables.shortTimeout)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)))
                .click();
    }
    public void configureAllDay() {
        allDayToggle.click();
    }

    public boolean isHourPickerHidden() {
        return new WebDriverWait(driver, GlobalVariables.shortTimeout)
                .until(ExpectedConditions.invisibilityOf(hourPicker));
    }

    public void addNewEventCreated() {
        addButton.click();
    }
}
