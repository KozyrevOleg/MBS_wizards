package com.msp360.at.wizards;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NbfFilesWizardWhereToBackUpPage extends BaseClassPage {

    public NbfFilesWizardWhereToBackUpPage(WebDriver driver) {
        super(driver);
    }

    //SELECT BACKUP STORAGE STEP
    @FindBy(xpath = "//a[normalize-space()='click here']")
    private WebElement addNewStorageAccountLink;

    public void clickAddNewStorageAccountLink() {
        this.addNewStorageAccountLink.click();
    }

    public void selectDestination(String destinationType) {
        driver.findElement(By.xpath("//div[contains(text(),'" + destinationType + "')]")).click();
    }

    @FindBy(xpath = "//button[@id='mainPlaceHolder_ucAccountList_btnAddAccountNewWizard']")
    private WebElement addAccountButton;

    public String addNewAccount() {
        return this.addAccountButton.getDomProperty("textContent");
    }

    //Assertions
    public String checkDestination() {
        WebElement destinationName;
        destinationName = driver
                .findElement(By.xpath("//div[@class='mbs-table-grid_row -selected']//div[@role='cell']"));
        return destinationName.getText();
    }

}
