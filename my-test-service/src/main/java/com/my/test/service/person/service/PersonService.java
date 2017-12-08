package com.my.test.service.person.service;

import com.my.test.service.person.model.Person;

import java.util.List;

/**
 * Created by vivi on 2017/11/25.
 */
public interface PersonService {

    void addPerson(Person person);

    void updatePersonByid(Person person);

    List<Person> findAllPerson();
}
