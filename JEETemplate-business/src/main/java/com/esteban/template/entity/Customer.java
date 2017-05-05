package com.esteban.template.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Created by esteban on 23.03.17.
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Customer extends Person {

    /**
     * Default value included to remove warning.
     **/
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 50)
    private String reference;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "Customer{"
                + simpleToString()
                + "reference='" + reference + '\''
                + '}';
    }

}
