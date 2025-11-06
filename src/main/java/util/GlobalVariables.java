package util;

import java.time.Duration;

public class GlobalVariables {

    public static final String appiumLocalUrl = "http://127.0.0.1:4723/";

    public static final Duration shortTimeout = Duration.ofSeconds(10);
    public static final Duration longTimeout = Duration.ofSeconds(30);

    public static String response;
    public static String responseActivityValue;
}
