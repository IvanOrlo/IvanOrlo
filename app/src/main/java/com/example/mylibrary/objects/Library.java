package com.example.mylibrary.objects;

public class Library {
    private String name;
    private String adress;
    private int id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }


    public Library(String name, String adress, String city) {
        this.name = name;
        this.adress = adress;

    }
}
