package com.msp360.at.wizards;

import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseClassPage {
    public final WebDriver driver;
    public final WebDriverWait wait;

    protected static final String S3_STANDARD = "Standard";
    protected static final String S3_INTELLIGENT_TIERING = "Intelligent-Tiering";
    protected static final String S3_STANDARD_IA = "Standard-IA";
    protected static final String S3_ONE_ZONE_IA = "One Zone-IA";
    protected static final String S3_GLACIER_FLEXIBLE_RETRIEVAL = "Glacier Flexible Retrieval";
    protected static final String S3_GLACIER_DEEP_ARCHIVE = "Glacier Deep Archive";
    protected static final String S3_GLACIER_INSTANT_RETRIEVAL = "Glacier Instant Retrieval";
    protected static final String AZURE_HOT = "Hot";
    protected static final String AZURE_COOL = "Cool";
    protected static final String AZURE_ARCHIVE = "Archive";
    protected static final String S3_COMPATIBLE_OFF = "!?";
    protected static final String S3_COMPATIBLE_SYNTHETIC_ON = "?";
    protected static final String MINIO_SYNTHETIC_ON = "??";
    protected static final String MINIO_SYNTHETIC_OFF = "!??";

    protected static final String CHECKED_CHECKBOX = "rgb(45, 108, 162)";
    //"\"\uE914\"";
    protected static final String UNCHECKED_CHECKBOX = "rgb(227, 227, 227)";
    protected static final String DISABLED_CHECKBOX = "rgb(227, 227, 227)";
    protected static final String DISABLED_CHECKED_CHECKBOX = "rgb(129, 167, 199)";
    protected static final String ENABLED_RADIOBUTTON = "rgb(45, 108, 162)";
    protected static final String DISABLED_RADIOBUTTON = "rgb(227, 227, 227)";

    protected static final String CHECKBOX_DOESNT_EXIST = "Checkbox doesn't exist";

    protected static final String NO_CLASSES = "";

    public BaseClassPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public boolean isCheckboxExist(WebElement ele) {
        try {
            ele.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String isDataExist(String ele) {
        try {
            return ele;
        } catch (Exception e) {
            return "selected data is invalid";
        }
    }

}
