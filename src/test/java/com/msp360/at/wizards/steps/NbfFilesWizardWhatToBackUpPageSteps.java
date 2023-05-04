package com.msp360.at.wizards.steps;

import com.msp360.at.wizards.NbfFilesWizardWhatToBackUpPage;
import com.msp360.at.wizards.tests.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class NbfFilesWizardWhatToBackUpPageSteps extends BaseClass {

    private final NbfFilesWizardWhatToBackUpPage nbfFilesWizardWhatToBackUpPage;

    //Backup Source (convert to list?)

    protected static final String WINDOWS_PATH0 = "C:\\TestFiles\\main_folder_delete";
    protected static final String WINDOWS_PATH1 = "C:\\TestFiles\\main_folder_no_changes";
    protected static final String WINDOWS_PATH2 = "C:\\TestFiles\\main_folder_change_name";
    protected static final String WINDOWS_PATH3 = "C:\\TestFiles\\empty folder";
    protected static final String WINDOWS_PATH4 = "C:\\TestFiles\\pro_test";
    protected static final String WINDOWS_PATH5 = "C:\\TestFiles\\test";
    protected static final String WINDOWS_PATH6 = "C:\\TestFiles\\testo";
    protected static final String WINDOWS_PATH7 = "C:\\TestFiles\\main_folder";
    protected static final String WINDOWS_PATH8 = "C:\\TestFiles\\main_folder_exclude\\exclude_folder";
    protected static final String WINDOWS_PATH9 = "C:\\TestFiles\\main_folder_exclude";
    protected static final String WINDOWS_INVALID_PATH1 = "C::\\\\123";
    protected static final String WINDOWS_INVALID_PATH2 = "C:/123";
    protected static final String WINDOWS_INVALID_PATH3 = "/home/123";
    protected static final String WINDOWS_INVALID_PATH4 = " ";

    public NbfFilesWizardWhatToBackUpPageSteps(WebDriver driver) {
        BaseClass.getDriver();
        nbfFilesWizardWhatToBackUpPage = new NbfFilesWizardWhatToBackUpPage(driver);
    }


    @Step("Select Backup data via tree")
    public void selectBackupDataTree() {
        nbfFilesWizardWhatToBackUpPage.selectTreeDataForBackup();
    }

    @Step("Select Backup data via Plain Text. Case 0")
    public void fillBackupDataPlainTextCase0() throws InterruptedException {
        nbfFilesWizardWhatToBackUpPage.selectShowPlainText();
        nbfFilesWizardWhatToBackUpPage.includeFieldAddNewPath(WINDOWS_PATH1);
    }

    @Step("Verify selected Backup paths. Case 0")
    public void verifySelectedPathsPlainTextCase0() throws InterruptedException {
        nbfFilesWizardWhatToBackUpPage.selectShowPlainText();
        checkSelectedPathsIncludeField(WINDOWS_PATH1);
    }

    @Step("Select Backup data via Plain Text. Case 1")
    public void fillBackupDataPlainTextCase1() throws InterruptedException {
        nbfFilesWizardWhatToBackUpPage.selectShowPlainText();
        nbfFilesWizardWhatToBackUpPage.includeFieldAddNewPath(WINDOWS_PATH0);
        nbfFilesWizardWhatToBackUpPage.deletePathButton();
        nbfFilesWizardWhatToBackUpPage.includeFieldAddNewPath(WINDOWS_PATH1);
        nbfFilesWizardWhatToBackUpPage.changePath(WINDOWS_PATH2);
        nbfFilesWizardWhatToBackUpPage.includeFieldAddNewPath(WINDOWS_PATH3);
        nbfFilesWizardWhatToBackUpPage.includeFieldAddNewPath(WINDOWS_PATH4);
        nbfFilesWizardWhatToBackUpPage.includeFieldAddNewPath(WINDOWS_PATH5);
        nbfFilesWizardWhatToBackUpPage.includeFieldAddNewPath(WINDOWS_PATH6);
        nbfFilesWizardWhatToBackUpPage.includeFieldAddNewPath(WINDOWS_PATH7);
        nbfFilesWizardWhatToBackUpPage.includeFieldAddNewPath(WINDOWS_PATH8);
        nbfFilesWizardWhatToBackUpPage.excludeFieldAddNewPath(WINDOWS_PATH9);
    }

    @Step("Verify selected Backup paths. Case 1")
    public void verifySelectedPathsPlainTextCase1() throws InterruptedException {
        nbfFilesWizardWhatToBackUpPage.selectShowPlainText();
        checkSelectedPathsIncludeField(WINDOWS_PATH2);
        checkSelectedPathsIncludeField(WINDOWS_PATH3);
        checkSelectedPathsIncludeField(WINDOWS_PATH4);
        checkSelectedPathsIncludeField(WINDOWS_PATH5);
        checkSelectedPathsIncludeField(WINDOWS_PATH6);
        checkSelectedPathsIncludeField(WINDOWS_PATH7);
    }

    @Step("Select Backup data via Plain Text. Case 2")
    public void fillBackupDataPlainTextCase2() throws InterruptedException {
        nbfFilesWizardWhatToBackUpPage.selectShowPlainText();
        nbfFilesWizardWhatToBackUpPage.includeFieldAddNewInvalidPath(WINDOWS_INVALID_PATH1);
        nbfFilesWizardWhatToBackUpPage.includeFieldAddNewInvalidPath(WINDOWS_INVALID_PATH2);
        nbfFilesWizardWhatToBackUpPage.includeFieldAddNewInvalidPath(WINDOWS_INVALID_PATH3);
        nbfFilesWizardWhatToBackUpPage.includeFieldAddNewPath(WINDOWS_PATH1);
        nbfFilesWizardWhatToBackUpPage.includeFieldCopyPastePath(WINDOWS_PATH2);
        nbfFilesWizardWhatToBackUpPage.excludeFieldAddNewInvalidPath(WINDOWS_INVALID_PATH1);
        nbfFilesWizardWhatToBackUpPage.excludeFieldAddNewInvalidPath(WINDOWS_INVALID_PATH2);
        nbfFilesWizardWhatToBackUpPage.excludeFieldAddNewInvalidPath(WINDOWS_INVALID_PATH3);
        nbfFilesWizardWhatToBackUpPage.excludeFieldCopyPastePath(WINDOWS_PATH9);
    }

    @Step("Verify selected Backup paths. Case 2")
    public void verifySelectedPathsPlainTextCase2() throws InterruptedException {
        nbfFilesWizardWhatToBackUpPage.selectShowPlainText();
        checkSelectedPathsIncludeField(WINDOWS_PATH1);
        checkSelectedPathsIncludeField(WINDOWS_PATH2);
        checkSelectedPathsExcludeField(WINDOWS_PATH9);
    }

    //In a pathname is already exist in the xpath selector. Is this assertion necessary?
    @Step("checking Selected paths include field")
    public void checkSelectedPathsIncludeField(String selectedPath) {
        try {
            Assert.assertEquals(selectedPath,
                    nbfFilesWizardWhatToBackUpPage.checkSelectedPathIncludeField(selectedPath));
        } catch (NoSuchElementException e) {
            Assert.assertEquals("The path doesn't exist", selectedPath);
        }
    }

    @Step("checking Selected paths exclude field")
    public void checkSelectedPathsExcludeField(String selectedPath) {
        try {
            Assert.assertEquals(selectedPath,
                    nbfFilesWizardWhatToBackUpPage.checkSelectedPathIncludeField(selectedPath));
        } catch (NoSuchElementException e) {
            Assert.assertEquals("The path doesn't exist", selectedPath);
        }
    }

}
