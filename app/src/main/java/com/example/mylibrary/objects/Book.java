package com.example.mylibrary.objects;

public class Book {
    private String name;
    private String author;
    private String location;
    private String isFree = "free";
    private int date;
    private int id;
    public String getGener() {
        return gener;
    }

    public void setGener(String gener) {
        this.gener = gener;
    }

    private String gener;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Book(String name, String author, int date, int id,String location) {
        this.name = name;
        this.author = author;
        this.date = date;
        this.id = id;
        this.location = location;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String isFree() {
        return isFree;
    }

    public void setFree(String free) {
        isFree = free;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
