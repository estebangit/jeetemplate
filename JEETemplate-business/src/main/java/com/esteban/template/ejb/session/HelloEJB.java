package com.esteban.template.ejb.session;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.concurrent.Future;
import java.util.logging.Logger;

/**
 * Created by esteban on 20.03.17.
 */
@Stateless
public class HelloEJB implements IHelloLocal, IHelloRemote {

    private static final Logger log = Logger.getLogger(HelloEJB.class.getName());

    private final long sleepTime = 5000;

    @Override
    public String sayHello(final String name) {
        return "Hello " + name;
    }

    @Asynchronous
    @Override
    public Future<String> sayHelloAsync(String name) {
        log.info("Will wait for " + sleepTime + "ms");
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
        }
        log.info("returning the result");
        return new AsyncResult<>("Hello " + name);
    }

    @Override
    public boolean ping() {
        return true;
    }

}
