package ru.library.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.models.Book;
import ru.library.models.Person;
import ru.library.repositories.BookRepositories;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServices {

    private final BookRepositories bookRepositories;

    @Autowired
    public BookServices(BookRepositories bookRepositories) {
        this.bookRepositories = bookRepositories;
    }

    public List<Book> findAll(){
        return bookRepositories.findAll();
    }

    public Book findOne(int id){
        Optional<Book> foundBook = bookRepositories.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void release(int id){
        Book bookOwner = bookRepositories.getOne(id);
        bookOwner.setPersonBook(null);
        bookRepositories.save(bookOwner);
    }

    @Transactional
    public void assign(int id, Person selectedPerson){
        Book bookOwner = bookRepositories.getOne(id);
        bookOwner.setPersonBook(selectedPerson);
        bookRepositories.save(bookOwner);
    }

    @Transactional
    public void save(Book book) {
        bookRepositories.save(book);
    }

    @Transactional
    public void update(int id, Book updateBook) {
        updateBook.setId(id);
        bookRepositories.save(updateBook);
    }

    @Transactional
    public void delete(int id) {
        bookRepositories.deleteById(id);
    }
}
