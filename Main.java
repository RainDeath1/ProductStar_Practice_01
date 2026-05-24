package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserManager userManager = new UserManager();

        while (true){
            System.out.println("\nВыберите действие:");
            System.out.println("1 - Добавить пользователя");
            System.out.println("2 - Показать список пользователей");
            System.out.println("3 - Выход");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice){

                case 1:
                    System.out.println("Введите имя пользователя: ");
                    String name = sc.nextLine();

                    System.out.println("Введите город пользователя: ");
                    String city = sc.nextLine();

                    User user = new User(name, city);

                    userManager.addUser(user);

                    System.out.println("Пользователь сохранен");
                    break;

                case 2:
                    userManager.showUsers();
                    break;

                case 3:
                    System.out.println("Выход из программы");
                    return;

                default:
                    System.out.println("Неверный выбор");
            }
        }
    }
}
