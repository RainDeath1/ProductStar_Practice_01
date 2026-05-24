package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CafeSimulation {
    private static final List<String> orders = new ArrayList<>();

    private static boolean cafeOpen = true;

    private static final  int MAX_DISHES = 10;

    public static void main(String[] args) {
        Thread chef = new Thread(new Chef());

        Thread visitor1 = new Thread(new Visitor(1));
        Thread visitor2 = new Thread(new Visitor(2));
        Thread visitor3 = new Thread(new Visitor(3));

        chef.start();

        visitor1.start();
        visitor2.start();
        visitor3.start();

        try{
            chef.join();

            visitor1.join();
            visitor2.join();
            visitor3.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("\nКафе закрылось");
    }

    static class Chef implements Runnable{
        private final String[] dishes ={
                "Бургер",
                "Суп",
                "Борщ",
                "Пицца",
                "Салат",
                "Донер",
                "Паста",
                "Стейк",
                "Роллы",
                "Омлет"
        };
        @Override
        public void run(){
            for (int i = 0; i < MAX_DISHES; i++) {
                try {
                    Thread.sleep(3000);//повар готовит блюдо 20 секунд
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                synchronized (orders){
                    String dish = dishes[i];
                    orders.add(dish);
                    System.out.println("Повар приготовил "+ dish);
                    orders.notifyAll();
                }
            }

            synchronized (orders){
                cafeOpen = false;
                orders.notifyAll();
            }
        }
    }

    static class Visitor implements Runnable{
        private final int id;
        private final Random random = new Random();

        public Visitor(int id){
            this.id = id;
        }

        @Override
        public void run(){
            System.out.println("Посетитель " + id +" зашел в кафе");

            while (true){
                synchronized (orders){
                    while (orders.isEmpty() && cafeOpen){
                        System.out.println("Еды нет, посетитель " + id + " ждет");
                        try {
                            orders.wait();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    if (orders.isEmpty() && !cafeOpen){
                        System.out.println("Посетитель " + id + " ушел домой");
                        break;
                    }
                    String dish = orders.remove(0);
                    System.out.println("Посетитель " + id + " съел " + dish);
                }
                try {
                    Thread.sleep(random.nextInt(2000) + 1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}