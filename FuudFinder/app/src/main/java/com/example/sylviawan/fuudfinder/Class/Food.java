package com.example.sylviawan.fuudfinder.Class;

public class Food {

    private String name, address, type, rating;

    public Food(String name, String address, String type, String rating) {
        this.name = name;
        this.address = address;
        this.type = type;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }

    public String getRating() {
        return rating;
    }
}
