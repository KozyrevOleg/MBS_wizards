package com.msp360.at.wizards.steps;

import com.msp360.at.wizards.NbfFilesWizardWelcomePage;
import com.msp360.at.wizards.tests.BaseClass;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class NbfFilesWizardWelcomePageSteps extends BaseClass {

    protected static final String FFI_LINK = "https://mspbackups.com/AP/Help/backup/about/ffi";
    protected static final String PLAN_TYPE_NAME = " Welcome to Files Backup Plan Wizard ";

    private final NbfFilesWizardWelcomePage nbfFilesWizardWelcomePage;

    public NbfFilesWizardWelcomePageSteps(WebDriver driver) {
        BaseClass.getDriver();
        nbfFilesWizardWelcomePage = new NbfFilesWizardWelcomePage(driver);
    }

    @Step("Fill Welcome step. Case 0. Plan name {0}")
    @Description("The simple step for testing other steps")
    public void fillWelcomeStepCase0(String planName) throws InterruptedException {
        nbfFilesWizardWelcomePage.fillPlanName(planName);
    }


    @Step("Fill Welcome step. Case 1. Step name {0}, Plan name {1}")
    @Description("Checking FFI link and Plan Name field")
    public void fillWelcomeStepCase1(String stepName, String planName) throws InterruptedException {
        nbfFilesWizardWelcomePage.fillPlanName(planName);
        nbfFilesWizardWelcomePage.checkNameOfTheStep(stepName);
        String wizardHandle = getDriver().getWindowHandle();
        nbfFilesWizardWelcomePage.clickLearMore();
        Thread.sleep(7000);
        var handles = driver.get().getWindowHandles();
        for (String handle : handles) {
            driver.get().switchTo().window(handle);
            if (FFI_LINK.equals(driver.get().getCurrentUrl())) {
                Assert.assertEquals(driver.get().getTitle(), "Forever Forward Incremental Backup");
                driver.get().close();
                driver.get().switchTo().window(wizardHandle);
            }
        }
    }

    //Assertions
    @Step("Verify Welcome page. Case 1. Plan name {0}, Step name {1}")
    public void verifyPlanName(String planName, String stepName) {
        Assert.assertEquals(nbfFilesWizardWelcomePage.checkPlanName(), planName);
        Assert.assertEquals(nbfFilesWizardWelcomePage.checkNameOfTheStep(stepName), PLAN_TYPE_NAME);
    }
}
