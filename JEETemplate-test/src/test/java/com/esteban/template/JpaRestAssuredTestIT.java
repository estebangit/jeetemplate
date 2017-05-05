package com.esteban.template;

import com.esteban.template.dto.CustomerDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

import static io.restassured.RestAssured.*;

/**
 * REST-assured mock le client et permet de tester le serveur rest.<br>
 * Created by esteban on 29.03.17.
 */
public class JpaRestAssuredTestIT {

    private static final Logger log = LoggerFactory.getLogger(JpaRestAssuredTestIT.class);

    @Before
    public void setUp() {
        // set default port for REST-assured
        RestAssured.port = 8090;

        // set default URI for REST-assured.
        RestAssured.baseURI = "http://localhost/JEETemplate/rest/";
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetCustomers() {
        final String response =
                given().
                        when().
                        get("/jpa/customer").
                        then().
                        statusCode(200).
                        contentType(ContentType.JSON).
                        extract().asString();

        log.info("-> {}", response);
        assertNotNull(response);
    }

    @Test
    public void testGetPersons() {
        final String result =
                given().
                        header("Accept-Encoding", "application/json").
                        when().
                        get("/jpa/person").
                        then().
                        statusCode(200).
                        contentType(ContentType.JSON).
                        extract().asString();
        log.info("Result -> {}", result);
    }

    @Test
    public void testCreateCustomer() {
        given().
                contentType("application/json").
                body(builCustomer(1), ObjectMapperType.JACKSON_2).
                when().
                post("/jpa/customer").
                then().
                statusCode(200).
                contentType(ContentType.JSON);
    }

    private CustomerDto builCustomer(final int idx) {
        final String firstName = "Jean" + idx;
        final String lastName = "Dupond" + idx;
        final CustomerDto dto = new CustomerDto();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setEmail(firstName + "." + lastName + "@toto.org");
        dto.setPhone("010000000" + idx);
        dto.setReference("CUST000" + idx);
        return dto;
    }

}
