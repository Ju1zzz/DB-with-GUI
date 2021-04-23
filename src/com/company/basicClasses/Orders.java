package com.company.basicClasses;

public class Orders {
    private int id;
    private String work_name = "";
    private String emp_name="";
    private String comment;
    private  String car;
    public Orders(int id, String work_name, String emp_name,String car, String comment){
        this.id=id;
        this.work_name=work_name;
        this.emp_name=emp_name;
        this.comment=comment;
        this.car=car;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", work='" + work_name + '\'' +
                ", emp='" + emp_name + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getWork() {
        return work_name;
    }

    public String getEmp() {
        return emp_name;
    }

    public String getComment() {
        return comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmp(String emp) {
        this.emp_name = emp_name;
    }

    public void setWork(String work) {
        this.work_name = work_name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCar() {
        return car;
    }
}
