package com.msp360.at.wizards;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NbfFilesWizardCompressionAndEncryptionPage extends BaseClassPage {

    JavascriptExecutor js = (JavascriptExecutor) driver;

    public NbfFilesWizardCompressionAndEncryptionPage(WebDriver driver) {
        super(driver);
    }

    //Compression and Encryption Options

    @FindBy(xpath = "//label[@for='enable-compression-checkbox']")
    public WebElement enableCompression;

    public void selectCompression() {
        this.enableCompression.click();
    }

    @FindBy(xpath = "//label[@for='enable-encryption-checkbox']")
    public WebElement enableEncryption;

    public void selectEncryption() {
        this.enableEncryption.click();
    }

    @FindBy(xpath = "//input[@id='compressionEncryptionPassword']")
    public WebElement enterPassword;

    @FindBy(xpath = "//mbs-input[@name='Password']//span[@class='fa fa-eye']")
    public WebElement passwordEye;

    public void enterPassword(String password) {
        this.enterPassword.clear();
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            String s = String.valueOf(c);
            this.enterPassword.sendKeys(s);
        }
    }

    @FindBy(xpath = "//input[@id='compressionEncryptionConfirmationPassword']")
    public WebElement confirmPassword;

    @FindBy(xpath = "//mbs-input[@name='ConfirmationPassword']//span[@class='fa fa-eye']")
    public WebElement confirmPasswordEye;

    public void confirmPassword(String confirmPassword) {
        this.confirmPassword.clear();
        for (int i = 0; i < confirmPassword.length(); i++) {
            char c = confirmPassword.charAt(i);
            String s = String.valueOf(c);
            this.confirmPassword.sendKeys(s);
        }
    }

    @FindBy(xpath = "//mbs-input[@formcontrolname='hint']//input[@name='undefined']")
    public WebElement hint;

    public void enterHint(String hint) {
        this.enterPassword.clear();
        for (int i = 0; i < hint.length(); i++) {
            char c = hint.charAt(i);
            String s = String.valueOf(c);
            this.hint.sendKeys(s);
        }
    }

    @FindBy(xpath = "//div[@class='tooltip-inner']")
    public WebElement seekPassword;

    @FindBy(xpath = "//div[@class='invalid-feedback pre-wrap']")
    public WebElement passwordDoNotMatch;

    public void validatePassword() {
        this.seekPassword.click();
        this.passwordDoNotMatch.click();
    }

    @FindBy(xpath = "//div[@role='combobox']")
    public WebElement algorithm;

    public void selectAlgorithm(String algorithmType) {
        this.algorithm.click();
        driver.findElement(By.xpath("//span[normalize-space()='" + algorithmType + "']")).click();
    }

    //Amazon S3 only
    @FindBy(xpath = "//label[@for='server-side-encryption-checkbox']")
    public WebElement serverSideEncryption;

    public void selectServerSideEncryption() {
        this.serverSideEncryption.click();
    }

    //Assertions

    //Compression and Encryption step
    public String checkCompression() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-form-group:nth-child(1) > div > mbs-checkbox > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkEncryption() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-form-group:nth-child(2) > div > mbs-checkbox > div.mbs-checkbox > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

    public String checkAlgorithm() {
        WebElement alg = driver.findElement(By.xpath("//span[@class='ng-value-label']"));
        return alg.getText();
    }

    //как проверить заполненность полей??
    /*    public String checkHint() {
        WebElement algorithm = driver.findElement(By.xpath("span[@class='ng-value-label']"));
        return algorithm.getText();
    }*/

    public String checkUseServerSideEncryption() {
        return js.executeScript("return window.getComputedStyle"
                        + "(document.querySelector"
                        + "('mbs-form-group:nth-child(3) > div > mbs-checkbox > div > label'),'::before')"
                        + ".getPropertyValue('border-color')")
                .toString();
    }

}
