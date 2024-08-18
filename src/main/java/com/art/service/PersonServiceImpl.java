package com.art.service;


import com.art.dao.PersonDao;
import com.art.model.Person;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonDao personDao;

    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public List<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    public Person findById(Long id) {
        return personDao.findById(id).orElseThrow(() -> new RuntimeException("Person not Found"));
    }

    @Override
    public void save(Person person) {
        person.setAge(calculateAge(person.getBirthDate()));
        personDao.save(person);
    }

    @Override
    public void update(Long id, Person person) {
        person.setAge(calculateAge(person.getBirthDate()));
        personDao.update(id, person);
    }

    @Override
    public void delete(Long id) {
        personDao.delete(id);
    }

    private long calculateAge(LocalDate birthDate) {
        return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }


}
