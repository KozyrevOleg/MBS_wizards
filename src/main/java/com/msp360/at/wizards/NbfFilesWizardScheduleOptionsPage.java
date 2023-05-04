package com.msp360.at.wizards;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NbfFilesWizardScheduleOptionsPage extends BaseClassPage {

    JavascriptExecutor js = (JavascriptExecutor) driver;

    public NbfFilesWizardScheduleOptionsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//label[@for='schedule-type-radio-1']")
    private WebElement noSchedule;

    public void selectNoSchedule() {
        this.noSchedule.click();
    }

    @FindBy(xpath = "//label[@for='schedule-type-radio-3']")
    private WebElement selectRecurringIncremental;

    public void selectRecurringIncremental() {
        this.selectRecurringIncremental.click();
    }

    @FindBy(xpath = "//mbs-radio[@value='predefined']//button[@type='button'][normalize-space()='Edit']")
    private WebElement editRecurringIncremental;

    public void editRecurringIncremental() {
        this.editRecurringIncremental.click();
    }


    @FindBy(xpath = "//label[@for='full-backup-enabled-checkbox']")
    private WebElement executeFullBackup;

    public void selectExecuteFullBackup() {
        wait.until(ExpectedConditions.elementToBeClickable(this.executeFullBackup)).click();
    }

    @FindBy(xpath = "//button[@id='full-backup-enabled-checkbox-edit']")
    private WebElement editExecuteFullBackup;

    public void editExecuteFullBackup() {
        this.editExecuteFullBackup.click();
    }

    @FindBy(xpath = "//label[@for='stop-after-enabled-checkbox']")
    private WebElement stopThePlanIfItRunsFor;


    @FindBy(xpath = "//input[@id='stop-after-hours-number']")
    private WebElement stopThePlanIfItRunsForHours;

    @FindBy(xpath = "//input[@id='stop-after-minutes-number']")
    private WebElement stopThePlanIfItRunsForMinutes;

    public void selectStopThePlanIfItRunsFor(String hours, String minutes) throws InterruptedException {
        Thread.sleep(2000);
        this.stopThePlanIfItRunsFor.click();
        this.stopThePlanIfItRunsForHours.sendKeys(Keys.BACK_SPACE);
        for (int i = 0; i < hours.length(); i++) {
            char c = hours.charAt(i);
            String s = String.valueOf(c);
            this.stopThePlanIfItRunsForHours.sendKeys(s);
        }
        this.stopThePlanIfItRunsForMinutes.sendKeys(Keys.BACK_SPACE);

        for (int i = 0; i < minutes.length(); i++) {
            char c = minutes.charAt(i);
            String s = String.valueOf(c);
            this.stopThePlanIfItRunsForMinutes.sendKeys(s);
        }
    }

    @FindBy(xpath = "//label[@for='overdue-after-success-enabled-checkbox']")
    private WebElement alertPlanAsOverdueAfterCheckbox;

    public void selectAlertPlanAsOverdueAfter() {
        this.alertPlanAsOverdueAfterCheckbox.click();
    }

    @FindBy(xpath = "//input[@class='form-control mbs-number-input ng-untouched ng-pristine ng-valid']")
    private WebElement alertPlanAsOverdueField;

    public void selectAlertPlanAsOverdueField() {
        this.alertPlanAsOverdueField.sendKeys();
    }

    @FindBy(xpath = "//div[@role='combobox']")
    private WebElement alertPlanOverdueAfterDropDown;

    @FindBy(xpath = "//span[@class='ng-option-label']")
    private WebElement alertPlanOverdueAfterDropDownChangeDefaultValue;

    @FindBy(xpath = "//mbs-number[@formcontrolname='overdueAfterSuccessAmount']//input")
    private WebElement alertPlanOverdueAfterField;

    public void selectAlertOverdueAfter(String overdueNumber) {
        alertPlanAsOverdueAfterCheckbox.click();
        alertPlanOverdueAfterDropDown.click();
        alertPlanOverdueAfterDropDownChangeDefaultValue.click();
        alertPlanOverdueAfterField.sendKeys(Keys.BACK_SPACE);
        alertPlanOverdueAfterField.sendKeys(overdueNumber);
    }

    @FindBy(xpath = "//label[@for='run-missed-checkbox']")
    private WebElement runMissedSchedule;

    public void selectRunMissedSchedule() {
        runMissedSchedule.click();
    }

    // Schedule option
    @FindBy(xpath = "//label[@for='schedule-type-radio-6']")
    private WebElement foreverForwardIncremental;

    public void selectForeverForwardIncremental() {
        this.foreverForwardIncremental.click();
    }

    @FindBy(xpath = "//mbs-radio[@value='ffi']")
    private WebElement alertForeverForwardIncremental;


    @FindBy(xpath = "//span[@class='mbs-radio_label-content']//span//button[@type='button'][normalize-space()='Edit']")
    private WebElement editForeverForwardIncremental;

    public void editForeverForwardIncremental() {
        this.editForeverForwardIncremental.click();
    }

    @FindBy(xpath = "//mbs-select[@formcontrolname='recurringPeriod']//span[@class='ng-arrow-wrapper']")
    private WebElement scheduleTypeDropDown;

    @FindBy(xpath = "//label[@for='modal-occurs-at-radio-2']")
    private WebElement occursEveryRadioButton;

    public void selectOccursEveryType() {
        this.occursEveryRadioButton.click();
    }

    @FindBy(xpath = "//mbs-number[@formcontrolname='occursEveryCount']"
            + "//input[@class='form-control mbs-number-input ng-untouched ng-pristine ng-valid']")
    private WebElement occursEvery;

    @FindBy(xpath = "//span[normalize-space()='Hour(s)']")
    private WebElement selectOccursEveryType;

    //span[normalize-space()='Hour(s)']


    public void selectType(String type) {
        WebElement ele = driver.findElement(By.xpath("//span[normalize-space()='" + type + "']"));
        ele.click();
    }

    public void setOccursEvery(String number, String type) {
        /*this.occursEvery.sendKeys(Keys.BACK_SPACE);
        this.occursEvery.click();*/
        //this.occursEvery.click();
        Actions act = new Actions(driver);
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            String s = String.valueOf(c);
            this.occursEvery.sendKeys(s);
        }
        act.click(selectOccursEveryType).perform();
        selectType(type);
    }

    public void selectScheduleType(String type) {
        this.scheduleTypeDropDown.click();
        WebElement scheduleType = driver.findElement(By.xpath("//span[normalize-space()='" + type + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(scheduleType)).click();
    }

    @FindBy(xpath = "//mbs-select[@formcontrolname='occurrence']//span[@class='ng-arrow-wrapper']")
    private WebElement occurrenceTypeDropdown;

    public void selectOccurrenceType(String type) {
        this.occurrenceTypeDropdown.click();
        WebElement occurrenceType = driver.findElement(By.xpath("//span[normalize-space()='" + type + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(occurrenceType)).click();
    }

    @FindBy(xpath = "//mbs-select[@formcontrolname='dayOfWeek']//span[@class='ng-arrow-wrapper']")
    private WebElement dayType;

    @FindBy(xpath = "//input[@id='modal-day-of-month-count']")
    private WebElement dayNumber;

    @FindBy(xpath = "//div[@class='mbs-card_body']//p[@class='mb-0']")
    private WebElement totalInfo;

    public void selectDayType(String type) {
        this.dayType.click();
        selectDay(type);
    }

    public void selectDay(String type) {
        WebElement dayType = driver.findElement(By.xpath("//span[normalize-space()='" + type + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(dayType)).click();

    }

    public void selectDayNumber(String number) {
        this.dayNumber.sendKeys(Keys.BACK_SPACE);
        this.dayNumber.sendKeys(number);
    }

    @FindBy(xpath = "//button[@class='btn btn-primary'][normalize-space()='Save']")
    private WebElement scheduleModalSaveButton;

    public void saveScheduleModal() {
        this.scheduleModalSaveButton.click();
    }

    @FindBy(xpath = "//label[@for='run-missed-checkbox']")
    private WebElement runMissedScheduledPlanImmediately;

    public void selectRunMissedScheduledPlanImmediately() {
        this.runMissedScheduledPlanImmediately.click();
    }

    @FindBy(xpath = "//div[@class='input-group mbs-timepicker_input-group']")
    private WebElement occursAt;

    @FindBy(xpath = "//input[@placeholder='HH']")
    private WebElement selectHours;

    @FindBy(xpath = "//input[@placeholder='MM']")
    private WebElement selectMinutes;


    public void occursAtSelectMeridiem(String meridiem) {
        WebElement mer = driver.findElement(By.xpath("//button[normalize-space()='" + meridiem + "']"));
        mer.click();
    }

    public void occursEveryFromSelectMeridiem(String meridiem) {
        WebElement mer = driver.findElement(By.xpath("//div[@class='mbs-timepicker_dropdown -arrow dropdown-menu show']"
                + "//button[@type='button'][normalize-space()='" + meridiem + "']"));
        mer.click();
    }

    public void occursEveryToSelectMeridiem() {
        WebElement mer = driver.findElement(By.xpath("//ngb-timepicker[@class='mbs-timepicker_timepicker "
                + "ng-pristine ng-valid ng-touched']//div[@class='ngb-tp-meridian']"));
        mer.click();
    }


    public void setOccursAt(String hour, String minute, String meridiem) {
        Actions act = new Actions(driver);
        this.occursAt.click();
        act.doubleClick(selectHours).perform();
        this.selectHours.sendKeys(hour);
        act.doubleClick(selectMinutes).perform();
        this.selectMinutes.sendKeys(minute);
        occursAtSelectMeridiem(meridiem);
    }

    @FindBy(xpath = "//div[@class='mbs-timepicker_dropdown -arrow dropdown-menu show']//input[@placeholder='HH']")
    private WebElement occursAtDailyTypeHours;


    @FindBy(xpath = "//div[@class='mbs-timepicker_dropdown -arrow dropdown-menu show']//input[@placeholder='MM']")
    private WebElement occursAtDailyTypeMinutes;

    @FindBy(xpath = "//div[@class='mbs-timepicker_dropdown -arrow dropdown-menu show']//input[@placeholder='HH']")
    private WebElement occursEveryToTimeHours;


    @FindBy(xpath = "//div[@class='mbs-timepicker_dropdown -arrow dropdown-menu show']//input[@placeholder='MM']")
    private WebElement occursEveryToTimeMinutes;

    @FindBy(xpath = "//input[@id='schedule-modal-repeat-every-count']")
    private WebElement repeatEvery;

    @FindBy(xpath = "//mbs-button[@class='mbs-number-btn -up']//button[@type='button']")
    private WebElement repeatEveryPlusButton;

    @FindBy(xpath = "//mbs-button[@class='mbs-number-btn -down']//button[@type='button']")
    private WebElement repeatEveryMinusButton;

    public void setRepeatEvery(String number) {
        this.repeatEvery.sendKeys(Keys.BACK_SPACE);
        this.repeatEvery.sendKeys(number);
    }

    @FindBy(xpath = "//input[@class='dropdown-toggle form-control']")
    private WebElement startFrom;

    public void setStartFrom(String startFromDate) {
        this.startFrom.clear();
        this.startFrom.sendKeys(startFromDate);
    }

    @FindBy(xpath = "//mbs-timepicker[@formcontrolname='occursEveryFromTime']"
            + "//div[@class='input-group mbs-timepicker_input-group']")
    private WebElement fromTime;

    @FindBy(xpath = "//input[@id='modal-occurs-every-to-time']")
    private WebElement toTime;

    public void setFromToTime(String fromHour, String fromMinute, String meridiem, String toHour, String toMinute)
            throws InterruptedException {
        Actions act = new Actions(driver);
        this.fromTime.click();
        act.doubleClick(occursAtDailyTypeHours).perform();
        this.occursAtDailyTypeHours.sendKeys(fromHour);
        act.doubleClick(occursAtDailyTypeMinutes).perform();
        this.occursAtDailyTypeMinutes.sendKeys(fromMinute);
        occursEveryFromSelectMeridiem(meridiem);

        this.toTime.click();
        act.doubleClick(occursAtDailyTypeHours).perform();
        this.occursEveryToTimeHours.sendKeys(toHour);
        act.doubleClick(occursAtDailyTypeMinutes).perform();
        this.occursEveryToTimeMinutes.sendKeys(toMinute);
        //occursEveryToSelectMeridiem();
    }


    //Assertions
    public String checkNoSchedule() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-form-group:nth-child(1) > div > mbs-radio:nth-child(1) > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkForeverForwardIncremental() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-form-group:nth-child(1) > div > mbs-radio:nth-child(2) > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkForeverForwardIncrementalAlert() {
        return this.alertForeverForwardIncremental.getDomProperty("textContent");
    }


    public String checkRecurringIncremental() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-form-group:nth-child(1) > div > mbs-radio:nth-child(3) > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkFullSchedule() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('div.col-9 > div > mbs-wizard-step.-active > mbs-schedule-step > section > div >"
                        + " mbs-form-group.mbs-form-group.ng-untouched.ng-pristine > div > "
                        + "mbs-checkbox > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkScheduleType(String type) {
        WebElement scheduleType = driver.findElement(By.xpath("//span[normalize-space()='" + type + "']"));
        return scheduleType.getDomProperty("textContent");
    }

    public String checkOccurrenceType(String type) {
        WebElement occurrenceType = driver.findElement(By.xpath("//span[normalize-space()='" + type + "']"));
        return occurrenceType.getDomProperty("textContent");
    }

    public String checkDayType(String type) {
        WebElement dayType = driver.findElement(By.xpath("//span[normalize-space()='" + type + "']"));
        return dayType.getDomProperty("textContent");
    }

    public String checkDayNumber(String number) {
        return this.dayNumber.getDomProperty("value");
    }

    public String checkTotalInfo() {
        return this.totalInfo.getDomProperty("textContent");
    }

    public String checkOccursAtHour(String hours) {
        return this.selectHours.getDomProperty("textContent");
    }

    public String checkOccursAtMinutes(String minutes) {
        return this.selectMinutes.getDomProperty("textContent");
    }

    public String checkOccursArMeridiem(String meridiem) {
        WebElement ele = driver.findElement(By.xpath("//button[normalize-space()='" + meridiem + "']"));
        return String.valueOf(ele);
    }

    public String checkRepeatEvery() {
        return this.repeatEvery.getDomProperty("value");
    }

    public String checkStartFrom() {
        return this.startFrom.getDomProperty("value");
    }

    public String checkStopThePlanIfItRunsFor() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-form-group:nth-child(3) > div > "
                        + "div > div.col-4 > mbs-checkbox > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkStopThePlanIfItRunsForHours() {
        return this.stopThePlanIfItRunsForHours.getDomProperty("value");
    }

    public String checkStopThePlanIfItRunsForMinutes() {
        return this.stopThePlanIfItRunsForMinutes.getDomProperty("value");
    }

    public String checkAlertPlanAsOverdueAfter() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-form-group:nth-child(4) > div > "
                        + "div > div.col-auto > mbs-checkbox > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkAlertPlanAsOverdueAfterField() {
        return this.alertPlanAsOverdueField.getDomProperty("value");
    }

    public String checkAlertPlanAsOverdueType() {
        return driver.findElement(By.xpath("//span[@class='ng-value-label']"))
                .getDomProperty("textContent");
    }

    public String checkRunMissedScheduledPlanImmediatelyWhenComputerStartsUp() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-form-group.mbs-form-group.mb-5 > div > mbs-checkbox > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkDaySunday() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-form-group.mbs-form-group.mb-4 > div > div > "
                        + "div.col-8 > div > mbs-checkbox:nth-child(1) > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkDayMonday() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-form-group.mbs-form-group.mb-4 > div > div > "
                        + "div.col-8 > div > mbs-checkbox:nth-child(2) > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkDayTuesday() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-form-group.mbs-form-group.mb-4 > div > div > "
                        + "div.col-8 > div > mbs-checkbox:nth-child(3) > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkDayWednesday() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-form-group.mbs-form-group.mb-4 > div > div > "
                        + "div.col-8 > div > mbs-checkbox:nth-child(4) > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkDayThursday() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-form-group.mbs-form-group.mb-4 > div > div > "
                        + "div.col-8 > div > mbs-checkbox:nth-child(5) > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkDayFriday() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-form-group.mbs-form-group.mb-4 > div > div > "
                        + "div.col-8 > div > mbs-checkbox:nth-child(6) > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkDaySaturday() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-form-group.mbs-form-group.mb-4 > div > div > "
                        + "div.col-8 > div > mbs-checkbox:nth-child(7) > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

}
