package com.my.test.service.person.dao;

import com.my.test.service.person.model.Person;

import java.util.List;

/**
 * Created by vivi on 2017/11/25.
 */
public interface PersonDao {

    void addPerson(Person person);

    void updatePersonByid(Person person);

    List<Person> findAllPerson();
}
