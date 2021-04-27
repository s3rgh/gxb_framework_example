package ru.s3rgh.task.helpers;

import dto.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginHelper extends HelperBase {

    public LoginHelper(WebDriver webDriver) {
        super(webDriver);
    }

    public void user(User user) {
        sendTextByLocator(user.getLogin(), By.id("passp-field-login"));
        submit();
        sendTextByLocator(user.getPassword(), By.id("passp-field-passwd"));
        submit();
    }

}
