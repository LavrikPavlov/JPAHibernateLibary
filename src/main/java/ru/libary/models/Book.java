package ru.libary.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {

    private int id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(max = 100, message = "Вы превысили количество символов")
    private String author;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(max = 100, message = "Вы превысили количество символов")
    private String name;

    @NotEmpty(message = "Поле не должно быть пустым")
    private int yearProd;

    public Book(){}

    public Book(String author, String name, int yearProd) {
        this.author = author;
        this.name = name;
        this.yearProd = yearProd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearProd() {
        return yearProd;
    }

    public void setYearProd(int yearProd) {
        this.yearProd = yearProd;
    }
}
