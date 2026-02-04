package Practice02;
import java.util.Scanner;
import java.util.Random;

public class GuessGame {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1;
        int tryCount = 0;
        while (true){
            System.out.println("Введите число");
            if(!in.hasNextInt()){
                System.out.println("Введите именно число");
                in.next();
                continue;
            }
            int userGuess = in.nextInt();
            tryCount++;
            if(numberToGuess==userGuess){
                System.out.println("Вы угадали. Количество попыток:" + tryCount);
                break;
            } else if(userGuess < numberToGuess){
                System.out.println("Больше");
            }else {
                System.out.println("Меньше");
                }
            }
        in.close();
        }

    }

