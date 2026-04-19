package org.example;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class StoryGenerator{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random random = new Random();

        ArrayList<String> characters = new ArrayList<>();
        ArrayList<String> actions = new ArrayList<>();
        ArrayList<String> places = new ArrayList<>();

        ArrayList<String> stories = new ArrayList<>();

        characters.add("Гном");
        characters.add("Принцесса");
        characters.add("Робот");

        actions.add("танцует");
        actions.add("летает");
        actions.add("сражается");

        places.add("в лесу");
        places.add("на кухне");
        places.add("в космосе");

        System.out.println("Добро пожаловать в генератор случайных историй");
        System.out.println("У нас есть персонажи (" + characters.size() +"шт.), "
                +"(" + actions.size() +"шт.),"
                + "(" + places.size() +"шт.)" );

        int choice;

        do {
            System.out.println("\nВыберите действие:");
            System.out.println("1 - Добавить персонажа");
            System.out.println("2 - Добавить действие");
            System.out.println("3 - Добавить место");
            System.out.println("4 - Сгенерировать историю");
            System.out.println("5 - Посмотреть все истории");
            System.out.println("6 - Выход");

            choice = in.nextInt();
            in.nextLine();

            switch (choice){
                case 1:
                    System.out.println("Введите нового персонажа: ");
                    String character = in.nextLine();
                    characters.add(character);
                    System.out.println("Персонаж добавлен");
                    break;

                case 2:
                    System.out.println("Введите новое действие: ");
                    String action = in.nextLine();
                    actions.add(action);
                    System.out.println("Действие добавлено");
                    break;

                case 3:
                    System.out.println("Введите новое место: ");
                    String place = in.nextLine();
                    places.add(place);
                    System.out.println("Место добавлено");
                    break;

                case 4:
                    String randomCharacter = characters.get(random.nextInt(characters.size()));
                    String randomAction = actions.get(random.nextInt(actions.size()));
                    String randomPlace = places.get(random.nextInt(places.size()));

                    String story = randomCharacter + " " + randomAction + " " + randomPlace;

                    stories.add(story);

                    System.out.println("Сгенерированная история: " + story);
                    break;

                case 5:
                    if(stories.isEmpty()){
                        System.out.println("Историй пока нет");
                    } else{
                        System.out.println("Истории: ");
                        for (int i = 0; i < stories.size(); i++) {
                            System.out.println((i+1) + ") " + stories.get(i));
                        }
                    }
                    break;

                case 6:
                    System.out.println("Прграмма завершена.");
                    break;

                default:
                    System.out.println("Неверный пункт меню!");
            }

        }while(choice !=6);

        in.close();
    }
}
