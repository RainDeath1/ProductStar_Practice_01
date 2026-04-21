package org.example;

public class Main {
    public static void main(String[] args) {
        CoworkingSystem coworkingSystem = new CoworkingSystem();

        Workspace first = new Workspace(1,"Standard");
        Workspace second = new Workspace(2, "Calling room");
        Workspace third = new Workspace(3, "VIP");

        coworkingSystem.addWorkspace(first);
        coworkingSystem.addWorkspace(second);
        coworkingSystem.addWorkspace(third);

        User user1 = new User("Иван", "Шаляпин");
        User user2 = new User("Александр", "Якунин");
        User user3 = new User("Иван", "Янковский");
        User user4 = new User("Анна", "Смирнова");

        coworkingSystem.registerUser(user1);
        coworkingSystem.registerUser(user2);
        coworkingSystem.registerUser(user3);
        coworkingSystem.registerUser(user4);


        try {
            coworkingSystem.bookWorkspace(user1, first);
            coworkingSystem.bookWorkspace(user2,second);
            coworkingSystem.bookWorkspace(user1, third);

            coworkingSystem.showAvailableWorkspaces();

            coworkingSystem.cancelBooking(user1,first);
            System.out.println(first.getType());
            coworkingSystem.showAvailableWorkspaces();
            System.out.println(user1.getName());
            System.out.println(user3.getId());
            System.out.println(user4.getSurname());
            System.out.println(user1.getBookedWorkspaces());
        }catch (WorkspaceNotAvailableException|UserNotRegisteredException e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}