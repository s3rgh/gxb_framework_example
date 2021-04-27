package ru.s3rgh.task.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.s3rgh.task.appmanager.ApplicationManager;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();

    @BeforeMethod
    public void setUp() {
        app.initialize();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }
}
