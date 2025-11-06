package pages;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

import util.Helpers;

public class NewCalendarPage extends Helpers {

    protected IOSDriver driver;
    @iOSXCUITFindBy(accessibility = "calendar-title-field")
    private RemoteWebElement newCalendarTitleTextField;

    @iOSXCUITFindBy(accessibility = "chevron")
    private RemoteWebElement colorSelector;

    @iOSXCUITFindBy(accessibility = "Blue")
    private RemoteWebElement blueColor;

    @iOSXCUITFindBy(accessibility = "Add Calendar")
    private RemoteWebElement calendarColourBackButton;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name == \"done-button\"`]")
    private RemoteWebElement doneButton;

    @iOSXCUITFindBy(accessibility = "Delete Calendar")
    private RemoteWebElement deleteCalendarButton;

    public NewCalendarPage(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @Step("Entering new calendar title")
    public void enterNewCalendarTitle(String title) {
        newCalendarTitleTextField.clear();
        newCalendarTitleTextField.sendKeys(title);
    }
    @Step("Opening color selector")
    public void openColorSelector() {
        colorSelector.click();
    }
    @Step("Selecting blue color")
    public void selectBlueColor() {
        blueColor.click();
    }
    @Step("Returning to Add Calendar Page")
    public void returnToAddCalendarPage() {
        calendarColourBackButton.click();
    }
    @Step("Adding new calendar created")
    public void addNewCalendarCreated() {
        doneButton.click();
    }
    @Step("Deleting calendar")
    public void deleteCalendar() {
        deleteCalendarButton.click();
    }
}
