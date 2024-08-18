package com.art.dao;

import com.art.model.Person;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PersonDao {
    List<Person> findAll();

    Optional<Person> findById(Long id);

    void save(Person person);

    void update(Long id, Person person);

    void delete(Long id);
}
