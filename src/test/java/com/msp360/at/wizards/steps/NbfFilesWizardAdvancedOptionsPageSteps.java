package com.msp360.at.wizards.steps;

import com.msp360.at.wizards.NbfFilesWizardAdvancedOptionsPage;
import com.msp360.at.wizards.NbfFilesWizardPage;
import com.msp360.at.wizards.tests.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class NbfFilesWizardAdvancedOptionsPageSteps extends BaseClass {

    private final NbfFilesWizardAdvancedOptionsPage nbfFilesWizardAdvancedOptionsPage;
    private final NbfFilesWizardPage nbfFilesWizardPage;

    public NbfFilesWizardAdvancedOptionsPageSteps(WebDriver driver) {
        BaseClass.getDriver();
        nbfFilesWizardAdvancedOptionsPage = new NbfFilesWizardAdvancedOptionsPage(driver);
        nbfFilesWizardPage = new NbfFilesWizardPage(driver);
    }

    //добавить про отображение опции Синтетика
    @Step("Verify AdvancedFilterOptionsDefaultOptions. Class type {0}")
    public void verifyDefaultOptions(StorageClasses classType) {

        switch (classType) {
            case S3_STANDARD:
            case S3_STANDARD_IA:
            case S3_ONE_ZONE_IA:
            case S3_INTELLIGENT_TIERING:
                checkUseS3TransferAcceleration(classType, UNCHECKED_CHECKBOX);
                checkSynthetic(String.valueOf(classType), CHECKBOX_DOESNT_EXIST);
                checkStorageClassType(String.valueOf(classType));
                break;

            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_DEEP_ARCHIVE:
            case S3_GLACIER_INSTANT_RETRIEVAL:
            case AZURE_ARCHIVE:
                checkUseS3TransferAcceleration(classType, CHECKBOX_DOESNT_EXIST);
                checkSynthetic(String.valueOf(classType), UNCHECKED_CHECKBOX);
                checkStorageClassType(String.valueOf(classType));
                break;

            case AZURE_HOT:
            case AZURE_COOL:
                checkUseS3TransferAcceleration(classType, CHECKBOX_DOESNT_EXIST);
                checkSynthetic(String.valueOf(classType), CHECKBOX_DOESNT_EXIST);
                checkStorageClassType(String.valueOf(classType));
                break;

            default:
                checkUseS3TransferAcceleration(classType, CHECKBOX_DOESNT_EXIST);
                checkStorageClassType(CHECKBOX_DOESNT_EXIST);
                checkSynthetic(String.valueOf(classType), CHECKBOX_DOESNT_EXIST);
        }
        checkBackupNtfsPermission(String.valueOf(classType), UNCHECKED_CHECKBOX);
        checkUseFastNtfsScan(String.valueOf(classType), CHECKED_CHECKBOX);
        checkForceUsingVss(String.valueOf(classType), DISABLED_CHECKED_CHECKBOX);
        checkUseSystemVss(String.valueOf(classType), DISABLED_CHECKBOX);
        checkDecryptEfsRadiobutton(String.valueOf(classType), ENABLED_RADIOBUTTON);
        checkKeepEfsRadiobuttonState(String.valueOf(classType), DISABLED_RADIOBUTTON);

    }

    //Use this case for other wizards steps
    @Step("Fill Advanced Options step. Case 0. Change storage classes. Class type : {0}")

    public void fillAdvancedOptionsStepCase0(StorageClasses classType) {

        switch (classType) {
            case S3_STANDARD_IA:
            case S3_ONE_ZONE_IA:
            case S3_INTELLIGENT_TIERING:
            case AZURE_COOL:
                nbfFilesWizardAdvancedOptionsPage.selectStorageClass();
                nbfFilesWizardAdvancedOptionsPage.selectClass(String.valueOf(classType));
                nbfFilesWizardPage.clickNext();
                break;

            case AZURE_ARCHIVE:
            case S3_GLACIER_DEEP_ARCHIVE:
            case S3_GLACIER_INSTANT_RETRIEVAL:
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
                nbfFilesWizardAdvancedOptionsPage.selectStorageClass();
                nbfFilesWizardAdvancedOptionsPage.selectClass(String.valueOf(classType));
                nbfFilesWizardPage.clickNext();
                nbfFilesWizardAdvancedOptionsPage.syntheticModalClickYesButton();
                break;

            default:
                nbfFilesWizardPage.clickNext();
                break;
        }
    }


    @Step("Fill Advanced Options step. Case 1. Following options are selected: NTFS + "
            + "VSS dependency check + EFS encryption + modal. Class type {0}")

    public void fillAdvancedOptionsStepCase1(StorageClasses classType) throws InterruptedException {
        switch (classType) {
            //case S3_STANDARD:
            case S3_STANDARD_IA:
            case S3_ONE_ZONE_IA:
            case S3_INTELLIGENT_TIERING:
            case S3_GLACIER_DEEP_ARCHIVE:
            case S3_GLACIER_INSTANT_RETRIEVAL:
            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
                nbfFilesWizardAdvancedOptionsPage.selectS3TransferAcceleration();
                nbfFilesWizardAdvancedOptionsPage.selectStorageClass();
                nbfFilesWizardAdvancedOptionsPage.selectClass(String.valueOf(classType));
                break;

            //case AZURE_HOT:
            case AZURE_COOL:
            case AZURE_ARCHIVE:
                nbfFilesWizardAdvancedOptionsPage.selectStorageClass();
                nbfFilesWizardAdvancedOptionsPage.selectClass(String.valueOf(classType));
                break;
            default:
                break;
        }
        checkForceUsingVss(String.valueOf(classType), DISABLED_CHECKED_CHECKBOX);
        nbfFilesWizardAdvancedOptionsPage.selectUseNtfsScan(); //ER: disable
        nbfFilesWizardAdvancedOptionsPage.selectUseSystemVssProvider();
        nbfFilesWizardAdvancedOptionsPage.selectForceUsingVss(); //ER: disable
        //nbfFilesWizardPage.selectUseSystemVssProvider(); //ER: disable
        checkForceUsingVss(String.valueOf(classType), UNCHECKED_CHECKBOX);
        checkUseSystemVss(String.valueOf(classType), DISABLED_CHECKBOX);
        nbfFilesWizardAdvancedOptionsPage.selectUseNtfsScan(); //ER: enable
        nbfFilesWizardAdvancedOptionsPage.keepEfsEncryption();
        nbfFilesWizardAdvancedOptionsPage.cancelEfsEncryptionModal(); //ER: keep EFS disable
        checkKeepEfsRadiobuttonState(String.valueOf(classType), DISABLED_RADIOBUTTON);
        Thread.sleep(1500);
        nbfFilesWizardAdvancedOptionsPage.keepEfsEncryption();
        nbfFilesWizardAdvancedOptionsPage.closeEfsEncryptionModal(); //ER: keep EFS disable
        Thread.sleep(1500);
        nbfFilesWizardAdvancedOptionsPage.keepEfsEncryption();
        nbfFilesWizardAdvancedOptionsPage.enableEfsEncryptionModal(); //ER: keep EFS enable

    }

    @Step("Verify AdvancedOptionsStepDefaultCase1. Class type {0}")
    public void verifyFillAdvancedOptionsCase1(StorageClasses classType) {

        switch (classType) {
            case S3_STANDARD:
            case S3_STANDARD_IA:
            case S3_ONE_ZONE_IA:
            case S3_INTELLIGENT_TIERING:
                checkUseS3TransferAcceleration(classType, CHECKED_CHECKBOX);
                checkSynthetic(String.valueOf(classType), CHECKBOX_DOESNT_EXIST);
                checkStorageClassType(String.valueOf(classType));
                break;

            case AZURE_HOT:
            case AZURE_COOL:
                checkUseS3TransferAcceleration(classType, CHECKBOX_DOESNT_EXIST);
                checkSynthetic(String.valueOf(classType), CHECKBOX_DOESNT_EXIST);
                checkStorageClassType(String.valueOf(classType));
                break;

            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_DEEP_ARCHIVE:
            case S3_GLACIER_INSTANT_RETRIEVAL:
                checkUseS3TransferAcceleration(classType, CHECKED_CHECKBOX);
                checkSynthetic(String.valueOf(classType), UNCHECKED_CHECKBOX);
                checkStorageClassType(String.valueOf(classType));
                break;

            case AZURE_ARCHIVE:
                checkUseS3TransferAcceleration(classType, CHECKBOX_DOESNT_EXIST);
                checkSynthetic(String.valueOf(classType), UNCHECKED_CHECKBOX);
                checkStorageClassType(String.valueOf(classType));
                break;

            default:
                checkUseS3TransferAcceleration(classType, CHECKBOX_DOESNT_EXIST);
                checkSynthetic(String.valueOf(classType), CHECKBOX_DOESNT_EXIST);
                checkStorageClassType(CHECKBOX_DOESNT_EXIST);
        }
        checkBackupNtfsPermission(String.valueOf(classType), UNCHECKED_CHECKBOX);
        checkUseFastNtfsScan(String.valueOf(classType), CHECKED_CHECKBOX);
        checkForceUsingVss(String.valueOf(classType), DISABLED_CHECKED_CHECKBOX);
        checkUseSystemVss(String.valueOf(classType), DISABLED_CHECKBOX);
        checkDecryptEfsRadiobutton(String.valueOf(classType), DISABLED_RADIOBUTTON);
        checkKeepEfsRadiobuttonState(String.valueOf(classType), ENABLED_RADIOBUTTON);
    }

    @Step("Fill Advanced Options step. Case 2. Check Synthetic option for Archive storage classes. Class type {0}")

    public void fillAdvancedOptionsStepCase2(StorageClasses classType) throws InterruptedException {
        nbfFilesWizardAdvancedOptionsPage.selectBackupNtfsPermissions(); //ER: enable
        checkBackupNtfsPermission(String.valueOf(classType), CHECKED_CHECKBOX);
        switch (classType) {
            case S3_STANDARD:
            case S3_STANDARD_IA:
            case S3_ONE_ZONE_IA:
            case S3_INTELLIGENT_TIERING:
            case AZURE_HOT:
            case AZURE_COOL:
                nbfFilesWizardAdvancedOptionsPage.selectStorageClass();
                nbfFilesWizardAdvancedOptionsPage.selectClass(String.valueOf(classType));
                break;

            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_DEEP_ARCHIVE:
            case S3_GLACIER_INSTANT_RETRIEVAL:
            case AZURE_ARCHIVE:
                nbfFilesWizardAdvancedOptionsPage.selectStorageClass();
                nbfFilesWizardAdvancedOptionsPage.selectClass(String.valueOf(classType));
                nbfFilesWizardPage.clickNext();
                nbfFilesWizardAdvancedOptionsPage.syntheticModalClickYesButton();
                Thread.sleep(2000);
                nbfFilesWizardPage.clickBack();
                checkSynthetic(String.valueOf(classType), UNCHECKED_CHECKBOX);
                break;

            default:
                break;
        }
    }


    @Step("Verify AdvancedOptionsStepCase2. Class type {0}")
    public void verifyFillAdvancedOptionsStepCase2(StorageClasses classType) {

        switch (classType) {
            case S3_STANDARD:
            case S3_STANDARD_IA:
            case S3_ONE_ZONE_IA:
            case S3_INTELLIGENT_TIERING:
                checkUseS3TransferAcceleration(classType, UNCHECKED_CHECKBOX);
                checkStorageClassType(String.valueOf(classType));
                break;

            case AZURE_HOT:
            case AZURE_COOL:
                checkUseS3TransferAcceleration(classType, CHECKBOX_DOESNT_EXIST);
                checkStorageClassType(String.valueOf(classType));
                break;

            case S3_GLACIER_FLEXIBLE_RETRIEVAL:
            case S3_GLACIER_DEEP_ARCHIVE:
            case S3_GLACIER_INSTANT_RETRIEVAL:
                checkUseS3TransferAcceleration(classType, UNCHECKED_CHECKBOX);
                checkStorageClassType(String.valueOf(classType));
                checkSynthetic(String.valueOf(classType), UNCHECKED_CHECKBOX);
                break;

            case AZURE_ARCHIVE:
                checkStorageClassType(String.valueOf(classType));
                checkSynthetic(String.valueOf(classType), UNCHECKED_CHECKBOX);
                break;

            default:
                checkUseS3TransferAcceleration(classType, CHECKBOX_DOESNT_EXIST);
                checkSynthetic(String.valueOf(classType), CHECKBOX_DOESNT_EXIST);
                checkStorageClassType(CHECKBOX_DOESNT_EXIST);
                break;
        }
        checkBackupNtfsPermission(String.valueOf(classType), CHECKED_CHECKBOX);
        checkUseFastNtfsScan(String.valueOf(classType), CHECKED_CHECKBOX);
        checkForceUsingVss(String.valueOf(classType), DISABLED_CHECKED_CHECKBOX);
        checkUseSystemVss(String.valueOf(classType), DISABLED_CHECKBOX);
        checkKeepEfsRadiobuttonState(String.valueOf(classType), DISABLED_RADIOBUTTON);
    }


    //Assertions

    @Step("checking S3 Transfer Acceleration. Class type {0}, Checkbox {1}")
    public void checkUseS3TransferAcceleration(StorageClasses classType, String checkboxState) {
        Assert.assertEquals(nbfFilesWizardAdvancedOptionsPage
                .checkUseS3TransferAcceleration(String.valueOf(classType)), checkboxState);
    }

    @Step("checking selected Storage class. Class type {0}")
    public void checkStorageClassType(String classType) {
        Assert.assertEquals(nbfFilesWizardAdvancedOptionsPage
                .checkStorageClassType(String.valueOf(classType)), String.valueOf(classType));
    }

    @Step("checking Synthetic checkbox. Class type {0}, Checkbox {1}")
    public void checkSynthetic(String storageClasses, String checkboxState) {
        Assert.assertEquals(nbfFilesWizardAdvancedOptionsPage.checkSynthetic(storageClasses), checkboxState);
    }

    @Step("checking Decrypt EFS Radiobutton state. Class type {0}, Checkbox {1}")
    public void checkDecryptEfsRadiobutton(String classType, String efsRadioButton) {
        Assert.assertEquals(nbfFilesWizardAdvancedOptionsPage
                .checkDecryptEfsEncryptedFiles(String.valueOf(classType)), efsRadioButton);
    }

    @Step("checking Backup NTFS Permission. Class type {0}, Checkbox {1}")
    public void checkBackupNtfsPermission(String classType, String checkboxState) {
        Assert.assertEquals(nbfFilesWizardAdvancedOptionsPage
                .checkBackupNtfsPermission(String.valueOf(classType), checkboxState), checkboxState);
    }

    @Step("checking Use Fast NTFS Scan. Class type {0}, Checkbox {1}")
    public void checkUseFastNtfsScan(String classType, String checkboxState) {
        Assert.assertEquals(nbfFilesWizardAdvancedOptionsPage
                .checkUseFastNtfsScan(String.valueOf(classType)), checkboxState);
    }

    @Step("checking Force Using Vss. Class type {0}, Checkbox {1}")
    public void checkForceUsingVss(String classType, String checkboxState) {
        Assert.assertEquals(nbfFilesWizardAdvancedOptionsPage
                .checkForceUsingVss(String.valueOf(classType)), checkboxState);
    }

    @Step("checking Use System Vss. Class type {0}, Checkbox {1}")
    public void checkUseSystemVss(String classType, String checkboxState) {
        Assert.assertEquals(nbfFilesWizardAdvancedOptionsPage
                .checkUseSystemVss(String.valueOf(classType)), checkboxState);
    }

    @Step("checking EFS RadiobuttonState. Class type {0}, Checkbox {1}")
    public void checkKeepEfsRadiobuttonState(String classType, String checkboxState) {
        Assert.assertEquals(nbfFilesWizardAdvancedOptionsPage
                .checkKeepEfsRadiobuttonState(String.valueOf(classType)), checkboxState);
    }

}
