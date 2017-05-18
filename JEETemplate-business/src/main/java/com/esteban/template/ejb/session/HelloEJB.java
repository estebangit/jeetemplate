package com.esteban.template.ejb.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.util.concurrent.Future;
/**
 * Created by esteban on 20.03.17.
 */
@Stateless
public class HelloEJB implements IHelloLocal, IHelloRemote {

    private static final Logger log = LoggerFactory.getLogger(HelloEJB.class);

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
            log.trace(e.getMessage(), e);
        }
        log.info("returning the result");
        return new AsyncResult<>("Hello " + name);
    }

    @Override
    public boolean ping() {
        return true;
    }

}
