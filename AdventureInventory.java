package org.example;

import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Map;

public class AdventureInventory {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int choice;

        LinkedHashMap<String, Integer> inventory = new LinkedHashMap<>();

        System.out.println("Добро пожаловать в инвентарь приключенца");

        do{
            System.out.println("\nВыберите действие:");
            System.out.println("1 - Добавить новый предмет");
            System.out.println("2 - Изменить количество предметов");
            System.out.println("3 - Удалить предмет");
            System.out.println("4 - Найти предмет по названию");
            System.out.println("5 - Показать весь инвентарь");
            System.out.println("6 - Выход");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Введите название предмета: ");
                    String itemName = scanner.nextLine();
                    if (itemName.isEmpty()) {
                        System.out.println("Название предмета не может быть пустым");
                        break;
                    }

                    System.out.println("Введите количество: ");
                    int quality = scanner.nextInt();
                    scanner.nextLine();

                    if(quality<=0){
                        System.out.println("Кол-во должно быть больше 0");
                        break;
                    }

                    if(inventory.containsKey(itemName)) {
                        System.out.println("Предмет ужесуществует");
                        System.out.println("Введите новое количество для обновления: ");
                        int newQuality = scanner.nextInt();
                        scanner.nextLine();

                        if (newQuality > 0) {
                            inventory.put(itemName, newQuality);
                            System.out.println("Количество для \"" + itemName + "\" обновлено.");
                        } else {
                            System.out.println("Количество должно быть больше 0.");
                        }
                    } else {
                        inventory.put(itemName, quality);
                        System.out.println("Предмет \"" + itemName + "\" успешно добавлен");
                    }
                    break;

                case 2:
                    System.out.println("Введите название предмета: ");
                    String updateItem = scanner.nextLine();
                    if(!inventory.containsKey(updateItem)){
                        System.out.println("Предмет не найден");
                        break;
                    }

                    System.out.println("Введите новое количество");
                    int updateQuality = scanner.nextInt();
                    scanner.nextLine();

                    if(updateQuality<0){
                        System.out.println("Количества " + updateItem + " не может быть отрицательным");
                    }else if(updateQuality == 0) {
                        inventory.remove(updateItem);
                        System.out.println("Предмет \"" + updateItem + "\" был удален");
                    }else{
                        inventory.put(updateItem, updateQuality);
                        System.out.println("Количество для \"" + updateItem + "\" обновлено");
                    }

                    break;

                case 3:
                    System.out.println("Введите предмет, чтобы удалить: ");
                    String removeItem = scanner.nextLine();
                    if (inventory.remove(removeItem)!= null) {
                        System.out.println("Предмет \"" + removeItem + "\" удален.");
                    } else {
                        System.out.println("Предмета нет в инвентаре");
                    }
                    break;

                case 4:
                    System.out.println("Введите название предмета: ");
                    String searchItem = scanner.nextLine();
                    if (inventory.containsKey(searchItem)) {
                        System.out.println("Количество \"" + searchItem +
                                           "\": " + inventory.get(searchItem));
                    } else {
                        System.out.println("Предмета нет в инвентаре");
                    }
                    break;

                case 5:
                    if (inventory.isEmpty()) {
                        System.out.println("Инвентарь пуст");
                    } else {
                        System.out.println("Предметы в инвентаре:");
                        for (Map.Entry<String,Integer> entry: inventory.entrySet()) {
                            System.out.println(entry.getKey()+ " - " + entry.getValue());
                        }
                    }
                    break;

                case 6:
                    System.out.println("Инвентарь закрыт");
                    break;

                default:
                    System.out.println("Неверный пункт меню!");
            }
        }while (choice!=6);

        scanner.close();
    }
}
