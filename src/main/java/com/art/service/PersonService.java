package com.art.service;

import com.art.model.Person;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface PersonService {
    List<Person> findAll();

    Person findById(Long id);

    void save(Person person);

    void update(Long id, Person person) throws SQLException;

    void delete(Long id) throws SQLException;
}
