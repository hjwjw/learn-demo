package io.github.hjwjw.sample.app.service;

import io.github.hjwjw.sample.app.dto.Person;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * The IHelloWorldThree class.
 *
 * @author hjwjw
 * @date
 */
@WebService(targetNamespace = "http://service.app.sample.hjwjw.github.io")
public interface HelloWorldService {


    @WebResult(name = "toYou") String sayHello(Person person);
}
