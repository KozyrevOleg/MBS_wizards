package com.msp360.at.wizards.tests.nbf.files;

import com.msp360.at.wizards.tests.BaseClass;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class AdvancedFilter extends BaseClass {

    @Test(dataProvider = "nbfAdvancedFilter",
            description = "Tests for 'Advanced Filter' step. Cases with different list of selected options")
    @Severity(SeverityLevel.NORMAL)
    public void testAdvancedFilter(String computerName, String planName, String destination)
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
        switch (destination) {
            case WASABI_IMMUT_OFF:
            case S3_IMMUT_OFF:
            case B2_IMMUT_ON:
                nbfFilesWizardAdvancedFilterPageSteps.fillAdvancedFilterCase1();
                nbfFilesWizardPageSteps.clickNext();
                break;
            case WASABI_IMMUT_ON:
            case B2_IMMUT_OFF:
            case AZURE_STACK:
            case MINIO_SYNTHETIC_ON:
                nbfFilesWizardAdvancedFilterPageSteps.fillAdvancedFilterCase2();
                nbfFilesWizardPageSteps.clickNext();
                break;
            default:
                nbfFilesWizardPage.clickNext();
                break;
        }
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        Thread.sleep(2000);
        nbfFilesWizardPage.fullBackupIsNotScheduled.click();
        Thread.sleep(2000);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(6000);
        remoteManagementSidePanelPageSteps.editPlan(planName);
        Thread.sleep(4000);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        switch (destination) {
            case WASABI_IMMUT_OFF:
            case S3_IMMUT_OFF:
            case B2_IMMUT_ON:
                nbfFilesWizardAdvancedFilterPageSteps.assertAdvancedFilterCase1();
                nbfFilesWizardPageSteps.clickNext();
                break;
            case WASABI_IMMUT_ON:
            case B2_IMMUT_OFF:
            case AZURE_STACK:
            case MINIO_SYNTHETIC_ON:
                nbfFilesWizardAdvancedFilterPageSteps.verifyAdvancedFilterCase2();
                break;
            default:
                nbfFilesWizardPage.clickNext();
        }
        nbfFilesWizardPageSteps.clickNext();
        Thread.sleep(2000);
        nbfFilesWizardPageSteps.saveThePlan();
        nbfFilesWizardPage.fullBackupIsNotScheduled.click();
        Thread.sleep(3000);
        remoteManagementSidePanelPageSteps.deletePlan(planName);
        Thread.sleep(4000);
    }

}
