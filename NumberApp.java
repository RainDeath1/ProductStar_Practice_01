package org.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class NumberApp {

    private static final int SIZE = 100_000;
    private static final int[] numbers = new int[SIZE];


    private static final AtomicInteger twoDigit = new AtomicInteger(0);
    private static final AtomicInteger threeDigit = new AtomicInteger(0);
    private static final AtomicInteger fourDigit = new AtomicInteger(0);

    private static final Random random = new Random();

    public static void main(String[] args) {

        generateArray();

        Thread t1 = new Thread(new TwoDigitChecker());
        Thread t2 = new Thread(new ThreeDigitChecker());
        Thread t3 = new Thread(new FourDigitChecker());

        t1.start();
        t2.start();
        t3.start();

        try{
            t1.join();
            t2.join();
            t3.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Двузначных чисел: " + twoDigit.get() + " шт.");
        System.out.println("Трехзначных чисел: " + threeDigit.get() + " шт.");
        System.out.println("Четырехзначных чисел: " + fourDigit.get() + " шт.");
    }

    private static void generateArray(){
        for (int i = 0; i < SIZE; i++) {
            numbers[i]=generateNumber(10, 9999);
        }
    }

    private static int generateNumber(int min, int max){
        return random.nextInt(max - min + 1)+min;
    }

    static class TwoDigitChecker implements Runnable{
        @Override
        public void run(){
            for (int num : numbers){
                if (num>=10 && num <= 99){
                    twoDigit.incrementAndGet();
                }
            }
        }
    }
    static class ThreeDigitChecker implements Runnable{
        @Override
        public void run(){
            for (int num : numbers){
                if (num>=100 && num <= 999){
                    threeDigit.incrementAndGet();
                }
            }
        }
    }

    static class FourDigitChecker implements Runnable{
        @Override
        public void run(){
            for (int num : numbers){
                if (num>=1000 && num <= 9999){
                    fourDigit.incrementAndGet();
                }
            }
        }
    }
}
