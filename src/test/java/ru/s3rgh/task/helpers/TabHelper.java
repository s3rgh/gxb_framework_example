package ru.s3rgh.task.helpers;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class TabHelper extends HelperBase {

    public TabHelper(WebDriver webDriver) {
        super(webDriver);
    }

    public void toNumber(int tabNumber) {
        List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(tabNumber - 1));
    }
}
