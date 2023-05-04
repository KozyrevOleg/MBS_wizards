package com.msp360.at.wizards.tests;

import java.util.ArrayList;
import java.util.HashMap;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

    protected static final String TEST_NAME = "NBF Files wizard";

    //Get Chrome Options
    public static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();

        /*options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        //options.addArguments("--incognito");
        return options;*/
        //chromeOptions.setCapability("VNC", "enabled");

        chromeOptions.setCapability("selenoid:options", new HashMap<String, Object>() {
            {
                put("browserVersion", "112.0");

                //String[] array = {"clipboardReadWrite"};
                put("Browser.grantPermissions", new HashMap<String, Object>() {
                    {
                        put("permissions", "clipboardReadWrite");
                    }
                });

                //* How to add test badge *//*
                put("name", TEST_NAME);

                //* How to set session timeout *//*
                put("sessionTimeout", "10m");

                //* How to set timezone *//*
                put("env", new ArrayList<String>() {
                    {
                        add("TZ=UTC");
                    }
                });

                //* How to add "trash" button *//*
                put("labels", new HashMap<String, Object>() {
                    {
                        put("manual", "true");
                    }
                });

                //* How to enable video recording *//*
                //put("enableVideo", true);

                put("enableVNC", true);
            }
        });

        return chromeOptions;
    }

    //Get Firefox Options
    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("browserVersion", "112.0");
        firefoxOptions.setCapability("selenoid:options", new HashMap<String, Object>() {
            {
                /* How to add test badge */
                put("name", TEST_NAME);

                /* How to set session timeout */
                put("sessionTimeout", "10m");

                /* How to set timezone */
                put("env", new ArrayList<String>() {
                    {
                        add("TZ=UTC");
                    }
                });

                /* How to add "trash" button */
                put("labels", new HashMap<String, Object>() {
                    {
                        put("manual", "true");
                    }
                });

                /* How to enable video recording */
                //put("enableVideo", true);

                put("enableVNC", true);
            }
        });
        return firefoxOptions;
    }
}
