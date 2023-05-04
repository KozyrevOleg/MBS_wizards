package com.msp360.at.wizards;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NbfFilesWizardBackupConsistencyCheckPage extends BaseClassPage {

    JavascriptExecutor js = (JavascriptExecutor) driver;

    public NbfFilesWizardBackupConsistencyCheckPage(WebDriver driver) {
        super(driver);
    }

    //Backup Consistency Check
    @FindBy(xpath = "//label[@for='use-full-consistency-checkbox']")
    public WebElement backupConsistencyCheck;

    public void selectBackupConsistencyCheck() {
        this.backupConsistencyCheck.click();
    }

    //Assertions Backup Consistency Check step
    public String checkBackupConsistencyCheck() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-archive-consistency-step > section > div > "
                        + "mbs-form-group > div > mbs-checkbox > div.mbs-checkbox > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }
}
