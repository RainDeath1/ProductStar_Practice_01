package org.example;

import org.example.cli.Menu;
import org.example.database.DatabaseInitializer;

public class Main {

    public static void main(String[] args) {

        DatabaseInitializer.initialize();

        Menu menu = new Menu();
        menu.start();
    }
}