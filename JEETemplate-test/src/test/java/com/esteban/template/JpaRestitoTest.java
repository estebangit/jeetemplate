package com.esteban.template;

import com.xebialabs.restito.server.StubServer;
import com.xebialabs.restito.support.junit.NeedsServer;
import com.xebialabs.restito.support.junit.ServerDependencyRule;
import io.restassured.RestAssured;
import org.glassfish.grizzly.http.Method;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.xebialabs.restito.semantics.Action.ok;
import static org.junit.Assert.assertEquals;
import static io.restassured.RestAssured.expect;
import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.builder.verify.VerifyHttp.verifyHttp;
import static com.xebialabs.restito.semantics.Action.status;
import static com.xebialabs.restito.semantics.Condition.*;

/**
 * Restito permet de mocke l'api rest. Il cre pour cela un serveur web, sur un port disponible.<br>
 * Rest assured mock le client et permet de tester le serveur rest.<br>
 * Created by esteban on 29.03.17.
 */
public class JpaRestitoTest {

    private static final Logger log = LoggerFactory.getLogger(JpaRestitoTest.class);

    private StubServer server;

    @Rule
    public ServerDependencyRule serverDependency = new ServerDependencyRule();

    @Before
    public void setUp() {
        if (serverDependency.isServerDependent()) {
            server = new StubServer().run();
            RestAssured.port = server.getPort();
        }
    }

    @After
    public void tearDown() {
        if (server != null) {
            server.stop();
        }
    }

    @Test
    public void testCalculer() {
        assertEquals(new Long(4).toString(), "4");
    }

    @Test
    @NeedsServer
    public void testGetCustomers() {
        log.info("Debut");

        // Restito
        whenHttp(server).
                match(endsWithUri("/rest/jpa/customer")).
                //      then(status(HttpStatus.OK_200));
                        then(ok());

        log.info("Get");

        // Rest-assured
        expect().statusCode(200).when().get("/rest/jpa/customer");

        log.info("Verify is {}", 200);

        // Restito
        verifyHttp(server).once(
                method(Method.GET),
                uri("/rest/jpa/customer")
        );

        log.info("Fin");
    }

    @Test
    @NeedsServer
    public void testGetPersons() {
        // Restito
        whenHttp(server).
                match(endsWithUri("/rest/jpa/person")).
                then(status(HttpStatus.OK_200));
        // Rest-assured
        expect().statusCode(200).when().get("/rest/jpa/person");
        // Restito
        verifyHttp(server).once(
                method(Method.GET),
                uri("/rest/jpa/person")
        );
    }

}