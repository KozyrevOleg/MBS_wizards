package com.msp360.at.wizards.steps;

import com.msp360.at.wizards.NbfFilesWizardCompressionAndEncryptionPage;
import com.msp360.at.wizards.tests.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class NbfFilesWizardCompressionAndEncryptionPageSteps extends BaseClass {

    private final NbfFilesWizardCompressionAndEncryptionPage nbfFilesWizardCompressionAndEncryptionPage;

    //Compression and Encryption step
    protected static final String ENTER_INVALID_PASSWORD = "123";
    protected static final String CONFIRM_INVALID_PASSWORD = "1234";
    protected static final String ENTER_VALID_PASSWORD = "sobakakot123!";
    protected static final String CONFIRM_VALID_PASSWORD = "sobakakot123!";
    protected static final String PASSWORD_HINT = "sobakakot123!!";
    protected static final String ALGORITHM_AES128 = "AES 128-bit";
    protected static final String ALGORITHM_AES192 = "AES 192-bit";
    protected static final String ALGORITHM_AES256 = "AES 256-bit";

    public NbfFilesWizardCompressionAndEncryptionPageSteps(WebDriver driver) {
        BaseClass.getDriver();
        nbfFilesWizardCompressionAndEncryptionPage = new NbfFilesWizardCompressionAndEncryptionPage(driver);
    }

    @Step("Fill Compression and Encryption Options step. Case 1. Following options are selected: "
            + "Compression, Encryption, Algorithm SES 192, Hint,"
            + "Server Side Encryption, Validation passwords and hint fields")

    public void fillCompressionAndEncryptionCase1() throws InterruptedException {
        nbfFilesWizardCompressionAndEncryptionPage.selectEncryption();
        nbfFilesWizardCompressionAndEncryptionPage.enterPassword(ENTER_INVALID_PASSWORD);
        nbfFilesWizardCompressionAndEncryptionPage.confirmPassword(CONFIRM_INVALID_PASSWORD);
        nbfFilesWizardCompressionAndEncryptionPage.validatePassword();
        nbfFilesWizardCompressionAndEncryptionPage.enterPassword(ENTER_VALID_PASSWORD);
        nbfFilesWizardCompressionAndEncryptionPage.confirmPassword(CONFIRM_VALID_PASSWORD);
        nbfFilesWizardCompressionAndEncryptionPage.enterHint(PASSWORD_HINT);
        nbfFilesWizardCompressionAndEncryptionPage.selectAlgorithm(ALGORITHM_AES192);
        nbfFilesWizardCompressionAndEncryptionPage.selectServerSideEncryption();
        Thread.sleep(2000);
    }

    @Step("Verify Compression and Encryption step Case 1. Destination {0}")
    public void verifyCompressionAndEncryptionCase1(String destination) {
        checkCompression(CHECKED_CHECKBOX);
        checkEncryption(CHECKED_CHECKBOX);
        //проверка заполнения полей password????
        checkAlgorithm(ALGORITHM_AES192);
        //проверка заполнения hint????
        checkUseServerSideEncryption(destination, CHECKED_CHECKBOX);

    }

    @Step("Verify Compression and Encryption step default settings. Destination {0}")
    public void verifyCompressionAndEncryptionDefaultSettings(String destination) {
        checkCompression(CHECKED_CHECKBOX);
        checkEncryption(UNCHECKED_CHECKBOX);
        //проверка заполнения полей password????
        checkAlgorithm(ALGORITHM_AES256);
        //проверка заполнения hint????
        checkUseServerSideEncryption(destination, UNCHECKED_CHECKBOX);

    }

    @Step("Fill Compression and Encryption Options step. Case 2. Following options are selected: "
            + "Encryption, Algorithm SES 256")

    public void fillCompressionAndEncryptionStepCase2() throws InterruptedException {
        nbfFilesWizardCompressionAndEncryptionPage.selectCompression();
        nbfFilesWizardCompressionAndEncryptionPage.selectEncryption();
        nbfFilesWizardCompressionAndEncryptionPage.enterPassword(ENTER_VALID_PASSWORD);
        nbfFilesWizardCompressionAndEncryptionPage.confirmPassword(CONFIRM_VALID_PASSWORD);
        nbfFilesWizardCompressionAndEncryptionPage.selectAlgorithm(ALGORITHM_AES128);
        Thread.sleep(3000);
    }

    @Step("Verify Compression and Encryption step Case 2. Destination {0}")
    public void verifyCompressionAndEncryptionCase2(String destination) {
        checkCompression(UNCHECKED_CHECKBOX);
        checkEncryption(CHECKED_CHECKBOX);
        //проверка заполнения полей password????
        checkAlgorithm(ALGORITHM_AES128);
        checkUseServerSideEncryption(destination, UNCHECKED_CHECKBOX);
        //проверка заполнения hint????
    }

    @Step("checking Compression option. Checkbox {0}")
    public void checkCompression(String checkboxState) {
        Assert.assertEquals(nbfFilesWizardCompressionAndEncryptionPage.checkCompression(), checkboxState);
    }


    @Step("checking Encryption option. Checkbox {0}")
    public void checkEncryption(String checkboxState) {
        Assert.assertEquals(nbfFilesWizardCompressionAndEncryptionPage.checkEncryption(), checkboxState);
    }

    @Step("checking Algorithm option. Algorithm {0}")
    public void checkAlgorithm(String algorithmType) {
        Assert.assertEquals(nbfFilesWizardCompressionAndEncryptionPage.checkAlgorithm(), algorithmType);
    }

    @Step("checking Use server side encryption option isn't displayed. Destination {0}, Checkbox {1}")
    public void checkUseServerSideEncryption(String destination, String checkboxState) {
        switch (destination) {
            case S3_IMMUT_OFF:
            case S3_IMMUT_ON:
                Assert.assertEquals(String.valueOf(nbfFilesWizardCompressionAndEncryptionPage
                        .isCheckboxExist(nbfFilesWizardCompressionAndEncryptionPage
                                .serverSideEncryption)), "true");
                Assert.assertEquals(nbfFilesWizardCompressionAndEncryptionPage
                        .checkUseServerSideEncryption(), checkboxState);
                break;

            default:
                Assert.assertEquals(String.valueOf(nbfFilesWizardCompressionAndEncryptionPage
                        .isCheckboxExist(nbfFilesWizardCompressionAndEncryptionPage
                                .serverSideEncryption)), "false");
                break;
        }
    }

}
