package org.example;

import java.util.TreeSet;
import java.util.UUID;

public class User  implements Comparable<User>{
    private final String name;
    private final String surname;
    private final UUID id;
    private final TreeSet<Workspace> bookedWorkspaces;

    public User(String name, String surname){
        this.name = name;
        this.surname = surname;
        this.id = UUID.randomUUID();
        this.bookedWorkspaces = new TreeSet<>();
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public UUID getId(){
        return id;
    }

    public TreeSet<Workspace> getBookedWorkspaces(){
        return bookedWorkspaces;
    }

    public void bookWorkspace(Workspace workspace){
        bookedWorkspaces.add(workspace);
    }

    public void cancelBooking(Workspace workspace){
        bookedWorkspaces.remove(workspace);
    }

    @Override
    public int compareTo(User other) {
        int surnameCompare = this.surname.compareTo(other.surname);
        if(surnameCompare!=0){
            return surnameCompare;
        }
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString(){
        return name + " " + surname;
    }
}
