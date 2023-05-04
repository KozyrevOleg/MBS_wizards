package com.msp360.at.wizards.steps;

import com.msp360.at.wizards.RemoteManagementSidePanelPage;
import com.msp360.at.wizards.tests.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class RemoteManagementSidePanelPageSteps extends BaseClass {
    public final RemoteManagementSidePanelPage remoteManagementSidePanelPage;

    public RemoteManagementSidePanelPageSteps(WebDriver driver) {
        BaseClass.getDriver();
        remoteManagementSidePanelPage = new RemoteManagementSidePanelPage(driver);
    }

    @Step("Close the side panel")
    public void closeSidePanel() {
        remoteManagementSidePanelPage.closeSidePanel();
    }

    @Step("Refresh the side panel")
    public void refreshSidePanel() {
        remoteManagementSidePanelPage.refreshSidePanel();
    }

    @Step("Edit the plan. Plan name {0}")
    public void editPlan(String planName) throws InterruptedException {
        //System.out.println(remoteManagementSidePanelPage.planIdSettingsButton());
        //remoteManagementSidePanelPage.clickSettingsButton();
        remoteManagementSidePanelPage.clickSettingsButtonById(planName);
        remoteManagementSidePanelPage.clickEditButton();
    }

    @Step("Delete the plan. Plan name {0}")
    public void deletePlan(String planName) throws InterruptedException {
        //remoteManagementSidePanelPage.clickSettingsButton();
        remoteManagementSidePanelPage.clickSettingsButtonById(planName);
        remoteManagementSidePanelPage.clickDeleteButton();
        remoteManagementSidePanelPage.deleteModalDeleteButton();
    }

    //Assertions
    @Step("Verify the plan name. Plan name {0}, Class type {1}")
    public void checkPlanName(String getText, String classType) {
        Assert.assertEquals(remoteManagementSidePanelPage.checkPlanName(getText), classType);
    }

    //How to make dynamic destination name?
    /*    @Step("checking Destination in short plan menu")
    public void checkDestinationName(String destinationName) {
        Assert.assertTrue(true,remoteManagementSidePanelPage.checkDestinationNameInShortMenu(destinationName));
    }*/

}
