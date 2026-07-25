package org.example.entity;

public class Reader {
    private int id;
    private String name;
    private String phone;

    public Reader(){};

    public Reader(int id, String name, String phone){
        this.name = name;
        this.id = id;
        this.phone = phone;
    }

    public Reader(String name, String phone){
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString(){
        return "Reader{" + "id=" +id
                + ",name = " + name + '\'' +
                ", phone = '" + phone + '\''
                + "}";
    }
}
