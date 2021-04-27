package ru.s3rgh.task.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperBase {

    WebDriver webDriver;

    public HelperBase(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected void submit() {
        webDriver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    protected void sendTextByLocator(String text, By locator) {
        if (text != null) {
            String existingText = webDriver.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                webDriver.findElement(locator).clear();
                webDriver.findElement(locator).sendKeys(text);
            }
        }
    }
}
