package data;

import org.testng.annotations.DataProvider;

public class CalendarTestData {

    @DataProvider(name = "calendarData")
    public Object[][] calendarData() {
        return new Object[][] {
                {"01:00", "Test"},
                {"05:00", "test1"},
                {"13:00", "test2"}
        };
    }
}