package com.my.test.service.person.dao.impl;

import com.my.test.service.person.dao.PersonDao;
import com.my.test.service.person.model.Person;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vivi on 2017/11/25.
 */
@Repository("personDao")
public class PersonDaoImpl extends SqlSessionDaoSupport implements PersonDao {

    public void addPerson(Person person) {
        getSqlSession().insert("Person.addPerson",person);
    }

    public void updatePersonByid(Person person) {
        getSqlSession().update("Person.updatePersonByid",person);
    }

    public List<Person> findAllPerson() {
        return getSqlSession().selectList("Person.findAllPerson");
    }

    @Override
    public Person personDetail(String id) {
        return getSqlSession().selectOne("Person.personDetail",id);
    }

}
