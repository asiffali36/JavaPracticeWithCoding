package com.example.demo.bottApi;

public class ModelClass {
    private String name;
    private String status;
    private String imageUrl;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ModelClass(int id, String name, String status, String imageUrl) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.imageUrl = imageUrl;
    }

    public ModelClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "modelClass{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
