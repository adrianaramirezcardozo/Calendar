package util;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import io.qameta.allure.Step;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.testng.annotations.*;

import pages.*;

@Listeners({ITestListenerUtility.class})
public class DriverSetup extends ConfigReader {

    protected static IOSDriver driver;
    private final AppiumServerManager appiumServerManager = new AppiumServerManager();

    protected CalendarHomePage calendarHomePage;
    protected NewEventPage newEventPage;

    protected CalendarsPage calendarsPage;
    protected NewCalendarPage newCalendarPage;
    protected RestAssuredUtility restAssuredUtility;

    @BeforeSuite
    public void startAppiumServer() {
        appiumServerManager.startAppiumServerWithCustomFlags();
    }

    @Step("Starting XCUITest driver")
    @BeforeMethod
    public void setUp() {

        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(getProperty("device.name"))
                .setPlatformVersion(getProperty("platform.version"))
                .setBundleId(getProperty("bundle.id"))
                .setNoReset(false)
                .setFullReset(false)
                .setAutoAcceptAlerts(true);

        try {
            driver = new IOSDriver(new URI(GlobalVariables.appiumLocalUrl).toURL(), options);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        restAssuredUtility = new RestAssuredUtility();

        calendarHomePage = new CalendarHomePage(driver);
        newEventPage = new NewEventPage(driver);
        calendarsPage = new CalendarsPage(driver);
        newCalendarPage = new NewCalendarPage(driver);
    }

    @Step("Stopping XCUITest driver")
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

    @AfterSuite(alwaysRun = true)
    public void stopAppiumServer() {
        appiumServerManager.stopAppiumServer();
    }
}