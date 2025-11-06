package pages;

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

import util.GlobalVariables;
import util.Helpers;

public class CalendarsPage extends Helpers {

    protected IOSDriver driver;

    @iOSXCUITFindBy(accessibility = "add-calendar-button")
    private RemoteWebElement addCalendarButton;

    @iOSXCUITFindBy(accessibility = "add-calendar-menubutton")
    private RemoteWebElement addCalendarMenuButton;

    @iOSXCUITFindBy(accessibility = "calendar-title-field")
    private RemoteWebElement newCalendarTitleTextField;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeImage[`name == \"chevron.forward\"`]")
    private RemoteWebElement openCalendarsCreatedButton;

    @iOSXCUITFindBy(accessibility = "done-button")
    private RemoteWebElement doneButton;

    public CalendarsPage(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @Step("Clicking Add new calendar")
    public void addNewCalendar() {
        addCalendarButton.click();
    }
    @Step("Opening Add Calendar Menu")
    public void openAddCalendarMenu() {
        addCalendarMenuButton.click();
    }

    public void openNewCalendarsCreated() {
        openCalendarsCreatedButton.click();
    }
    @Step("Title is Displayed")
    public boolean isTitleDisplayed(String calendarName) {
        String xpath = String.format("//XCUIElementTypeCell[@name='%s']//XCUIElementTypeStaticText[@value='%s']", calendarName, calendarName);
        WebDriverWait wait = new WebDriverWait(driver, GlobalVariables.longTimeout);
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return title.isDisplayed();
    }
    @Step("Opening calendar info")
    public void openInfoCalendarInfo(String calendarName) {
        String xpath = String.format("//XCUIElementTypeCell[@name='%s']//XCUIElementTypeImage[@name='info.circle']", calendarName);
        WebElement infoButton = driver.findElement(By.xpath(xpath));
        infoButton.click();
    }
    @Step("Clicking Done button")
    public void clickDoneButton() {
        doneButton.click();
    }
}