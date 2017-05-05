package com.esteban.template.rest;

import com.esteban.template.ejb.session.IHelloLocal;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by esteban on 23.03.17.
 */
@Path("/")
public class HelloRest {

    @Inject
    private IHelloLocal service;

    @GET
    @Path("hello")
    @Produces({"application/json"})
    public String getHelloWorldJSON() {
        return service.sayHello("World");
    }

}
