package com.example.secondgroupproject;

public class Car {
    private String nameCar;
    private String Color;
    private String Manufactorer;
    private String imageID;
    private String Year;
    private int id;
    private String location;

    public Car(String nameCar, String color, String manufactorer, String imageID, String year, int id, String location) {
        this.nameCar = nameCar;
        this.Color = color;
        this.Manufactorer = manufactorer;
        this.Year = year;
        this.imageID=imageID;
        this.id=id;
        this.location=location;
    }

    public Car() {

    }

//    public subjects( String nameCar, String color, String manufactorer, String year,int imageID) {
//        this.nameCar = nameCar;
//        this.Color = color;
//        this.Manufactorer = manufactorer;
//        this.Year = year;
//        this.imageID=imageID;
//        this.id=id;
//    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getManufactorer() {
        return Manufactorer;
    }

    public void setManufactorer(String manufactorer) {
        Manufactorer = manufactorer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
