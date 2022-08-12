package com.example.demo.bottApi;

public class Customer {
    private int id;
    private String name;
    private double cell;


    public Customer() {
    }

    public Customer(int id, String name, double cell) {
        this.id = id;
        this.name = name;
        this.cell = cell;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCell() {
        return cell;
    }

    public void setCell(double cell) {
        this.cell = cell;
    }
}
