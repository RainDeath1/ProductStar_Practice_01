package org.example;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PhoneNumber {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите номер телефона");
        String phone_number = in.nextLine();

        String regex = "^\\+\\d{1,3}([ -]?\\d{1,3})([ -]?\\d{2,4}){2,3}$";

        boolean isValid = Pattern.matches(regex,phone_number);

        if(isValid){
            System.out.println("Номер введен корректно");
        }else {
            System.out.println("Номер введен некорректно");
        }
        in.close();
    }
}