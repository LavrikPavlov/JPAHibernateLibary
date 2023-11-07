package ru.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.models.Book;
import ru.library.models.Person;
import ru.library.repositories.BookRepositories;

import java.util.Date;
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

    public List<Book> findAll(boolean sortByYearProd, boolean sortByHaveNotPerson){
        if (sortByYearProd && sortByHaveNotPerson) {
            return bookRepositories.findByPersonBookIsNotNull(Sort.by("yearProd"));
        } else if (sortByYearProd) {
            return bookRepositories.findAll(Sort.by("yearProd"));
        } else if (sortByHaveNotPerson) {
            return bookRepositories.findByPersonBookIsNotNull();
        } else {
            return bookRepositories.findAll();
        }
    }

    public Book findOne(int id){
        Optional<Book> foundBook = bookRepositories.findById(id);
        return foundBook.orElse(null);
    }

    public List<Book> findWithPagination(int page, int booksPerPagem,
                                         boolean sortByYearProd, boolean sortByHaveNotPerson){
        if (sortByYearProd && sortByHaveNotPerson) {
            return bookRepositories.findByPersonBookIsNotNull(PageRequest.of(page, booksPerPagem,
                            Sort.by("yearProd"))).getContent();
        } else if (sortByYearProd) {
            return bookRepositories.findAll(PageRequest.of(page, booksPerPagem,
                            Sort.by("yearProd"))).getContent();
        } else if (sortByHaveNotPerson) {
            return bookRepositories.findByPersonBookIsNotNull(PageRequest.of(page, booksPerPagem)).getContent();
        } else {
            return bookRepositories.findAll(PageRequest.of(page, booksPerPagem)).getContent();
        }

    }

    public List<Book> serchByNameBook(String name){
        return bookRepositories.findByNameStartingWith(name);
    }

    public Person getBookPerson(int id){
        return bookRepositories.findById(id).map(Book::getPersonBook).orElse(null);
    }

    @Transactional
    public void release(int id){
//        Book bookOwner = bookRepositories.getOne(id);
//        bookOwner.setPersonBook(null);
//        bookOwner.setTakenTime(null);
//        bookRepositories.save(bookOwner);

        bookRepositories.findById(id).ifPresent(book -> {
            book.setPersonBook(null);
            book.setTakenTime(null);
        });
    }

    @Transactional
    public void assign(int id, Person selectedPerson){
//        Book bookOwner = bookRepositories.getOne(id);
//        bookOwner.setPersonBook(selectedPerson);
//        bookOwner.setTakenTime(new Date());
//        bookRepositories.save(bookOwner);

        bookRepositories.findById(id).ifPresent(book -> {
            book.setPersonBook(selectedPerson);
            book.setTakenTime(new Date());
        });
    }

    @Transactional
    public void save(Book book) {
        bookRepositories.save(book);
    }

    @Transactional
    public void update(int id, Book updateBook) {
        Book bookWillUpdate = bookRepositories.findById(id).get();

        updateBook.setId(id);
        updateBook.setPersonBook(bookWillUpdate.getPersonBook());

        bookRepositories.save(updateBook);
    }

    @Transactional
    public void delete(int id) {
        bookRepositories.deleteById(id);
    }
}
