package ru.library.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.library.dao.PersonDAO;
import ru.library.models.Person;
import ru.library.services.PersonServices;

@Component
public class PersonValidator implements Validator {

    private final PersonServices personServices;

    @Autowired
    public PersonValidator(PersonDAO personDAO, PersonServices personServices) {
        this.personServices = personServices;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if(personServices.getPersonByName(person.getName()).isPresent())
            errors.rejectValue("name", "Человек с таким ФИО уже есть");
    }
}
