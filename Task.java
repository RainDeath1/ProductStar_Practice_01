package org.example;

public class Task {
    private  String title;
    private String description;
    private int dueDate;
    private TaskStatus status;

    public Task(String title, String description, int dueDate){
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = TaskStatus.TODO;
    }


    public final String getTitle(){
        return title;
    }

    public final void setTitle(String title){
        this.title = title;
    }

    public final String getDescription(){
        return description;
    }

    public  final void setDescription(String description){
        this.description = description;
    }

    public final int getDueDate(){
        return  dueDate;
    }

    public  final void setDueDate(int dueDate){
        this.dueDate=dueDate;
    }

    public final TaskStatus getStatus(){
        return  status;
    }

    public final void setStatus(TaskStatus status){
        this.status = status;
    }

}
