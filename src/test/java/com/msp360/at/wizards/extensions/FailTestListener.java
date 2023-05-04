package com.msp360.at.wizards.extensions;

import com.msp360.at.wizards.tests.BaseClass;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class FailTestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        saveScreenshotFailure(BaseClass.getDriver());
        saveLogs(result.getMethod().getConstructorOrMethod().getName());
    }

    @Attachment(
        value = "Page screenshot",
        type = "image/png"
    )
    public byte[] saveScreenshotFailure(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(
        value = "Stacktrace",
        type = "text/plain"
    )
    public static String saveLogs(String message) {
        return message;
    }
}
