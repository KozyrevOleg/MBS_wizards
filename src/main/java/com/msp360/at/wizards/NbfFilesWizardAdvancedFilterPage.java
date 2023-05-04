package com.msp360.at.wizards;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class NbfFilesWizardAdvancedFilterPage extends BaseClassPage {

    JavascriptExecutor js = (JavascriptExecutor) driver;

    public NbfFilesWizardAdvancedFilterPage(WebDriver driver) {
        super(driver);
    }

    //ADVANCED FILTER STEP
    @FindBy(xpath = "//label[@for='backup-all-files-radio-0']")
    private WebElement backUpAllFilesInSelectedFolders;

    public void selectBackUpAllFilesInSelectedFolders() {
        this.backUpAllFilesInSelectedFolders.click();
    }

    @FindBy(xpath = "//label[@for='backup-all-files-radio-1']")
    private WebElement backUpFilesOfSpecifiedTypes;

    @FindBy(xpath = "//input[@id='include-mask-input']")
    private WebElement backUpFilesOfSpecifiedTypesInputField;

    public void selectBackUpFilesOfSpecifiedTypes(String filesType) {
        this.backUpFilesOfSpecifiedTypes.click();
        for (int i = 0; i < filesType.length(); i++) {
            char c = filesType.charAt(i);
            String s = String.valueOf(c);
            this.backUpFilesOfSpecifiedTypesInputField.sendKeys(s);
        }
    }

    @FindBy(xpath = "//label[@for='backup-all-files-radio-2']")
    private WebElement doNotBackUpFilesOfTheseTypes;

    @FindBy(xpath = "//input[@id='exclude-mask-input']")
    private WebElement doNotBackUpFilesOfTheseTypesInputField;

    public void selectDoNotBackUpFilesOfTheseTypes(String filesType) {
        this.doNotBackUpFilesOfTheseTypes.click();
        for (int i = 0; i < filesType.length(); i++) {
            char c = filesType.charAt(i);
            String s = String.valueOf(c);
            this.doNotBackUpFilesOfTheseTypesInputField.sendKeys(s);
        }
    }

    @FindBy(xpath = "//label[@for='skip-folders-checkbox']")
    private WebElement skipFolders;

    @FindBy(xpath = "//input[@id='skip-folder-name']")
    private WebElement skipFoldersInputField;

    public void selectSkipFolders(String foldersType) {
        this.skipFolders.click();
        for (int i = 0; i < foldersType.length(); i++) {
            char c = foldersType.charAt(i);
            String s = String.valueOf(c);
            this.skipFoldersInputField.sendKeys(s);
        }
    }

    //BACKUP-11408 Plan Wizard: "Back up empty folders" option. Which storage account supports this option?

    @FindBy(xpath = "//label[@for='backup-empty-checkbox']")
    private WebElement backupEmptyFolders;

    public void selectBackupEmptyFolders() {
        this.backupEmptyFolders.click();
    }

    @FindBy(xpath = "//label[@for='backup-files-modified-checkbox']")
    private WebElement backUpObjectsModified;

    @FindBy(xpath = "//input[@id='days-ago-input-number']")
    private WebElement backUpObjectsModifiedDays;

    public void selectBackUpObjectsModified(String daysAgo) {
        this.backUpObjectsModified.click();
        this.backUpObjectsModifiedDays.clear();
        this.backUpObjectsModifiedDays.sendKeys(Keys.BACK_SPACE);
        for (int i = 0; i < daysAgo.length(); i++) {
            char c = daysAgo.charAt(i);
            String s = String.valueOf(c);
            this.backUpObjectsModifiedDays.sendKeys(s);
        }
    }

    @FindBy(xpath = "//label[@for='backup-files-modified-since-checkbox']")
    private WebElement backUpObjectsModifiedSince;

    //Date Picker
    @FindBy(xpath = "//span[@class='ico ico-Calendar']")
    private WebElement backUpObjectsModifiedSinceCalendar;

    @FindBy(xpath = "//select[@title='Select month']")
    private WebElement backUpObjectsModifiedSinceCalendarMonth;

    @FindBy(xpath = "//option[@value='2']")
    private WebElement selectFebruary;

    @FindBy(xpath = "//span[@class='mbs-datepicker_datepicker-day'][normalize-space()='2']")
    private WebElement selectDate;

    @FindBy(xpath = "//select[@title='Select year']")
    private WebElement selectYear;

    //TimePicker
    @FindBy(xpath = "//label[normalize-space()='Time']")
    private WebElement selectTime;

    @FindBy(xpath = "//div[@class='input-group mbs-timepicker_input-group']//button[@type='button']")
    private WebElement clickClock;

    @FindBy(xpath = "//input[@placeholder='HH']")
    private WebElement selectHour;

    @FindBy(xpath = "//input[@placeholder='MM']")
    private WebElement selectMinute;

    @FindBy(xpath = "//input[@id='backup-files-modified-since-time-picker']")
    private WebElement timePicker;

    @FindBy(xpath = "//div[@class='ngb-tp-meridian']")
    private WebElement selectAmPm;

    /*    public void selectAm (String time) {
        WebElement ele;
        if(ele.equals())
        driver.findElement(By.xpath("//button[normalize-space()='PM']"));
    }*/
    //button[normalize-space()='PM']

    public void selectBackUpObjectsModifiedSince() {
        this.backUpObjectsModifiedSince.click();
    }

    /*    public void selectTimePicker() {
        this.timePicker.sendKeys();
    }*/

    /*    public void selectBackUpObjectsModifiedSinceDateAndTime(String year, String hours, String minutes) {

        Actions act = new Actions(driver);
        this.backUpObjectsModifiedSinceCalendar.click();
        Select select = new Select(selectYear);
        select.selectByVisibleText(year);
        this.backUpObjectsModifiedSinceCalendarMonth.click();
        this.selectFebruary.click();
        this.selectDate.click();
        this.selectTime.click();
        act.doubleClick(selectHour).perform();
        this.selectHour.sendKeys(hours);
        act.doubleClick(selectMinute).perform();
        this.selectMinute.sendKeys(minutes);
    }*/

    @FindBy(xpath = "//input[@id='backup-files-modified-since-time-picker']")
    private WebElement backUpObjectsModifiedSinceTimePicker;

    @FindBy(xpath = "//input[@id='backup-files-modified-since-date-picker']")
    private WebElement backUpObjectsModifiedSinceDatePicker;

    public void selectBackUpObjectsModifiedSinceDateAndTimePicker(String datePicker, String hour,
                                                                  String minute, String meridiem) {
        this.backUpObjectsModifiedSinceDatePicker.click();
        this.backUpObjectsModifiedSinceDatePicker.clear();
        for (int i = 0; i < datePicker.length(); i++) {
            char c = datePicker.charAt(i);
            String s = String.valueOf(c);
            this.backUpObjectsModifiedSinceDatePicker.sendKeys(s);
        }

        this.backUpObjectsModifiedSinceTimePicker.click();
        /*Actions act = new Actions(driver);
        act.doubleClick(selectHour).perform();*/

        selectHour.click();
        selectHour.sendKeys(Keys.DELETE);
        selectHour.sendKeys(Keys.BACK_SPACE);
        for (int i = 0; i < hour.length(); i++) {
            char c = hour.charAt(i);
            String s = String.valueOf(c);
            this.selectHour.sendKeys(s);
        }

        //act.doubleClick(selectMinute).perform();

        selectMinute.click();
        selectMinute.sendKeys(Keys.DELETE);
        selectMinute.sendKeys(Keys.BACK_SPACE);
        for (int i = 0; i < minute.length(); i++) {
            char c = minute.charAt(i);
            String s = String.valueOf(c);
            this.selectMinute.sendKeys(s);
        }


        /*for (int i = 0; i < hour.length(); i++){
            char c = hour.charAt(i);
            String s = String.valueOf(c);
            this.selectHour.sendKeys(s);
        }

        act.doubleClick(selectMinute).perform();

        for (int i = 0; i < minute.length(); i++){
            char c = minute.charAt(i);
            String s = String.valueOf(c);
            this.selectMinute.sendKeys(s);
        }*/

        //act.doubleClick(selectAmPm).perform();
        this.timePicker.sendKeys(Keys.BACK_SPACE);
        this.timePicker.sendKeys(Keys.BACK_SPACE);

        for (int i = 0; i < meridiem.length(); i++) {
            char c = meridiem.charAt(i);
            String s = String.valueOf(c);
            this.backUpObjectsModifiedSinceTimePicker.sendKeys(s);
        }
        //this.backUpObjectsModifiedSinceTimePicker.sendKeys(timePicker);
    }

    @FindBy(xpath = "//label[@for='do-not-backup-files-checkbox']")
    public WebElement doNotBackUpFilesLargerThan;

    @FindBy(xpath = "//input[@id='file-size-input-number']")
    public WebElement doNotBackUpFilesLargerThanInputField;

    public void selectDoNotBackUpFilesLargerThan(String fileSize) {
        this.doNotBackUpFilesLargerThan.click();
        this.doNotBackUpFilesLargerThanInputField.clear();
        this.doNotBackUpFilesLargerThanInputField.sendKeys(Keys.BACK_SPACE);
        for (int i = 0; i < fileSize.length(); i++) {
            char c = fileSize.charAt(i);
            String s = String.valueOf(c);
            this.doNotBackUpFilesLargerThanInputField.sendKeys(s);
        }
    }

    @FindBy(xpath = "//label[@for='ignore-system-hidden-checkbox']")
    public WebElement doNotBackUpSystemAndHiddenFiles;

    public void selectDoNotBackUpSystemAndHiddenFiles() {
        this.doNotBackUpSystemAndHiddenFiles.click();
    }

    @FindBy(xpath = "//label[@for='ignore-locked-checkbox']")
    public WebElement doNotBackUpFilesUsedByOtherProcesses;

    public void selectDoNotBackUpFilesUsedByOtherProcesses() {
        this.doNotBackUpFilesUsedByOtherProcesses.click();
    }

    //for OneDrive files (Win 10 1709, Win Server 2019 or later)
    @FindBy(xpath = "//label[@for='ignore-on-demand-checkbox']")
    public WebElement doNotBackUpOnDemandFiles;

    public void selectDoNotBackUpOnDemandFiles() {
        this.doNotBackUpOnDemandFiles.click();
    }

    //Assertions
    public String checkBackUpAllFilesInSelectedFolders() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-radio:nth-child(1) > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }


    public String checkBackUpFilesOfSpecifiedTypesRadiobutton() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-radio:nth-child(2) > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkBackUpFilesOfSpecifiedTypesInputField() {
        return this.backUpFilesOfSpecifiedTypesInputField.getDomProperty("value");
    }

    public String checkDoNotBackUpFilesOfTheseTypesRadiobutton() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-radio:nth-child(3) > div.mbs-radio > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkDoNotBackUpFilesOfTheseTypesInputField() {
        return this.doNotBackUpFilesOfTheseTypesInputField.getDomProperty("value");
    }


    public String checkSkipFolders() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('div.col-3 > mbs-checkbox > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkSkipFoldersInputField() {
        return skipFoldersInputField.getDomProperty("value");
    }

    public String checkBackUpEmptyFolders() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('div.col-9 > mbs-checkbox > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    //checkBackUpObjectsModified
    public String checkBackUpObjectsModified() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-card-group:nth-child(2) > mbs-form-group:nth-child(2) >"
                        + " div > div > div.col-4 > mbs-checkbox > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkBackUpObjectsModifiedInputField() {
        return this.backUpObjectsModifiedDays.getDomProperty("value");
    }

    public String checkBackUpObjectsModifiedSince() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-card-group:nth-child(2) > mbs-form-group:nth-child(3) > "
                        + "div > div > div.col-5 > mbs-checkbox > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkBackUpObjectsModifiedSinceDatePicker() {
        return this.backUpObjectsModifiedSinceDatePicker.getDomProperty("value");

    }

    public String checkBackUpObjectsModifiedSinceTimePicker() {
        return this.backUpObjectsModifiedSinceTimePicker.getDomProperty("value");
    }

    public String checkDoNotBackUpFilesLargerThan() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-card-group:nth-child(2) > mbs-form-group.mbs-form-group.mb-0 > "
                        + "div > div > div.col-5 > mbs-checkbox > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkDoNotBackUpFilesLargerThanInputField() {
        return this.doNotBackUpFilesLargerThanInputField.getDomProperty("value");
    }

    public String checkDoNotBackUpSystemAndHiddenFiles() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-card-group:nth-child(3) > mbs-form-group > "
                        + "div > mbs-checkbox:nth-child(1) > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }


    public String checkDoNotBackUpFilesUsedByOtherProcessesAtTheMomentOfTheBackupPlanRun() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-card-group:nth-child(3) > mbs-form-group > div > "
                        + "mbs-checkbox:nth-child(2) > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkDoNotBackUpOnDemandFiles() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-card-group:nth-child(3) > mbs-form-group > div > "
                        + "mbs-checkbox:nth-child(3) > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

}

