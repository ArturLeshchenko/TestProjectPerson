package com.art.dao;

import com.art.model.Person;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

//@Repository
public class PersonDaoInMemory implements PersonDao {
    private static Long id = 0L;
    private final Map<Long, Person> personMap = new HashMap<>();

    @Override
    public List<Person> findAll() {
        return new ArrayList<>(personMap.values());
    }

    @Override
    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(personMap.get(id));
    }

    @Override
    public void save(Person person) {
        person.setId(++id);
        personMap.put(person.getId(), person);
    }

    @Override
    public void update(Long id, Person person) {
        person.setId(id);
        personMap.put(id, person);
    }

    @Override
    public void delete(Long id) {
        personMap.remove(id);
    }
}
