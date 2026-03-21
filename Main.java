package org.example;

public class Main {
    public static void main(String[] args) {
        CoffeeShop coffeeShop =new CoffeeShop();

        Coffee coffee =new Coffee("Latte", 1200, "Strong", Size.MEDIUM);
        Tea tea = new Tea("Wild barriers", 800, "fruit tea", Size.LARGE);
        Pastry pastry = new Pastry("Croissant", 600, true);

        coffeeShop.addMenuItem(coffee);
        coffeeShop.addMenuItem(tea);
        coffeeShop.addMenuItem(pastry);

        System.out.println("===Menu===");
        coffeeShop.showMenu();

        CoffeeShop.Order order = coffeeShop.createOrder();

        order.addItem(coffee);
        order.addItem(tea);

        System.out.println("Сумма заказа: " + order.getTotal());
        CoffeeShop.Order.CoffeeShopStats.showStats();

    }
}