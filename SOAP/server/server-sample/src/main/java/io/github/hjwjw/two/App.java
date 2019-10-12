package io.github.hjwjw.two;


import javax.xml.ws.Endpoint;

/**
 * The App class.
 *
 * @author
 * @date
 */
public class App {

    public static void main(String[] args) {
        String address = "http://127.0.0.1:7856/ws/two/hello";
        Endpoint.publish(address,new HelloWorldTwo());
        System.out.println("Wsdl地址：" + address + "?wsdl");
    }
}
