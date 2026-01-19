import java.util.Scanner;

public class Rectangle {

    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);

        double width = readPositiveArgument(in, "Введите ширину прямоугольника: ");
        double length = readPositiveArgument(in,"Введите длину прямоугольника: ");

        RectangleFigure rectangle = new RectangleFigure(length, width);
        // Выводи результата с двумя данными после заяпятой
        System.out.printf(java.util.Locale.US,"Площадь прямоугольника: %.2f%n", rectangle.getArea());

        in.close();

    }

    private static double readPositiveArgument(Scanner in, String userPrompt){
        while (true) {
            System.out.print(userPrompt);
            String input = in.nextLine().trim().replace(',', '.');

            try {
                double value = Double.parseDouble(input);
                if (value <= 0) {
                    System.out.println("Значение должно быть положительным и больше нуля. Попробуйте снова.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка:введите положительное число(например: 2, 10.4 и т.п)");
            }
        }
    }
}



class RectangleFigure{
    private double length;
    private double width;

    public RectangleFigure(double length, double width){
        if(length <=0 || width <=0){
        throw new IllegalArgumentException("Длина и ширина должны быть положительными");
        }

        this.length = length;
        this.width  = width;
    }

    public double getArea(){
        return length*width;
    }
}
