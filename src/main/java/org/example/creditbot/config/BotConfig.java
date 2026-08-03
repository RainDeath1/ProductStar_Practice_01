package org.example.creditbot.config;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BotConfig {

    private final String botName;
    private final String botToken;

    private final String managerLogin;
    private final String managerPassword;


    public BotConfig() {

        Properties properties = new Properties();

        try (InputStream inputStream =
                     getClass()
                             .getClassLoader()
                             .getResourceAsStream("bot.properties")) {


            if (inputStream == null) {
                throw new RuntimeException(
                        "Файл bot.properties не найден"
                );
            }


            properties.load(inputStream);


        } catch (IOException e) {

            throw new RuntimeException(
                    "Ошибка чтения bot.properties",
                    e
            );
        }


        Dotenv dotenv = Dotenv.load();


        botName =
                getProperty(
                        properties,
                        "bot.name"
                );


        botToken =
                getEnv(
                        dotenv,
                        "BOT_TOKEN"
                );


        managerLogin =
                getEnv(
                        dotenv,
                        "MANAGER_LOGIN"
                );


        managerPassword =
                getEnv(
                        dotenv,
                        "MANAGER_PASSWORD"
                );
    }



    private String getProperty(
            Properties properties,
            String key
    ) {

        String value =
                properties.getProperty(key);


        if(value == null || value.isBlank()) {

            throw new RuntimeException(
                    "Не найден параметр: " + key
            );
        }

        return value;
    }



    private String getEnv(
            Dotenv dotenv,
            String key
    ) {

        String value =
                dotenv.get(key);


        if(value == null || value.isBlank()) {

            throw new RuntimeException(
                    "Не найдена переменная окружения: " + key
            );
        }


        return value;
    }



    public String getBotName() {
        return botName;
    }


    public String getBotToken() {
        return botToken;
    }


    public String getManagerLogin() {
        return managerLogin;
    }


    public String getManagerPassword() {
        return managerPassword;
    }
}