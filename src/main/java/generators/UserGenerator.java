package generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import dto.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserGenerator {

    @Parameter(names = {"--count", "-c"}, description = "Count of users")
    int count;

    @Parameter(names = {"--file", "-f"}, description = "Destination of file")
    String file;

    @Parameter(names = {"--data", "-d"}, description = "Data format")
    String format;

    public static void main(String[] args) throws IOException {
        UserGenerator generator = new UserGenerator();
        JCommander jCommander = JCommander.newBuilder().addObject(generator).build();
        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            jCommander.usage();
            return;
        }

        generator.run();
    }

    private void run() throws IOException {
        List<User> users = generateUsers(count);
        switch (format) {
            case "csv":
                saveAsCsv(users, new File(file));
                break;
            case "xml":
                saveAsXml(users, new File(file));
                break;
            case "json":
                saveAsJson(users, new File(file));
                break;
            default:
                System.out.println("Wrong data format!!!");
                break;
        }
    }

    private void saveAsJson(List<User> users, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(users);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXml(List<User> users, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(User.class);
        String xml = xStream.toXML(users);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsCsv(List<User> users, File file) throws IOException {
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (User user : users) {
                fileWriter.write(String.format("%s;%s\n", user.getLogin(), user.getPassword()));
            }
        }
    }

    private List<User> generateUsers(int count) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            users.add(new User().withLogin(String.format("user%s", i)).withPassword(String.format("password%s", i)));
        }
        return users;
    }
}
