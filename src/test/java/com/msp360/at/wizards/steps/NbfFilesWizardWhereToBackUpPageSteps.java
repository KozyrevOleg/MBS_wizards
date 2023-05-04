package com.msp360.at.wizards.steps;

import com.msp360.at.wizards.NbfFilesWizardWhereToBackUpPage;
import com.msp360.at.wizards.tests.BaseClass;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class NbfFilesWizardWhereToBackUpPageSteps extends BaseClass {

    protected static final String FFI_LINK = "https://mspbackups.com/AP/EditAccount.aspx";

    private final NbfFilesWizardWhereToBackUpPage nbfFilesWizardWhereToBackUpPage;

    public NbfFilesWizardWhereToBackUpPageSteps(WebDriver driver) {
        BaseClass.getDriver();
        nbfFilesWizardWhereToBackUpPage = new NbfFilesWizardWhereToBackUpPage(driver);
    }

    @Step("Fill Select Backup Storage step. Destination {0}")
    public void fillSelectBackupStorage(String destinationType) throws InterruptedException {
        nbfFilesWizardWhereToBackUpPage.selectDestination(destinationType);
        String wizardHandle = getDriver().getWindowHandle();
        nbfFilesWizardWhereToBackUpPage.clickAddNewStorageAccountLink();
        Thread.sleep(2000);
        var handles = driver.get().getWindowHandles();
        for (String handle : handles) {
            driver.get().switchTo().window(handle);
            if (URL_STORAGE_ACCOUNT.equals(driver.get().getCurrentUrl())) {
                Thread.sleep(10000);
                wait.until(ExpectedConditions
                        .frameToBeAvailableAndSwitchToIt(driver.get().findElement(By.cssSelector("div > iframe"))));
                Assert.assertEquals(nbfFilesWizardWhereToBackUpPage.addNewAccount(), "Add Account");
                driver.get().close();
                driver.get().switchTo().window(wizardHandle);
            }
        }
    }

    @Step("Select Backup Storage. Destination {0}")
    @Description("The simple step for testing other steps")
    public void selectBackupStorage(String destinationType) {
        nbfFilesWizardWhereToBackUpPage.selectDestination(destinationType);
    }

    //Assertions
    @Step("checking a selected destination. Destination {0}")
    public void checkDestination(String destination) {
        Assert.assertEquals(nbfFilesWizardWhereToBackUpPage.checkDestination(), destination);
    }

}
