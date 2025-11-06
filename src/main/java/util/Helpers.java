package util;

import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.util.List;
import java.util.stream.IntStream;

import static java.time.Duration.ZERO;
import static java.time.Duration.ofSeconds;

import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

public class Helpers {

    public enum Directions {
        UP,
        DOWN
    }

    private final PointerInput FINGER = new PointerInput(TOUCH, "finger");

    public void swipeVertically(IOSDriver driver, Directions direction) {
        int startX = driver.manage().window().getSize().getWidth() / 2;
        int startY = driver.manage().window().getSize().getHeight() / 2;

        int endY;

        switch (direction) {
            case UP -> endY = (int) (driver.manage().window().getSize().getHeight() * 0.2);
            case DOWN -> endY = (int) (driver.manage().window().getSize().getHeight() * 0.8);
            default -> throw new IllegalArgumentException("Invalid direction selected: " + direction);
        }

        Sequence swipeVertically = new Sequence(FINGER, 0);
        swipeVertically.addAction(FINGER.createPointerMove(ZERO, viewport(), startX, startY));
        swipeVertically.addAction(FINGER.createPointerDown(LEFT.asArg()));
        swipeVertically.addAction(FINGER.createPointerMove(ofSeconds(1), viewport(), startX, endY));
        swipeVertically.addAction(FINGER.createPointerUp(LEFT.asArg()));
        driver.perform(List.of(swipeVertically));
    }

    public void scrollTo(IOSDriver driver, WebElement element, Directions direction, int swipeCount) {
        IntStream.range(0, swipeCount).forEach(obj -> {
            if (!element.isDisplayed())
                swipeVertically(driver, direction);
        });
    }

    public void longPress(IOSDriver driver, WebElement element) {
        Point location = getCenter(element);
        Sequence longPress = new Sequence(FINGER, 0);
        longPress.addAction(FINGER.createPointerMove(ZERO, viewport(), location.x, location.y));
        longPress.addAction(FINGER.createPointerDown(LEFT.asArg()));
        longPress.addAction(FINGER.createPointerMove(ofSeconds(1), viewport(), location.x, location.y));
        longPress.addAction(FINGER.createPointerUp(LEFT.asArg()));
        driver.perform(List.of(longPress));
    }

    public Point getCenter(WebElement element) {
        Point location = element.getLocation();
        Dimension size = element.getSize();
        return new Point(location.x + size.getWidth() / 2, location.y + size.getHeight() / 2);
    }
}
