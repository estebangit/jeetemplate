package com.esteban.template.ejb.session;

import javax.ejb.Local;

/**
 * Created by esteban on 20.03.17.
 */
@Local
public interface IHelloLocal extends IHelloRemote {

    boolean ping();

}
