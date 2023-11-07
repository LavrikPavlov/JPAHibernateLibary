package ru.library.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(max = 100, message = "Вы превысили количество символов")
    @Column(name = "author")
    private String author;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(max = 100, message = "Вы превысили количество символов")
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "year_prod")
    private int yearProd;


    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person personBook;

    @Column(name = "taken_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenTime;

    @Transient
    private boolean timeOut;

    public Book(){}

    public Book(String author, String name, int yearProd, Person personBook) {
        this.author = author;
        this.name = name;
        this.yearProd = yearProd;
        this.personBook = personBook;
    }

    public Person getPersonBook() {
        return personBook;
    }

    public void setPersonBook(Person personBook) {
        this.personBook = personBook;
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

    public Date getTakenTime() {
        return takenTime;
    }

    public void setTakenTime(Date takenTime) {
        this.takenTime = takenTime;
    }

    public boolean isTimeOut() {
        return timeOut;
    }

    public void setTimeOut(boolean timeOut) {
        this.timeOut = timeOut;
    }
}
