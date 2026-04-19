package org.example;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DetectiveGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Set<String> foundClues = new HashSet<>();

        Set<String> dbClues = new HashSet<>();

        dbClues.add("Опечаток пальца на двери");
        dbClues.add("Запись с камеры видеонаблюдения");
        dbClues.add("Волосы на кресле");
        dbClues.add("След обуви");

        int choice;

        System.out.println("Добро пожаловать в детективную игру!");

        do{
            System.out.println("\nВыберите действие:");
            System.out.println("1 - Добавить улику");
            System.out.println("2 - Проверить наличие улики");
            System.out.println("3 - Удалить улику");
            System.out.println("4 - Сравнить с базой данных");
            System.out.println("5 - Показать все найденные улики");
            System.out.println("6 - Выход");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Введите название новой улики: ");
                    String newClue = sc.nextLine();
                    if (foundClues.add(newClue)) {
                        System.out.println("Улика \"" + newClue + "\"добавлена.");
                    } else {
                        System.out.println("Такая улика уже существует");
                    }
                    break;

                case 2:
                    System.out.println("Введите название улики для проверки: ");
                    String checkClue = sc.nextLine();
                    if (foundClues.contains(checkClue)) {
                        System.out.println("Улика найдена");
                    } else {
                        System.out.println("Улика не найдена");
                    }
                    break;

                case 3:
                    System.out.println("Удаление улики: ");
                    String removeClue = sc.nextLine();
                    if (foundClues.remove(removeClue)) {
                        System.out.println("Улика \"" + removeClue + "\" удалена.");
                    } else {
                        System.out.println("Такой улики нет");
                    }
                    break;

                case 4:
                    System.out.println("Совпадения с базой данных");

                    boolean hasMatches = false;
                    for (String clue : foundClues) {
                        for(String dbClue : dbClues) {
                            if(dbClue.equalsIgnoreCase(clue)){
                                System.out.println("- " + clue);
                                hasMatches = true;
                            }
                        }
                    }

                    if (!hasMatches) {
                        System.out.println("Совпадений не найдено");
                    }

                    break;

                case 5:
                    if (foundClues.isEmpty()) {
                        System.out.println("Найденных улик пока нет");
                    } else {
                        System.out.println("Найденные улики:");
                        for (String clue : foundClues) {
                            System.out.println("- " + clue);
                        }
                    }
                    break;

                case 6:
                    System.out.println("Программа завершена");
                    break;
            }
        }while (choice!=6);

        sc.close();
    }
}
