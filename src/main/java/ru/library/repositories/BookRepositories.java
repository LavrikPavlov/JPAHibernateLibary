package ru.library.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.library.models.Book;
import ru.library.models.Person;

import java.util.List;


@Repository
public interface BookRepositories extends JpaRepository<Book, Integer> {
    List<Book> findByNameStartingWith(String name);

    List<Book> findByPersonBookIsNotNull(Sort var1);
    Page<Book> findByPersonBookIsNotNull(Pageable var1);
    List<Book> findByPersonBookIsNotNull();
}
