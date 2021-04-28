package ru.s3rgh.task.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.*;
import ru.s3rgh.task.appmanager.ApplicationManager;

import java.io.IOException;

public class TestBase {

    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser"), System.getProperty("headless"));

    @BeforeTest
    public void setUp() throws IOException {
        app.initialize();
    }

    @AfterMethod
    public void tearDown() throws IOException {
        app.stop();
    }
}
