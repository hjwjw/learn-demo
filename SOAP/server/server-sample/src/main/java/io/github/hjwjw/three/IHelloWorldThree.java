package io.github.hjwjw.three;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * The IHelloWorldThree class.
 *
 * @author hjwjw
 * @date
 */
@WebService(serviceName = "HelloWorldThree",targetNamespace = "http://three.hjwjw.github.io")
public interface IHelloWorldThree {

    @WebMethod(exclude = true)
    String sayHi(String name);

    @WebResult(name = "toYou") String sayHello(Person person);
}
