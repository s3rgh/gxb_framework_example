package ru.s3rgh.task.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.s3rgh.task.appmanager.ApplicationManager;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser"), System.getProperty("headless"));

    @BeforeMethod
    public void setUp() throws IOException {
        app.initialize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws IOException {
        app.stop();
    }

    @BeforeMethod
    public void loggingStart(Method m, Object[] p) {
        logger.info("Start test " + m.getName() + "with parameters " + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void loggingStop(Method m, Object[] p) {
        logger.info("Stop test " + m.getName() + "with parameters " + Arrays.asList(p));
    }
}
