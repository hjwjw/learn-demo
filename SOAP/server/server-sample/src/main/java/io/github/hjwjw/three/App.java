package io.github.hjwjw.three;

import io.github.hjwjw.three.impl.HelloWorldThree;

import javax.xml.ws.Endpoint;

/**
 * The App class.
 *
 * @author
 * @date
 */
public class App {
    public static void main(String[] args) {
        IHelloWorldThree helloWorldThree = new HelloWorldThree();
        //自定义一个服务地址
        String address = "http://127.0.0.1:7856/ws/three/hello";
        //使用端点服务，将对象绑定到一个地址和端口，同时必须在端口后给服务取一个名称
        Endpoint.publish(address,helloWorldThree);
        //WebService服务说明文档wsdl地址
        System.out.println("Wsdl地址：" + address + "?wsdl");
    }
}
