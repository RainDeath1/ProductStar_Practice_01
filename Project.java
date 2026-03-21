package org.example;
import  java.util.*;
public class Project implements Manageable {
    private String name;
    private List<Task> tasks = new ArrayList<>();

    public Project(String name){
        this.name = name;
    }

    public  void addTask(Task task){
        tasks.add(task);
        ProjectStats.totalTasks++;
    }


    public  List<Task> getTasks(){
        return tasks;
    }


    @Override
    public void assign(Task task) {
        System.out.println("Задача назначена: " +task.getTitle());
    }

    @Override
    public void start(Task task) {
        task.setStatus(TaskStatus.IN_PROGRESS);
    }

    @Override
    public void complete(Task task) {
        task.setStatus(TaskStatus.DONE);
        ProjectStats.completedTasks++;
    }

    public static class TaskComparator implements Comparator<Task>{
        public enum SortType{
            BY_TITLE,
            BY_DATE
        }

        private  SortType type;

        public TaskComparator(SortType type){
            this.type = type;
        }
        @Override
        public int compare(Task t1, Task t2){
            if(type == SortType.BY_TITLE){
                return t1.getTitle().compareTo(t2.getTitle());
            }else {
                return Integer.compare(t1.getDueDate(), t2.getDueDate());
            }
        }
    }
}
