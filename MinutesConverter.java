import java.util.Scanner;
public class MinutesConverter {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        double minutes = readTotalMinutes(in);
        System.out.print("Всего минут:" + minutes);
    }
    private static double readTotalMinutes(Scanner in){

        while (true){
            System.out.print("Введите время:");

            String input = in.nextLine().toLowerCase().replace(',','.').trim();

            if(input.isEmpty()){
                System.out.println("Ошибка: пустой ввод");
                continue;
            }

            if(input.matches("\\d+(\\.\\d+)?")){
                double hours = Double.parseDouble(input);
                if(hours<0){
                    System.out.println("Время не может быть отрицательным");
                    continue;
                }
                return Minutes.toMinutes(hours);
            }

            String[] parts = input.split("\\D+");

            if(parts.length ==2){
                try{
                    double hours = Double.parseDouble(parts[0]);
                    double minutes = Double.parseDouble(parts[1]);

                    if(hours<0 || minutes<0 || minutes>59){
                        System.out.println("Ошибка: Минуты не могут иметь отрицательнок значение и не могу быть больше 59");
                        continue;
                    }
                    return Minutes.toMinutes(hours, minutes);
                } catch (NumberFormatException ignored){

                }
            }
            System.out.println("Ошибка, неверный формат ввода");
        }

    }
}


class Minutes{

    public static double toMinutes(double hours, double minutes){

        return hours * 60 + minutes;
    }

    public static double toMinutes(double hours){
        return hours*60;
    }
}
