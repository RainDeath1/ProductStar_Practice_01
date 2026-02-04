package Practice02;
import java.util.Scanner;

public class TimeOfDay {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите время");

        String input = in.nextLine();
        try{
            int hour;

            if(input.matches("\\d{1,2}:\\d{2}")){
                String[] part = input.split(":");
                hour = Integer.parseInt(part[0]);
                int minutes = Integer.parseInt(part[1]);
                if (minutes < 0 || minutes>59){
                    throw new NumberFormatException();
                }
            }
            else {
                hour = Integer.parseInt(input);
            }
            if (hour < 0 || hour > 23){
                System.out.println("Часы должны быть от 0 до 23");
                return;
            }
            switch (hour){
                case 6,7,8,9,10,11 -> System.out.println("Утро");
                case 12,13,14,15,16,17 -> System.out.println("День");
                case 18,19,20,21 -> System.out.println("Вечер");
                case 22,23,0,1,2,3,4,5 -> System.out.println("Ночь");
            }
        } catch (NumberFormatException e){
            System.out.println("Введите время в формате HH или HH:MM");
        }
        in.close();
    }
}
