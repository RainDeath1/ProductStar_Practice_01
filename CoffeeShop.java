package org.example;

import java.util.ArrayList;
import java.util.List;

public class CoffeeShop {
    private List<MenuItem> menu = new ArrayList<>();

    public void addMenuItem(MenuItem item){
        menu.add(item);
    }

    public void showMenu(){
        for (MenuItem item : menu){
            System.out.println(item.getName() + " - " + item.getPrice());
        }
    }

    public  Order createOrder(){
        return new Order();
    }

    public class Order{
        private List<MenuItem> items = new ArrayList<>();
        public void addItem(MenuItem item){
            items.add(item);
            CoffeeShopStats.totalOrders++;
            CoffeeShopStats.totalRevenue += item.getPrice();

            if(item instanceof Preparable){
                ((Preparable)item).prepare();
            }
        }

        public int getTotal(){
            int sum = 0;
                    for(MenuItem item : items){
                        sum += item.getPrice();
                    }
                    return sum;
        }

        public static class CoffeeShopStats{
            public static int totalOrders = 0;
            public static int totalRevenue = 0;

            public static void showStats(){
                System.out.println("Всего продано товаров: " + totalOrders);
                System.out.println("Общая выручка: " + totalRevenue);
            }
        }
    }
}
