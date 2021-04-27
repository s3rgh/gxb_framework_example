package ru.s3rgh.task.appmanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import ru.s3rgh.task.helpers.DeskHelper;
import ru.s3rgh.task.helpers.LoginHelper;
import ru.s3rgh.task.helpers.TabHelper;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    public WebDriver webDriver;

    private DeskHelper deskHelper;
    private LoginHelper loginHelper;
    private TabHelper tabHelper;


    public void initialize() {

        if (webDriver != null) {
            return;
        }

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
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        deskHelper = new DeskHelper(webDriver);
        loginHelper = new LoginHelper(webDriver);
        tabHelper = new TabHelper(webDriver);
    }

    public void stop() {
        webDriver.quit();
    }

    public void open() {
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
