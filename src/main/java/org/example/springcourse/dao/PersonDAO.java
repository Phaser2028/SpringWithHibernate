package org.example.springcourse.dao;

import org.example.springcourse.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();

        List<Person> people = session.createQuery("select p from Person p", Person.class)
                .getResultList();

        return people;
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();

        Person p = session.get(Person.class, id);

        return p;

    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();

        session.save(person);

    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();

        Person p = session.get(Person.class, id);

        p.setName(updatedPerson.getName());
        p.setAge(updatedPerson.getAge());

        session.update(p);

    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        session.delete(person);
    }
}
