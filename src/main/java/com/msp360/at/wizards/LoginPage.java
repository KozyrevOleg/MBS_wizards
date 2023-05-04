package com.msp360.at.wizards;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseClassPage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='txtLogin']")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id='txtPassword']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='btnLogin']")
    private WebElement loginButton;

    // the dropdown list about short user info and Log off button
    @FindBy(xpath = "//button[@id='dropdownUser']")
    private WebElement dropdownUser;

    @FindBy(xpath = "//button[normalize-space()='Log off']")
    private WebElement logOffButton;


    public void fillEmail(String body) {
        this.emailField.sendKeys(body);
    }

    public void fillPassword(String body) {
        this.passwordField.sendKeys(body);
    }

    public void pushLoginButton() {
        this.loginButton.click();
    }

    public void pushLogOffDropdown() {
        this.dropdownUser.click();
    }

    public void pushLogOffButton() {
        this.logOffButton.click();
    }

}
