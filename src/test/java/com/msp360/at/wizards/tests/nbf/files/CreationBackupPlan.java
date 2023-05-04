package com.msp360.at.wizards.tests.nbf.files;

import com.msp360.at.wizards.tests.BaseClass;


public class CreationBackupPlan extends BaseClass {


   /* @Test(dataProvider = "nbfFiles")
    public void CreationBackupPlan(String computerName, String planName, String destination, String classType)
            throws InterruptedException {

        //разобраться с VSS чекбоксом
        //PasteFromClipboard нет проверки
        //Связи по разным шагам прописываем в этих переменных
        boolean notStandardNotArchive = planName.equals("Intelligent-Tiering") || planName.equals("Standard-IA")
                || planName.equals("One Zone-IA");

        remoteManagementPageSteps.selectPc(computerName);
        remoteManagementPageSteps.createNBFBackupPlan();
        //выделено
        // nbfFilesWizardWelcomePageSteps.fillWelcomeStep(planName);
        nbfFilesWizardPageSteps.clickNext();
        nbfFilesWizardWhereToBackUpPageSteps.selectBackupStorage(destination);
        nbfFilesWizardPageSteps.clickNext();
*//*        if (planName.equals("Standard")) {
            nbfFilesWizardPageSteps.clickNext();
        } else if (notStandardNotArchive) {
            nbfFilesWizardAdvancedOptionsPageSteps.fillAdvancedOptionsStepCase1(classType);
            nbfFilesWizardPageSteps.clickNext();
        } else {
            nbfFilesWizardAdvancedOptionsPageSteps.fillAdvancedOptionsStepCase2(classType);
            nbfFilesWizardPageSteps.clickNext();
        }*//*
        //добавить тестовые диски
        //nbfFilesWizardPageSteps.selectBackupDataTree();
        nbfFilesWizardWhatToBackUpPageSteps.fillBackupDataPlainTextCase2();
        nbfFilesWizardPageSteps.clickNext();
        if (planName.equals("Standard")) {
            //nbfFilesWizardPageSteps.clickNext();
            nbfFilesWizardAdvancedFilterPageSteps.fillAdvancedFilterCase1();
            nbfFilesWizardPageSteps.clickNext();
            nbfFilesWizardPageSteps.clickNext();
        } else if (notStandardNotArchive) {
            nbfFilesWizardAdvancedFilterPageSteps.fillAdvancedFilterCase2();
            nbfFilesWizardPageSteps.clickNext();
            nbfFilesWizardCompressionAndEncryptionPageSteps.fillCompressionAndEncryptionCase1();
            //create a new bug
            nbfFilesWizardPageSteps.clickNext();
            nbfFilesWizardPageSteps.clickNext();
            nbfFilesWizardPageSteps.clickNext();
            nbfFilesWizardPageSteps.clickNext();
            nbfFilesWizardPageSteps.clickBack();
            nbfFilesWizardPageSteps.clickNext();
            nbfFilesWizardBackupConsistencyCheckPageSteps.fillBackupConsistencyCheckCase2();
            nbfFilesWizardPageSteps.clickNext();
        } else {
            nbfFilesWizardAdvancedFilterPageSteps.fillAdvancedFilterCase2();
            nbfFilesWizardPageSteps.clickNext();
            nbfFilesWizardCompressionAndEncryptionPageSteps.fillCompressionAndEncryptionStepCase2();
            nbfFilesWizardPageSteps.clickNext();
            nbfFilesWizardPageSteps.clickNext();
            nbfFilesWizardPageSteps.clickNext();
        }
        nbfFilesWizardPageSteps.clickNext();
        nbfFilesWizardPageSteps.clickNext();
        nbfFilesWizardPage.fullBackupIsNotScheduledModal();
        nbfFilesWizardPageSteps.clickNext();
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(3000);

        //Plan name has the same name as selected S3 storage class
        remoteManagementSidePanelPageSteps.checkPlanName(planName, classType);
        remoteManagementSidePanelPageSteps.editPlan();
        //выделено
        //nbfFilesWizardWelcomePageSteps.assertPlanName(planName);
        nbfFilesWizardPageSteps.clickNext();
        //Assert selected destination
        nbfFilesWizardWhereToBackUpPageSteps.checkDestination(destination);
        nbfFilesWizardPageSteps.clickNext();

        if (planName.equals("Standard")) {
            nbfFilesWizardAdvancedOptionsPageSteps.assertDefaultOptions(classType);
            nbfFilesWizardPageSteps.clickNext();
            nbfFilesWizardWhatToBackUpPageSteps.assertSelectedPathsPlainTextCase2();
            //Assert для BackupSource + добавить exclude в тесты
            //проверка заполненности полей пассворда точками?
            nbfFilesWizardPageSteps.clickNext();
            nbfFilesWizardAdvancedFilterPageSteps.assertAdvancedFilterCase1();
            //Assert для Advanced Filter Options
            nbfFilesWizardPageSteps.clickNext();
            nbfFilesWizardCompressionAndEncryptionPageSteps.assertCompressionAndEncryptionDefaultSettings(destination);
            nbfFilesWizardPageSteps.clickNext();
            nbfFilesWizardBackupConsistencyCheckPageSteps.assertBackupConsistencyCheckCase1();
            nbfFilesWizardPageSteps.clickNext();

        } else if (notStandardNotArchive) {
            nbfFilesWizardAdvancedOptionsPageSteps.assertFillAdvancedOptionsCase1(classType);
            nbfFilesWizardPageSteps.clickNext();
            //разные проверки для прода и стэйджей
            nbfFilesWizardWhatToBackUpPageSteps.assertSelectedPathsPlainTextCase2();
            nbfFilesWizardPageSteps.clickNext();
            nbfFilesWizardAdvancedFilterPageSteps.assertAdvancedFilterCase2();
            nbfFilesWizardCompressionAndEncryptionPageSteps.assertCompressionAndEncryptionCase1(destination);
            nbfFilesWizardPageSteps.clickNext();
            nbfFilesWizardBackupConsistencyCheckPageSteps.assertBackupConsistencyCheckCase2();
            nbfFilesWizardPageSteps.clickNext();
        } else {
            nbfFilesWizardAdvancedOptionsPageSteps.assertFillAdvancedOptionsStepCase2(classType);
            nbfFilesWizardPageSteps.clickNext();
            nbfFilesWizardCompressionAndEncryptionPageSteps.assertCompressionAndEncryptionCase2(destination);
            nbfFilesWizardPageSteps.clickNext();
            nbfFilesWizardBackupConsistencyCheckPageSteps.assertBackupConsistencyCheckCase1();
            nbfFilesWizardPageSteps.clickNext();
        }
        nbfFilesWizardPageSteps.clickNext();
        nbfFilesWizardPageSteps.clickNext();
        nbfFilesWizardPage.fullBackupIsNotScheduledModal();
        nbfFilesWizardPageSteps.clickNext();
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(6000);
        remoteManagementSidePanelPageSteps.deletePlan();
        Thread.sleep(3000);
    }*/
}
