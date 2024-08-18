package com.art.controller;


import com.art.model.Person;
import com.art.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/api/v1/persons")
    public List<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping("/api/v1/persons/{id}")
    public Person findById(@PathVariable(name = "id") Long id) {
        return personService.findById(id);
    }

    @PostMapping("api/v1/persons")
    public void save(@RequestBody Person person) {
        personService.save(person);
    }

    @DeleteMapping("/api/v1/persons/{id}")
    public void delete(@PathVariable(name = "id") Long id) throws SQLException {
        personService.delete(id);
    }

    @PutMapping("/api/v1/persons/{id}")
    public void update(@PathVariable(name = "id") Long id, @RequestBody Person person) throws SQLException {
        personService.update(id, person);
    }

}
