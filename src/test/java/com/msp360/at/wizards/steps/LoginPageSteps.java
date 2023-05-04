package com.msp360.at.wizards.steps;

import com.msp360.at.wizards.LoginPage;
import com.msp360.at.wizards.tests.BaseClass;
import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


public class LoginPageSteps extends BaseClass {
    private final LoginPage loginPage;

    public LoginPageSteps(WebDriver driver) {
        BaseClass.getDriver();
        loginPage = new LoginPage(driver);
    }

    @Step("Open Login page")
    public void openLoginPage() {
        driver.get().manage().window().setSize(new Dimension(1920, 1080));
        driver.get().navigate().to(URL_NEW_RM_PAGE);
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
    }

    @Step("Log in under Provider: email {0}, password {1}")
    public void loginProvider(String email, String password) {
        loginPage.fillEmail(email);
        loginPage.fillPassword(password);
        loginPage.pushLoginButton();
    }

    @Step("Log out")
    public void logOut() {
        loginPage.pushLogOffDropdown();
        loginPage.pushLogOffButton();
        driver.get().quit();
    }

}
