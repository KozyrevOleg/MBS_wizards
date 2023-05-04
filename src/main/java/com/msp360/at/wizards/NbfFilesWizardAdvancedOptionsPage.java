package com.msp360.at.wizards;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class NbfFilesWizardAdvancedOptionsPage extends BaseClassPage {

    JavascriptExecutor js = (JavascriptExecutor) driver;

    public NbfFilesWizardAdvancedOptionsPage(WebDriver driver) {
        super(driver);
    }

    //ADVANCED OPTIONS STEP
    @FindBy(xpath = "//span[normalize-space()='Use S3 transfer acceleration']")
    private WebElement s3TransferAccelerationLabel;

    @FindBy(xpath = "//label[@for='use-S3-acceleration-checkbox']")
    private WebElement s3TransferAcceleration;

    public void selectS3TransferAcceleration() {
        this.s3TransferAcceleration.click();
    }

    @FindBy(xpath = "//span[@class='mbs-form_label-content']")
    private WebElement storageClassLabel;

    @FindBy(xpath = "//div[@role='combobox']")
    private WebElement storageClassesDropDown;

    public void selectStorageClass() {
        this.storageClassesDropDown.click();
    }

    public void selectClass(String classType) {
        WebElement type = driver.findElement(By.xpath("//span[normalize-space()='" + classType + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(type)).click();
    }

    @FindBy(xpath = "//span[normalize-space()='Synthetic Full backup']")
    private WebElement syntheticLabel;

    @FindBy(xpath = "//label[@for='synthetic-full-checkbox']")
    private WebElement synthetic;

    @FindBy(xpath = "//span[normalize-space()='Back up NTFS permissions']")
    private WebElement backupNtfsPermissionLabel;

    @FindBy(xpath = "//label[@for='backup-ntfs-permissions-checkbox']")
    private WebElement ntfsPermissions;

    public void selectBackupNtfsPermissions() {
        this.ntfsPermissions.click();
    }

    @FindBy(xpath = "//span[normalize-space()='Use fast NTFS scan']")
    private WebElement ntfsScanLabel;

    @FindBy(xpath = "//label[@for='use-fast-ntfs-scan-checkbox']")
    private WebElement ntfsScan;

    public void selectUseNtfsScan() {
        this.ntfsScan.click();
    }

    @FindBy(xpath = "//span[normalize-space()='Use system VSS provider']")
    private WebElement useSystemVssLabel;

    @FindBy(xpath = "//label[@for='always-use-VSS-provider-checkbox']")
    private WebElement useSystemVssProvider;

    public void selectVss() {
        this.useSystemVssProvider.click();
    }

    @FindBy(xpath = "//span[normalize-space()='Force using VSS (volume shadow copy service)']")
    private WebElement forceUsingVssLabel;

    @FindBy(xpath = "//label[@for='always-use-VSS-provider-checkbox']")
    private WebElement forceUsingVSS;

    public void selectForceUsingVss() {
        this.forceUsingVSS.click();
    }

    @FindBy(xpath = "//label[@for='use-system-VSS-provider-checkbox']")
    private WebElement vssProvider;

    public void selectUseSystemVssProvider() {
        this.vssProvider.click();
    }

    @FindBy(xpath = "//span[normalize-space()='Keep EFS encryption']")
    private WebElement keepEfsEncryptionLabel;

    @FindBy(xpath = "//label[@for='KeepAsis']")
    private WebElement keepEFSEncryption;

    public void keepEfsEncryption() {
        wait.until(ExpectedConditions.elementToBeClickable(this.keepEFSEncryption)).click();
    }

    @FindBy(xpath = "//span[normalize-space()='Decrypt EFS-encrypted files']")
    private WebElement decryptEfsLabel;

    @FindBy(xpath = "//label[@for='decryptEFS']")
    private WebElement decryptEFS;

    public void selectDecryptEfsEncryption() {
        this.decryptEFS.click();
    }

    @FindBy(xpath = "//button[normalize-space()='Enable']")
    private WebElement enableEfsEncryptionModal;

    public void enableEfsEncryptionModal() {
        this.enableEfsEncryptionModal.click();
    }

    @FindBy(xpath = "//button[@aria-disabled='false'][normalize-space()='Cancel']")
    private WebElement cancelEfsEncryptionModal;

    public void cancelEfsEncryptionModal() {
        this.cancelEfsEncryptionModal.click();
    }

    @FindBy(xpath = "//mbs-button[@aria-label='Close']//span[@class='ctrl-ico ico ico-Close']")
    private WebElement closeEfsEncryptionModal;

    public void closeEfsEncryptionModal() {
        this.closeEfsEncryptionModal.click();
    }


    //Synthetic modal
    @FindBy(xpath = "//span[normalize-space()='Synthetic Full backup']")
    private WebElement syntheticCheckbox;

    @FindBy(xpath = "//button[normalize-space()='Yes']")
    private WebElement syntheticModalDisable;

    public void syntheticModalClickYesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(this.syntheticModalDisable)).click();
    }

    @FindBy(xpath = "//mbs-button[@aria-label='Close']//span[@class='ctrl-ico ico ico-Close']")
    private WebElement closeSyntheticModal;

    public void closeSyntheticModal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(closeSyntheticModal));
        this.closeSyntheticModal.click();
    }

    @FindBy(xpath = "//button[normalize-space()='No']")
    private WebElement leaveSyntheticEnabled;

    public void syntheticModalClickNoButton() {
        this.leaveSyntheticEnabled.click();
    }

    //Advanced Options Step

    public String checkStorageClassType(String storageClasses) {

        try {
            storageClassLabel.isDisplayed();
        } catch (NoSuchElementException e) {
            return CHECKBOX_DOESNT_EXIST;
        }

        switch (storageClasses) {
            case S3_GLACIER_DEEP_ARCHIVE:
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_INSTANT_RETRIEVAL:
            case S3_STANDARD:
            case S3_STANDARD_IA:
            case S3_ONE_ZONE_IA:
            case S3_INTELLIGENT_TIERING:
            case AZURE_HOT:
            case AZURE_COOL:
            case AZURE_ARCHIVE:
                WebElement destinationName = driver
                        .findElement(By.xpath("//span[normalize-space()='" + storageClasses + "']"));
                return destinationName.getText();

            default:
                return null;
        }
    }

    public String checkUseS3TransferAcceleration(String storageClasses) {

        try {
            s3TransferAccelerationLabel.isDisplayed();
        } catch (NoSuchElementException e) {
            return CHECKBOX_DOESNT_EXIST;

        }

        switch (storageClasses) {
            case S3_GLACIER_DEEP_ARCHIVE:
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_INSTANT_RETRIEVAL:
            case S3_STANDARD:
            case S3_STANDARD_IA:
            case S3_ONE_ZONE_IA:
            case S3_INTELLIGENT_TIERING:

                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(1) > div > mbs-checkbox > div > label'),'::before')"
                                + ".getPropertyValue('border-bottom-color')")
                        .toString();

            default:
                return null;

        }
    }

    public String checkBackupNtfsPermission(String storageClasses, String checkboxState) {

        switch (storageClasses) {
            case S3_GLACIER_DEEP_ARCHIVE:
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_INSTANT_RETRIEVAL:

                this.backupNtfsPermissionLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(5) > div > mbs-checkbox > div > label'),'::before')"
                                + ".getPropertyValue('border-bottom-color')")
                        .toString();

            case S3_STANDARD:
            case S3_STANDARD_IA:
            case S3_ONE_ZONE_IA:
            case S3_INTELLIGENT_TIERING:
            case AZURE_ARCHIVE:

                this.backupNtfsPermissionLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(4) > div > mbs-checkbox > div > label'),'::before')"
                                + ".getPropertyValue('border-bottom-color')")
                        .toString();


            case AZURE_HOT:
            case AZURE_COOL:
            case MINIO_SYNTHETIC_ON:
            case S3_COMPATIBLE_SYNTHETIC_ON:

                this.backupNtfsPermissionLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(3) > div > mbs-checkbox > div > label'),'::before')"
                                + ".getPropertyValue('border-bottom-color')")
                        .toString();

            default:

                this.backupNtfsPermissionLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(2) > div > mbs-checkbox > div > label'),'::before')"
                                + ".getPropertyValue('border-bottom-color')")
                        .toString();
        }

    }

    //radiobutton checking
    public String checkDecryptEfsEncryptedFiles(String storageClasses) {
        //if (isCheckboxExist(storageClassesDropDown)) {
        switch (storageClasses) {
            case S3_GLACIER_DEEP_ARCHIVE:
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_INSTANT_RETRIEVAL:

                this.decryptEfsLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(8) > div > mbs-radio:nth-child(1) > div > label'),"
                                + "'::before').getPropertyValue('border-bottom-color')")
                        .toString();

            case S3_STANDARD:
            case S3_STANDARD_IA:
            case S3_ONE_ZONE_IA:
            case S3_INTELLIGENT_TIERING:
            case AZURE_ARCHIVE:

                this.decryptEfsLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(7) > div > mbs-radio:nth-child(1) > div > label'),"
                                + "'::before').getPropertyValue('border-bottom-color')")
                        .toString();

            case AZURE_HOT:
            case AZURE_COOL:
            case MINIO_SYNTHETIC_ON:
            case S3_COMPATIBLE_SYNTHETIC_ON:

                this.decryptEfsLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(6) > div > mbs-radio:nth-child(1) > div > label'),"
                                + "'::before').getPropertyValue('border-bottom-color')")
                        .toString();

            default:

                this.decryptEfsLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(5) > div > mbs-radio:nth-child(1) > div > label'),"
                                + "'::before').getPropertyValue('border-bottom-color')")
                        .toString();
        }
    }

    public String checkKeepEfsRadiobuttonState(String storageClasses) {
        //if (isCheckboxExist(storageClassesDropDown)) {
        switch (storageClasses) {
            case S3_GLACIER_DEEP_ARCHIVE:
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_INSTANT_RETRIEVAL:

                this.keepEfsEncryptionLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(8) > div > mbs-radio:nth-child(2) > div > label'),"
                                + "'::before').getPropertyValue('border-bottom-color')")
                        .toString();

            case S3_STANDARD:
            case S3_STANDARD_IA:
            case S3_ONE_ZONE_IA:
            case S3_INTELLIGENT_TIERING:
            case AZURE_ARCHIVE:

                this.keepEfsEncryptionLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(7) > div > mbs-radio:nth-child(2) > div > label'),"
                                + "'::before').getPropertyValue('border-bottom-color')")
                        .toString();

            case AZURE_HOT:
            case AZURE_COOL:
            case MINIO_SYNTHETIC_ON:
            case S3_COMPATIBLE_SYNTHETIC_ON:

                this.keepEfsEncryptionLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(6) > div > mbs-radio:nth-child(2) > div > label'),"
                                + "'::before').getPropertyValue('border-bottom-color')")
                        .toString();

            default:

                this.keepEfsEncryptionLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(5) > div > mbs-radio:nth-child(2) > div > label'),"
                                + "'::before').getPropertyValue('border-bottom-color')")
                        .toString();

        }
    }

    public String checkUseFastNtfsScan(String storageClasses) {
        //if (isCheckboxExist(storageClassesDropDown)) {
        switch (storageClasses) {
            case S3_GLACIER_DEEP_ARCHIVE:
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_INSTANT_RETRIEVAL:

                this.ntfsScanLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(6) > div > mbs-checkbox > div > label'),'::before')"
                                + ".getPropertyValue('border-bottom-color')")
                        .toString();

            case S3_STANDARD:
            case S3_STANDARD_IA:
            case S3_ONE_ZONE_IA:
            case S3_INTELLIGENT_TIERING:
            case AZURE_ARCHIVE:

                this.ntfsScanLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(5) > div > mbs-checkbox > div > label'),'::before')"
                                + ".getPropertyValue('border-bottom-color')")
                        .toString();

            case AZURE_HOT:
            case AZURE_COOL:
            case MINIO_SYNTHETIC_ON:
            case S3_COMPATIBLE_SYNTHETIC_ON:

                this.ntfsScanLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(4) > div > mbs-checkbox > div > label'),'::before')"
                                + ".getPropertyValue('border-bottom-color')")
                        .toString();
            default:

                this.ntfsScanLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(3) > div > mbs-checkbox > div > label'),'::before')"
                                + ".getPropertyValue('border-bottom-color')")
                        .toString();
        }
    }

    public String checkForceUsingVss(String storageClasses) {
        //if (isCheckboxExist(storageClassesDropDown)) {
        switch (storageClasses) {
            case S3_GLACIER_DEEP_ARCHIVE:
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_INSTANT_RETRIEVAL:

                this.forceUsingVssLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(7) > div > mbs-checkbox > div.mbs-checkbox > label'),"
                                + "'::before')"
                                + ".getPropertyValue('border-bottom-color')")
                        .toString();

            case S3_STANDARD:
            case S3_STANDARD_IA:
            case S3_ONE_ZONE_IA:
            case S3_INTELLIGENT_TIERING:
            case AZURE_ARCHIVE:

                this.forceUsingVssLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(6) > div > mbs-checkbox > div.mbs-checkbox > label'),"
                                + "'::before')"
                                + ".getPropertyValue('border-bottom-color')")
                        .toString();

            case AZURE_HOT:
            case AZURE_COOL:
            case MINIO_SYNTHETIC_ON:
            case S3_COMPATIBLE_SYNTHETIC_ON:

                this.forceUsingVssLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(5) > div > mbs-checkbox > div.mbs-checkbox > label'),"
                                + "'::before')"
                                + ".getPropertyValue('border-bottom-color')")
                        .toString();

            default:

                this.forceUsingVssLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(4) > div > mbs-checkbox > "
                                + "div.mbs-checkbox > label'),'::before')"
                                + ".getPropertyValue('border-bottom-color')")
                        .toString();
        }
    }

    public String checkUseSystemVss(String storageClasses) {
        // if (isCheckboxExist(storageClassesDropDown)) {
        switch (storageClasses) {
            case S3_GLACIER_DEEP_ARCHIVE:
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_INSTANT_RETRIEVAL:

                this.useSystemVssLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(7) > div > mbs-checkbox > "
                                + "div.mbs-checkbox_description.-inside > mbs-checkbox > div > label'),'::before')"
                                + ".getPropertyValue('border-bottom-color')")
                        .toString();

            case S3_STANDARD:
            case S3_STANDARD_IA:
            case S3_ONE_ZONE_IA:
            case S3_INTELLIGENT_TIERING:
            case AZURE_ARCHIVE:

                this.useSystemVssLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(6) > div > mbs-checkbox > "
                                + "div.mbs-checkbox_description.-inside > mbs-checkbox > div > label'),'::before')"
                                + ".getPropertyValue('border-bottom-color')")
                        .toString();

            case AZURE_HOT:
            case AZURE_COOL:
            case MINIO_SYNTHETIC_ON:
            case S3_COMPATIBLE_SYNTHETIC_ON:

                this.useSystemVssLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(5) > div > mbs-checkbox > "
                                + "div.mbs-checkbox_description.-inside > mbs-checkbox > div > label'),'::before')"
                                + ".getPropertyValue('border-bottom-color')")
                        .toString();
            default:

                this.useSystemVssLabel.isDisplayed();
                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(4) > div > mbs-checkbox > "
                                + "div.mbs-checkbox_description.-inside > mbs-checkbox > div > label'),'::before')"
                                + ".getPropertyValue('border-bottom-color')")
                        .toString();
        }
    }

    public String checkSynthetic(String storageClasses) {

        try {
            this.syntheticLabel.isDisplayed();
        } catch (NoSuchElementException e) {
            return CHECKBOX_DOESNT_EXIST;
        }
        switch (storageClasses) {
            case S3_GLACIER_DEEP_ARCHIVE:
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_INSTANT_RETRIEVAL:

                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(4) > div > mbs-checkbox > "
                                + "div.mbs-checkbox > label'),'::before')"
                                + ".getPropertyValue('border-bottom-color')")
                        .toString();

            case AZURE_ARCHIVE:

                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(3) > div > mbs-checkbox > "
                                + "div.mbs-checkbox > label'),'::before')"
                                + ".getPropertyValue('border-bottom-color')")
                        .toString();

            case S3_COMPATIBLE_SYNTHETIC_ON:
            case MINIO_SYNTHETIC_ON:

                return js.executeScript("return window.getComputedStyle"
                                + "(document.querySelector"
                                + "('mbs-form-group:nth-child(2) > div > mbs-checkbox > "
                                + "div.mbs-checkbox > label'),'::before')"
                                + ".getPropertyValue('border-bottom-color')")
                        .toString();

            default:
                return null;
        }
    }
}
