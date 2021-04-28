package ru.s3rgh.task.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import dto.User;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class YandexLoginDiskTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validUsersFromCsv() throws IOException {
        List<Object[]> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/users.csv"))) {
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split(";");
                list.add(new Object[]{new User().withLogin(split[0]).withPassword(split[1])});
                line = reader.readLine();
            }
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> validUsersFromXml() throws IOException {
        StringBuilder xml;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/users.xml"))) {
            xml = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                xml.append(line);
                line = reader.readLine();
            }
        }
        XStream xStream = new XStream();
        xStream.processAnnotations(User.class);
        List<User> users = (List<User>) xStream.fromXML(xml.toString());
        return users.stream().map((u) -> new Object[]{u}).collect(Collectors.toList()).iterator();
    }

    @DataProvider
    public Iterator<Object[]> validUsersFromJson() throws IOException {
        StringBuilder json;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/users.json"))) {
            json = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                json.append(line);
                line = reader.readLine();
            }
        }
        Gson gson = new Gson();
        List<User> users = gson.fromJson(json.toString(), new TypeToken<List<User>>(){}.getType());
        return users.stream().map((u) -> new Object[]{u}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validUsersFromXml")
    public void yandexLoginDiskTest(User user) {
        app.open();
        app.getDesk().choose("Диск");
        app.getTab().toNumber(2);
        app.getLogin().user(user);
        assertThat(app.webDriver.findElement(By.xpath("//img[@class='user-pic__image']")).isEnabled()).isEqualTo(true);
    }
}