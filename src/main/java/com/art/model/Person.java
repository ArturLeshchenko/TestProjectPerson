package com.art.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Person {
    private Long id;
    private String firstname;
    private String lastname;
    private String midlename;
    private Long age;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthDate;


    public Person(Long id, String firstname, String lastname, String midlename, LocalDate birthDate) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.midlename = midlename;
        this.birthDate = birthDate;
    }

    public Person() {

    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMidlename() {
        return midlename;
    }

    public Long getAge() {
        return age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setMidlename(String midlename) {
        this.midlename = midlename;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
