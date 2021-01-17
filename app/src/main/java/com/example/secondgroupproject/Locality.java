package com.example.secondgroupproject;

public class Locality {
    private String price;
    private String location;
    private String localityType;
    private String surface;
    private String imageID;
    private String rooms;
    private String description;

    public Locality(String price, String location, String localityType, String surface, String imageID, String rooms, String description) {
        this.price = price;
        this.location = location;
        this.localityType = localityType;
        this.surface = surface;
        this.imageID = imageID;
        this.rooms = rooms;
        this.description = description;
    }

    public Locality() {

    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocalityType() {
        return localityType;
    }

    public void setLocalityType(String localityType) {
        this.localityType = localityType;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
