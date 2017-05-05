package com.esteban.template.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by esteban on 23.03.17.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person implements Serializable {

    /**
     * Default value included to remove warning.
     **/
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "PERSON_SEQ_GENERATOR", sequenceName = "SEQ_PERSON")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_SEQ_GENERATOR")
    Long id;

    @Column(nullable = false, length = 50)
    String firstName;

    @Column(nullable = false, length = 50)
    String lastName;

    @Column(nullable = false, length = 15)
    String phone;

    @Column(unique = true, nullable = false, length = 50)
    String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" + simpleToString() + '}';
    }

    String simpleToString() {
        return "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'';
    }

}
