package org.example;

import java.util.TreeMap;
import java.util.TreeSet;

public class CoworkingSystem implements BookingManager{

    private TreeSet<Workspace> workspaces;
    private TreeMap<User, TreeSet<Workspace>> bookings;

    public CoworkingSystem(){
        workspaces = new TreeSet<>();
        bookings = new TreeMap<>();
    }

    public void addWorkspace(Workspace workspace){
        workspaces.add(workspace);
        System.out.println("Добавлено рабочее место: " + workspace);
    }

    public void removeWorkspace(Workspace workspace){
        workspaces.remove(workspace);
        System.out.println("Рабочее место удалено: " + workspace);
    }

    public void registerUser(User user){
        bookings.put(user, new TreeSet<>());
        System.out.println("Зарегистрирован новый пользователь: " + user);
    }

    @Override
    public void bookWorkspace(User user, Workspace workspace)
            throws WorkspaceNotAvailableException, UserNotRegisteredException {
        if (!bookings.containsKey(user)){
            throw new UserNotRegisteredException("Пользователь не зарегистрирован");
        }
        if (!workspace.isAvailable()){
            throw new WorkspaceNotAvailableException(
                    "Рабочее место " + workspace.getNumber() + " уже занято"
            );
        }

        workspace.markAsBooked();
        user.bookWorkspace(workspace);
        bookings.get(user).add(workspace);
        System.out.println(user + " отменяет бронирование рабочего места №" + workspace.getNumber());

    }

    @Override
    public void cancelBooking(User user, Workspace workspace)
            throws UserNotRegisteredException {
        if (!bookings.containsKey(user)){
            throw new UserNotRegisteredException("Пользователь не зврегистрирован");
        }
        workspace.markAsAvailable();
        user.cancelBooking(workspace);
        bookings.get(user).remove(workspace);

        System.out.println(user + " отменяет бронирование рабочего места №" +
                workspace.getNumber());
    }

    public void showAvailableWorkspaces(){
        System.out.println("\nСписок доступных рабочих мест: ");

        boolean found = false;

        for(Workspace workspace : workspaces){
            if(workspace.isAvailable()){
                System.out.println(workspace);
                found = true;
            }
        }
        if(!found){
            System.out.println("Свободных рабочих мест - не имеется");
        }
    }
}
