package org.example;

import  java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private final List<User> users = new ArrayList<>();

    private final Path path = Paths.get("users.txt");

    public UserManager() {
        loadFromFile();
    }

    public void addUser(User user) {
        users.add(user);
        saveToFile(user);
    }

    public void showUsers() {
        if (users.isEmpty()) {
            System.out.println("Список пользователей пуст.");
        }
        System.out.println("\nСписок пользователей:");

        for (User user : users) {
            System.out.println(user);
        }
    }

    private void saveToFile(User user) {

        String data = user.getName() + "," + user.getCity() + "\n";
        try {
            Files.write(
                    path,
                    data.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile(){
        if(!Files.exists(path)){
            return;
        }
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines){
                String[] parts = line.split(",");

                if (parts.length == 2){
                    String name  = parts[0];
                    String city = parts[1];
                    users.add(new User(name, city));
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

