package ru.s3rgh.task.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeskHelper extends HelperBase {

    public DeskHelper(WebDriver webDriver) {
        super(webDriver);
    }

    public void choose(String str) {
        webDriver.findElement(By.xpath("//div[@class='desk-notif-card__login-new-items']/descendant::*[contains(text(), '" + str + "')]")).click();
    }
}
