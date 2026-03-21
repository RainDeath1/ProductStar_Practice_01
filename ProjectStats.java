package org.example;

public class ProjectStats {
    public static int totalTasks = 0;
    public static int completedTasks = 0;

    public static void showStats(){
        System.out.println("Всего задач: " + totalTasks);
        System.out.println("Выполнено: " + completedTasks);
    }
}
