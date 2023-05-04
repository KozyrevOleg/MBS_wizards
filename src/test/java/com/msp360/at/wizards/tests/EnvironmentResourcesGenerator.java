package com.msp360.at.wizards.tests;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.openqa.selenium.Capabilities;


public class EnvironmentResourcesGenerator {

    private static final String ENV_PROPERTIES_NAME = "environment.properties";
    private static final String ALLURE_RESULTS = "allure-results";
    public Capabilities capabilities;

    public Capabilities getCapabilities(String browser) {
        if (browser.equals("Firefox")) {
            capabilities = OptionsManager.getFirefoxOptions();
        } else {
            capabilities = OptionsManager.getChromeOptions();
        }
        return capabilities;
    }

    public EnvironmentResourcesGenerator() {
    }

    public EnvironmentResourcesGenerator(Capabilities capabilities) {
        this.capabilities = capabilities;
    }

    public void createProperties() {
        List<String> properties;
        if (capabilities == null) {
            properties = List.of(
                    "environment=test",
                    "url= https://mspbackups.com/",
                    "os=" + System.getProperty("os.name")
            );
        } else {
            properties = List.of(
                    "environment=test",
                    "url= https://mspbackups.com/",
                    "os=" + System.getProperty("os.name"),
                    "browser.name=" + capabilities.getBrowserName(),
                    "browser.version=" + capabilities.getBrowserVersion()
            );
        }

        try {
            Path allureResults = Paths.get(ClassLoader.getSystemResource("").toURI()).getParent();
            allureResults = Paths.get(allureResults.toAbsolutePath().toString(), ALLURE_RESULTS, ENV_PROPERTIES_NAME);
            if (!Files.exists(allureResults.getParent())) {
                Files.createDirectories(allureResults.getParent());
            }
            Files.write(allureResults, properties, StandardCharsets.UTF_8);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
