package com.esteban.template.ejb.session;

import javax.ejb.Remote;
import java.util.concurrent.Future;

/**
 * Created by esteban on 21.03.17.
 */
@Remote
public interface IHelloRemote {

    String sayHello(String name);

    Future<String> sayHelloAsync(String name);

}
