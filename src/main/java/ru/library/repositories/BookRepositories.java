package ru.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.library.models.Book;


@Repository
public interface BookRepositories extends JpaRepository<Book, Integer> {
}
