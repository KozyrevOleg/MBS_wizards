package com.msp360.at.wizards.steps;

import com.msp360.at.wizards.NbfFilesWizardAdvancedFilterPage;
import com.msp360.at.wizards.tests.BaseClass;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class NbfFilesWizardAdvancedFilterPageSteps extends BaseClass {

    private final NbfFilesWizardAdvancedFilterPage nbfFilesWizardAdvancedFilterPage;

    protected final String typeOfFiles = "*txt; *png; *jpeg";
    protected final String backUpObjectsModified = "1";
    protected final String doNotBackUpFilesLargerThan = "10";
    protected final String selectSkipFolders = "*test; Test*; *test*";
    protected final String modifiedYear = "2022";
    //непонятно как проверить значения AP/PM, при выборе через клик
    protected final String modifiedHours = "07";
    protected final String modifiedMinutes = "07";
    protected final String modifiedMeridiem = "AM";
    protected final String timePicker = "07:07 AM";
    protected final String datePicker = "Feb 28, 2023";

    public NbfFilesWizardAdvancedFilterPageSteps(WebDriver driver) {
        BaseClass.getDriver();
        nbfFilesWizardAdvancedFilterPage = new NbfFilesWizardAdvancedFilterPage(driver);
    }

    //@Step("fill Advanced Filter step. Case 0. Default settings")
    @Step("Fill Advanced Filter step. Case 1")
    @Description("Following options are selected"
            + "Do not back up files of these types: *txt, *png, *jpeg, "
                    + "Back up objects modified: 1 day, "
                    + "Do not back up files larger than - 10MB"
                    + "Do not back up system and hidden files"
                    + "Do not back up On-Demand files")
    public void fillAdvancedFilterCase1() {
        nbfFilesWizardAdvancedFilterPage.selectDoNotBackUpFilesOfTheseTypes(typeOfFiles);
        nbfFilesWizardAdvancedFilterPage.selectBackUpObjectsModified(backUpObjectsModified);
        nbfFilesWizardAdvancedFilterPage.selectDoNotBackUpFilesLargerThan(doNotBackUpFilesLargerThan);
        //other options selected by default
    }

    @Step("Verify Advanced Filter step. Case 1")
    public void assertAdvancedFilterCase1() {

        checkBackUpAllFilesInSelectedFolders(DISABLED_RADIOBUTTON);
        checkBackUpFilesOfSpecifiedTypes(DISABLED_RADIOBUTTON);
        checkDoNotBackUpFilesOfTheseTypes(ENABLED_RADIOBUTTON);
        checkBackUpObjectsModified(CHECKED_CHECKBOX);
        checkSkipFolders(DISABLED_CHECKBOX);
        checkBackUpEmptyFolders(DISABLED_CHECKBOX);
        checkBackUpObjectsModifiedSince(DISABLED_CHECKBOX);
        checkDoNotBackUpFilesLargerThan(CHECKED_CHECKBOX);
        checkDoNotBackUpSystemAndHiddenFiles(CHECKED_CHECKBOX);
        checkDoNotBackUpFilesUsedByOtherProcesses(UNCHECKED_CHECKBOX);
        checkDoNotBackUpOnDemandFiles(CHECKED_CHECKBOX);
    }

    //BACKUP-11408 Plan Wizard: "Back up empty folders" option. Which storage account supports this option?
    @Step("Fill Advanced Filter step. Case 2")
    @Description("Following options are selected"
                        + "Back up files of specified types: : *txt, *png, *jpeg,"
                        + "Skip folders: *test, Test*, *test*"
                        + "Back up empty folders"
                        + "Back up objects modified since"
                        + "Do not back up files used by other processes at the moment of the backup plan run")

    public void fillAdvancedFilterCase2() throws InterruptedException {
        nbfFilesWizardAdvancedFilterPage.selectBackUpFilesOfSpecifiedTypes(typeOfFiles);
        nbfFilesWizardAdvancedFilterPage.selectSkipFolders(selectSkipFolders);
        nbfFilesWizardAdvancedFilterPage.selectBackupEmptyFolders();
        nbfFilesWizardAdvancedFilterPage.selectBackUpObjectsModifiedSince();
        nbfFilesWizardAdvancedFilterPage
                .selectBackUpObjectsModifiedSinceDateAndTimePicker(datePicker, modifiedHours,
                        modifiedMinutes, modifiedMeridiem);
        nbfFilesWizardAdvancedFilterPage.selectDoNotBackUpSystemAndHiddenFiles(); //unchecked
        nbfFilesWizardAdvancedFilterPage.selectDoNotBackUpFilesUsedByOtherProcesses();
        nbfFilesWizardAdvancedFilterPage.selectDoNotBackUpOnDemandFiles(); //unchecked
    }

    @Step("Verify Advanced Filter step. Case 2")
    public void verifyAdvancedFilterCase2() {

        checkBackUpAllFilesInSelectedFolders(DISABLED_RADIOBUTTON);
        checkBackUpFilesOfSpecifiedTypes(ENABLED_RADIOBUTTON);
        checkDoNotBackUpFilesOfTheseTypes(DISABLED_RADIOBUTTON);
        checkSkipFolders(CHECKED_CHECKBOX);
        checkBackUpEmptyFolders(CHECKED_CHECKBOX);
        checkBackUpObjectsModified(UNCHECKED_CHECKBOX);
        checkBackUpObjectsModifiedSince(CHECKED_CHECKBOX);
        checkDoNotBackUpFilesLargerThan(UNCHECKED_CHECKBOX);
        checkDoNotBackUpSystemAndHiddenFiles(UNCHECKED_CHECKBOX);
        checkDoNotBackUpFilesUsedByOtherProcesses(CHECKED_CHECKBOX);
        checkDoNotBackUpOnDemandFiles(UNCHECKED_CHECKBOX);
    }

    //Assertions Advanced Filter step
    @Step("checking Back Up All Files In Selected Folders. Radiobutton {0}")
    public void checkBackUpAllFilesInSelectedFolders(String radioButtonState) {
        Assert.assertEquals(nbfFilesWizardAdvancedFilterPage
                .checkBackUpAllFilesInSelectedFolders(), radioButtonState);
    }

    @Step("checking Back Up Files Of Specified Types option. Radiobutton {0}")
    public void checkBackUpFilesOfSpecifiedTypes(String radioButtonState) {
        Assert.assertEquals(nbfFilesWizardAdvancedFilterPage
                .checkBackUpFilesOfSpecifiedTypesRadiobutton(), radioButtonState);

        if (radioButtonState.equals(ENABLED_RADIOBUTTON)) {
            Assert.assertEquals(nbfFilesWizardAdvancedFilterPage
                    .checkBackUpFilesOfSpecifiedTypesInputField(), typeOfFiles);
        }

    }

    @Step("checking Do Not Back Up Files Of These Types Radiobutton. Radiobutton {0}")
    public void checkDoNotBackUpFilesOfTheseTypes(String radioButtonState) {
        Assert.assertEquals(nbfFilesWizardAdvancedFilterPage
                .checkDoNotBackUpFilesOfTheseTypesRadiobutton(), radioButtonState);

        if (radioButtonState.equals(ENABLED_RADIOBUTTON)) {
            Assert.assertEquals(nbfFilesWizardAdvancedFilterPage
                    .checkDoNotBackUpFilesOfTheseTypesInputField(), typeOfFiles);
        }
    }

    @Step("checking Skip Folders option. Checkbox {0}")
    public void checkSkipFolders(String checkboxState) {
        Assert.assertEquals(nbfFilesWizardAdvancedFilterPage.checkSkipFolders(), checkboxState);

        if (checkboxState.equals(CHECKED_CHECKBOX)) {
            Assert.assertEquals(nbfFilesWizardAdvancedFilterPage.checkSkipFoldersInputField(), selectSkipFolders);
        }
    }

    @Step("checking Back up empty folders option. Checkbox {0}")
    public void checkBackUpEmptyFolders(String checkboxState) {
        Assert.assertEquals(nbfFilesWizardAdvancedFilterPage.checkBackUpEmptyFolders(), checkboxState);
    }

    @Step("checking Back Up Objects Modified. Checkbox {0}")
    public void checkBackUpObjectsModified(String checkboxState) {
        Assert.assertEquals(nbfFilesWizardAdvancedFilterPage
                .checkBackUpObjectsModified(), checkboxState);

        if (checkboxState.equals(CHECKED_CHECKBOX)) {
            Assert.assertEquals(nbfFilesWizardAdvancedFilterPage
                    .checkBackUpObjectsModifiedInputField(), backUpObjectsModified);
        }
    }


    @Step("checking Back up objects modified since. Checkbox {0}")
    public void checkBackUpObjectsModifiedSince(String checkboxState) {
        Assert.assertEquals(nbfFilesWizardAdvancedFilterPage
                .checkBackUpObjectsModifiedSince(), checkboxState);

        if (checkboxState.equals(CHECKED_CHECKBOX)) {
            Assert.assertEquals(nbfFilesWizardAdvancedFilterPage
                    .checkBackUpObjectsModifiedSinceDatePicker(), datePicker);
            Assert.assertEquals(nbfFilesWizardAdvancedFilterPage
                    .checkBackUpObjectsModifiedSinceTimePicker(), timePicker);
        }
    }

    @Step("checking Back up objects modified since. Checkbox {0}")
    public void checkDoNotBackUpFilesLargerThan(String checkboxState) {
        Assert.assertEquals(nbfFilesWizardAdvancedFilterPage
                .checkDoNotBackUpFilesLargerThan(), checkboxState);

        if (checkboxState.equals(CHECKED_CHECKBOX)) {
            Assert.assertEquals(nbfFilesWizardAdvancedFilterPage
                    .checkDoNotBackUpFilesLargerThanInputField(), doNotBackUpFilesLargerThan);
        }
    }

    @Step("checking Do Not Back Up System And Hidden Files. Checkbox {0}")
    public void checkDoNotBackUpSystemAndHiddenFiles(String checkboxState) {
        Assert.assertEquals(nbfFilesWizardAdvancedFilterPage
                .checkDoNotBackUpSystemAndHiddenFiles(), checkboxState);

        if (checkboxState.equals(CHECKED_CHECKBOX)) {
            Assert.assertEquals(nbfFilesWizardAdvancedFilterPage
                    .checkDoNotBackUpFilesLargerThanInputField(), doNotBackUpFilesLargerThan);
        }
    }

    @Step("check Do Not Back Up Files Used By Other Processes At The Moment Of The Backup Plan Run. Checkbox {0}")
    public void checkDoNotBackUpFilesUsedByOtherProcesses(String checkboxState) {
        Assert.assertEquals(nbfFilesWizardAdvancedFilterPage
                        .checkDoNotBackUpFilesUsedByOtherProcessesAtTheMomentOfTheBackupPlanRun(), checkboxState);
    }

    @Step("check Do Not Back Up On Demand Files. Checkbox {0}")
    public void checkDoNotBackUpOnDemandFiles(String checkboxState) {
        Assert.assertEquals(nbfFilesWizardAdvancedFilterPage.checkDoNotBackUpOnDemandFiles(), checkboxState);
    }

}
