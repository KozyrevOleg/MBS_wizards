package com.msp360.at.wizards.tests.nbf.files;

import com.msp360.at.wizards.steps.ScheduleTypeDaily;
import com.msp360.at.wizards.steps.ScheduleTypeMonthly;
import com.msp360.at.wizards.steps.StorageClasses;
import com.msp360.at.wizards.tests.BaseClass;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class ScheduleOptions extends BaseClass {

    //FFI
    @Test(dataProvider = "nbfScheduleOptionsFfiEnabledTypeMonthly",
            description = "Tests for 'FFI Schedule' option. Schedule Type: Monthly -> Day of week")
    @Severity(SeverityLevel.NORMAL)
    public void testFfiScheduleOptionsTypeMonthly(String computerName, String planName,
                                                  String destination, StorageClasses storageClasses,
                                                  ScheduleTypeMonthly scheduleTypeMonthly,
                                                  ScheduleTypeMonthly occurrenceType, ScheduleTypeMonthly dayType)
            throws InterruptedException {

        remoteManagementPageSteps.selectPc(computerName);
        Thread.sleep(2000);
        remoteManagementPageSteps.createNBFBackupPlan();
        nbfFilesWizardWelcomePageSteps.fillWelcomeStepCase0(planName);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardWhereToBackUpPageSteps.selectBackupStorage(destination);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardAdvancedOptionsPageSteps.fillAdvancedOptionsStepCase0(storageClasses);
        nbfFilesWizardWhatToBackUpPageSteps.fillBackupDataPlainTextCase0();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPageScheduleOptionsPageSteps
                .fillScheduleOptionsStepForeverForwardIncrementalTypeMonthlyCase1(destination, storageClasses,
                        scheduleTypeMonthly, occurrenceType, dayType);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        switch (storageClasses) {
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_DEEP_ARCHIVE:
            case AZURE_ARCHIVE:
                nbfFilesWizardPage.fullBackupIsNotScheduled.click();
                Thread.sleep(1500);
                nbfFilesWizardPage.clickNext();

                if (destination.equals(S3_WITHOUT_IN_CLOUD_OBJECT_COPYING_SUPPORT)) {
                    nbfFilesWizardPage.fullBackupIsNotScheduled.click();
                    Thread.sleep(1500);
                    nbfFilesWizardPage.clickNext();
                }
                break;

            default:
                nbfFilesWizardPage.clickNext();
                break;
        }
        //добавить выставление полиси на Retention Policy step
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(4000);
        remoteManagementSidePanelPageSteps.editPlan(planName);
        Thread.sleep(4000);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPageScheduleOptionsPageSteps
                .verifyScheduleOptionsStepForeverForwardIncrementalTypeMonthlyCase1(destination,
                        storageClasses, scheduleTypeMonthly, occurrenceType, dayType);

        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        switch (storageClasses) {
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_DEEP_ARCHIVE:
            case AZURE_ARCHIVE:
                nbfFilesWizardPage.fullBackupIsNotScheduled.click();
                Thread.sleep(2000);

                if (destination.equals(S3_WITHOUT_IN_CLOUD_OBJECT_COPYING_SUPPORT)) {
                    nbfFilesWizardPage.fullBackupIsNotScheduled.click();
                    Thread.sleep(2000);
                }
                break;

            default:
                break;
        }
        nbfFilesWizardPage.clickNext();
        Thread.sleep(3000);
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(3000);
        remoteManagementSidePanelPageSteps.deletePlan(planName);
        Thread.sleep(5000);
    }


    @Test(dataProvider = "nbfScheduleOptionsFfiEnabledTypeMonthlyDayOfMonth",
            description = "Tests for 'FFI Schedule' option. Schedule Type: Monthly -> Day of month")
    @Severity(SeverityLevel.NORMAL)
    public void testFfiScheduleOptionsTypeMonthlyDayOfMonth(String computerName, String planName,
                                                         String destination, StorageClasses storageClasses,
                                                         ScheduleTypeMonthly scheduleTypeMonthly,
                                                         ScheduleTypeMonthly occurrenceType,
                                                         ScheduleTypeMonthly dayNumber)
            throws InterruptedException {

        remoteManagementPageSteps.selectPc(computerName);
        remoteManagementPageSteps.createNBFBackupPlan();
        nbfFilesWizardWelcomePageSteps.fillWelcomeStepCase0(planName);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardWhereToBackUpPageSteps.selectBackupStorage(destination);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardAdvancedOptionsPageSteps.fillAdvancedOptionsStepCase0(storageClasses);
        nbfFilesWizardWhatToBackUpPageSteps.fillBackupDataPlainTextCase0();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPageScheduleOptionsPageSteps
                .fillScheduleOptionsStepForeverForwardIncrementalTypeMonthlyDayOfMonthCase2(destination,
                        storageClasses, scheduleTypeMonthly, occurrenceType, dayNumber);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        switch (storageClasses) {
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_DEEP_ARCHIVE:
            case AZURE_ARCHIVE:
                nbfFilesWizardPage.fullBackupIsNotScheduled.click();

                if (destination.equals(S3_WITHOUT_IN_CLOUD_OBJECT_COPYING_SUPPORT)) {
                    nbfFilesWizardPage.fullBackupIsNotScheduled.click();
                }
                break;

            default:
                nbfFilesWizardPage.clickNext();
                break;
        }

        //добавить выставление полиси на Retention Policy step
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(6000);
        remoteManagementSidePanelPageSteps.editPlan(planName);
        Thread.sleep(4000);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        Thread.sleep(2000);
        nbfFilesWizardPageScheduleOptionsPageSteps
                .verifyScheduleOptionsStepForeverForwardIncrementalTypeMonthlyDayOfMonthCase2(destination,
                        storageClasses, scheduleTypeMonthly, occurrenceType, dayNumber);

        switch (storageClasses) {
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_DEEP_ARCHIVE:
            case AZURE_ARCHIVE:
                nbfFilesWizardPage.fullBackupIsNotScheduled.click();

                if (destination.equals(S3_WITHOUT_IN_CLOUD_OBJECT_COPYING_SUPPORT)) {
                    nbfFilesWizardPage.fullBackupIsNotScheduled.click();
                }
                break;

            default:
                nbfFilesWizardPage.clickNext();
                break;
        }

        Thread.sleep(3000);
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(4000);
        remoteManagementSidePanelPageSteps.deletePlan(planName);
        Thread.sleep(4000);
    }


    @Test(dataProvider = "nbfScheduleOptionsFfiEnabledTypeDaily",
            description = "Tests for 'FFI Schedule' option. Schedule Type: Daily")
    @Severity(SeverityLevel.NORMAL)
    public void testFfiScheduleOptionsTypeDaily(String computerName, String planName, String destination,
                                             StorageClasses storageClasses, ScheduleTypeDaily scheduleTypeDaily,
                                             ScheduleTypeDaily day) throws InterruptedException {

        remoteManagementPageSteps.selectPc(computerName);
        Thread.sleep(2000);
        remoteManagementPageSteps.createNBFBackupPlan();
        nbfFilesWizardWelcomePageSteps.fillWelcomeStepCase0(planName);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardWhereToBackUpPageSteps.selectBackupStorage(destination);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardAdvancedOptionsPageSteps.fillAdvancedOptionsStepCase0(storageClasses);
        nbfFilesWizardWhatToBackUpPageSteps.fillBackupDataPlainTextCase0();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPageScheduleOptionsPageSteps
                .fillScheduleOptionsStepForeverForwardIncrementalTypeDailyCase3(destination, storageClasses,
                        scheduleTypeDaily, day);

        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        switch (storageClasses) {
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_DEEP_ARCHIVE:
            case AZURE_ARCHIVE:
                nbfFilesWizardPage.fullBackupIsNotScheduled.click();
                nbfFilesWizardPage.clickNext();

                if (destination.equals(S3_WITHOUT_IN_CLOUD_OBJECT_COPYING_SUPPORT)) {
                    nbfFilesWizardPage.fullBackupIsNotScheduled.click();
                }
                break;

            default:
                nbfFilesWizardPage.clickNext();
                break;
        }

        //добавить выставление полиси на Retention Policy step
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(6000);
        remoteManagementSidePanelPageSteps.editPlan(planName);
        Thread.sleep(4000);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        Thread.sleep(8000);
        nbfFilesWizardPageScheduleOptionsPageSteps
                .verifyScheduleOptionsStepForeverForwardIncrementalTypeDailyCase3(destination, storageClasses,
                        scheduleTypeDaily);

        switch (storageClasses) {
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_DEEP_ARCHIVE:
            case AZURE_ARCHIVE:
                nbfFilesWizardPage.fullBackupIsNotScheduled.click();

                if (destination.equals(S3_WITHOUT_IN_CLOUD_OBJECT_COPYING_SUPPORT)) {
                    nbfFilesWizardPage.fullBackupIsNotScheduled.click();
                }
                break;

            default:
                nbfFilesWizardPage.clickNext();
                break;
        }

        Thread.sleep(3000);
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(3000);
        remoteManagementSidePanelPageSteps.deletePlan(planName);
        Thread.sleep(6000);
    }

    //Recurring
    @Test(dataProvider = "nbfScheduleOptionsRecurringEnabledTypeMonthly",
            description = "Tests for 'Recurring Schedule' option. Schedule Type: Monthly -> Day of week")
    @Severity(SeverityLevel.NORMAL)

    public void testRecurringScheduleOptionsTypeMonthly(String computerName, String planName,
                                               String destination, StorageClasses storageClasses,
                                               ScheduleTypeMonthly scheduleTypeMonthly,
                                               ScheduleTypeMonthly occurrenceType, ScheduleTypeMonthly dayType)
            throws InterruptedException {

        remoteManagementPageSteps.selectPc(computerName);
        Thread.sleep(2000);
        remoteManagementPageSteps.createNBFBackupPlan();
        nbfFilesWizardWelcomePageSteps.fillWelcomeStepCase0(planName);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardWhereToBackUpPageSteps.selectBackupStorage(destination);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardAdvancedOptionsPageSteps.fillAdvancedOptionsStepCase0(storageClasses);
        nbfFilesWizardWhatToBackUpPageSteps.fillBackupDataPlainTextCase0();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPageScheduleOptionsPageSteps
                .fillScheduleOptionsStepRecurringIncrementalTypeMonthlyCase1(scheduleTypeMonthly,
                        occurrenceType, dayType);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        Thread.sleep(1000);
        nbfFilesWizardPage.fullBackupIsNotScheduled.click();
        Thread.sleep(2000);
        nbfFilesWizardPage.clickNext();
        //добавить выставление полиси на Retention Policy step
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(4000);
        remoteManagementSidePanelPageSteps.editPlan(planName);
        Thread.sleep(6000);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPageScheduleOptionsPageSteps
                .verifyScheduleOptionsStepRecurringIncrementalTypeMonthlyCase1(scheduleTypeMonthly,
                        occurrenceType, dayType);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        //nbfFilesWizardPage.clickNext();
        //nbfFilesWizardPage.fullBackupIsNotScheduled.click();
        //nbfFilesWizardPage.clickNext();
        //Thread.sleep(3000);
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(3000);
        remoteManagementSidePanelPageSteps.deletePlan(planName);
        Thread.sleep(4000);
    }


    @Test(dataProvider = "nbfScheduleOptionsRecurringEnabledTypeMonthlyDayOfMonth",
            description = "Tests for 'Recurring Schedule' option. Schedule Type: Monthly -> Day of month")
    @Severity(SeverityLevel.NORMAL)
    @Issue("MBS-19424")
    public void testRecurringScheduleOptionsTypeMonthlyDayOfMonth(String computerName, String planName,
                                                         String destination, StorageClasses storageClasses,
                                                         ScheduleTypeMonthly scheduleTypeMonthly,
                                                         ScheduleTypeMonthly occurrenceType,
                                                         ScheduleTypeMonthly dayNumber)
            throws InterruptedException {

        remoteManagementPageSteps.selectPc(computerName);
        remoteManagementPageSteps.createNBFBackupPlan();
        nbfFilesWizardWelcomePageSteps.fillWelcomeStepCase0(planName);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardWhereToBackUpPageSteps.selectBackupStorage(destination);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardAdvancedOptionsPageSteps.fillAdvancedOptionsStepCase0(storageClasses);
        nbfFilesWizardWhatToBackUpPageSteps.fillBackupDataPlainTextCase0();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPageScheduleOptionsPageSteps
                .fillScheduleOptionsStepRecurringIncrementalTypeMonthlyDayOfMonthCase2(scheduleTypeMonthly,
                        occurrenceType, dayNumber);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.fullBackupIsNotScheduled.click();
        Thread.sleep(2000);
        nbfFilesWizardPage.clickNext();
        //добавить выставление полиси на Retention Policy step
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(6000);
        remoteManagementSidePanelPageSteps.editPlan(planName);
        Thread.sleep(4000);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        Thread.sleep(2000);
        nbfFilesWizardPageScheduleOptionsPageSteps
                .verifyScheduleOptionsStepRecurringIncrementalTypeMonthlyDayOfMonthCase2(scheduleTypeMonthly,
                        occurrenceType, dayNumber);
        nbfFilesWizardPage.clickNext();
        Thread.sleep(1000);
        nbfFilesWizardPage.fullBackupIsNotScheduled.click();
        Thread.sleep(3000);
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(4000);
        remoteManagementSidePanelPageSteps.deletePlan(planName);
        Thread.sleep(4000);
    }


    @Test(dataProvider = "nbfScheduleOptionsRecurringEnabledTypeDaily",
            description = "Tests for 'Recurring Schedule' option. Schedule Type: Daily")
    @Severity(SeverityLevel.NORMAL)
    public void testRecurringScheduleOptionsTypeDaily(String computerName, String planName, String destination,
                                             StorageClasses storageClasses, ScheduleTypeDaily scheduleTypeDaily,
                                             ScheduleTypeDaily day) throws InterruptedException {

        remoteManagementPageSteps.selectPc(computerName);
        remoteManagementPageSteps.createNBFBackupPlan();
        nbfFilesWizardWelcomePageSteps.fillWelcomeStepCase0(planName);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardWhereToBackUpPageSteps.selectBackupStorage(destination);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardAdvancedOptionsPageSteps.fillAdvancedOptionsStepCase0(storageClasses);
        nbfFilesWizardWhatToBackUpPageSteps.fillBackupDataPlainTextCase0();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPageScheduleOptionsPageSteps
                .fillScheduleOptionsStepRecurringIncrementalTypeDailyCase3(scheduleTypeDaily, day);

        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.fullBackupIsNotScheduled.click();
        Thread.sleep(2000);
        nbfFilesWizardPage.clickNext();
        //добавить выставление полиси на Retention Policy step
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(6000);
        remoteManagementSidePanelPageSteps.editPlan(planName);
        Thread.sleep(4000);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        Thread.sleep(8000);
        nbfFilesWizardPageScheduleOptionsPageSteps
                .verifyScheduleOptionsStepRecurringIncrementalTypeDailyCase3(scheduleTypeDaily);
        nbfFilesWizardPage.clickNext();
        //nbfFilesWizardPage.fullBackupIsNotScheduled.click();
        Thread.sleep(3000);
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(3000);
        remoteManagementSidePanelPageSteps.deletePlan(planName);
        Thread.sleep(6000);
    }

    //Full
    @Test(dataProvider = "nbfScheduleOptionsFullEnabledTypeMonthly",
            description = "Tests for 'Full Schedule' option. Schedule Type: Monthly")
    @Severity(SeverityLevel.NORMAL)
    public void testFullScheduleOptionsTypeMonthly(String computerName, String planName,
                                                        String destination, StorageClasses storageClasses,
                                                        ScheduleTypeMonthly scheduleTypeMonthly,
                                                        ScheduleTypeMonthly occurrenceType, ScheduleTypeMonthly dayType)
            throws InterruptedException {

        remoteManagementPageSteps.selectPc(computerName);
        Thread.sleep(2000);
        remoteManagementPageSteps.createNBFBackupPlan();
        nbfFilesWizardWelcomePageSteps.fillWelcomeStepCase0(planName);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardWhereToBackUpPageSteps.selectBackupStorage(destination);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardAdvancedOptionsPageSteps.fillAdvancedOptionsStepCase0(storageClasses);
        nbfFilesWizardWhatToBackUpPageSteps.fillBackupDataPlainTextCase0();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPageScheduleOptionsPageSteps
                .fillScheduleOptionsStepFullTypeMonthlyCase1(scheduleTypeMonthly, occurrenceType, dayType);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        //nbfFilesWizardPage.fullBackupIsNotScheduled.click();
        //добавить выставление полиси на Retention Policy step
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(4000);
        remoteManagementSidePanelPageSteps.editPlan(planName);
        Thread.sleep(4000);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPageScheduleOptionsPageSteps
                .verifyScheduleOptionsStepFullTypeMonthlyCase1(scheduleTypeMonthly, occurrenceType, dayType);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        //nbfFilesWizardPage.fullBackupIsNotScheduled.click();
        Thread.sleep(3000);
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(3000);
        remoteManagementSidePanelPageSteps.deletePlan(planName);
        Thread.sleep(4000);
    }


    @Test(dataProvider = "nbfScheduleOptionsFullEnabledTypeMonthlyDayOfMonth",
            description = "Tests for 'Full Schedule' option. Schedule Type: Monthly -> Day of month")
    @Severity(SeverityLevel.NORMAL)
    public void testFullScheduleOptionsTypeMonthlyDayOfMonth(String computerName, String planName,
                                                                  String destination, StorageClasses storageClasses,
                                                                  ScheduleTypeMonthly scheduleTypeMonthly,
                                                                  ScheduleTypeMonthly occurrenceType,
                                                                  ScheduleTypeMonthly dayNumber)
            throws InterruptedException {

        remoteManagementPageSteps.selectPc(computerName);
        remoteManagementPageSteps.createNBFBackupPlan();
        nbfFilesWizardWelcomePageSteps.fillWelcomeStepCase0(planName);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardWhereToBackUpPageSteps.selectBackupStorage(destination);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardAdvancedOptionsPageSteps.fillAdvancedOptionsStepCase0(storageClasses);
        nbfFilesWizardWhatToBackUpPageSteps.fillBackupDataPlainTextCase0();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPageScheduleOptionsPageSteps
                .fillScheduleOptionsStepFullTypeMonthlyDayOfMonthCase2(scheduleTypeMonthly, occurrenceType, dayNumber);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        //nbfFilesWizardPage.fullBackupIsNotScheduled.click();
        //добавить выставление полиси на Retention Policy step
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(6000);
        remoteManagementSidePanelPageSteps.editPlan(planName);
        Thread.sleep(4000);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        Thread.sleep(2000);
        nbfFilesWizardPageScheduleOptionsPageSteps
                .verifyScheduleOptionsStepFullTypeMonthlyDayOfMonthCase2(scheduleTypeMonthly,
                        occurrenceType, dayNumber);
        nbfFilesWizardPage.clickNext();
        //nbfFilesWizardPage.fullBackupIsNotScheduled.click();
        Thread.sleep(3000);
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(4000);
        remoteManagementSidePanelPageSteps.deletePlan(planName);
        Thread.sleep(4000);
    }


    @Test(dataProvider = "nbfScheduleOptionsFullEnabledTypeDaily",
            description = "Tests for 'Full Schedule' option. Schedule Type: Daily")
    @Issue("MBS-19424")
    @Severity(SeverityLevel.NORMAL)
    public void testFullScheduleOptionsTypeDaily(String computerName, String planName, String destination,
        StorageClasses storageClasses, ScheduleTypeDaily scheduleTypeDaily,
        ScheduleTypeDaily day) throws InterruptedException {

        remoteManagementPageSteps.selectPc(computerName);
        remoteManagementPageSteps.createNBFBackupPlan();
        nbfFilesWizardWelcomePageSteps.fillWelcomeStepCase0(planName);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardWhereToBackUpPageSteps.selectBackupStorage(destination);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardAdvancedOptionsPageSteps.fillAdvancedOptionsStepCase0(storageClasses);
        nbfFilesWizardWhatToBackUpPageSteps.fillBackupDataPlainTextCase0();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPageScheduleOptionsPageSteps
                .fillScheduleOptionsStepFullTypeDailyCase3(scheduleTypeDaily, day);

        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        //nbfFilesWizardPage.fullBackupIsNotScheduled.click();
        //добавить выставление полиси на Retention Policy step
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(6000);
        remoteManagementSidePanelPageSteps.editPlan(planName);
        Thread.sleep(4000);
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        nbfFilesWizardPage.clickNext();
        Thread.sleep(8000);
        nbfFilesWizardPageScheduleOptionsPageSteps
                .verifyScheduleOptionsStepFullTypeDailyCase3(scheduleTypeDaily);

        nbfFilesWizardPage.clickNext();
        //nbfFilesWizardPage.fullBackupIsNotScheduled.click();
        Thread.sleep(3000);
        nbfFilesWizardPageSteps.saveThePlan();
        Thread.sleep(3000);
        remoteManagementSidePanelPageSteps.deletePlan(planName);
        Thread.sleep(6000);
    }


}
