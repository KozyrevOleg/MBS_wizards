package com.msp360.at.wizards.tests;

import org.openqa.selenium.Capabilities;

public class CapabilityFactory {
    public Capabilities capabilities;

    public Capabilities getCapabilities(String browser) {
        if (browser.equals("Firefox")) {
            capabilities = OptionsManager.getFirefoxOptions();
        } else {
            capabilities = OptionsManager.getChromeOptions();
        }
        return capabilities;
    }
}
