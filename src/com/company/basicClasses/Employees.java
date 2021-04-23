package com.company.basicClasses;

public class Employees {
    private int id;
    private String name;
    private String job;
    public Employees(int id, String name, String job){
        this.id=id;
        this.name=name;
        this.job=job;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return getId()+" "+getJob()+" "+getName();
    }
}