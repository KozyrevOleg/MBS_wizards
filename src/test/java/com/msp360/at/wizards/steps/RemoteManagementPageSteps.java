package com.msp360.at.wizards.steps;

import com.msp360.at.wizards.RemoteManagementPage;
import com.msp360.at.wizards.tests.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RemoteManagementPageSteps extends BaseClass {
    public final RemoteManagementPage remoteManagementPage;

    public RemoteManagementPageSteps(WebDriver driver) {
        BaseClass.getDriver();
        remoteManagementPage = new RemoteManagementPage(driver);
    }

    @Step("Open Remote Management page")
    public void openRemoteManagementPage() {
        remoteManagementPage.selectComputerTab();
        remoteManagementPage.selectRemoteManagementPage();
    }


    @Step("switch on the Beta page")
    public void switchBeta() {
        remoteManagementPage.switchToBeta();
    }

    @Step("Select a PC. Computer name {0}")
    public void selectPc(String computerName) {
        remoteManagementPage.selectMyPC(computerName);
    }

    @Step("Add new backup plan")
    public void addBackupPlan() {
        remoteManagementPage.addBackupPlan();
    }

    @Step("Create new NBF backup plan")
    public void createNBFBackupPlan() {
        try {
            remoteManagementPage.addBackupPlan();
            remoteManagementPage.createNbfFilesBackupPlan();
        } catch (StaleElementReferenceException e) {
            remoteManagementPage.addBackupPlan();
            remoteManagementPage.createNbfFilesBackupPlan();
        }
    }

    @Step("Close alerts and feedback modal")
    public void closeAlertsAndFeedback() {
        remoteManagementPage.closeAgentVersionsAlert();
        remoteManagementPage.closeWasabiDeletionDataAlert();
    }


}
