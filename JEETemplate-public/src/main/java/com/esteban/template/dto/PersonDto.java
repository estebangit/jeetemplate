package com.esteban.template.dto;

import java.io.Serializable;

/**
 * Created by esteban on 24.03.17.
 */
public abstract class PersonDto implements Serializable {

    /**
     * Default value included to remove warning.
     **/
    private static final long serialVersionUID = 1L;

    Long id;

    String firstName;

    String lastName;

    String phone;

    String email;

    public PersonDto() {
        // empty constructor
    }

    public PersonDto(Long id, String firstName, String lastName, String phone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

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
