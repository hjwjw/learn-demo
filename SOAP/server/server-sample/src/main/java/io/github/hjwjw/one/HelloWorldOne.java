package io.github.hjwjw.one;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * The HelloWorldThree class.
 * 简单的WebService发布
 * @author
 * @date
 */
@WebService
public class HelloWorldOne {

    @WebMethod
    public String sayHi(String name) {
        return "Hi,"+name;
    }

    public String sayHello(String name) {
        return "Hello,"+name;
    }
}
