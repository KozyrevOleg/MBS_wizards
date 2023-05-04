package com.msp360.at.wizards.tests.nbf.files;


import com.msp360.at.wizards.tests.BaseClass;
import org.testng.annotations.Test;

public class WhatToBackUp extends BaseClass {


    @Test(dataProvider = "nbfWhatToBackUp")
    public void testWhatToBackUp(String computerName, String planName, String destination) throws InterruptedException {

        remoteManagementPageSteps.selectPc(computerName);
        remoteManagementPageSteps.createNBFBackupPlan();
        nbfFilesWizardWelcomePageSteps.fillWelcomeStepCase0(planName);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardWhereToBackUpPageSteps.selectBackupStorage(destination);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();

        switch (destination) {
            case WASABI_IMMUT_ON:
            case WASABI_IMMUT_OFF:
            case B2_IMMUT_ON:
            case B2_IMMUT_OFF:
            case AZURE_STACK:
            case MINIO_SYNTHETIC_ON:
                nbfFilesWizardWhatToBackUpPageSteps.fillBackupDataPlainTextCase2();
                break;

            default:
                nbfFilesWizardWhatToBackUpPageSteps.fillBackupDataPlainTextCase1();
                break;
        }

        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        Thread.sleep(3000);
        nbfFilesWizardPage.fullBackupIsNotScheduled.click();
        Thread.sleep(2000);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(6000);
        remoteManagementSidePanelPageSteps.editPlan(planName);
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
                nbfFilesWizardWhatToBackUpPageSteps.verifySelectedPathsPlainTextCase2();
                break;

            default:
                nbfFilesWizardWhatToBackUpPageSteps.verifySelectedPathsPlainTextCase1();
                break;
        }

        nbfFilesWizardPageSteps.saveThePlan();
        nbfFilesWizardPage.fullBackupIsNotScheduled.click();
        Thread.sleep(4000);
        remoteManagementSidePanelPageSteps.deletePlan(planName);
        Thread.sleep(4000);

    }
}
