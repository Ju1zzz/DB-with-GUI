package com.company.basicClasses;

public class Works {
    private int id;
    private String title;
    private float hours;
    private float price;
    public Works( int id, String title, float hours, float price){
        this.id=id;
        this.title=title;
        this.hours=hours;
        this.price=price;
    }

    public int getId() {
        return id;
    }

    public float getHours() {
        return hours;
    }

    public float getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }


    @Override
    public String toString() {
        return getId()+" "+getTitle()+" "+getPrice()+" "+getHours();
    }
}
