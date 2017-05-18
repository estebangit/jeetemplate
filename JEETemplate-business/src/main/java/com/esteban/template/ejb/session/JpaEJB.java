package com.esteban.template.ejb.session;

import com.esteban.template.entity.Customer;
import com.esteban.template.entity.Person;
import com.esteban.template.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by esteban on 23.03.17.
 */
@Stateless
public class JpaEJB {

    private static final Logger log = LoggerFactory.getLogger(JpaEJB.class);

    @PersistenceContext(unitName = "samplePU")
    private EntityManager entityManager;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Customer createCustomer(String firstName, String lastName, String phone, String email, String reference) {
        Customer person = (Customer) buildPerson(new Customer(), firstName, lastName, phone, email);
        person.setReference(reference);
        entityManager.persist(person);
        log.info("Created {}", person);
        return person;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Student createStudent(String firstName, String lastName, String phone, String email, String school) {
        Student person = (Student) buildPerson(new Student(), firstName, lastName, phone, email);
        person.setSchool(school);
        entityManager.persist(person);
        log.info("Created {}", person);
        return person;
    }

    private Person buildPerson(Person person, String firstName, String lastName, String phone, String email) {
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setPhone(phone);
        person.setEmail(email);
        return person;
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    @SuppressWarnings("unchecked")
    public List<Person> listPersons() {
        log.info("listPersons");
        return entityManager.createQuery("select p from Person p").getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    @SuppressWarnings("unchecked")
    public List<Customer> listCustomers() {
        log.info("listCustomers");
        return entityManager.createQuery("select c from Customer c").getResultList();
    }

}
