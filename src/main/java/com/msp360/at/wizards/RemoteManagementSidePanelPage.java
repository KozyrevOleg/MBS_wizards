package com.msp360.at.wizards;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RemoteManagementSidePanelPage extends BaseClassPage {

    public RemoteManagementSidePanelPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//mbs-button[@icon='ico ico-Refresh']//button[@type='button']")
    private WebElement refreshing;

    public void refreshSidePanel() {
        this.refreshing.click();
    }

    @FindBy(xpath = "//*[@id='computer_backup_info']/aside/header/div/div[3]/mbs-button/button/span")
    private WebElement closeSidePanelButton;

    public void closeSidePanel() {
        this.closeSidePanelButton.click();
    }
    //sdfsdf
    /*@FindBy(xpath = "//mbs-card[@id='plan-5d30ebe9-dd94-4ddb-80bd-65bcbbecd043']" +
            "//span[@class='ico ico-EllipsisV text-base']")*/

    @FindBy(xpath = "//span[@class='ico ico-EllipsisV text-base']")
    private WebElement settingsButton;

    /*@FindBy(xpath = "//mbs-card[@id='plan-5d30ebe9-dd94-4ddb-80bd-65bcbbecd043']" +
            "//span[@class='ico ico-EllipsisV text-base']")*/
    /*WebElement password = driver.findElement(By.id("password"));
    WebElement email = driver.findElement(with(By.tagName("input")).above(passwordField));*/

    public void clickSettingsButtonById(String planName) throws InterruptedException {

        WebElement name = driver.findElement(By.xpath("//span[contains(text(),'" + planName + "')]"));
        Thread.sleep(3000);
        //The following code for Chrome only
        //new Actions(driver).scrollToElement(name).perform();

        int deltaY = name.getRect().y;
        new Actions(driver)
                .scrollByAmount(0, deltaY)
                .perform();
        Thread.sleep(1000);
        WebElement settings =
                driver.findElement(with(By.xpath("//div[@class='w-100']/div[@class='form-row align-items-center']"
                        + "/div[@class='col-auto d-flex']/mbs-button")).toRightOf(name));
        wait.until(ExpectedConditions.elementToBeClickable(settings)).click();
    }

    public void clickSettingsButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(settingsButton));
        this.settingsButton.click();
    }

    /*    @FindBy(xpath = "//button[normalize-space()='Edit']")
    private WebElement editButton;*/

    public void clickEditButton() {
        //this.editButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        /*wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/button[1]")));
        WebElement ele = driver.findElement(By.xpath("/html/body/div[3]/div/button[1]"));*/

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='dropdown-menu show']"
                + "//button[@class='dropdown-item'][normalize-space()='Edit']")));
        WebElement ele = driver.findElement(By.xpath("//div[@class='dropdown-menu show']"
                + "//button[@class='dropdown-item'][normalize-space()='Edit']"));


        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", ele);
    }

    /*    @FindBy(xpath = "//button[normalize-space()='Delete']")
    private WebElement deleteButton;*/

    @FindBy(xpath = "//button[@type='button'][normalize-space()='Delete']")
    public WebElement deleteModalDeleteButton;

    public void deleteModalDeleteButton() {
        this.deleteModalDeleteButton.click();
    }

    /*    public void selectPlanForDeletionByName(String name) {
        WebElement planName = driver.findElement(By.xpath("//span[contains(text(),'" + name + "')]"));


        }
    }*/

    public void clickDeleteButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='dropdown-menu show']"
                + "//button[@class='dropdown-item'][normalize-space()='Delete']")));
        WebElement ele = driver.findElement(By.xpath("//div[@class='dropdown-menu show']"
                + "//button[@class='dropdown-item'][normalize-space()='Delete']"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", ele);

    }


    //Assertions
    public String checkPlanName(String name) {
        WebElement planName = driver.findElement(By.xpath("//span[contains(text(),'" + name + "')]"));
        return planName.getText();
    }

    public String checkDestinationNameInShortMenu(String destinationName) {
        WebElement destination = driver.findElement(By.xpath("//td[normalize-space()='" + destinationName + "']"));
        return destination.getText();
    }

}
