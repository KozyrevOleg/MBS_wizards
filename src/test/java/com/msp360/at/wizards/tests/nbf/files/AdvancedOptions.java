package com.msp360.at.wizards.tests.nbf.files;

import com.msp360.at.wizards.steps.StorageClasses;
import com.msp360.at.wizards.tests.BaseClass;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import org.openqa.selenium.ElementClickInterceptedException;
import org.testng.annotations.Test;

public class AdvancedOptions extends BaseClass {

    @Tags({@Tag("UI"), @Tag("Wizard Step")})
    @Test(dataProvider = "nbfAdvancedOptions",
            description = "Tests for 'Advanced Options' step. Cases with different list of selected options")
    @Severity(SeverityLevel.NORMAL)
    public void testAdvancedOptions(String computerName, String planName, String destination,
                                    StorageClasses storageClasses) throws InterruptedException {

        remoteManagementPageSteps.selectPc(computerName);
        Thread.sleep(2000);
        remoteManagementPageSteps.createNBFBackupPlan();
        nbfFilesWizardWelcomePageSteps.fillWelcomeStepCase0(planName);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardWhereToBackUpPageSteps.selectBackupStorage(destination);
        nbfFilesWizardPage.clickNext();
        switch (storageClasses) {
            case S3_INTELLIGENT_TIERING:
            case S3_STANDARD_IA:
            case S3_ONE_ZONE_IA:
            case AZURE_COOL:
                nbfFilesWizardAdvancedOptionsPageSteps.fillAdvancedOptionsStepCase1(storageClasses);
                Thread.sleep(2000);
                nbfFilesWizardPageSteps. clickNext();
                break;
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_DEEP_ARCHIVE:
            case S3_GLACIER_INSTANT_RETRIEVAL:
            case AZURE_ARCHIVE:
                nbfFilesWizardAdvancedOptionsPageSteps.fillAdvancedOptionsStepCase2(storageClasses);
                break;
            default:
                nbfFilesWizardPage.clickNext();
                break;
        }
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardWhatToBackUpPageSteps.fillBackupDataPlainTextCase0();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        Thread.sleep(3000);
        nbfFilesWizardPage.fullBackupIsNotScheduled.click();
        Thread.sleep(3000);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(6000);
        remoteManagementSidePanelPageSteps.editPlan(planName);
        Thread.sleep(4000);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        switch (storageClasses) {

            case S3_INTELLIGENT_TIERING:
            case S3_STANDARD_IA:
            case S3_ONE_ZONE_IA:
            case AZURE_COOL:
                nbfFilesWizardAdvancedOptionsPageSteps
                        .verifyFillAdvancedOptionsCase1(storageClasses);
                break;
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_DEEP_ARCHIVE:
            case S3_GLACIER_INSTANT_RETRIEVAL:
            case AZURE_ARCHIVE:
                nbfFilesWizardAdvancedOptionsPageSteps
                        .verifyFillAdvancedOptionsStepCase2(storageClasses);
                break;
            default:
                nbfFilesWizardAdvancedOptionsPageSteps.verifyDefaultOptions(storageClasses);
                nbfFilesWizardPage.clickNext();
                break;

        }
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPageSteps.saveThePlan();
        nbfFilesWizardPage.fullBackupIsNotScheduled.click();
        try {
            nbfFilesWizardPageSteps.saveThePlan();
            Thread.sleep(4000);
            remoteManagementSidePanelPageSteps.deletePlan(planName);
            Thread.sleep(4000);
        } catch (ElementClickInterceptedException e) {
            Thread.sleep(4000);
            remoteManagementSidePanelPageSteps.deletePlan(planName);
            Thread.sleep(4000);
        }
    }
}
