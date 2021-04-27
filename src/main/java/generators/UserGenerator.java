package generators;

import dto.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserGenerator {
    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<User> users = generateUsers(count);
        save(users, file);
    }

    private static void save(List<User> users, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        for (User user : users) {
            fileWriter.write(String.format("%s;%s\n", user.getLogin(), user.getPassword()));
        }
        fileWriter.close();
    }

    private static List<User> generateUsers(int count) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            users.add(new User().withLogin(String.format("user%s", i)).withPassword(String.format("password%s", i)));
        }
        return users;
    }
}
