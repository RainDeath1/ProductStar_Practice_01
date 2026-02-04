package Practice02;
import java.util.Scanner;

public class TimesTable {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Введите число от 1 до 9:");
        int number = in.nextInt();
        if(number>=1 && 9>=number){
            for (int i=1; i<=10;i++){
                System.out.println(number + "*" + i + "=" +(number*i));
            }
        }else{
            System.out.println("Число должно быть от 1 до 9");
            }
        in.close();
    }
}
