package com.msp360.at.wizards;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NbfFilesWizardPage extends BaseClassPage {

    //JavascriptExecutor js = (JavascriptExecutor) driver;

    public NbfFilesWizardPage(WebDriver driver) {
        super(driver);
    }

    //WIZARD BUTTONS
    @FindBy(xpath = "//button[normalize-space()='Next']")
    private WebElement nextButton;

    public void clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        //this.nextButton.click();
    }

    @FindBy(xpath = "//mbs-button-group[@class='mr-3']//mbs-button[1]//button[1]")
    private WebElement backButton;

    public void clickBack() {
        wait.until(ExpectedConditions.elementToBeClickable(backButton));
        this.backButton.click();
    }

    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement saveButton;

    public void saveThePlan() {
        this.saveButton.click();
    }

    //Retention policy step
    @FindBy(xpath = "//div[@class='modal-footer']//mbs-button")
    public WebElement fullBackupIsNotScheduled;

    public void fullBackupIsNotScheduledModal() {
        this.fullBackupIsNotScheduled.click();
    }


    //Assertions

}
