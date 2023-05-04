package com.msp360.at.wizards.tests.nbf.files;

import com.msp360.at.wizards.tests.BaseClass;
import org.testng.annotations.Test;

public class WelcomePage extends BaseClass {

    @Test(dataProvider = "nbfWelcomePage", description = "Checking FFI link and Plan Name field")
    public void testWelcomePage(String computerName, String stepName,
                                String planName, String destination) throws InterruptedException {

        //разобраться с VSS чекбоксом
        //PasteFromClipboard нет проверки
        //Связи по разным шагам прописываем в этих переменных
        remoteManagementPageSteps.selectPc(computerName);
        remoteManagementPageSteps.createNBFBackupPlan();
        nbfFilesWizardWelcomePageSteps.fillWelcomeStepCase1(stepName, planName);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardWhereToBackUpPageSteps.selectBackupStorage(destination);
        nbfFilesWizardPage.clickNext();
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
        Thread.sleep(2000);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(6000);
        remoteManagementSidePanelPageSteps.editPlan(planName);
        nbfFilesWizardWelcomePageSteps.verifyPlanName(planName, stepName);
        nbfFilesWizardPageSteps.saveThePlan();
        nbfFilesWizardPage.fullBackupIsNotScheduled.click();
        Thread.sleep(3000);
        remoteManagementSidePanelPageSteps.deletePlan(planName);
        Thread.sleep(4000);
    }
}
