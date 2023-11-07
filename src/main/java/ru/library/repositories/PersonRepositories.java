package ru.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.library.models.Person;

import java.util.Optional;

@Repository
public interface PersonRepositories extends JpaRepository<Person, Integer> {
    Optional<Person> findByName(String name);
}
