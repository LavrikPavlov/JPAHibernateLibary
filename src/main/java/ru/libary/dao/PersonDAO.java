package ru.libary.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import ru.libary.models.Book;
import ru.libary.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;


    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        List<Person> results = jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class));

        if (!results.isEmpty()) {
            return results.get(0);
        } else {
            // Вернуть null или бросить исключение, в зависимости от вашей логики
            return null;
        }
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO Person(name, year_born) VALUES(?,?)",
                person.getName(), person.getYearBorn());
    }

    public void update(Person updatePerson, int id){
        jdbcTemplate.update("UPDATE Person SET name=?, year_born=? WHERE id=?",
                updatePerson.getName(), updatePerson.getYearBorn(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public Optional<Person> getPersonByFullName(String name) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new Object[]{name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }


    public List<Book> getBooksByPersondId(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }

}
