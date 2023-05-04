package com.msp360.at.wizards.steps;

import com.msp360.at.wizards.NbfFilesWizardBackupConsistencyCheckPage;
import com.msp360.at.wizards.tests.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class NbfFilesWizardBackupConsistencyCheckPageSteps extends BaseClass {

    //JavascriptExecutor js = (JavascriptExecutor) driver;

    private final NbfFilesWizardBackupConsistencyCheckPage nbfFilesWizardBackupConsistencyCheckPage;

    public NbfFilesWizardBackupConsistencyCheckPageSteps(WebDriver driver) {
        BaseClass.getDriver();
        nbfFilesWizardBackupConsistencyCheckPage = new NbfFilesWizardBackupConsistencyCheckPage(driver);
    }

    //Backup Consistency Check
    @Step("Verify Backup Consistency Check")
    public void verifyBackupConsistencyCheckDefaultSettings() {
        checkBackupConsistencyCheck(CHECKED_CHECKBOX);
    }

    @Step("Backup Consistency Check. Case 1. Unselected option")
    public void fillBackupConsistencyCheckCase1() {
        nbfFilesWizardBackupConsistencyCheckPage.selectBackupConsistencyCheck();
    }

    @Step("Verify Backup Consistency Check")
    public void verifyBackupConsistencyCheckCase1() {
        checkBackupConsistencyCheck(UNCHECKED_CHECKBOX);
    }

    //Assertions Backup Consistency Check step
    @Step("checking the selected destination. Checkbox {0}.")
    public void checkBackupConsistencyCheck(String checkboxState) {
        Assert.assertEquals(nbfFilesWizardBackupConsistencyCheckPage.checkBackupConsistencyCheck(), checkboxState);
    }


}
