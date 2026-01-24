import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //int choice = readChoice(in);

        //double value = readDouble(
        //               in, choice == 1 ?
        //               "Ведите температуру в °C"
        //               "Ведите температуру в °F"
        // )
        // double result;
        // if(choice == 1){
        //      result = Temperature.fahrenheitToCelsius(value);
        //      System.out.printf(Locale.US,"Температура в °C:%2f%n",result);
        //
        //
        // }
        //in.close();
        //
        double userCelsiusValue = readArgument(in, "Введите температуру в градусах \u00B0C");

        Fahrenheit userValue =new Fahrenheit(userCelsiusValue);
        System.out.printf(Locale.US, "Температура в Фаренгейтах:%.2f%n", userValue.converter());

        in.close();
    }

    private static double readArgument(Scanner in, String userPrompt){
        while (true){
            System.out.print(userPrompt);
            String input = in.nextLine().trim().replace(',','.');
            try {
                return Double.parseDouble(input);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка:введите число:");
                }
            }

        }
}

class Fahrenheit{
    private double celsiusValue;

    public Fahrenheit(double celsiusValue){

        this.celsiusValue = celsiusValue;
    }

    public double converter(){
        return celsiusValue*1.8 + 32;
    }
}
