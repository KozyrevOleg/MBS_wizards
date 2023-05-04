package com.msp360.at.wizards.steps;

import com.msp360.at.wizards.NbfFilesWizardPage;
import com.msp360.at.wizards.tests.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NbfFilesWizardPageSteps extends BaseClass {

    private final NbfFilesWizardPage nbfFilesWizardPage;

    public NbfFilesWizardPageSteps(WebDriver driver) {
        BaseClass.getDriver();
        nbfFilesWizardPage = new NbfFilesWizardPage(driver);
    }


    @Step("Click Next")
    public void clickNext() {
        nbfFilesWizardPage.clickNext();
    }

    @Step("Click Back")
    public void clickBack() {
        nbfFilesWizardPage.clickBack();
    }

    @Step("Save the plan")
    public void saveThePlan() {
        nbfFilesWizardPage.saveThePlan();
    }


}
