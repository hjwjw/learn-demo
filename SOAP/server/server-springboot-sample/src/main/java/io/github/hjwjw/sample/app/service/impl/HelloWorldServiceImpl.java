package io.github.hjwjw.sample.app.service.impl;



import io.github.hjwjw.sample.app.service.HelloWorldService;
import io.github.hjwjw.sample.app.dto.Person;
import org.springframework.stereotype.Component;
import javax.jws.WebService;

/**
 * The HelloWorldThree class.
 * 面向接口的简单WebService发布
 * @author
 * @date
 */

@WebService(serviceName = "HelloWorld",
        endpointInterface = "io.github.hjwjw.sample.app.service.HelloWorldService",//设置服务端点接口，指定提供服务的接口
        targetNamespace = "http://service.app.sample.hjwjw.github.io") //命名空间，服务接口类包名取反
@Component
public class HelloWorldServiceImpl implements HelloWorldService {



    @Override
    public String sayHello(Person person) {
        return "Hello,"+person.getName()+ "you are "+ person.getAge() +"old.";
    }
}
