package com.esteban.template.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Created by esteban on 23.03.17.
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Student extends Person {

    /**
     * Default value included to remove warning.
     **/
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 50)
    private String school;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Student{"
                + simpleToString()
                + ", school='" + school + '\''
                + '}';
    }

}
