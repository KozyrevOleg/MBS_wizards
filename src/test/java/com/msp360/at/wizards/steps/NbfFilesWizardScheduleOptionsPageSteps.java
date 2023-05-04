package com.msp360.at.wizards.steps;

import com.msp360.at.wizards.NbfFilesWizardScheduleOptionsPage;
import com.msp360.at.wizards.tests.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class NbfFilesWizardScheduleOptionsPageSteps extends BaseClass {

    private final NbfFilesWizardScheduleOptionsPage nbfFilesWizardScheduleOptionsPage;

    protected final String hours = "07";
    protected final String minutes = "07";
    protected final String meridiem = "AM";
    protected final String startFrom = "Mar 30, 2023";
    protected final String repeatEvery = "1";
    protected final String occursEvery = "7";
    protected final String occursEveryTypeHours = "Hour(s)";
    protected final String occursEveryTypeMinutes = "Minute(s)";
    protected final String timeFromHours = "07";
    protected final String timeFromMinutes = "07";
    protected final String timeToHours = "08";
    protected final String timeToMinutes = "08";
    protected final String stopThePlanIfItRunsForHours = "1";
    protected final String stopThePlanIfItRunsForMinutes = "59";
    protected final String alertPlanAsOverdueAfterNumber = "1";

    public NbfFilesWizardScheduleOptionsPageSteps(WebDriver driver) {
        BaseClass.getDriver();
        nbfFilesWizardScheduleOptionsPage = new NbfFilesWizardScheduleOptionsPage(driver);
    }

    @Step("Case 1. Set Forever Forward Incremental Schedule Option. Type Monthly ")
    public void fillScheduleOptionsStepForeverForwardIncrementalTypeMonthlyCase1(String destination,
        StorageClasses storageClasses, ScheduleTypeMonthly scheduleTypeMonthly, ScheduleTypeMonthly occurrenceType,
        ScheduleTypeMonthly dayType) throws InterruptedException {

        switch (storageClasses) {
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_DEEP_ARCHIVE:
            case AZURE_ARCHIVE:
                checkNoSchedule(ENABLED_RADIOBUTTON);
                checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
                checkForeverForwardIncrementalAlert(destination);

                if (S3_WITHOUT_IN_CLOUD_OBJECT_COPYING_SUPPORT.equals(destination)) {
                    checkNoSchedule(ENABLED_RADIOBUTTON);
                    checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
                    checkForeverForwardIncrementalAlert(destination);
                }

                break;

            default:
                checkNoSchedule(ENABLED_RADIOBUTTON);
                nbfFilesWizardScheduleOptionsPage.selectForeverForwardIncremental();
                checkForeverForwardIncremental(ENABLED_RADIOBUTTON);
                //select Daily type, because Monthly is default
                nbfFilesWizardScheduleOptionsPage.selectScheduleType("Daily");
                //select Monthly again
                nbfFilesWizardScheduleOptionsPage.selectScheduleType(String.valueOf(scheduleTypeMonthly));
                nbfFilesWizardScheduleOptionsPage.selectOccurrenceType(String.valueOf(occurrenceType));
                nbfFilesWizardScheduleOptionsPage.selectDayType(String.valueOf(dayType));
                nbfFilesWizardScheduleOptionsPage.setOccursAt(hours, minutes, meridiem);
                nbfFilesWizardScheduleOptionsPage.setRepeatEvery(repeatEvery);
                nbfFilesWizardScheduleOptionsPage.setStartFrom(startFrom);
                nbfFilesWizardScheduleOptionsPage.saveScheduleModal();
                nbfFilesWizardScheduleOptionsPage
                        .selectStopThePlanIfItRunsFor(stopThePlanIfItRunsForHours, stopThePlanIfItRunsForMinutes);
                nbfFilesWizardScheduleOptionsPage.selectAlertOverdueAfter(alertPlanAsOverdueAfterNumber);
                nbfFilesWizardScheduleOptionsPage.selectRunMissedSchedule();
                break;

        }
    }


    /* Amazon S3 (except for the S3 Glacier Flexible Retrieval and S3 Glacier Deep Archive long-term storage tiers)
    Microsoft Azure (except Azure Archive)
    Wasabi
    Backblaze B2
    MinIO
    S3 compatible (except storage providers without in-cloud object copying support)*/

    @Step("Forever Forward Incremental. Verify Case 1. Destination {0}, Storage Class {1}, Schedule type {2}, "
            + "Occurrence type {3}, Day type {4}")
    public void verifyScheduleOptionsStepForeverForwardIncrementalTypeMonthlyCase1(String destination,
        StorageClasses storageClasses, ScheduleTypeMonthly scheduleTypeMonthly, ScheduleTypeMonthly occurrenceType,
        ScheduleTypeMonthly dayType) throws InterruptedException {

        switch (storageClasses) {
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_DEEP_ARCHIVE:
            case AZURE_ARCHIVE:
                checkNoSchedule(ENABLED_RADIOBUTTON);
                checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
                checkForeverForwardIncrementalAlert(destination);

                if (S3_WITHOUT_IN_CLOUD_OBJECT_COPYING_SUPPORT.equals(destination)) {
                    checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
                    checkForeverForwardIncrementalAlert(destination);
                }
                break;

            default:
                checkNoSchedule(DISABLED_RADIOBUTTON);
                checkForeverForwardIncremental(ENABLED_RADIOBUTTON);
                checkRecurringIncremental(DISABLED_RADIOBUTTON);
                nbfFilesWizardScheduleOptionsPage.editForeverForwardIncremental();
                checkScheduleType(String.valueOf(scheduleTypeMonthly));
                checkOccurrenceType(String.valueOf(occurrenceType));
                checkDayType(String.valueOf(dayType));
                nbfFilesWizardScheduleOptionsPage.saveScheduleModal();
                Thread.sleep(3500);
                checkExecuteFullBackupSynthetic(DISABLED_CHECKBOX);
                checkStopThePlanIfItRunsFor(CHECKED_CHECKBOX,
                        stopThePlanIfItRunsForHours, stopThePlanIfItRunsForMinutes);
                checkAlertPlanAsOverdueAfter(CHECKED_CHECKBOX, alertPlanAsOverdueAfterNumber, "Hour(s)");
                checkRunMissedScheduledPlanImmediatelyWhenComputerStartsUp(CHECKED_CHECKBOX);
                break;
        }
    }


    @Step("Case 2. Set Forever Forward Incremental Schedule Option. Type Monthly Day of Month. "
            + "Destination {0}, Storage Class {1}, Schedule type {2}, Occurrence type {3}, Day number {4}")
    public void fillScheduleOptionsStepForeverForwardIncrementalTypeMonthlyDayOfMonthCase2(String destination,
        StorageClasses storageClasses, ScheduleTypeMonthly scheduleTypeMonthly, ScheduleTypeMonthly occurrenceType,
        ScheduleTypeMonthly dayNumber) throws InterruptedException {

        switch (storageClasses) {
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_DEEP_ARCHIVE:
            case AZURE_ARCHIVE:
                checkNoSchedule(ENABLED_RADIOBUTTON);
                checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
                checkForeverForwardIncrementalAlert(destination);

                if (S3_WITHOUT_IN_CLOUD_OBJECT_COPYING_SUPPORT.equals(destination)) {
                    checkNoSchedule(ENABLED_RADIOBUTTON);
                    checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
                    checkForeverForwardIncrementalAlert(destination);
                }
                break;

            default:
                checkNoSchedule(ENABLED_RADIOBUTTON);
                nbfFilesWizardScheduleOptionsPage.selectForeverForwardIncremental();
                checkForeverForwardIncremental(ENABLED_RADIOBUTTON);
                //select Daily type, because Monthly is default
                nbfFilesWizardScheduleOptionsPage.selectScheduleType("Daily");
                //select Monthly again
                nbfFilesWizardScheduleOptionsPage.selectScheduleType(String.valueOf(scheduleTypeMonthly));
                nbfFilesWizardScheduleOptionsPage.selectOccurrenceType(String.valueOf(occurrenceType));
                nbfFilesWizardScheduleOptionsPage.selectDayNumber(String.valueOf(dayNumber));
                nbfFilesWizardScheduleOptionsPage.setOccursAt(hours, minutes, meridiem);
                nbfFilesWizardScheduleOptionsPage.setRepeatEvery(repeatEvery);
                nbfFilesWizardScheduleOptionsPage.setStartFrom(startFrom);
                nbfFilesWizardScheduleOptionsPage.saveScheduleModal();
                Thread.sleep(2000);
                break;
        }
    }

    @Step("Forever Forward Incremental. Verify Case 2. Type Monthly Day of Month.Destination {0}, Storage Class {1}, "
            + "Schedule type {2}, Occurrence type {3}, Day number {4}")
    public void verifyScheduleOptionsStepForeverForwardIncrementalTypeMonthlyDayOfMonthCase2(String destination,
        StorageClasses storageClasses, ScheduleTypeMonthly scheduleTypeMonthly,
        ScheduleTypeMonthly occurrenceType, ScheduleTypeMonthly dayNumber) throws InterruptedException {

        switch (storageClasses) {
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_DEEP_ARCHIVE:
            case AZURE_ARCHIVE:
                checkNoSchedule(ENABLED_RADIOBUTTON);
                checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
                checkForeverForwardIncrementalAlert(destination);

                if (S3_WITHOUT_IN_CLOUD_OBJECT_COPYING_SUPPORT.equals(destination)) {
                    checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
                    checkForeverForwardIncrementalAlert(destination);
                }
                break;

            default:
                checkNoSchedule(DISABLED_RADIOBUTTON);
                checkForeverForwardIncremental(ENABLED_RADIOBUTTON);
                checkRecurringIncremental(DISABLED_RADIOBUTTON);
                nbfFilesWizardScheduleOptionsPage.editForeverForwardIncremental();
                checkScheduleType(String.valueOf(scheduleTypeMonthly));
                checkOccurrenceType(String.valueOf(occurrenceType));
                checkDayNumber(String.valueOf(dayNumber));
                //checkTotalInfoMonthly(dayNumber);
                nbfFilesWizardScheduleOptionsPage.saveScheduleModal();
                Thread.sleep(3500);
                checkExecuteFullBackupSynthetic(DISABLED_CHECKBOX);
                checkStopThePlanIfItRunsFor(UNCHECKED_CHECKBOX, stopThePlanIfItRunsForHours,
                        stopThePlanIfItRunsForMinutes);
                checkAlertPlanAsOverdueAfter(UNCHECKED_CHECKBOX, alertPlanAsOverdueAfterNumber, "Hour(s)");
                checkRunMissedScheduledPlanImmediatelyWhenComputerStartsUp(UNCHECKED_CHECKBOX);
                break;
        }
    }


    @Step("Case 3. Set Forever Forward Incremental Schedule Option. Type Daily. "
            + "Destination {0}, Storage Class {1}, Schedule type {2}, "
            + "Day type {3}")
    public void fillScheduleOptionsStepForeverForwardIncrementalTypeDailyCase3(String destination,
        StorageClasses storageClasses, ScheduleTypeDaily scheduleTypeDaily, ScheduleTypeDaily day)
            throws InterruptedException {

        switch (storageClasses) {
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_DEEP_ARCHIVE:
            case AZURE_ARCHIVE:
                checkNoSchedule(ENABLED_RADIOBUTTON);
                checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
                checkForeverForwardIncrementalAlert(destination);

                if (S3_WITHOUT_IN_CLOUD_OBJECT_COPYING_SUPPORT.equals(destination)) {
                    checkNoSchedule(ENABLED_RADIOBUTTON);
                    checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
                    checkForeverForwardIncrementalAlert(destination);
                }
                break;

            default:
                checkNoSchedule(ENABLED_RADIOBUTTON);
                nbfFilesWizardScheduleOptionsPage.selectForeverForwardIncremental();
                checkForeverForwardIncremental(ENABLED_RADIOBUTTON);
                nbfFilesWizardScheduleOptionsPage.selectScheduleType(String.valueOf(scheduleTypeDaily));
                nbfFilesWizardScheduleOptionsPage.selectDay(String.valueOf(day));
                nbfFilesWizardScheduleOptionsPage.selectOccursEveryType();
                nbfFilesWizardScheduleOptionsPage.setOccursEvery(occursEvery, occursEveryTypeMinutes);
                nbfFilesWizardScheduleOptionsPage
                        .setFromToTime(timeFromHours, timeFromMinutes, meridiem, timeToHours, timeToMinutes);
                nbfFilesWizardScheduleOptionsPage.setRepeatEvery(repeatEvery);
                nbfFilesWizardScheduleOptionsPage.setStartFrom(startFrom);
                nbfFilesWizardScheduleOptionsPage.saveScheduleModal();
                Thread.sleep(2000);
                break;

        }

    }

    @Step("Forever Forward Incremental. Verify Case 3. Destination {0}, Storage Class {1}, Schedule type {2}")
    public void verifyScheduleOptionsStepForeverForwardIncrementalTypeDailyCase3(String destination,
        StorageClasses storageClasses, ScheduleTypeDaily scheduleTypeDaily)
            throws InterruptedException {
        switch (storageClasses) {
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_DEEP_ARCHIVE:
            case AZURE_ARCHIVE:
                checkNoSchedule(ENABLED_RADIOBUTTON);
                checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
                checkForeverForwardIncrementalAlert(destination);

                if (S3_WITHOUT_IN_CLOUD_OBJECT_COPYING_SUPPORT.equals(destination)) {
                    checkNoSchedule(ENABLED_RADIOBUTTON);
                    checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
                    checkForeverForwardIncrementalAlert(destination);
                }
                break;

            default:
                checkNoSchedule(DISABLED_RADIOBUTTON);
                checkForeverForwardIncremental(ENABLED_RADIOBUTTON);
                checkRecurringIncremental(DISABLED_RADIOBUTTON);
                nbfFilesWizardScheduleOptionsPage.editForeverForwardIncremental();
                checkScheduleType(String.valueOf(scheduleTypeDaily));
                checkDays(CHECKED_CHECKBOX,
                        UNCHECKED_CHECKBOX,
                        CHECKED_CHECKBOX,
                        UNCHECKED_CHECKBOX,
                        UNCHECKED_CHECKBOX,
                        UNCHECKED_CHECKBOX,
                        UNCHECKED_CHECKBOX);
                //checkTotalInfoDailyOccursEvery(day);
                nbfFilesWizardScheduleOptionsPage.saveScheduleModal();
                Thread.sleep(3500);
                checkExecuteFullBackupSynthetic(DISABLED_CHECKBOX);
                checkStopThePlanIfItRunsFor(UNCHECKED_CHECKBOX, stopThePlanIfItRunsForHours,
                        stopThePlanIfItRunsForMinutes);
                checkAlertPlanAsOverdueAfter(UNCHECKED_CHECKBOX, alertPlanAsOverdueAfterNumber, "Hour(s)");
                checkRunMissedScheduledPlanImmediatelyWhenComputerStartsUp(UNCHECKED_CHECKBOX);
                break;
        }

    }

    @Step("Case 1. Set Recurring (incremental) Schedule Option. Type Monthly. Destination {0}, Storage Class {1}, "
            + "Schedule type {2}, Day type {3}")
    public void fillScheduleOptionsStepRecurringIncrementalTypeMonthlyCase1(ScheduleTypeMonthly scheduleTypeMonthly,
        ScheduleTypeMonthly occurrenceType, ScheduleTypeMonthly dayType) throws InterruptedException {

        //select Recurring Incremental option
        nbfFilesWizardScheduleOptionsPage.selectRecurringIncremental();
        checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
        //select Daily type, because Monthly is default
        nbfFilesWizardScheduleOptionsPage.selectScheduleType("Daily");
        //select Monthly again
        nbfFilesWizardScheduleOptionsPage.selectScheduleType(String.valueOf(scheduleTypeMonthly));
        nbfFilesWizardScheduleOptionsPage.selectOccurrenceType(String.valueOf(occurrenceType));
        nbfFilesWizardScheduleOptionsPage.selectDayType(String.valueOf(dayType));
        nbfFilesWizardScheduleOptionsPage.setOccursAt(hours, minutes, meridiem);
        nbfFilesWizardScheduleOptionsPage.setRepeatEvery(repeatEvery);
        nbfFilesWizardScheduleOptionsPage.setStartFrom(startFrom);
        nbfFilesWizardScheduleOptionsPage.saveScheduleModal();
        nbfFilesWizardScheduleOptionsPage
                .selectStopThePlanIfItRunsFor(stopThePlanIfItRunsForHours, stopThePlanIfItRunsForMinutes);
        nbfFilesWizardScheduleOptionsPage.selectAlertOverdueAfter(alertPlanAsOverdueAfterNumber);
        nbfFilesWizardScheduleOptionsPage.selectRunMissedSchedule();

    }


    @Step("Recurring Schedule. Verify Case 1. Schedule type {0}, Occurrence type {1}, Day type {2}")
    public void verifyScheduleOptionsStepRecurringIncrementalTypeMonthlyCase1(ScheduleTypeMonthly scheduleTypeMonthly,
        ScheduleTypeMonthly occurrenceType, ScheduleTypeMonthly dayType) throws InterruptedException {

        checkNoSchedule(DISABLED_RADIOBUTTON);
        checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
        checkRecurringIncremental(ENABLED_RADIOBUTTON);
        nbfFilesWizardScheduleOptionsPage.editRecurringIncremental();
        checkScheduleType(String.valueOf(scheduleTypeMonthly));
        checkOccurrenceType(String.valueOf(occurrenceType));
        checkDayType(String.valueOf(dayType));
        nbfFilesWizardScheduleOptionsPage.saveScheduleModal();
        Thread.sleep(3500);
        checkExecuteFullBackupSynthetic(DISABLED_CHECKBOX);
        checkStopThePlanIfItRunsFor(CHECKED_CHECKBOX,
                stopThePlanIfItRunsForHours, stopThePlanIfItRunsForMinutes);
        checkAlertPlanAsOverdueAfter(CHECKED_CHECKBOX, alertPlanAsOverdueAfterNumber, "Hour(s)");
        checkRunMissedScheduledPlanImmediatelyWhenComputerStartsUp(CHECKED_CHECKBOX);
    }


    @Step("Case 2. Set Recurring Schedule Option. Type Monthly Day of Month. Schedule type {0}, "
            + "Occurrence type {1}, Day number {2}")
    public void fillScheduleOptionsStepRecurringIncrementalTypeMonthlyDayOfMonthCase2(ScheduleTypeMonthly
        scheduleTypeMonthly, ScheduleTypeMonthly occurrenceType,
        ScheduleTypeMonthly dayNumber) throws InterruptedException {

        nbfFilesWizardScheduleOptionsPage.selectRecurringIncremental();
        checkNoSchedule(DISABLED_RADIOBUTTON);
        checkRecurringIncremental(ENABLED_RADIOBUTTON);
        checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
        //select Daily type, because Monthly is default
        nbfFilesWizardScheduleOptionsPage.selectScheduleType("Daily");
        //select Monthly again
        nbfFilesWizardScheduleOptionsPage.selectScheduleType(String.valueOf(scheduleTypeMonthly));
        nbfFilesWizardScheduleOptionsPage.selectOccurrenceType(String.valueOf(occurrenceType));
        nbfFilesWizardScheduleOptionsPage.selectDayNumber(String.valueOf(dayNumber));
        nbfFilesWizardScheduleOptionsPage.setOccursAt(hours, minutes, meridiem);
        nbfFilesWizardScheduleOptionsPage.setRepeatEvery(repeatEvery);
        nbfFilesWizardScheduleOptionsPage.setStartFrom(startFrom);
        nbfFilesWizardScheduleOptionsPage.saveScheduleModal();
        Thread.sleep(2000);
    }

    @Step("Recurring Schedule. Verify Case 2. Type Monthly Day of Month. Schedule type {0},"
            + "Occurrence type {1}, Day number {2}")
    public void verifyScheduleOptionsStepRecurringIncrementalTypeMonthlyDayOfMonthCase2(ScheduleTypeMonthly
        scheduleTypeMonthly, ScheduleTypeMonthly occurrenceType,
        ScheduleTypeMonthly dayNumber) throws InterruptedException {

        checkNoSchedule(DISABLED_RADIOBUTTON);
        checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
        checkRecurringIncremental(ENABLED_RADIOBUTTON);
        nbfFilesWizardScheduleOptionsPage.editRecurringIncremental();
        checkScheduleType(String.valueOf(scheduleTypeMonthly));
        checkOccurrenceType(String.valueOf(occurrenceType));
        checkDayNumber(String.valueOf(dayNumber));
        //checkTotalInfoMonthly(dayNumber);
        nbfFilesWizardScheduleOptionsPage.saveScheduleModal();
        Thread.sleep(3500);
        checkExecuteFullBackupSynthetic(DISABLED_CHECKBOX);
        checkStopThePlanIfItRunsFor(UNCHECKED_CHECKBOX, stopThePlanIfItRunsForHours,
                stopThePlanIfItRunsForMinutes);
        checkAlertPlanAsOverdueAfter(UNCHECKED_CHECKBOX, alertPlanAsOverdueAfterNumber, "Hour(s)");
        checkRunMissedScheduledPlanImmediatelyWhenComputerStartsUp(UNCHECKED_CHECKBOX);

    }


    @Step("Case 3. Set Recurring Schedule Option. Type Daily. Type Monthly Day of Month. Schedule type {0},"
            + "Occurrence type {1}, Day {2}")
    public void fillScheduleOptionsStepRecurringIncrementalTypeDailyCase3(ScheduleTypeDaily scheduleTypeDaily,
        ScheduleTypeDaily day) throws InterruptedException {
        nbfFilesWizardScheduleOptionsPage.selectRecurringIncremental();
        checkNoSchedule(DISABLED_RADIOBUTTON);
        checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
        nbfFilesWizardScheduleOptionsPage.selectScheduleType(String.valueOf(scheduleTypeDaily));
        nbfFilesWizardScheduleOptionsPage.selectDay(String.valueOf(day));
        nbfFilesWizardScheduleOptionsPage.selectOccursEveryType();
        nbfFilesWizardScheduleOptionsPage.setOccursEvery(occursEvery, occursEveryTypeMinutes);
        nbfFilesWizardScheduleOptionsPage
                .setFromToTime(timeFromHours, timeFromMinutes, meridiem, timeToHours, timeToMinutes);
        nbfFilesWizardScheduleOptionsPage.setRepeatEvery(repeatEvery);
        nbfFilesWizardScheduleOptionsPage.setStartFrom(startFrom);
        nbfFilesWizardScheduleOptionsPage.saveScheduleModal();
        Thread.sleep(2000);

    }

    @Step("Recurring Schedule. Verify Case 3. Type Monthly Day of Month. Schedule type {0}")
    public void verifyScheduleOptionsStepRecurringIncrementalTypeDailyCase3(ScheduleTypeDaily scheduleTypeDaily)
            throws InterruptedException {

        checkNoSchedule(DISABLED_RADIOBUTTON);
        checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
        checkRecurringIncremental(ENABLED_RADIOBUTTON);
        nbfFilesWizardScheduleOptionsPage.editRecurringIncremental();
        checkScheduleType(String.valueOf(scheduleTypeDaily));
        checkDays(CHECKED_CHECKBOX,
                UNCHECKED_CHECKBOX,
                CHECKED_CHECKBOX,
                UNCHECKED_CHECKBOX,
                UNCHECKED_CHECKBOX,
                UNCHECKED_CHECKBOX,
                UNCHECKED_CHECKBOX);
        //checkTotalInfoDailyOccursEvery(day);
        nbfFilesWizardScheduleOptionsPage.saveScheduleModal();
        Thread.sleep(3500);
        checkExecuteFullBackupSynthetic(DISABLED_CHECKBOX);
        checkStopThePlanIfItRunsFor(UNCHECKED_CHECKBOX, stopThePlanIfItRunsForHours,
                stopThePlanIfItRunsForMinutes);
        checkAlertPlanAsOverdueAfter(UNCHECKED_CHECKBOX, alertPlanAsOverdueAfterNumber, "Hour(s)");
        checkRunMissedScheduledPlanImmediatelyWhenComputerStartsUp(UNCHECKED_CHECKBOX);

    }

    @Step("Case 1. Set Recurring (incremental) Schedule Option. Type Monthly. "
            + "Type Monthly Day of Month. Schedule type {0},"
            + "Occurrence type {1}, Day {2}")
    public void fillScheduleOptionsStepFullTypeMonthlyCase1(ScheduleTypeMonthly scheduleTypeMonthly,
        ScheduleTypeMonthly occurrenceType, ScheduleTypeMonthly dayType) throws InterruptedException {

        nbfFilesWizardScheduleOptionsPage.selectRecurringIncremental();
        nbfFilesWizardScheduleOptionsPage.saveScheduleModal();
        Thread.sleep(2000);
        nbfFilesWizardScheduleOptionsPage.selectExecuteFullBackup();
        checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
        //select Daily type, because Monthly is default
        nbfFilesWizardScheduleOptionsPage.selectScheduleType("Daily");
        //select Monthly again
        nbfFilesWizardScheduleOptionsPage.selectScheduleType(String.valueOf(scheduleTypeMonthly));
        nbfFilesWizardScheduleOptionsPage.selectOccurrenceType(String.valueOf(occurrenceType));
        nbfFilesWizardScheduleOptionsPage.selectDayType(String.valueOf(dayType));
        nbfFilesWizardScheduleOptionsPage.setRepeatEvery(repeatEvery);
        nbfFilesWizardScheduleOptionsPage.setStartFrom(startFrom);
        nbfFilesWizardScheduleOptionsPage.saveScheduleModal();
        Thread.sleep(2000);
        nbfFilesWizardScheduleOptionsPage
                .selectStopThePlanIfItRunsFor(stopThePlanIfItRunsForHours, stopThePlanIfItRunsForMinutes);
        nbfFilesWizardScheduleOptionsPage.selectAlertOverdueAfter(alertPlanAsOverdueAfterNumber);
        nbfFilesWizardScheduleOptionsPage.selectRunMissedSchedule();

    }


    @Step("Recurring Schedule. Verify Case 1. Type Monthly Day of Month. Schedule type {0},"
            + "Occurrence type {1}, Day {2}")
    public void verifyScheduleOptionsStepFullTypeMonthlyCase1(ScheduleTypeMonthly scheduleTypeMonthly,
        ScheduleTypeMonthly occurrenceType, ScheduleTypeMonthly dayType) throws InterruptedException {

        checkNoSchedule(DISABLED_RADIOBUTTON);
        checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
        checkRecurringIncremental(ENABLED_RADIOBUTTON);
        nbfFilesWizardScheduleOptionsPage.editExecuteFullBackup();
        checkScheduleType(String.valueOf(scheduleTypeMonthly));
        checkOccurrenceType(String.valueOf(occurrenceType));
        checkDayType(String.valueOf(dayType));
        nbfFilesWizardScheduleOptionsPage.saveScheduleModal();
        Thread.sleep(3500);
        checkExecuteFullBackupSynthetic(CHECKED_CHECKBOX);
        checkStopThePlanIfItRunsFor(CHECKED_CHECKBOX,
                stopThePlanIfItRunsForHours, stopThePlanIfItRunsForMinutes);
        checkAlertPlanAsOverdueAfter(CHECKED_CHECKBOX, alertPlanAsOverdueAfterNumber, "Hour(s)");
        checkRunMissedScheduledPlanImmediatelyWhenComputerStartsUp(CHECKED_CHECKBOX);

    }


    @Step("Case 2. Set Recurring Schedule Option. Type Monthly Day of Month. "
            + "Type Monthly Day of Month. Schedule type {0},Occurrence type {1}, Day {2}")
    public void fillScheduleOptionsStepFullTypeMonthlyDayOfMonthCase2(ScheduleTypeMonthly scheduleTypeMonthly,
        ScheduleTypeMonthly occurrenceType, ScheduleTypeMonthly dayNumber) throws InterruptedException {

        nbfFilesWizardScheduleOptionsPage.selectRecurringIncremental();
        nbfFilesWizardScheduleOptionsPage.saveScheduleModal();
        Thread.sleep(2000);
        nbfFilesWizardScheduleOptionsPage.selectExecuteFullBackup();
        //select Daily type, because Monthly is default
        nbfFilesWizardScheduleOptionsPage.selectScheduleType("Daily");
        //select Monthly again
        nbfFilesWizardScheduleOptionsPage.selectScheduleType(String.valueOf(scheduleTypeMonthly));
        nbfFilesWizardScheduleOptionsPage.selectOccurrenceType(String.valueOf(occurrenceType));
        nbfFilesWizardScheduleOptionsPage.selectDayNumber(String.valueOf(dayNumber));
        nbfFilesWizardScheduleOptionsPage.setRepeatEvery(repeatEvery);
        nbfFilesWizardScheduleOptionsPage.setStartFrom(startFrom);
        nbfFilesWizardScheduleOptionsPage.saveScheduleModal();
        Thread.sleep(2000);

    }

    @Step("Full Schedule. Verify Case 2. Type Monthly Day of Month.Schedule type {0},Occurrence type {1},Day number{2}")
    public void verifyScheduleOptionsStepFullTypeMonthlyDayOfMonthCase2(ScheduleTypeMonthly scheduleTypeMonthly,
        ScheduleTypeMonthly occurrenceType, ScheduleTypeMonthly dayNumber) throws InterruptedException {

        checkNoSchedule(DISABLED_RADIOBUTTON);
        checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
        checkRecurringIncremental(ENABLED_RADIOBUTTON);
        nbfFilesWizardScheduleOptionsPage.editExecuteFullBackup();
        checkScheduleType(String.valueOf(scheduleTypeMonthly));
        checkOccurrenceType(String.valueOf(occurrenceType));
        checkDayNumber(String.valueOf(dayNumber));
        //checkTotalInfoMonthly(dayNumber);
        nbfFilesWizardScheduleOptionsPage.saveScheduleModal();
        Thread.sleep(3500);
        checkExecuteFullBackupSynthetic(CHECKED_CHECKBOX);
        checkStopThePlanIfItRunsFor(UNCHECKED_CHECKBOX, stopThePlanIfItRunsForHours,
                stopThePlanIfItRunsForMinutes);
        checkAlertPlanAsOverdueAfter(UNCHECKED_CHECKBOX, alertPlanAsOverdueAfterNumber, "Hour(s)");
        checkRunMissedScheduledPlanImmediatelyWhenComputerStartsUp(UNCHECKED_CHECKBOX);

    }


    @Step("Case 3. Set Full Schedule Option. Type Daily. Schedule type {0},Day number{1}")
    public void fillScheduleOptionsStepFullTypeDailyCase3(ScheduleTypeDaily scheduleTypeDaily,
        ScheduleTypeDaily day) throws InterruptedException {

        nbfFilesWizardScheduleOptionsPage.selectRecurringIncremental();
        nbfFilesWizardScheduleOptionsPage.saveScheduleModal();
        Thread.sleep(1500);
        nbfFilesWizardScheduleOptionsPage.selectExecuteFullBackup();
        nbfFilesWizardScheduleOptionsPage.selectScheduleType(String.valueOf(scheduleTypeDaily));
        nbfFilesWizardScheduleOptionsPage.selectDay(String.valueOf(day));
        nbfFilesWizardScheduleOptionsPage.setRepeatEvery(repeatEvery);
        nbfFilesWizardScheduleOptionsPage.setStartFrom(startFrom);
        nbfFilesWizardScheduleOptionsPage.saveScheduleModal();
        Thread.sleep(2000);

    }

    @Step("Full Schedule. Verify Case 3. Schedule type {0}")
    public void verifyScheduleOptionsStepFullTypeDailyCase3(ScheduleTypeDaily scheduleTypeDaily)
            throws InterruptedException {

        checkNoSchedule(DISABLED_RADIOBUTTON);
        checkForeverForwardIncremental(DISABLED_RADIOBUTTON);
        checkRecurringIncremental(ENABLED_RADIOBUTTON);
        nbfFilesWizardScheduleOptionsPage.editExecuteFullBackup();
        checkScheduleType(String.valueOf(scheduleTypeDaily));
        checkDays(UNCHECKED_CHECKBOX,
                UNCHECKED_CHECKBOX,
                CHECKED_CHECKBOX,
                UNCHECKED_CHECKBOX,
                UNCHECKED_CHECKBOX,
                UNCHECKED_CHECKBOX,
                UNCHECKED_CHECKBOX);
        //checkTotalInfoDailyOccursEvery(day);
        nbfFilesWizardScheduleOptionsPage.saveScheduleModal();
        Thread.sleep(3500);
        checkExecuteFullBackupSynthetic(CHECKED_CHECKBOX);
        checkStopThePlanIfItRunsFor(UNCHECKED_CHECKBOX, stopThePlanIfItRunsForHours, stopThePlanIfItRunsForMinutes);
        checkAlertPlanAsOverdueAfter(UNCHECKED_CHECKBOX, alertPlanAsOverdueAfterNumber, "Hour(s)");
        checkRunMissedScheduledPlanImmediatelyWhenComputerStartsUp(UNCHECKED_CHECKBOX);
    }

    @Step("Checking no schedule option. Radiobutton {0}")
    public void checkNoSchedule(String radiobuttonState) {
        Assert.assertEquals(nbfFilesWizardScheduleOptionsPage.checkNoSchedule(), radiobuttonState);
    }

    @Step("Checking FFI schedule option. Radiobutton {0}")
    public void checkForeverForwardIncremental(String radiobuttonState) {
        Assert.assertEquals(nbfFilesWizardScheduleOptionsPage.checkForeverForwardIncremental(), radiobuttonState);
    }

    @Step("Checking FFI alert. Destination {0}")
    public void checkForeverForwardIncrementalAlert(String destination) {
        Assert.assertEquals(nbfFilesWizardScheduleOptionsPage.checkForeverForwardIncrementalAlert(),
                " new Forever Forward Incremental Forever "
                        + "Forward Incremental is not supported for the selected backup storage: " + destination + " ");
    }

    @Step("Checking Recurring (incremental). Radiobutton {0}")
    public void checkRecurringIncremental(String radiobuttonState) {
        Assert.assertEquals(nbfFilesWizardScheduleOptionsPage.checkRecurringIncremental(), radiobuttonState);
    }

    @Step("Checking Execute Full backup Synthetic. Checkbox {0}")
    public void checkExecuteFullBackupSynthetic(String checkboxState) {
        Assert.assertEquals(nbfFilesWizardScheduleOptionsPage.checkFullSchedule(), checkboxState);
    }

    @Step("Checking Schedule Type. Type {0} ")
    public void checkScheduleType(String type) {
        Assert.assertEquals(nbfFilesWizardScheduleOptionsPage
                .isDataExist(nbfFilesWizardScheduleOptionsPage.checkScheduleType(type)), type);
    }

    @Step("Checking Occurrence Type. Type {0} ")
    public void checkOccurrenceType(String type) {
        Assert.assertEquals(nbfFilesWizardScheduleOptionsPage
                .isDataExist(nbfFilesWizardScheduleOptionsPage.checkOccurrenceType(type)), type);
    }

    @Step("Checking Day Type. Type {0} ")
    public void checkDayType(String type) {
        Assert.assertEquals(nbfFilesWizardScheduleOptionsPage
                .isDataExist(nbfFilesWizardScheduleOptionsPage.checkDayType(type)), type);
    }

    @Step("Checking Day Number. Type {0} ")
    public void checkDayNumber(String type) {
        Assert.assertEquals(nbfFilesWizardScheduleOptionsPage
                .isDataExist(nbfFilesWizardScheduleOptionsPage.checkDayNumber(type)), type);
    }

    @Step("Checking Days Sunday. Sun {0}, Mon {1}, Tue {2}, Wed {3}, Thu {4}, Fri {5}, Sat {6}")
    public void checkDays(String checkboxStateSunday, String checkboxStateMonday,
                          String checkboxStateTuesday, String checkboxStateWednesday,
                          String checkboxStateThursday, String checkboxStateFriday, String checkboxStateSaturday) {
        Assert.assertEquals(nbfFilesWizardScheduleOptionsPage.checkDaySunday(), checkboxStateSunday);
        Assert.assertEquals(nbfFilesWizardScheduleOptionsPage.checkDayMonday(), checkboxStateMonday);
        Assert.assertEquals(nbfFilesWizardScheduleOptionsPage.checkDayTuesday(), checkboxStateTuesday);
        Assert.assertEquals(nbfFilesWizardScheduleOptionsPage.checkDayMonday(), checkboxStateWednesday);
        Assert.assertEquals(nbfFilesWizardScheduleOptionsPage.checkDayMonday(), checkboxStateThursday);
        Assert.assertEquals(nbfFilesWizardScheduleOptionsPage.checkDayMonday(), checkboxStateFriday);
        Assert.assertEquals(nbfFilesWizardScheduleOptionsPage.checkDayMonday(), checkboxStateSaturday);
    }


    /*    @Step("Checking Total Info Monthly")
    public void checkTotalInfoMonthly(ScheduleTypeMonthly dayNumber) {
        Assert.assertEquals(nbfFilesWizardScheduleOptionsPage.checkTotalInfo(),
                " Forever Forward Incremental: "
                        + "Occurs every " + REPEAT_EVERY + " month on " + dayNumber + " day "
                        + "at " + HOURS + ":" + MINUTES + " " + MERIDIEM + " ");
    }*/

    /*    @Step("Checking Total Info Daily")
    public void checkTotalInfoDailyOccursEvery(ScheduleTypeDaily day) {
        Assert.assertEquals(nbfFilesWizardScheduleOptionsPage.checkTotalInfo(),
                " Forever Forward Incremental: " +
                        "Occurs every 1 week on Sunday" + day + " at " + HOURS + ":" + MINUTES + " " + MERIDIEM + " ");
    }*/

    @Step("Checking Stop The Plan If It Runs For. Checkbox {0}, Hour/s {1}, Minute/s {2}")
    public void checkStopThePlanIfItRunsFor(String checkboxState, String hours, String minutes) {
        Assert.assertEquals(nbfFilesWizardScheduleOptionsPage.checkStopThePlanIfItRunsFor(), checkboxState);

        if (checkboxState.equals(CHECKED_CHECKBOX)) {
            Assert.assertEquals(nbfFilesWizardScheduleOptionsPage.checkStopThePlanIfItRunsForHours(), hours);
            Assert.assertEquals(nbfFilesWizardScheduleOptionsPage.checkStopThePlanIfItRunsForMinutes(), minutes);
        }

    }

    @Step("Checking Alert plan as overdue after. Checkbox {0}, number {1}, type {2}")
    public void checkAlertPlanAsOverdueAfter(String checkboxState, String number, String type) {
        Assert.assertEquals(nbfFilesWizardScheduleOptionsPage.checkAlertPlanAsOverdueAfter(), checkboxState);

        if (checkboxState.equals(CHECKED_CHECKBOX)) {
            Assert.assertEquals(nbfFilesWizardScheduleOptionsPage.checkAlertPlanAsOverdueAfterField(), number);
            Assert.assertEquals(nbfFilesWizardScheduleOptionsPage.checkAlertPlanAsOverdueType(), type);
        }
    }

    @Step("Checking Run missed scheduled plan immediately when computer starts up. Checkbox {0},")
    public void checkRunMissedScheduledPlanImmediatelyWhenComputerStartsUp(String checkboxState) {
        Assert.assertEquals(nbfFilesWizardScheduleOptionsPage
                .checkRunMissedScheduledPlanImmediatelyWhenComputerStartsUp(), checkboxState);
    }

}
