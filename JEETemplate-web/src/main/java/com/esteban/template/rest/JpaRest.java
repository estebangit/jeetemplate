package com.esteban.template.rest;

import com.esteban.template.dto.CustomerDto;
import com.esteban.template.ejb.session.JpaEJB;
import com.esteban.template.entity.Customer;
import com.esteban.template.entity.Person;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by esteban on 24.03.17.
 */
@Path("/jpa/")
@Produces({"application/json"})
@Consumes({"application/json"})
public class JpaRest {

    @Inject
    private JpaEJB service;

    @GET
    @Path("person")
    public List<Person> getPersons() {
        return service.listPersons();
    }

    @GET
    @Path("customer")
    public List<Customer> getCustomers() {
        return service.listCustomers();
    }

    @POST
    @Path("customer")
    public Customer createCustomer(CustomerDto person) {
        return service.createCustomer(
                person.getFirstName(),
                person.getLastName(),
                person.getPhone(),
                person.getEmail(),
                person.getReference());
    }

    // TODO Ajout test avec
    // https://github.com/rest-assured/rest-assured/wiki/Usage
    // https://github.com/mkotsur/restito/blob/master/guide.md

}
