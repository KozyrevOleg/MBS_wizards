package com.msp360.at.wizards.tests;

import com.msp360.at.wizards.NbfFilesWizardPage;
import com.msp360.at.wizards.steps.LoginPageSteps;
import com.msp360.at.wizards.steps.NbfFilesWizardAdvancedFilterPageSteps;
import com.msp360.at.wizards.steps.NbfFilesWizardAdvancedOptionsPageSteps;
import com.msp360.at.wizards.steps.NbfFilesWizardBackupConsistencyCheckPageSteps;
import com.msp360.at.wizards.steps.NbfFilesWizardCompressionAndEncryptionPageSteps;
import com.msp360.at.wizards.steps.NbfFilesWizardPageSteps;
import com.msp360.at.wizards.steps.NbfFilesWizardScheduleOptionsPageSteps;
import com.msp360.at.wizards.steps.NbfFilesWizardWelcomePageSteps;
import com.msp360.at.wizards.steps.NbfFilesWizardWhatToBackUpPageSteps;
import com.msp360.at.wizards.steps.NbfFilesWizardWhereToBackUpPageSteps;
import com.msp360.at.wizards.steps.RemoteManagementPageSteps;
import com.msp360.at.wizards.steps.RemoteManagementSidePanelPageSteps;
import com.msp360.at.wizards.steps.ScheduleTypeDaily;
import com.msp360.at.wizards.steps.ScheduleTypeMonthly;
import com.msp360.at.wizards.steps.StorageClasses;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public abstract class BaseClass {

    //protected static WebDriver driver;
    protected static WebDriverWait wait;
    public CapabilityFactory capabilityFactory = new CapabilityFactory();
    public EnvironmentResourcesGenerator environmentResourcesGenerator = new EnvironmentResourcesGenerator();
    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    protected static final String CONTAINER_URL = "http://172.17.0.2:4444/wd/hub";
    //http://172.17.0.2:4444/wd/hub, http://localhost:4444/wd/hub
    protected static final String URL = "https://mspbackups.com/";
    protected static final String URL_STORAGE_ACCOUNT = "" + URL + "AP/EditAccount.aspx";
    protected static final String URL_NEW_RM_PAGE = "" + URL + "AP/Computers";
    protected static final String EMAIL = "oleg.k@msp360.com";
    protected static final String PASSWORD = "hSX89Je2e3!";
    protected static final String COMPUTER_NAME = "DONT-TOUCH";
    protected static final String AZURE_STACK = "kozyrev-sel (kozyrev-sel)";
    protected static final String GOOGLE = "kozyrev_sel_google (kozyrev_sel)";
    protected static final String S3_IMMUT_ON = "kozyrevtestprod_sel (kozyrevtestprodsel)";
    protected static final String S3_IMMUT_OFF = "kozyrevtestprod_sel2 (kozyrevtestprodsel2)";
    protected static final String WASABI_IMMUT_ON = "kozyrev_sel_immut (kozyrevselimmut)";
    protected static final String WASABI_IMMUT_OFF = "kozyrev_sel (kozyrev_sel)";
    protected static final String B2_IMMUT_ON = "kozyrev_selen_immut_on (kozyrev-selen-immut-on)";
    protected static final String B2_IMMUT_OFF = "kozyrev-selen-immut-off (kozyrev-selen-immut-off)";
    protected static final String S3_COMPATIBLE_SYNTHETIC_OFF = "!?";
    protected static final String S3_WITHOUT_IN_CLOUD_OBJECT_COPYING_SUPPORT = "!!?"; //FFI SUPPORT
    protected static final String S3_COMPATIBLE_SYNTHETIC_ON = "?";
    protected static final String MINIO_SYNTHETIC_ON = "??";
    protected static final String MINIO_SYNTHETIC_OFF = "!??";
    //Checkbox states
    protected static final String CHECKBOX_DOESNT_EXIST = "Checkbox doesn't exist";
    protected static final String CHECKED_CHECKBOX = "rgb(45, 108, 162)";
    //"\"\uE914\"";
    protected static final String UNCHECKED_CHECKBOX = "rgb(227, 227, 227)";
    protected static final String DISABLED_CHECKBOX = "rgb(227, 227, 227)";
    protected static final String DISABLED_CHECKED_CHECKBOX = "rgb(129, 167, 199)";
    protected static final String ENABLED_RADIOBUTTON = "rgb(45, 108, 162)";
    protected static final String DISABLED_RADIOBUTTON = "rgb(227, 227, 227)";

    protected NbfFilesWizardPage nbfFilesWizardPage;
    protected LoginPageSteps loginPageSteps;
    protected RemoteManagementPageSteps remoteManagementPageSteps;
    protected NbfFilesWizardPageSteps nbfFilesWizardPageSteps;
    protected NbfFilesWizardWelcomePageSteps nbfFilesWizardWelcomePageSteps;
    protected NbfFilesWizardWhereToBackUpPageSteps nbfFilesWizardWhereToBackUpPageSteps;
    protected NbfFilesWizardAdvancedOptionsPageSteps nbfFilesWizardAdvancedOptionsPageSteps;
    protected NbfFilesWizardWhatToBackUpPageSteps nbfFilesWizardWhatToBackUpPageSteps;
    protected NbfFilesWizardCompressionAndEncryptionPageSteps nbfFilesWizardCompressionAndEncryptionPageSteps;
    protected NbfFilesWizardAdvancedFilterPageSteps nbfFilesWizardAdvancedFilterPageSteps;
    protected NbfFilesWizardBackupConsistencyCheckPageSteps nbfFilesWizardBackupConsistencyCheckPageSteps;
    protected NbfFilesWizardScheduleOptionsPageSteps nbfFilesWizardPageScheduleOptionsPageSteps;
    protected RemoteManagementSidePanelPageSteps remoteManagementSidePanelPageSteps;

    /*    public static WebDriver getDriver() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return driver;
    }*/

    public static WebDriver getDriver() {
        //Get driver from ThreadLocalMap
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(35));
        return driver.get();
    }


    @BeforeClass
    static void beforeAll() {
        new EnvironmentResourcesGenerator().createProperties();
    }

    @BeforeMethod
    @Parameters(value = {"browser"})
    //Exception for RemoteWebDriver
    public void setUp(String browser) throws MalformedURLException {
        //Log in
        if (browser.equals("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
        } else {
            WebDriverManager.chromedriver().setup();
        }
        //After updating Chrome, the following strings were added
        //ChromeOptions chromeOptions = new ChromeOptions();

        driver.set(new RemoteWebDriver(new URL(CONTAINER_URL),
            capabilityFactory.getCapabilities(browser)));

        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


        //Access for clipboardReadWrite Chrome. What To Back Up step. LOCAL
        /*String[] array = {"clipboardReadWrite"};
        ((ChromeDriver) driver.get()).executeCdpCommand("Browser.grantPermissions",
        Map.of("permissions", asList(array)));*/

        //Access for clipboardReadWrite Chrome. What To Back Up step. DOCKER.
        /*JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript(
        "let copyText = document.querySelector(\"[name='q']\");\n" +
        "copyText.select();\n" +
        "document.execCommand(\"copy\");");*/


        loginPageSteps = new LoginPageSteps(driver.get());
        remoteManagementPageSteps = new RemoteManagementPageSteps(driver.get());
        nbfFilesWizardPageSteps = new NbfFilesWizardPageSteps(driver.get());
        nbfFilesWizardWelcomePageSteps = new NbfFilesWizardWelcomePageSteps(driver.get());
        nbfFilesWizardWhereToBackUpPageSteps = new NbfFilesWizardWhereToBackUpPageSteps(driver.get());
        nbfFilesWizardAdvancedOptionsPageSteps = new NbfFilesWizardAdvancedOptionsPageSteps(driver.get());
        nbfFilesWizardWhatToBackUpPageSteps = new NbfFilesWizardWhatToBackUpPageSteps(driver.get());
        nbfFilesWizardCompressionAndEncryptionPageSteps =
                new NbfFilesWizardCompressionAndEncryptionPageSteps(driver.get());
        nbfFilesWizardAdvancedFilterPageSteps = new NbfFilesWizardAdvancedFilterPageSteps(driver.get());
        nbfFilesWizardBackupConsistencyCheckPageSteps = new NbfFilesWizardBackupConsistencyCheckPageSteps(driver.get());
        nbfFilesWizardPageScheduleOptionsPageSteps = new NbfFilesWizardScheduleOptionsPageSteps(driver.get());
        remoteManagementSidePanelPageSteps = new RemoteManagementSidePanelPageSteps(driver.get());

        nbfFilesWizardPage = new NbfFilesWizardPage(driver.get());
        loginPageSteps.openLoginPage();
        loginPageSteps.loginProvider(EMAIL, PASSWORD);
    }

    @DataProvider
    public Object[][] nbfFiles() {
        return new Object[][]{
                //Computer Name, Plan name, destination, storage class
                //{"DONT-TOUCH", "Standard", "kozyrevtestprod_sel2 (kozyrevtestprodsel2)", "Standard"},
                /*{"DONT-TOUCH", "Intelligent-Tiering", "kozyrevtestprod_sel2 (kozyrevtestprodsel2)",
                        "Intelligent-Tiering"},
                {"DONT-TOUCH", "Standard-IA", "kozyrevtestprod_sel2 (kozyrevtestprodsel2)",
                        "Standard-IA"},
                {"DONT-TOUCH", "One Zone-IA", "kozyrevtestprod_sel2 (kozyrevtestprodsel2)",
                        "One Zone-IA"},
                {"DONT-TOUCH", "Glacier Flexible Retrieval", "kozyrevtestprod_sel2 (kozyrevtestprodsel2)",
                        "Glacier Flexible Retrieval"},
                {"DONT-TOUCH", "Glacier Deep Archive", "kozyrevtestprod_sel2 (kozyrevtestprodsel2)",
                        "Glacier Deep Archive"},
                {"DONT-TOUCH", "Glacier Instant Retrieval", "kozyrevtestprod_sel2 (kozyrevtestprodsel2)",
                        "Glacier Instant Retrieval"},*/
        };
    }

    @DataProvider
    public Object[][] nbfWelcomePage() {
        return new Object[][]{
            {COMPUTER_NAME, "Welcome to Files Backup Plan Wizard", "WelcomePage_plan1", S3_IMMUT_OFF},
            {COMPUTER_NAME, "Welcome to Files Backup Plan Wizard", "WelcomePage_plan2", S3_IMMUT_OFF},
            {COMPUTER_NAME, "Welcome to Files Backup Plan Wizard", "WelcomePage_plan3", S3_IMMUT_OFF},
            /*{COMPUTER_NAME, "Welcome to Files Backup Plan Wizard",
            "longnamelongnamelongnamelongnamelongnamelongnamelongname"
            + "longnamelongnamelongnamelongnamelongnamelongnamelongname"
            + "longnamelongnamelongnamelongnamelongnamelongnamelongname"
            + "longnamelongnamelongnamelongnamelongnamelongnamelongname"
            + "longnamelongnamelongnamelongnamelongnamelongnamelongname"
            + "longnamelongnamelongnamelongnamelongnamelongnamelongname"
            + "longnamelongnamelongnamelongnamelongnamelongnamelongname"
            + "longnamelongnamelongnamelongnamelongnamelongnamelongname"
            + "longnamelongnamelongnamelongnamelongnamelongnamelongname"
            + "longnamelongnamelongnamelongnamelongnamelongnamelongname"
            + "longnamelongnamelongnamelongnamelongnamelongnamelongname"
            + "longnamelongnamelongnamelongnamelongnamelongnamelongname"
            + "longnamelongnamelongnamelongnamelongnamelongnamelongname"
            + "longnamelongnamelongnamelongnamelongnamelongnamelongname"
            + "longnamelongnamelongnamelongnamelongnamelongnamelongname"
            + "longnamelongnamelongnamelongnamelongnamelongnamelongname"
            + "longnamelongnamelongnamelongnamelongnamelongnamelongname"
            + "longnamelongnamelongnamelongnamelongnamelongname",
            "kozyrevtestprod_sel2 (kozyrevtestprodsel2)"},*/
        };
    }

    @DataProvider
    public Object[][] nbfBackupStorage() {
        return new Object[][]{
                {COMPUTER_NAME, "BackupStorage_plan1", S3_IMMUT_ON},
                {COMPUTER_NAME, "BackupStorage_plan2", WASABI_IMMUT_ON},
                {COMPUTER_NAME, "BackupStorage_plan3", B2_IMMUT_OFF},
                {COMPUTER_NAME, "BackupStorage_plan4", AZURE_STACK},
                {COMPUTER_NAME, "BackupStorage_plan5", GOOGLE},
        };

    }

    @DataProvider
    public Object[][] nbfAdvancedOptions() {
        return new Object[][]{
            {COMPUTER_NAME, "AdvancedOptionsClasses_plan1", S3_IMMUT_OFF, StorageClasses.S3_INTELLIGENT_TIERING},
            {COMPUTER_NAME, "AdvancedOptionsClasses_plan2", S3_IMMUT_OFF, StorageClasses.S3_GLACIER_FLEXIBLE_RETRIEVAL},
            {COMPUTER_NAME, "AdvancedOptionsClasses_plan3", S3_IMMUT_OFF, StorageClasses.S3_GLACIER_DEEP_ARCHIVE},
            {COMPUTER_NAME, "AdvancedOptionsClasses_plan4", AZURE_STACK, StorageClasses.AZURE_HOT},
            {COMPUTER_NAME, "AdvancedOptionsClasses_plan5", AZURE_STACK, StorageClasses.AZURE_ARCHIVE},
            {COMPUTER_NAME, "AdvancedOptionsWithoutClasses_plan1", WASABI_IMMUT_OFF, StorageClasses.NO_CLASSES},
            {COMPUTER_NAME, "AdvancedOptionsWithoutClasses_plan2", B2_IMMUT_ON, StorageClasses.NO_CLASSES},
            {COMPUTER_NAME, "AdvancedOptionsWithoutClasses_plan3", GOOGLE, StorageClasses.NO_CLASSES},
        };
    }


    @DataProvider
    public Object[][] nbfWhatToBackUp() {
        return new Object[][]{
                //{COMPUTER_NAME, S3_STORAGE_ACCOUNT_IMMUT_OFF},
                {COMPUTER_NAME, "WhatToBackUp_plan1", WASABI_IMMUT_OFF},
                {COMPUTER_NAME, "WhatToBackUp_plan2", B2_IMMUT_ON},
        };

    }

    @DataProvider
    public Object[][] nbfAdvancedFilter() {
        return new Object[][]{
                {COMPUTER_NAME, "Advanced_Filter_plan1", S3_IMMUT_OFF},
                {COMPUTER_NAME, "Advanced_Filter_plan2", WASABI_IMMUT_ON},
        };


    }

    @DataProvider
    public Object[][] nbfCompressionAndEncryption() {
        return new Object[][]{
                {COMPUTER_NAME, "CompressionAndEncryption_plan1", S3_IMMUT_ON},
                {COMPUTER_NAME, "CompressionAndEncryption_plan2", WASABI_IMMUT_OFF},
                /*{COMPUTER_NAME, B2_STORAGE_ACCOUNT_IMMUT_OFF},
                {COMPUTER_NAME, AZURE_STACK_ACCOUNT},*/
        };
    }

    @DataProvider
    public Object[][] nbfBackupConsistencyCheck() {
        return new Object[][]{
                {COMPUTER_NAME, "ConsistencyCheck_plan1", S3_IMMUT_ON},
                {COMPUTER_NAME, "ConsistencyCheck_plan2", WASABI_IMMUT_OFF},
        };
    }

    @DataProvider
    public Object[][] nbfScheduleOptionsFfiEnabledTypeMonthly() {
        return new Object[][]{
                {COMPUTER_NAME, "FFI_Schedule_plan1", S3_IMMUT_OFF, StorageClasses.S3_STANDARD_IA,
                    ScheduleTypeMonthly.TYPE_MONTHLY, ScheduleTypeMonthly.TYPE_MONTHLY_OCCURRENCE_SECOND,
                    ScheduleTypeMonthly.TYPE_MONTHLY_FRIDAY},
                {COMPUTER_NAME, "FFI_Schedule_plan2", S3_IMMUT_OFF, StorageClasses.S3_GLACIER_INSTANT_RETRIEVAL,
                    ScheduleTypeMonthly.TYPE_MONTHLY, ScheduleTypeMonthly.TYPE_MONTHLY_OCCURRENCE_SECOND,
                    ScheduleTypeMonthly.TYPE_MONTHLY_FRIDAY},
                {COMPUTER_NAME, "FFI_Schedule_plan3", S3_IMMUT_OFF, StorageClasses.S3_GLACIER_FLEXIBLE_RETRIEVAL,
                    ScheduleTypeMonthly.TYPE_MONTHLY, ScheduleTypeMonthly.TYPE_MONTHLY_OCCURRENCE_SECOND,
                    ScheduleTypeMonthly.TYPE_MONTHLY_FRIDAY},
                {COMPUTER_NAME, "FFI_Schedule_plan4", S3_IMMUT_OFF, StorageClasses.S3_GLACIER_DEEP_ARCHIVE,
                    ScheduleTypeMonthly.TYPE_MONTHLY, ScheduleTypeMonthly.TYPE_MONTHLY_OCCURRENCE_SECOND,
                    ScheduleTypeMonthly.TYPE_MONTHLY_FRIDAY},
                {COMPUTER_NAME, "FFI_Schedule_plan5", AZURE_STACK, StorageClasses.AZURE_HOT,
                    ScheduleTypeMonthly.TYPE_MONTHLY, ScheduleTypeMonthly.TYPE_MONTHLY_OCCURRENCE_PENULTIMATE,
                    ScheduleTypeMonthly.TYPE_MONTHLY_WEDNESDAY},
                {COMPUTER_NAME, "FFI_Schedule_plan6", AZURE_STACK, StorageClasses.AZURE_ARCHIVE,
                    ScheduleTypeMonthly.TYPE_MONTHLY, ScheduleTypeMonthly.TYPE_MONTHLY_OCCURRENCE_SECOND,
                    ScheduleTypeMonthly.TYPE_MONTHLY_FRIDAY},
                    //S3 compatible (except storage providers without in-cloud object copying support) ???
        };
    }

    @DataProvider
    public Object[][] nbfScheduleOptionsFfiEnabledTypeMonthlyDayOfMonth() {
        return new Object[][]{
                {COMPUTER_NAME, "FFI_Schedule_plan7", AZURE_STACK, StorageClasses.AZURE_COOL,
                    ScheduleTypeMonthly.TYPE_MONTHLY, ScheduleTypeMonthly.TYPE_MONTHLY_OCCURRENCE_DAY_OF_MONTH,
                    ScheduleTypeMonthly.TYPE_MONTHLY_DAY_OF_MONTH_1},
                {COMPUTER_NAME, "FFI_Schedule_plan8", S3_IMMUT_ON, StorageClasses.S3_STANDARD,
                    ScheduleTypeMonthly.TYPE_MONTHLY, ScheduleTypeMonthly.TYPE_MONTHLY_OCCURRENCE_DAY_OF_MONTH,
                    ScheduleTypeMonthly.TYPE_MONTHLY_DAY_OF_MONTH_31}
        };
    }

    @DataProvider
    public Object[][] nbfScheduleOptionsFfiEnabledTypeDaily() {
        return new Object[][]{
                {COMPUTER_NAME, "FFI_ScheduleTypeDaily_plan1", AZURE_STACK,
                    StorageClasses.AZURE_COOL, ScheduleTypeDaily.TYPE_DAILY, ScheduleTypeDaily.TYPE_DAILY_TUESDAY
                },
                {COMPUTER_NAME, "FFI_ScheduleTypeDaily_plan2", S3_IMMUT_ON,
                    StorageClasses.S3_STANDARD_IA, ScheduleTypeDaily.TYPE_DAILY,
                    ScheduleTypeDaily.TYPE_DAILY_TUESDAY
                },
                {COMPUTER_NAME, "FFI_ScheduleTypeDaily_plan3", WASABI_IMMUT_ON,
                    StorageClasses.NO_CLASSES, ScheduleTypeDaily.TYPE_DAILY, ScheduleTypeDaily.TYPE_DAILY_TUESDAY
                }
        };
    }

    @DataProvider
    public Object[][] nbfScheduleOptionsRecurringEnabledTypeMonthly() {
        return new Object[][]{
                {COMPUTER_NAME, "Recurring_Schedule_plan1", S3_IMMUT_OFF, StorageClasses.S3_GLACIER_INSTANT_RETRIEVAL,
                    ScheduleTypeMonthly.TYPE_MONTHLY, ScheduleTypeMonthly.TYPE_MONTHLY_OCCURRENCE_SECOND,
                    ScheduleTypeMonthly.TYPE_MONTHLY_FRIDAY},
                {COMPUTER_NAME, "Recurring_Schedule_plan2", S3_IMMUT_OFF, StorageClasses.S3_GLACIER_FLEXIBLE_RETRIEVAL,
                    ScheduleTypeMonthly.TYPE_MONTHLY, ScheduleTypeMonthly.TYPE_MONTHLY_OCCURRENCE_SECOND,
                    ScheduleTypeMonthly.TYPE_MONTHLY_FRIDAY},
                    //S3 compatible (except storage providers without in-cloud object copying support) ???
        };
    }

    @DataProvider
    public Object[][] nbfScheduleOptionsRecurringEnabledTypeMonthlyDayOfMonth() {
        return new Object[][]{
                {COMPUTER_NAME, "Recurring_Schedule_plan3", AZURE_STACK, StorageClasses.AZURE_COOL,
                    ScheduleTypeMonthly.TYPE_MONTHLY, ScheduleTypeMonthly.TYPE_MONTHLY_OCCURRENCE_DAY_OF_MONTH,
                    ScheduleTypeMonthly.TYPE_MONTHLY_DAY_OF_MONTH_1},
                {COMPUTER_NAME, "Recurring_Schedule_plan4", S3_IMMUT_ON, StorageClasses.S3_STANDARD,
                    ScheduleTypeMonthly.TYPE_MONTHLY, ScheduleTypeMonthly.TYPE_MONTHLY_OCCURRENCE_DAY_OF_MONTH,
                    ScheduleTypeMonthly.TYPE_MONTHLY_DAY_OF_MONTH_31}
        };
    }

    @DataProvider
    public Object[][] nbfScheduleOptionsRecurringEnabledTypeDaily() {
        return new Object[][]{
                {COMPUTER_NAME, "Recurring_ScheduleTypeDaily_plan5", AZURE_STACK,
                    StorageClasses.AZURE_COOL, ScheduleTypeDaily.TYPE_DAILY, ScheduleTypeDaily.TYPE_DAILY_TUESDAY
                },
                {COMPUTER_NAME, "Recurring_ScheduleTypeDaily_plan6", S3_IMMUT_ON,
                    StorageClasses.S3_STANDARD_IA, ScheduleTypeDaily.TYPE_DAILY,
                    ScheduleTypeDaily.TYPE_DAILY_TUESDAY
                },
        };
    }


    @DataProvider
    public Object[][] nbfScheduleOptionsFullEnabledTypeMonthly() {
        return new Object[][]{
                {COMPUTER_NAME, "Full_Schedule_plan1", S3_IMMUT_OFF, StorageClasses.S3_GLACIER_INSTANT_RETRIEVAL,
                    ScheduleTypeMonthly.TYPE_MONTHLY, ScheduleTypeMonthly.TYPE_MONTHLY_OCCURRENCE_SECOND,
                    ScheduleTypeMonthly.TYPE_MONTHLY_FRIDAY},
                {COMPUTER_NAME, "Full_Schedule_plan2", S3_IMMUT_OFF, StorageClasses.S3_GLACIER_FLEXIBLE_RETRIEVAL,
                    ScheduleTypeMonthly.TYPE_MONTHLY, ScheduleTypeMonthly.TYPE_MONTHLY_OCCURRENCE_SECOND,
                    ScheduleTypeMonthly.TYPE_MONTHLY_FRIDAY},
                    //S3 compatible (except storage providers without in-cloud object copying support) ???
        };
    }

    @DataProvider
    public Object[][] nbfScheduleOptionsFullEnabledTypeMonthlyDayOfMonth() {
        return new Object[][]{
                {COMPUTER_NAME, "Full_Schedule_plan3", AZURE_STACK, StorageClasses.AZURE_COOL,
                    ScheduleTypeMonthly.TYPE_MONTHLY, ScheduleTypeMonthly.TYPE_MONTHLY_OCCURRENCE_DAY_OF_MONTH,
                    ScheduleTypeMonthly.TYPE_MONTHLY_DAY_OF_MONTH_1},
                {COMPUTER_NAME, "Full_Schedule_plan4", S3_IMMUT_ON, StorageClasses.S3_STANDARD,
                    ScheduleTypeMonthly.TYPE_MONTHLY, ScheduleTypeMonthly.TYPE_MONTHLY_OCCURRENCE_DAY_OF_MONTH,
                    ScheduleTypeMonthly.TYPE_MONTHLY_DAY_OF_MONTH_31}
        };
    }

    @DataProvider
    public Object[][] nbfScheduleOptionsFullEnabledTypeDaily() {
        return new Object[][]{
                {COMPUTER_NAME, "Full_ScheduleTypeDaily_plan5", AZURE_STACK,
                    StorageClasses.AZURE_COOL, ScheduleTypeDaily.TYPE_DAILY, ScheduleTypeDaily.TYPE_DAILY_TUESDAY
                },
                {COMPUTER_NAME, "Full_ScheduleTypeDaily_plan6", S3_IMMUT_ON,
                    StorageClasses.S3_STANDARD_IA, ScheduleTypeDaily.TYPE_DAILY,
                    ScheduleTypeDaily.TYPE_DAILY_TUESDAY
                },
        };
    }


    @AfterMethod
    //Log out
    public void tearDown() {

        driver.get().quit();
        /*public void tearDown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions
                .visibilityOfElementLocated((By.xpath("//button[@id='dropdownUser']"))));
        loginPageSteps.logOut();*/
    }

    @AfterSuite
    void afterClass() {
        new EnvironmentResourcesGenerator(driver.get().getCapabilities()).createProperties();
    }
}
