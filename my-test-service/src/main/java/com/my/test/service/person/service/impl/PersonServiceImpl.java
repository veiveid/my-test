package com.my.test.service.person.service.impl;

import com.my.test.service.person.dao.PersonDao;
import com.my.test.service.person.model.Person;
import com.my.test.service.person.service.PersonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vivi on 2017/11/25.
 */
@Transactional
@Service("personService")
public class PersonServiceImpl implements PersonService {

    private static final Log logger = LogFactory.getLog(PersonServiceImpl.class);

    @Autowired
    private PersonDao personDao;

    public void addPerson(Person person) {
        personDao.addPerson(person);
    }

    public void updatePersonByid(Person person) {
        personDao.updatePersonByid(person);
        Person p = new Person();
        p.setAddress("山东");
        p.setAge(23);
        p.setName("宋小二");
        /*String str = null;
        str.split("23");事务测试*/
        this.addPerson(p);
    }

    public List<Person> findAllPerson() {
        return personDao.findAllPerson();
    }

    @Override
    public Person personDetail(String id) {
        return personDao.personDetail(id);
    }
}
