package com.esteban.template.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Created by esteban on 23.03.17.
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Worker extends Person {

    /**
     * Default value included to remove warning.
     **/
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 50)
    private String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Worker{"
                + simpleToString()
                + "company='" + company + '\''
                + '}';
    }

}
