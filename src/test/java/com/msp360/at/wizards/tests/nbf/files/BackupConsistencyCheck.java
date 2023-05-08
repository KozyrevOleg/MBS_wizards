package com.msp360.at.wizards.tests.nbf.files;

import com.msp360.at.wizards.tests.BaseClass;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import org.testng.annotations.Test;

public class BackupConsistencyCheck extends BaseClass {

    @Tags({@Tag("UI"), @Tag("Wizard Step")})
    @Test(dataProvider = "nbfBackupConsistencyCheck",
            description = "Tests for 'Backup Consistency Check' step. Cases with enabled/disabled option")
    @Severity(SeverityLevel.NORMAL)
    public void testSelectBackupStorage(String computerName, String planName, String destination)
            throws InterruptedException {

        remoteManagementPageSteps.selectPc(computerName);
        remoteManagementPageSteps.createNBFBackupPlan();
        nbfFilesWizardWelcomePageSteps.fillWelcomeStepCase0(planName);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardWhereToBackUpPageSteps.selectBackupStorage(destination);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardWhatToBackUpPageSteps.fillBackupDataPlainTextCase0();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();

        switch (destination) {
            case WASABI_IMMUT_ON:
            case WASABI_IMMUT_OFF:
            case B2_IMMUT_ON:
            case B2_IMMUT_OFF:
            case AZURE_STACK:
            case MINIO_SYNTHETIC_ON:
                nbfFilesWizardBackupConsistencyCheckPageSteps.fillBackupConsistencyCheckCase1();
                nbfFilesWizardPage.clickNext();
                break;

            default:
                //default state
                nbfFilesWizardPage.clickNext();
                break;
        }

        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        Thread.sleep(2000);
        nbfFilesWizardPage.fullBackupIsNotScheduled.click();
        Thread.sleep(2000);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(6000);
        remoteManagementSidePanelPageSteps.editPlan(planName);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();

        switch (destination) {
            case WASABI_IMMUT_ON:
            case WASABI_IMMUT_OFF:
            case B2_IMMUT_ON:
            case B2_IMMUT_OFF:
            case AZURE_STACK:
            case MINIO_SYNTHETIC_ON:
                nbfFilesWizardBackupConsistencyCheckPageSteps.verifyBackupConsistencyCheckCase1();
                break;

            default:
                nbfFilesWizardBackupConsistencyCheckPageSteps.verifyBackupConsistencyCheckDefaultSettings();
                nbfFilesWizardPage.clickNext();
        }

        Thread.sleep(3000);
        nbfFilesWizardPageSteps.saveThePlan();
        nbfFilesWizardPage.fullBackupIsNotScheduled.click();
        Thread.sleep(3000);
        remoteManagementSidePanelPageSteps.deletePlan(planName);
        Thread.sleep(4000);
    }
}
