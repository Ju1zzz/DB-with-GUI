package com.company.basicClasses;

public class Clients {
    private int id;
    private String name;
    private String phone;
    private  String car;
    public Clients(int id, String name, String phone, String car){
        this.id=id;
        this.name=name;
        this.phone=phone;
        this.car=car;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCar() {
        return car;
    }

    @Override
    public String toString() {
        return getId()+" "+getName();
    }
}