package org.example;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Project> projects = new ArrayList<>();

    public void addProject(Project project){
        projects.add(project);
    }

    public void showAllTasks(){
        for (Project project : projects){
            for (Task task : project.getTasks()){
                System.out.println(task.getTitle() + " - " + task.getStatus());
            }
        }
    }
}
