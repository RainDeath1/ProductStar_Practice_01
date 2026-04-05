package org.example;
import java.util.Scanner;

public class WithoutSpacesAndLetters {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите текст: ");
        String input = in.nextLine();

        String result = input.replaceAll("[\\p{L}\\s]+", "");
        System.out.println("Вывод: " + result);

        in.close();
    }
}
