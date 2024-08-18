package com.art.dao;

import com.art.exception.SqlProcessingException;
import com.art.model.Person;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonDaoImpl implements PersonDao {
    private static final String FIND_ALL = "select * from persons";
    private static final String FIND_BY_ID = "select * from persons where id=?";
    private static final String SAVE = "insert into persons (id, firstname, lastname, midlename, age) values (?, ?, ?, ?, ?)";
    private static final String UPDATE = "update persons set person=? where id=?";
    private static final String DELETE = "delete from persons where id=?";
    private final DataSource dataSource;

    public PersonDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = (long) resultSet.getInt("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String midlename = resultSet.getString("midlename");
                LocalDate birthDate = resultSet.getDate("date_of_birth").toLocalDate();
                Person person = new Person(id, firstname, lastname, midlename, birthDate);
                persons.add(person);
            }
        } catch (SQLException e) {
            throw new SqlProcessingException(e);
        }
        return persons;
    }

    @Override
    public Optional<Person> findById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String midlename = resultSet.getString("midlename");
                LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
                return Optional.of(new Person(id, firstname, lastname, midlename, birthDate));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new SqlProcessingException(e);
        }
    }

    @Override
    public void save(Person person) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
            preparedStatement.setLong(1, person.getId());
            preparedStatement.setString(2, person.getFirstname());
            preparedStatement.setString(3, person.getLastname());
            preparedStatement.setString(4, person.getMidlename());
            preparedStatement.setLong(5, person.getAge());
            //preparedStatement.setDate(6, person.getBirthDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SqlProcessingException(e);
        }
    }

    @Override
    public void update(Long id, Person person) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setLong(1, id);
            //preparedStatement.setPerson(2, person.getFirstname());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SqlProcessingException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SqlProcessingException(e);
        }
    }
}
