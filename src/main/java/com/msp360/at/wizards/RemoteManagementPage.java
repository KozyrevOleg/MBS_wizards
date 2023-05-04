package com.msp360.at.wizards;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RemoteManagementPage extends BaseClassPage {

    public RemoteManagementPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='computers']")
    private WebElement computersTab;

    @FindBy(xpath = "//a[normalize-space()='Remote Management']")
    private WebElement remoteManagementPage;

    @FindBy(css = "div > iframe")
    private WebElement switchIframeForBetaPage;

    @FindBy(xpath = "//*[@id='cont']/header/div[1]/a/span[2]")
    private WebElement switchToggle;

    @FindBy(xpath = "//span[normalize-space()='DONT-TOUCH']")
    private WebElement selectMyPC;

    //old locator
    /*@FindBy(xpath = "//mbs-button[@icon='ico ico-Plus']")
    private WebElement addBackupPlan;*/

    @FindBy(xpath = "//span[@class='btn-ico ico ico-Plus']")
    private WebElement addBackupPlan;

    @FindBy(xpath = "//button[normalize-space()='Files Backup Plan']")
    private WebElement nbfFilesBackupPlan;

    //the alert about old agent versions
    @FindBy(xpath = "//mbs-alert[@class='alert alert-danger']//span[@class='ctrl-ico ico ico-Close']")
    private WebElement agentVersionsAlert;

    //the alert about Wasabi data
    @FindBy(xpath = "//mbs-alert[@class='mb-0 alert alert-danger']//span[@class='ctrl-ico ico ico-Close']")
    private WebElement wasabiDeletionDataAlert;

    //the feedback modal
    @FindBy(xpath = "//*[@class='mbs-feedback-ctrl mbs-feedback-close -isCtrl']")
    private WebElement feedbackModal;


    public void refresh() {
        driver.navigate().refresh();
    }

    public void selectComputerTab() {
        this.computersTab.click();
    }

    public void selectRemoteManagementPage() {
        this.remoteManagementPage.click();
    }

    public void switchToBeta() {
        WebDriverWait wait;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(switchIframeForBetaPage));
        this.switchToggle.click();
    }

    public void selectMyPC(String computerName) {
        WebDriverWait wait;
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//span[normalize-space()='" + computerName + "']")));
        this.selectMyPC.click();
    }

    public void addBackupPlan() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(addBackupPlan));
        //wait.until(ExpectedConditions.visibilityOf(addBackupPlan));
        this.addBackupPlan.click();
    }

    public void createNbfFilesBackupPlan() {
        this.nbfFilesBackupPlan.click();
    }

    public void closeAgentVersionsAlert() {
        this.agentVersionsAlert.click();
    }

    public void closeWasabiDeletionDataAlert() {
        this.wasabiDeletionDataAlert.click();
    }

    public void closeFeedbackModal() {
        this.feedbackModal.click();
    }

}
