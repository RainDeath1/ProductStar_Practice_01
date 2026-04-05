package org.example;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class UsernameFormatter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите имя пользователя: ");
        String input = in.nextLine();

        if(input.length()<3 || input.length()>20){
            System.out.println("Ошибка: имя пользователя должно содержать " +
                    "от 3 до 20 символов");
            return;
        }
        if(!input.matches("^[a-zA-Z].*")){
            System.out.println("Ошибка: имя пользователя должно начинаться с бкувы");
            return;
        }

        Pattern pattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]{2,19}$");
        Matcher matcher = pattern.matcher(input);

        if(!matcher.matches()) {
            System.out.println("Ошибка: допустимы только буквы, цифры и знак подчеркивания");
            return;
        }

        String formatted = input.toLowerCase().replaceAll("_+","_");
        System.out.println("DEBUG: длина строки = " + input.length());
        System.out.println("Result:" + formatted);

        in.close();
    }
}
