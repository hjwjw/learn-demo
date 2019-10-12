package io.github.hjwjw.three.impl;

import io.github.hjwjw.three.IHelloWorldThree;
import io.github.hjwjw.three.Person;

import javax.jws.WebService;

/**
 * The HelloWorldThree class.
 * 面向接口的简单WebService发布
 * @author
 * @date
 */

@WebService(endpointInterface = "io.github.hjwjw.three.IHelloWorldThree",//设置服务端点接口，指定提供服务的接口
        targetNamespace = "http://three.hjwjw.github.io") //命名空间，服务接口类包名取反
public class HelloWorldThree implements IHelloWorldThree {

    @Override
    public String sayHi(String name) {
        return "Hi,"+name;
    }

    @Override
    public String sayHello(Person person) {
        return "Hello,"+person.getName()+ "you are "+ person.getAge() +"old.";
    }
}
