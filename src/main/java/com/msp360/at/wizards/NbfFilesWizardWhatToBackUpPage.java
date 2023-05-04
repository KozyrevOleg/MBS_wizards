package com.msp360.at.wizards;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NbfFilesWizardWhatToBackUpPage extends BaseClassPage {

    public NbfFilesWizardWhatToBackUpPage(WebDriver driver) {
        super(driver);
    }

    //Select all disk
    @FindBy(xpath = "//label[@for='0']")
    private WebElement selectBackupData;

    @FindBy(xpath = "//mbs-button[@class='mbs-tree-item_arrow -isCtrl']//button[@type='button']")
    private WebElement expandTree;

    public void selectTreeDataForBackup() {
        //this.expandTree.click();
        this.selectBackupData.click();
    }

    @FindBy(xpath = "//button[normalize-space()='Show plain text']")
    private WebElement showPlainText;

    public void selectShowPlainText() throws InterruptedException {
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(showPlainText)).click();
    }

    //include a path
    @FindBy(xpath = "//mbs-input[@formcontrolname='includeString']//input[@name='undefined']")
    private WebElement includeField;


    @FindBy(xpath = "//mbs-input[@formcontrolname='includeString']//button[@type='button']")
    private WebElement includeFieldAddButton;

    public void includeFieldAddNewPath(String filePath) {
        this.includeField.clear();
        for (int i = 0; i < filePath.length(); i++) {
            char c = filePath.charAt(i);
            String s = String.valueOf(c);
            this.includeField.sendKeys(s);
        }
        this.includeFieldAddButton.click();
    }

    //include an invalid path
    @FindBy(xpath = "//div[@class='invalid-feedback pre-wrap']")
    private WebElement checkInvalidPath;

    public void includeFieldAddNewInvalidPath(String filePath) {
        this.includeField.clear();
        for (int i = 0; i < filePath.length(); i++) {
            char c = filePath.charAt(i);
            String s = String.valueOf(c);
            this.includeField.sendKeys(s);
        }
        this.includeFieldAddButton.click();
        this.checkInvalidPath.click();
    }

    //change a path name
    @FindBy(xpath = "//div[@class='mbs-card_body']//span[@class='ctrl-ico fa fa-pencil px-1']")
    private WebElement changePath;

    @FindBy(xpath = "//span[@class='fa fa-check-circle text-success']")
    private WebElement acceptChangesButton;

    //Paste From Clipboard
    @FindBy(css = "mbs-form-group:nth-child(2) > div:nth-child(1) > "
            + "div:nth-child(1) > div:nth-child(2) > "
            + "mbs-button:nth-child(1) > button:nth-child(1) > span:nth-child(2)")
    private WebElement includeFieldCopyFromClipboard;

    public void changePath(String filePath) {
        Actions action = new Actions(driver);
        action.moveToElement(changePath).perform();
        this.changePath.click();
        this.includeField.clear();
        for (int i = 0; i < filePath.length(); i++) {
            char c = filePath.charAt(i);
            String s = String.valueOf(c);
            this.includeField.sendKeys(s);
        }
        this.acceptChangesButton.click();

    }

    public void includeFieldCopyPastePath(String filePath) {
        Actions action = new Actions(driver);
        action.moveToElement(changePath).perform();
        this.includeField.clear();
        for (int i = 0; i < filePath.length(); i++) {
            char c = filePath.charAt(i);
            String s = String.valueOf(c);
            this.includeField.sendKeys(s);
        }
        this.includeField.sendKeys(Keys.CONTROL + "a");
        this.includeField.sendKeys(Keys.CONTROL + "c");
        this.includeField.clear();
        this.includeFieldCopyFromClipboard.click();
    }


    //delete a path
    @FindBy(xpath = "//div[@class='mbs-card_body']//span[@class='ctrl-ico fa fa-times-circle px-1']")
    private WebElement deletePath;


    public void deletePathButton() {
        Actions action = new Actions(driver);
        action.moveToElement(deletePath).perform();
        this.deletePath.click();
    }


    //exclude a path
    @FindBy(xpath = "//mbs-input[@formcontrolname='excludeString']//input[@name='undefined']")
    private WebElement excludeField;


    @FindBy(xpath = "//mbs-input[@formcontrolname='excludeString']//button[@type='button']")
    private WebElement excludeFieldAddButton;

    //Paste From Clipboard
    @FindBy(css = "mbs-form-group:nth-child(3) > div:nth-child(1) > "
            + "div:nth-child(1) > div:nth-child(2) > "
            + "mbs-button:nth-child(1) > button:nth-child(1) > span:nth-child(2)")
    private WebElement excludeFieldCopyFromClipboard;

    public void excludeFieldAddNewPath(String filePath) {
        for (int i = 0; i < filePath.length(); i++) {
            char c = filePath.charAt(i);
            String s = String.valueOf(c);
            this.excludeField.sendKeys(s);
        }
        wait.until(ExpectedConditions.elementToBeClickable(this.excludeFieldAddButton)).click();
    }

    public void excludeFieldAddNewInvalidPath(String filePath) {
        this.excludeField.clear();
        for (int i = 0; i < filePath.length(); i++) {
            char c = filePath.charAt(i);
            String s = String.valueOf(c);
            this.excludeField.sendKeys(s);
        }
        wait.until(ExpectedConditions.elementToBeClickable(this.excludeFieldAddButton)).click();
        this.checkInvalidPath.click();
    }

    public void excludeFieldCopyPastePath(String filePath) throws InterruptedException {
        Actions action = new Actions(driver);
        action.moveToElement(changePath).perform();
        this.excludeField.clear();
        for (int i = 0; i < filePath.length(); i++) {
            char c = filePath.charAt(i);
            String s = String.valueOf(c);
            this.excludeField.sendKeys(s);
        }
        this.excludeField.sendKeys(Keys.CONTROL + "a");
        this.excludeField.sendKeys(Keys.CONTROL + "c");
        this.excludeField.clear();
        this.excludeFieldCopyFromClipboard.click();
    }

    @FindBy(xpath = "//button[normalize-space()='Ok']")
    private WebElement invalidPathModal;

    public void acceptInvalidPathModal() {
        this.invalidPathModal.click();
    }

    //Assertions
    public String checkSelectedPathIncludeField(String selectedPath) {
        WebElement path = driver
                .findElement(By.xpath("//span[@class='pre-wrap'][normalize-space()='" + selectedPath + "']"));
        return path.getDomProperty("textContent");
    }

    public String checkSelectedPathExcludeField(String selectedPath) {
        WebElement path = driver
                .findElement(By.xpath("//span[normalize-space()='" + selectedPath + "']"));
        return path.getDomProperty("textContent");
    }
}
