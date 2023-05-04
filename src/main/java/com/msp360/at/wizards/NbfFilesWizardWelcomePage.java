package com.msp360.at.wizards;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NbfFilesWizardWelcomePage extends BaseClassPage {

    public NbfFilesWizardWelcomePage(WebDriver driver) {
        super(driver);
    }

    //WELCOME PAGE STEP
    @FindBy(xpath = "//input[@id='planName']")
    private WebElement planName;

    public void fillPlanName(String name) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(planName)).clear();
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            String s = String.valueOf(c);
            this.planName.sendKeys(s);
        }
    }

    @FindBy(xpath = "//a[normalize-space()='Learn more']")
    private WebElement learnMore;

    public void clickLearMore() {
        this.learnMore.click();
    }

    /*@FindBy(xpath = "//iframe[@id='helpframe']")
    private WebElement switchIframeForLearnMoreTab;*/

    @FindBy(css = "div > iframe")
    private WebElement switchIframeForLearnMoreTab;

    public void switchIframe() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(switchIframeForLearnMoreTab));
    }


    //Assertions
    public String checkPlanName() {
        return this.planName.getDomProperty("value");
    }

    public String checkNameOfTheStep(String stepName) {
        return driver.findElement(By.xpath("//h4[normalize-space()='" + stepName + "']"))
                .getDomProperty("textContent");
    }

    /* WebElement destinationName;
    destinationName = driver.findElement(By.xpath("//div[@class='mbs-table-grid_row -selected']//div[@role='cell']"));
        return destinationName.getText();*/
}
