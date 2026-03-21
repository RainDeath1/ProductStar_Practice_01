package org.example;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Project project = new Project("Учебный проект");

        Task t1 = new Task("Код", "Написать код", 22);
        Task t2 = new Task("Домашнее задание", "Написать 3 примера", 23);

        project.addTask(t1);
        project.addTask(t2);

        project.start(t1);
        project.complete(t1);

        Collections.sort(project.getTasks(),
                new Project.TaskComparator(Project.TaskComparator.SortType.BY_DATE));

        TaskManager manager = new TaskManager();
        manager.addProject(project);
        manager.showAllTasks();

        ProjectStats.showStats();
    }
}