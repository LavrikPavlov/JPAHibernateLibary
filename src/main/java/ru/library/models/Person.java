package ru.library.models;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 100, message = "Ваше имя слишком длинное или короткое")
    @Column(name = "name")
    private String name;

    @Min(value = 1900 ,message = "Год рождения указан неверно")
    @Column(name = "year_born")
    private int yearBorn;

    @OneToMany(mappedBy = "personBook")
    private List<Book> bookList;

    public Person() {};

    public Person(String name, int year_born, List<Book> bookList) {
        this.name = name;
        this.yearBorn = year_born;
        this.bookList = bookList;
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

    public int getYearBorn() {
        return yearBorn;
    }

    public void setYearBorn(int yearBorn) {
        this.yearBorn = yearBorn;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
