package ru.s3rgh.task.appmanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import ru.s3rgh.task.helpers.DeskHelper;
import ru.s3rgh.task.helpers.LoginHelper;
import ru.s3rgh.task.helpers.TabHelper;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private String browser;
    private String headless;
    public WebDriver webDriver;

    private DeskHelper deskHelper;
    private LoginHelper loginHelper;
    private TabHelper tabHelper;
    private final Properties properties;

    public ApplicationManager(String browser, String headless) {
        this.browser = browser;
        this.headless = headless;
        properties = new Properties();
    }

    public void initialize() throws IOException {

        if (webDriver != null) {
            return;
        }

        properties.load(new FileReader("src/test/resources/local.properties"));

        if (browser == null) {
            browser = properties.getProperty("browser");
        }

        if (headless == null) {
            headless = properties.getProperty("headless");
        }

        if (browser.equals(BrowserType.CHROME)) {
            if (headless.equals("on")) {
                initHeadlessChromeDriver();
            } else {
                initChromeDriver();
            }
        } else if (browser.equals(BrowserType.FIREFOX)) {
            if (headless.equals("on")) {
                initHeadlessFirefoxDriver();
            } else {
                initFirefoxDriver();
            }
        }

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        deskHelper = new DeskHelper(webDriver);
        loginHelper = new LoginHelper(webDriver);
        tabHelper = new TabHelper(webDriver);
    }

    private void initHeadlessFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("--window-size=1366x768");
        options.addArguments("--disable-notifications");
        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver(options);
    }

    private void initFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-notifications");
        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver(options);
    }

    private void initHeadlessChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("--window-size=1366x768");
        options.addArguments("--disable-notifications");
        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver(options);
    }

    private void initChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver(options);
    }

    public void stop() {
        webDriver.quit();
        webDriver = null;
    }

    public void open() throws IOException {
        if (webDriver == null) {
            initialize();
        }
        webDriver.get("https://yandex.ru/");
    }

    public DeskHelper getDesk() {
        return deskHelper;
    }

    public LoginHelper getLogin() {
        return loginHelper;
    }

    public TabHelper getTab() {
        return tabHelper;
    }
}