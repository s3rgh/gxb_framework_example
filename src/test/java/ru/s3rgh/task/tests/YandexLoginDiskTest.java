package ru.s3rgh.task.tests;

import dto.User;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class YandexLoginDiskTest extends TestBase {

    @Test
    public void yandexLoginDiskTest() {
        app.open();
        app.getDesk().choose("Диск");
        app.getTab().toNumber(2);
        app.getLogin().user(new User().withLogin("s3rgh").withPassword("321MNBasd"));
        assertThat(app.webDriver.findElement(By.xpath("//img[@class='user-pic__image']")).isEnabled()).isEqualTo(true);
    }
}
